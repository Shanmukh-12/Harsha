package main.dao.storeIssues;

import java.util.List;

import main.models.productModels.entities.ProductStock;
import main.models.productModels.inputModels.ProductsProductIdInputModel;
import main.models.storeIssueModels.entities.StoreIssueData;
import main.models.storeIssueModels.entities.StoreIssues;
import main.models.storeIssueModels.inputModels.StoreFilters;
import main.models.storeIssueModels.outputModels.StoreIssueIds;
import main.models.storeIssueModels.outputModels.StoreIssueProducts;
import main.models.storeIssueModels.outputModels.StoreIssuesData;
import main.models.storeModels.inputmodels.StoreId;

public interface StoreIssueDao {

	public List<StoreIssueData> getStoreIds(StoreId sid);

	// public List<StoreIssues> getStoreIds(StoreId sid);

	public List<StoreIssuesData> getAllStoreIssues();

	public List<StoreIssueProducts> getStoreIssuesProductsList(StoreIssueIds storeIssueIds);

	public List<StoreIssuesData> getStoreIssuesListByTo(StoreFilters storeFilters);

	public List<StoreIssuesData> getStoreIssuesListByFrom(StoreFilters storeFilters);

	public List<StoreIssuesData> getStoreIssuesListByStatus(StoreFilters storeFilters);

	public List<StoreIssuesData> getStoreIssuesListByStatusFrom(StoreFilters storeFilters);

	public List<StoreIssuesData> getStoreIssuesListById(StoreFilters storeFilters);

	public List<StoreIssuesData> getStoreIssuesListByIdFrom(StoreFilters storeFilters);

	public List<StoreIssuesData> getStoreIssuesListByIdStatus(StoreFilters storeFilters);

	public List<StoreIssuesData> getStoreIssuesListByIdStatusFrom(StoreFilters storeFilters);

	public List<ProductStock> getBatchNumbers(ProductsProductIdInputModel productId);

	public boolean saveStoreInfo(StoreIssues storeIssues);

}
