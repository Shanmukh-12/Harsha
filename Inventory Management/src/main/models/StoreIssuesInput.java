package main.models.storeissuesModels;

import java.util.List;

public class StoreIssuesInput {
	
	int storeIssueId;
	List<StoreIssuesProductsInput> productsList;
	
	public int getStoreIssueId() {
		return storeIssueId;
	}
	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
	}
	public List<StoreIssuesProductsInput> getProductsList() {
		return productsList;
	}
	public void setProductsList(List<StoreIssuesProductsInput> productsList) {
		this.productsList = productsList;
	}
	
	
	

}
