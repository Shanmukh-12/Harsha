package main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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

@Controller
public class StoreIssuesController {

    @Autowired
    StoreIssueDao storeIssueDao; // Autowired dependency injection for StoreIssueDao

    @Autowired
    StoreIssuesBLL storeIssuesBLL; // Autowired dependency injection for StoreIssuesBLL

    @Autowired
    ModelMapper modelMapper; // Autowired dependency injection for ModelMapper

    ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper for JSON serialization/deserialization

    // Getting Batch Numbers and Purchase Sale Price
    @PostMapping("/getBatchNumbers")
    public @ResponseBody List<ProductOutput> getBatchNumbers(@RequestBody ProductsProductIdInputModel productId) {
        // Retrieve the list of ProductStock objects based on the given productId using storeIssueDao
        List<ProductStock> productsStockList = storeIssueDao.getBatchNumbers(productId);

        List<ProductOutput> productOutputList = new ArrayList<>();
        for (ProductStock productsStock : productsStockList) {
            // Map each ProductStock object to ProductOutput using ModelMapper and add it to the productOutputList
            productOutputList.add(modelMapper.map(productsStock, ProductOutput.class));
        }

        return productOutputList; // Return the list of ProductOutput objects as a JSON response
    }

    // Calculate total stock issue amount and save the stock issues
    @PostMapping("/issueStock")
    public String issueStock(@RequestBody StoreIssuesList storeIssuesList) {
        
        // Calculate the total purchase amount based on the store issues list
        double totalPurchaseAmount = storeIssuesBLL.calculateTotalPurchaseAmount(storeIssuesList);
        
        // Map the storeIssuesList to a StoreIssues object using the modelMapper
        StoreIssues storeIssues = modelMapper.map(storeIssuesList, StoreIssues.class);
        
        // Set the total purchase amount to the storeIssues object
        storeIssues.setAmount(totalPurchaseAmount);
        
        // Save the storeIssues object to the storeIssueDao
        storeIssueDao.saveStoreInfo(storeIssues);
        
        // Return the string "inventory/inventoryHome" as the response
        return "inventory/inventoryHome";
    }

    // Getting storeIssueIds
    @RequestMapping(value = "/getStoreIssueIds", method = RequestMethod.POST)
    public @ResponseBody List<StoreIssueIds> getStoreIssueIds(StoreId storeId, Model m) {
        List<StoreIssueData> issueData = storeIssueDao.getStoreIds(storeId);

        List<StoreIssueIds> storeIssueList = new ArrayList<>();
        for (StoreIssueData storeIssueData : issueData) {
            // Map each StoreIssueData object to StoreIssueIds using ModelMapper and add it to the res list
        	storeIssueList.add(modelMapper.map(storeIssueData, StoreIssueIds.class));
        }

        return storeIssueList; // Return the list of StoreIssueIds objects as a JSON response
    }

    // Getting StoreIssues List
    @PostMapping("/getStoreIssuesList")
    @ResponseBody
    public List<StoreIssuesData> getStoreIssues(Model model) {
        // Retrieve all store issues from the database using storeIssueDao
        List<StoreIssuesData> storeIssuesDataList = storeIssueDao.getAllStoreIssues();

        return storeIssuesDataList; // Return the list of StoreIssuesData objects as a JSON response
    }

