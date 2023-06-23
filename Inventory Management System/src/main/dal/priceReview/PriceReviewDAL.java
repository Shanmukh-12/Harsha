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

	@Transactional
	public List<PriceReviewList> getPriceReview() {
		List<PriceReviewList> l = entityManager.createQuery("SELECT v FROM PriceReviewList v").getResultList();
		for (PriceReviewList v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

	@Transactional
	public boolean savePriceReview(PriceReviewList priceReviewList) {
		System.out.println("Inside priceReviewDao");
		System.out.println(priceReviewList);
		entityManager.persist(priceReviewList);
		List<PriceReviewProductsList> prl;
		prl = priceReviewList.getProductsList();
		for (PriceReviewProductsList s : prl) {
			s.setPr_id(priceReviewList.getPriceReviewId());
			System.out.println(s);
			entityManager.persist(s);

			ProductStock ips = (ProductStock) entityManager
					.createQuery("select s from ProductStock s where s.productId=:prodId and s.batchNo=:batchNo")
					.setParameter("prodId", s.getProduct_id()).setParameter("batchNo", s.getBatch_no())
					.getSingleResult();
			ips.setProductSalePrice(s.getNew_price());
		}
		return true;

	}

	@Transactional
	public List<PriceReviewProductsListData> getPriceReviewProductsList(PriceReviewInputList pricereviewid) {

		int data = pricereviewid.getPr_id();
		System.out.println(data);
		@SuppressWarnings("unchecked")
		List<PriceReviewProductsListData> s = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewProductsListData(e.product_id, p.productName, pc.productCategoryName, e.batch_no, e.old_price, e.new_price, e.review_desc)"
						+ "FROM PriceReviewProductsList e "
						+ "JOIN main.models.productModels.entities.Products p ON e.product_id = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.pr_id = :data")
				.setParameter("data", data).getResultList();
		for (PriceReviewProductsListData p : s)
			System.out.println("Inside " + p);

		return s;

	}

	@Override
	public List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductIdFrom(
			PriceReviewFilterInput priceReviewFilterInput) {
		@SuppressWarnings("unchecked")
		List<PriceReviewFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE p.productId = :productId and e.priceReviewDate between :fromDate and :toDate")
				.setParameter("productId", priceReviewFilterInput.getProductId())
				.setParameter("fromDate", priceReviewFilterInput.getFromDate())
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return lst;

	}

	@Override
	public List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductId(
			PriceReviewFilterInput priceReviewFilterInput) {
		@SuppressWarnings("unchecked")
		List<PriceReviewFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE p.productId = :productId and e.priceReviewDate <= :toDate")
				.setParameter("productId", priceReviewFilterInput.getProductId())
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return lst;
	}

	@Override
	public List<PriceReviewFilterOutput> getFilterDataByCategoryIdFrom(PriceReviewFilterInput priceReviewFilterInput) {
		@SuppressWarnings("unchecked")
		List<PriceReviewFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE e.priceReviewDate between :fromDate and :toDate")
				.setParameter("fromDate", priceReviewFilterInput.getFromDate())
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return lst;
	}

	@Override
	public List<PriceReviewFilterOutput> getFilterDataByCategoryId(PriceReviewFilterInput priceReviewFilterInput) {
		@SuppressWarnings("unchecked")
		List<PriceReviewFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE e.priceReviewDate <= : toDate")
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return lst;
	}

	@Override
	public List<PriceReviewFilterOutput> getFilterDataByFrom(PriceReviewFilterInput priceReviewFilterInput) {
		@SuppressWarnings("unchecked")
		List<PriceReviewFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE e.priceReviewDate between :fromDate and :toDate")
				.setParameter("fromDate", priceReviewFilterInput.getFromDate())
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return lst;
	}

	@Override
	public List<PriceReviewFilterOutput> getFilterDataByTo(PriceReviewFilterInput priceReviewFilterInput) {
		@SuppressWarnings("unchecked")
		List<PriceReviewFilterOutput> lst = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewFilterOutput(e.priceReviewId, e.priceReviewDate)"
						+ " FROM PriceReviewFilter e"
						+ " JOIN main.models.priceReviewModels.entities.PriceReviewProductsList pi ON e.priceReviewId = pi.pr_id"
						+ " JOIN main.models.productModels.entities.Products p ON p.productId = pi.product_id"
						+ " WHERE e.priceReviewDate <= :toDate")
				.setParameter("toDate", priceReviewFilterInput.getToDate()).getResultList();
		return lst;
	}

}
