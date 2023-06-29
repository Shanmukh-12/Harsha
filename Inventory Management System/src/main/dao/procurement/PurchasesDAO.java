package main.dao.procurement;

import java.util.ArrayList;
import java.util.List;

import main.models.procurementModels.dtomodels.PurchaseOrdersDTO;
import main.models.procurementModels.dtomodels.PurchaseReturnDTO;
import main.models.procurementModels.inputmodels.PagenationDetails;
import main.models.procurementModels.inputmodels.ProductInputMapping;
import main.models.procurementModels.inputmodels.PurchaseId;
import main.models.procurementModels.inputmodels.PurchaseReturnId;
import main.models.procurementModels.inputmodels.PurchasesFilter;
import main.models.procurementModels.inputmodels.PurchasesReturnFilter;
import main.models.procurementModels.outputmodels.PurchaseInformation;
import main.models.procurementModels.outputmodels.PurchaseOrderDetails;
import main.models.procurementModels.outputmodels.PurchaseReturnDetails;
import main.models.purchaseOrder.entityModels.PurchaseOrder;
import main.models.purchaseReturns.entityModels.PurchaseReturn;
import main.models.warehouseModels.dtomodels.ProductDetailsDTO;
import main.models.warehouseModels.dtomodels.ProductsDataDTO;
import main.models.warehouseModels.outputmodels.ProductCategoryCount;
import main.models.warehouseModels.outputmodels.TotalStock;
import main.models.warehouseModels.outputmodels.TotalWarehouseVal;
import main.models.warehouseModels.outputmodels.VendorCount;

public interface PurchasesDAO {

	public List<PurchaseReturnDTO> getPurchaseReturnProducts(PurchaseReturnId x);

	public List<PurchaseOrdersDTO> getPurchaseProducts(PurchaseId x);

	public void persistPurchase(PurchaseOrder stud);

	public void persistPurchaseReturn(PurchaseReturn stud, ProductInputMapping pm);

	public List<ProductsDataDTO> getDashboardData();

	public List<ProductDetailsDTO> getProductsCount();

	public ArrayList<TotalStock> getTotalStock();

	public ProductCategoryCount getCategoriesCount();

	public TotalWarehouseVal getWarehouseValue();

	public VendorCount getVendorsCount();

	public PurchaseInformation getAllPurchaseOrders(PagenationDetails p);

	public List<PurchaseOrderDetails> getPurchaseOrdersByVendor(PurchasesFilter p);

	public List<PurchaseOrderDetails> getPurchaseOrdersByVendorAndDateRange(PurchasesFilter p);

	public List<PurchaseOrderDetails> getPurchaseOrdersByVendorAndStartDate(PurchasesFilter p);

	public List<PurchaseOrderDetails> getPurchaseOrdersByDateRange(PurchasesFilter p);

	public List<PurchaseOrderDetails> getPurchaseOrdersByStartDate(PurchasesFilter p);

	public List<PurchaseOrderDetails> getPurchaseOrdersByEndDate(PurchasesFilter p);

	public PurchaseOrderDetails getPurchaseOrderDetailsById(PurchaseId p);

	public PurchaseReturnDetails getPurchaseReturnsListDetailsById(PurchaseReturnId p);

	public List<PurchaseReturnDetails> getDefaultPurchaseReturns();

	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorId(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorAndStartDate(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorAndDateRange(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorAndGrnCost(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorGrnCostAndStartDate(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorGrnCostAndDateRange(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByDateRange(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByStartDate(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByGrnCost(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByGrnCostAndStartDate(PurchasesReturnFilter p);

	public List<PurchaseReturnDetails> getPurchaseReturnsByGrnCostAndDateRange(PurchasesReturnFilter p);

}
