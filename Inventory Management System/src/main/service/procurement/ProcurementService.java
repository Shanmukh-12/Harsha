package main.service.procurement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import main.bll.procurement.TotalStockBLL;
import main.dao.procurement.PurchasesDAO;
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
import main.models.warehouseModels.outputmodels.TotalOverallStock;
import main.models.warehouseModels.outputmodels.TotalStock;
import main.models.warehouseModels.outputmodels.TotalWarehouseVal;
import main.models.warehouseModels.outputmodels.VendorCount;

@Component
public class ProcurementService {
	@Autowired
	@Qualifier("PurchasesDAO")
	PurchasesDAO procurementService;
	@Autowired
	VendorCount vendorCount;
	@Autowired
	TotalStockBLL totalStockBLL;
	@Autowired
	ProductCategoryCount categoryCount;
	@Autowired
	TotalWarehouseVal totalWarehouseValue;
	@Autowired
	TotalOverallStock totalStocks;

	// to create new purchase orders
	public void persistPurchase(PurchaseOrder purchase) {
		procurementService.persistPurchase(purchase);
		// Persist the purchase order

	}

	// to create new purchase returns
	public void persistPurchaseReturn(PurchaseReturn purchaseReturn, ProductInputMapping productInputDetails) {
		procurementService.persistPurchaseReturn(purchaseReturn, productInputDetails);
		// Persist the purchase return along with the product input mapping

	}

	// to get products under purchase id
	public List<PurchaseOrdersDTO> getPurchaseProducts(PurchaseId purchaseId) {
		List<PurchaseOrdersDTO> PurchaseProductDetails = procurementService.getPurchaseProducts(purchaseId);
		return PurchaseProductDetails;// Returns the purchase products based on the purchase ID

	}

	// to get products under purchase return id
	public List<PurchaseReturnDTO> getPurchaseReturnProducts(PurchaseReturnId x) {
		List<PurchaseReturnDTO> PurchaseReturnProductDetails = procurementService.getPurchaseReturnProducts(x);
		return PurchaseReturnProductDetails;// Returns the purchase return products based on the purchase ID

	}

	// to get overall stock quantity
	public TotalOverallStock getTotalStock() {
		ArrayList<TotalStock> totalStockValue = procurementService.getTotalStock();
		totalStocks.setTotal_product_stock(totalStockBLL.getTotalStockQuantity(totalStockValue));
		// Retrieve the total stock value and calculate the total product stock quantity
		TotalOverallStock totalStock = totalStocks;
		return totalStock;

	}

	// to get total count of products in warehouse
	public List<ProductDetailsDTO> getProductsCount() {

		return procurementService.getProductsCount(); // returns the count of products

	}

	// gets the total value of products in warehouse
	public TotalWarehouseVal getWarehouseValue() {
		totalWarehouseValue = procurementService.getWarehouseValue();
		return totalWarehouseValue;

	}

	// gives total number of categories in warehouse
	public ProductCategoryCount getCategoriesCount() {
		categoryCount = procurementService.getCategoriesCount();
		return categoryCount; // returns category count

	}

	// gives count of total number of vendors
	public VendorCount getVendorsCount() {
		VendorCount vendorCount = procurementService.getVendorsCount();
		return vendorCount;// return vendor count

	}

	// data for dashboard
	public List<ProductsDataDTO> getDashboardData() {
		return procurementService.getDashboardData();// returns all the data required for dashboard
	}

	public PurchaseOrderDetails getPurchaseOrderDetailsById(PurchaseId purchaseId) {
		PurchaseOrderDetails purchaseDetails = procurementService.getPurchaseOrderDetailsById(purchaseId);
		return purchaseDetails; // Retrieve the purchase order details by ID and returns

	}

	public PurchaseReturnDetails getPurchaseReturnsListDetailsById(PurchaseReturnId purchaseReturnId) {
		PurchaseReturnDetails purchaseReturnDetails = procurementService
				.getPurchaseReturnsListDetailsById(purchaseReturnId);
		return purchaseReturnDetails; // Retrieve the purchase return details by ID and returns

	}

