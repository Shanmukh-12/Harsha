package main.models.grnModels.inputModels;

import java.util.List;

public class GrnInputList {

	int purchaseOrderId;
	List<GrnInputProductsList> productsList;

	public GrnInputList() {
		super();
	}

	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public List<GrnInputProductsList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<GrnInputProductsList> productsList) {
		this.productsList = productsList;
	}

	public GrnInputList(int purchaseOrderId, List<GrnInputProductsList> productsList) {
		super();
		this.purchaseOrderId = purchaseOrderId;
		this.productsList = productsList;
	}

	@Override
	public String toString() {
		return "GrnInputList [purchaseOrderId=" + purchaseOrderId + ", productsList=" + productsList + "]";
	}

}