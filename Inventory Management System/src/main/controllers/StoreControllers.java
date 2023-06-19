package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.users.StoreUsersDAO;
import main.dto.users.StoreDto;
import main.models.storeModels.entities.Store;


@Controller
public class StoreControllers {
	@Autowired
	public StoreUsersDAO storeDAO;

	@GetMapping("/createStore")
	public String showCreateForm(Model model) {
		model.addAttribute("store", new Store());
		return "admin/addStore";
	}

	@PostMapping("/saveStore")
	public ResponseEntity<String> saveStore(@RequestBody Store store) {
	    try {
	        System.out.println(store);
	        storeDAO.saveStore(store);
	        return ResponseEntity.ok("Store added successfully.");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the store.");
	    }
	}


	@GetMapping("/showStores")
	public String createStore(Model m) {
		m.addAttribute("stores", storeDAO.getAllStores());
		System.out.println("Store created");
		return "admin/storeData";
	}

	@RequestMapping(value = "/getStoreData", method = RequestMethod.POST)
	@ResponseBody
	public Store getStoreData(@RequestBody StoreDto v) {
		Store store = storeDAO.getStoreData(v);
		return store;
	}

	@GetMapping("/storeHome")
	public String getStore() {
		return "store/storeHome";
	}

	@PostMapping("/getStoreIndents")
	public String listStoreIndent() {
		return "store/storeIndents";
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
