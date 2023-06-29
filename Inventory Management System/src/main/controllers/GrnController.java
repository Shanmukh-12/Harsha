package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import main.service.procurement.interfaces.GrnService;

@Controller
public class GrnController {

	@Autowired
	GrnDAO grndao;

	@Autowired
	GrnService grnService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	GrnBll grnBll;

	// creating the object for the logger
	private static final Logger logger = LoggerFactory.getLogger(GrnController.class);

	@PostMapping("/makeGrn")
	// It takes GRN data as input and updates it in the GRN table and also the stock table.
	public @ResponseBody String makeGrn(String jsonData) {
		logger.info("Received request to make GRN");

		// Create an object mapper to deserialize JSON
		ObjectMapper objectMapper = new ObjectMapper();

		GrnInputList grnInputList = null;
		try {

			// Deserialize the JSON data into a GrnInputList object
			grnInputList = objectMapper.readValue(jsonData, GrnInputList.class);
			logger.info("jsonData is mapped to the grnInputList");

		} catch (JsonProcessingException e) {

			logger.error("Error occurred while parsing JSON data: {}", e.getMessage());

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

			logger.info("GRN successfully created and updated in the database");

		} catch (Exception e) {
			// Handle any exceptions that occur during the database operations and print the stack trace
			e.printStackTrace();
			logger.error("Error occurred while processing GRN: {}", e.getMessage());

		}
		logger.info("completion of makeGrn method execution!");
		return "Success";
	}

	// It takes GRN id as input and returns list of products corresponing to that GRN id
	@RequestMapping(value = "/getGrnProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGrnProducts(GrnIdInput grnIdInput) {
		logger.info("Received request to get GRN products");

		try {
			// Retrieve the list of GrnListProductsOutputModel objects from the GrnDAO
			List<GrnListProductsOutputModel> productsList = grndao.getGrnProducts(grnIdInput);

			// Create an object mapper and register the JavaTimeModule for date/time serialization/deserialization
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());

			// Convert the list of GrnListProductsOutputModel objects to JSON string
			String jsonData = objectMapper.writeValueAsString(productsList);

			logger.info("GRN products successfully retrieved");
			logger.info("completed the execution of getGrnProducts method!");

			// Return the JSON data as the response
			return jsonData;

		} catch (JsonProcessingException e) {

			logger.error("Error occurred while processing GRN products: {}", e.getMessage());
			// Handle the JsonProcessingException and print the stack trace
			e.printStackTrace();
			// Return an error message or appropriate response
			return "Error occurred while processing JSON data";
		}

	}

	@RequestMapping(value = "/getGrnList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGrnList(String filters) {

		logger.info("Received request to get GRN list");

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		GrnInputFilters grnInputFilter = null;
		try {
			// Deserializing filters string to StoreFilters object
			grnInputFilter = objectMapper.readValue(filters, GrnInputFilters.class);
		} catch (Exception e) {
			logger.error("Error occurred while parsing filters JSON data: {}", e.getMessage());

			e.printStackTrace();
		}

		// Retrieve the list of ImGrnOutputModel objects from the GrnDAO based on the input filter
		List<ImGrnOutputModel> l = grnService.getGrnList(grnInputFilter);

		// Convert the list of ImGrnOutputModel objects to JSON string
		String jsonData = null;
		try {
			jsonData = objectMapper.writeValueAsString(l);
		} catch (JsonProcessingException e) {
			logger.error("Error occurred while processing GRN list: {}", e.getMessage());

			e.printStackTrace();
		}

		logger.info("GRN list successfully retrieved");

		// Return the JSON data as the response
		return jsonData;
	}

}