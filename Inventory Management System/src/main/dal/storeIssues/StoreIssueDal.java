package main.dal.storeIssues;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.storeIssues.StoreIssueDao;
import main.models.productModels.entities.ProductStock;
import main.models.productModels.inputModels.ProductsProductIdInputModel;
import main.models.storeIssueModels.entities.StoreIssueData;
import main.models.storeIssueModels.entities.StoreIssues;
import main.models.storeIssueModels.inputModels.StoreFilters;
import main.models.storeIssueModels.outputModels.StoreIssueIds;
import main.models.storeIssueModels.outputModels.StoreIssueProducts;
import main.models.storeIssueModels.outputModels.StoreIssuesData;
import main.models.storeModels.inputmodels.StoreId;

@Component
public class StoreIssueDal implements StoreIssueDao {

	@PersistenceContext
	EntityManager entityManager;
    //Getting StoreIssue Information Based on storeID
	@Transactional
	public List<StoreIssueData> getStoreIds(StoreId sid) {
		int value = sid.getStoreId();
		System.out.println(value);
		List<StoreIssueData> data = entityManager
				.createQuery("select s from StoreIssueData s where s.storeId = :id", StoreIssueData.class)
				.setParameter("id", value).getResultList();
		System.out.println("data is : " + data);
		return data;
	}

