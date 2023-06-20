package main.models.storeModels.inputmodels;

import java.util.List;

public class StoreReturnsInputList {
	int storeReturnId;
	int date;
	int storeIssueId;
	List<StoreReturnInputProductsList> productsList;
	public int getStoreReturnId() {
		return storeReturnId;
	}
	public void setStoreReturnId(int storeReturnId) {
		this.storeReturnId = storeReturnId;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getStoreIssueId() {
		return storeIssueId;
	}
	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
	}
	public List<StoreReturnInputProductsList> getProductsList() {
		return productsList;
	}
	public void setProductsList(List<StoreReturnInputProductsList> productsList) {
		this.productsList = productsList;
	}
	@Override
	public String toString() {
		return "StoreReturnsInputList [storeReturnId=" + storeReturnId + ", date=" + date + ", storeIssueId="
				+ storeIssueId + ", productsList=" + productsList + "]";
	}
	
}
