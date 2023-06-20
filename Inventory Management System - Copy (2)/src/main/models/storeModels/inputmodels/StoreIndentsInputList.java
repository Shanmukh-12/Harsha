package main.models.storeModels.inputmodels;

import java.util.List;

public class StoreIndentsInputList {

	int indentID;
	int storeID;
	List<StoreIndentInputProductsList> productsList;

	public int getIndentID() {
		return indentID;
	}

	public void setIndentID(int indentID) {
		this.indentID = indentID;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public List<StoreIndentInputProductsList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<StoreIndentInputProductsList> productsList) {
		this.productsList = productsList;
	}

	@Override
	public String toString() {
		return "StoreIndentsList [indentID=" + indentID + ", storeID=" + storeID + ",\n productsList=" + productsList
				+ "]";
	}

}
