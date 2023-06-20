package main.models.indentModels.inputModels;

import java.util.List;

public class ProcurementIndentsInputList {

	int indentID;
	List<ProcurementIndentInputProductsList> productsList;

	public int getIndentID() {
		return indentID;
	}

	public void setIndentID(int indentID) {
		this.indentID = indentID;
	}

	public List<ProcurementIndentInputProductsList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<ProcurementIndentInputProductsList> productsList) {
		this.productsList = productsList;
	}

}
