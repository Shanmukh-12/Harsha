package main.dal.storeIndents;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.storeIndents.StoreIndentsDao;
import main.models.storeModels.entities.Store;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentProductsList;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.StoreFilters;

@Component
public class StoreIndentsDal implements StoreIndentsDao{

	@PersistenceContext
	EntityManager entityManager;


	@Override
	@Transactional
	public List<StoreIndentData> getStoreIndentsList() {
		return entityManager.createQuery("select e from StoreIndentData e  ORDER BY e.indentId DESC")
				.setMaxResults(5)
				.getResultList();
		
	}

	@Override
	@Transactional
	public List<StoreIndentProductsList> getStoreIndentsProductsList(IndentId indentid) {
	    int data = indentid.getIndentId();
	    System.out.println(data);
	    List<StoreIndentProductsList> s = entityManager
	        .createQuery("select e from StoreIndentProductsList e where e.indentID = :data", StoreIndentProductsList.class)
	        .setParameter("data", data)
	        .getResultList();
	    return s;
	}


	
	@Transactional
	public boolean saveStoreIndent(StoreIndentsList storeIndentsList) {
		System.out.println("Inside storeIndentsDao");
		System.out.println(storeIndentsList);
		entityManager.persist(storeIndentsList);
		List<StoreIndentProductsList> sipl = storeIndentsList.getProductsList();
		for (StoreIndentProductsList s : sipl) {
			s.setIndentID(storeIndentsList.getIndentID());
			entityManager.persist(s);
		}
		return true;
	}
	
	

	@Override
	public List<Store> getStoreIds() {
		List<Store> lst = entityManager.createQuery("SELECT e FROM Store e").getResultList();
		return lst;
	}

	public List<StoreIndentData> getStoreIndentsListByFilters(StoreFilters storeFilters) {
				
		List<StoreIndentData> lst = entityManager
		.createQuery("SELECT e FROM StoreIndentData e where e.date<=:toDate and e.date>=fromDate")
		.setParameter("toDate", storeFilters.getToDate())
		.setParameter("fromDate", storeFilters.getFromDate())
		.getResultList();
		
		return lst;
	}

	@Override
	public List<StoreIndentData> getStoreIndentsListByIdStatusFrom(StoreFilters storeFilters) {
		List<StoreIndentData> lst = entityManager
		.createQuery("SELECT e FROM StoreIndentData e where e.storeId=:storeId and e.indentStatus=:status and e.date >= :fromDate AND e.date <= :toDate")
		.setParameter("storeId", storeFilters.getStoreId())
		.setParameter("status",storeFilters.getIndentStatus())
		.setParameter("toDate", storeFilters.getToDate())
		.setParameter("fromDate", storeFilters.getFromDate())
		.getResultList();
		return lst;
	}

	@Override
	public List<StoreIndentData> getStoreIndentsListByIdStatus(StoreFilters storeFilters) {
		List<StoreIndentData> lst = entityManager
		.createQuery("SELECT e FROM StoreIndentData e where e.storeId=:storeId and e.indentStatus=:status and e.date <= :toDate")
		.setParameter("storeId", storeFilters.getStoreId())
		.setParameter("status",storeFilters.getIndentStatus())
		.setParameter("toDate", storeFilters.getToDate())
		.getResultList();
		return lst;
	}

	@Override
	public List<StoreIndentData> getStoreIndentsListByIdFrom(StoreFilters storeFilters) {
		List<StoreIndentData> lst = entityManager
		.createQuery("SELECT e FROM StoreIndentData e where e.storeId=:storeId and e.date >= :fromDate AND e.date <= :toDate")
		.setParameter("storeId", storeFilters.getStoreId())
		.setParameter("toDate", storeFilters.getToDate())
		.setParameter("fromDate", storeFilters.getFromDate())
		.getResultList();
		return lst;
	}

	@Override
	public List<StoreIndentData> getStoreIndentsListById(StoreFilters storeFilters) {
		List<StoreIndentData> lst = entityManager
		.createQuery("SELECT e FROM StoreIndentData e where e.storeId=:storeId and e.date <= :toDate")
		.setParameter("storeId", storeFilters.getStoreId())
		.setParameter("toDate", storeFilters.getToDate())
		.getResultList();
		return lst;
	}

	@Override
	public List<StoreIndentData> getStoreIndentsListByStatusFrom(StoreFilters storeFilters) {
		List<StoreIndentData> lst = entityManager
		.createQuery("SELECT e FROM StoreIndentData e where e.indentStatus=:status and e.date >= :fromDate AND e.date <= :toDate")
		.setParameter("status",storeFilters.getIndentStatus())
		.setParameter("toDate", storeFilters.getToDate())
		.setParameter("fromDate", storeFilters.getFromDate())
		.getResultList();
		return lst;
	}

	@Override
	public List<StoreIndentData> getStoreIndentsListByStatus(StoreFilters storeFilters) {
		List<StoreIndentData> lst = entityManager
		.createQuery("SELECT e FROM StoreIndentData e where e.indentStatus=:status and e.date <= :toDate")
		.setParameter("status",storeFilters.getIndentStatus())
		.setParameter("toDate", storeFilters.getToDate())
		.getResultList();
		return lst;
	}

	@Override
	public List<StoreIndentData> getStoreIndentsListByFrom(StoreFilters storeFilters) {
		List<StoreIndentData> lst = entityManager
		.createQuery("SELECT e FROM StoreIndentData e where e.date >= :fromDate AND e.date <= :toDate")
		.setParameter("toDate", storeFilters.getToDate())
		.setParameter("fromDate", storeFilters.getFromDate())
		.getResultList();
		return lst;
	}

	@Override
	public List<StoreIndentData> getStoreIndentsListByTo(StoreFilters storeFilters) {
		
		List<StoreIndentData> lst = entityManager
		.createQuery("SELECT e FROM StoreIndentData e where e.date <= :toDate")
		.setParameter("toDate", storeFilters.getToDate())
		.getResultList();
		return lst;
	}




}