package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.storeIndents.StoreIndentsDao;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.StoreIndentsInputList;
import main.models.storeModels.outputmodels.StoreIndentProducts;

@Controller
public class StoreIndentController {

	@Autowired
	StoreIndentsDao storeIndentsDao;

	@Autowired
	ModelMapper modelMapper;
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/getStoreIndentsList")
	public @ResponseBody List<StoreIndentData> getStoreIndentList(Model m) {
		System.out.println("indents");
		List<StoreIndentData> sl = storeIndentsDao.getStoreIndentsList();
		for (StoreIndentData s : sl)
			System.out.println(s);
		return sl;
	}

	@PostMapping("/getStoreIndentProductsList")
	public String getStoreIndentProductsList(String indentId, Model m) {
		System.out.println(indentId);
		IndentId indentid = null;
		try {
			indentid = objectMapper.readValue(indentId, IndentId.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<StoreIndentProducts> storeIndentProducts = storeIndentsDao.getStoreIndentsProductsList(indentid);

		// List<StoreIndentProducts> storeIndentProducts = new ArrayList();
		// for (StoreIndentProductsList s : storeIndentProductsList)
		// storeIndentProducts.add(modelMapper.map(s, StoreIndentProducts.class));
		//
		m.addAttribute("productsList", storeIndentProducts);

		for (StoreIndentProducts s : storeIndentProducts)
			System.out.println(s);

		return "store/storeIndentProducts";
	}

	@PostMapping("/newCreateStoreIndent")
	public String createStoreIndent(String jsonData, Model m) {
		// System.out.println("Data is " + jsonData);
		StoreIndentsInputList storeIndentsInputList = null;

		try {
			storeIndentsInputList = objectMapper.readValue(jsonData, StoreIndentsInputList.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StoreIndentsList storeIndentsList = modelMapper.map(storeIndentsInputList, StoreIndentsList.class);

		// System.out.println(storeIndentsList);
		// m.addAttribute("data", storeIndentsList);

		storeIndentsDao.saveStoreIndent(storeIndentsList);

		return "store/createStoreIndent";
	}

}
