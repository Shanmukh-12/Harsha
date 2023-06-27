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
		// Persist the main GRN object
		manager.persist(imGrn);

		// Get the list of associated products from the GRN object
		List<ImGrnProducts> l = imGrn.getProductsList();
		System.out.println(l);

		// Iterate over each product and associate it with the GRN
		for (ImGrnProducts i : l) {
			System.out.println(i);
			System.out.println(imGrn.getGrnId());

			// Set the GRN ID for the current product
			i.setGrnId(imGrn.getGrnId());

			// Persist the product
			manager.persist(i);
		}

		// Return true to indicate successful saving of the GRN
		return true;
	}

	@Override
	@Transactional
	public void updateStock(GrnInputList grnInputList) {
		// Get the list of products from the GrnInputList object
		List<GrnInputProductsList> productsList = grnInputList.getProductsList();

		// Iterate over each product in the list
		for (GrnInputProductsList product : productsList) {
			System.out.println(product.getProductId() + " " + product.getBatchNo());

			// Create a new ProductPrice object based on the product details
			ProductPrice productPrice = new ProductPrice(product.getProductId(), product.getQuantity(),
					product.getTotalPrice());

			// Get the sale price for the product using a method from a class named gb
			SalePrice s = gb.getProductSalePrice(productPrice);

			try {
				// Try to find existing stock for the product using productId and batchNo
				ProductStock existingStock = (ProductStock) manager
						.createQuery(
								"SELECT s FROM ProductStock s WHERE s.productId = :productId AND s.batchNo = :batchNo")
						.setParameter("productId", product.getProductId()).setParameter("batchNo", product.getBatchNo())
						.getSingleResult();

				// Update the existing stock with the additional quantity and sale price
				existingStock.setProductStock(existingStock.getProductStock() + product.getQuantity());
				existingStock.setProductSalePrice(s.getSalePrice());
			} catch (NoResultException e) {
				// If no existing stock found, create a new ProductStock object
				ModelMapper mp = new ModelMapper();
				ProductStock newStock = mp.map(product, ProductStock.class);
				newStock.setProductCost(product.getTotalPrice() / product.getQuantity());
				newStock.setProductMrp(product.getMrp());
				newStock.setProductSalePrice(s.getSalePrice());
				newStock.setProductStock(product.getQuantity());

				// Persist the new stock in the database
				manager.persist(newStock);
			}
		}
	}

	@Override
	@Transactional
	public void updatePurchaseOrder(GrnInputList grnInputList) {
		// Get the list of products from the GrnInputList object
		List<GrnInputProductsList> productsList = grnInputList.getProductsList();

		// Iterate over each product in the list
		for (GrnInputProductsList product : productsList) {
			// Query the database to retrieve the corresponding Im_Purchase_Order_Products object
			Im_Purchase_Order_Products p = (Im_Purchase_Order_Products) manager.createQuery(
					"SELECT s FROM Im_Purchase_Order_Products s WHERE s.purchase_order_id = :purId AND s.product_id = :prodId")
					.setParameter("purId", grnInputList.getPurchaseOrderId())
					.setParameter("prodId", product.getProductId()).getSingleResult();

			// Update the received quantity of the purchase order product
			p.setQuantity_received(p.getQuantity_received() + product.getQuantity());
		}
	}

	@Transactional
	public List<GrnListProductsOutputModel> getGrnProducts(GrnIdInput gid) {
		// Create a ModelMapper instance for object mapping
		ModelMapper mapper = new ModelMapper();

		// Create an empty list to store the GrnListProductsOutputModel objects
		List<GrnListProductsOutputModel> a = new ArrayList<>();

		// Retrieve the ImGrn object from the database based on the provided GRN ID
		ImGrn imGrn = (ImGrn) manager.createQuery("SELECT s FROM ImGrn s WHERE s.grnId = :x")
				.setParameter("x", gid.getGrnId()).getSingleResult();

		// Get the list of ImGrnProducts from the ImGrn object
		List<ImGrnProducts> l = imGrn.getProductsList();

		// Iterate over each ImGrnProducts object
		for (ImGrnProducts i : l) {
			// Map the ImGrnProducts object to a GrnListProductsOutputModel object using ModelMapper
			GrnListProductsOutputModel io = mapper.map(i, GrnListProductsOutputModel.class);

			// Set additional properties of the GrnListProductsOutputModel object
			io.setGrn_id(imGrn.getGrnId());
			io.setProduct_id(i.getProductId());
			io.setBatch_no(i.getBatchNo());
			io.setTotalQuantity(i.getQuantity() + i.getBonus());

			// Add the GrnListProductsOutputModel object to the list
			a.add(io);
		}

		// Return the list of GrnListProductsOutputModel objects
		return a;
	}

	@Transactional
	public List<ImGrnOutputModel> getGrnList(GrnInputFilters g) {

		System.out.println(g.getGrnToDate());
		if (g.getVendor_id() == 0 && g.getGrnFromDate() == null && g.getGrn_amount() == 0.0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			// Execute the query with all filters set to default values
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchaseOrderId=p.purchase_order_id where s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();

			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() == null && g.getGrn_amount() == 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {

			// Execute the query with the vendor ID filter
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p on  s.purchaseOrderId=p.purchase_order_id WHERE p.vendor_id =:v and s.grnDate<=:d",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnToDate())).getResultList();

			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() == 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			// Execute the query with the vendor ID and date range filters
			List<ImGrnOutputModel> s = manager.createQuery(
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id  WHERE p.vendor_id = :v AND s.grnDate >= :d and s.grnDate<=:t",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate()))
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() != null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			// Execute the query with the vendor ID, date range, and amount filters
			List<ImGrnOutputModel> s = manager.createQuery(
					// check
					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id  where p.vendor_id = :v AND s.grnDate>= :d and s.grnDate<=:t AND s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id())
					.setParameter("d", LocalDate.parse(g.getGrnFromDate())).setParameter("a", g.getGrn_amount())
					.setParameter("t", LocalDate.parse(g.getGrnToDate())).getResultList();
			return s;
		} else if (g.getVendor_id() != 0 && g.getGrnFromDate() == null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			// Execute the query with the vendor ID and amount filters
			List<ImGrnOutputModel> s = manager.createQuery(

					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id WHERE p.vendor_id = :v AND s.grnDate<=: and s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("v", g.getVendor_id()).setParameter("a", g.getGrn_amount())
					.getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else if (g.getVendor_id() == 0 && g.getGrnFromDate() == null && g.getGrn_amount() != 0
				&& g.getGrnToDate().equals(String.valueOf(LocalDate.now()))) {
			// Execute the query with the amount filter
			List<ImGrnOutputModel> s = manager.createQuery(

					"SELECT new main.models.grnModels.outputModels.ImGrnOutputModel(s.grnId, p.vendor_id, s.purchaseOrderId, s.grnDate, s.grnAmount) FROM ImGrn s JOIN Im_Purchase_Order p  on  s.purchaseOrderId=p.purchase_order_id WHERE s.grnAmount = :a",
					ImGrnOutputModel.class).setParameter("a", g.getGrn_amount()).getResultList();

			for (ImGrnOutputModel x : s) {
				System.out.println(x.toString());
			}
			return s;
		} else {
			// Execute the query with the date range filter
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