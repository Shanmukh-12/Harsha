package main.dal.storeReturns;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.storeReturns.StoreReturnsDao;
import main.models.productModels.entities.ProductStock;
import main.models.storeModels.inputmodels.ReturnId;
import main.models.storeReturnsModels.entities.StoreReturnProductsList;
import main.models.storeReturnsModels.entities.StoreReturnsData;
import main.models.storeReturnsModels.entities.StoreReturnsList;

@Component
public class StoreReturnsDal implements StoreReturnsDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public List<StoreReturnsData> getStoreReturnsList() {
		return entityManager.createQuery("select e from StoreReturnsData e  ORDER BY e.storeIssueId DESC").setMaxResults(5)
				.getResultList();

	}

	@Override
	@Transactional
	public List<StoreReturnProductsList> getStoreReturnsProductsList(ReturnId returnid) {
		int data = returnid.getReturnId();
		System.out.println(data);
		List<StoreReturnProductsList> s = entityManager
				.createQuery("select e from StoreReturnProductsList e where e.returnId = :data",
						StoreReturnProductsList.class)
				.setParameter("data", data).getResultList();
		return s;
	}

	@Override
	@Transactional
	public boolean saveStoreReturns(StoreReturnsList storeReturnsList) {
		System.out.println("Inside storeReturnsDao");
		System.out.println(storeReturnsList);
		entityManager.persist(storeReturnsList);
		List<StoreReturnProductsList> sipl = storeReturnsList.getProductsList();
		for (StoreReturnProductsList s : sipl) {
			s.setReturnId(storeReturnsList.getReturnId());
			entityManager.persist(s);

			ProductStock ips = (ProductStock) entityManager
					.createQuery("select s from ProductStock s where s.productId=:prodId and s.batchNo=:batchNo")
					.setParameter("prodId", s.getProductId()).setParameter("batchNo", s.getBatchNo()).getSingleResult();
			ips.setProductStock(ips.getProductStock() + s.getQuantity());
		}
		return true;
	}

}
