package main.models.adjustmentsModels.inputModels;

import java.time.LocalDate;

public class AdjustmentsFilterInput {

	private int productCategoryId;
	private int productId;
	private LocalDate fromDate;
	private LocalDate toDate;

	public int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "AdjustmentsFilterInput [productCategoryId=" + productCategoryId + ", productId=" + productId
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

}
