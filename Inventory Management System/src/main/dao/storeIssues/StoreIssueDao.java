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
	 //Getting StoreIssue Information Based on storeID
	public List<StoreIssueData> getStoreIds(StoreId sid); 
	
	//Getting storeIssue Information storeID,StoreIssueId,storeIssueDate,storeIssueStatus,amount
	public List<StoreIssuesData> getAllStoreIssues();
	
	//Getting StoreIssue Products Information ProductID,BatchNo,ProductName,Product Category,Quantity
	public List<StoreIssueProducts> getStoreIssuesProductsList(StoreIssueIds storeIssueIds);  
	
	//Getting Filtered Data based on To date
	public List<StoreIssuesData> getStoreIssuesListByTo(StoreFilters storeFilters);  
	
	//Getting Filtered Data Based on from Date,To date
	public List<StoreIssuesData> getStoreIssuesListByFrom(StoreFilters storeFilters);
	
	//Getting Filtered Information based on StoreIssueStatus,To date
	public List<StoreIssuesData> getStoreIssuesListByStatus(StoreFilters storeFilters);   
	
	//Getting Filtered Information based on StoreIssueStatus,From date,To date
	public List<StoreIssuesData> getStoreIssuesListByStatusFrom(StoreFilters storeFilters); 
	
	//Getting Filtered data based on StoreID
	public List<StoreIssuesData> getStoreIssuesListById(StoreFilters storeFilters); 
	
	//Getting Filtered Information based on StoreId,From date
	public List<StoreIssuesData> getStoreIssuesListByIdFrom(StoreFilters storeFilters);  
	
	//Getting Filtered Information based on storeId,StoreIssue Status 
	public List<StoreIssuesData> getStoreIssuesListByIdStatus(StoreFilters storeFilters);   

	//Getting Filtered Information based on StoreId,StoreIssue Status,From Date 
	public List<StoreIssuesData> getStoreIssuesListByIdStatusFrom(StoreFilters storeFilters);  
	
	//Getting PRoductStock Information based on ProductId
	public List<ProductStock> getBatchNumbers(ProductsProductIdInputModel productId);   

	//Saving StoreIssue Information
	public boolean saveStoreInfo(StoreIssues storeIssues);    


}
