package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import main.dal.indents.IndentsDAL;

@Controller
public class InventoryControllers {

	@Autowired
	public IndentsDAL p;

	@GetMapping(value = "/inventoryHome")
	public String user() {
		return "inventory/inventoryHome";
	}

	@PostMapping(value = "/createIndentButton")
	public String CallCreateIndent() {
		return "inventory/indents";
	}

	@PostMapping(value = "/adjustmentsButton")
	public String CallAdjustments() {
		return "inventory/adjustments";
	}

	@PostMapping(value = "/priceReviewButton")
	public String CallPriceReview() {
		return "inventory/priceReview";
	}

	@PostMapping(value = "/createStockIssueButton")
	public String CreateStoreIssue() {
		return "inventory/createStockIssues";
	}

	@PostMapping(value = "/stockIssuesButton")
	public String StockIssues() {
		return "inventory/stockIssues";
	}

	@PostMapping(value = "/stockIssuedProductsButton")
	public String IssuedProductsIndents() {
		return "inventory/stockIssuedProducts";
	}

	@PostMapping(value = "/storeReturnsButton")
	public String StockReturns() {
		return "inventory/storeReturns";
	}

	@PostMapping(value = "/storeReturnedProductsButton")
	public String StockReturnedProducts() {
		return "inventory/storeReturnedProducts";
	}

	@PostMapping(value = "/indentProductsButton")
	public String IndentProducts() {
		return "inventory/indentProducts";
	}

	// @GetMapping("/indentsButton")
	// public String getIndentsPage(Model m) {
	// m.addAttribute("indents", p.getAllIndents());
	// return "inventory/indents";
	// }

	@PostMapping("/storeIndentsButton")
	public String getStoreIndentsPage(Model m) {
		m.addAttribute("indents", p.getAllIndents());
		return "inventory/storeIndents";
	}

	@PostMapping(value = "/logoutButton")
	public String CallLogout() {
		return "Login/login";

	}

}