    // Getting StoreIssue Products List
    @PostMapping("/getStoreIssueProductsList")
    public String getStoreIndentProductsList(String storeIssueId, Model model) {
        StoreIssueIds storeIssueIds = null;
        try {
            storeIssueIds = objectMapper.readValue(storeIssueId, StoreIssueIds.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Retrieve the list of StoreIssueProducts based on the given storeIssueIds using storeIssueDao
        List<StoreIssueProducts> storeIssueProduct = storeIssueDao.getStoreIssuesProductsList(storeIssueIds);

        model.addAttribute("productsList", storeIssueProduct);
        return "inventory/stockIssuedProducts";
    }

/* Filters */
    // Filter based on Store Id, StockIssueStatus, From date, to date
    @PostMapping("/getIssuesFilterDataIdStatusFrom")
    public @ResponseBody List<StoreIssuesData> getIssuesFilterDataIdStatusFrom(String filters, Model model) {
        StoreFilters storeFilters = null;
        objectMapper.registerModule(new JavaTimeModule());
        try {
            storeFilters = objectMapper.readValue(filters, StoreFilters.class);// Deserialize the JSON string "filters" into a StoreFilters object
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retrieve the filtered StoreIssuesData list based on the given storeFilters using storeIssueDao
        List<StoreIssuesData> storeIssues = storeIssueDao.getStoreIssuesListByIdStatusFrom(storeFilters);
        return storeIssues; // Return the filtered list of StoreIssuesData objects as a JSON response
    }
    //filter based on storeId,StockIssuestatus,to Date
	@PostMapping("/getIssuesFilterDataIdStatus")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataIdStatus(String filters, Model model) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);// Deserialize the JSON string "filters" into a StoreFilters object
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Retrieve the filtered StoreIssuesData list based on the given storeFilters using storeIssueDao
		List<StoreIssuesData> storeIssues = storeIssueDao.getStoreIssuesListByIdStatus(storeFilters);
		return storeIssues;// Return the filtered list of StoreIssuesData objects as a JSON response
	}
   //Filter based on StoreId,from date,to Date
	@PostMapping("/getIssuesFilterDataIdFrom")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataIdFrom(String filters, Model model) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);// Deserialize the JSON string "filters" into a StoreFilters object
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Retrieve the filtered StoreIssuesData list based on the given storeFilters using storeIssueDao
		List<StoreIssuesData> storeIssues = storeIssueDao.getStoreIssuesListByIdFrom(storeFilters);
		return storeIssues;// Return the filtered list of StoreIssuesData objects as a JSON response

	}
    //Filter based on StoreID,toDate
	@PostMapping("/getIssuesFilterDataId")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataId(String filters, Model model) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);// Deserialize the JSON string "filters" into a StoreFilters object
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Retrieve the filtered StoreIssuesData list based on the given storeFilters using storeIssueDao
		List<StoreIssuesData> storeIssues = storeIssueDao.getStoreIssuesListById(storeFilters);
		return storeIssues;// Return the filtered list of StoreIssuesData objects as a JSON response
	}
    //filter Based on StockIssueStatus,From date,to date
	@PostMapping("/getIssuesFilterDataStatusFrom")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataStatusFrom(String filters, Model model) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);// Deserialize the JSON string "filters" into a StoreFilters object
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Retrieve the filtered StoreIssuesData list based on the given storeFilters using storeIssueDao
		List<StoreIssuesData> storeIssues = storeIssueDao.getStoreIssuesListByStatusFrom(storeFilters);
		return storeIssues;// Return the filtered list of StoreIssuesData objects as a JSON response
	}
    //Filters based on StockIssueStatus,to date
	@PostMapping("/getIssuesFilterDataStatus")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataStatus(String filters, Model model) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);// Deserialize the JSON string "filters" into a StoreFilters object
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Retrieve the filtered StoreIssuesData list based on the given storeFilters using storeIssueDao
		List<StoreIssuesData> storeIssues = storeIssueDao.getStoreIssuesListByStatus(storeFilters);
		return storeIssues;// Return the filtered list of StoreIssuesData objects as a JSON response
	}
    //Filters based on From Date,to date
	@PostMapping("/getIssuesFilterDataFrom")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataFrom(String filters, Model model) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);// Deserialize the JSON string "filters" into a StoreFilters object
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Retrieve the filtered StoreIssuesData list based on the given storeFilters using storeIssueDao
		List<StoreIssuesData> storeIssues = storeIssueDao.getStoreIssuesListByFrom(storeFilters);
		return storeIssues;// Return the filtered list of StoreIssuesData objects as a JSON response
	}
    //Filters based on To date only
	@PostMapping("/getIssuesFilterDataTo")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataTo(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);// Deserialize the JSON string "filters" into a StoreFilters object
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Retrieve the filtered StoreIssuesData list based on the given storeFilters using storeIssueDao
		List<StoreIssuesData> storeIssues = storeIssueDao.getStoreIssuesListByTo(storeFilters);
		return storeIssues;// Return the filtered list of StoreIssuesData objects as a JSON response
	}
}
