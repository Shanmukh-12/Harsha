package main.dao.procurement;

import java.util.ArrayList;
import java.util.List;

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
import main.models.userModels.outputModels.UserOutput;
import main.models.warehouseModels.dtomodels.JoinClass2;
import main.models.warehouseModels.dtomodels.joinclass;
import main.models.warehouseModels.outputmodels.ProductCategoryCount;
import main.models.warehouseModels.outputmodels.TotalStock;
import main.models.warehouseModels.outputmodels.TotalWarehouseVal;
import main.models.warehouseModels.outputmodels.VendorCount;

public interface ProcurementDAO {

	public boolean persist(User stud);

	public List<PurchaseId> getPurchaseId(PurchasesFilter p);

	public List<PurchaseReturnId> getPurchaseReturnsList(PurchasesReturnFilter p);

	public List<PurchaseReturnJoinClass> getPurchaseReturnProducts(PurchaseReturnId x);

	public List<PurchaseJoinClass> getPurchaseProducts(PurchaseId x);

	public Im_Purchase_Order persistpurchase(Im_Purchase_Order stud);

	public ImPurchaseReturn persistpurchasereturn(ImPurchaseReturn stud, ProductInputMapping pm);

	public List<joinclass> getAllData();

	public List<JoinClass2> getProductsCount();

	public ArrayList<TotalStock> getTotalStock();

	public ProductCategoryCount getCategoriesCount();

	public TotalWarehouseVal getWarehouseValue();

	public VendorCount getVendorsCount();

	public UserOutput check(MailDetails m);

	public void getData(MailDetails m, String num);

	public UserOutput getRow(password p);

	public void getRow2(password p);

	public UserOutput getAuthent(credentials2 s);

	public List<ImPurchaseOrderOutput> getPurchaseId2(PurchasesFilter p);

	public List<PurchaseReturnOutput> getPurchaseReturnsList2(PurchasesReturnFilter p);

	public ImPurchaseOrderOutput getPurchaseId3(PurchaseId p);

	public PurchaseReturnOutput getPurchaseReturnsList3(PurchaseReturnId p);

}
