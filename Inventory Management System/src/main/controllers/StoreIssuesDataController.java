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
public class StoreIssuesDataController {

	@Autowired
	StoreIssueDao storeIssueDao;

	@Autowired
	StoreIssuesBLL storeIssuesBLL;

	@Autowired
	ModelMapper modelMapper;
	ObjectMapper objectMapper = new ObjectMapper();
    //Getting Batch Numbers and Purchase Sale Price
	@PostMapping("/getBatchNumbers")
	public @ResponseBody List<ProductOutput> getBatchNumbers(@RequestBody ProductsProductIdInputModel productId) {
		System.out.println(productId);
		System.out.println("Getting product Id");

		List<ProductStock> productsStockList = storeIssueDao.getBatchNumbers(productId);

		List<ProductOutput> productOutputList = new ArrayList<>();
		for (ProductStock productsStock : productsStockList) {
			productOutputList.add(modelMapper.map(productsStock, ProductOutput.class));
		}

		return productOutputList;
	}
    //Calculate total stock issue amount and save the stock issues 
	@PostMapping("/issueStock")
	public String issueStock(@RequestBody StoreIssuesList storeIssuesList) {
		System.out.println(storeIssuesList);

		double totalPurchaseAmount = storeIssuesBLL.calculateTotalPurchaseAmount(storeIssuesList);

		StoreIssues storeIssues = modelMapper.map(storeIssuesList, StoreIssues.class);
		storeIssues.setAmount(totalPurchaseAmount);
		System.out.println(storeIssues);
		storeIssueDao.saveStoreInfo(storeIssues);
		System.out.println("inserted successfull");
		return "inventory/inventoryHome";
	}
    //Getting storeIssueIds
	@RequestMapping(value = "/getStoreIssueIds", method = RequestMethod.POST)
	public @ResponseBody List<StoreIssueIds> getStoreIssueIds(StoreId storeId, Model m) {
		System.out.println("Inside");
		List<StoreIssueData> data = storeIssueDao.getStoreIds(storeId);
		List<StoreIssueIds> res = new ArrayList<>();
		for (StoreIssueData s : data)
			res.add(modelMapper.map(s, StoreIssueIds.class));
		return res;
	}

	// Getting StoreIssues List
	@PostMapping("/getStoreIssuesList")
	@ResponseBody
	public List<StoreIssuesData> getStoreIssues(Model m) {

		List<StoreIssuesData> storeIssuesDataList = storeIssueDao.getAllStoreIssues();

		return storeIssuesDataList;
	}

	// Getting StoreIssue Products List
	@PostMapping("/getStoreIssueProductsList")
	public String getStoreIndentProductsList(String storeIssueId, Model m) {
		System.out.println(storeIssueId);
		StoreIssueIds storeIssueIds = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			storeIssueIds = objectMapper.readValue(storeIssueId, StoreIssueIds.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<StoreIssueProducts> storeIssueProduct = storeIssueDao.getStoreIssuesProductsList(storeIssueIds);

		m.addAttribute("productsList", storeIssueProduct);
		return "inventory/stockIssuedProducts";
	}

/* Filters */
	//Filter based on Store Id,StockIssueStatus,From date,to date
	@PostMapping("/getIssuesFilterDataIdStatusFrom")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataIdStatusFrom(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<StoreIssuesData> sl = storeIssueDao.getStoreIssuesListByIdStatusFrom(storeFilters);
		return sl;
	}
    //filter based on storeId,StockIssuestatus,to Date
	@PostMapping("/getIssuesFilterDataIdStatus")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataIdStatus(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIssuesData> sl = storeIssueDao.getStoreIssuesListByIdStatus(storeFilters);
		return sl;
	}
   //Filter based on StoreId,from date,to Date
	@PostMapping("/getIssuesFilterDataIdFrom")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataIdFrom(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIssuesData> sl = storeIssueDao.getStoreIssuesListByIdFrom(storeFilters);
		return sl;
	}
    //Filter based on StoreID,toDate
	@PostMapping("/getIssuesFilterDataId")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataId(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIssuesData> sl = storeIssueDao.getStoreIssuesListById(storeFilters);
		return sl;
	}
    //filter Based on StockIssueStatus,From date,to date
	@PostMapping("/getIssuesFilterDataStatusFrom")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataStatusFrom(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIssuesData> sl = storeIssueDao.getStoreIssuesListByStatusFrom(storeFilters);
		return sl;
	}
    //Filters based on StockIssueStatus,to date
	@PostMapping("/getIssuesFilterDataStatus")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataStatus(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIssuesData> sl = storeIssueDao.getStoreIssuesListByStatus(storeFilters);
		return sl;
	}
    //Filters based on From Date,to date
	@PostMapping("/getIssuesFilterDataFrom")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataFrom(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIssuesData> sl = storeIssueDao.getStoreIssuesListByFrom(storeFilters);
		return sl;
	}
    //Filters based on To date only
	@PostMapping("/getIssuesFilterDataTo")
	public @ResponseBody List<StoreIssuesData> getIssuesFilterDataTo(String filters, Model m) {
		StoreFilters storeFilters = null;
		System.out.println("Inside to");
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIssuesData> sl = storeIssueDao.getStoreIssuesListByTo(storeFilters);
		return sl;
	}
}
