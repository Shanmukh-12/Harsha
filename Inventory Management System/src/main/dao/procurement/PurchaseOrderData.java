package main.dao.procurement;

import main.models.grnModels.dto.GrnProductInfo;
import main.models.purchaseOrder.dto.PurchasedQuantity;

public interface PurchaseOrderData {

	public PurchasedQuantity getNegotiationPrice(GrnProductInfo gpi);

}
