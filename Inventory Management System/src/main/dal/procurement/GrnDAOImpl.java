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
import main.models.purchaseOrder.entityModels.PurchaseOrderProducts;

@Component
public class GrnDAOImpl implements GrnDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	GrnBll gb;

	@Transactional
	public boolean saveGrn(ImGrn imGrn) {
		try {
			// Persist the main GRN object
			entityManager.persist(imGrn);

			// Get the list of associated products from the GRN object
			List<ImGrnProducts> productsList = imGrn.getProductsList();

			// Iterate over each product and associate it with the GRN
			for (ImGrnProducts product : productsList) {
				// Set the GRN ID for the current product
				product.setGrnId(imGrn.getGrnId());

				// Persist the product
				entityManager.persist(product);
			}

			// Return true to indicate successful saving of the GRN
			return true;
		} catch (Exception e) {
			// Handle any exceptions that occur during persistence
			e.printStackTrace();
			return false;
		}
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
				// Try to find existing stock for the product using productId and batchNo
				ProductStock existingStock = (ProductStock) entityManager

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

				// Persist the new stock in the database
				entityManager.persist(newStock);

			}
		}
	}

	@Override
	@Transactional
	public void updatePurchaseOrder(GrnInputList grnInputList) {
		List<GrnInputProductsList> productsList = grnInputList.getProductsList();
		for (GrnInputProductsList product : productsList) {
			// Query the database to retrieve the corresponding Im_Purchase_Order_Products object
			Im_Purchase_Order_Products p = (Im_Purchase_Order_Products) entityManager.createQuery(
					"SELECT s FROM Im_Purchase_Order_Products s WHERE s.purchase_order_id = :purId AND s.product_id = :prodId")

					.setParameter("purId", grnInputList.getPurchaseOrderId())
					.setParameter("prodId", product.getProductId()).getSingleResult();
			p.setQuantity_received(p.getQuantity_received() + product.getQuantity());
		}
	}

	@Transactional
	public List<GrnListProductsOutputModel> getGrnProducts(GrnIdInput grnIdInput) {
		// Create a ModelMapper instance for object mapping
		ModelMapper mapper = new ModelMapper();

		// Create an empty list to store the GrnListProductsOutputModel objects
		List<GrnListProductsOutputModel> grnProductsList = new ArrayList<>();

		// Retrieve the ImGrn object from the database based on the provided GRN ID
		ImGrn imGrn = (ImGrn) entityManager.createQuery("SELECT s FROM ImGrn s WHERE s.grnId = :x")
				.setParameter("x", grnIdInput.getGrnId()).getSingleResult();

		// Get the list of ImGrnProducts from the ImGrn object
		List<ImGrnProducts> productsList = imGrn.getProductsList();

		// Iterate over each ImGrnProducts object
		for (ImGrnProducts product : productsList) {
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

		// Return the list of GrnListProductsOutputModel objects
		return grnProductsList;

	}

	@Transactional
	public List<ImGrnOutputModel> getGrnList(GrnInputFilters g) {
		if (g.getVendor_id() == 0 && g.getGrnFromDate() == null && g.getGrn_amount() == 0.0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {

			// Execute the query with all filters set to default values
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchaseOrderId=p.purchase_order_id where s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();
			return imGrnOutputList;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() == null && g.getGrn_amount() == 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {

			// Execute the query with the vendor ID filter
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchaseOrderId=p.purchase_order_id WHERE p.vendor_id =:v and s.grnDate<=:d",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnToDate())).getResultList();

			return imGrnOutputList;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() == 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			// Execute the query with the vendor ID and date range filters
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id  WHERE p.vendor_id = :v AND s.grnDate >= :d and s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate()))
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();
			return imGrnOutputList;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			// Execute the query with the vendor ID, date range, and amount filters
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(

					// check
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN PurchaseOrder p  on  s.purchaseOrderId=p.purchase_order_id  where p.vendor_id = :v AND s.grnDate>= :d and s.grnDate<=:t AND s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate())).setParameter("a", g.getGrn_amount())
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();
			return imGrnOutputList;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() == null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			// Execute the query with the vendor ID and amount filters
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(

					// check
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN PurchaseOrder p  on  s.purchaseOrderId=p.purchase_order_id WHERE p.vendor_id = :v AND s.grnDate >=:d AND s.grnAmount = :a AND s.grnDate <= :t",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate())).setParameter("a", g.getGrn_amount())
					.setParameter("t", g.getGrnToDate()).getResultList();

			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() == null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			System.out.println("6");
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN PurchaseOrder p  on  s.purchaseOrderId=p.purchase_order_id WHERE p.vendor_id = :v AND s.grnDate<=: and s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id()).setParameter("a", g.getGrn_amount())
					.getResultList();
			return imGrnOutputList;
		} else if (g.getVendor_id() == 0 && g.getGrnFromDate() == null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			// Execute the query with the amount filter
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(

					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id WHERE s.grnAmount = :a",

					ImGrnOutputModel.class).setParameter("a", g.getGrn_amount()).getResultList();
			return imGrnOutputList;
		} else {
			// Execute the query with the date range filter
			List<ImGrnOutputModel> imGrnOutputList = entityManager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchaseOrderId=p.purchase_order_id where s.grnDate>=:v and s.grnDate<=:t",

					ImGrnOutputModel.class).setParameter("v", LocalDate.parse(g.getGrnFromDate()))
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			return imGrnOutputList;
		}
	}

}
