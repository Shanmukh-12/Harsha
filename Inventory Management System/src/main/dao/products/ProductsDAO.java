package main.dao.products;

import java.util.List;

import main.models.productModels.dto.ProductProfit;
import main.models.productModels.entities.HSNEntityModel;
import main.models.productModels.entities.ProductsCategory;
import main.models.productModels.inputModels.ProductsProductIdInputModel;
import main.models.productModels.outputModels.ProductStockData;
import main.models.productModels.outputModels.ProductsReOrderList;

public interface ProductsDAO {

	public List<ProductsReOrderList> getReOrderLevelProducts();

	public List<ProductStockData> getProductsByCategory(int selectedCategoryId);

	public List<ProductStockData> getProductsByProductId(int selectedProductId);

	public ProductStockData getQuantityandpriceByProductIdOrBatchNo(int selectedProductId, int selectedBatchNo);

	public boolean saveCategory(ProductsCategory productsCategory);

	public boolean saveHSN(HSNEntityModel hsnEntityModel);

	public ProductProfit getProfitPercentage(ProductsProductIdInputModel pp);

}
