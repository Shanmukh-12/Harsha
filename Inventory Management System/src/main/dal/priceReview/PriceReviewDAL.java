package main.dal.priceReview;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.priceReview.PriceReviewDAO;
import main.models.priceReviewModels.entities.PriceReviewList;
import main.models.priceReviewModels.entities.PriceReviewProductsList;

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

}
