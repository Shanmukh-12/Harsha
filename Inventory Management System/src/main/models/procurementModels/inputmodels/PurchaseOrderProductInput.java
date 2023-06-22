package main.models.procurementModels.inputmodels;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PurchaseOrderProductInput implements Serializable {
	int purchase_order_id;
	int product_id;
	int purchase_order_quantity;
	BigDecimal negotiation_price;
	int quantity_received;
	PurchaseOrderInput parent;

	@Override
	public String toString() {
		return "PurchaseOrderProductInput [purchase_order_id=" + purchase_order_id + ", product_id=" + product_id
				+ ", purchase_order_quantity=" + purchase_order_quantity + ", negotiation_price=" + negotiation_price
				+ ", quantity_received=" + quantity_received + ", parent=" + parent + "]";
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

	public PurchaseOrderInput getParent() {
		return parent;
	}

	public void setParent(PurchaseOrderInput parent) {
		this.parent = parent;
	}

	public int getQuantity_received() {
		return quantity_received;
	}

	public void setQuantity_received(int quantity_received) {
		this.quantity_received = quantity_received;
	}

	public PurchaseOrderProductInput() {
		super();
	}
}
