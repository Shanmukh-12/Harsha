package main.dal.adjustments;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.adjustments.AdjustmentsDAO;
import main.models.adjustmentsModels.entities.AdjustmentsList;
import main.models.adjustmentsModels.entities.AdjustmentsProductsList;
import main.models.adjustmentsModels.inputModels.AdjustmentsFilterInput;
import main.models.adjustmentsModels.inputModels.AdjustmentsInputList;
import main.models.adjustmentsModels.outputModels.AdjustmentProductsListData;
import main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput;
import main.models.productModels.entities.ProductStock;

@Component
public class AdjustmentsDAL implements AdjustmentsDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/* getAdjustments method is used to retrieve a list of adjustments and uses entityManager to create a 
query to select all the AdjustmentsList entities.

The AdjustmentsList class represents the entity mapping for the im_adjustments (adjs_id, adjs_date)
table in the database.
 */

	@Transactional
	public List<AdjustmentsList> getAdjustments() {
		List<AdjustmentsList> l = entityManager.createQuery("SELECT v FROM AdjustmentsList v").getResultList();
		for (AdjustmentsList v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

/* The saveAdjustments method is responsible for persisting the AdjustmentsList object and 
its associated AdjustmentsProductsList objects into the database.

 The AdjustmentsProductsList class represents the entity mapping for the im_adjustment_items table, including the composite primary key (adjs_id, product_id, batch_no)
*/

	@Transactional
	public boolean saveAdjustments(AdjustmentsList adjustmentsList) {
		
		entityManager.persist(adjustmentsList);
		List<AdjustmentsProductsList> apl;
		apl = adjustmentsList.getProductsList();
		for (AdjustmentsProductsList s : apl) {
			s.setAdjs_id(adjustmentsList.getAdjustmentID());
			System.out.println(s);
			entityManager.persist(s);
			ProductStock ips = (ProductStock) entityManager
					.createQuery("select s from ProductStock s where s.productId=:prodId and s.batchNo=:batchNo")
					.setParameter("prodId", s.getProduct_id()).setParameter("batchNo", s.getBatch_no())
					.getSingleResult();
			ips.setProductStock(s.getUpdated_stock());
		}
		return true;

	}

/* getAdjustmentProductsList method displays the list of all the products that were 
adjusted according to their adjustment Id */
    @Transactional
	public List<AdjustmentProductsListData> getAdjustmentProductsList(AdjustmentsInputList adjustmentid) {
		int data = adjustmentid.getAdjs_id();
		
		List<AdjustmentProductsListData> s = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentProductsListData(e.product_id, p.productName, pc.productCategoryName, e.batch_no, e.current_stock, e.updated_stock, e.adjs_desc) "
						+ "FROM AdjustmentsProductsList e "
						+ "JOIN main.models.productModels.entities.Products p ON e.product_id = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.adjs_id = :data",
				AdjustmentProductsListData.class).setParameter("data", data).getResultList();
		/*for (AdjustmentProductsListData p : s)
			System.out.println("Inside " + p); */

		return s;

	}

	

// This method filters Adjustment ID's by product category Id, product Id and From date
	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductIdFrom(
			AdjustmentsFilterInput adjustmentsFilterInput) {
		
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE p.productId = :productId and e.adjustmentDate between :fromDate and :toDate and p.category = :categoryId"
						+ " GROUP BY e.adjustmentID, e.adjustmentDate")
				.setParameter("categoryId", adjustmentsFilterInput.getProductCategoryId())
				.setParameter("productId", adjustmentsFilterInput.getProductId())
				.setParameter("fromDate", adjustmentsFilterInput.getFromDate())
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;

	}

// This method filters Adjustment ID's by product category Id and product Id
	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductId(
			AdjustmentsFilterInput adjustmentsFilterInput) {
		
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE p.productId = :productId and e.adjustmentDate<= :toDate and p.category = :categoryId"
						+ " GROUP BY e.adjustmentID, e.adjustmentDate")
				.setParameter("categoryId", adjustmentsFilterInput.getProductCategoryId())
				.setParameter("productId", adjustmentsFilterInput.getProductId())
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;
	}

	// This method filters Adjustment ID's by product category Id and From Date
	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdFrom(AdjustmentsFilterInput adjustmentsFilterInput) {
		
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE  p.category = :categoryId and e.adjustmentDate between :fromDate and :toDate "
						+ " GROUP BY e.adjustmentID, e.adjustmentDate")
				.setParameter("categoryId", adjustmentsFilterInput.getProductCategoryId())
				.setParameter("fromDate", adjustmentsFilterInput.getFromDate())
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;
	}

// This method filters Adjustment ID's by product category Id
	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryId(AdjustmentsFilterInput adjustmentsFilterInput) {
		
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE p.category = :categoryId and e.adjustmentDate <= :toDate"
						+ " GROUP BY e.adjustmentID, e.adjustmentDate")
				.setParameter("categoryId", adjustmentsFilterInput.getProductCategoryId())
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		
		return lst;
	}
// This method filters Adjustment ID's by From date
	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByFrom(AdjustmentsFilterInput adjustmentsFilterInput) {
		
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE e.adjustmentDate between :fromDate and :toDate"
						+ " GROUP BY e.adjustmentID, e.adjustmentDate")
				.setParameter("fromDate", adjustmentsFilterInput.getFromDate())
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;
	}

// This method filters Adjustment ID's by To date
	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByTo(AdjustmentsFilterInput adjustmentsFilterInput) {
		
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE e.adjustmentDate <= :toDate" + " GROUP BY e.adjustmentID, e.adjustmentDate")
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;
	}

}
