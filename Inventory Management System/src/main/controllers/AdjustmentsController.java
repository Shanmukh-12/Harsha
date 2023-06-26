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

import main.dao.adjustments.AdjustmentsDAO;
import main.models.adjustmentsModels.entities.AdjustmentsList;
import main.models.adjustmentsModels.inputModels.AdjustmentsFilterInput;
import main.models.adjustmentsModels.inputModels.AdjustmentsInputList;
import main.models.adjustmentsModels.outputModels.AdjustmentProductsListData;
import main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput;

@Controller
public class AdjustmentsController {

	@Autowired
	AdjustmentsDAO adjustmentsDAO;

	// The getAdjustments method is used to retrieve a list of all the Adjustments 
	@PostMapping("/adjustmentsListButton")
	public String showDataPage(Model model) {
		List<AdjustmentsList> adjustments = adjustmentsDAO.getAdjustments();
		model.addAttribute("adjustments", adjustments);
		return "inventory/adjustmentsList";
	}

	/*
	 * The saveAdjustments method is responsible for persisting adjustments details (product category, product name, batch no, original stock, updated
	 * stock and reason) to the database.
	 */
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

	// The getAdjustmentProductsList method is for displaying the list of all the products in each Adjustment ID
	@PostMapping("/getAdjustmentProductsList")
	public String getAdjustmentProductsList(String adjs_id, Model m) {

		ObjectMapper objectMapper = new ObjectMapper();

		AdjustmentsInputList adjustmentid = null;
		try {
			adjustmentid = objectMapper.readValue(adjs_id, AdjustmentsInputList.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<AdjustmentProductsListData> adjustmentProductsListData = adjustmentsDAO
				.getAdjustmentProductsList(adjustmentid);

		m.addAttribute("productsList", adjustmentProductsListData); // adding to the view

		for (AdjustmentProductsListData s : adjustmentProductsListData)
			System.out.println(s);

		return "inventory/adjustmentProducts";
	}

	// This method filters Adjustment ID's by product category Id, product Id and From date
	@PostMapping("/getFilterDataByCategoryIdProductIdFrom")
	public @ResponseBody List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductIdFrom(String filters,
			Model model) {
		AdjustmentsFilterInput adjustmentsFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			adjustmentsFilterInput = objectMapper.readValue(filters, AdjustmentsFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		List<AdjustmentsFilterOutput> sl = adjustmentsDAO
				.getFilterDataByCategoryIdProductIdFrom(adjustmentsFilterInput);
		return sl;
	}

	// This method filters Adjustment ID's by product category Id and product Id
	@PostMapping("/getFilterDataByCategoryIdProductId")
	public @ResponseBody List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductId(String filters, Model model) {
		AdjustmentsFilterInput adjustmentsFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			adjustmentsFilterInput = objectMapper.readValue(filters, AdjustmentsFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		List<AdjustmentsFilterOutput> sl = adjustmentsDAO.getFilterDataByCategoryIdProductId(adjustmentsFilterInput);
		return sl;
	}

	// This method filters Adjustment ID's by product category Id and From Date
	@PostMapping("/getFilterDataByCategoryIdFrom")
	public @ResponseBody List<AdjustmentsFilterOutput> getFilterDataByCategoryIdFrom(String filters, Model model) {
		AdjustmentsFilterInput adjustmentsFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			adjustmentsFilterInput = objectMapper.readValue(filters, AdjustmentsFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		List<AdjustmentsFilterOutput> sl = adjustmentsDAO.getFilterDataByCategoryIdFrom(adjustmentsFilterInput);
		return sl;
	}

	// This method filters Adjustment ID's by product category Id
	@PostMapping("/getFilterDataByCategoryId")
	public @ResponseBody List<AdjustmentsFilterOutput> getFilterDataByCategoryId(String filters, Model model) {
		AdjustmentsFilterInput adjustmentsFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			adjustmentsFilterInput = objectMapper.readValue(filters, AdjustmentsFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		List<AdjustmentsFilterOutput> sl = adjustmentsDAO.getFilterDataByCategoryId(adjustmentsFilterInput);
		return sl;
	}

	// This method filters Adjustment ID's by From date
	@PostMapping("/getFilterDataByFrom")
	public @ResponseBody List<AdjustmentsFilterOutput> getFilterDataByFrom(String filters, Model model) {
		AdjustmentsFilterInput adjustmentsFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			adjustmentsFilterInput = objectMapper.readValue(filters, AdjustmentsFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		List<AdjustmentsFilterOutput> sl = adjustmentsDAO.getFilterDataByFrom(adjustmentsFilterInput);
		return sl;
	}

	// This method filters Adjustment ID's by To date
	@PostMapping("/getFilterDataByTo")
	public @ResponseBody List<AdjustmentsFilterOutput> getFilterDataByTo(String filters, Model model) {
		AdjustmentsFilterInput adjustmentsFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			adjustmentsFilterInput = objectMapper.readValue(filters, AdjustmentsFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		List<AdjustmentsFilterOutput> sl = adjustmentsDAO.getFilterDataByTo(adjustmentsFilterInput);
		return sl;
	}

}
