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

	@PostMapping("/priceReviewListButton")
	public String showDataPage(Model model) {

		List<PriceReviewList> priceReview = priceReviewDAO.getPriceReview();
		model.addAttribute("priceReview", priceReview);
		return "inventory/priceReviewList";
	}

	@PostMapping("/createPriceReview")
	public String updateData(String jsonData, Model model) {

		System.out.println(jsonData);

		ObjectMapper objectMapper = new ObjectMapper();
		ModelMapper modelMapper = new ModelMapper();
		PriceReviewInputList priceReviewInputList = null;

		try {
			priceReviewInputList = objectMapper.readValue(jsonData, PriceReviewInputList.class);
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}

		System.out.println(priceReviewInputList);
		PriceReviewList priceReviewList = modelMapper.map(priceReviewInputList, PriceReviewList.class);
		System.out.println(priceReviewList);

		priceReviewDAO.savePriceReview(priceReviewList);

		return "inventory/priceReview";

	}

	@PostMapping("/getPriceReviewProductsList")
	public String getPriceReviewProductsList(String pr_id, Model m) {
		System.out.println("in the controller");
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(pr_id);
		PriceReviewInputList pricereviewid = null;
		try {
			pricereviewid = objectMapper.readValue(pr_id, PriceReviewInputList.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<PriceReviewProductsListData> priceReviewProductsListData = priceReviewDAO
				.getPriceReviewProductsList(pricereviewid);

		System.out.println("after dao");
		m.addAttribute("productsList", priceReviewProductsListData);

		for (PriceReviewProductsListData s : priceReviewProductsListData)
			System.out.println(s);

		return "inventory/priceReviewProducts";
	}

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
		System.out.println(priceReviewFilterInput);

		List<PriceReviewFilterOutput> sl = priceReviewDAO
				.getFilterDataByCategoryIdProductIdFrom(priceReviewFilterInput);
		return sl;
	}

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
		System.out.println(priceReviewFilterInput);

		List<PriceReviewFilterOutput> sl = priceReviewDAO.getFilterDataByCategoryIdProductId(priceReviewFilterInput);
		return sl;
	}

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
		System.out.println(priceReviewFilterInput);

		List<PriceReviewFilterOutput> sl = priceReviewDAO.getFilterDataByCategoryIdFrom(priceReviewFilterInput);
		return sl;
	}

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
		System.out.println(priceReviewFilterInput);

		List<PriceReviewFilterOutput> sl = priceReviewDAO.getFilterDataByCategoryId(priceReviewFilterInput);
		return sl;
	}

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
		System.out.println(priceReviewFilterInput);

		List<PriceReviewFilterOutput> sl = priceReviewDAO.getFilterDataByFrom(priceReviewFilterInput);
		return sl;
	}

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
		System.out.println(priceReviewFilterInput);

		List<PriceReviewFilterOutput> sl = priceReviewDAO.getFilterDataByTo(priceReviewFilterInput);
		return sl;
	}

}
