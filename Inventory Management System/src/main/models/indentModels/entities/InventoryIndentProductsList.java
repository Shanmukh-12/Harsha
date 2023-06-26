package main.models.indentModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_indents_Products")
public class InventoryIndentProductsList implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "indents_id")
	int indentID;
	@Id
	@Column(name = "indents_products_id")
	int productId;
	@Column(name = "indents_products_quantity")
	int quantity;
	@Column(name = "indents_products_status")
	String status = "Active";

	public InventoryIndentProductsList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InventoryIndentProductsList(int indentID, int productId, int quantity, String status,
			InventoryIndentsList pil) {
		super();
		this.indentID = indentID;
		this.productId = productId;
		this.quantity = quantity;
		this.status = status;
		this.pil = pil;
	}

	@ManyToOne
	@JoinColumn(name = "indents_id", referencedColumnName = "indents_id", insertable = false, updatable = false)
	InventoryIndentsList pil;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public InventoryIndentsList getPil() {
		return pil;
	}

	public void setPil(InventoryIndentsList pil) {
		this.pil = pil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "InventoryIndentProductsList [indentID=" + indentID + ", productId=" + productId + ", quantity="
				+ quantity + ", status=" + status + "]";
	}

}