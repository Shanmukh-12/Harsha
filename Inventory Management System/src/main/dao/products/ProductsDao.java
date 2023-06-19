package main.dao.products;

import java.util.List;

import main.models.productModels.outputModels.ProductStockData;

public interface ProductsDao {
	public List<ProductStockData> getProductsByCategory(int categoryId);
}
