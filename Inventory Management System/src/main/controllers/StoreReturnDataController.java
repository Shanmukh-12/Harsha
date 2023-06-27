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

@Controller
public class StoreReturnDataController {

	@Autowired
	StoreReturnsDao storeReturnsDao;

	@Autowired
	ModelMapper modelMapper;
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/getStoreReturnsDataBasedOnFilters")
	public @ResponseBody List<StoreReturnsDataOutput> getStoreReturnsDataBasedOnFilters(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<StoreReturnsDataOutput> storeReturnsData = null;

		if (storeFilters.getStoreId() != 0) {
			if (storeFilters.getFromDate() != null) {
				storeReturnsData = storeReturnsDao.getStoreReturnsFilterDataIdFrom(storeFilters);
			} else {
				storeReturnsData = storeReturnsDao.getStoreReturnsFilterDataId(storeFilters);
			}
		} else {
			if (storeFilters.getFromDate() != null) {
				storeReturnsData = storeReturnsDao.getStoreReturnsFilterDataFrom(storeFilters);
			} else {
				storeReturnsData = storeReturnsDao.getStoreReturnsFilterDataTo(storeFilters);
			}
		}
		return storeReturnsData;
	}
}
