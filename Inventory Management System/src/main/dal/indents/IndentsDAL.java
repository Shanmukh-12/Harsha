package main.dal.indents;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.models.indentModels.entities.ProcurementIndentProductsList;
import main.models.indentModels.entities.ProcurementIndentsList;

@Component
public class IndentsDAL {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public boolean saveProcurementIndent(ProcurementIndentsList procurementIndentsList) {
		System.out.println("Inside storeIndentsDao");
		System.out.println(procurementIndentsList);
		entityManager.persist(procurementIndentsList);
		List<ProcurementIndentProductsList> pipl;
		pipl = procurementIndentsList.getProductsList();
		for (ProcurementIndentProductsList s : pipl) {
			s.setIndentID(procurementIndentsList.getIndentID());
			System.out.println(s);
			entityManager.persist(s);
		}
		System.out.println(procurementIndentsList);
		return true;
	}

	@Transactional
	public List<ProcurementIndentsList> getAllIndents() {
		List<ProcurementIndentsList> l = entityManager.createQuery("SELECT v FROM ProcurementIndentsList v")
				.getResultList();
		for (ProcurementIndentsList v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

}