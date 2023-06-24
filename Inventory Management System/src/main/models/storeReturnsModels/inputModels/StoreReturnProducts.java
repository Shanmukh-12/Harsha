package main.models.storeReturnsModels.inputModels;

public class StoreReturnProducts {

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

	public StoreReturnProducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreReturnProducts(int productId, int batchNo, int quantity, String reason) {
		super();
		this.productId = productId;
		this.batchNo = batchNo;
		this.quantity = quantity;
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "StoreReturnProducts [productId=" + productId + ", batchNo=" + batchNo + ", quantity=" + quantity
				+ ", reason=" + reason + "]";
	}
}
