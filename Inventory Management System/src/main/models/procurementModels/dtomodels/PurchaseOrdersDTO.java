package main.models.procurementModels.dtomodels;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import main.models.purchaseOrder.entityModels.PurchaseOrder;
import main.models.purchaseOrder.entityModels.PurchaseOrderProducts;
@Component
@Scope("prototype")
public class PurchaseOrdersDTO {
	private PurchaseOrder po;
	private PurchaseOrderProducts pop;
	public PurchaseOrder getPo() {
		return po;
	}
	public void setPo(PurchaseOrder po) {
		this.po = po;
	}
	public PurchaseOrderProducts getPop() {
		return pop;
	}
	public void setPop(PurchaseOrderProducts pop) {
		this.pop = pop;
	}
	public PurchaseOrdersDTO(PurchaseOrder po, PurchaseOrderProducts pop) {
		super();
		this.po = po;
		this.pop = pop;
	}
	public PurchaseOrdersDTO() {
		super();
	}
	@Override
	public String toString() {
		return "PurchaseOrdersDTO [po=" + po + ", pop=" + pop + "]";
	}



}
