package main.dao.storeIssues;

import java.util.List;

import main.models.storeIssueModels.entities.StoreIssues;
import main.models.storeIssueModels.outputModels.StoreIssueIds;
import main.models.storeModels.inputmodels.StoreId;

public interface StoreIssueDao {

	public List<StoreIssues> getStoreIds(StoreId sid);
}
