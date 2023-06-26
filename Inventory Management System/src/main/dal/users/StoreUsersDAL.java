package main.dal.users;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import main.dao.users.StoreUsersDAO;
import main.models.storeModels.entities.Store;
import main.models.storeModels.inputmodels.StoreId;

@Component
public class StoreUsersDAL implements StoreUsersDAO {

	@PersistenceContext
	private EntityManager entityManager;
    //Saving StoreUser Information
	@Transactional
	public void saveStore(Store store) {
		try {
			entityManager.persist(store);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Store with the same unique key already exists.");
		}
	}
   //Getting all Store Users
	@Transactional
	public List<Store> getAllStores() {
		List<Store> l = entityManager.createQuery("SELECT s FROM Store s").getResultList();
		return l;
	}
   //Soft Deleting the Store User
	@Transactional
	public Store deleteStore(StoreId store) {
		Store existingStore = entityManager.find(Store.class, store.getStoreId());
		existingStore.setStatus("Inactive");
		entityManager.merge(existingStore);
		return existingStore;

	}
   //Getting all Active Stores
	@Transactional
	public List<Store> getAllActiveStores() {
		List<Store> stores = entityManager.createQuery("SELECT s FROM Store s WHERE status = 'Active'").getResultList();
		return stores;
	}

}