	// retreives data based on filters entered
	public PurchaseInformation getPurchaseIdDetails(PurchasesFilter purchasesFilter, PagenationDetails p) {
		if (purchasesFilter.getVendor_id() == 0 && purchasesFilter.getPurchase_order_expected_date() == null
				&& purchasesFilter.getPurchase_order_expected_date1() == null) {
			return procurementService.getAllPurchaseOrders(p);
			// If no vendor ID and no date range specified, retrieve all purchase orders
		}

		// else if (purchasesFilter.getVendor_id() != 0 && purchasesFilter.getPurchase_order_expected_date() == null)
		// {
		// return procurementService.getPurchaseOrdersByVendor(purchasesFilter);
		// // If vendor ID is specified but no date range, retrieve purchase orders by vendor
		// } else if (purchasesFilter.getVendor_id() != 0 && purchasesFilter.getPurchase_order_expected_date() != null
		// && purchasesFilter.getPurchase_order_expected_date1() != null) {
		// return procurementService.getPurchaseOrdersByVendorAndDateRange(purchasesFilter);
		// // If vendor ID and date range are specified, retrieve purchase orders by vendor and date range
		// } else if (purchasesFilter.getVendor_id() != 0 && purchasesFilter.getPurchase_order_expected_date() != null
		// && purchasesFilter.getPurchase_order_expected_date1() == null) {
		// return procurementService.getPurchaseOrdersByVendorAndStartDate(purchasesFilter);
		// // If vendor ID and start date are specified, retrieve purchase orders by vendor and start date
		// } else if (purchasesFilter.getVendor_id() == 0 && purchasesFilter.getPurchase_order_expected_date() != null
		// && purchasesFilter.getPurchase_order_expected_date1() != null) {
		// return procurementService.getPurchaseOrdersByDateRange(purchasesFilter);
		// // If no vendor ID and date range is specified, retrieve purchase orders by date range
		// } else if (purchasesFilter.getVendor_id() == 0 && purchasesFilter.getPurchase_order_expected_date() != null
		// && purchasesFilter.getPurchase_order_expected_date1() == null) {
		// return procurementService.getPurchaseOrdersByStartDate(purchasesFilter);
		// // If no vendor ID and start date are specified, retrieve purchase orders by start date
		// } else if (purchasesFilter.getVendor_id() == 0 && purchasesFilter.getPurchase_order_expected_date() == null
		// && purchasesFilter.getPurchase_order_expected_date1() != null) {
		// return procurementService.getPurchaseOrdersByEndDate(purchasesFilter);
		// // If no vendor ID and end date are specified, retrieve purchase orders by end date
		else {
			return procurementService.getAllPurchaseOrders(p);
			// If none of the above conditions are met, retrieve all purchase orders
		}
	}

