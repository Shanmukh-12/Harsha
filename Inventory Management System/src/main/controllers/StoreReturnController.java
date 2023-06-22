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
import main.models.storeReturnsModels.entities.StoreReturnsData;
import main.models.storeReturnsModels.entities.StoreReturnsList;
import main.models.storeReturnsModels.inputModels.StoreReturnProducts;
import main.models.storeReturnsModels.inputModels.StoreReturns;
import main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput;

@Controller
public class StoreReturnController {

	@Autowired
	StoreReturnsDao storeReturnsDao;

	@Autowired
	ModelMapper modelMapper;
	ObjectMapper objectMapper = new ObjectMapper();

	
	@PostMapping("/getStoreReturnsList")
	public @ResponseBody List<StoreReturnsData> getStoreReturnsList(Model m) {
		System.out.println("Returns");
		List<StoreReturnsData> sl = storeReturnsDao.getStoreReturnsList();
		List<StoreReturnsDataOutput> res = new ArrayList<>();
		for (StoreReturnsData s : sl)
		{
			res.add(modelMapper.map(s, StoreReturnsDataOutput.class));
			System.out.println(s);
		}
		return sl;
	}

	@PostMapping("/getStoreReturnProductsList")
	public String getStoreIndentProductsList(String returnId, Model m) {
		System.out.println(returnId);
		ReturnId returnid = null;
		try {
			returnid = objectMapper.readValue(returnId, ReturnId.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<StoreReturnProductsList> storeReturnProductsList = storeReturnsDao.getStoreReturnsProductsList(returnid);

		 List<StoreReturnProducts> storeReturnProducts = new ArrayList();
		 for (StoreReturnProductsList s : storeReturnProductsList)
			 storeReturnProducts.add(modelMapper.map(s, StoreReturnProducts.class));
		
		m.addAttribute("productsList", storeReturnProducts);

		for (StoreReturnProductsList s : storeReturnProductsList)
			System.out.println(s);

		return "store/storeReturnProducts";
	}
	
	
	
	
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
