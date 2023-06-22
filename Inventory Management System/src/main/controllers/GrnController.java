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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import main.dao.procurement.GrnDAO;
import main.models.grnModels.entities.ImGrn;
import main.models.grnModels.inputModels.GrnIdInput;
import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.inputModels.GrnInputList;
import main.models.grnModels.inputModels.ProductInfoMapping;
import main.models.grnModels.outputModels.GrnListProductsOutputModel;
import main.models.grnModels.outputModels.ImGrnOutputModel;

@Controller
public class GrnController {

	@Autowired
	GrnDAO grndao;
	@Autowired
	ProductInfoMapping pi;

	@PostMapping("/makeGrn")
	public void makeGrn(String jsonData) throws JsonMappingException, JsonProcessingException {
		System.out.println(jsonData);
		ObjectMapper o = new ObjectMapper();
		ModelMapper mapper = new ModelMapper();

		GrnInputList grnInputList = null;
		try {
			grnInputList = o.readValue(jsonData, GrnInputList.class);
			System.out.println("\n");
			System.out.println(grnInputList.toString());
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		pi = o.readValue(jsonData, ProductInfoMapping.class);
		System.out.println(pi.toString());

		ImGrn imGrn = mapper.map(grnInputList, ImGrn.class);
		System.out.println("\n");
		System.out.println(imGrn.toString());

		grndao.saveGrn(imGrn);
		grndao.updateStock(pi);

	}

	@RequestMapping(value = "/getGrnProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGrnProducts(GrnIdInput grnIdInput) throws JsonProcessingException {
		List<GrnListProductsOutputModel> l = grndao.getGrnProducts(grnIdInput);
		for (GrnListProductsOutputModel i : l) {
			System.out.println(i.toString());
		}
		ObjectMapper ob = new ObjectMapper();
		ob.registerModule(new JavaTimeModule());
		String jsonData = ob.writeValueAsString(l);
		System.out.println(jsonData);
		return jsonData;

	}

	@RequestMapping(value = "/getGrnList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGrnList(GrnInputFilters grnInputFilter) throws JsonProcessingException {
		System.out.println(grnInputFilter.toString());
		List<ImGrnOutputModel> l = grndao.getGrnList(grnInputFilter);

		for (ImGrnOutputModel i : l) {
			System.out.println(i.toString());
		}
		ObjectMapper ob = new ObjectMapper();
		ob.registerModule(new JavaTimeModule());
		String jsonData = ob.writeValueAsString(l);
		return jsonData;
	}

}