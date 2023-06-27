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
import main.models.storeModels.inputmodels.StoreStatus;

@Component
public class StoreUsersDAL implements StoreUsersDAO {

	@PersistenceContext
	private EntityManager entityManager;

	// Saving StoreUser Information
	@Transactional
	public void saveStore(Store store) {
		try {
			entityManager.persist(store); // Save the Store object in the database
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Store with the same unique key already exists.");
		}
	}

	// Getting all Store Users
	@Transactional
	public List<Store> getAllStores() {
		List<Store> listStores = entityManager.createQuery("SELECT s FROM Store s").getResultList(); // Retrieve all Store objects from the database
		return listStores;
	}

	// Soft Deleting the Store User
	@Transactional
	public Store deleteStore(StoreId store) {
		Store existingStore = entityManager.find(Store.class, store.getStoreId()); // Find the Store object with the given storeId
		existingStore.setStatus("Inactive"); // Set the status of the existing Store object to "Inactive"
		entityManager.merge(existingStore); // Merge the changes into the database
		return existingStore; // Return the updated Store object

	}

	// Getting all Active Stores
	@Transactional
	public List<Store> getAllActiveStores() {
		List<Store> stores = entityManager.createQuery("SELECT s FROM Store s WHERE status = 'Active'").getResultList(); // Retrieve all Store objects with status "Active"
		return stores;//Returns all the store users Information
	}

	@Override
	public List<Store> getStoreStatus(StoreStatus storeStatus) {
		String status = storeStatus.getStoreStatus(); // Retrieve the store status from the input parameter
		List<Store> listStores = entityManager.createQuery("SELECT v FROM Store v where v.status=:status")
				.setParameter("status", storeStatus.getStoreStatus()).getResultList(); // Retrieve all Store objects with the given store status
		return listStores; // Return the list of Store objects
	}
}
