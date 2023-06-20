package main.dal.storeIssues;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.dao.storeIssues.StoreIssueDao;
import main.models.storeIssueModels.entities.StoreIssues;
import main.models.storeIssueModels.outputModels.StoreIssueIds;
import main.models.storeModels.inputmodels.StoreId;

@Component
public class StoreIssueDal implements StoreIssueDao{

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public List<StoreIssues> getStoreIds(StoreId sid)
	{
		List<StoreIssues> data = entityManager.createQuery("select e from StoreIssues s where s.storeId=:id")
				.setParameter("id",sid.getStoreId()).getResultList();
		return data;
	}
}
