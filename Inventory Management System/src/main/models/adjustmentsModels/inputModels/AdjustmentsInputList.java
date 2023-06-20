package main.models.adjustmentsModels.inputModels;

import java.util.List;

public class AdjustmentsInputList {
	private int adjs_id;

	@Override
	public String toString() {
		return "AdjustmentsInputList [adjs_id=" + adjs_id + ", productsList=" + productsList + "]";
	}

	private List<AdjustmentProductsInputList> productsList;

	public List<AdjustmentProductsInputList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<AdjustmentProductsInputList> productsList) {
		this.productsList = productsList;
	}

	public int getAdjs_id() {
		return adjs_id;
	}

	public void setAdjs_id(int adjs_id) {
		this.adjs_id = adjs_id;
	}

}
