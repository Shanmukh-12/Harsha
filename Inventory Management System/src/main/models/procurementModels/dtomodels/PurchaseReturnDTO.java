package main.models.procurementModels.dtomodels;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.models.purchaseReturns.entityModels.PurchaseReturn;
import main.models.purchaseReturns.entityModels.PurchaseReturnProducts;
@Component
@Scope("prototype")
public class PurchaseReturnDTO {
	private PurchaseReturn pr;
	private PurchaseReturnProducts prp;
	public PurchaseReturn getPr() {
		return pr;
	}
	public void setPr(PurchaseReturn pr) {
		this.pr = pr;
	}
	public PurchaseReturnProducts getPrp() {
		return prp;
	}
	public void setPrp(PurchaseReturnProducts prp) {
		this.prp = prp;
	}
	public PurchaseReturnDTO(PurchaseReturn pr, PurchaseReturnProducts prp) {
		super();
		this.pr = pr;
		this.prp = prp;
	}
	public PurchaseReturnDTO() {
		super();
	}
	@Override
	public String toString() {
		return "PurchaseReturnDTO [pr=" + pr + ", prp=" + prp + "]";
	}

	

}
