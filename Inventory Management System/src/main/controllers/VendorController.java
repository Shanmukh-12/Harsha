package main.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.vendor.VendorsDAO;
import main.models.vendorModels.entities.Vendor;
import main.models.vendorModels.inputModels.VendorId;
import main.models.vendorModels.inputModels.VendorInput;
import main.models.vendorModels.outputModels.VendorOutput;

@Controller
public class VendorController {

	@Autowired
	public VendorsDAO vendorDAO;
	@Autowired
	ModelMapper mapper;

	// Saving Vendor Data
	@PostMapping("/saveVendor")
	public String saveVendor(@RequestBody String data, Model model) {
		VendorInput vendorInput = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			vendorInput = objectMapper.readValue(data, VendorInput.class);
			System.out.println(vendorInput);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vendor v = mapper.map(vendorInput, Vendor.class);
		vendorDAO.saveVendor(v);
		return "admin/adminHome";

	}

	// Getting Vendor Id and Name for Dropdown
	@PostMapping("/getVendor")
	public @ResponseBody List<VendorOutput> deleteVendor(Model model) {

		List<Vendor> vendors = vendorDAO.getAllVendors();

		List<VendorOutput> vendorOutputs = vendors.stream().map(vendor -> mapper.map(vendor, VendorOutput.class))
				.collect(Collectors.toList());

		return vendorOutputs;
	}

	// Updating Vendor Information
	@RequestMapping(value = "/getVendorData", method = RequestMethod.POST)
	@ResponseBody
	public Vendor getVendorData(@RequestBody VendorId v) {
		Vendor vendor = vendorDAO.getVendorData(v);
		return vendor;
	}

	@PostMapping("/updateVendor")
	@ResponseBody
	public String updateVendor(@RequestBody Vendor vendor) {
		vendorDAO.updateVendor(vendor);
		return "admin/success";
	}

	// Soft Deleting the Vendor
	@PostMapping("/delete")
	@ResponseBody
	public String deleteVendor(@RequestBody VendorId vendor) {
		vendorDAO.deleteVendor(vendor);
		return "admin/success";
	}

	// Getting Vendor Information
	@GetMapping("/showVendors")
	public @ResponseBody List<Vendor> showVendors() {
		return vendorDAO.getAllVendors();
	}

}
