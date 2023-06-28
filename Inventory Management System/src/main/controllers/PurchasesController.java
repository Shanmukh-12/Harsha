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

import main.models.procurementModels.dtomodels.PurchaseOrdersDTO;
import main.models.procurementModels.inputmodels.PagenationDetails;
import main.models.procurementModels.inputmodels.PurchaseId;
import main.models.procurementModels.inputmodels.PurchaseOrderInput;
import main.models.procurementModels.inputmodels.PurchasesFilter;
import main.models.procurementModels.outputmodels.PurchaseInformation;
import main.models.procurementModels.outputmodels.PurchaseOrderDetails;
import main.models.purchaseOrder.entityModels.PurchaseOrder;
import main.service.procurement.ProcurementService;

@Controller
public class PurchasesController {

	@Autowired
	ProcurementService procurementService;

	// Gets all purchase ids and additional details based on the entered filters
	@RequestMapping(value = "/getPurchaseIdDetails", method = RequestMethod.GET)
	@ResponseBody
	public PurchaseInformation getPurchaseIdDetails(@ModelAttribute PurchasesFilter purchasesFilter, Model m,
			@ModelAttribute PagenationDetails pageRequest) {
		System.out.println(pageRequest.getPage() + "luuu" + pageRequest.getPageSize());
		// Retrieve purchaseorder details
		PurchaseInformation p = procurementService.getPurchaseIdDetails(purchasesFilter, pageRequest);

		return p;// returns all purchaseid details
	}

	// Gets all purchase order details based on the entered purchase id
	@RequestMapping(value = "/getPurchaseIdDetailsById", method = RequestMethod.GET)
	@ResponseBody
	public PurchaseOrderDetails getPurchaseOrderDetailsById(@ModelAttribute PurchaseId purchaseId, Model m) {
		// Retrieve purchase order details
		PurchaseOrderDetails purchaseOrderData = procurementService.getPurchaseOrderDetailsById(purchaseId);
		return purchaseOrderData;// returns purchase order details of one purchase id

	}

	// Gets all products under the purchase order id, taking the purchase id as input
	@RequestMapping(value = "/getPurchaseProducts", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseOrdersDTO> getPurchaseProducts(PurchaseId purchaseId) {
		List<PurchaseOrdersDTO> purchaseProductsData = procurementService.getPurchaseProducts(purchaseId); // Retrieve
																											// purchase
																											// products
		return purchaseProductsData;// returns all purchase products under a purchase id
	}

	// Creates a purchase order by taking all products to be ordered as input
	@RequestMapping(value = "/makePurchseOrder", method = RequestMethod.POST)
	@ResponseBody
	public void makePurchseOrder(@RequestBody String json, Model m) {
		ObjectMapper objectMapper = new ObjectMapper();
		ModelMapper mapper = new ModelMapper();
		PurchaseOrderInput purchaseOrder1;
		try {
			purchaseOrder1 = objectMapper.readValue(json, PurchaseOrderInput.class);
			// Map JSON to PurchaseOrderInput object
			System.out.println(purchaseOrder1.toString()); // Print mapped purchase order details
			PurchaseOrder purchaseOrder = mapper.map(purchaseOrder1, PurchaseOrder.class); // Map PurchaseOrderInput to
																							// Im_Purchase_Order entity
			procurementService.persistPurchase(purchaseOrder); // Persist purchase order
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
