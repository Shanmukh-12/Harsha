package main.models.procurementModels.dtomodels;

import main.models.purchaseReturns.entityModels.ImPurchaseReturn;
import main.models.purchaseReturns.entityModels.ImPurchaseReturnProduct;

public class PurchaseReturnJoinClass {
	private ImPurchaseReturn pr;
	private ImPurchaseReturnProduct prp;

	public ImPurchaseReturn getPr() {
		return pr;
	}

	public void setPr(ImPurchaseReturn pr) {
		this.pr = pr;
	}

	public ImPurchaseReturnProduct getPrp() {
		return prp;
	}

	public void setPrp(ImPurchaseReturnProduct prp) {
		this.prp = prp;
	}

	@Override
	public String toString() {
		return "PurchaseReturnJoinClass [pr=" + pr + ", prp=" + prp + "]";
	}

	public PurchaseReturnJoinClass(ImPurchaseReturn pr, ImPurchaseReturnProduct prp) {
		super();
		this.pr = pr;
		this.prp = prp;
	}

	public PurchaseReturnJoinClass() {
		super();
	}

}
