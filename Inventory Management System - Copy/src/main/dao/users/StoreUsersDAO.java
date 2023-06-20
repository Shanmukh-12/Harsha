package main.dao.users;

import java.util.List;

import main.dto.users.StoreDto;
import main.models.storeModels.entities.Store;

public interface StoreUsersDAO {
	public void saveStore(Store store);
	public List<Store> getAllStores();
	public Store getStoreData(StoreDto v);
	public Store deleteStore(Store store);
	public List<Store> getAllActiveStores();


}
