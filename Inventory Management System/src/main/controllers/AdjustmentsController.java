package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.adjustments.AdjustmentsDAO;
import main.models.adjustmentsModels.entities.AdjustmentsList;
import main.models.adjustmentsModels.inputModels.AdjustmentsInputList;
import main.models.adjustmentsModels.outputModels.AdjustmentProductsListData;

@Controller
public class AdjustmentsController {

	@Autowired
	AdjustmentsDAO adjustmentsDAO;

	@PostMapping("/adjustmentsListButton")
	public String showDataPage(Model model) {
		System.out.println("hi in controller1");
		List<AdjustmentsList> adjustments = adjustmentsDAO.getAdjustments();
		model.addAttribute("adjustments", adjustments);
		return "inventory/adjustmentsList";
	}

	@PostMapping("/createAdjustments")
	public String updateData(String jsonData, Model model) {

		System.out.println(jsonData);

		ObjectMapper objectMapper = new ObjectMapper();
		ModelMapper modelMapper = new ModelMapper();
		AdjustmentsInputList adjustmentsInputList = null;

		try {
			adjustmentsInputList = objectMapper.readValue(jsonData, AdjustmentsInputList.class);
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}

		System.out.println(adjustmentsInputList);
		AdjustmentsList adjustmentsList = modelMapper.map(adjustmentsInputList, AdjustmentsList.class);
		System.out.println(adjustmentsList);

		adjustmentsDAO.saveAdjustments(adjustmentsList);

		return "inventory/adjustments";

	}

	@PostMapping("/getAdjustmentProductsList")
	public String getAdjustmentProductsList(String adjs_id, Model m) {
		System.out.println("in the controller");
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(adjs_id);
		AdjustmentsInputList adjustmentid = null;
		try {
			adjustmentid = objectMapper.readValue(adjs_id, AdjustmentsInputList.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<AdjustmentProductsListData> adjustmentProductsListData = adjustmentsDAO
				.getAdjustmentProductsList(adjustmentid);

		// List<StoreIndentProducts> storeIndentProducts = new ArrayList();
		// for (StoreIndentProductsList s : storeIndentProductsList)
		// storeIndentProducts.add(modelMapper.map(s, StoreIndentProducts.class));
		//
		System.out.println("after dao");
		m.addAttribute("productsList", adjustmentProductsListData);

		for (AdjustmentProductsListData s : adjustmentProductsListData)
			System.out.println(s);

		return "inventory/adjustmentProducts";
	}

}
