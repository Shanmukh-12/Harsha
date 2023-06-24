package main.models.purchaseOrder.dto;

public class PurchasedQuantity {

	private int purchase_order_quantity;
	private double negotiation_price;

	public PurchasedQuantity(int purchase_order_quantity, double negotiation_price) {
		super();
		this.purchase_order_quantity = purchase_order_quantity;
		this.negotiation_price = negotiation_price;
	}

	@Override
	public String toString() {
		return "PurchasedQuantity [purchase_order_quantity=" + purchase_order_quantity + ", negotiation_price="
				+ negotiation_price + "]";
	}

	public int getPurchase_order_quantity() {
		return purchase_order_quantity;
	}

	public void setPurchase_order_quantity(int purchase_order_quantity) {
		this.purchase_order_quantity = purchase_order_quantity;
	}

	public double getNegotiation_price() {
		return negotiation_price;
	}

	public void setNegotiation_price(double negotiation_price) {
		this.negotiation_price = negotiation_price;
	}

	public PurchasedQuantity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
