package main.models.storeIssueModels.outputModels;

public class StoreIssueProducts {

	int productId;
	int batchNo;
	String productName;
	String productCategoryName;
	int quantity;

	public int getProductId() {
		return productId;
	}

	public StoreIssueProducts() {
		super();
	}

	public StoreIssueProducts(int productId, int batchNo, String productName, String productCategoryName,
			int quantity) {
		super();
		this.productId = productId;
		this.batchNo = batchNo;
		this.productName = productName;
		this.productCategoryName = productCategoryName;
		this.quantity = quantity;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	@Override
	public String toString() {
		return "StoreIndentProducts [productId=" + productId + ", productName=" + productName + ", productCategoryName="
				+ productCategoryName + ", quantity=" + quantity + "]";
	}

}
