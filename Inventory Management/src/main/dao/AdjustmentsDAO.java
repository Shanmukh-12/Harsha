package main.dao.adjustments;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import main.models.adjustmentModels.Adjustments;

public class AdjusmentsDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Transactional
	public List<Adjustments> getAdjustments() {
		List<Adjustments> l = entityManager.createQuery("SELECT v FROM Adjustments v").getResultList();
		for (Adjustments v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

	@Transactional
	public void updateAdjustments() {

	}

	

}
