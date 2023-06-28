package main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import main.dao.storeIndents.StoreIndentsDao;
import main.models.storeIndentModels.outputmodels.StoreIndentDataOutput;
import main.models.storeIndentModels.outputmodels.StoreIndentProducts;
import main.models.storeModels.entities.Store;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.StoreFilters;
import main.models.storeModels.inputmodels.StoreIndentsInputList;
import main.models.storeModels.outputmodels.StoreIds;
import main.service.store.interfaces.StoreService;

@Controller
public class StoreIndentController {

	@Autowired
	StoreIndentsDao storeIndentsDao; // Autowired dependency for StoreIndentsDao

	@Autowired
	StoreService storeService;
	
	@Autowired
	ModelMapper modelMapper; // Autowired dependency for ModelMapper

	ObjectMapper objectMapper = new ObjectMapper(); // Creating an instance of ObjectMapper
	
	private static final Logger logger = LoggerFactory.getLogger(StoreIndentController.class);

	@PostMapping("/getStoreIndentsList")
	// Retrieves the list of store indent data
	public @ResponseBody List<StoreIndentDataOutput> getStoreIndentList(Model m) {

		// Entry log
	    logger.info("Entering getStoreIndentList method");
		
		// Fetching store indent data list from StoreIndentsDao
	    logger.info("Fetching store indent data list from StoreIndentsDao");
		List<StoreIndentData> storeIndentsList = storeIndentsDao.getStoreIndentsList();
		List<StoreIndentDataOutput> storeIndents = new ArrayList<>();

		for (StoreIndentData indent : storeIndentsList) {
			// Converting each indent list to output model
	        logger.debug("Mapping indent {} to StoreIndentDataOutput", indent.getIndentId());
			storeIndents.add(modelMapper.map(indent, StoreIndentDataOutput.class));
		}

		// Returning the list of Store Indents
	    logger.info("Returning the list of Store Indents with {} entries", storeIndents.size());
		return storeIndents;
	}

	@PostMapping("/getStoreIndentProductsListData")
	// Retrieves the list of store indent products based on indentId
	public @ResponseBody List<StoreIndentProducts> getStoreIndentProductsListData(String indentId, Model m) {

		// Entry log
	    logger.info("Entering getStoreIndentProductsListData method");

		IndentId indentid = null;
		try {
			// Converting into input model
	        logger.debug("Converting input indentId into IndentId object");
			indentid = objectMapper.readValue(indentId, IndentId.class);
		} catch (JsonProcessingException e) {
	        logger.error("Error occurred while processing JSON input: {}", e.getMessage());
	        e.printStackTrace();
		}

	    logger.info("Fetching store indent products list based on indentId: {}", indentid);
		// Fetching store indent products list based on indentid
		List<StoreIndentProducts> storeIndentProducts = storeIndentsDao.getStoreIndentsProductsList(indentid);

	    logger.info("Returning the list of StoreIndentProducts with {} entries", storeIndentProducts.size());
		// Returning the list of StoreIndentProducts
		return storeIndentProducts;
	}

	@PostMapping("/getStoreIndentProductsList")
	// Retrieves the list of store indent products based on indentId and adds it to the model attribute
	public String getStoreIndentProductsList(String indentId, Model m) {
	    logger.info("Entering getStoreIndentProductsList method");
		IndentId indentid = null;
		try {
			// Converting input data to input model
	        logger.debug("Converting input indentId into IndentId object");
			indentid = objectMapper.readValue(indentId, IndentId.class);

		} catch (JsonProcessingException e) {
	        logger.error("Error occurred while processing JSON input: {}", e.getMessage());
			e.printStackTrace();
		}

	    logger.info("Fetching store indent products list based on indentId: {}", indentid);
		// Fetching store indent products list based on indentid
		List<StoreIndentProducts> storeIndentProducts = storeIndentsDao.getStoreIndentsProductsList(indentid);

	    logger.info("Adding the storeIndentProducts to the model attribute");
		// Adding the storeIndentProducts to the model attribute
		m.addAttribute("productsList", storeIndentProducts);

		logger.info("Returning the view name: store/storeIndentProducts");
		return "store/storeIndentProducts";
	}

