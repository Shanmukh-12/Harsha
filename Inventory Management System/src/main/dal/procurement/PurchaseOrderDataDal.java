package main.dal.procurement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.procurement.PurchaseOrderData;
import main.models.grnModels.dto.GrnProductInfo;
import main.models.purchaseOrder.dto.PurchasedQuantity;

@Component
public class PurchaseOrderDataDal implements PurchaseOrderData {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public PurchasedQuantity getNegotiationPrice(GrnProductInfo gpi) {

		PurchasedQuantity g = em.createQuery(
				"select new main.models.purchaseOrder.dto.PurchasedQuantity(e.purchase_order_quantity, e.negotiation_price)"
						+ " from Im_Purchase_Order_Products e "
						+ "where e.purchase_order_id=:purId and e.product_id=:prodId",
				PurchasedQuantity.class).setParameter("purId", gpi.getProductId())
				.setParameter("prodId", gpi.getProductId()).getSingleResult();
		return g;
	}
}
