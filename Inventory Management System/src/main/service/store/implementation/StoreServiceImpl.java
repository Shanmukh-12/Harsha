package main.service.store.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.dao.storeIndents.StoreIndentsDao;
import main.dao.storeReturns.StoreReturnsDao;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.inputmodels.StoreFilters;
import main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput;
import main.service.store.interfaces.StoreService;

@Component
public class StoreServiceImpl implements StoreService {

	@Autowired
	// Autowired dependency for StoreIndentsDao
	StoreIndentsDao storeIndentsDao;

	@Autowired
	// Autowired dependency for StoreReturnsDao
	StoreReturnsDao storeReturnsDao;

	@Override
	public List<StoreIndentData> getStoreIndetnsByFilterData(StoreFilters storeFilters) {
		List<StoreIndentData> storeIndents = null;
		if (storeFilters.getStoreId() != 0) {
			if (storeFilters.getIndentStatus().length() > 0) {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with store ID, indent status, and from date
					storeIndents = storeIndentsDao.getStoreIndentsListByIdStatusFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with store ID and indent status
					storeIndents = storeIndentsDao.getStoreIndentsListByIdStatus(storeFilters);
				}
			} else {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with store ID and from date
					storeIndents = storeIndentsDao.getStoreIndentsListByIdFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with store ID
					storeIndents = storeIndentsDao.getStoreIndentsListById(storeFilters);
				}
			}
		} else {
			if (storeFilters.getIndentStatus().length() > 0) {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with indent status and from date
					storeIndents = storeIndentsDao.getStoreIndentsListByStatusFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with indent status
					storeIndents = storeIndentsDao.getStoreIndentsListByStatus(storeFilters);
				}
			} else {
				if (storeFilters.getFromDate() != null) {
					// Fetching store indents by filter data with from date
					storeIndents = storeIndentsDao.getStoreIndentsListByFrom(storeFilters);
				} else {
					// Fetching store indents by filter data with to date
					storeIndents = storeIndentsDao.getStoreIndentsListByTo(storeFilters);
				}
			}
		}
		return storeIndents;
	}

	@Override
	public List<StoreReturnsDataOutput> getStoreReturnsByFilterData(StoreFilters storeFilters) {
		List<StoreReturnsDataOutput> storeReturnsData = null;

		if (storeFilters.getStoreId() != 0) {
			if (storeFilters.getFromDate() != null) {
				// Fetching store returns data based on storeId, toDate and fromDate
				storeReturnsData = storeReturnsDao.getStoreReturnsFilterDataIdFrom(storeFilters);
			} else {
				// Fetching store returns data based on storeId and toDate
				storeReturnsData = storeReturnsDao.getStoreReturnsFilterDataId(storeFilters);
			}
		} else {
			if (storeFilters.getFromDate() != null) {
				// Fetching store returns data based on fromDate and toDate
				storeReturnsData = storeReturnsDao.getStoreReturnsFilterDataFrom(storeFilters);
			} else {
				// Fetching store returns data based on toDate only
				storeReturnsData = storeReturnsDao.getStoreReturnsFilterDataTo(storeFilters);
			}
		}
		return storeReturnsData;
	}

}
