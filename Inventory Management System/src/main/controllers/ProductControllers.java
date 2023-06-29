
package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import main.dal.products.ProductsDAOException;
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
		List<ProductsCategory> productCategory = null;
		try {
			productCategory = productCategoryDAO.getProductCategories();
		} catch (ProductsDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productCategory;
	}
	
	
	@PostMapping("/getProductsIds")
	public @ResponseBody List<ProductStockData> getProductsIDs(@ModelAttribute("categoryInputModel") CategoryRequest categoryInputModel, Model model) throws Exception{

	        // Call the appropriate method in the service layer
	        List<ProductStockData> products = productsDAO.getProductsByCategory(categoryInputModel);
	        // Return a successful response with the retrieved products
	        return products;

	}


//It return products Data by taking the categoryId as an input
	@PostMapping("/getProductStockData")
	public @ResponseBody List<ProductStockData> getProducts(
			@ModelAttribute("categoryInputModel") CategoryRequest categoryInputModel, Model model) throws Exception{


	 // listing the Products Details by category by calling productsDao Interface method.
		List<ProductStockData> products=null;

			
			products = productsDAO.getProductsByCategory(categoryInputModel);

		return products;
	}
	
//It return product BatchNos by taking the productId as an input
	@PostMapping("/getProductBatchNos")
	public @ResponseBody List<ProductStockData> getProductBatchesNos(
			@ModelAttribute("productsProductIdInputModel") ProductsProductIdInputModel productsProductIdInputModel,
			Model model)  throws Exception {

		int selectedProductId = productsProductIdInputModel.getProductId();
		
		// listing the Product BatchNos by category by calling productsDao Interface method.
		List<ProductStockData> batchesNos =null;
		
		try {
			
			batchesNos = productsDAO.getProductsByProductId(selectedProductId);
			
		} catch (ProductsDAOException e) {
			
			model.addAttribute("error",e.getMessage());
		}
		return batchesNos;
	}

//It return products Data by taking the categoryId as an input
	@PostMapping("/getProductQuantityOrPrice")
	public @ResponseBody ProductStockData getProductQuantityOrPrice(
			@ModelAttribute("productsProductIdandBatchNoInputModel") ProductsProductIdandBatchNoInputModel productsProductIdandBatchNoInputModel,
			Model model)  throws Exception{

		// listing the Product quantity  and price by category by calling productsDao Interface method.
		ProductStockData quantityOrPrice=null;
		
		try {
			
			quantityOrPrice = productsDAO.getQuantityandpriceByProductIdOrBatchNo(productsProductIdandBatchNoInputModel);
			
		} catch (ProductsDAOException e) {
			
			model.addAttribute("error",e.getMessage());
		}
		return quantityOrPrice;
	}

//It returns List of Re-order Products 
	@PostMapping("/getReOrderProductsData")
	public @ResponseBody List<ProductsReOrderList> getReOrderLevelProducts(Model model)  throws Exception {
		
		// listing the Re-order Products List  category by calling productsDao Interface method.
		List<ProductsReOrderList> reOrderlist=null;
		try {
			
			reOrderlist = productsDAO.getReOrderLevelProducts();
			
		} catch (ProductsDAOException e) {
			
			model.addAttribute("error",e.getMessage());
			
		}

		return reOrderlist;
	}
	
//It persist the category created by procurement team
	@PostMapping("/createCategory")
	@ResponseBody
	public void saveCategory(@ModelAttribute("categoryInputModel") CategoryRequest2 categoryInputModel)  throws Exception {
		ModelMapper modelMapper = new ModelMapper();

		//Mapping the input model to the Entity Model by using Model Mapping.
		ProductsCategory productsCategory = modelMapper.map(categoryInputModel, ProductsCategory.class);
		productsDAO.saveCategory(productsCategory);

	}
	
	
//It persist the HSN created by the procurement team
	@PostMapping("/createHSN")
	
	public void saveHSN(@ModelAttribute("hsnInputModel") HSNInputModel hsnInputModel)  throws Exception{

		ModelMapper modelMapper = new ModelMapper();
			
		//Mapping the input model to the Entity Model by using Model Mapping.
		HSNEntityModel hsnEntityModel = modelMapper.map(hsnInputModel, HSNEntityModel.class);
		
		productsDAO.saveHSN(hsnEntityModel);



	}

}