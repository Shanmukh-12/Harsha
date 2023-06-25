package main.models.indentModels.inputModels;

import java.util.List;

public class InventoryIndentsInputList {

	int indentID;
	List<InventoryIndentInputProductsList> productsList;

	public int getIndentID() {
		return indentID;
	}

	public void setIndentID(int indentID) {
		this.indentID = indentID;
	}

	public List<InventoryIndentInputProductsList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<InventoryIndentInputProductsList> productsList) {
		this.productsList = productsList;
	}

}