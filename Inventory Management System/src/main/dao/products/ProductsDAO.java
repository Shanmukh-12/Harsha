package main.dao.products;

import java.util.List;

import main.dal.products.ProductsDAOException;
import main.models.productModels.dto.ProductProfit;
import main.models.productModels.entities.HSNEntityModel;
import main.models.productModels.entities.ProductsCategory;
import main.models.productModels.inputModels.CategoryRequest;
import main.models.productModels.inputModels.ProductsProductIdInputModel;
import main.models.productModels.inputModels.ProductsProductIdandBatchNoInputModel;
import main.models.productModels.outputModels.ProductIdListOutput;
import main.models.productModels.outputModels.ProductStockData;
import main.models.productModels.outputModels.ProductsReOrderList;

public interface ProductsDAO {
	

	//It returns List of Re-order Products 
	public List<ProductsReOrderList> getReOrderLevelProducts() throws ProductsDAOException;
	
	//It return Products Data by taking the Category Id as an input
	public List<ProductStockData> getProductsByCategory(CategoryRequest categoryInputModel) throws ProductsDAOException;

	// Getting ProductId and Product Name Based on Category Id
	public List<ProductIdListOutput> getProductsByCategoryId(CategoryRequest categoryInputModel) throws ProductsDAOException;

	//It return Products Data by taking the productId as an input
	public List<ProductStockData> getProductsByProductId(int selectedProductId) throws ProductsDAOException;

	//It return products Data by taking the BatchNo and ProductId as an input
	public ProductStockData getQuantityandpriceByProductIdOrBatchNo(ProductsProductIdandBatchNoInputModel productsProductIdandBatchNoInputModel) throws ProductsDAOException;

	//It persist the category created by procurement team
	public boolean saveCategory(ProductsCategory productsCategory) throws ProductsDAOException;

	//It persist the HSN created by the procurement team
	public boolean saveHSN(HSNEntityModel hsnEntityModel) throws ProductsDAOException;
	
	// Return Product Profit by taking Product Id as Input.
	public ProductProfit getProfitPercentage(ProductsProductIdInputModel pp) throws ProductsDAOException;

}
