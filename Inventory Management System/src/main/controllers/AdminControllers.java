package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.users.StoreUsersDAO;
import main.dao.users.WarehouseUsersDAO;
import main.dao.vendor.VendorsDAO;
import main.models.storeModels.entities.Store;
import main.models.userModels.entities.User;
import main.models.vendorModels.Vendor;

@Controller
public class AdminControllers {

	@Autowired
	WarehouseUsersDAO userDAO;
	@Autowired
	VendorsDAO vendorDAO;
	@Autowired 
	StoreUsersDAO storeDAO;

	// Warehouse and Home page
	@RequestMapping(value = { "/adminHome" })
	public String getHome() {
		return "admin/adminHome";
	}

	// StockData
	@RequestMapping(value = "/warehouseStock")
	public String getwarehouseStock() {
		return "admin/warehouseStock";
	}

	// StockData
	@RequestMapping(value = "/storeStock")
	public String getStoreStock() {
		return "admin/storeStock";
	}

	// Manipulating users

	// Adding vendor
	@GetMapping("/addVendor")
	public String addVendor() {
		return "admin/addVendor";
	}

	// Update vendor
	@GetMapping("/updateVendor")
	public String updateVendor() {
		return "admin/updateVendor";
	}

	// Delete vendor
	// @GetMapping("/deleteVendor")
	// public String deleteVendor() {
	// return "admin/deleteVendor";
	// }
	@GetMapping("/deleteVendor")
	public String deleteVendor(Model model) {
		List<Vendor> vendors = vendorDAO.getAllVendors();
		model.addAttribute("vendors", vendors);
		System.out.println("\n\n\nThis");
		return "admin/deleteVendor";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(Model model) {
		List<User> users = userDAO.getAllActiveUsers();
		model.addAttribute("users", users);
		System.out.println("\n\n\nThis");
		return "admin/deleteUser";
	}

	@PostMapping("/deleteUserData")
	@ResponseBody
	public String deleteUser(@RequestBody User user) {
		// System.out.println(vendor);
		// System.out.println(vendor.getVendorName());
		userDAO.deleteUser(user);
		return "admin/success";
	}

	@PostMapping("/delete")
	@ResponseBody
	public String deleteVendor(@RequestBody Vendor vendor) {
		// System.out.println(vendor);
		// System.out.println(vendor.getVendorName());
		vendorDAO.deleteVendor(vendor);
		return "admin/success";
	}
	@GetMapping("/deleteStores")
	public String deleteStore(Model model) {
		List<Store> stores = storeDAO.getAllActiveStores();
		model.addAttribute("stores", stores);
		System.out.println("\n\n\nThis");
		return "admin/deleteStore";
	}
	@PostMapping("/deleteStore")
	@ResponseBody
	public String deleteStore(@RequestBody Store store) {
		// System.out.println(vendor);
		// System.out.println(vendor.getVendorName());
		storeDAO.deleteStore(store);
		return "admin/success";
	}

	// add user
	@GetMapping("/addUser")
	public String addUser() {
		return "admin/addUser";
	}

	// update user
	@GetMapping("/updateUser")
	public String updateUser() {
		return "admin/updateUser";
	}

	@GetMapping("/addStore")
	public String addStore() {
		return "admin/addStore";
	}

	// Product Categories
	@GetMapping("/productCategories")
	public String getProductCategories() {
		return "admin/productCategories";
	}

	// Reports
	@GetMapping("/reports")
	public String getReports() {
		return "admin/reports";
	}

}
