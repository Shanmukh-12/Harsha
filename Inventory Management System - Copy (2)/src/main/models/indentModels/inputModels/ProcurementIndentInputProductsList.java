package main.models.indentModels.inputModels;

public class ProcurementIndentInputProductsList {

	int indentID;
	int productId;
	int quantity;

	ProcurementIndentsInputList pil;

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

	public ProcurementIndentsInputList getSil() {
		return pil;
	}

	public void setSil(ProcurementIndentsInputList pil) {
		this.pil = pil;
	}

	@Override
	public String toString() {
		return "StoreIndentInputProductsList [indentID=" + indentID + ", productId=" + productId + ", quantity="
				+ quantity + ", pil=" + pil + "]";
	}

}
