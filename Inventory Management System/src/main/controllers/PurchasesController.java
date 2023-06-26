package main.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import main.models.procurementModels.dtomodels.PurchaseJoinClass;
import main.models.procurementModels.inputmodels.PurchaseId;
import main.models.procurementModels.inputmodels.PurchaseOrderInput;
import main.models.procurementModels.inputmodels.PurchasesFilter;
import main.models.procurementModels.outputmodels.ImPurchaseOrderOutput;
import main.models.purchaseOrder.entityModels.Im_Purchase_Order;
import main.service.procurement.ProcurementService;

@Controller
public class PurchasesController {

	@Autowired
	ProcurementService x;
    //gets all purchase ids based on filter entered
	@RequestMapping(value = "/getPurchaseId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseId(@ModelAttribute PurchasesFilter p, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		System.out.println(p.getVendor_id());
		System.out.println(p.getPurchase_order_expected_date());

		List<PurchaseId> s = x.getPurchaseId(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}
	
    //gets all purchase ids and additionsl details based on filters entered
	@RequestMapping(value = "/getPurchaseIdDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseId2(@ModelAttribute PurchasesFilter p, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		System.out.println(p.getVendor_id());
		System.out.println(p.getPurchase_order_expected_date());
		System.out.println(p.getPurchase_order_expected_date1());

		List<ImPurchaseOrderOutput> s = x.getPurchaseId2(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}
	
    //gets all purchase id  details based on purchase id entered
	@RequestMapping(value = "/getPurchaseIdDetailsById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseId3(@ModelAttribute PurchaseId p, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		System.out.println(p.getPurchase_order_id());

		ImPurchaseOrderOutput s = x.getPurchaseId3(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}
	
    //gets all products under purchase id taking purchase id as input
	@RequestMapping(value = "/getPurchaseProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseProducts(PurchaseId p) throws JsonProcessingException {
		List<PurchaseJoinClass> s = x.getPurchaseProducts(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}
	
	//create purchase order taking all products to be ordered as input
	@RequestMapping(value = "/makePurchseOrder", method = RequestMethod.POST)
	@ResponseBody
	public void makePurchseOrder(@RequestBody String json, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		String json2 = json.toString();
		ObjectMapper ob = new ObjectMapper();
		ModelMapper mp = new ModelMapper();

		System.out.println("hii" + json);
		PurchaseOrderInput purchaseOrder1 = ob.readValue(json, PurchaseOrderInput.class);
		System.out.println(purchaseOrder1.toString());
		Im_Purchase_Order purchaseOrder = mp.map(purchaseOrder1, Im_Purchase_Order.class);

		System.out.println(purchaseOrder.toString());
		x.persistpurchase(purchaseOrder);

	}

}