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
public class ProcurementIndentProductsList implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "indents_id")
	int indentID;
	@Id
	@Column(name = "indents_products_id")
	int productId;
	@Column(name = "indents_products_quantity")
	int quantity;

	@ManyToOne
	@JoinColumn(name = "indents_id", referencedColumnName = "indents_id", insertable = false, updatable = false)
	ProcurementIndentsList pil;

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

	public ProcurementIndentsList getSil() {
		return pil;
	}

	public void setSil(ProcurementIndentsList sil) {
		this.pil = sil;
	}

	@Override
	public String toString() {
		return "ProcurementIndentProductsList [indentID=" + indentID + ", productId=" + productId + ", quantity="
				+ quantity + "]";
	}

}
