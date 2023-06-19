package main.dal.procurement;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import main.dao.procurement.ProcurementDAO;
import main.models.loginModel.inputModels.MailDetails;
import main.models.loginModel.inputModels.credentials2;
import main.models.loginModel.inputModels.password;
import main.models.procurementModels.dtomodels.PurchaseJoinClass;
import main.models.procurementModels.dtomodels.PurchaseReturnJoinClass;
import main.models.procurementModels.inputmodels.ProductInput;
import main.models.procurementModels.inputmodels.ProductInputMapping;
import main.models.procurementModels.inputmodels.PurchaseId;
import main.models.procurementModels.inputmodels.PurchaseReturnId;
import main.models.procurementModels.inputmodels.PurchasesFilter;
import main.models.procurementModels.inputmodels.PurchasesReturnFilter;
import main.models.procurementModels.outputmodels.ImPurchaseOrderOutput;
import main.models.procurementModels.outputmodels.PurchaseReturnOutput;
import main.models.productModels.entities.im_products;
import main.models.productModels.entities.im_products_stock;
import main.models.purchaseOrder.entityModels.Im_Purchase_Order;
import main.models.purchaseOrder.entityModels.Im_Purchase_Order_Products;
import main.models.purchaseReturns.entityModels.ImPurchaseReturn;
import main.models.purchaseReturns.entityModels.ImPurchaseReturnProduct;
import main.models.userModels.entities.User;
import main.models.userModels.outputModels.UserOutput;
import main.models.warehouseModels.dtomodels.JoinClass2;
import main.models.warehouseModels.dtomodels.joinclass;
import main.models.warehouseModels.outputmodels.ProductCategoryCount;
import main.models.warehouseModels.outputmodels.ProductName;
import main.models.warehouseModels.outputmodels.TotalStock;
import main.models.warehouseModels.outputmodels.TotalWarehouseVal;
import main.models.warehouseModels.outputmodels.VendorCount;
import main.models.warehouseModels.outputmodels.productquant;

public class ProcurementDAL implements ProcurementDAO {
	// Custom query methods, if needed
	@PersistenceContext
	EntityManager em;
	@Autowired
	private ApplicationContext applicationContext;
	productquant t1;
	ProductName r1;

	im_products r;
	im_products_stock t;
	Im_Purchase_Order f1;
	Im_Purchase_Order_Products u1;

