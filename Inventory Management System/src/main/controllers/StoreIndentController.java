package main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.storeIndents.StoreIndentsDao;
import main.models.storeIndentModels.outputmodels.StoreIndentDataOutput;
import main.models.storeIndentModels.outputmodels.StoreIndentProducts;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.StoreIndentsInputList;
import main.models.storeModels.outputmodels.StoreIds;

@Controller
public class StoreIndentController {

	@Autowired
	StoreIndentsDao storeIndentsDao;

	@Autowired
	ModelMapper modelMapper;
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/getStoreIndentsList")
	public @ResponseBody List<StoreIndentDataOutput> getStoreIndentList(Model m) {
		List<StoreIndentData> sl = storeIndentsDao.getStoreIndentsList();
		List<StoreIndentDataOutput> res = new ArrayList<>();
		for (StoreIndentData s : sl) {
			res.add(modelMapper.map(s, StoreIndentDataOutput.class));
		}
		return res;
	}

	@PostMapping("/getStoreIndentProductsListData")
	public @ResponseBody List<StoreIndentProducts> getStoreIndentProductsListData(String indentId, Model m) {
		IndentId indentid = null;
		try {
			indentid = objectMapper.readValue(indentId, IndentId.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<StoreIndentProducts> storeIndentProducts = storeIndentsDao.getStoreIndentsProductsList(indentid);

		return storeIndentProducts;
	}

	@PostMapping("/getStoreIndentProductsList")
	public String getStoreIndentProductsList(String indentId, Model m) {
		IndentId indentid = null;
		try {
			indentid = objectMapper.readValue(indentId, IndentId.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<StoreIndentProducts> storeIndentProducts = storeIndentsDao.getStoreIndentsProductsList(indentid);

		m.addAttribute("productsList", storeIndentProducts);

		return "store/storeIndentProducts";
	}

	@PostMapping("/newCreateStoreIndent")
	public String createStoreIndent(String jsonData, Model m) {

		StoreIndentsInputList storeIndentsInputList = null;

		try {
			storeIndentsInputList = objectMapper.readValue(jsonData, StoreIndentsInputList.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StoreIndentsList storeIndentsList = modelMapper.map(storeIndentsInputList, StoreIndentsList.class);

		storeIndentsDao.saveStoreIndent(storeIndentsList);

		return "store/createStoreIndent";
	}

	@PostMapping("/getIndentsListByStoreID")
	public @ResponseBody List<StoreIndentData> getIndentsList(@ModelAttribute("storeIds") StoreIds storeIds,
			Model model) {

		int selectedStoreId = storeIds.getStoreId();

		List<StoreIndentData> indents = storeIndentsDao.getIndentsByStoreID(selectedStoreId);

		return indents;
	}

}
