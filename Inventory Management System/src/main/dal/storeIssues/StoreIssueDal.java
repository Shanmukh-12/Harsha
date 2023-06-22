package main.dal.storeIssues;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.storeIssues.StoreIssueDao;
import main.models.storeIssueModels.entities.StoreIssueData;
import main.models.storeModels.inputmodels.StoreId;

@Component
public class StoreIssueDal implements StoreIssueDao {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public List<StoreIssueData> getStoreIds(StoreId sid) {
		int value = sid.getStoreId();
		System.out.println(value);
		List<StoreIssueData> data = entityManager
				.createQuery("select s from StoreIssueData s where s.storeId = :id", StoreIssueData.class)
				.setParameter("id", value).getResultList();
		System.out.println("data is : "+data);
		return data;
	}
}
