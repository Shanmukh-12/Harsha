package main.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.dal.indents.IndentsDAL;
import main.models.indentModels.entities.ProcurementIndentsList;
import main.models.indentModels.inputModels.ProcurementIndentsInputList;

@Controller
public class IndentsController {

	@Autowired
	IndentsDAL procurementIndentsDAL;

	@PostMapping("/createProcurementIndent")
	public String createStoreIndent(String jsonData, Model m) {
		ObjectMapper objectMapper = new ObjectMapper();
		ModelMapper modelMapper = new ModelMapper();
		ProcurementIndentsInputList procurementIndentsInputList = null;

		try {
			procurementIndentsInputList = objectMapper.readValue(jsonData, ProcurementIndentsInputList.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ProcurementIndentsList procurementIndentsList = modelMapper.map(procurementIndentsInputList,
				ProcurementIndentsList.class);

		m.addAttribute("data", procurementIndentsList);
		System.out.println(procurementIndentsList.toString());

		procurementIndentsDAL.saveProcurementIndent(procurementIndentsList);

		return "inventory/createIndent";
	}

	@GetMapping("/indentsButton")
	public String createVendor(Model m) {
		m.addAttribute("indents", procurementIndentsDAL.getAllIndents());
		return "inventory/indents";
	}

}