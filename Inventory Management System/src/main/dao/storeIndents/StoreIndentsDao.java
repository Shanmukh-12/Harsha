package main.dao.storeIndents;

import java.util.List;

import main.models.storeIndentModels.outputmodels.StoreIndentProducts;
import main.models.storeModels.entities.Store;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.StoreFilters;

public interface StoreIndentsDao {

	// Returns the recent 5 store indents
	public List<StoreIndentData> getStoreIndentsList();

	// Returns the list of storeIndent products
	public List<StoreIndentProducts> getStoreIndentsProductsList(IndentId indentid);

	// Stores the input indents list to database
	public boolean saveStoreIndent(StoreIndentsList storeIndentsList);

	// Getting store Id's
	public List<Store> getStoreIds();

	// Listing indents based on store id
	public List<StoreIndentData> getIndentsByStoreID(int selectedStoreId);

	// Filters data based on the storeId, status, fromDate and toDate
	public List<StoreIndentData> getStoreIndentsListByIdStatusFrom(StoreFilters storeFilters);

	// Filters data based on the storeId, status and toDate
	public List<StoreIndentData> getStoreIndentsListByIdStatus(StoreFilters storeFilters);

	// Filters data based on the storeId, fromDate and toDate
	public List<StoreIndentData> getStoreIndentsListByIdFrom(StoreFilters storeFilters);

	// Filters data based on the storeId, and toDate
	public List<StoreIndentData> getStoreIndentsListById(StoreFilters storeFilters);

	// Filters data based on the status, fromDate and toDate
	public List<StoreIndentData> getStoreIndentsListByStatusFrom(StoreFilters storeFilters);

	// Filters data based on the status and toDate
	public List<StoreIndentData> getStoreIndentsListByStatus(StoreFilters storeFilters);

	// Filters data based on the fromDate and toDate
	public List<StoreIndentData> getStoreIndentsListByFrom(StoreFilters storeFilters);

	// Filters data based on toDate
	public List<StoreIndentData> getStoreIndentsListByTo(StoreFilters storeFilters);

}
