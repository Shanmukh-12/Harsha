package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.dao.users.StoreUsersDAO;
import main.dao.users.WarehouseUsersDAO;
import main.dao.vendor.VendorsDAO;


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

	// WareHouseStockData
	@RequestMapping(value = "/warehouseStock")
	public String getwarehouseStock() {
		return "admin/warehouseStock";
	}

	// StoreStockData
	@RequestMapping(value = "/storeStock")
	public String getStoreStock() {
		return "admin/storeStock";
	}
	/* Manipulating Users */
	/* Vendor */

	// Redirect to Add vendor page
	@GetMapping("/addVendor")
	public String addVendor() {
		return "admin/addVendor";
	}

	// Redirect to Update vendor page
	@GetMapping("/updateVendor")
	public String updateVendor() {
		return "admin/updateVendor";
	}

	// Redirect to Get Vendors Page
	@GetMapping("/displayVendors")
	public String getVendors() {
		return "admin/vendorData";
	}

	// Redirect to Delete Vendors Page
	@GetMapping("/deleteVendorPage")
	public String deleteVendor() {
		return "admin/deleteVendor";
	}

	/* Store */

	// Redirect to Add Stores Page
	@GetMapping("/addStorePage")
	public String addStore() {
		return "admin/addStore";
	}

	// Redirect to Delete Stores Page
	@GetMapping("/deleteStorePage")
	public String deleteStore() {
		return "admin/deleteStore";
	}

	// Redirect to Display Stores Page
	@GetMapping("/displayStorePage")
	public String displayStore() {
		return "admin/storeData";
	}

	/* User */

	// Add user Page
	@GetMapping("/addUserPage")
	public String addUser() {
		return "admin/addUser";
	}

	// Delete User Page
	@GetMapping("/deleteUserPage")
	public String deleteUser() {
		return "admin/deleteUser";
	}

	// Display User Information Page
	@GetMapping("/displayUserPage")
	public String displayUser() {
		return "admin/userData";
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
