package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreControllers {

	@GetMapping("/storeHome")
	public String getStore() {
		return "store/storeHome";
	}

	@PostMapping("/listStoreIndent")
	public String listStoreIndent() {
		return "store/listStoreIndent";
	}

	@PostMapping("/createStoreIndent")
	public String createStoreIndent() {
		return "store/createStoreIndent";
	}

	@PostMapping("/listStoreReturns")
	public String listStoreReturns() {
		return "store/listStoreReturns";
	}

	@PostMapping("/createStoreReturn")
	public String createStoreReturn() {
		return "store/createStoreReturn";
	}

}
