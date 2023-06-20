package main.dal.storeReturns;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.storeReturns.StoreReturnsDao;
import main.models.storeModels.entities.Store;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentProductsList;
import main.models.storeModels.entities.StoreReturnProductsList;
import main.models.storeModels.entities.StoreReturnsData;
import main.models.storeModels.entities.StoreReturnsList;
import main.models.storeModels.inputmodels.ReturnId;
import main.models.storeModels.inputmodels.StoreFilters;

@Component
public class StoreReturnsDal implements StoreReturnsDao{

	@PersistenceContext
	EntityManager entityManager;


	@Override
	@Transactional
	public List<StoreReturnsData> getStoreReturnsList() {
		return entityManager.createQuery("select e from StoreReturnsData e  ORDER BY e.indentId DESC")
				.setMaxResults(5)
				.getResultList();
		
	}

	@Override
	@Transactional
	public List<StoreReturnProductsList> getStoreReturnsProductsList(ReturnId returnid) {
	    int data = returnid.getReturnId();
	    System.out.println(data);
	    List<StoreReturnProductsList> s = entityManager
	        .createQuery("select e from StoreReturnProductsList e where e.retrunsID = :data", StoreReturnProductsList.class)
	        .setParameter("data", data)
	        .getResultList();
	    return s;
	}


	@Override
	@Transactional
	public boolean saveStoreReturns(StoreReturnsList storeReturnsList) {
		System.out.println("Inside storeReturnsDao");
		System.out.println(storeReturnsList);
		entityManager.persist(storeReturnsList);
		List<StoreReturnProductsList> sipl = storeReturnsList.getProductsList();
		for (StoreReturnProductsList s : sipl) {
			s.setReturnId(storeReturnsList.getReturnId());
			entityManager.persist(s);
		}
		return true;
	}
	
	
}
