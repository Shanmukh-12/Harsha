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
	StoreIndentsDao storeIndentsDao;
	
	@Autowired
	ModelMapper modelMapper;
	ObjectMapper objectMapper = new ObjectMapper();
	
	@PostMapping("/getStoreIds")
	public @ResponseBody List<StoreIds> getStoreIds(Model m)
	{
		System.out.println("StoreDataController");
		List<Store> lst = storeIndentsDao.getStoreIds();
		List<StoreIds> res = new ArrayList();
		for(Store si : lst)
			res.add(modelMapper.map(si,StoreIds.class));
		System.out.println("id ");
		for(StoreIds s: res)
			System.out.println("id  "+s);
		return res;
	}

	@PostMapping("/getFilterDataIdStatusFrom")
	public @ResponseBody List<StoreIndentData> getFilterDataIdStatusFrom(String filters,Model m)
	{
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {e.printStackTrace();}
		
		List<StoreIndentData> sl =storeIndentsDao.getStoreIndentsListByIdStatusFrom(storeFilters);
		return sl;
	}
	@PostMapping("/getFilterDataIdStatus")
	public @ResponseBody List<StoreIndentData> getFilterDataIdStatus(String filters,Model m)
	{
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIndentData> sl =storeIndentsDao.getStoreIndentsListByIdStatus(storeFilters);
		return sl;
	}
	@PostMapping("/getFilterDataIdFrom")
	public @ResponseBody List<StoreIndentData> getFilterDataIdFrom(String filters,Model m)
	{
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIndentData> sl =storeIndentsDao.getStoreIndentsListByIdFrom(storeFilters);
		return sl;
	}
	@PostMapping("/getFilterDataId")
	public @ResponseBody List<StoreIndentData> getFilterDataId(String filters,Model m)
	{
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIndentData> sl =storeIndentsDao.getStoreIndentsListById(storeFilters);
		return sl;
	}
	@PostMapping("/getFilterDataStatusFrom")
	public @ResponseBody List<StoreIndentData> getFilterDataStatusFrom(String filters,Model m)
	{
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIndentData> sl =storeIndentsDao.getStoreIndentsListByStatusFrom(storeFilters);
		return sl;
	}
	@PostMapping("/getFilterDataStatus")
	public @ResponseBody List<StoreIndentData> getFilterDataStatus(String filters,Model m)
	{
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIndentData> sl =storeIndentsDao.getStoreIndentsListByStatus(storeFilters);
		return sl;
	}
	@PostMapping("/getFilterDataFrom")
	public @ResponseBody List<StoreIndentData> getFilterDataFrom(String filters,Model m)
	{
		StoreFilters storeFilters = null;
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIndentData> sl =storeIndentsDao.getStoreIndentsListByFrom(storeFilters);
		return sl;
	}
	@PostMapping("/getFilterDataTo")
	public @ResponseBody List<StoreIndentData> getFilterDataTo(String filters,Model m)
	{
		StoreFilters storeFilters = null;
		System.out.println("Inside to");
		objectMapper.registerModule(new JavaTimeModule());
		try {
			storeFilters = objectMapper.readValue(filters, StoreFilters.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StoreIndentData> sl =storeIndentsDao.getStoreIndentsListByTo(storeFilters);
		return sl;
	}
	
}
