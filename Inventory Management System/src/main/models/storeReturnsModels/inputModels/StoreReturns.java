package main.models.storeReturnsModels.inputModels;

import java.util.List;

public class StoreReturns {

	int storeID;
	int issueId;
	List<StoreReturnProducts> productsList;

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public List<StoreReturnProducts> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<StoreReturnProducts> productsList) {
		this.productsList = productsList;
	}

	public StoreReturns(int storeID, int issueId, List<StoreReturnProducts> productsList) {
		super();
		this.storeID = storeID;
		this.issueId = issueId;
		this.productsList = productsList;
	}

	public StoreReturns() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ReturnProducts [storeID=" + storeID + ", issueId=" + issueId + ", productsList=" + productsList + "]";
	}

}
