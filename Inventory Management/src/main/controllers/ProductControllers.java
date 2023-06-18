package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.products.ProductsCategoryDAO;
import main.dao.products.ProductsDAO;
import main.models.adminModels.Products;
import main.models.adminModels.ProductsCategory;
import main.models.productsModels.CategoryInputModel;

@Controller
public class ProductControllers {

	@Autowired
	ProductsDAO productsDAO;
	@Autowired
	ProductsCategoryDAO productCategoryDAO;

	@PostMapping("/getProductCategories")
	public @ResponseBody List<ProductsCategory> getProductCategories(@ModelAttribute("categoryInputModel") CategoryInputModel categoryInputModel, Model model) {
		List<ProductsCategory> productCategory = productCategoryDAO.getProductCategories();
		System.out.println(productCategory);
		return productCategory;
	}

	@PostMapping("/getProducts")
	public List<Products> getProductsList(int id, Model model) {
		System.out.println("Inside");
		System.out.println(id);
		List<Products> products = productsDAO.getProducts();
		return products;
	}

}
