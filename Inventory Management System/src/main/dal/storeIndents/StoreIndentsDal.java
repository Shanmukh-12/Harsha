package main.dal.storeIndents;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.storeIndents.StoreIndentsDao;
import main.models.storeIndentModels.outputmodels.StoreIndentProducts;
import main.models.storeModels.entities.Store;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentProductsList;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.StoreFilters;

@Component
public class StoreIndentsDal implements StoreIndentsDao {

	@PersistenceContext
	EntityManager entityManager; // EntityManager for interacting with the persistence context

	@Override
	@Transactional
	// Returns the recent 5 store indents
	public List<StoreIndentData> getStoreIndentsList() {
		// Retrieves the list of StoreIndentData objects sorted by indentId in descending order
		return entityManager.createQuery("select e from StoreIndentData e  ORDER BY e.indentId DESC").setMaxResults(5)
				.getResultList();
	}

	@Override
	@Transactional
	// Returns the list of storeIndent products
	public List<StoreIndentProducts> getStoreIndentsProductsList(IndentId indentid) {
		int data = indentid.getIndentId();
		// Retrieves the list of StoreIndentProducts objects with specific attributes
		List<StoreIndentProducts> storeIndents = entityManager.createQuery(
				"SELECT NEW main.models.storeIndentModels.outputmodels.StoreIndentProducts(e.productId, p.productName, pc.productCategoryName, e.quantity) "
						+ "FROM StoreIndentProductsList e "
						+ "JOIN main.models.productModels.entities.Products p ON e.productId = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.indentID = :data",
				StoreIndentProducts.class).setParameter("data", data).getResultList();
		return storeIndents;
	}

	@Transactional
	// Stores the input indents list to database
	public boolean saveStoreIndent(StoreIndentsList storeIndentsList) {
		try {
			// Persisting the Store Indents List to the database
			entityManager.persist(storeIndentsList);
			List<StoreIndentProductsList> storeIndents = storeIndentsList.getProductsList();

			// persisting the store indent products to the database
			for (StoreIndentProductsList product : storeIndents) {
				product.setIndentID(storeIndentsList.getIndentID());
				entityManager.persist(product);
			}
			return true;
		}
		// Handling the exception
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	// Getting store Id's
	public List<Store> getStoreIds() {
		// Retrieves a list of Store objects
		List<Store> lst = entityManager.createQuery("SELECT e FROM Store e").getResultList();
		return lst;
	}

	@Override
	// Listing indents based on store id
	public List<StoreIndentData> getIndentsByStoreID(int storeId) {
		return entityManager
				.createQuery("SELECT si FROM StoreIndentData si WHERE si.storeId = :storeId", StoreIndentData.class)
				.setParameter("storeId", storeId).getResultList();
	}

	/*
	 * 
	 * Indent listing filters
	 * 
	 */

	@Override
	// Filters data based on the storeId, status, fromDate and toDate
	public List<StoreIndentData> getStoreIndentsListByIdStatusFrom(StoreFilters storeFilters) {
		List<StoreIndentData> indents = entityManager.createQuery(
				"SELECT e FROM StoreIndentData e where e.storeId=:storeId and e.indentStatus=:status and e.date >= :fromDate AND e.date <= :toDate")
				.setParameter("storeId", storeFilters.getStoreId())
				.setParameter("status", storeFilters.getIndentStatus()).setParameter("toDate", storeFilters.getToDate())
				.setParameter("fromDate", storeFilters.getFromDate()).getResultList();
		return indents;
	}

	@Override
	// Filters data based on the storeId, status and toDate
	public List<StoreIndentData> getStoreIndentsListByIdStatus(StoreFilters storeFilters) {
		List<StoreIndentData> indents = entityManager.createQuery(
				"SELECT e FROM StoreIndentData e where e.storeId=:storeId and e.indentStatus=:status and e.date <= :toDate")
				.setParameter("storeId", storeFilters.getStoreId())
				.setParameter("status", storeFilters.getIndentStatus()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return indents;
	}

	@Override
	// Filters data based on the storeId, fromDate and toDate
	public List<StoreIndentData> getStoreIndentsListByIdFrom(StoreFilters storeFilters) {
		List<StoreIndentData> indents = entityManager.createQuery(
				"SELECT e FROM StoreIndentData e where e.storeId=:storeId and e.date >= :fromDate AND e.date <= :toDate")
				.setParameter("storeId", storeFilters.getStoreId()).setParameter("toDate", storeFilters.getToDate())
				.setParameter("fromDate", storeFilters.getFromDate()).getResultList();
		return indents;
	}

	@Override
	// Filters data based on the storeId, and toDate
	public List<StoreIndentData> getStoreIndentsListById(StoreFilters storeFilters) {
		List<StoreIndentData> indents = entityManager
				.createQuery("SELECT e FROM StoreIndentData e where e.storeId=:storeId and e.date <= :toDate")
				.setParameter("storeId", storeFilters.getStoreId()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return indents;
	}

	@Override
	// Filters data based on the status, fromDate and toDate
	public List<StoreIndentData> getStoreIndentsListByStatusFrom(StoreFilters storeFilters) {
		List<StoreIndentData> indents = entityManager.createQuery(
				"SELECT e FROM StoreIndentData e where e.indentStatus=:status and e.date >= :fromDate AND e.date <= :toDate")
				.setParameter("status", storeFilters.getIndentStatus()).setParameter("toDate", storeFilters.getToDate())
				.setParameter("fromDate", storeFilters.getFromDate()).getResultList();
		return indents;
	}

	@Override
	// Filters data based on the status and toDate
	public List<StoreIndentData> getStoreIndentsListByStatus(StoreFilters storeFilters) {
		List<StoreIndentData> indents = entityManager
				.createQuery("SELECT e FROM StoreIndentData e where e.indentStatus=:status and e.date <= :toDate")
				.setParameter("status", storeFilters.getIndentStatus()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return indents;
	}

	@Override
	// Filters data based on the fromDate and toDate
	public List<StoreIndentData> getStoreIndentsListByFrom(StoreFilters storeFilters) {
		List<StoreIndentData> indents = entityManager
				.createQuery("SELECT e FROM StoreIndentData e where e.date >= :fromDate AND e.date <= :toDate")
				.setParameter("toDate", storeFilters.getToDate()).setParameter("fromDate", storeFilters.getFromDate())
				.getResultList();
		return indents;
	}

	@Override
	// Filters data based on toDate
	public List<StoreIndentData> getStoreIndentsListByTo(StoreFilters storeFilters) {

		List<StoreIndentData> indents = entityManager
				.createQuery("SELECT e FROM StoreIndentData e where e.date <= :toDate")
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return indents;
	}

}
