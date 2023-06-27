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
	// Returns the recent 5 store returns
	public List<StoreReturnsDataOutput> getStoreReturnsList() {

		List<StoreReturnsDataOutput> storeReturns = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "ORDER BY e.storeIssueId DESC")
				.setMaxResults(5).getResultList();
		return storeReturns;
	}

	@Override
	@Transactional
	// Returns the list of storeReturn products
	public List<StoreReturnProductsList> getStoreReturnsProductsList(ReturnId returnid) {
		int data = returnid.getReturnId();
		List<StoreReturnProductsList> storeReturnProducts = entityManager
				.createQuery("select e from StoreReturnProductsList e where e.returnId = :data",
						StoreReturnProductsList.class)
				.setParameter("data", data).getResultList();
		return storeReturnProducts;
	}

	@Override
	@Transactional
	// Stores the input returns list to database
	public boolean saveStoreReturns(StoreReturnsList storeReturnsList) {
		try {
			// Persisting the Store Returns List to the database
			entityManager.persist(storeReturnsList);
			List<StoreReturnProductsList> storeReturnProductsList = storeReturnsList.getProductsList();
			for (StoreReturnProductsList product : storeReturnProductsList) {
				product.setReturnId(storeReturnsList.getReturnId());
				entityManager.persist(product);

				// persisting the store return products to the database
				ProductStock productStock = (ProductStock) entityManager
						.createQuery("select s from ProductStock s where s.productId=:prodId and s.batchNo=:batchNo")
						.setParameter("prodId", product.getProductId()).setParameter("batchNo", product.getBatchNo())
						.getSingleResult();
				productStock.setProductStock(productStock.getProductStock() + product.getQuantity());
			}
			return true;
		}
		// Handling the exception
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 
	 * Store Returns filters
	 * 
	 */

	@Override
	// Filters data based on the storeId, fromDate and toDate
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataIdFrom(StoreFilters storeFilters) {

		List<StoreReturnsDataOutput> reutrnsData = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "where s.storeId=:id and e.date between :fromDate and :toDate "
						+ "ORDER BY e.storeIssueId DESC")
				.setParameter("id", storeFilters.getStoreId()).setParameter("fromDate", storeFilters.getFromDate())
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return reutrnsData;

	}

	@Override
	// Filters data based on the storeId and toDate
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataId(StoreFilters storeFilters) {
		List<StoreReturnsDataOutput> reutrnsData = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "where s.storeId=:id and e.date <= :toDate " + "ORDER BY e.storeIssueId DESC")
				.setParameter("id", storeFilters.getStoreId()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return reutrnsData;
	}

	@Override
	// Filters data based on the fromDate and toDate
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataFrom(StoreFilters storeFilters) {
		List<StoreReturnsDataOutput> reutrnsData = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "where e.date between :fromDate and :toDate " + "ORDER BY e.storeIssueId DESC")
				.setParameter("fromDate", storeFilters.getFromDate()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return reutrnsData;
	}

	@Override
	// Filters data based on toDate
	public List<StoreReturnsDataOutput> getStoreReturnsFilterDataTo(StoreFilters storeFilters) {
		List<StoreReturnsDataOutput> reutrnsData = entityManager.createQuery(
				"select new main.models.storeReturnsModels.outputModels.StoreReturnsDataOutput(e.returnId,s.storeId,e.date,e.storeIssueId) "
						+ "from StoreReturnsData e  "
						+ "Join main.models.storeIssueModels.entities.StoreIssueData s on e.storeIssueId = s.storeIssueId "
						+ "where e.date <= :toDate " + "ORDER BY e.storeIssueId DESC")
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return reutrnsData;
	}

}
