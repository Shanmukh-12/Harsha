package main.dao.users;

import java.util.List;

import main.models.storeModels.entities.Store;
import main.models.storeModels.inputmodels.StoreId;

public interface StoreUsersDAO {
	public void saveStore(Store store);

	public Store deleteStore(StoreId store);

	public List<Store> getAllActiveStores();

	public List<Store> getAllStores();

}
