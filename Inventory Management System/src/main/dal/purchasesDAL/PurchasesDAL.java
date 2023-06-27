package main.dal.purchasesDAL;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import main.dao.procurement.ProcurementDAO;
import main.models.procurementModels.dtomodels.PurchaseOrdersDTO;
import main.models.procurementModels.dtomodels.PurchaseReturnDTO;
import main.models.procurementModels.inputmodels.ProductInput;
import main.models.procurementModels.inputmodels.ProductInputMapping;
import main.models.procurementModels.inputmodels.PurchaseId;
import main.models.procurementModels.inputmodels.PurchaseReturnId;
import main.models.procurementModels.inputmodels.PurchasesFilter;
import main.models.procurementModels.inputmodels.PurchasesReturnFilter;
import main.models.procurementModels.outputmodels.PurchaseOrderDetails;
import main.models.procurementModels.outputmodels.PurchaseReturnDetails;
import main.models.productModels.entities.ProductsEntity;
import main.models.warehouseModels.dtomodels.ProductsDataDTO;
import main.models.productModels.entities.ProductsStockEntity;
import main.models.purchaseOrder.entityModels.PurchaseOrder;
import main.models.purchaseOrder.entityModels.PurchaseOrderProducts;
import main.models.purchaseReturns.entityModels.PurchaseReturn;
import main.models.purchaseReturns.entityModels.PurchaseReturnProducts;
import main.models.warehouseModels.dtomodels.ProductDetailsDTO;
import main.models.warehouseModels.outputmodels.ProductCategoryCount;
import main.models.warehouseModels.outputmodels.ProductName;
import main.models.warehouseModels.outputmodels.ProductQuantity;
import main.models.warehouseModels.outputmodels.TotalStock;
import main.models.warehouseModels.outputmodels.TotalWarehouseVal;
import main.models.warehouseModels.outputmodels.VendorCount;

@Component
public class PurchasesDAL implements ProcurementDAO {
	// Custom query methods, if needed
	@PersistenceContext
	EntityManager manager;
	@Autowired
	private ApplicationContext applicationContext;
	ProductQuantity productQuantity;
	ProductName productName;
	ProductsEntity productEntity;
	ProductsStockEntity productStockEntity;


	//retreives the purchase order details taking id as input
	@Transactional
	public PurchaseOrderDetails getPurchaseOrderDetailsById(PurchaseId purchaseFilter) {
		PurchaseOrderDetails purchaseDetails = manager.createQuery(
				"SELECT new main.models.procurementModels.outputmodels.PurchaseOrderDetails(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from PurchaseOrder s where s.purchase_order_id=:purchaseId",
				PurchaseOrderDetails.class).setParameter("purchaseId", purchaseFilter.getPurchase_order_id()).getSingleResult();


		return purchaseDetails;//returns purchase details based on id

	}
    
	
	//retreives the purchase order details taking vendor id and date of purchase as input
	@Transactional
	public List<PurchaseOrderDetails> getPurchaseOrdersByVendorAndDateRange(PurchasesFilter purchaseFilter) {
	    return manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseOrderDetails(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from PurchaseOrder s where s.vendor_id=:vendorId and s.purchase_order_expected_date>=:fromDate and s.purchase_order_expected_date<=:toDate",
	            PurchaseOrderDetails.class).setParameter("fromDate", Date.valueOf(purchaseFilter.getPurchase_order_expected_date()))
	            .setParameter("toDate", Date.valueOf(purchaseFilter.getPurchase_order_expected_date1()))
	            .setParameter("vendorId", purchaseFilter.getVendor_id()).getResultList(); //returns purchase details based on vendor id and along with from date,to date
	}
	
	
	//retreives the purchase order details taking vendor id and from date of purchase as input
	@Transactional
	public List<PurchaseOrderDetails> getPurchaseOrdersByVendorAndStartDate(PurchasesFilter purchaseFilter) {
	    return manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseOrderDetails(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from PurchaseOrder s where s.vendor_id=:vendorId and s.purchase_order_expected_date>=:fromDate",
	            PurchaseOrderDetails.class).setParameter("fromDate", Date.valueOf(purchaseFilter.getPurchase_order_expected_date()))
	            .setParameter("vendorId", purchaseFilter.getVendor_id()).getResultList(); //returns purchase details based on vendor id and from date
	}
	
