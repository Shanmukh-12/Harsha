package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.storeIndents.StoreIndentsDao;
import main.models.storeModels.StoreIndentsList;

@Controller
public class StoreIndentController {

	@Autowired
	StoreIndentsDao storeIndentsDao;
	
	@PostMapping("/newCreateStoreIndent")
	public String createStoreIndent(String jsonData, Model m) {
//		System.out.println("Data is " + jsonData);
		ObjectMapper objectMapper = new ObjectMapper();
		StoreIndentsList storeIndentsList = null;
		
		try {
			storeIndentsList = objectMapper.readValue(jsonData, StoreIndentsList.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println(storeIndentsList);
		m.addAttribute("data", storeIndentsList);
		
		storeIndentsDao.saveStoreIndent(storeIndentsList);
		
		return "store/createStoreIndent";
	}

}
