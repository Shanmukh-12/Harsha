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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.users.StoreUsersDAO;
import main.models.storeModels.entities.Store;
import main.models.storeModels.inputmodels.StoreId;
import main.models.storeModels.inputmodels.StoreInfo;
import main.models.storeModels.inputmodels.StoreStatus;
import main.models.storeModels.outputmodels.StoreIds;

@Controller
public class StoreController {

    @Autowired
    public StoreUsersDAO storeDAO; // Autowired dependency injection for StoreUsersDAO

    @Autowired
    ModelMapper mapper; // Autowired dependency injection for ModelMapper

    // Saving Store Information
    @PostMapping("/saveStore")
    public String saveStore(@RequestBody String data, Model model) {
        StoreInfo storeInfo = null;
        ObjectMapper objectMapper = new ObjectMapper(); // Object mapper for JSON serialization/deserialization
        try {
            storeInfo = objectMapper.readValue(data, StoreInfo.class); // Deserialize the JSON string to StoreInfo object
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Store store = mapper.map(storeInfo, Store.class); // Map StoreInfo to Store using ModelMapper
        storeDAO.saveStore(store); // Save the Store object using storeDAO
        return "admin/adminHome";
    }

    // Getting Store Id and Name for Dropdown
    @PostMapping("/getStore")
    public @ResponseBody List<StoreIds> deleteStore(Model model) {
        List<Store> stores = storeDAO.getAllActiveStores(); // Retrieve all active stores from the database using storeDAO

        // Map each Store object to StoreIds using ModelMapper and collect into a list
        List<StoreIds> storeOutputs = stores.stream()
                .map(store -> mapper.map(store, StoreIds.class))
                .collect(Collectors.toList());

        return storeOutputs; // Return the list of StoreIds objects as a JSON response
    }

    // Delete store i.e.making status Inactive
    @PostMapping("/deleteStoreData")
    public String deleteStore(@RequestBody StoreId store) {
        storeDAO.deleteStore(store); // Delete the store based on the given StoreId using storeDAO
        return "admin/success";
    }

    // Getting Store Information
    @GetMapping("/showStores")
    public @ResponseBody List<Store> showStores() {
        return storeDAO.getAllStores(); // Retrieve all stores from the database using storeDAO and return as a JSON response
    }
  //Getting store User Status for Filtering the store user Information
    @PostMapping("/storeStatus")
    public @ResponseBody List<Store> vendorStatus(StoreStatus storeStatus) {
        List<Store> storesList = storeDAO.getStoreStatus(storeStatus); // Retrieve active stores based on the given StoreStatus using storeDAO
        return storesList; // Return the list of Store objects as a JSON response
    }
//Navigating to Store Dashboard 
    @GetMapping("/storeHome")
    public String getStoreDashboard() {
        return "store/storeHome";
    }
//navigating to list store Indents Page
    @PostMapping("/getStoreIndents")
    public String listStoreIndent() {
        return "store/storeIndents";
    }
//Navigating to creating store Indents Page
    @PostMapping("/createStoreIndent")
    public String createStoreIndent() {
        return "store/createStoreIndent";
    }
//Navigating to list store Returns Page
    @PostMapping("/getStoreReturns")
    public String listStoreReturns() {
        return "store/storeReturns";
    }
  //Navigating to creating store Returns page 
    @PostMapping("/createStoreReturn")
    public String createStoreReturn() {
        return "store/createStoreReturn";
    }

}
