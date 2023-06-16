package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.dao.vendor.VendorDao;
import main.models.vendorModels.Vendor;

@Controller
public class AdminControllers {

	@Autowired
	VendorDao vendorDao;

	// Warehouse and Home page
	@RequestMapping(value = "/adminHome")
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
	@GetMapping("/deleteVendor")
	public String deleteVendor(Model model) {
		List<Vendor> vendors = vendorDao.getAllVendors();
		model.addAttribute("vendors", vendors);
		System.out.println("\n\n\nThis");
		return "admin/deleteVendor";
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

	// delete user
	@GetMapping("/deleteUser")
	public String deleteUser() {
		return "admin/deleteUser";
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
