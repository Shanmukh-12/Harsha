package main.dao.storeIndents;

import java.util.List;

import main.models.storeIndentModels.outputmodels.StoreIndentProducts;
import main.models.storeModels.entities.Store;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.StoreFilters;

public interface StoreIndentsDao {

	public List<StoreIndentData> getStoreIndentsList();

	public List<StoreIndentProducts> getStoreIndentsProductsList(IndentId indentid);

	public boolean saveStoreIndent(StoreIndentsList storeIndentsList);

	public List<Store> getStoreIds();

	public List<StoreIndentData> getStoreIndentsListByIdStatusFrom(StoreFilters storeFilters);

	public List<StoreIndentData> getStoreIndentsListByIdStatus(StoreFilters storeFilters);

	public List<StoreIndentData> getStoreIndentsListByIdFrom(StoreFilters storeFilters);

	public List<StoreIndentData> getStoreIndentsListById(StoreFilters storeFilters);

	public List<StoreIndentData> getStoreIndentsListByStatusFrom(StoreFilters storeFilters);

	public List<StoreIndentData> getStoreIndentsListByStatus(StoreFilters storeFilters);

	public List<StoreIndentData> getStoreIndentsListByFrom(StoreFilters storeFilters);

	List<StoreIndentData> getStoreIndentsListByTo(StoreFilters storeFilters);

	public List<StoreIndentData> getIndentsByStoreID(int selectedStoreId);

}
