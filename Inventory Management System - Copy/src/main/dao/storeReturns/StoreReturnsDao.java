package main.dao.storeReturns;

import java.util.List;

import main.models.storeModels.entities.Store;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentProductsList;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.entities.StoreReturnProductsList;
import main.models.storeModels.entities.StoreReturnsData;
import main.models.storeModels.entities.StoreReturnsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.ReturnId;
import main.models.storeModels.inputmodels.StoreFilters;

public interface StoreReturnsDao {

	public boolean saveStoreReturns(StoreReturnsList storeReturnsList);
	public List<StoreReturnProductsList> getStoreReturnsProductsList(ReturnId returnid) ;
	public List<StoreReturnsData> getStoreReturnsList();


}
