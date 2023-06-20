package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.adjustments.AdjustmentsDAO;
import main.models.adjustmentsModels.entities.AdjustmentsList;
import main.models.adjustmentsModels.inputModels.AdjustmentsInputList;

@Controller
public class AdjustmentsController {

	@Autowired
	AdjustmentsDAO adjustmentsDAL;

	@PostMapping("/adjustments")
	public String showDataPage(Model model) {

		List<AdjustmentsList> adjustments = adjustmentsDAL.getAdjustments();
		model.addAttribute("adjustments", adjustments);
		return "inventory/adjustments";
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

		adjustmentsDAL.saveAdjustments(adjustmentsList);

		return "inventory/adjustments";

	}

}
