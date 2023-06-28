package main.models.purchaseOrder.entityModels;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "im_purchase_order_products")
@JsonIgnoreProperties(ignoreUnknown = true)

public class PurchaseOrderProducts implements Serializable {
	@Id
	@Column(name = "purchase_order_id")
	int purchase_order_id;
	@Id
	@Column(name = "product_id")
	int product_id;
	@Column(name = "purchase_order_quantity")
	int purchase_order_quantity;
	@Column(name = "negotiation_price")
	BigDecimal negotiation_price;
	@Column(name = "quantity_received")
	int quantity_received;

	@ManyToOne(fetch = FetchType.LAZY)

	@JsonIgnore
	@JoinColumn(name = "purchase_order_id", referencedColumnName = "purchase_order_id", insertable = false, updatable = false)
	private PurchaseOrder parent;

	@Override
	public String toString() {
		return "Im_Purchase_Order_Products [purchase_order_id=" + purchase_order_id + ", product_id=" + product_id
				+ ", purchase_order_quantity=" + purchase_order_quantity + ", negotiation_price=" + negotiation_price
				+ ", quantity_received=" + quantity_received + "]";
	}

	public int getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(int purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getPurchase_order_quantity() {
		return purchase_order_quantity;
	}

	public void setPurchase_order_quantity(int purchase_order_quantity) {
		this.purchase_order_quantity = purchase_order_quantity;
	}

	public BigDecimal getNegotiation_price() {
		return negotiation_price;
	}

	public void setNegotiation_price(BigDecimal negotiation_price) {
		this.negotiation_price = negotiation_price;
	}

	public int getQuantity_received() {
		return quantity_received;
	}

	public void setQuantity_received(int quantity_received) {
		this.quantity_received = quantity_received;
	}

	public PurchaseOrder getParent() {
		return parent;
	}

	public void setParent(PurchaseOrder parent) {
		this.parent = parent;
	}

	public PurchaseOrderProducts(int purchase_order_id, int product_id, int purchase_order_quantity,
			BigDecimal negotiation_price, int quantity_received, PurchaseOrder parent) {
		super();
		this.purchase_order_id = purchase_order_id;
		this.product_id = product_id;
		this.purchase_order_quantity = purchase_order_quantity;
		this.negotiation_price = negotiation_price;
		this.quantity_received = quantity_received;
		this.parent = parent;
	}

	public PurchaseOrderProducts() {
		super();
	}

	

		

}
