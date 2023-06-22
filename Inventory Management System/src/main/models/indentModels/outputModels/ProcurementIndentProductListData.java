package main.models.indentModels.outputModels;


public class ProcurementIndentProductListData {

	int productId;
	String productName;
	String productCategoryName;
	int quantity;

	public int getProductId() {
		return productId;
	}

	public ProcurementIndentProductListData() {
		super();
	}

	public ProcurementIndentProductListData(int productId, String productName, String productCategoryName, int quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategoryName = productCategoryName;
		this.quantity = quantity;
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