package main.models.storeIssueModels.inputModels;

import java.util.List;

public class StoreIssuesList {

	int storeId;
	List<StoreIssuesListData> storeProducts;

	public StoreIssuesList() {
		super();
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public List<StoreIssuesListData> getStoreProducts() {
		return storeProducts;
	}

	public void setStoreProducts(List<StoreIssuesListData> storeProducts) {
		this.storeProducts = storeProducts;
	}

	@Override
	public String toString() {
		return "StoreIssuesList [storeId=" + storeId + ", storeProducts=" + storeProducts + "]";
	}

}
