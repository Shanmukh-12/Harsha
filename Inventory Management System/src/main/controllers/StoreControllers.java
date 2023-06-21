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
import main.models.storeModels.outputmodels.StoreIds;

@Controller
public class StoreControllers {
	@Autowired
	public StoreUsersDAO storeDAO;
	@Autowired
	ModelMapper mapper;

	// Saving Store Information
	@PostMapping("/saveStore")
	public String saveStore(@RequestBody String data, Model model) {
		StoreInfo storeInfo = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			storeInfo = objectMapper.readValue(data, StoreInfo.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Store s = mapper.map(storeInfo, Store.class);
		storeDAO.saveStore(s);
		return "admin/adminHome";

	}

	// Getting Store Id and Name for Drop down
	@PostMapping("/getStore")
	public @ResponseBody List<StoreIds> deleteStore(Model model) {

		List<Store> stores = storeDAO.getAllActiveStores();

		List<StoreIds> storeOutputs = stores.stream().map(store -> mapper.map(store, StoreIds.class))
				.collect(Collectors.toList());

		return storeOutputs;
	}

	// Delete store
	@PostMapping("/deleteStoreData")
	public String deleteStore(@RequestBody StoreId store) {
		storeDAO.deleteStore(store);
		return "admin/success";
	}

	// Getting Store Information
	@GetMapping("/showStores")
	public @ResponseBody List<Store> showStores() {
		return storeDAO.getAllStores();
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
