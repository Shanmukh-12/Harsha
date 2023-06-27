package main.dao.storeReturns;

import java.util.List;

import main.models.storeModels.inputmodels.ReturnId;
import main.models.storeModels.inputmodels.StoreFilters;
import main.models.storeReturnsModels.entities.StoreReturnProductsList;
import main.models.storeReturnsModels.entities.StoreReturnsList;
import main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput;

public interface StoreReturnsDao {

	// Returns the recent 5 store returns
	public boolean saveStoreReturns(StoreReturnsList storeReturnsList);

	// Returns the list of storeReturn products
	public List<StoreReturnProductsList> getStoreReturnsProductsList(ReturnId returnid);

	// Stores the input returns list to database
	public List<StoreReturnsDataOutput> getStoreReturnsList();

	// Filters data based on the storeId, fromDate and toDate
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataIdFrom(StoreFilters storeFilters);

	// Filters data based on the storeId and toDate
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataId(StoreFilters storeFilters);

	// Filters data based on the fromDate and toDate
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataFrom(StoreFilters storeFilters);

	// Filters data based on toDate
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataTo(StoreFilters storeFilters);

}
