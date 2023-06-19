package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import main.dao.storeissues.StoreIssuesDAO;
import main.models.storeissuesModels.StoreIssues;

import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class StoreIssuesController {
	
	@Autowired
	StoreIssuesDAO storeIssuesDao;

	@PostMapping("/createProcurementIndent")
	public String createStoreIndent(String jsonData, Model m) {
		// System.out.println("Data is " + jsonData);
		ObjectMapper objectMapper = new ObjectMapper();
		StoreIssues storeIssues = null;

		try {
			storeIssues = objectMapper.readValue(jsonData, StoreIssues.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		m.addAttribute("data", storeIssues);
		System.out.println(storeIssues.toString());

		storeIssuesDao.saveStoreIssues(storeIssues);

		return "inventory/createIndent";
	}


	@GetMapping("/indentsButton")
	public String createVendor(Model m) {
		m.addAttribute("indents", storeIssuesDao.getAllIndents());
		return "inventory/indents";
	}
	

}
