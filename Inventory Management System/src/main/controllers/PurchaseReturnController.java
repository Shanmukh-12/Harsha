package main.controllers;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.models.procurementModels.dtomodels.PurchaseReturnDTO;
import main.models.procurementModels.inputmodels.ProductInputMapping;
import main.models.procurementModels.inputmodels.PurchaseReturnId;
import main.models.procurementModels.inputmodels.PurchaseReturnInput;
import main.models.procurementModels.inputmodels.PurchasesReturnFilter;
import main.models.procurementModels.outputmodels.PurchaseReturnDetails;
import main.models.purchaseReturns.entityModels.PurchaseReturn;
import main.service.procurement.ProcurementService;

@Controller
public class PurchaseReturnController {

	@Autowired
	ProcurementService procurementService;

	// Gets all purchase return ids and additional details based on the entered filter
	@RequestMapping(value = "/getPurchaseReturnsListDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseReturnDetails> getPurchaseReturnsListDetails(@ModelAttribute PurchasesReturnFilter purchasesReturnFilter, Model m)
	{
		List<PurchaseReturnDetails> purchaseReturnData = procurementService.getPurchaseReturnsListDetails(purchasesReturnFilter); // Retrieve purchase return details
		return purchaseReturnData; //returns all purchase return id details
		
	}
	

	// Gets all purchase return id details based on the entered purchase id
	@RequestMapping(value = "/getPurchaseReturnsListDetailsById", method = RequestMethod.GET)
	@ResponseBody
	public PurchaseReturnDetails getPurchaseReturnsListDetailsById(@ModelAttribute PurchaseReturnId purchasesReturnFilter, Model m) {
		PurchaseReturnDetails purchaseReturnData = procurementService.getPurchaseReturnsListDetailsById(purchasesReturnFilter); // Retrieve purchase return details
	    return  purchaseReturnData;//returns all purchase return details of single id
	}
	

	// Gets all products under the purchase return id, taking the purchase id as input
	@RequestMapping(value = "/getPurchaseReturnProducts", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseReturnDTO> getPurchaseReturnProducts(PurchaseReturnId s) {
		List<PurchaseReturnDTO> purchaseReturnProductsData = procurementService.getPurchaseReturnProducts(s); // Retrieve purchase return products
		return purchaseReturnProductsData; //returns all purchase return products under a id
 	}

	 
	// Creates a purchase return by taking all products to be returned as input
	@RequestMapping(value = "/makePurchaseReturn", method = RequestMethod.POST)
	@ResponseBody
	public void makePurchseOrder(@RequestBody String json, Model m) {
		ObjectMapper objectMapper = new ObjectMapper();
		ModelMapper mp = new ModelMapper();//create new instance of model mapper for data mapping
		PurchaseReturnInput purchaseOrder;
		try {
			purchaseOrder = objectMapper.readValue(json, PurchaseReturnInput.class);
			PurchaseReturn purchaseReturn = mp.map(purchaseOrder, PurchaseReturn.class); // Map PurchaseReturnInput to ImPurchaseReturn entity
			ProductInputMapping pi = objectMapper.readValue(json, ProductInputMapping.class);// Map JSON to ProductInputMapping object
			procurementService.persistPurchaseReturn(purchaseReturn, pi); // Persist purchase return and its products
		} catch (JsonMappingException e) {	
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 


	}
}
