
package main.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
//It return productsIds and productNames by taking the categoryId as an input
	@PostMapping("/getProductsIds")
    public @ResponseBody List<ProductIdListOutput> getProductsIDs(@ModelAttribute("categoryInputModel") CategoryRequest categoryInputModel, Model model) {

        // listing the ProductsIds by category by calling productsDao Interface method.
        List<ProductIdListOutput> products=null;
        
		try {
			
			products = productsDAO.getProductsByCategoryId(categoryInputModel);
			
		} 
		catch (ProductsDAOException e) {
			
			model.addAttribute("error",e.getMessage());
		}
		
        return products;
    }
//It return products Data by taking the categoryId as an input
	@PostMapping("/getProductStockData")
	public @ResponseBody List<ProductStockData> getProducts(
			@ModelAttribute("categoryInputModel") CategoryRequest categoryInputModel, Model model) {


	 // listing the Products Details by category by calling productsDao Interface method.
		List<ProductStockData> products=null;
		try {
			
			products = productsDAO.getProductsByCategory(categoryInputModel);
		
		}
		catch (ProductsDAOException e) {

			
			model.addAttribute("error",e.getMessage());
		}
		return products;
	}
	
//It return product BatchNos by taking the productId as an input
	@PostMapping("/getProductBatchNos")
	public @ResponseBody List<ProductStockData> getProductBatchesNos(
			@ModelAttribute("productsProductIdInputModel") ProductsProductIdInputModel productsProductIdInputModel,
			Model model) {

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
			Model model) {

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
	public @ResponseBody List<ProductsReOrderList> getReOrderLevelProducts(Model model) {
		
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
	public String saveCategory(@ModelAttribute("categoryInputModel") CategoryRequest2 categoryInputModel) {
		ModelMapper modelMapper = new ModelMapper();
		try {
		//Mapping the input model to the Entity Model by using Model Mapping.
		ProductsCategory productsCategory = modelMapper.map(categoryInputModel, ProductsCategory.class);
		productsDAO.saveCategory(productsCategory);
		
		}
		catch(ProductsDAOException e) {
			e.printStackTrace();
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
		catch(ProductsDAOException e) {
			e.printStackTrace();
			return "Can't Created the new HSN";
		}
		return "Succesfully Created the new HSN";

	}

}