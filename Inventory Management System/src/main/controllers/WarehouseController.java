package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import main.models.warehouseModels.dtomodels.ProductDetailsDTO;
import main.models.warehouseModels.dtomodels.ProductsDataDTO;
import main.models.warehouseModels.outputmodels.ProductCategoryCount;
import main.models.warehouseModels.outputmodels.TotalOverallStock;
import main.models.warehouseModels.outputmodels.TotalWarehouseVal;
import main.models.warehouseModels.outputmodels.VendorCount;
import main.service.procurement.ProcurementService;

@Controller
public class WarehouseController {

	@Autowired
	ProcurementService procurementService;

  
	//gets total count of products in inventory with its name and count
	@RequestMapping(value = "/getTotalProductsCount", method = RequestMethod.GET)
	@ResponseBody
	public TotalOverallStock getTotalProductsCount() {
		TotalOverallStock totalStock = procurementService.getTotalStock();
	     return totalStock; //returns total stock inside warehouse

	}
	  
	
    //gets total count of categories in inventory
	@RequestMapping(value = "/getCategoriesCount", method = RequestMethod.GET)
	@ResponseBody
	public ProductCategoryCount getCategoriesCount() {
		ProductCategoryCount categoryCount = procurementService.getCategoriesCount();
		return categoryCount; //returns total categories of products available

	}
	
    //gets total cost of all products in inventory
	@RequestMapping(value = "/getWarehouseValue", method = RequestMethod.GET)
	@ResponseBody
	public TotalWarehouseVal getWarehouseValue() {
		TotalWarehouseVal warehouseValue = procurementService.getWarehouseValue();
		return warehouseValue; //returns total value of warehouse products

	}
	
    //gets total count of vendors in inventory
	@RequestMapping(value = "/getVendorsCount", method = RequestMethod.GET)
	@ResponseBody
	public VendorCount getVendorsCount()  {
		VendorCount vendorCount = procurementService.getVendorsCount(); //returns total numbers of vendors
	    return vendorCount;

	}
	
	 //gets all required data for dashboard
	@RequestMapping(value = "/getStock", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductsDataDTO> getStock(){
		List<ProductsDataDTO> graphData = procurementService.getDashboardData();
		return graphData; //returns stock data required for graphs

	}

	//gets total count of products in inventory
	@RequestMapping(value = "/getProductsCount", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductDetailsDTO> getProductsCount() {
		List<ProductDetailsDTO> productsData = procurementService.getProductsCount();
		return productsData; //returns summation of products available in warehouse

	}
}