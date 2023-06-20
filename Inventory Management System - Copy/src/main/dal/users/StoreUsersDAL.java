package main.dal.users;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import main.dao.users.StoreUsersDAO;
import main.dto.users.StoreDto;
import main.models.storeModels.entities.Store;




@Component
public class StoreUsersDAL implements StoreUsersDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void saveStore(Store store) {
	    try {
	        entityManager.persist(store);
	    } catch (DataIntegrityViolationException e) {
	        throw new IllegalArgumentException("Store with the same unique key already exists.");
	    }
	}


	@Transactional
	public List<Store> getAllStores() {
		List<Store> l = entityManager.createQuery("SELECT s FROM Store s").getResultList();
//		for (Vendor v : l) {
//			System.out.println(v.toString());
//		}
		return l;
	}

	@Transactional
	public Store getStoreData(StoreDto v) {
		Store getStore= entityManager.find(Store.class, v.getStoreId());
		return getStore;
	}
	 @Transactional
	 public Store deleteStore(Store store) {
		 Store existingStore= entityManager.find(Store.class, store.getStoreId());
	     existingStore.setStatus("Inactive");
	     entityManager.merge(existingStore);
		 return  existingStore;
	
	 }
	 @Transactional
	 public List<Store> getAllActiveStores() {
			List<Store> stores = entityManager.createQuery("SELECT s FROM Store s WHERE status = 'Active'").getResultList();
			return stores;
		}

}
