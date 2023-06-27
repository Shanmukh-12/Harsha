package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcurementControllers {

	// Returns the HomeProcurement page
	@GetMapping("/HomeProcurement")
	public String getIndex() {
		return "procurement/HomeProcurement";
	}

	// Returns the PurchasesList page
	@GetMapping("/PurchasesList")
	public String getPurchasesList() {
		return "procurement/PurchasesList";
	}

	// Returns the grnlist page
	@GetMapping("/grnData")
	public String getGrn() {
		return "procurement/grnlist";
	}

	// Returns the create-grn page
	@GetMapping("/createGRN")
	public String createGRN() {
		return "procurement/create-grn";
	}

	// Returns the PurchasesList page
	@GetMapping("/purchaseOrderData")
	public String purchaseOrderData() {
		return "procurement/PurchasesList";
	}

	// Returns the purchase orders page
	@GetMapping("/createPurchaseOrder")
	public String createPurchaseOrder() {
		return "procurement/purchase orders";
	}

	// Returns the purchase orders from scratch page
	@GetMapping("/createNewPurchaseOrders")
	public String createNewPurchaseOrders() {
		return "procurement/purchase orders from scratch";
	}

	// Returns the indents page
	@GetMapping("/indents")
	public String indents() {
		return "procurement/indents";
	}

	// Returns the PurchaseReturnsList page
	@GetMapping("/prnData")
	public String prnData() {
		return "procurement/PurchaseReturnsList";
	}

	// Returns the PurchaseReturnsList page
	@GetMapping("/prnData2")
	public String prnData2() {
		return "procurement/PurchaseReturnsList";
	}

	// Returns the createprn page
	@GetMapping("/createPRN")
	public String createPRN() {
		return "procurement/createprn";
	}

	// Returns the addHSN page
	@GetMapping("/addHSN")
	public String addHSN() {
		return "procurement/addHSN";
	}

	// Returns the addProductCategory page
	@GetMapping("/addProductCategory")
	public String addProductCategory() {
		return "procurement/addProductCategory";
	}

	// Returns the addProduct page
	@GetMapping("/addProduct")
	public String addProduct() {
		return "procurement/addProduct";
	}

	// Returns the Warehouse page
	@GetMapping("/warehousestock")
	public String warehouse() {
		return "procurement/Warehouse";
	}
}