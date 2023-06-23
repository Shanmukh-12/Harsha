package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import main.dal.indents.IndentsDAL;
import main.models.indentModels.entities.ProcurementIndentsList;
import main.models.indentModels.inputModels.FilterInput;
import main.models.indentModels.inputModels.ProcurementIndentsInputList;
import main.models.indentModels.outputModels.FilteredIndent;
import main.models.indentModels.outputModels.ProcurementIndentProductListData;
import main.models.storeModels.inputmodels.IndentId;

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

	@PostMapping("/getProcurementIndentProductsList")
	@ResponseBody
	public List<ProcurementIndentProductListData> getProcurementIndentProductsList(String indentId, Model m) {
		System.out.println("in the controller");
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(indentId);
		IndentId indentid = null;
		try {
			indentid = objectMapper.readValue(indentId, IndentId.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<ProcurementIndentProductListData> procurementIndentProductListData = procurementIndentsDAL
				.getProcurementIndentProductsList(indentid);

		// List<StoreIndentProducts> storeIndentProducts = new ArrayList();
		// for (StoreIndentProductsList s : storeIndentProductsList)
		// storeIndentProducts.add(modelMapper.map(s, StoreIndentProducts.class));
		//
		m.addAttribute("productsList", procurementIndentProductListData);

		for (ProcurementIndentProductListData s : procurementIndentProductListData)
			System.out.println(s);

		return procurementIndentProductListData;
	}

	@PostMapping("/filterIndents")
	@ResponseBody
	public List<FilteredIndent> filterIndents(String filters) {
		FilterInput filterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			filterInput = objectMapper.readValue(filters, FilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<FilteredIndent> sl = procurementIndentsDAL.getfilterIndents(filterInput);
		return sl;
	}

}