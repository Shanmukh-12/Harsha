package main.dao.products;

import java.util.List;

import main.dal.products.ProductsDAOException;
import main.models.productModels.entities.ProductsCategory;



public interface ProductCategoryDAO {
	
	
	//Getting Product Category ID,Category Name
	  public List<ProductsCategory> getProductCategories() throws ProductsDAOException;
}
