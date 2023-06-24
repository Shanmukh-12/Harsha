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

	@Transactional
	public List<AdjustmentsList> getAdjustments() {
		List<AdjustmentsList> l = entityManager.createQuery("SELECT v FROM AdjustmentsList v").getResultList();
		for (AdjustmentsList v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

	@Transactional
	public boolean saveAdjustments(AdjustmentsList adjustmentsList) {
		System.out.println("Inside adjustmentsDao");
		System.out.println(adjustmentsList);
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

	@Transactional
	public List<AdjustmentProductsListData> getAdjustmentProductsList(AdjustmentsInputList adjustmentid) {
		int data = adjustmentid.getAdjs_id();
		System.out.println(data);
		List<AdjustmentProductsListData> s = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentProductsListData(e.product_id, p.productName, pc.productCategoryName, e.batch_no, e.current_stock, e.updated_stock, e.adjs_desc) "
						+ "FROM AdjustmentsProductsList e "
						+ "JOIN main.models.productModels.entities.Products p ON e.product_id = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.adjs_id = :data",
				AdjustmentProductsListData.class).setParameter("data", data).getResultList();
		for (AdjustmentProductsListData p : s)
			System.out.println("Inside " + p);

		return s;
      
		
	}

	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductIdFrom(
			AdjustmentsFilterInput adjustmentsFilterInput) {
		@SuppressWarnings("unchecked")
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE p.productId = :productId and e.adjustmentDate between :fromDate and :toDate")
				.setParameter("productId", adjustmentsFilterInput.getProductId())
				.setParameter("fromDate", adjustmentsFilterInput.getFromDate())
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;

	}

	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdProductId(
			AdjustmentsFilterInput adjustmentsFilterInput) {
		@SuppressWarnings("unchecked")
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE p.productId = :productId and e.adjustmentDate<= :toDate")
				.setParameter("productId", adjustmentsFilterInput.getProductId())
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;
	}

	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryIdFrom(AdjustmentsFilterInput adjustmentsFilterInput) {
		@SuppressWarnings("unchecked")
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE e.adjustmentDate between :fromDate and :toDate")
				.setParameter("fromDate", adjustmentsFilterInput.getFromDate())
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;
	}

	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByCategoryId(AdjustmentsFilterInput adjustmentsFilterInput) {
		@SuppressWarnings("unchecked")
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE e.adjustmentDate <= :toDate")
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;
	}

	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByFrom(AdjustmentsFilterInput adjustmentsFilterInput) {
		@SuppressWarnings("unchecked")
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE e.adjustmentDate between :fromDate and :toDate")
				.setParameter("fromDate", adjustmentsFilterInput.getFromDate())
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;
	}

	@Override
	public List<AdjustmentsFilterOutput> getFilterDataByTo(AdjustmentsFilterInput adjustmentsFilterInput) {
		@SuppressWarnings("unchecked")
		List<AdjustmentsFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.adjustmentsModels.outputModels.AdjustmentsFilterOutput(e.adjustmentID, e.adjustmentDate)"
						+ " FROM AdjustmentsFilter e"
						+ " JOIN main.models.adjustmentsModels.entities.AdjustmentsProductsList ai ON e.adjustmentID = ai.adjs_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = ai.product_id"
						+ " WHERE e.adjustmentDate <= :toDate")
				.setParameter("toDate", adjustmentsFilterInput.getToDate()).getResultList();
		return lst;
	}

}