	@PostMapping("/newCreateStoreIndent")
	// Creates a new store indent using the provided input Data
	public String createStoreIndent(String jsonData, Model m) {

	    logger.info("Entering createStoreIndent method");
		StoreIndentsInputList storeIndentsInputList = null;

		try {
			// converting input data to input model
	        logger.debug("Converting input jsonData into StoreIndentsInputList object");
			storeIndentsInputList = objectMapper.readValue(jsonData, StoreIndentsInputList.class);
		} catch (Exception e) {
	        logger.error("Error occurred while processing JSON input: {}", e.getMessage());
			e.printStackTrace();
		}

		// Mapping input model data to entity model
	    logger.debug("Mapping StoreIndentsInputList to StoreIndentsList entity model");
		StoreIndentsList storeIndentsList = modelMapper.map(storeIndentsInputList, StoreIndentsList.class);

		// Saving the StoreIndentsList object
	    logger.info("Saving the StoreIndentsList object");
		storeIndentsDao.saveStoreIndent(storeIndentsList);

	    logger.info("Returning the view name: store/createStoreIndent");
		return "store/createStoreIndent";
	}

	@PostMapping("/getIndentsListByStoreID")
	// Retrieves the list of store indents by store ID
	public @ResponseBody List<StoreIndentData> getIndentsList(@ModelAttribute("storeIds") StoreIds storeIds,
			Model model) {

	    logger.info("Entering getIndentsList method");

	    int selectedStoreId = storeIds.getStoreId();
		// Fetching store indent products list based on StoreId
	    logger.info("Fetching store indent products list based on StoreId: {}", selectedStoreId);
		List<StoreIndentData> indents = storeIndentsDao.getIndentsByStoreID(selectedStoreId);

		// Returning the list of StoreIndentData
	    logger.info("Returning the list of StoreIndentData with {} entries", indents.size());
		return indents;
	}
	
	@PostMapping("/getStoreIds")
	// Listing the Store ids
	public @ResponseBody List<StoreIds> getStoreIds(Model m) {

	    logger.info("Entering getStoreIds method");
		// Fetching list of stores from StoreIndentsDao
		List<Store> storeIds = storeIndentsDao.getStoreIds();
		List<StoreIds> storeIdsList = new ArrayList();

		// Converting each Store entity object to output model
	    logger.debug("Converting each Store entity object to StoreIds output model");
		for (Store store : storeIds)
			storeIdsList.add(modelMapper.map(store, StoreIds.class));

		// Returning the list of StoreIds
	    logger.info("Returning the list of StoreIds with {} entries", storeIdsList.size());
		return storeIdsList;
	}

	@PostMapping("/getIndentsByFilterData")
	public @ResponseBody List<StoreIndentData> getIndentsByFilterData(String filters, Model m) {
		StoreFilters storeFilters = null;
	    logger.info("Entering getIndentsByFilterData method");

		// Registering JavaTimeModule with the objectMapper
		objectMapper.registerModule(new JavaTimeModule());

		try {
	        logger.debug("Deserializing filters string to StoreFilters object");
			// Deserializing filters string to StoreFilters object
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
	        logger.error("Error occurred while processing filters: {}", e.getMessage());
			e.printStackTrace();
		}

		// Getting the store return data based on the input filters
	    logger.info("Getting the store return data based on the input filters");
		List<StoreIndentData> storeIndents = storeService.getStoreIndetnsByFilterData(storeFilters);

		// Returning the list of StoreIndentData
	    logger.info("Returning the list of StoreIndentData with {} entries", storeIndents.size());
		return storeIndents;
	}

}
