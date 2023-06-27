package main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.storeReturns.StoreReturnsDao;
import main.models.storeModels.inputmodels.ReturnId;
import main.models.storeReturnsModels.entities.StoreReturnProductsList;
import main.models.storeReturnsModels.entities.StoreReturnsList;
import main.models.storeReturnsModels.inputModels.StoreReturnProducts;
import main.models.storeReturnsModels.inputModels.StoreReturns;
import main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput;

@Controller
public class StoreReturnController {

	@Autowired
	StoreReturnsDao storeReturnsDao; // Autowired dependency for StoreReturnsDao

	@Autowired
	ModelMapper modelMapper; // Autowired dependency for ModelMapper

	ObjectMapper objectMapper = new ObjectMapper(); // Creating an instance of ObjectMapper

	@PostMapping("/getStoreReturnsList")
	// Retrieves the list of store returns
	public @ResponseBody List<StoreReturnsDataOutput> getStoreReturnsList(Model m) {

		// Fetching store returns list
		List<StoreReturnsDataOutput> storeReturns = storeReturnsDao.getStoreReturnsList();

		// Returning the list of StoreReturnsDataOutput
		return storeReturns;
	}

	@PostMapping("/getStoreReturnProductsList")
	// Retrieves the list of store return products based on returnId and adds it to the model attribute
	public String getStoreIndentProductsList(String returnId, Model m) {
		ReturnId returnid = null;
		try {
			// Converting the input data to input model
			returnid = objectMapper.readValue(returnId, ReturnId.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// Fetching store return products list based on returnid
		List<StoreReturnProductsList> storeReturnProductsList = storeReturnsDao.getStoreReturnsProductsList(returnid);
		List<StoreReturnProducts> storeReturnProducts = new ArrayList();

		// converting entity to output model
		for (StoreReturnProductsList product : storeReturnProductsList) {
			storeReturnProducts.add(modelMapper.map(product, StoreReturnProducts.class));
		}

		// Adding the storeReturnProducts to the model attribute
		m.addAttribute("productsList", storeReturnProducts);

		return "store/storeReturnProducts";
	}

	@PostMapping("/newCreateStoreReturn")
	// Creates a new store return using the provided data
	public String createStoreReturn(@RequestBody String data, Model m) {
		StoreReturns storeReturns = null;
		try {
			// converting input data to StoreReturns object
			storeReturns = objectMapper.readValue(data, StoreReturns.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Mapping input model data to output model using modelMapper
		StoreReturnsList storeReturnsList = modelMapper.map(storeReturns, StoreReturnsList.class);

		// Saving the storeReturnsList object to the database
		storeReturnsDao.saveStoreReturns(storeReturnsList);
		return "store/createStoreReturn";
	}

}
