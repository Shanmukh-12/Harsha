package main.models.productModels.outputModels;

public class ProductsReOrderList {
	
	private int productId;

	private String productName;

	private int productReorderLevel;
	private long sumOfProducts;
	public ProductsReOrderList(int productId, String productName, int productReorderLevel, long sumOfProducts) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productReorderLevel = productReorderLevel;
		this.sumOfProducts = sumOfProducts;
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
	public int getProductReorderLevel() {
		return productReorderLevel;
	}
	public void setProductReorderLevel(int productReorderLevel) {
		this.productReorderLevel = productReorderLevel;
	}
	public long getSumOfProducts() {
		return sumOfProducts;
	}
	public void setSumOfProducts(int sumOfProducts) {
		this.sumOfProducts = sumOfProducts;
	}
	@Override
	public String toString() {
		return "ProductsReOrderList [productId=" + productId + ", productName=" + productName + ", productReorderLevel="
				+ productReorderLevel + ", sumOfProducts=" + sumOfProducts + "]";
	}
	
	
	
	
	
}