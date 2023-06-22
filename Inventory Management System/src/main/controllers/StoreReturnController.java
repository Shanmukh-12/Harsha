package main.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.storeReturns.StoreReturnsDao;
import main.models.storeReturnsModels.entities.StoreReturnsList;
import main.models.storeReturnsModels.inputModels.StoreReturns;

@Controller
public class StoreReturnController {

	@Autowired
	StoreReturnsDao storeReturnsDao;

	@Autowired
	ModelMapper modelMapper;
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/newCreateStoreReturn")
	public String createStoreReturn(@RequestBody String data, Model m) {
		System.out.println("Inside");
		System.out.println(data);
		StoreReturns storeReturns = null;
		try {
			storeReturns = objectMapper.readValue(data, StoreReturns.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StoreReturnsList storeReturnsList = modelMapper.map(storeReturns, StoreReturnsList.class);

		System.out.println(storeReturnsList);
		storeReturnsDao.saveStoreReturns(storeReturnsList);
		return "store/createStoreReturn";
	}

}