	@Transactional
	public boolean persist(User stud) {
		try {
			em.persist(stud);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public List<ImPurchaseOrderOutput> getPurchaseId2(PurchasesFilter p) {
		if (p.getVendor_id() == 0 && p.getPurchase_order_expected_date() == null
				&& p.getPurchase_order_expected_date1() == null) {
			List<ImPurchaseOrderOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.ImPurchaseOrderOutput(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from Im_Purchase_Order s",
					ImPurchaseOrderOutput.class).getResultList();
			for (ImPurchaseOrderOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_order_expected_date() == null) {
			List<ImPurchaseOrderOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.ImPurchaseOrderOutput(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from Im_Purchase_Order s where s.vendor_id=:v",
					ImPurchaseOrderOutput.class).setParameter("v", p.getVendor_id()).getResultList();
			for (ImPurchaseOrderOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_order_expected_date() != null
				&& p.getPurchase_order_expected_date1() != null) {
			List<ImPurchaseOrderOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.ImPurchaseOrderOutput(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from Im_Purchase_Order s where s.vendor_id=:v and s.purchase_order_expected_date>=:dat and s.purchase_order_expected_date<=:dat1",
					ImPurchaseOrderOutput.class).setParameter("dat", Date.valueOf(p.getPurchase_order_expected_date()))
					.setParameter("dat1", Date.valueOf(p.getPurchase_order_expected_date1()))
					.setParameter("v", p.getVendor_id()).getResultList();
			for (ImPurchaseOrderOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_order_expected_date() != null
				&& p.getPurchase_order_expected_date1() == null) {
			List<ImPurchaseOrderOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.ImPurchaseOrderOutput(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from Im_Purchase_Order s where s.vendor_id=:v and s.purchase_order_expected_date>=:dat",
					ImPurchaseOrderOutput.class).setParameter("dat", Date.valueOf(p.getPurchase_order_expected_date()))
					.setParameter("v", p.getVendor_id()).getResultList();
			for (ImPurchaseOrderOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() == 0 && p.getPurchase_order_expected_date() != null
				&& p.getPurchase_order_expected_date1() != null) {
			List<ImPurchaseOrderOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.ImPurchaseOrderOutput(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from Im_Purchase_Order s where s.purchase_order_expected_date>=:dat and s.purchase_order_expected_date<=:dat1",
					ImPurchaseOrderOutput.class).setParameter("dat", Date.valueOf(p.getPurchase_order_expected_date()))
					.setParameter("dat1", Date.valueOf(p.getPurchase_order_expected_date1())).getResultList();
			for (ImPurchaseOrderOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() == 0 && p.getPurchase_order_expected_date() != null
				&& p.getPurchase_order_expected_date1() == null) {
			List<ImPurchaseOrderOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.ImPurchaseOrderOutput(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from Im_Purchase_Order s where s.purchase_order_expected_date>=:dat",
					ImPurchaseOrderOutput.class).setParameter("dat", Date.valueOf(p.getPurchase_order_expected_date()))
					.getResultList();
			for (ImPurchaseOrderOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		}

		else {
			List<ImPurchaseOrderOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.ImPurchaseOrderOutput(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from Im_Purchase_Order s",
					ImPurchaseOrderOutput.class).getResultList();
			for (ImPurchaseOrderOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		}

	}

	@Transactional
	public ImPurchaseOrderOutput getPurchaseId3(PurchaseId p) {
		ImPurchaseOrderOutput s = em.createQuery(
				"SELECT new main.models.procurementModels.outputmodels.ImPurchaseOrderOutput(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from Im_Purchase_Order s where s.purchase_order_id=:dat",
				ImPurchaseOrderOutput.class).setParameter("dat", p.getPurchase_order_id()).getSingleResult();

		System.out.println(s.toString());

		return s;

	}

	@Transactional
	public List<PurchaseId> getPurchaseId(PurchasesFilter p) {
		if (p.getVendor_id() == 0 && p.getPurchase_order_expected_date() == null) {
			List<PurchaseId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseId(s.purchase_order_id) from Im_Purchase_Order s",
					PurchaseId.class).getResultList();
			for (PurchaseId x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_order_expected_date() == null) {
			List<PurchaseId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseId(s.purchase_order_id) from Im_Purchase_Order s where s.vendor_id=:v",
					PurchaseId.class).setParameter("v", p.getVendor_id()).getResultList();
			for (PurchaseId x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() == 0 && p.getPurchase_order_expected_date() != null) {
			List<PurchaseId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseId(s.purchase_order_id) from Im_Purchase_Order s where s.purchase_order_expected_date=:dat",
					PurchaseId.class).setParameter("dat", Date.valueOf(p.getPurchase_order_expected_date()))
					.getResultList();
			for (PurchaseId x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else {
			List<PurchaseId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseId(s.purchase_order_id) from Im_Purchase_Order s where s.vendor_id=:v and s.purchase_order_expected_date=:dat",
					PurchaseId.class).setParameter("v", p.getVendor_id())
					.setParameter("dat", Date.valueOf(p.getPurchase_order_expected_date())).getResultList();
			for (PurchaseId x : s) {
				System.out.println(s.toString());
			}
			return s;
		}

	}

	@Transactional
	public List<PurchaseReturnId> getPurchaseReturnsList(PurchasesReturnFilter p) {
		if (p.getVendor_id() == 0 && p.getPurchase_return_date() == null && p.getGrn_value() == 0) {
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s",
					PurchaseReturnId.class).getResultList();
			for (PurchaseReturnId x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_return_date() == null && p.getGrn_value() == 0) {
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.vendor_id=:v",
					PurchaseReturnId.class).setParameter("v", p.getVendor_id()).getResultList();
			for (PurchaseReturnId x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_return_date() != null && p.getGrn_value() == 0) {
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.vendor_id=:v and s.purchase_return_date=:v1",
					PurchaseReturnId.class).setParameter("v", p.getVendor_id())
					.setParameter("v1", Date.valueOf(p.getPurchase_return_date())).getResultList();
			for (PurchaseReturnId x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_return_date() == null && p.getGrn_value() != 0) {
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.vendor_id=:v and s.grn_cost=:v1",
					PurchaseReturnId.class).setParameter("v", p.getVendor_id())
					.setParameter("v1", BigDecimal.valueOf(p.getGrn_value())).getResultList();
			return s;
		} else if (p.getVendor_id() == 0 && p.getPurchase_return_date() != null && p.getGrn_value() == 0) {
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.purchase_return_date=:v1",
					PurchaseReturnId.class).setParameter("v1", Date.valueOf(p.getPurchase_return_date()))
					.getResultList();
			return s;
		} else if (p.getVendor_id() == 0 && p.getPurchase_return_date() == null && p.getGrn_value() != 0) {
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.grn_cost=:v1",
					PurchaseReturnId.class).setParameter("v1", BigDecimal.valueOf(p.getGrn_value())).getResultList();
			return s;
		} else if (p.getVendor_id() == 0 && p.getPurchase_return_date() != null && p.getGrn_value() != 0) {
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.procurementModels.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.grn_cost=:v1 and s.purchase_return_date=:v2",
					PurchaseReturnId.class).setParameter("v1", BigDecimal.valueOf(p.getGrn_value()))
					.setParameter("v2", Date.valueOf(p.getPurchase_return_date())).getResultList();
			return s;
		} else {
			System.out.println(p.getPurchase_return_date());
			List<PurchaseReturnId> s = em.createQuery(

					"SELECT new main.models.procurementModels.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.vendor_id=:v and s.purchase_return_date=:v1 and s.grn_cost=:v2",
					PurchaseReturnId.class).setParameter("v", p.getVendor_id())
					.setParameter("v1", Date.valueOf(p.getPurchase_return_date()))
					.setParameter("v2", BigDecimal.valueOf(p.getGrn_value())).getResultList();
			for (PurchaseReturnId x : s) {
				System.out.println(s.toString());
			}
			return s;
		}
	}

	// s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description
	@Transactional
	public List<PurchaseReturnOutput> getPurchaseReturnsList2(PurchasesReturnFilter p) {
		if (p.getVendor_id() == 0 && p.getPurchase_return_date() == null && p.getGrn_value() == 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s",
					PurchaseReturnOutput.class).getResultList();
			for (PurchaseReturnOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_return_date() == null && p.getGrn_value() == 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.vendor_id=:v",
					PurchaseReturnOutput.class).setParameter("v", p.getVendor_id()).getResultList();
			for (PurchaseReturnOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_return_date() != null && p.getPurchase_return_date1() == null
				&& p.getGrn_value() == 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.vendor_id=:v and s.purchase_return_date>=:v1",
					PurchaseReturnOutput.class).setParameter("v", p.getVendor_id())
					.setParameter("v1", Date.valueOf(p.getPurchase_return_date())).getResultList();
			for (PurchaseReturnOutput x : s) {
				System.out.println(s.toString());
			}

			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_return_date() != null && p.getPurchase_return_date1() != null
				&& p.getGrn_value() == 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.vendor_id=:v and s.purchase_return_date>=:v1 and s.purchase_return_date<=:v2",
					PurchaseReturnOutput.class).setParameter("v", p.getVendor_id())
					.setParameter("v2", Date.valueOf(p.getPurchase_return_date1()))
					.setParameter("v1", Date.valueOf(p.getPurchase_return_date())).getResultList();
			for (PurchaseReturnOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		}

		else if (p.getVendor_id() != 0 && p.getPurchase_return_date() == null && p.getGrn_value() != 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.vendor_id=:v and s.grn_cost=:v1",
					PurchaseReturnOutput.class).setParameter("v", p.getVendor_id())
					.setParameter("v1", BigDecimal.valueOf(p.getGrn_value())).getResultList();
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_return_date() != null && p.getPurchase_return_date1() == null
				&& p.getGrn_value() != 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.vendor_id=:v and s.grn_cost=:v1 and s.purchase_return_date>=:v2",
					PurchaseReturnOutput.class).setParameter("v1", BigDecimal.valueOf(p.getGrn_value()))
					.setParameter("v", p.getVendor_id()).setParameter("v2", Date.valueOf(p.getPurchase_return_date()))
					.getResultList();
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_return_date() != null && p.getPurchase_return_date1() != null
				&& p.getGrn_value() != 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.vendor_id=:v and s.grn_cost=:v1 and s.purchase_return_date>=:v2 and s.purchase_return_date<=:v3",
					PurchaseReturnOutput.class).setParameter("v1", BigDecimal.valueOf(p.getGrn_value()))
					.setParameter("v", p.getVendor_id()).setParameter("v3", Date.valueOf(p.getPurchase_return_date1()))
					.setParameter("v2", Date.valueOf(p.getPurchase_return_date())).getResultList();
			return s;
		}

		else if (p.getVendor_id() == 0 && p.getPurchase_return_date() != null && p.getPurchase_return_date1() != null
				&& p.getGrn_value() == 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.purchase_return_date>=:v1 and s.purchase_return_date<=:v2",
					PurchaseReturnOutput.class).setParameter("v1", Date.valueOf(p.getPurchase_return_date()))
					.setParameter("v2", Date.valueOf(p.getPurchase_return_date1())).getResultList();
			return s;
		} else if (p.getVendor_id() == 0 && p.getPurchase_return_date() != null && p.getPurchase_return_date1() == null
				&& p.getGrn_value() == 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.purchase_return_date>=:v1",
					PurchaseReturnOutput.class).setParameter("v1", Date.valueOf(p.getPurchase_return_date()))
					.getResultList();
			return s;
		}

