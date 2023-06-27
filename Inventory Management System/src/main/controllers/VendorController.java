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
import main.models.vendorModels.inputModels.VendorStatus;
import main.models.vendorModels.outputModels.VendorOutput;

@Controller
public class VendorController {

    @Autowired
    public VendorsDAO vendorDAO; // Autowired dependency injection for VendorsDAO
    @Autowired
    ModelMapper mapper; // Autowired dependency injection for ModelMapper

    // Saving Vendor Data
    @PostMapping("/saveVendor")
    public String saveVendor(@RequestBody String data, Model model) {
        VendorInput vendorInput = null;
        ObjectMapper objectMapper = new ObjectMapper(); // Object mapper for JSON serialization/deserialization
        try {
            vendorInput = objectMapper.readValue(data, VendorInput.class); // Deserialize the JSON string to VendorInput object
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Vendor vendor = mapper.map(vendorInput, Vendor.class); // Map VendorInput to Vendor using ModelMapper
        vendorDAO.saveVendor(vendor); // Save the Vendor object using vendorDAO
        return "admin/adminHome";
    }

    // Getting Vendor Id and Name for Dropdown
    @PostMapping("/getVendor")
    public @ResponseBody List<VendorOutput> deleteVendor(Model model) {
        List<Vendor> vendors = vendorDAO.getAllVendors(); // Retrieve all vendors from the database using vendorDAO

        // Map each Vendor object to VendorOutput using ModelMapper and collect into a list
        List<VendorOutput> vendorOutputs = vendors.stream()
                .map(vendor -> mapper.map(vendor, VendorOutput.class))
                .collect(Collectors.toList());

        return vendorOutputs; // Return the list of VendorOutput objects as a JSON response
    }

    // Updating Vendor Information
    @RequestMapping(value = "/getVendorData", method = RequestMethod.POST)
    @ResponseBody
    public Vendor getVendorData(@RequestBody VendorId vendorId) {
        Vendor vendor = vendorDAO.getVendorData(vendorId); // Retrieve vendor data based on the given VendorId using vendorDAO
        return vendor; // Return the Vendor object as a JSON response
    }

    @PostMapping("/updateVendor")
    @ResponseBody
    public String updateVendor(@RequestBody Vendor vendor) {
        vendorDAO.updateVendor(vendor); // Update the vendor information in the database using vendorDAO
        return "admin/success";
    }

    // Soft Deleting the Vendor
    @PostMapping("/delete")
    @ResponseBody
    public String deleteVendor(@RequestBody VendorId vendor) {
        vendorDAO.deleteVendor(vendor); // Soft delete the vendor based on the given VendorId using vendorDAO
        return "admin/success";
    }

    // Getting Vendor Information
    @GetMapping("/showVendors")
    public @ResponseBody List<Vendor> showVendors() {
        return vendorDAO.getAllVendors(); // Retrieve all vendors from the database using vendorDAO and return as a JSON response
    }

    /* filter */
    @PostMapping("/vendorStatus")
    public @ResponseBody List<Vendor> vendorStatus(VendorStatus vendorStatus) {
        List<Vendor> vendor = vendorDAO.getVendorStatus(vendorStatus); // Retrieve active vendors based on the given VendorStatus using vendorDAO
        return vendor; // Return the list of Vendor objects as a JSON response
    }
}
