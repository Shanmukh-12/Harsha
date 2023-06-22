package main.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.storeReturns.StoreReturnsDao;
import main.models.storeModels.entities.StoreReturnsList;
import main.models.storeModels.inputmodels.StoreReturnsInputList;
import main.models.storeReturnsModels.inputModels.ReturnProducts;

@Controller
public class StoreReturnController {

	@Autowired
	StoreReturnsDao storeReturnsDao;

	@Autowired
	ModelMapper modelMapper;
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/newCreateStoreReturn")
	public String createStoreReturn(@RequestBody ReturnProducts data, Model m) {
		System.out.println("Inside");
		System.out.println(data);
		StoreReturnsInputList storeReturnsInputList = null;
		StoreReturnsList storeReturnsList = modelMapper.map(storeReturnsInputList, StoreReturnsList.class);
		System.out.println(storeReturnsList);
		storeReturnsDao.saveStoreReturns(storeReturnsList);
		return "store/createStoreReturn";
	}
}
