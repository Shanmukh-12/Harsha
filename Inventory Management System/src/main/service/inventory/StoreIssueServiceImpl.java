package main.service.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.dao.storeIssues.StoreIssueDao;
import main.models.storeIssueModels.inputModels.StoreFilters;
import main.models.storeIssueModels.outputModels.StoreIssuesData;

@Component
public class StoreIssueServiceImpl implements StoreIssueService {

	StoreIssueDao storeIssuesDao;

	@Autowired
	public StoreIssueServiceImpl(StoreIssueDao storeIssuesDao) {
		this.storeIssuesDao = storeIssuesDao;
	}

	@Override
	public List<StoreIssuesData> getStoreIssuesFilters(StoreFilters storeFilters) {
		List<StoreIssuesData> storeIssues = null;
		if (storeFilters.getStoreId() != 0) {
			if (storeFilters.getStoreIssueStatus().length() > 0) {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with store ID, indent status, and from date
					storeIssues = storeIssuesDao.getStoreIssuesListByIdStatusFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with store ID and indent status
					storeIssues = storeIssuesDao.getStoreIssuesListByIdStatus(storeFilters);
				}
			} else {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with store ID and from date
					storeIssues = storeIssuesDao.getStoreIssuesListByIdFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with store ID
					storeIssues = storeIssuesDao.getStoreIssuesListById(storeFilters);
				}
			}
		} else {
			if (storeFilters.getStoreIssueStatus().length() > 0) {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with indent status and from date
					storeIssues = storeIssuesDao.getStoreIssuesListByStatusFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with indent status
					storeIssues = storeIssuesDao.getStoreIssuesListByStatus(storeFilters);
				}
			} else {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with from date
					storeIssues = storeIssuesDao.getStoreIssuesListByFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with to date
					storeIssues = storeIssuesDao.getStoreIssuesListByTo(storeFilters);
				}
			}
		}
		return storeIssues;
	}

}
