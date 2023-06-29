package main.service.inventory;

import java.util.List;

import main.models.storeIssueModels.inputModels.StoreFilters;
import main.models.storeIssueModels.outputModels.StoreIssuesData;

public interface StoreIssueService {

	List<StoreIssuesData> getStoreIssuesFilters(StoreFilters storeFilters);

}
