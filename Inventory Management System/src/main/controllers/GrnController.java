package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import main.bll.procurement.GrnBll;
import main.dao.procurement.GrnDAO;
import main.models.grnModels.dto.GrnAmount;
import main.models.grnModels.entities.ImGrn;
import main.models.grnModels.inputModels.GrnIdInput;
import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.inputModels.GrnInputList;
import main.models.grnModels.outputModels.GrnListProductsOutputModel;
import main.models.grnModels.outputModels.ImGrnOutputModel;

@Controller
public class GrnController {

	@Autowired
	GrnDAO grndao;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	GrnBll grnBll;

	@PostMapping("/makeGrn")
	// It takes GRN data as input and updates it in the GRN table and also the stock table.
	public void makeGrn(String jsonData) {

		// Create an object mapper to deserialize JSON
		ObjectMapper objectMapper = new ObjectMapper();

		GrnInputList grnInputList = null;
		try {

			// Deserialize the JSON data into a GrnInputList object
			grnInputList = objectMapper.readValue(jsonData, GrnInputList.class);

		} catch (JsonProcessingException e) {

			// Handle any JSON processing exceptions and print the stack trace
			e.printStackTrace();
		}

		// Map the GrnInputList object to an ImGrn object using the model mapper
		ImGrn imGrn = modelMapper.map(grnInputList, ImGrn.class);
		imGrn.setGrnId(0);

		// Calculate the GrnAmount using the GrnBll and set it on the ImGrn object
		GrnAmount amount = grnBll.getGrnAmount(grnInputList);
		imGrn.setGrnAmount(amount.getAmount());

		try {
			// Save the ImGrn object using the GrnDAO
			grndao.saveGrn(imGrn);

			// Update the stock using the GrnDAO
			grndao.updateStock(grnInputList);

			// Update the purchase order using the GrnDAO
			grndao.updatePurchaseOrder(grnInputList);
		} catch (Exception e) {
			// Handle any exceptions that occur during the database operations and print the stack trace
			e.printStackTrace();
		}
	}

	// It takes GRN id as input and returns list of products corresponing to that GRN id
	@RequestMapping(value = "/getGrnProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGrnProducts(GrnIdInput grnIdInput) {
		try {
			// Retrieve the list of GrnListProductsOutputModel objects from the GrnDAO
			List<GrnListProductsOutputModel> productsList = grndao.getGrnProducts(grnIdInput);

			// Create an object mapper and register the JavaTimeModule for date/time serialization/deserialization
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());

			// Convert the list of GrnListProductsOutputModel objects to JSON string
			String jsonData = objectMapper.writeValueAsString(productsList);

			// Return the JSON data as the response
			return jsonData;

		} catch (JsonProcessingException e) {
			// Handle the JsonProcessingException and print the stack trace
			e.printStackTrace();
			// Return an error message or appropriate response
			return "Error occurred while processing JSON data";
		}
	}

	@RequestMapping(value = "/getGrnList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGrnList(GrnInputFilters grnInputFilter) {
		try {

			// Retrieve the list of ImGrnOutputModel objects from the GrnDAO based on the input filter
			List<ImGrnOutputModel> l = grndao.getGrnList(grnInputFilter);

			// Create an object mapper and register the JavaTimeModule for date/time serialization/deserialization
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());

			// Convert the list of ImGrnOutputModel objects to JSON string
			String jsonData = objectMapper.writeValueAsString(l);

			// Return the JSON data as the response
			return jsonData;
		} catch (JsonProcessingException e) {
			// Handle the JsonProcessingException and print the stack trace
			e.printStackTrace();
			// Return an error message or appropriate response
			return "Error occurred while processing JSON data";
		}
	}

}