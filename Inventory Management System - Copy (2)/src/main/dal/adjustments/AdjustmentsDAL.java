package main.dal.adjustments;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.adjustments.AdjustmentsDAO;
import main.models.adjustmentsModels.entities.AdjustmentsList;
import main.models.adjustmentsModels.entities.AdjustmentsProductsList;

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
		}
		return true;

	}

}