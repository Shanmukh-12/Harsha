package main.service.procurement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import main.bll.procurement.TotalStockBLL;
import main.dao.procurement.ProcurementDAO;
import main.models.loginModel.inputModels.MailDetails;
import main.models.loginModel.inputModels.credentials2;
import main.models.loginModel.inputModels.password;
import main.models.procurementModels.dtomodels.PurchaseJoinClass;
import main.models.procurementModels.dtomodels.PurchaseReturnJoinClass;
import main.models.procurementModels.inputmodels.ProductInputMapping;
import main.models.procurementModels.inputmodels.PurchaseId;
import main.models.procurementModels.inputmodels.PurchaseReturnId;
import main.models.procurementModels.inputmodels.PurchasesFilter;
import main.models.procurementModels.inputmodels.PurchasesReturnFilter;
import main.models.procurementModels.outputmodels.ImPurchaseOrderOutput;
import main.models.procurementModels.outputmodels.PurchaseReturnOutput;
import main.models.purchaseOrder.entityModels.Im_Purchase_Order;
import main.models.purchaseReturns.entityModels.ImPurchaseReturn;
import main.models.userModels.entities.User;
import main.models.warehouseModels.dtomodels.JoinClass2;
import main.models.warehouseModels.dtomodels.joinclass;
import main.models.warehouseModels.outputmodels.ProductCategoryCount;
import main.models.warehouseModels.outputmodels.TotalOverallStock;
import main.models.warehouseModels.outputmodels.TotalStock;
import main.models.warehouseModels.outputmodels.TotalWarehouseVal;
import main.models.warehouseModels.outputmodels.VendorCount;

@Component
public class ProcurementService {
	@Autowired
	@Qualifier("ProcurementDAO")
	ProcurementDAO sd;
	@Autowired
	VendorCount vc;
	@Autowired
	TotalStockBLL tb;
	@Autowired
	ProductCategoryCount pcc;
	@Autowired
	TotalWarehouseVal twv;
	@Autowired
	TotalOverallStock tsc;

	@Autowired
	public ProcurementService(@Qualifier("ProcurementDAO") ProcurementDAO sd) {
		super();
		this.sd = sd;
	}

	public void persistpurchase(Im_Purchase_Order stud) {
		Im_Purchase_Order l = sd.persistpurchase(stud);
		System.out.println(stud.getPurchase_order_id());

	}

	public void persistpurchasereturn(ImPurchaseReturn stud, ProductInputMapping pm) {
		ImPurchaseReturn l = sd.persistpurchasereturn(stud, pm);
		System.out.println(l.getPurchase_return_id());

	}

	public List<PurchaseJoinClass> getPurchaseProducts(PurchaseId x) {
		List<PurchaseJoinClass> l = sd.getPurchaseProducts(x);

		return l;

	}

	public List<PurchaseReturnJoinClass> getPurchaseReturnProducts(PurchaseReturnId x) {
		List<PurchaseReturnJoinClass> l = sd.getPurchaseReturnProducts(x);
		return l;

	}

	public List<PurchaseReturnId> getPurchaseReturnsList(PurchasesReturnFilter p) {

		return sd.getPurchaseReturnsList(p);
	}

	public List<PurchaseId> getPurchaseId(PurchasesFilter p) {
		List<PurchaseId> s = sd.getPurchaseId(p);
		for (PurchaseId x : s) {
			System.out.println(s.toString());
		}
		return s;

	}

	public TotalOverallStock getTotalStock() {
		ArrayList<TotalStock> ts = sd.getTotalStock();

		tsc.setTotal_product_stock(tb.getTotalStockQuantity(ts));
		TotalOverallStock tsc2 = tsc;
		return tsc2;

	}

	public List<PurchaseReturnOutput> getPurchaseReturnsList2(PurchasesReturnFilter p) {
		List<PurchaseReturnOutput> l = sd.getPurchaseReturnsList2(p);
		System.out.println(l.toString());
		return l;
	}

	public List<ImPurchaseOrderOutput> getPurchaseId2(PurchasesFilter p) {
		List<ImPurchaseOrderOutput> l = sd.getPurchaseId2(p);
		System.out.println(l.toString());
		return l;
	}

	public List<JoinClass2> getProductsCount() {

		return sd.getProductsCount();

	}

	public TotalWarehouseVal getWarehouseValue() {
		double val = sd.getWarehouseValue();
		twv.setTotal_warehouse_value(val);
		return twv;

	}

	public ProductCategoryCount getCategoriesCount() {
		pcc.setTotal_product_category_count(sd.getCategoriesCount());
		System.out.println(pcc.toString());
		return pcc;

	}

	public VendorCount getVendorsCount() {

		vc.setVendorcount(sd.getVendorsCount());
		return vc;

	}

	public boolean add(User s) {
		return sd.persist(s);
	}

	public List<joinclass> getAllData() {
		return sd.getAllData();
	}

	public boolean check(MailDetails m) {
		try {
			User s = sd.check(m);
		} catch (NoResultException e) {
			// No entity found, return false
			return false;
		}

		return true;

	}

	public String getAuthent(credentials2 c) {
		try {
			User s = sd.getAuthent(c);
		} catch (NoResultException e) {
			// No entity found, return false
			return "login failed";
		}

		return "login success";
	}

	public void getDat(MailDetails m, String num) {
		System.out.println("hello");
		sd.getData(m, num);
		System.out.println("hello");
	}

	public User getRow(password m) {
		return sd.getRow(m);
	}

	public void getRow2(password p) {
		sd.getRow2(p);

	}

	public ImPurchaseOrderOutput getPurchaseId3(PurchaseId p) {
		ImPurchaseOrderOutput s = sd.getPurchaseId3(p);

		System.out.println(s.toString());

		return s;

	}

	public PurchaseReturnOutput getPurchaseReturnsList3(PurchaseReturnId p) {
		PurchaseReturnOutput s = sd.getPurchaseReturnsList3(p);
		System.out.println(s.toString());
		return s;
	}

	// public List<PurchaseId> getPurchaseReturnsList() {
	//
	// }

}