package main.dao.storeIssues;

import java.util.List;

import main.models.storeIssueModels.entities.StoreIssueData;
import main.models.storeModels.inputmodels.StoreId;

public interface StoreIssueDao {

	public List<StoreIssueData> getStoreIds(StoreId sid);
}
