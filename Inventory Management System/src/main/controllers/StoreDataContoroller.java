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

@Controller
public class StoreDataContoroller {

	@Autowired
	// Autowired dependency for StoreIndentsDao
	StoreIndentsDao storeIndentsDao;

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

		List<StoreIndentData> storeIndents = null;

		if (storeFilters.getStoreId() != 0) {
			if (storeFilters.getIndentStatus().length() > 0) {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with store ID, indent status, and from date
					storeIndents = storeIndentsDao.getStoreIndentsListByIdStatusFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with store ID and indent status
					storeIndents = storeIndentsDao.getStoreIndentsListByIdStatus(storeFilters);
				}
			} else {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with store ID and from date
					storeIndents = storeIndentsDao.getStoreIndentsListByIdFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with store ID
					storeIndents = storeIndentsDao.getStoreIndentsListById(storeFilters);
				}
			}
		} else {
			if (storeFilters.getIndentStatus().length() > 0) {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with indent status and from date
					storeIndents = storeIndentsDao.getStoreIndentsListByStatusFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with indent status
					storeIndents = storeIndentsDao.getStoreIndentsListByStatus(storeFilters);
				}
			} else {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with from date
					storeIndents = storeIndentsDao.getStoreIndentsListByFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with to date
					storeIndents = storeIndentsDao.getStoreIndentsListByTo(storeFilters);
				}
			}
		}

		// Returning the list of StoreIndentData
		return storeIndents;
	}
}
