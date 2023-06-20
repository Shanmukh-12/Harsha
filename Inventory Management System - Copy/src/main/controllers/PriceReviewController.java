package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.priceReview.PriceReviewDAO;
import main.models.priceReviewModels.entities.PriceReviewList;
import main.models.priceReviewModels.inputModels.PriceReviewInputList;

@Controller
public class PriceReviewController {

	@Autowired
	PriceReviewDAO priceReviewDAL;

	@GetMapping("/priceReview")
	public String showDataPage(Model model) {

		List<PriceReviewList> priceReview = priceReviewDAL.getPriceReview();
		model.addAttribute("priceReview", priceReview);
		return "inventory/priceReview";
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

		priceReviewDAL.savePriceReview(priceReviewList);

		return "inventory/priceReview";

	}

}
