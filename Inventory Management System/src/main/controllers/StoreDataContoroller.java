package main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import main.dao.storeIndents.StoreIndentsDao;
import main.models.storeModels.entities.Store;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.inputmodels.StoreFilters;
import main.models.storeModels.outputmodels.StoreIds;
import main.service.store.interfaces.StoreService;

@Controller
public class StoreDataContoroller {

	@Autowired
	// Autowired dependency for StoreIndentsDao
	StoreIndentsDao storeIndentsDao;

	@Autowired
	StoreService storeService;

	@Autowired
	// Autowired dependency for ModelMapper
	ModelMapper modelMapper;

	// Creating an instance of ObjectMapper
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/getStoreIds")
	// Listing the Store ids
	public @ResponseBody List<StoreIds> getStoreIds(Model m) {

		// Fetching list of stores from StoreIndentsDao
		List<Store> storeIds = storeIndentsDao.getStoreIds();
		List<StoreIds> storeIdsList = new ArrayList();

		// Converting each Store entity object to output model
		for (Store store : storeIds)
			storeIdsList.add(modelMapper.map(store, StoreIds.class));

		// Returning the list of StoreIds
		return storeIdsList;
	}

	@PostMapping("/getIndentsByFilterData")
	public @ResponseBody List<StoreIndentData> getIndentsByFilterData(String filters, Model m) {
		StoreFilters storeFilters = null;

		// Registering JavaTimeModule with the objectMapper
		objectMapper.registerModule(new JavaTimeModule());

		try {
			// Deserializing filters string to StoreFilters object
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Getting the store return data based on the input filters
		List<StoreIndentData> storeIndents = storeService.getStoreIndetnsByFilterData(storeFilters);

		// Returning the list of StoreIndentData
		return storeIndents;
	}
}
