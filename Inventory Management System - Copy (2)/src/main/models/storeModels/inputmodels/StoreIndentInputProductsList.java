package main.models.storeModels.inputmodels;

public class StoreIndentInputProductsList {

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
		return "StoreIndentInputProductsList [productId=" + productId + ", quantity="
				+ quantity + ", sil=" + "]";
	}

}
