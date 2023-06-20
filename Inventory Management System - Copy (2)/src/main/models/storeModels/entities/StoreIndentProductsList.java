package main.models.storeModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_Store_Indents_Products")
public class StoreIndentProductsList implements Serializable {

	@Id
	@Column(name = "indents_Id")
	int indentID;
	@Id
	@Column(name = "indents_products_id")
	int productId;
	@Column(name = "indents_products_quantity")
	int quantity;

	@ManyToOne
	@JoinColumn(name = "indents_Id", referencedColumnName = "indents_Id", insertable = false, updatable = false)
	StoreIndentsList sil;

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

	public StoreIndentsList getSil() {
		return sil;
	}

	public void setSil(StoreIndentsList sil) {
		this.sil = sil;
	}

	@Override
	public String toString() {
		return "StoreIndentProductsList [Indents_ID=" + indentID + ", productId=" + productId + ", quantity=" + quantity
				+ ", sil=" + sil + "]";
	}

}