	/*
	 * @Transactional public List<StoreIssues> getStoreIds(StoreId sid) { List<StoreIssues> data =
	 * entityManager.createQuery("select e from StoreIssues s where s.storeId=:id") .setParameter("id",
	 * sid.getStoreId()).getResultList(); return data; }
	 */
	//Getting all StoreIssue Infromation
	@Override
	public List<StoreIssuesData> getAllStoreIssues() {
		List<StoreIssuesData> storeIssue = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p",
				StoreIssuesData.class).getResultList();
		for (StoreIssuesData s : storeIssue) {
			System.out.println(s);
		}
		return storeIssue;
	}
    //Getting StoreIssue Products Information
	@Override
	public List<StoreIssueProducts> getStoreIssuesProductsList(StoreIssueIds storeIssueIds) {
		// TODO Auto-generated method stub
		int data = storeIssueIds.getStoreIssueId();
		System.out.println(data);
		List<StoreIssueProducts> s = entityManager.createQuery(
				"SELECT NEW main.models.storeIssueModels.outputModels.StoreIssueProducts(e.productId,e.batchNo, p.productName, pc.productCategoryName, e.issuedQuantity) "
						+ "FROM main.models.storeIssueModels.entities.StoreIssueProduct e "
						+ "JOIN main.models.productModels.entities.Products p ON e.productId = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.storeIssueId = :data",
				StoreIssueProducts.class) // Change the result type here
				.setParameter("data", data).getResultList();

		return s;

	}
    //Getting Filtered Data based on To date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByTo(StoreFilters storeFilters) {
		List<StoreIssuesData> lst = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeIssueDate<=:toDate")
				.setParameter("toDate", storeFilters.getToDate()).getResultList();

		return lst;
	}
    //Getting Filtered Data Based on from Date,To date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByFrom(StoreFilters storeFilters) {
		List<StoreIssuesData> lst = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeIssueDate>=:fromDate and p.storeIssueDate<=:toDate")
				.setParameter("fromDate", storeFilters.getFromDate()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return lst;

	}
    //Getting Filtered Information based on StoreIssueStatus,To date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByStatus(StoreFilters storeFilters) {
		List<StoreIssuesData> lst = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeIssueStatus=:storeIssueStatus and p.storeIssueDate<=:toDate")
				.setParameter("storeIssueStatus", storeFilters.getStoreIssueStatus())
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return lst;

	}
    //Getting Filtered Information based on StoreIssueStatus,From date,To date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByStatusFrom(StoreFilters storeFilters) {
		List<StoreIssuesData> lst = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId)"
						+ " from StoreIssue p "
						+ "where p.storeIssueDate<=:toDate and p.storeIssueDate>=:fromDate and p.storeIssueStatus=:storeIssueStatus")
				.setParameter("storeIssueStatus", storeFilters.getStoreIssueStatus())
				.setParameter("toDate", storeFilters.getToDate()).setParameter("fromDate", storeFilters.getFromDate())
				.getResultList();
		return lst;
	}
    //Getting Filtered data based on StoreID
	@Override
	public List<StoreIssuesData> getStoreIssuesListById(StoreFilters storeFilters) {
		List<StoreIssuesData> lst = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeId=:storeId and p.storeIssueDate<=:toDate")
				.setParameter("storeId", storeFilters.getStoreId()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return lst;

	}
   //Getting Filtered Information based on StoreId,From date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByIdFrom(StoreFilters storeFilters) {
		List<StoreIssuesData> lst = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeId=:storeId and p.storeIssueDate<=:toDate and p.storeIssueDate>=:fromDate")
				.setParameter("storeId", storeFilters.getStoreId()).setParameter("fromDate", storeFilters.getFromDate())
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return lst;
	}
   //Getting Filtered Information based on storeId,StoreIssue Status 
	@Override
	public List<StoreIssuesData> getStoreIssuesListByIdStatus(StoreFilters storeFilters) {
		List<StoreIssuesData> lst = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeId=:storeId and p.storeIssueDate<=:toDate and p.storeIssueStatus=:storeIssueStatus")
				.setParameter("storeId", storeFilters.getStoreId())
				.setParameter("storeIssueStatus", storeFilters.getStoreIssueStatus())
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return lst;
	}
   //Getting Filtered Information based on StoreId,StoreIssue Status,From Date 
	@Override
	public List<StoreIssuesData> getStoreIssuesListByIdStatusFrom(StoreFilters storeFilters) {
		List<StoreIssuesData> lst = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId)"
						+ " from StoreIssue p "
						+ "where p.storeIssueDate<=:toDate and p.storeIssueDate>=:fromDate and p.storeId=:storeId and p.storeIssueStatus=:storeIssueStatus")
				.setParameter("storeId", storeFilters.getStoreId())
				.setParameter("storeIssueStatus", storeFilters.getStoreIssueStatus())
				.setParameter("toDate", storeFilters.getToDate()).setParameter("fromDate", storeFilters.getFromDate())
				.getResultList();
		return lst;
	}
   //Getting PRoductStock Information based on ProductId
	@Override
	@Transactional
	public List<ProductStock> getBatchNumbers(ProductsProductIdInputModel productId) {
		System.out.println("Inside getBatchNumbers " + productId.getProductId());
		List<ProductStock> query = entityManager
				.createQuery("SELECT p FROM ProductStock p WHERE p.productId = :productId", ProductStock.class)
				.setParameter("productId", productId.getProductId()).getResultList();
		System.out.println("after  " + query);
		return query;
	}
    //Saving StoreIssue Information
	@Transactional
	public boolean saveStoreInfo(StoreIssues storeIssues) {
		try {
			System.out.println(storeIssues);
			storeIssues.setStatus("Approved");
			entityManager.persist(storeIssues);
			System.out.println(storeIssues.getStoreIssueId());
			System.out.println(storeIssues.getStoreProducts());
			List<main.models.storeIssueModels.entities.StoreIssueProducts> sild = storeIssues.getStoreProducts();
			for (main.models.storeIssueModels.entities.StoreIssueProducts s : sild) {
				s.setStoreIssueId(storeIssues.getStoreIssueId());
				s.setStoreIssues(storeIssues); // Set the relationship with the saved StoreIssues instance
				System.out.println(s);
				entityManager.persist(s);

				ProductStock ips = entityManager
						.createQuery("select e from ProductStock e where e.productId=:productId and e.batchNo=:batchNo",
								ProductStock.class)
						.setParameter("productId", s.getProductId()).setParameter("batchNo", s.getBatchNo())
						.getSingleResult();

				int productStock = ips.getProductStock();
				int issuedQuantity = s.getQuantity();

				if (productStock >= issuedQuantity) {
					ips.setProductStock(productStock - issuedQuantity);
					System.out.println(ips);
				} else {
					throw new IllegalArgumentException("Insufficient product stock for productId: " + s.getProductId()
							+ " and batchNo: " + s.getBatchNo());
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
