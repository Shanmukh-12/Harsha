package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.products.ProductCategoryDAO;
import main.dao.products.ProductsDao;
import main.models.productModels.entities.ProductsCategory;
import main.models.productModels.inputModels.CategoryRequest;
import main.models.productModels.outputModels.ProductStockData;

@Controller
public class ProductControllers {

	@Autowired
	ProductsDao productsDAO;
	@Autowired
	ProductCategoryDAO productCategoryDAO;

	@PostMapping("/getProductCategories")
	public @ResponseBody List<ProductsCategory> getProductCategories(Model model) {
		List<ProductsCategory> productCategory = productCategoryDAO.getProductCategories();
		return productCategory;
	}

	 @PostMapping("/getProducts")
	    public @ResponseBody List<ProductStockData> getProducts(String categoryId, Model model) {
		 ObjectMapper objectMapper = new ObjectMapper();
		 CategoryRequest categoryRequest = null;
		try {
			categoryRequest = objectMapper.readValue(categoryId,CategoryRequest.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(categoryRequest.getCategoryId());

	        int selectedCategoryId = categoryRequest.getCategoryId();
	        List<ProductStockData> products = productsDAO.getProductsByCategory(selectedCategoryId);
	        return products;
	    }

}
