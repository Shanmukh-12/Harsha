package main.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.procurement.GrnDAO;
import main.models.grnModels.entities.ImGrn;
import main.models.grnModels.inputModels.GrnInputList;
import main.models.grnModels.inputModels.ProductInfoMapping;

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

}