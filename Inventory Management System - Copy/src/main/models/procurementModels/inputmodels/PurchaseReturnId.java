package main.models.procurementModels.inputmodels;

public class PurchaseReturnId {
	private int purchase_return_id;

	public int getPurchase_return_id() {
		return purchase_return_id;
	}

	public void setPurchase_return_id(int purchase_return_id) {
		this.purchase_return_id = purchase_return_id;
	}

	@Override
	public String toString() {
		return "PurchaseReturnId [purchase_return_id=" + purchase_return_id + "]";
	}

	public PurchaseReturnId(int purchase_return_id) {
		super();
		this.purchase_return_id = purchase_return_id;
	}

	public PurchaseReturnId() {
		super();
	}

}
