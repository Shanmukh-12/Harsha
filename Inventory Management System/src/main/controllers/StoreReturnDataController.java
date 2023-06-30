package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import main.dao.storeReturns.StoreReturnsDao;
import main.models.storeModels.inputmodels.StoreFilters;
import main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput;
import main.service.store.interfaces.StoreService;

@Controller
public class StoreReturnDataController {

	@Autowired
	StoreReturnsDao storeReturnsDao; // Autowired dependency for StoreReturnsDao

	@Autowired
	StoreService storeService;

	@Autowired
	ModelMapper modelMapper; // Autowired dependency for ModelMapper

	ObjectMapper objectMapper = new ObjectMapper(); // Creating an instance of ObjectMapper

	@PostMapping("/getStoreReturnsDataBasedOnFilters")

	// Retrieves store returns data based on the provided filters
	public @ResponseBody List<StoreReturnsDataOutput> getStoreReturnsDataBasedOnFilters(String filters, Model m) {
		StoreFilters storeFilters = null;
		// Registering JavaTimeModule with the objectMapper
		objectMapper.registerModule(new JavaTimeModule());
		try {
			// Converting input data to StoreFilters object
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Getting the store return data based on the input filters
		List<StoreReturnsDataOutput> storeReturnsData = storeService.getStoreReturnsByFilterData(storeFilters);
		return storeReturnsData; // Returning the store returns data
	}
}
