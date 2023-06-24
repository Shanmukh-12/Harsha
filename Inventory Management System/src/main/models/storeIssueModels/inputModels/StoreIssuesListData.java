package main.models.storeIssueModels.inputModels;

public class StoreIssuesListData {
	int productId;
	int batchNo;
	int issuedQuantity;
	double purchaseAmount;

	public StoreIssuesListData() {
		super();
	}

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

	public int getIssuedQuantity() {
		return issuedQuantity;
	}

	public void setIssuedQuantity(int issuedQuantity) {
		this.issuedQuantity = issuedQuantity;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	@Override
	public String toString() {
		return "StoreIssuesListData [productId=" + productId + ", batchNo=" + batchNo + ", issuedQuantity="
				+ issuedQuantity + ", purchaseAmount=" + purchaseAmount + "]";
	}

}
