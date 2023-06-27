package main.dao.users;

import java.util.List;

import main.models.storeModels.entities.Store;
import main.models.storeModels.inputmodels.StoreId;
import main.models.storeModels.inputmodels.StoreStatus;

public interface StoreUsersDAO {
	
	// Saving StoreUser Information
	public void saveStore(Store store);
	
	// Soft Deleting the Store User i.e.Status Inactive
	public Store deleteStore(StoreId store);
	
    //Getting all Active Store Users
	public List<Store> getAllActiveStores();
	
    //Getting all store users 
	public List<Store> getAllStores();
	
    //Filtering the store users Based on Status
	public List<Store> getStoreStatus(StoreStatus storeStatus);

}
