package main.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import main.dao.indents.IndentsDao;
import main.models.indentModels.ProcurementIndentsList;

import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class IndentsController {
	
	
	@Autowired
	IndentsDao procurementIndentsDao;
	
	@PostMapping("/createProcurementIndent")
	public String createStoreIndent(String jsonData, Model m) {
		ObjectMapper objectMapper = new ObjectMapper();
		ProcurementIndentsList procurementIndentsList = null;

		try {
			procurementIndentsList = objectMapper.readValue(jsonData, ProcurementIndentsList.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		m.addAttribute("data", procurementIndentsList);
		System.out.println(procurementIndentsList.toString());

		procurementIndentsDao.saveProcurementIndent(procurementIndentsList);

		return "inventory/createIndent";
	}


	@GetMapping("/indentsButton")
	public String createVendor(Model m) {
		m.addAttribute("indents", procurementIndentsDao.getAllIndents());
		return "inventory/indents";
	}

}