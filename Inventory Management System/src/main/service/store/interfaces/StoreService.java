package main.service.store.interfaces;

import java.util.List;

import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.inputmodels.StoreFilters;
import main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput;

public interface StoreService {

	public List<StoreIndentData> getStoreIndetnsByFilterData(StoreFilters storeFilters);

	public List<StoreReturnsDataOutput> getStoreReturnsByFilterData(StoreFilters storeFilters);
}
