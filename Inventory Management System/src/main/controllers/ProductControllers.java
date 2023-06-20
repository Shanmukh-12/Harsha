package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.products.ProductCategoryDAO;
import main.dao.products.ProductsDAO;
import main.models.productModels.entities.HSNEntityModel;
import main.models.productModels.entities.ProductsCategory;
import main.models.productModels.inputModels.CategoryRequest;
import main.models.productModels.inputModels.HSNInputModel;
import main.models.productModels.inputModels.ProductsProductIdInputModel;
import main.models.productModels.inputModels.ProductsProductIdandBatchNoInputModel;
import main.models.productModels.outputModels.ProductStockData;

@Controller
public class ProductControllers {

	@Autowired
	ProductsDAO productsDAO;
	@Autowired
	ProductCategoryDAO productCategoryDAO;

	@GetMapping("/getProductCategories")
	public @ResponseBody List<ProductsCategory> getProductCategories(
			@ModelAttribute("categoryInputModel") CategoryRequest categoryInputModel, Model model) {
		List<ProductsCategory> productCategory = productCategoryDAO.getProductCategories();
		System.out.println(productCategory);
		return productCategory;
	}

	@PostMapping("/getProductStockData")
	public @ResponseBody List<ProductStockData> getProducts(
			@ModelAttribute("categoryInputModel") CategoryRequest categoryInputModel, Model model) {

		System.out.println(categoryInputModel.getCategoryId());

		System.out.println("hello");

		int selectedCategoryId = categoryInputModel.getCategoryId();

		List<ProductStockData> products = productsDAO.getProductsByCategory(selectedCategoryId);
		System.out.println(products);
		return products;
	}

	@PostMapping("/getProductBatchNos")
	public @ResponseBody List<ProductStockData> getProductBatchesNos(
			@ModelAttribute("productsProductIdInputModel") ProductsProductIdInputModel productsProductIdInputModel,
			Model model) {

		System.out.println(productsProductIdInputModel.getProductId());

		int selectedProductId = productsProductIdInputModel.getProductId();
		List<ProductStockData> batchesNos = productsDAO.getProductsByProductId(selectedProductId);
		System.out.println(batchesNos);
		return batchesNos;
	}

	@PostMapping("/getProductQuantityOrPrice")
	public @ResponseBody ProductStockData getProductQuantityOrPrice(
			@ModelAttribute("productsProductIdandBatchNoInputModel") ProductsProductIdandBatchNoInputModel productsProductIdandBatchNoInputModel,
			Model model) {

		int selectedProductId = productsProductIdandBatchNoInputModel.getProductId();
		int selectedBatchNo = productsProductIdandBatchNoInputModel.getBatchNo();
		ProductStockData quantity = productsDAO.getQuantityandpriceByProductIdOrBatchNo(selectedProductId,
				selectedBatchNo);
		return quantity;
	}

	@PostMapping("/getReOrderLevelProducts")
	public @ResponseBody List<ProductStockData> getReOrderLevelProducts(
			@ModelAttribute("productsProductIdandBatchNoInputModel") ProductsProductIdandBatchNoInputModel productsProductIdandBatchNoInputModel,
			Model model) {

		List<ProductStockData> list = productsDAO.getReOrderLevelProducts();
		return list;
	}

	@PostMapping("/createCategory")
	public String saveCategory(@ModelAttribute("categoryInputModel") CategoryRequest categoryInputModel) {

		ModelMapper modelMapper = new ModelMapper();
		ProductsCategory productsCategory = modelMapper.map(categoryInputModel, ProductsCategory.class);
		productsDAO.saveCategory(productsCategory);
		return "redirect:/showCategories";
	}

	@PostMapping("/createHSN")
	public String saveHSN(@ModelAttribute("hsnInputModel") HSNInputModel hsnInputModel) {

		ModelMapper modelMapper = new ModelMapper();
		HSNEntityModel hsnEntityModel = modelMapper.map(hsnInputModel, HSNEntityModel.class);
		productsDAO.saveHSN(hsnEntityModel);
		return "redirect:/showHSNs";
	}

}