	//retreives the purchase order details taking from date and to date of purchase
	@Transactional
	public List<PurchaseOrderDetails> getPurchaseOrdersByDateRange(PurchasesFilter purchaseFilter) {
	    return manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseOrderDetails(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from PurchaseOrder s where s.purchase_order_expected_date>=:fromDate and s.purchase_order_expected_date<=:toDate",
	            PurchaseOrderDetails.class).setParameter("fromDate", Date.valueOf(purchaseFilter.getPurchase_order_expected_date()))
	            .setParameter("toDate", Date.valueOf(purchaseFilter.getPurchase_order_expected_date1())).getResultList(); 	//returns the purchase order details based on combination of filters

	}
	
	//retreives the purchase order details taking from date only
	@Transactional
	public List<PurchaseOrderDetails> getPurchaseOrdersByStartDate(PurchasesFilter purchaseFilter) {
	    return manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseOrderDetails(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from PurchaseOrder s where s.purchase_order_expected_date>=:fromDate",
	            PurchaseOrderDetails.class).setParameter("fromDate", Date.valueOf(purchaseFilter.getPurchase_order_expected_date())) 
	            .getResultList(); //returns the purchase order details starting from from date
 
	}
	
	//retreives the purchase order details taking to date only
	@Transactional
	public List<PurchaseOrderDetails> getPurchaseOrdersByEndDate(PurchasesFilter p) {
	    return manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseOrderDetails(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from PurchaseOrder s where s.purchase_order_expected_date<=:toDate",
	            PurchaseOrderDetails.class).setParameter("toDate", Date.valueOf(p.getPurchase_order_expected_date1()))
	            .getResultList();////returns the purchase order details order before to date
	}
	
