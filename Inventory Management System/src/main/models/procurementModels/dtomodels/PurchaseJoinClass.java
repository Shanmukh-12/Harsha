package main.models.procurementModels.dtomodels;

import main.models.purchaseOrder.entityModels.Im_Purchase_Order;
import main.models.purchaseOrder.entityModels.Im_Purchase_Order_Products;

public class PurchaseJoinClass {
	private Im_Purchase_Order po;
	private Im_Purchase_Order_Products pop;

	public Im_Purchase_Order getPo() {
		return po;
	}

	public void setPo(Im_Purchase_Order po) {
		this.po = po;
	}

	@Override
	public String toString() {
		return "PurchaseJoinClass [po=" + po + ", pop=" + pop + "]";
	}

	public PurchaseJoinClass(Im_Purchase_Order po, Im_Purchase_Order_Products pop) {
		super();
		this.po = po;
		this.pop = pop;
	}

	public Im_Purchase_Order_Products getPop() {
		return pop;
	}

	public void setPop(Im_Purchase_Order_Products pop) {
		this.pop = pop;
	}

	public PurchaseJoinClass() {
		super();
	}

}
