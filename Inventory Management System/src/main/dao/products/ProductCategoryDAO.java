package main.dao.products;

import java.util.List;

import main.models.productModels.entities.ProductsCategory;

public interface ProductCategoryDAO {
	  public List<ProductsCategory> getProductCategories();
}
