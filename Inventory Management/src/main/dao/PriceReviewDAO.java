package main.dao.pricereview;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class PriceReviewDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	private void updatePriceReview() {
		// TODO Auto-generated method stub

	}

	@Transactional
	public List<PriceReview> getPriceReview() {
		List<PriceReview> l = entityManager.createQuery("SELECT v FROM PriceReview_Items v").getResultList();
		for (PriceReview v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

}
