package main.dal.priceReview;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.priceReview.PriceReviewDAO;
import main.models.priceReviewModels.entities.PriceReviewList;
import main.models.priceReviewModels.entities.PriceReviewProductsList;
import main.models.priceReviewModels.inputModels.PriceReviewInputList;
import main.models.priceReviewModels.outputModels.PriceReviewProductsListData;

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
		}
		return true;

	}

	@Transactional
	public List<PriceReviewProductsListData> getPriceReviewProductsList(PriceReviewInputList pricereviewid) {
		int data = pricereviewid.getPr_id();
		System.out.println(data);
		List<PriceReviewProductsListData> s = entityManager.createQuery(
				"SELECT NEW main.models.priceReviewModels.outputModels.PriceReviewProductsListData(e.product_id, p.productName, pc.productCategoryName, e.batch_no, e.old_price, e.new_price, e.review_desc) "
						+ "FROM PriceReviewProductsList e "
						+ "JOIN main.models.productModels.entities.Products p ON e.product_id = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.pr_id = :data",
				PriceReviewProductsListData.class).setParameter("data", data).getResultList();
		for (PriceReviewProductsListData p : s)
			System.out.println("Inside " + p);

		return s;

	}

}
