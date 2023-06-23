package main.dao.storeReturns;

import java.util.List;

import main.models.storeModels.inputmodels.ReturnId;
import main.models.storeModels.inputmodels.StoreFilters;
import main.models.storeReturnsModels.entities.StoreReturnProductsList;
import main.models.storeReturnsModels.entities.StoreReturnsList;
import main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput;

public interface StoreReturnsDao {

	public boolean saveStoreReturns(StoreReturnsList storeReturnsList);

	public List<StoreReturnProductsList> getStoreReturnsProductsList(ReturnId returnid);

	public List<StoreReturnsDataOutput> getStoreReturnsList();

	List<StoreReturnsDataOutput> getStoreReturnsFilterDataIdFrom(StoreFilters storeFilters);

	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataId(StoreFilters storeFilters);

	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataFrom(StoreFilters storeFilters);

	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataTo(StoreFilters storeFilters);

}
