package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.models.warehouseModels.dtomodels.JoinClass2;
import main.models.warehouseModels.dtomodels.joinclass;
import main.models.warehouseModels.outputmodels.ProductCategoryCount;
import main.models.warehouseModels.outputmodels.TotalOverallStock;
import main.models.warehouseModels.outputmodels.TotalWarehouseVal;
import main.models.warehouseModels.outputmodels.VendorCount;
import main.service.procurement.ProcurementService;

@Controller
public class WarehouseController {

	@Autowired
	ProcurementService x;

	@RequestMapping(value = "/stockgraph", method = RequestMethod.GET)
	public String stockdata() {

		return "OtpReq";
	}
    //gets all required data for dashboard
	@RequestMapping(value = "/getStock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getStock() throws JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<joinclass> l = x.getAllData();
		System.out.println(ob.writeValueAsString(l));
		return ob.writeValueAsString(l);

	}
    //gets all required data to plot a graph using products details

	@RequestMapping(value = "/graphs", method = RequestMethod.GET)
	public String getGraph() {
		System.out.println("hello");
		return "graphs";
	}
	//gets total count of products in inventory with its name and count
	@RequestMapping(value = "/getTotalProductsCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getTotalProductsCount() throws JsonProcessingException {
		TotalOverallStock s = x.getTotalStock();
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}
    //gets total count of products in inventory
	@RequestMapping(value = "/getProductsCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getProductsCount() throws JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<JoinClass2> l = x.getProductsCount();
		System.out.println(ob.writeValueAsString(l));
		return ob.writeValueAsString(l);

	}
    //gets total count of categories in inventory

	@RequestMapping(value = "/getCategoriesCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getCategoriesCount() throws JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		ProductCategoryCount l = x.getCategoriesCount();
		System.out.println(ob.writeValueAsString(l));
		return ob.writeValueAsString(l);

	}
    //gets total cost of all products in inventory

	@RequestMapping(value = "/getWarehouseValue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getWarehouseValue() throws JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		TotalWarehouseVal l = x.getWarehouseValue();
		System.out.println(ob.writeValueAsString(l));
		return ob.writeValueAsString(l);

	}
    //gets total count of vendors in inventory

	@RequestMapping(value = "/getVendorsCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getVendorsCount() throws JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		VendorCount l = x.getVendorsCount();
		System.out.println(ob.writeValueAsString(l));
		return ob.writeValueAsString(l);

	}

	public WarehouseController() {

	}

}