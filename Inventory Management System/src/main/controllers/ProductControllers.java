
package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.products.ProductCategoryDAO;
import main.dao.products.ProductsDAO;
import main.models.productModels.entities.HSNEntityModel;
import main.models.productModels.entities.ProductsCategory;
import main.models.productModels.inputModels.CategoryRequest;
import main.models.productModels.inputModels.CategoryRequest2;
import main.models.productModels.inputModels.HSNInputModel;
import main.models.productModels.inputModels.ProductsProductIdInputModel;
import main.models.productModels.inputModels.ProductsProductIdandBatchNoInputModel;
import main.models.productModels.outputModels.ProductIdListOutput;
import main.models.productModels.outputModels.ProductStockData;
import main.models.productModels.outputModels.ProductsReOrderList;

@Controller
public class ProductControllers {

	@Autowired
	ProductsDAO productsDAO;
	@Autowired
	ProductCategoryDAO productCategoryDAO;

	
//It returns the Product Categories
	@PostMapping("/getProductCategories")
	public @ResponseBody List<ProductsCategory> getProductCategories(Model model) {
		
		 // listing the CategoryIds by category by calling productsDao Interface method.
		List<ProductsCategory> productCategory = productCategoryDAO.getProductCategories();
		return productCategory;
	}
	
	
//It return productsIds and productNames by taking the categoryId as an input
	@PostMapping("/getProductsIds")
    public @ResponseBody List<ProductIdListOutput> getProducts(String categoryId, Model model) {
		
	 ObjectMapper objectMapper = new ObjectMapper();
	 CategoryRequest categoryRequest = null;
	try {
		//Reading the input JSon into the Input CategoryRequest Class
		categoryRequest = objectMapper.readValue(categoryId,CategoryRequest.class);
	} catch (Exception e) {
		e.printStackTrace();
	} 

        int selectedCategoryId = categoryRequest.getCategoryId();
        // listing the ProductsIds by category by calling productsDao Interface method.
        List<ProductIdListOutput> products = productsDAO.getProductsByCategoryId(selectedCategoryId);
        return products;
    }
//It return products Data by taking the categoryId as an input
	@PostMapping("/getProductStockData")
	public @ResponseBody List<ProductStockData> getProducts(
			@ModelAttribute("categoryInputModel") CategoryRequest categoryInputModel, Model model) {


		int selectedCategoryId = categoryInputModel.getCategoryId();
	 // listing the Products Details by category by calling productsDao Interface method.
		List<ProductStockData> products = productsDAO.getProductsByCategory(selectedCategoryId);
		System.out.println(products);
		return products;
	}
	
//It return product BatchNos by taking the productId as an input
	@PostMapping("/getProductBatchNos")
	public @ResponseBody List<ProductStockData> getProductBatchesNos(
			@ModelAttribute("productsProductIdInputModel") ProductsProductIdInputModel productsProductIdInputModel,
			Model model) {

		int selectedProductId = productsProductIdInputModel.getProductId();
		
		 // listing the Product BatchNos by category by calling productsDao Interface method.
		List<ProductStockData> batchesNos = productsDAO.getProductsByProductId(selectedProductId);
		System.out.println(batchesNos);
		return batchesNos;
	}

//It return products Data by taking the categoryId as an input
	@PostMapping("/getProductQuantityOrPrice")
	public @ResponseBody ProductStockData getProductQuantityOrPrice(
			@ModelAttribute("productsProductIdandBatchNoInputModel") ProductsProductIdandBatchNoInputModel productsProductIdandBatchNoInputModel,
			Model model) {

		int selectedProductId = productsProductIdandBatchNoInputModel.getProductId();
		int selectedBatchNo = productsProductIdandBatchNoInputModel.getBatchNo();
		// listing the Product quantity  and price by category by calling productsDao Interface method.
		ProductStockData quantityOrPrice = productsDAO.getQuantityandpriceByProductIdOrBatchNo(selectedProductId,
				selectedBatchNo);
		return quantityOrPrice;
	}

//It returns List of Re-order Products 
	@PostMapping("/getReOrderProductsData")
	public @ResponseBody List<ProductsReOrderList> getReOrderLevelProducts(Model model) {
		
		// listing the Re-order Products List  category by calling productsDao Interface method.
		List<ProductsReOrderList> reOrderlist = productsDAO.getReOrderLevelProducts();

		return reOrderlist;
	}
	
//It persist the category created by procurement team
	@PostMapping("/createCategory")
	@ResponseBody
	public String saveCategory(@ModelAttribute("categoryInputModel") CategoryRequest2 categoryInputModel) {
		ModelMapper modelMapper = new ModelMapper();
		try {
		//Mapping the input model to the Entity Model by using Model Mapping.
		ProductsCategory productsCategory = modelMapper.map(categoryInputModel, ProductsCategory.class);
		productsDAO.saveCategory(productsCategory);
		
		}
		catch(Exception e) {
			return "Can't created the New Category";
		}
		return  "Succesfully created the New Category";
	}
	
	
//It persist the HSN created by the procurement team
	@PostMapping("/createHSN")
	
	public @ResponseBody String saveHSN(@ModelAttribute("hsnInputModel") HSNInputModel hsnInputModel) {

		ModelMapper modelMapper = new ModelMapper();
		try {
			
		//Mapping the input model to the Entity Model by using Model Mapping.
		HSNEntityModel hsnEntityModel = modelMapper.map(hsnInputModel, HSNEntityModel.class);
		
		productsDAO.saveHSN(hsnEntityModel);
		
		
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Can't Created the new HSN";
		}
		return "Succesfully Created the new HSN";

	}

}