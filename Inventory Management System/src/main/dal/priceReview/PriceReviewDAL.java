package main.dal.priceReview;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.priceReview.PriceReviewDAO;
import main.models.priceReviewModels.entities.PriceReviewList;
import main.models.priceReviewModels.entities.PriceReviewProductsList;
import main.models.priceReviewModels.inputModels.PriceReviewFilterInput;
import main.models.priceReviewModels.inputModels.PriceReviewInputList;
import main.models.priceReviewModels.outputModels.PriceReviewFilterOutput;
import main.models.priceReviewModels.outputModels.PriceReviewProductsListData;
import main.models.productModels.entities.ProductStock;

@Component
public class PriceReviewDAL implements PriceReviewDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * getPriceReview method is used to retrieve a list of price reviews and uses entityManager to create a query to
	 * select all the PriceReviewList entities.
	 * 
	 * The PriceReviewList class represents the entity mapping for the im_priceReview (pr_id, pr_date) table in the
	 * database.
	 */

	@Transactional
	public List<PriceReviewList> getPriceReview() {
		List<PriceReviewList> priceReviewList = null;

		priceReviewList = entityManager.createQuery("SELECT v FROM PriceReviewList v").getResultList();
		return priceReviewList;

	}

	/*
	 * The savePriceReview method is responsible for persisting the PriceReviewList object and its associated
	 * PriceReviewProductsList objects into the database.
	 * 
	 * The PriceReviewProductsList class represents the entity mapping for the im_priceReview_items table, including the
	 * composite primary key (pr_id, product_id, batch_no)
	 */
	@Transactional
	public boolean savePriceReview(PriceReviewList priceReviewList) {

		entityManager.persist(priceReviewList);
		List<PriceReviewProductsList> priceReviewProductsList = priceReviewList.getProductsList();

		for (PriceReviewProductsList product : priceReviewProductsList) {
			product.setPr_id(priceReviewList.getPriceReviewId());

			entityManager.persist(product);

			ProductStock productStock = (ProductStock) entityManager
					.createQuery(" SELECT s from ProductStock s where s.productId = :prodId and s.batchNo = :batchNo ")
					.setParameter("prodId", product.getProduct_id()).setParameter("batchNo", product.getBatch_no())
					.getSingleResult();
			productStock.setProductSalePrice(product.getNew_price());
		}
		return true;

	}

	/*
	 * getPriceReviewProductsList method displays the list of all the products whose prices were modified according to
	 * their price review Id
	 */

	@Transactional
	public List<PriceReviewProductsListData> getPriceReviewProductsList(PriceReviewInputList pricereviewid) {

		List<PriceReviewProductsListData> productsList = null;

		int data = pricereviewid.getPr_id();

		productsList = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewProductsListData(e.product_id, p.productName, pc.productCategoryName, e.batch_no, e.old_price, e.new_price, e.review_desc)"
						+ "FROM PriceReviewProductsList e "
						+ "JOIN main.models.productModels.entities.Products p ON e.product_id = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.pr_id = :data")
				.setParameter("data", data).getResultList();
		return productsList;

	}

	// This method filters Price Review ID's by product category Id, product Id and From date
	@Override
	public List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductIdFrom(
			PriceReviewFilterInput priceReviewFilterInput) {

		List<PriceReviewFilterOutput> priceReviewFilterOutput = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE p.productId = :productId and e.priceReviewDate between :fromDate and :toDate and p.category = :categoryId"
						+ " GROUP BY e.priceReviewId, e.priceReviewDate")
				.setParameter("categoryId", priceReviewFilterInput.getProductCategoryId())
				.setParameter("productId", priceReviewFilterInput.getProductId())
				.setParameter("fromDate", priceReviewFilterInput.getFromDate())
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return priceReviewFilterOutput;

	}

	// This method filters Price Review ID's by product category Id and product Id
	@Override
	public List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductId(
			PriceReviewFilterInput priceReviewFilterInput) {

		List<PriceReviewFilterOutput> priceReviewFilterOutput = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE p.productId = :productId and e.priceReviewDate <= :toDate and p.category = :categoryId"
						+ " GROUP BY e.priceReviewId, e.priceReviewDate")
				.setParameter("categoryId", priceReviewFilterInput.getProductCategoryId())
				.setParameter("productId", priceReviewFilterInput.getProductId())
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return priceReviewFilterOutput;

	}

	// This method filters Price Review ID's by product category Id and From Date
	@Override
	public List<PriceReviewFilterOutput> getFilterDataByCategoryIdFrom(PriceReviewFilterInput priceReviewFilterInput) {

		List<PriceReviewFilterOutput> priceReviewFilterOutput = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE  p.category = :categoryId and e.priceReviewDate between :fromDate and :toDate"
						+ " GROUP BY e.priceReviewId, e.priceReviewDate")
				.setParameter("categoryId", priceReviewFilterInput.getProductCategoryId())
				.setParameter("fromDate", priceReviewFilterInput.getFromDate())
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return priceReviewFilterOutput;

	}

	// This method filters Price Review ID's by product category Id
	@Override
	public List<PriceReviewFilterOutput> getFilterDataByCategoryId(PriceReviewFilterInput priceReviewFilterInput) {

		List<PriceReviewFilterOutput> priceReviewFilterOutput = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE p.category = :categoryId and e.priceReviewDate <= : toDate"
						+ " GROUP BY e.priceReviewId, e.priceReviewDate")
				.setParameter("categoryId", priceReviewFilterInput.getProductCategoryId())
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return priceReviewFilterOutput;
	}

	// This method filters Price Review ID's by From date
	@Override
	public List<PriceReviewFilterOutput> getFilterDataByFrom(PriceReviewFilterInput priceReviewFilterInput) {

		List<PriceReviewFilterOutput> priceReviewFilterOutput = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE e.priceReviewDate between :fromDate and :toDate"
						+ " GROUP BY e.priceReviewId, e.priceReviewDate")
				.setParameter("fromDate", priceReviewFilterInput.getFromDate())
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return priceReviewFilterOutput;

	}

	// This method filters Price Review ID's by To date
	@Override
	public List<PriceReviewFilterOutput> getFilterDataByTo(PriceReviewFilterInput priceReviewFilterInput) {

		@SuppressWarnings("unchecked")
		List<PriceReviewFilterOutput> priceReviewFilterOutput = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE e.priceReviewDate <= :toDate" + " GROUP BY e.priceReviewId, e.priceReviewDate")
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return priceReviewFilterOutput;

	}

}
