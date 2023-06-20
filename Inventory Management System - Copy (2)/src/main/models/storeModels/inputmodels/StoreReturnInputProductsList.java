package main.models.storeModels.inputmodels;

public class StoreReturnInputProductsList {

	int productId;
	int batchNo;
	int quantity;
	String reason;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "StoreReturnInputProductsList [productId=" + productId + ", batchNo=" + batchNo + ", quantity="
				+ quantity + ", reason=" + reason + "]";
	}
	
}