    //persist any new purchase orders from scratch or based on indents
	@Transactional
	public void persistPurchase(PurchaseOrder purchase) {
		try {
			manager.persist(purchase);//persist to purchase order table
			List<PurchaseOrderProducts> product = purchase.getChildren();//retreives all records in purchase order products related to it
			for (PurchaseOrderProducts purchaseProduct : product) {
				purchaseProduct.setPurchase_order_id(purchase.getPurchase_order_id());//sets purchase id to products records
				manager.persist(purchaseProduct);//persists products into purchases products

			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		

	}
	
    //persits the new purchase return created by user
	@Transactional
	public void persistPurchaseReturn(PurchaseReturn purchaseReturn, ProductInputMapping productDetails) {
		try {
			manager.persist(purchaseReturn);//persist to purchase return table
			List<PurchaseReturnProducts> purchaseReturnProducts = purchaseReturn.getPurchaseReturnProducts();//retreives all records in purchase order products related to it
			for (PurchaseReturnProducts purchaseReturnProduct : purchaseReturnProducts) {
				purchaseReturnProduct.setPurchase_return_id(purchaseReturn.getPurchase_return_id());//sets purchase return id to the each product 
				manager.persist(purchaseReturnProduct);//persists it to purchase return products
			}
			List<ProductInput> productInput = productDetails.getPi();//gets all details of products to be returned
			for (ProductInput product : productInput) {
				ProductsStockEntity productStock = (ProductsStockEntity) manager
						.createQuery("select s from ProductsStockEntity s where s.product_id=:productid and s.batch_no=:batchno")
						.setParameter("productid", product.getProduct_id()).setParameter("batchno", product.getBatch_no()).getSingleResult();
				int newProductStock = productStock.getProduct_stock() - product.getQuantity();//calculate stock
				productStock.setProduct_stock(newProductStock); //updates the stock in products stock based on product id and batch no
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	//retreives all the data needed for dashboard display
	@Transactional
	public List<ProductsDataDTO> getDashboardData() {
		ArrayList<Object[]> productsDataDTO = (ArrayList<Object[]>) manager
				.createQuery("SELECT s, p FROM ProductsEntity s JOIN  ProductsStockEntity p ON s.product_id = p.product_id")
				.getResultList();//retrieves data from products and products stock table via join and returns array of object arrays
		List<ProductsDataDTO> productsData = new ArrayList<ProductsDataDTO>();
		for (Object[] product : productsDataDTO) {
			for (int i = 0; i < product.length; i++) {
				if (i == 0) {
					productEntity = (ProductsEntity) product[i];//retreives row in products table
				
				} else {
					productStockEntity = (ProductsStockEntity) product[i];//retreives corresponding rows in products stock
				}
			}
			ProductsDataDTO dtoClass = applicationContext.getBean(ProductsDataDTO.class);//joins data with help of dto class
			dtoClass.setProduct(productEntity);
			dtoClass.setStock(productStockEntity);
			productsData.add(dtoClass);
		}

		return productsData;//returns all data of products and products stock

	}
    
	//retreives count of products along with name 
	@Transactional
	public List<ProductDetailsDTO> getProductsCount() {
		ArrayList<Object[]> productDetail = (ArrayList<Object[]>) manager
				.createQuery("SELECT s, p FROM ProductsEntity s JOIN ProductsStockEntity p ON s.product_id = p.product_id")
				.getResultList();//performs join on products and products stock tables and returns array of object arrays

		List<ProductDetailsDTO> productCountAndName = new ArrayList<ProductDetailsDTO>();
		for (Object[] product : productDetail) {
			System.out.println(product.length);

			for (int i = 0; i < product.length; i++) {
				if (i == 0) {
					productEntity = (ProductsEntity) product[i];
					productName = applicationContext.getBean(ProductName.class);
					productName.setProduct_name(productEntity.getProduct_name());//retreives one product name and other details
					
				} else {
					productStockEntity = (ProductsStockEntity) product[i];
					productQuantity = applicationContext.getBean(ProductQuantity.class);//retreives product quantity 
					productQuantity.setProduct_stock(productStockEntity.getProduct_stock());

				}
			}
			ProductDetailsDTO dtoClass = applicationContext.getBean(ProductDetailsDTO.class);//joins two tables data with dto class
			dtoClass.setProduct(productName);
			dtoClass.setStock(productQuantity);
			productCountAndName.add(dtoClass);
		}

		return productCountAndName;

	}

	//gets all the data about stock to calculate further bll calculations in service class
	@Transactional
	public ArrayList<TotalStock> getTotalStock() {
		ArrayList<TotalStock> totalStock = (ArrayList<TotalStock>) manager
				.createQuery("SELECT s FROM TotalStock s ", TotalStock.class).getResultList();
		return totalStock;//returns all products details to service class for further calculation
	}
	
    //retreives count of product categories avaialable in warehouse 
	@Transactional
	public ProductCategoryCount getCategoriesCount() {
		ProductCategoryCount CategoryCount = (ProductCategoryCount) manager.createQuery(
				"select new main.models.warehouseModels.outputmodels.ProductCategoryCount(count(*)) from  ProductCategories s")
				.getSingleResult();
		return CategoryCount;

	}

	//get the total cost of products in warehouse
	@Transactional
	public TotalWarehouseVal getWarehouseValue() {
		TotalWarehouseVal totalWarehouseValue = (TotalWarehouseVal) manager.createQuery(
				"select new main.models.warehouseModels.outputmodels.TotalWarehouseVal(sum(s.product_cost)) from ProductsStockEntity  s")
				.getSingleResult();
		return totalWarehouseValue;//returns total value of warehouse

	}
    
	//get the total count of vendors in warehouse
	@Transactional
	public VendorCount getVendorsCount() {
		VendorCount vendorCount = (VendorCount) manager
				.createQuery("select new main.models.warehouseModels.outputmodels.VendorCount(count(*)) from Vendor  s",
						VendorCount.class)
				.getSingleResult();
		return vendorCount;//returns number of vendors available

	}
	
	//to get all the products under purchase return id
	@Transactional
	public List<PurchaseReturnDTO> getPurchaseReturnProducts(PurchaseReturnId purchaseReturnId) {
		PurchaseReturn purchaseReturn = (PurchaseReturn) manager
				.createQuery("SELECT s from PurchaseReturn s where s.purchase_return_id=:x")
				.setParameter("x", purchaseReturnId.getPurchase_return_id()).getSingleResult();

		List<PurchaseReturnDTO> purchaseReturnProductDetails = new ArrayList<>();
		List<PurchaseReturnProducts> PurchaseReturnProducts = purchaseReturn.getPurchaseReturnProducts();
		for (int i = 0; i < PurchaseReturnProducts.size(); i++)

		{
			PurchaseReturnDTO purchaseReturnDTO= applicationContext.getBean(PurchaseReturnDTO.class);
			purchaseReturnDTO.setPr(purchaseReturn);
			purchaseReturnDTO.setPrp(PurchaseReturnProducts.get(i));     //creates new dto class object and add it to arraylist of details
			purchaseReturnProductDetails.add(purchaseReturnDTO);
		}
		return purchaseReturnProductDetails; //returns all products under purchase return id

	}
	
	//retreives all purchase orders details without input
	@Transactional
	public List<PurchaseOrderDetails> getAllPurchaseOrders() {
	    return manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseOrderDetails(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from PurchaseOrder s",
	            PurchaseOrderDetails.class).getResultList();
	}
	
	//retreives all purchase orders details with vendor id as input
	@Transactional
	public List<PurchaseOrderDetails> getPurchaseOrdersByVendor(PurchasesFilter p) {
	    return manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseOrderDetails(s.purchase_order_id, s.purchase_order_date, s.purchase_order_amount, s.vendor_id, s.purchase_order_expected_date) from PurchaseOrder s where s.vendor_id=:v",
	            PurchaseOrderDetails.class).setParameter("v", p.getVendor_id()).getResultList(); //returns purchase orders belonging to a vendor
	}
	
	//retreives all products under purchase id
	@Transactional
	public List<PurchaseOrdersDTO> getPurchaseProducts(PurchaseId purchaseId) {
		List<Object> purchaseDetails = manager.createQuery("SELECT s from PurchaseOrder s where s.purchase_order_id=:x")
				.setParameter("x", purchaseId.getPurchase_order_id()).getResultList(); //selects record based on order id

		List<PurchaseOrdersDTO> purchaseOrderProductDetails = new ArrayList<>();
		ArrayList<PurchaseOrderProducts> m = new ArrayList<>();

		for (Object q : purchaseDetails) {

			List<PurchaseOrderProducts> purchaseOrderDetails = ((PurchaseOrder) q).getChildren(); //retreives all the related records in products table
			System.out.println(purchaseOrderDetails.toString());
			for (int i = 0; i < purchaseOrderDetails.size(); i++) {
				PurchaseOrdersDTO purchaseOrderDTO=applicationContext.getBean(PurchaseOrdersDTO.class);
				purchaseOrderDTO.setPo((PurchaseOrder) q);
				purchaseOrderDTO.setPop(purchaseOrderDetails.get(i));
				purchaseOrderProductDetails.add(purchaseOrderDTO); //creates a joining class object
			}
			break;

		}
		return purchaseOrderProductDetails; //returns 
	}
	
	//retreives purchase returns details without input
	@Transactional
	public List<PurchaseReturnDetails> getDefaultPurchaseReturns() {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s",
	            PurchaseReturnDetails.class).getResultList();

	    return purchaseReturns; //returns details of all purchase returns
	}
	
	//retreives purchase return details based on return id
	@Transactional
	public PurchaseReturnDetails getPurchaseReturnsListDetailsById(PurchaseReturnId purchaseReturnId) {
		PurchaseReturnDetails purchaseReturns = manager.createQuery(
				"SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost,s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.purchase_return_id=:purchaseReturnId",
				PurchaseReturnDetails.class).setParameter("purchaseReturnId", purchaseReturnId.getPurchase_return_id()).getSingleResult();
		
		return purchaseReturns;//returns all details about purchase returns based on id as input
	}
	
	//retreives purchase return details based on vendorId
	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorId(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.vendor_id=:vendorId",
	            PurchaseReturnDetails.class).setParameter("vendorId", purchasesReturnFilter.getVendor_id()).getResultList();
	   
	     return purchaseReturns;	 // If vendor ID is specified but no return date and GRN value, retrieve purchase returns by vendor ID

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorAndStartDate(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.vendor_id=:vendorId and s.purchase_return_date>=:fromDate",
	            PurchaseReturnDetails.class).setParameter("vendorId", purchasesReturnFilter.getVendor_id())
	            .setParameter("fromDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date())).getResultList();
	  
	    return purchaseReturns;// If vendor ID and start date are specified but no return date and GRN value, retrieve purchase returns by vendor ID and start date

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorAndDateRange(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.vendor_id=:v and s.purchase_return_date>=:fromDate and s.purchase_return_date<=:toDate",
	            PurchaseReturnDetails.class).setParameter("vendorId", purchasesReturnFilter.getVendor_id())
	            .setParameter("fromdate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date()))
	            .setParameter("toDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date1())).getResultList();
	 
	    return purchaseReturns;// If vendor ID, start date, and end date are specified but no GRN value, retrieve purchase returns by vendor ID and date range

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorAndGrnCost(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.vendor_id=:vendorId and s.grn_cost=:grnCost",
	            PurchaseReturnDetails.class).setParameter("vendorId", purchasesReturnFilter.getVendor_id())
	            .setParameter("grnCost", BigDecimal.valueOf(purchasesReturnFilter.getGrn_value())).getResultList();
	   
	    return purchaseReturns; // If vendor ID and GRN value are specified but no return date, retrieve purchase returns by vendor ID and GRN value

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorGrnCostAndStartDate(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.vendor_id=:vendorId and s.grn_cost=:grnCost and s.purchase_return_date>=:fromDate",
	            PurchaseReturnDetails.class).setParameter("vendorId", purchasesReturnFilter.getVendor_id())
	            .setParameter("grnCost", BigDecimal.valueOf(purchasesReturnFilter.getGrn_value()))
	            .setParameter("fromDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date())).getResultList();
	 
	    return purchaseReturns;// If vendor ID, start date, and GRN value are specified but no return date, retrieve purchase returns by vendor ID, start date, and GRN value

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByVendorGrnCostAndDateRange(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.vendor_id=:vendorId and s.grn_cost=:grnCost and s.purchase_return_date>=:fromDate and s.purchase_return_date<=:toDate",
	            PurchaseReturnDetails.class).setParameter("vendorId", purchasesReturnFilter.getVendor_id())
	            .setParameter("grnCost", BigDecimal.valueOf(purchasesReturnFilter.getGrn_value()))
	            .setParameter("fromDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date()))
	            .setParameter("toDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date1())).getResultList();
	 
	    return purchaseReturns; // If vendor ID, start date, end date, and GRN value are specified, retrieve purchase returns by vendor ID, date range, and GRN value

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByDateRange(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.purchase_return_date>=:fromDate and s.purchase_return_date<=:toDate",
	            PurchaseReturnDetails.class).setParameter("fromDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date()))
	            .setParameter("toDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date1())).getResultList();
	    return purchaseReturns; // If no vendor ID, GRN value, and end date are specified but return date is specified, retrieve purchase returns by date range

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByStartDate(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.purchase_return_date>=:fromDate",
	            PurchaseReturnDetails.class).setParameter("fromDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date())).getResultList();
	 
	    return purchaseReturns; // If no vendor ID, GRN value, and start date are specified but return date is specified, retrieve purchase returns by start date

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByGrnCost(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.grn_cost=:grnCost",
	            PurchaseReturnDetails.class).setParameter("grnCost",BigDecimal.valueOf(purchasesReturnFilter.getGrn_value()) ).getResultList();
	  
	    return purchaseReturns;// If no vendor ID, return date, and end date are specified but GRN value is specified, retrieve purchase returns by GRN value

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByGrnCostAndStartDate(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.grn_cost=:grnCost and s.purchase_return_date>=:fromDate",
	            PurchaseReturnDetails.class).setParameter("grnCost", BigDecimal.valueOf(purchasesReturnFilter.getGrn_value()))
	            .setParameter("fromDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date())).getResultList();
	 
	    return purchaseReturns; // If no vendor ID, return date, and GRN value are specified but start date is specified, retrieve purchase returns by GRN value and start date

	}

	@Transactional
	public List<PurchaseReturnDetails> getPurchaseReturnsByGrnCostAndDateRange(PurchasesReturnFilter purchasesReturnFilter) {
	    List<PurchaseReturnDetails> purchaseReturns = manager.createQuery(
	            "SELECT new main.models.procurementModels.outputmodels.PurchaseReturnDetails(s.purchase_return_id, s.purchase_return_date, s.grn_no, s.grn_cost, s.vendor_id, s.purchase_return_description) from PurchaseReturn s where s.grn_cost=:grnCost and s.purchase_return_date>=:fromDate and s.purchase_return_date<=:toDate",
	            PurchaseReturnDetails.class).setParameter("grnCost", purchasesReturnFilter.getGrn_value())
	            .setParameter("fromDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date()))
	            .setParameter("toDate", Date.valueOf(purchasesReturnFilter.getPurchase_return_date1())).getResultList();
	 
	    return purchaseReturns;  // If no vendor ID, return date, and GRN value are specified but start date and end date are specified, retrieve purchase returns by GRN value and date range

	}
	

}
