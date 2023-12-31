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

import main.dao.priceReview.PriceReviewDAO;
import main.models.priceReviewModels.entities.PriceReviewList;
import main.models.priceReviewModels.inputModels.PriceReviewFilterInput;
import main.models.priceReviewModels.inputModels.PriceReviewInputList;
import main.models.priceReviewModels.outputModels.PriceReviewFilterOutput;
import main.models.priceReviewModels.outputModels.PriceReviewProductsListData;

@Controller
public class PriceReviewController {

	@Autowired
	PriceReviewDAO priceReviewDAO;

	// This method is used to retrieve list of all price reviews
	@PostMapping("/priceReviewListButton")
	public String showDataPage(Model model) {

		List<PriceReviewList> priceReview = priceReviewDAO.getPriceReview();
		model.addAttribute("priceReview", priceReview);
		return "inventory/priceReviewList";
	}

	/*
	 * This method is responsible for persisting price review details (product category, product name, batch no, old
	 * price, new price and reason) to DB
	 */
	@PostMapping("/createPriceReview")
	public String updateData(String jsonData, Model model) {

		ObjectMapper objectMapper = new ObjectMapper();
		ModelMapper modelMapper = new ModelMapper();
		PriceReviewInputList priceReviewInputList = null;

		try {
			priceReviewInputList = objectMapper.readValue(jsonData, PriceReviewInputList.class);
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}

		PriceReviewList priceReviewList = modelMapper.map(priceReviewInputList, PriceReviewList.class);

		priceReviewDAO.savePriceReview(priceReviewList);

		return "inventory/priceReview";

	}

	// This method is for displaying the list of all the products in each price review ID
	@PostMapping("/getPriceReviewProductsList")
	public String getPriceReviewProductsList(String prId, Model m) {

		ObjectMapper objectMapper = new ObjectMapper();

		PriceReviewInputList pricereviewid = null;
		try {
			pricereviewid = objectMapper.readValue(prId, PriceReviewInputList.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<PriceReviewProductsListData> priceReviewProductsListData = priceReviewDAO
				.getPriceReviewProductsList(pricereviewid);

		m.addAttribute("productsList", priceReviewProductsListData);

		return "inventory/priceReviewProducts";
	}

	// This method filters price review ID's by product category Id, product Id and From date
	@PostMapping("/getFilterDataByCategoryIdProductIdFrom1")
	public @ResponseBody List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductIdFrom(String filters,
			Model model) {
		PriceReviewFilterInput priceReviewFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			priceReviewFilterInput = objectMapper.readValue(filters, PriceReviewFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<PriceReviewFilterOutput> priceReviewFilterOutput = priceReviewDAO
				.getFilterDataByCategoryIdProductIdFrom(priceReviewFilterInput);
		return priceReviewFilterOutput;
	}

	// This method filters price review ID's by product category Id and product Id
	@PostMapping("/getFilterDataByCategoryIdProductId1")
	public @ResponseBody List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductId(String filters, Model model) {
		PriceReviewFilterInput priceReviewFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			priceReviewFilterInput = objectMapper.readValue(filters, PriceReviewFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<PriceReviewFilterOutput> priceReviewFilterOutput = priceReviewDAO
				.getFilterDataByCategoryIdProductId(priceReviewFilterInput);
		return priceReviewFilterOutput;
	}

	// This method filters price review ID's by product category Id and From Date
	@PostMapping("/getFilterDataByCategoryIdFrom1")
	public @ResponseBody List<PriceReviewFilterOutput> getFilterDataByCategoryIdFrom(String filters, Model model) {
		PriceReviewFilterInput priceReviewFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			priceReviewFilterInput = objectMapper.readValue(filters, PriceReviewFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<PriceReviewFilterOutput> priceReviewFilterOutput = priceReviewDAO
				.getFilterDataByCategoryIdFrom(priceReviewFilterInput);
		return priceReviewFilterOutput;
	}

	// This method filters price review ID's by product category Id
	@PostMapping("/getFilterDataByCategoryId1")
	public @ResponseBody List<PriceReviewFilterOutput> getFilterDataByCategoryId(String filters, Model model) {
		PriceReviewFilterInput priceReviewFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			priceReviewFilterInput = objectMapper.readValue(filters, PriceReviewFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<PriceReviewFilterOutput> priceReviewFilterOutput = priceReviewDAO
				.getFilterDataByCategoryId(priceReviewFilterInput);
		return priceReviewFilterOutput;
	}

	// This method filters price review ID's by From date
	@PostMapping("/getFilterDataByFrom1")
	public @ResponseBody List<PriceReviewFilterOutput> getFilterDataByFrom(String filters, Model model) {
		PriceReviewFilterInput priceReviewFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			priceReviewFilterInput = objectMapper.readValue(filters, PriceReviewFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<PriceReviewFilterOutput> priceReviewFilterOutput = priceReviewDAO
				.getFilterDataByFrom(priceReviewFilterInput);
		return priceReviewFilterOutput;
	}

	// This method filters price review ID's by To date
	@PostMapping("/getFilterDataByTo1")
	public @ResponseBody List<PriceReviewFilterOutput> getFilterDataByTo(String filters, Model model) {
		PriceReviewFilterInput priceReviewFilterInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			priceReviewFilterInput = objectMapper.readValue(filters, PriceReviewFilterInput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<PriceReviewFilterOutput> priceReviewFilterOutput = priceReviewDAO
				.getFilterDataByTo(priceReviewFilterInput);
		return priceReviewFilterOutput;
	}

}
