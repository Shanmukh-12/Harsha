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

	@PostMapping("/getStoreReturnsFilterDataIdFrom")
	public @ResponseBody List<StoreReturnsDataOutput> getStoreReturnsFilterDataIdFrom(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreReturnsDataOutput> sl = storeReturnsDao.getStoreReturnsFilterDataIdFrom(storeFilters);
		return sl;
	}

	@PostMapping("/getStoreReturnsFilterDataId")
	public @ResponseBody List<StoreReturnsDataOutput> getStoreReturnsFilterDataId(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreReturnsDataOutput> sl = storeReturnsDao.getStoreReturnsFilterDataId(storeFilters);
		return sl;
	}

	@PostMapping("/getStoreReturnsFilterDataFrom")
	public @ResponseBody List<StoreReturnsDataOutput> getStoreReturnsFilterDataFrom(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreReturnsDataOutput> sl = storeReturnsDao.getStoreReturnsFilterDataFrom(storeFilters);
		return sl;
	}

	@PostMapping("/getStoreReturnsFilterDataTo")
	public @ResponseBody List<StoreReturnsDataOutput> getStoreReturnsFilterDataTo(String filters, Model m) {
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreReturnsDataOutput> sl = storeReturnsDao.getStoreReturnsFilterDataTo(storeFilters);
		return sl;
	}
}
