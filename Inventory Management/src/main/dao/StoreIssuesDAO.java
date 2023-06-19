package main.dao.storeissues;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.models.storeissuesModels.StoreIssuesProducts;
import main.models.storeissuesModels.StoreIssues;

public class StoreIssuesDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public boolean saveStoreIssues(StoreIssues storeIssues) {
		System.out.println("Inside storeIssuesDao");
		System.out.println(storeIssues);
		entityManager.persist(storeIssues);
		List<StoreIssuesProducts> pipl;
        pipl = storeIssues.getProductsList();
		for (StoreIssuesProducts s : pipl) {
			s.setStoreIssueID(StoreIssues.getStoreIssueID());
			System.out.println(s);
			entityManager.persist(s);
		}
		System.out.println(storeIssues);
		return true;
	}

	@Transactional
	public List<StoreIssues> getAllStoreIssues() {
		List<StoreIssues> l = entityManager.createQuery("SELECT v FROM StoreIssues v").getResultList();
		for (StoreIssues v : l) {
			System.out.println(v.toString());
		}
		return l;
	}
	
}
	
	


