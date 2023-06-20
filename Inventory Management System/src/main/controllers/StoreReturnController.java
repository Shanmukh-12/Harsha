package main.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.storeReturns.StoreReturnsDao;
import main.models.storeModels.entities.StoreReturnsList;
import main.models.storeModels.inputmodels.StoreReturnsInputList;

@Controller
public class StoreReturnController {

	@Autowired
	StoreReturnsDao storeReturnsDao;
	
	@Autowired
	ModelMapper modelMapper;
	ObjectMapper objectMapper = new ObjectMapper();

	
	@PostMapping("/newCreateStoreReturn")
	public String createStoreReturn(String jsonData, Model m)
	{
		StoreReturnsInputList storeReturnsInputList = null;
		try {
			storeReturnsInputList = objectMapper.readValue(jsonData,StoreReturnsInputList.class);
		}
		catch(Exception e) {e.printStackTrace();}
		
		StoreReturnsList storeReturnsList = modelMapper.map(storeReturnsInputList, StoreReturnsList.class);
		storeReturnsDao.saveStoreReturns(storeReturnsList);
		return "store/createStoreReturn";
	}
}
