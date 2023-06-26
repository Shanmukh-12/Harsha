package main.models.productModels.outputModels;

public class ProductIdListOutput {

	private int productId;

	private String productName;

	public ProductIdListOutput(int productId, String productName) {
		super();
		this.productId = productId;
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProductIdListOutput [productId=" + productId + ", productName=" + productName + "]";
	}

}
