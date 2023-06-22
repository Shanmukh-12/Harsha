package main.models.grnModels.inputModels;

import java.util.List;

public class GrnInputList {

	int purchase_order_id;
	List<GrnInputProductsList> productsList;

	public GrnInputList() {
		super();
	}

	public GrnInputList(int purchase_order_id, List<GrnInputProductsList> productsList) {
		super();
		this.purchase_order_id = purchase_order_id;
		this.productsList = productsList;
	}

	public int getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(int purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public List<GrnInputProductsList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<GrnInputProductsList> productsList) {
		this.productsList = productsList;
	}

	@Override
	public String toString() {
		return "GrnInputList [purchase_order_id=" + purchase_order_id + ", productsList=" + productsList + "]";
	}

}