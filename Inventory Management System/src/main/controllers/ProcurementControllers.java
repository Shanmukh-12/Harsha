package main.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcurementControllers {

	private static Logger logger = LoggerFactory.getLogger(ProcurementControllers.class);

	// Returns the HomeProcurement page
	@GetMapping("/HomeProcurement")
	public String getIndex() {
		logger.info("redirecting to procurement home page");
		return "procurement/HomeProcurement";
	}

	// Returns the grnlist page
	@GetMapping("/grnData")
	public String getGrn() {
		logger.info("redirecting to grnlist page");

		return "procurement/grnlist";
	}

	// Returns the create-grn page
	@GetMapping("/createGRN")
	public String createGRN() {
		logger.info("redirecting to create-grn page");

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
		logger.info("redirecting to purchase orders page");

		return "procurement/purchase orders";
	}

	// Returns the purchase orders from scratch page
	@GetMapping("/createNewPurchaseOrders")
	public String createNewPurchaseOrders() {
		logger.info("redirecting to purchase orders from scratch page");

		return "procurement/purchase orders from scratch";
	}

	// Returns the indents page
	@GetMapping("/indents")
	public String indents() {
		logger.info("redirecting to indents page");

		return "procurement/indents";
	}

	// Returns the PurchaseReturnsList page
	@GetMapping("/prnData")
	public String prnData() {
		logger.info("redirecting to PurchaseReturnsList page");
		return "procurement/PurchaseReturnsList";
	}

	// Returns the createprn page
	@GetMapping("/createPRN")
	public String createPRN() {
		logger.info("redirecting to createprn page");
		return "procurement/createprn";
	}

	// Returns the addHSN page
	@GetMapping("/addHSN")
	public String addHSN() {
		logger.info("redirecting to addHSN page");
		return "procurement/addHSN";
	}

	// Returns the addProductCategory page
	@GetMapping("/addProductCategory")
	public String addProductCategory() {
		logger.info("redirecting to addProductCategory page");

		return "procurement/addProductCategory";
	}

	// Returns the addProduct page
	@GetMapping("/addProduct")
	public String addProduct() {
		logger.info("redirecting to addProduct page");

		return "procurement/addProduct";
	}

	// Returns the Warehouse page
	@GetMapping("/warehousestock")
	public String warehouse() {
		logger.info("redirecting to Warehouse page");

		return "procurement/Warehouse";
	}
}