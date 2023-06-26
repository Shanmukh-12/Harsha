package main.models.indentModels.inputModels;

public class InventoryIndentInputProductsList {

	int indentID;
	int productId;
	int quantity;

	InventoryIndentsInputList pil;

	public int getIndentID() {
		return indentID;
	}

	public void setIndentID(int indentID) {
		this.indentID = indentID;
	}

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

	public InventoryIndentsInputList getSil() {
		return pil;
	}

	public void setSil(InventoryIndentsInputList pil) {
		this.pil = pil;
	}

	@Override
	public String toString() {
		return "StoreIndentInputProductsList [indentID=" + indentID + ", productId=" + productId + ", quantity="
				+ quantity + ", pil=" + pil + "]";
	}

}