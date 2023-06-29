package main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import main.bll.inventory.StoreIssuesBLL;
import main.dao.storeIssues.StoreIssueDao;
import main.models.productModels.entities.ProductStock;
import main.models.productModels.inputModels.ProductsProductIdInputModel;
import main.models.productModels.outputModels.ProductOutput;
import main.models.storeIssueModels.entities.StoreIssueData;
import main.models.storeIssueModels.entities.StoreIssues;
import main.models.storeIssueModels.inputModels.StoreFilters;
import main.models.storeIssueModels.inputModels.StoreIssuesList;
import main.models.storeIssueModels.outputModels.StoreIssueIds;
import main.models.storeIssueModels.outputModels.StoreIssueProducts;
import main.models.storeIssueModels.outputModels.StoreIssuesData;
import main.models.storeModels.inputmodels.StoreId;
import main.service.inventory.StoreIssueService;

@Controller
public class StoreIssuesController {
	private final StoreIssueDao storeIssueDao;
	private final StoreIssuesBLL storeIssuesBLL;
	private final StoreIssueService storeIssueService;
	private final ModelMapper modelMapper;

	@Autowired
	public StoreIssuesController(StoreIssueDao storeIssueDao, StoreIssuesBLL storeIssuesBLL,
			StoreIssueService storeIssueService, ModelMapper modelMapper) {
		this.storeIssueDao = storeIssueDao;
		this.storeIssuesBLL = storeIssuesBLL;
		this.storeIssueService = storeIssueService;
		this.modelMapper = modelMapper;
	}

	// ObjectMapper for JSON serialization/deserialization
	ObjectMapper objectMapper = new ObjectMapper();

	private static final Logger logger = LoggerFactory.getLogger(StoreIssuesController.class);

	// Getting Batch Numbers and Purchase Sale Price
	@PostMapping("/getBatchNumbers")
	public @ResponseBody List<ProductOutput> getBatchNumbers(@RequestBody ProductsProductIdInputModel productId) {
		logger.info("Received request to get batch numbers for product: {}", productId);

		logger.info("Calling storeIssueDao.getBatchNumbers() with productId: {}", productId);

		// Retrieve the list of ProductStock objects based on the given productId using storeIssueDao
		List<ProductStock> productsStockList = storeIssueDao.getBatchNumbers(productId);

		logger.info("Retrieved batch numbers for product: {}", productId);

		List<ProductOutput> productOutputList = new ArrayList<>();
		for (ProductStock productsStock : productsStockList) {
			// Map each ProductStock object to ProductOutput using ModelMapper and add it to the productOutputList
			productOutputList.add(modelMapper.map(productsStock, ProductOutput.class));
		}

		logger.info("Returning batch numbers for product: {}", productId);

		return productOutputList; // Return the list of ProductOutput objects as a JSON response
	}

	// Calculate total stock issue amount and save the stock issues
	@PostMapping("/issueStock")
	public String issueStock(@RequestBody StoreIssuesList storeIssuesList) {
		logger.info("Received request to issue stock for store issues: {}", storeIssuesList);

		logger.info("Issuing stock for store issues: {}", storeIssuesList);
		logger.debug("Entering into calculateTotalPurchaseAmount()");

		// Calculate the total purchase amount based on the store issues list
		double totalPurchaseAmount = storeIssuesBLL.calculateTotalPurchaseAmount(storeIssuesList);

		logger.debug("Calculated total purchase amount: {}", totalPurchaseAmount);

		// Map the storeIssuesList to a StoreIssues object using the modelMapper
		StoreIssues storeIssues = modelMapper.map(storeIssuesList, StoreIssues.class);

		// Set the total purchase amount to the storeIssues object
		storeIssues.setAmount(totalPurchaseAmount);

		logger.debug("Entering into saveStoreInfo() method..");

		// Save the storeIssues object to the storeIssueDao
		storeIssueDao.saveStoreInfo(storeIssues);

		logger.info("Stock issued successfully for store issues: {}", storeIssuesList);

		// Return the string "inventory/inventoryHome" as the response
		return "inventory/inventoryHome";
	}

