package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcurementControllers {

	@GetMapping("/procurementHome")
	public String getIndex() {
		return "procurement/homeProcurement";
	}

	@GetMapping("/grnData")
	public String getGrn() {
		return "procurement/grnlist";
	}

	@GetMapping("/createGRN")
	public String createGRN() {
		return "procurement/create-grn";
	}

	@GetMapping("/purchaseOrderData")
	public String purchaseOrderData() {
		return "procurement/purchaseorderdata";
	}

	@GetMapping("/createPurchaseOrder")
	public String createPurchaseOrder() {
		return "procurement/purchaseOrders";
	}

	@GetMapping("/createNewPurchaseOrders")
	public String createNewPurchaseOrders() {
		return "purchase orders from scratch";
	}

	@GetMapping("/indents")
	public String indents() {
		return "procurement/indents";
	}

	@GetMapping("/prnData")
	public String prnData() {
		return "procurement/prnList";
	}

	@GetMapping("/createPRN")
	public String createPRN() {
		return "procurement/createPrn";
	}

	@GetMapping("/addHSN")
	public String addHSN() {
		return "procurement/addHSN";
	}

	@GetMapping("/addProductCategory")
	public String addProductCategory() {
		return "procurement/addProductCategory";
	}

	@GetMapping("/addProduct")
	public String addProduct() {
		return "procurement/addProduct";
	}

}
