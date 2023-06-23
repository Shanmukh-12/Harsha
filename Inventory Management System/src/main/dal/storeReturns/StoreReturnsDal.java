package main.dal.storeReturns;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.storeReturns.StoreReturnsDao;
import main.models.productModels.entities.ProductStock;
import main.models.storeModels.inputmodels.ReturnId;
import main.models.storeModels.inputmodels.StoreFilters;
import main.models.storeReturnsModels.entities.StoreReturnProductsList;
import main.models.storeReturnsModels.entities.StoreReturnsList;
import main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput;

@Component
public class StoreReturnsDal implements StoreReturnsDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public List<StoreReturnsDataOutput> getStoreReturnsList() {

		List<StoreReturnsDataOutput> al = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "ORDER BY e.storeIssueId DESC")
				.setMaxResults(5).getResultList();
		return al;
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

	@Override
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataIdFrom(StoreFilters storeFilters) {

		List<StoreReturnsDataOutput> al = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "where s.storeId=:id and e.date between :fromDate and :toDate "
						+ "ORDER BY e.storeIssueId DESC")
				.setParameter("id", storeFilters.getStoreId()).setParameter("fromDate", storeFilters.getFromDate())
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return al;

	}

	@Override
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataId(StoreFilters storeFilters) {
		List<StoreReturnsDataOutput> al = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "where s.storeId=:id and e.date <= :toDate " + "ORDER BY e.storeIssueId DESC")
				.setParameter("id", storeFilters.getStoreId()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return al;
	}

	@Override
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataFrom(StoreFilters storeFilters) {
		List<StoreReturnsDataOutput> al = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "where e.date between :fromDate and :toDate " + "ORDER BY e.storeIssueId DESC")
				.setParameter("fromDate", storeFilters.getFromDate()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return al;
	}

	@Override
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataTo(StoreFilters storeFilters) {
		List<StoreReturnsDataOutput> al = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "where e.date <= :toDate " + "ORDER BY e.storeIssueId DESC")
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return al;
	}

}