		else if (p.getVendor_id() == 0 && p.getPurchase_return_date() == null && p.getGrn_value() != 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id) from ImPurchaseReturn s where s.grn_cost=:v1",
					PurchaseReturnOutput.class).setParameter("v1", BigDecimal.valueOf(p.getGrn_value()))
					.getResultList();
			return s;
		} else if (p.getVendor_id() == 0 && p.getPurchase_return_date() != null && p.getPurchase_return_date1() == null
				&& p.getGrn_value() != 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.grn_cost=:v1 and s.purchase_return_date>=:v2",
					PurchaseReturnOutput.class).setParameter("v1", BigDecimal.valueOf(p.getGrn_value()))
					.setParameter("v2", Date.valueOf(p.getPurchase_return_date())).getResultList();
			return s;
		} else if (p.getVendor_id() == 0 && p.getPurchase_return_date() != null && p.getPurchase_return_date1() != null
				&& p.getGrn_value() != 0) {
			List<PurchaseReturnOutput> s = em.createQuery(
					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.grn_cost=:v1 and s.purchase_return_date>=:v2 and s.purchase_return_date<=:v3",
					PurchaseReturnOutput.class).setParameter("v1", BigDecimal.valueOf(p.getGrn_value()))
					.setParameter("v3", p.getPurchase_return_date1())
					.setParameter("v2", Date.valueOf(p.getPurchase_return_date())).getResultList();
			return s;
		}

		else {
			System.out.println(p.getPurchase_return_date());
			List<PurchaseReturnOutput> s = em.createQuery(

					"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s ",
					PurchaseReturnOutput.class).getResultList();
			for (PurchaseReturnOutput x : s) {
				System.out.println(s.toString());
			}
			return s;
		}

	}

	@Transactional
	public PurchaseReturnOutput getPurchaseReturnsList3(PurchaseReturnId p) {
		PurchaseReturnOutput s = em.createQuery(
				"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnOutput(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from ImPurchaseReturn s where s.purchase_return_id=:v2",
				PurchaseReturnOutput.class).setParameter("v2", p.getPurchase_return_id()).getSingleResult();
		System.out.println(s.toString());
		return s;
	}

	@Transactional
	public List<PurchaseReturnJoinClass> getPurchaseReturnProducts(PurchaseReturnId x) {
		ImPurchaseReturn s = (ImPurchaseReturn) em
				.createQuery("SELECT s from ImPurchaseReturn s where s.purchase_return_id=:x")
				.setParameter("x", x.getPurchase_return_id()).getSingleResult();

		List<PurchaseReturnJoinClass> l = new ArrayList<>();
		List<ImPurchaseReturnProduct> m = s.getPurchaseReturnProducts();
		for (int i = 0; i < m.size(); i++)

		{
			System.out.println(((ImPurchaseReturn) s).toString());
			l.add(new PurchaseReturnJoinClass(s, m.get(i)));
		}
		return l;

	}

	@Transactional
	public List<PurchaseJoinClass> getPurchaseProducts(PurchaseId x) {
		List<Object> s = em.createQuery("SELECT s from Im_Purchase_Order s where s.purchase_order_id=:x")
				.setParameter("x", x.getPurchase_order_id()).getResultList();

		List<PurchaseJoinClass> l = new ArrayList<>();
		ArrayList<Im_Purchase_Order_Products> m = new ArrayList<>();

		for (Object q : s) {

			List<Im_Purchase_Order_Products> j = ((Im_Purchase_Order) q).getChildren();

			System.out.println(q);

			for (int i = 0; i < j.size(); i++)

			{
				System.out.println(((Im_Purchase_Order) q).toString());
				l.add(new PurchaseJoinClass((Im_Purchase_Order) q, j.get(i)));
			}
			break;

		}
		return l;
	}

	@Transactional
	public Im_Purchase_Order persistpurchase(Im_Purchase_Order stud) {
		try {
			em.persist(stud);
			List<Im_Purchase_Order_Products> p = stud.getChildren();
			for (Im_Purchase_Order_Products x : p) {
				System.out.println("hello");
				System.out.println(stud.getPurchase_order_id());
				x.setPurchase_order_id(stud.getPurchase_order_id());
				System.out.println(x.toString());
				em.persist(x);

			}
			return stud;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return stud;

	}

	@Transactional
	public ImPurchaseReturn persistpurchasereturn(ImPurchaseReturn stud, ProductInputMapping pm) {
		try {
			em.persist(stud);
			List<ImPurchaseReturnProduct> p = stud.getPurchaseReturnProducts();
			for (ImPurchaseReturnProduct x : p) {
				System.out.println("hello");
				System.out.println(stud.getPurchase_return_id());
				x.setPurchase_return_id(stud.getPurchase_return_id());
				System.out.println(x.toString());
				em.persist(x);
			}
			List<ProductInput> pi = pm.getPi();
			for (ProductInput y : pi) {
				im_products_stock ip = (im_products_stock) em
						.createQuery("select s from im_products_stock s where s.product_id=:x1 and s.batch_no=:x2")
						.setParameter("x1", y.getProduct_id()).setParameter("x2", y.getBatch_no()).getSingleResult();
				int pstock = ip.getProduct_stock() - y.getQuantity();
				ip.setProduct_stock(pstock);
			}
			return stud;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return stud;

	}

	@Transactional
	public List<joinclass> getAllData() {
		ArrayList<Object[]> s = (ArrayList<Object[]>) em
				.createQuery("SELECT s, p FROM im_products s JOIN im_products_stock p ON s.product_id = p.product_id")
				.getResultList();
		List<joinclass> l = new ArrayList<joinclass>();
		for (Object[] x : s) {
			for (int i = 0; i < x.length; i++) {
				if (i == 0) {
					r = (im_products) x[i];
					System.out.println(r.toString());
					System.out.println("a ra babu");
				} else {
					t = (im_products_stock) x[i];
					System.out.println(t.toString());
				}
			}
			joinclass jc2=applicationContext.getBean(joinclass.class);
			jc2.setProduct(r);
			jc2.setStock(t);
			l.add(jc2);
		}

		return l;

	}

	@Transactional
	public List<JoinClass2> getProductsCount() {
		ArrayList<Object[]> s = (ArrayList<Object[]>) em
				.createQuery("SELECT s, p FROM im_products s JOIN im_products_stock p ON s.product_id = p.product_id")
				.getResultList();

		List<JoinClass2> l = new ArrayList<JoinClass2>();
		for (Object[] x : s) {
			System.out.println(x.length);

			for (int i = 0; i < x.length; i++) {
				if (i == 0) {
					r = (im_products) x[i];
					r1 = applicationContext.getBean(ProductName.class);
					r1.setProduct_name(r.getProduct_name());
					System.out.println(r1.toString());
					System.out.println("a ra babu");
				} else {
					t = (im_products_stock) x[i];
					t1 = applicationContext.getBean(productquant.class);
					t1.setProduct_stock(t.getProduct_stock());

					System.out.println(t1.toString());
				}
			}
			JoinClass2 jc2=applicationContext.getBean(JoinClass2.class);
			jc2.setProduct(r1);
			jc2.setStock(t1);
			l.add(jc2);
		}

		return l;

	}

	@Transactional
	public ArrayList<TotalStock> getTotalStock() {
		System.out.println("hello");
		ArrayList<TotalStock> ts = (ArrayList<TotalStock>) em
				.createQuery("SELECT s FROM TotalStock s ", TotalStock.class).getResultList();
		return ts;
	}

	@Transactional
	public ProductCategoryCount getCategoriesCount() {
		ProductCategoryCount val = (ProductCategoryCount) em.createQuery("select new main.models.warehouseModels.outputmodels.ProductCategoryCount(count(*)) from  ProductCategories s").getSingleResult();
		return val;

	}

	@Transactional
	public TotalWarehouseVal getWarehouseValue() {
		TotalWarehouseVal val = (TotalWarehouseVal) em.createQuery("select new main.models.warehouseModels.outputmodels.TotalWarehouseVal(sum(s.product_cost)) from im_products_stock  s").getSingleResult();
		return val;

	}

	@Transactional
	public VendorCount getVendorsCount() {
		VendorCount v =  (VendorCount) em.createQuery("select new main.models.warehouseModels.outputmodels.VendorCount(count(*)) from Vendor  s",VendorCount.class).getSingleResult();
		return v;

	}

	@Transactional
	public UserOutput check(MailDetails m) {
		Query q = em.createQuery("select s from User s where s.userName=:email", User.class).setParameter("email",
				m.getMail());
		System.out.println(m.getMail());
		User s = (User) q.getSingleResult();
		ModelMapper mp=new ModelMapper();
		UserOutput s1=mp.map(s, UserOutput.class);
		System.out.println(s.toString());
		return s1;
	}

	@Transactional
	public void getData(MailDetails m, String num) {
		Query q = em.createQuery("select s from User s where s.userName=:email", User.class).setParameter("email",
				m.getMail());
		User s = (User) q.getSingleResult();
		s.setOtp(num);
		s.setOtpExpiryTime((LocalDateTime.now().plusMinutes(2)));
		System.out.println(s.toString());

	}

	@Transactional
	public UserOutput getRow(password p) {
		Query q = em.createQuery("select s from User s where s.userName=:email", User.class).setParameter("email",
				p.getMail());
		User s = (User) q.getSingleResult();
		ModelMapper mp=new ModelMapper();
		UserOutput s1=mp.map(s, UserOutput.class);

		System.out.println(s.toString());
		return s1;

	}

	@Transactional
	public void getRow2(password p) {
		Query q = em.createQuery("select s from User s where s.userName=:email", User.class).setParameter("email",
				p.getMail());
		User s = (User) q.getSingleResult();
		s.setUserPassword(p.getPass());
		System.out.println(p.getPass());
		System.out.println(s.toString());

	}

	@Transactional
	public UserOutput getAuthent(credentials2 s) {
		Query q = em.createQuery(
				"select s from User s where s.userName=:email and s.userPassword=:password and s.userType=:usertype",
				User.class).setParameter("email", s.getUsername()).setParameter("password", s.getPassword())
				.setParameter("usertype", s.getUser_type());
		User ud = (User) q.getSingleResult();
		ModelMapper mp=new ModelMapper();
		UserOutput s1=mp.map(ud, UserOutput.class);
		System.out.println(ud.toString());
		return s1;

	}

}