	public List<PurchaseReturnDetails> getPurchaseReturnsListDetails(PurchasesReturnFilter purchasesReturnFilter) {
		if (purchasesReturnFilter.getVendor_id() == 0 && purchasesReturnFilter.getPurchase_return_date() == null
				&& purchasesReturnFilter.getGrn_value() == 0) {
			return procurementService.getDefaultPurchaseReturns();
			// If no vendor ID, return date, and GRN value specified, retrieve default purchase returns
		} else if (purchasesReturnFilter.getVendor_id() != 0 && purchasesReturnFilter.getPurchase_return_date() == null
				&& purchasesReturnFilter.getGrn_value() == 0) {
			return procurementService.getPurchaseReturnsByVendorId(purchasesReturnFilter);
			// If vendor ID is specified but no return date and GRN value, retrieve purchase returns by vendor ID
		} else if (purchasesReturnFilter.getVendor_id() != 0 && purchasesReturnFilter.getPurchase_return_date() != null
				&& purchasesReturnFilter.getPurchase_return_date1() == null
				&& purchasesReturnFilter.getGrn_value() == 0) {
			return procurementService.getPurchaseReturnsByVendorAndStartDate(purchasesReturnFilter);
			// If vendor ID and start date are specified but no return date and GRN value, retrieve purchase returns by
			// vendor ID and start date
		} else if (purchasesReturnFilter.getVendor_id() != 0 && purchasesReturnFilter.getPurchase_return_date() != null
				&& purchasesReturnFilter.getPurchase_return_date1() != null
				&& purchasesReturnFilter.getGrn_value() == 0) {
			return procurementService.getPurchaseReturnsByVendorAndDateRange(purchasesReturnFilter);
			// If vendor ID, start date, and end date are specified but no GRN value, retrieve purchase returns by
			// vendor ID and date range
		} else if (purchasesReturnFilter.getVendor_id() != 0 && purchasesReturnFilter.getPurchase_return_date() == null
				&& purchasesReturnFilter.getGrn_value() != 0) {
			return procurementService.getPurchaseReturnsByVendorAndGrnCost(purchasesReturnFilter);
			// If vendor ID and GRN value are specified but no return date, retrieve purchase returns by vendor ID and
			// GRN value
		} else if (purchasesReturnFilter.getVendor_id() != 0 && purchasesReturnFilter.getPurchase_return_date() != null
				&& purchasesReturnFilter.getPurchase_return_date1() == null
				&& purchasesReturnFilter.getGrn_value() != 0) {
			return procurementService.getPurchaseReturnsByVendorGrnCostAndStartDate(purchasesReturnFilter);
			// If vendor ID, start date, and GRN value are specified but no return date, retrieve purchase returns by
			// vendor ID, start date, and GRN value
		} else if (purchasesReturnFilter.getVendor_id() != 0 && purchasesReturnFilter.getPurchase_return_date() != null
				&& purchasesReturnFilter.getPurchase_return_date1() != null
				&& purchasesReturnFilter.getGrn_value() != 0) {
			return procurementService.getPurchaseReturnsByVendorGrnCostAndDateRange(purchasesReturnFilter);
			// If vendor ID, start date, end date, and GRN value are specified, retrieve purchase returns by vendor ID,
			// date range, and GRN value
		} else if (purchasesReturnFilter.getVendor_id() == 0 && purchasesReturnFilter.getPurchase_return_date() != null
				&& purchasesReturnFilter.getPurchase_return_date1() != null
				&& purchasesReturnFilter.getGrn_value() == 0) {
			return procurementService.getPurchaseReturnsByDateRange(purchasesReturnFilter);
			// If no vendor ID, GRN value, and end date are specified but return date is specified, retrieve purchase
			// returns by date range
		} else if (purchasesReturnFilter.getVendor_id() == 0 && purchasesReturnFilter.getPurchase_return_date() != null
				&& purchasesReturnFilter.getPurchase_return_date1() == null
				&& purchasesReturnFilter.getGrn_value() == 0) {
			return procurementService.getPurchaseReturnsByStartDate(purchasesReturnFilter);
			// If no vendor ID, GRN value, and start date are specified but return date is specified, retrieve purchase
			// returns by start date
		} else if (purchasesReturnFilter.getVendor_id() == 0 && purchasesReturnFilter.getPurchase_return_date() == null
				&& purchasesReturnFilter.getGrn_value() != 0) {
			return procurementService.getPurchaseReturnsByGrnCost(purchasesReturnFilter);
			// If no vendor ID, return date, and end date are specified but GRN value is specified, retrieve purchase
			// returns by GRN value
		} else if (purchasesReturnFilter.getVendor_id() == 0 && purchasesReturnFilter.getPurchase_return_date() != null
				&& purchasesReturnFilter.getPurchase_return_date1() == null
				&& purchasesReturnFilter.getGrn_value() != 0) {
			return procurementService.getPurchaseReturnsByGrnCostAndStartDate(purchasesReturnFilter);
			// If no vendor ID, return date, and GRN value are specified but start date is specified, retrieve purchase
			// returns by GRN value and start date
		} else if (purchasesReturnFilter.getVendor_id() == 0 && purchasesReturnFilter.getPurchase_return_date() != null
				&& purchasesReturnFilter.getPurchase_return_date1() != null
				&& purchasesReturnFilter.getGrn_value() != 0) {
			return procurementService.getPurchaseReturnsByGrnCostAndDateRange(purchasesReturnFilter);
			// If no vendor ID, return date, and GRN value are specified but start date and end date are specified,
			// retrieve purchase returns by GRN value and date range
		} else {
			return procurementService.getDefaultPurchaseReturns();
			// If none of the above conditions are met, retrieve default purchase returns
		}
	}

}