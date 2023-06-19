package main.models.storeModels.outputmodels;

public class StoreIndentProducts {

	int productId;
	int quantity;
	public int getProductId() {
		return productId;
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
	@Override
	public String toString() {
		return "StoreIndentProducts [productId=" + productId + ", quantity=" + quantity + "]";
	}
	
}