	// Getting storeIssueIds
	@RequestMapping(value = "/getStoreIssueIds", method = RequestMethod.POST)
	public @ResponseBody List<StoreIssueIds> getStoreIssueIds(StoreId storeId, Model m) {
		logger.info("Received request to get store issue IDs for store ID: {}", storeId);
		logger.info("Getting store issue IDs for store ID: {}", storeId);
		logger.debug("Entering into getStoreIds() method..");

		// Retrieve the store issue data from the storeIssueDao based on the store ID
		List<StoreIssueData> issueData = storeIssueDao.getStoreIds(storeId);

		// Create a list to store the StoreIssueIds objects
		List<StoreIssueIds> storeIssueList = new ArrayList<>();

		// Map each StoreIssueData object to StoreIssueIds using ModelMapper and add it to the storeIssueList
		for (StoreIssueData storeIssueData : issueData) {
			storeIssueList.add(modelMapper.map(storeIssueData, StoreIssueIds.class));
		}
		logger.info("Returned store issue IDs for store ID: {}", storeId);

		// Return the list of StoreIssueIds objects as a JSON response
		return storeIssueList;
	}

	// Getting StoreIssues List
	@PostMapping("/getStoreIssuesList")
	@ResponseBody
	public List<StoreIssuesData> getStoreIssues(Model model) {
		logger.info("Received request to get store issues list");
		logger.debug("Entering into getStoreIssuesList() method");

		// Retrieve all store issues from the database using storeIssueDao
		List<StoreIssuesData> storeIssuesDataList = storeIssueDao.getAllStoreIssues();
		logger.info("Returned store issues list: {}", storeIssuesDataList);

		// Return the list of StoreIssuesData objects as a JSON response
		return storeIssuesDataList;
	}

	// Getting StoreIssue Products List
	@PostMapping("/getStoreIssueProductsList")
	public String getStoreIndentProductsList(String storeIssueId, Model model) {
		logger.info("Received request to get store issue products list");
		logger.debug("Entering into getStoreIndentProductsList() method");

		StoreIssueIds storeIssueIds = null;
		try {
			storeIssueIds = objectMapper.readValue(storeIssueId, StoreIssueIds.class);
		} catch (JsonProcessingException e) {
			logger.error("Error occurred while parsing storeIssueId JSON", e);
			e.printStackTrace();
		}
		logger.debug("Entering into getStoreIssuesProductsList()");
		// Retrieve the list of StoreIssueProducts based on the given storeIssueIds using storeIssueDao
		List<StoreIssueProducts> storeIssueProduct = storeIssueDao.getStoreIssuesProductsList(storeIssueIds);

		// Add the storeIssueProduct list to the model
		model.addAttribute("productsList", storeIssueProduct);
		logger.info("Store issue products list retrieved successfully");

		// Return the view name for displaying the stock issued products
		return "inventory/stockIssuedProducts";
	}

	/* Filters */
	// Filter based on Store Id, StockIssueStatus, From date, to date
	@PostMapping("/getIssuesFilterData")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataIdStatusFrom(String filters, Model model) {
		logger.info("Received request to get filtered store issues data");

		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			// Deserialize the JSON string "filters" into a StoreFilters object
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			logger.error("Error occurred while parsing filters JSON", e);
			e.printStackTrace();
		}
		logger.debug("Entering into getStoreIssuesListByIdStatusFrom()");
		// Retrieve the filtered StoreIssuesData list based on the given storeFilters using storeIssueDao
		List<StoreIssuesData> storeIssues = storeIssueService.getStoreIssuesFilters(storeFilters);

		logger.info("Filtered store issues data retrieved successfully");
		// Return the filtered list of StoreIssuesData objects as a JSON response
		return storeIssues;
	}

}
