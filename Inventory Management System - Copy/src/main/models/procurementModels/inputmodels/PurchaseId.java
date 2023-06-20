package main.models.procurementModels.inputmodels;

public class PurchaseId {
	private int purchase_order_id;

	public int getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(int purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	@Override
	public String toString() {
		return "PurchaseId [purchase_order_id=" + purchase_order_id + "]";
	}

	public PurchaseId(int purchase_order_id) {
		super();
		this.purchase_order_id = purchase_order_id;
	}

	public PurchaseId() {
		super();
	}

}
