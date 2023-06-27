package main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.storeIndents.StoreIndentsDao;
import main.models.storeIndentModels.outputmodels.StoreIndentDataOutput;
import main.models.storeIndentModels.outputmodels.StoreIndentProducts;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.StoreIndentsInputList;
import main.models.storeModels.outputmodels.StoreIds;

@Controller
public class StoreIndentController {

	@Autowired
	StoreIndentsDao storeIndentsDao; // Autowired dependency for StoreIndentsDao

	@Autowired
	ModelMapper modelMapper; // Autowired dependency for ModelMapper

	ObjectMapper objectMapper = new ObjectMapper(); // Creating an instance of ObjectMapper

	@PostMapping("/getStoreIndentsList")
	// Retrieves the list of store indent data
	public @ResponseBody List<StoreIndentDataOutput> getStoreIndentList(Model m) {

		// Fetching store indent data list from StoreIndentsDao
		List<StoreIndentData> storeIndentsList = storeIndentsDao.getStoreIndentsList();
		List<StoreIndentDataOutput> storeIndents = new ArrayList<>();

		for (StoreIndentData indent : storeIndentsList) {
			// Converting each indent list to output model
			storeIndents.add(modelMapper.map(indent, StoreIndentDataOutput.class));
		}

		// Returning the list of Store Indents
		return storeIndents;
	}

	@PostMapping("/getStoreIndentProductsListData")
	// Retrieves the list of store indent products based on indentId
	public @ResponseBody List<StoreIndentProducts> getStoreIndentProductsListData(String indentId, Model m) {
		IndentId indentid = null;
		try {
			// Converting into input model
			indentid = objectMapper.readValue(indentId, IndentId.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// Fetching store indent products list based on indentid
		List<StoreIndentProducts> storeIndentProducts = storeIndentsDao.getStoreIndentsProductsList(indentid);

		// Returning the list of StoreIndentProducts
		return storeIndentProducts;
	}

	@PostMapping("/getStoreIndentProductsList")
	// Retrieves the list of store indent products based on indentId and adds it to the model attribute
	public String getStoreIndentProductsList(String indentId, Model m) {
		IndentId indentid = null;
		try {
			// Converting input data to input model
			indentid = objectMapper.readValue(indentId, IndentId.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// Fetching store indent products list based on indentid
		List<StoreIndentProducts> storeIndentProducts = storeIndentsDao.getStoreIndentsProductsList(indentid);

		// Adding the storeIndentProducts to the model attribute
		m.addAttribute("productsList", storeIndentProducts);
		return "store/storeIndentProducts";
	}

	@PostMapping("/newCreateStoreIndent")
	// Creates a new store indent using the provided input Data
	public String createStoreIndent(String jsonData, Model m) {
		StoreIndentsInputList storeIndentsInputList = null;

		try {
			// converting input data to input model
			storeIndentsInputList = objectMapper.readValue(jsonData, StoreIndentsInputList.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Mapping input model data to entity model
		StoreIndentsList storeIndentsList = modelMapper.map(storeIndentsInputList, StoreIndentsList.class);

		// Saving the StoreIndentsList object
		storeIndentsDao.saveStoreIndent(storeIndentsList);

		return "store/createStoreIndent";
	}

	@PostMapping("/getIndentsListByStoreID")
	// Retrieves the list of store indents by store ID
	public @ResponseBody List<StoreIndentData> getIndentsList(@ModelAttribute("storeIds") StoreIds storeIds,
			Model model) {

		int selectedStoreId = storeIds.getStoreId();
		// Fetching store indent products list based on StoreId
		List<StoreIndentData> indents = storeIndentsDao.getIndentsByStoreID(selectedStoreId);

		// Returning the list of StoreIndentData
		return indents;
	}

}
