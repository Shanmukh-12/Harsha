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

import main.models.procurementModels.dtomodels.PurchaseReturnJoinClass;
import main.models.procurementModels.inputmodels.ProductInputMapping;
import main.models.procurementModels.inputmodels.PurchaseReturnId;
import main.models.procurementModels.inputmodels.PurchaseReturnInput;
import main.models.procurementModels.inputmodels.PurchasesReturnFilter;
import main.models.procurementModels.outputmodels.PurchaseReturnOutput;
import main.models.purchaseReturns.entityModels.ImPurchaseReturn;
import main.service.procurement.ProcurementService;

@Controller
public class PurchaseReturnController {

	@Autowired
	ProcurementService x;

	@RequestMapping(value = "/getPurchaseReturnsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseReturnsList(@ModelAttribute PurchasesReturnFilter p, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		System.out.println(p.getGrn_value());
		System.out.println(p.getPurchase_return_date());
		System.out.println(p.getVendor_id());
		List<PurchaseReturnId> s = x.getPurchaseReturnsList(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}

	@RequestMapping(value = "/getPurchaseReturnsList2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseReturnsList2(@ModelAttribute PurchasesReturnFilter p, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		System.out.println(p.getGrn_value());
		System.out.println(p.getPurchase_return_date());
		System.out.println(p.getPurchase_return_date1());
		System.out.println(p.getVendor_id());
		List<PurchaseReturnOutput> s = x.getPurchaseReturnsList2(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}

	@RequestMapping(value = "/getPurchaseReturnsList3", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseReturnsList2(@ModelAttribute PurchaseReturnId p, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		System.out.println(p.getPurchase_return_id());

		PurchaseReturnOutput s = x.getPurchaseReturnsList3(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}

	@RequestMapping(value = "/getPurchaseReturnProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseReturnProducts(PurchaseReturnId s) throws JsonProcessingException {
		List<PurchaseReturnJoinClass> l = x.getPurchaseReturnProducts(s);
		for (PurchaseReturnJoinClass r : l) {
			System.out.println(r.toString());
		}
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(l));
		return ob.writeValueAsString(l);

	}

	@RequestMapping(value = "/makePurchaseReturn", method = RequestMethod.POST)
	@ResponseBody
	public String makePurchseOrder(@RequestBody String json, Model m) throws JsonProcessingException {
		System.out.println(json);
		ObjectMapper objectMapper = new ObjectMapper();
		ModelMapper mp = new ModelMapper();

		System.out.println("hii" + json);
		PurchaseReturnInput purchaseOrder = objectMapper.readValue(json, PurchaseReturnInput.class);
		ImPurchaseReturn purchaseReturn = mp.map(purchaseOrder, ImPurchaseReturn.class);
		System.out.println(purchaseReturn.toString());
		ProductInputMapping pi = objectMapper.readValue(json, ProductInputMapping.class);
		System.out.println(pi.toString());
		x.persistpurchasereturn(purchaseReturn, pi);

		return null;

	}

}