package main.dal.procurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import main.models.purchaseOrder.entityModels.PurchaseOrderProducts;

@Component
public class GrnDAOImpl implements GrnDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	GrnBll gb;

	private static final Logger logger = LoggerFactory.getLogger(GrnDAOImpl.class);

	@Transactional
	public boolean saveGrn(ImGrn imGrn) {
		logger.info("Entered into the saveGrn");
		try {
			// Persist the main GRN object
			entityManager.persist(imGrn);
			logger.debug("Main GRN object persisted: {}", imGrn);

			// Get the list of associated products from the GRN object
			List<ImGrnProducts> productsList = imGrn.getProductsList();

			// Iterate over each product and associate it with the GRN
			for (ImGrnProducts product : productsList) {
				// Set the GRN ID for the current product
				product.setGrnId(imGrn.getGrnId());

				// Persist the product
				entityManager.persist(product);
				logger.debug("Product persisted: {}", product);
			}
			logger.info("GRN saved successfully");
			// Return true to indicate successful saving of the GRN
			return true;
		} catch (Exception e) {
			// Handle any exceptions that occur during persistence
			logger.error("Error occurred while saving GRN", e);
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public void updateStock(GrnInputList grnInputList) throws Exception {
		logger.info("entered into the updateStock");
		// Get the list of products from the GrnInputList object
		List<GrnInputProductsList> productsList = grnInputList.getProductsList();

		// Iterate over each product in the list
		for (GrnInputProductsList product : productsList) {
			logger.debug("Updating stock for product: productId={}, batchNo={}", product.getProductId(),
					product.getBatchNo());

			// Create a new ProductPrice object based on the product details
			ProductPrice productPrice = new ProductPrice(product.getProductId(), product.getQuantity(),
					product.getTotalPrice());

			// Get the sale price for the product using a method from a class named gb
			SalePrice salePrice = gb.getProductSalePrice(productPrice);
			logger.debug("Calculated sale price: {}", salePrice.getSalePrice());

			try {
				// Try to find existing stock for the product using productId and batchNo
				ProductStock existingStock = (ProductStock) entityManager
						.createQuery(
								"SELECT s FROM ProductStock s WHERE s.productId = :productId AND s.batchNo = :batchNo")
						.setParameter("productId", product.getProductId()).setParameter("batchNo", product.getBatchNo())
						.getSingleResult();

				// Update the existing stock with the additional quantity and sale price
				existingStock.setProductStock(existingStock.getProductStock() + product.getQuantity());
				existingStock.setProductSalePrice(salePrice.getSalePrice());
				logger.debug("Existing stock updated: productId={}, batchNo={}, newStock={}, newSalePrice={}",
						product.getProductId(), product.getBatchNo(), existingStock.getProductStock(),
						existingStock.getProductSalePrice());
				logger.info("completed the execution of updateStock");
			} catch (NoResultException e) {
				// If no existing stock found, create a new ProductStock object
				ModelMapper mp = new ModelMapper();
				ProductStock newStock = mp.map(product, ProductStock.class);
				newStock.setProductCost(product.getTotalPrice() / product.getQuantity());
				newStock.setProductMrp(product.getMrp());
				newStock.setProductSalePrice(salePrice.getSalePrice());
				newStock.setProductStock(product.getQuantity());

				// Persist the new stock in the database
				entityManager.persist(newStock);

				logger.debug("New stock created and persisted: productId={}, batchNo={}, stock={}, salePrice={}",
						product.getProductId(), product.getBatchNo(), newStock.getProductStock(),
						newStock.getProductSalePrice());
				logger.info("completed the execution of updateStock");
			}
		}
	}

	@Override
	@Transactional
	public void updatePurchaseOrder(GrnInputList grnInputList) {
		logger.info("Entered into the updatePurchaseOrder ");

		// Get the list of products from the GrnInputList object
		List<GrnInputProductsList> productsList = grnInputList.getProductsList();

		// Iterate over each product in the list
		for (GrnInputProductsList product : productsList) {
			logger.debug("Updating purchase order for productId={}, purchaseOrderId={}", product.getProductId(),
					grnInputList.getPurchaseOrderId());

			try {
				// Query the database to retrieve the corresponding PurchaseOrderProducts object
				PurchaseOrderProducts purchaseOrderProduct = (PurchaseOrderProducts) entityManager.createQuery(
						"SELECT p FROM PurchaseOrderProducts p WHERE p.purchase_order_id = :purId AND p.product_id = :prodId")
						.setParameter("purId", grnInputList.getPurchaseOrderId())
						.setParameter("prodId", product.getProductId()).getSingleResult();

				// Update the received quantity of the purchase order product
				int previousQuantityReceived = purchaseOrderProduct.getQuantity_received();
				int newQuantityReceived = previousQuantityReceived + product.getQuantity();
				purchaseOrderProduct.setQuantity_received(newQuantityReceived);

				logger.debug(
						"Purchase order updated: productId={}, purchaseOrderId={}, previousReceived={}, newReceived={}",
						product.getProductId(), grnInputList.getPurchaseOrderId(), previousQuantityReceived,
						newQuantityReceived);
				logger.info("completed the execution of updatePurchaseOrder");
			} catch (NoResultException e) {
				logger.error("Failed to find purchase order product: productId={}, purchaseOrderId={}",
						product.getProductId(), grnInputList.getPurchaseOrderId(), e);
			}
		}
	}

	@Transactional
	public List<GrnListProductsOutputModel> getGrnProducts(GrnIdInput grnIdInput) {
		logger.info("Entered into the getGrnProducts ");

		// Create a ModelMapper instance for object mapping
		ModelMapper mapper = new ModelMapper();

		// Create an empty list to store the GrnListProductsOutputModel objects
		List<GrnListProductsOutputModel> grnProductsList = new ArrayList<>();

		try {
			// Retrieve the ImGrn object from the database based on the provided GRN ID
			ImGrn imGrn = (ImGrn) entityManager.createQuery("SELECT s FROM ImGrn s WHERE s.grnId = :grnid")
					.setParameter("grnid", grnIdInput.getGrnId()).getSingleResult();

			// Get the list of ImGrnProducts from the ImGrn object
			List<ImGrnProducts> productsList = imGrn.getProductsList();

			// Iterate over each ImGrnProducts object
			for (ImGrnProducts product : productsList) {
				logger.debug("Mapping ImGrnProducts to GrnListProductsOutputModel: productId={}, batchNo={}",
						product.getProductId(), product.getBatchNo());

				// Map the ImGrnProducts object to a GrnListProductsOutputModel object using ModelMapper
				GrnListProductsOutputModel grnListProductsOutputModel = mapper.map(product,
						GrnListProductsOutputModel.class);

				// Set additional properties of the GrnListProductsOutputModel object
				grnListProductsOutputModel.setGrn_id(imGrn.getGrnId());
				grnListProductsOutputModel.setProduct_id(product.getProductId());
				grnListProductsOutputModel.setBatch_no(product.getBatchNo());
				grnListProductsOutputModel.setTotalQuantity(product.getQuantity() + product.getBonus());

				// Add the GrnListProductsOutputModel object to the list
				grnProductsList.add(grnListProductsOutputModel);
			}

			logger.debug("Retrieved GRN products successfully: grnId={}, productCount={}", grnIdInput.getGrnId(),
					grnProductsList.size());
			logger.info("completed the execution of getGrnProducts");
		} catch (NoResultException e) {
			logger.error("Failed to retrieve GRN products: grnId={}", grnIdInput.getGrnId(), e);
		}

		// Return the list of GrnListProductsOutputModel objects
		return grnProductsList;
	}

	@Transactional
	public List<ImGrnOutputModel> getGrnListByIdFrom(GrnInputFilters grnInputFilters) {
		Logger logger = LoggerFactory.getLogger(getClass());

		try {
			// Execute the query with all filters set to default values
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) "
							+ "FROM ImGrn s " + "JOIN PurchaseOrder p ON s.purchaseOrderId = p.purchase_order_id "
							+ "WHERE p.vendor_id = :vendorId AND s.grnDate >= :fromDate AND s.grnDate <= :toDate",
					ImGrnOutputModel.class).setParameter("toDate", grnInputFilters.getGrnToDate())
					.setParameter("fromDate", grnInputFilters.getGrnFromDate())
					.setParameter("vendorId", grnInputFilters.getVendor_id()).getResultList();

			for (ImGrnOutputModel imGrnOutputModel : imGrnOutputList) {
				logger.info("GRN Output Model: grnId={}, vendorId={}, purchaseOrderId={}, grnDate={}, grnAmount={}",
						imGrnOutputModel.getGrnId(), imGrnOutputModel.getVendor_id(),
						imGrnOutputModel.getPurchase_order_id(), imGrnOutputModel.getGrnDate(),
						imGrnOutputModel.getGrnAmount());
			}

			logger.debug("Retrieved GRN list successfully: grnCount={}", imGrnOutputList.size());

			return imGrnOutputList;
		} catch (Exception e) {
			logger.error("Failed to retrieve GRN list: {}", e.getMessage(), e);
			return Collections.emptyList();
		}
	}

	@Transactional
	public List<ImGrnOutputModel> getGrnListById(GrnInputFilters grnInputFilters) {
		try {
			// Execute the query with all filters set to default values
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) "
							+ "FROM ImGrn s " + "JOIN PurchaseOrder p ON s.purchaseOrderId = p.purchase_order_id "
							+ "WHERE p.vendor_id = :vendorId AND s.grnDate <= :toDate",
					ImGrnOutputModel.class).setParameter("toDate", grnInputFilters.getGrnToDate())
					.setParameter("vendorId", grnInputFilters.getVendor_id()).getResultList();

			for (ImGrnOutputModel imGrnOutputModel : imGrnOutputList) {
				logger.debug("GRN Output Model: grnId={}, vendorId={}, purchaseOrderId={}, grnDate={}, grnAmount={}",
						imGrnOutputModel.getGrnId(), imGrnOutputModel.getVendor_id(),
						imGrnOutputModel.getPurchase_order_id(), imGrnOutputModel.getGrnDate(),
						imGrnOutputModel.getGrnAmount());
			}

			logger.info("Retrieved GRN list successfully: grnCount={}", imGrnOutputList.size());

			return imGrnOutputList;
		} catch (Exception e) {
			logger.error("Failed to retrieve GRN list: {}", e.getMessage(), e);
			return Collections.emptyList();
		}
	}

	@Transactional
	public List<ImGrnOutputModel> getGrnListByFrom(GrnInputFilters grnInputFilters) {

		try {
			// Execute the query with all filters set to default values
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) "
							+ "FROM ImGrn s " + "JOIN PurchaseOrder p ON s.purchaseOrderId = p.purchase_order_id "
							+ "WHERE s.grnDate >= :fromDate AND s.grnDate <= :toDate",
					ImGrnOutputModel.class).setParameter("toDate", grnInputFilters.getGrnToDate())
					.setParameter("fromDate", grnInputFilters.getGrnFromDate()).getResultList();

			for (ImGrnOutputModel imGrnOutputModel : imGrnOutputList) {
				logger.debug("GRN Output Model: grnId={}, vendorId={}, purchaseOrderId={}, grnDate={}, grnAmount={}",
						imGrnOutputModel.getGrnId(), imGrnOutputModel.getVendor_id(),
						imGrnOutputModel.getPurchase_order_id(), imGrnOutputModel.getGrnDate(),
						imGrnOutputModel.getGrnAmount());
			}

			logger.info("Retrieved GRN list successfully: grnCount={}", imGrnOutputList.size());

			return imGrnOutputList;
		} catch (Exception e) {
			logger.error("Failed to retrieve GRN list: {}", e.getMessage(), e);
			return Collections.emptyList();
		}
	}

	@Transactional
	public List<ImGrnOutputModel> getGrnListByTo(GrnInputFilters grnInputFilters) {
		Logger logger = LoggerFactory.getLogger(getClass());

		try {
			// Execute the query with all filters set to default values
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) "
							+ "FROM ImGrn s " + "JOIN PurchaseOrder p ON s.purchaseOrderId = p.purchase_order_id "
							+ "WHERE s.grnDate <= :toDate",
					ImGrnOutputModel.class).setParameter("toDate", grnInputFilters.getGrnToDate()).getResultList();

			for (ImGrnOutputModel imGrnOutputModel : imGrnOutputList) {
				logger.debug("GRN Output Model: grnId={}, vendorId={}, purchaseOrderId={}, grnDate={}, grnAmount={}",
						imGrnOutputModel.getGrnId(), imGrnOutputModel.getVendor_id(),
						imGrnOutputModel.getPurchase_order_id(), imGrnOutputModel.getGrnDate(),
						imGrnOutputModel.getGrnAmount());
			}

			logger.debug("Retrieved GRN list successfully: grnCount={}", imGrnOutputList.size());

			return imGrnOutputList;
		} catch (Exception e) {
			logger.error("Failed to retrieve GRN list: {}", e.getMessage(), e);
			return Collections.emptyList();
		}
	}

}
