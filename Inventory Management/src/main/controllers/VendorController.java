package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.vendor.VendorDao;
import main.dto.vendor.VendorDto;
import main.models.vendorModels.Vendor;

@Controller
public class VendorController {

	@Autowired
	public VendorDao vendorDao;

	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("vendor", new Vendor());
		return "admin/addVendor";
	}

	@PostMapping("/saveVendor")
	public String saveVendor(@ModelAttribute Vendor vendor, Model model) {
		vendorDao.saveVendor(vendor);
		model.addAttribute("Inserted", "vendor Inserted Successfully");
		return "admin/success";
	}

	@GetMapping("/showVendors")
	public String createVendor(Model m) {
		m.addAttribute("vendors", vendorDao.getAllVendors());
		System.out.println("vendor created");
		return "admin/vendorData";
	}

	@RequestMapping(value = "/getVendorData", method = RequestMethod.POST)
	@ResponseBody
	public Vendor getVendorData(@RequestBody VendorDto v) {
		Vendor vendor = vendorDao.getVendorData(v);
		return vendor;
	}

	@GetMapping("/update")
	public String updateVendor(Model model) {
		List<Vendor> vendors = vendorDao.getAllVendors();
		model.addAttribute("vendors", vendors);
		return "admin/updateVendor";
	}

	@PostMapping("/updateVendor")
	@ResponseBody
	public String updateVendor(@RequestBody Vendor vendor) {
		// System.out.println(vendor);
		// System.out.println(vendor.getVendorName());
		vendorDao.updateVendor(vendor);
		return "admin/success";
	}

	@PostMapping("/delete")
	@ResponseBody
	public String deleteVendor(@RequestBody Vendor vendor) {
		// System.out.println(vendor);
		// System.out.println(vendor.getVendorName());
		vendorDao.deleteVendor(vendor);
		return "admin/success";
	}

}
