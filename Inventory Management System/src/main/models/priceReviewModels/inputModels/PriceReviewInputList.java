package main.models.priceReviewModels.inputModels;

import java.util.List;

public class PriceReviewInputList {

	private int pr_id;
	private List<PriceReviewProductsInputList> productsList;

	public int getPr_id() {
		return pr_id;
	}

	public void setPr_id(int pr_id) {
		this.pr_id = pr_id;
	}

	public List<PriceReviewProductsInputList> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<PriceReviewProductsInputList> productsList) {
		this.productsList = productsList;
	}

	@Override
	public String toString() {
		return "PriceReviewInputList [pr_id=" + pr_id + ", productsList=" + productsList + "]";
	}

}
