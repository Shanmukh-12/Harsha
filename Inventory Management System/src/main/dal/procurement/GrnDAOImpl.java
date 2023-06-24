package main.dal.procurement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.bll.procurement.GrnBll;
import main.dao.procurement.GrnDAO;
import main.models.grnModels.entities.ImGrn;
import main.models.grnModels.entities.ImGrnProducts;
import main.models.grnModels.inputModels.GrnIdInput;
import main.models.grnModels.inputModels.GrnInputFilters;
import main.models.grnModels.inputModels.GrnInputList;
import main.models.grnModels.inputModels.GrnInputProductsList;
import main.models.grnModels.outputModels.GrnListProductsOutputModel;
import main.models.grnModels.outputModels.ImGrnOutputModel;
import main.models.productModels.dto.ProductPrice;
import main.models.productModels.dto.SalePrice;
import main.models.productModels.entities.ProductStock;
import main.models.purchaseOrder.entityModels.Im_Purchase_Order_Products;

@Component
public class GrnDAOImpl implements GrnDAO {

	@PersistenceContext
	EntityManager manager;

	@Autowired
	GrnBll gb;

	@Transactional
	public boolean saveGrn(ImGrn imGrn) {
		manager.persist(imGrn);

		List<ImGrnProducts> l = imGrn.getProductsList();
		System.out.println(l);
		for (ImGrnProducts i : l) {
			System.out.println(i);
			System.out.println(imGrn.getGrnId());
			i.setGrnId(imGrn.getGrnId());
			manager.persist(i);
		}
		return true;
	}

	@Override
	@Transactional
	public void updateStock(GrnInputList grnInputList) {
		List<GrnInputProductsList> productsList = grnInputList.getProductsList();
		for (GrnInputProductsList product : productsList) {
			System.out.println(product.getProductId() + " " + product.getBatchNo());
			ProductPrice productPrice = new ProductPrice(product.getProductId(), product.getQuantity(),
					product.getTotalPrice());

			SalePrice s = gb.getProductSalePrice(productPrice);
			try {
				ProductStock existingStock = (ProductStock) manager
						.createQuery(
								"SELECT s FROM ProductStock s WHERE s.productId = :productId AND s.batchNo = :batchNo")
						.setParameter("productId", product.getProductId()).setParameter("batchNo", product.getBatchNo())
						.getSingleResult();

				existingStock.setProductStock(existingStock.getProductStock() + product.getQuantity());
				existingStock.setProductSalePrice(s.getSalePrice());

			} catch (NoResultException e) {
				ModelMapper mp = new ModelMapper();
				ProductStock newStock = mp.map(product, ProductStock.class);
				newStock.setProductCost(product.getTotalPrice() / product.getQuantity());
				newStock.setProductMrp(product.getMrp());
				newStock.setProductSalePrice(s.getSalePrice());
				newStock.setProductStock(product.getQuantity());
				manager.persist(newStock);
			}
		}
	}

	@Override
	@Transactional
	public void updatePurchaseOrder(GrnInputList grnInputList) {
		List<GrnInputProductsList> productsList = grnInputList.getProductsList();
		for (GrnInputProductsList product : productsList) {

			Im_Purchase_Order_Products p = (Im_Purchase_Order_Products) manager.createQuery(
					"select s from Im_Purchase_Order_Products s where s.purchase_order_id =:purId and s.product_id=:prodId")
					.setParameter("purId", grnInputList.getPurchaseOrderId())
					.setParameter("prodId", product.getProductId()).getSingleResult();
			p.setQuantity_received(p.getQuantity_received() + product.getQuantity());
		}
	}

	@Transactional
	public List<GrnListProductsOutputModel> getGrnProducts(GrnIdInput gid) {
		ModelMapper mapper = new ModelMapper();
		List<GrnListProductsOutputModel> a = new ArrayList<>();
		ImGrn imGrn = (ImGrn) manager.createQuery("select s from ImGrn s where s.grnId=:x ")
				.setParameter("x", gid.getGrnId()).getSingleResult();

		List<ImGrnProducts> l = imGrn.getProductsList();
		for (ImGrnProducts i : l) {
			System.out.println(i.toString());
			GrnListProductsOutputModel io = mapper.map(i, GrnListProductsOutputModel.class);
			io.setGrn_id(imGrn.getGrnId());
			io.setProduct_id(i.getProductId());
			io.setBatch_no(i.getBatchNo());
			io.setTotalQuantity(i.getQuantity() + i.getBonus());
			a.add(io);
		}
		return a;
	}

	@Transactional
	public List<ImGrnOutputModel> getGrnList(GrnInputFilters g) {
		System.out.println(
				g.getGrn_amount() + " " + g.getGrnFromDate() + " " + g.getGrnToDate() + " " + g.getVendor_id());

		// System.out.println(g.toString());
		System.out.println(g.getGrnToDate());
		if (g.getVendor_id() == 0 && g.getGrnFromDate() == null && g.getGrn_amount() == 0.0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("1");
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchaseOrderId=p.purchase_order_id where s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() == null && g.getGrn_amount() == 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("2");
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchaseOrderId=p.purchase_order_id WHERE p.vendor_id =:v and s.grnDate<=:d",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() == 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("3");
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id  WHERE p.vendor_id = :v AND s.grnDate >= :d and s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate()))
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("4");
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id  where p.vendor_id = :v AND s.grnDate>= :d and s.grnDate<=:t AND s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate())).setParameter("a", g.getGrn_amount())
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("5");
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id WHERE p.vendor_id = :v AND s.grnDate >=:d AND s.grnAmount = :a AND s.grnDate <= :t",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate())).setParameter("a", g.getGrn_amount())
					.setParameter("t", g.getGrnToDate()).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() == null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("6");
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id WHERE p.vendor_id = :v AND s.grnDate<=: and s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id()).setParameter("a", g.getGrn_amount())
					.getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() == 0 && g.getGrnFromDate() == null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("6");
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id WHERE s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("a", g.getGrn_amount()).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else {
			System.out.println("7");
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchaseOrderId=p.purchase_order_id where s.grnDate>=:v and s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("v", LocalDate.parse(g.getGrnFromDate()))
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		}
	}

}
