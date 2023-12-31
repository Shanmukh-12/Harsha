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
	public List<StoreIssueData> getStoreIds(StoreId storeId) {
		int storeIds = storeId.getStoreId();
		List<StoreIssueData> data = entityManager
				.createQuery("select s from StoreIssueData s where s.storeId = :id", StoreIssueData.class)
				.setParameter("id", storeIds).getResultList();
		return data;//Returning StoreIssueId,storeIssueDate,storeIssueStatus,amount
	}
	//Getting all StoreIssue Infromation
	@Override
	public List<StoreIssuesData> getAllStoreIssues() {
		List<StoreIssuesData> storeIssue = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p",
				StoreIssuesData.class).getResultList();

		return storeIssue;//Returning storeID,StoreIssueId,storeIssueDate,storeIssueStatus,amount
	}
    //Getting StoreIssue Products Information
	@Override
	public List<StoreIssueProducts> getStoreIssuesProductsList(StoreIssueIds storeIssueIds) {
		// TODO Auto-generated method stub
		int storeIssueId = storeIssueIds.getStoreIssueId();
		List<StoreIssueProducts> storeIssueProducts = entityManager.createQuery(
				"SELECT NEW main.models.storeIssueModels.outputModels.StoreIssueProducts(e.productId,e.batchNo, p.productName, pc.productCategoryName, e.issuedQuantity) "
						+ "FROM main.models.storeIssueModels.entities.StoreIssueProduct e "
						+ "JOIN main.models.productModels.entities.Products p ON e.productId = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.storeIssueId = :data",
				StoreIssueProducts.class) // Change the result type here
				.setParameter("data", storeIssueId).getResultList();

		return storeIssueProducts;//Returning ProductID,BatchNo,ProductName,Product Category,Quantity

	}
    //Getting Filtered Data based on To date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByTo(StoreFilters storeFilters) {
		List<StoreIssuesData> listStoreIssues = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeIssueDate<=:toDate")
				.setParameter("toDate", storeFilters.getToDate()).getResultList();

		return listStoreIssues;//Returning StoreIssue Information based on the selected Filter
	}
    //Getting Filtered Data Based on from Date,To date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByFrom(StoreFilters storeFilters) {
		List<StoreIssuesData> listStoreIssues = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeIssueDate>=:fromDate and p.storeIssueDate<=:toDate")
				.setParameter("fromDate", storeFilters.getFromDate()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return listStoreIssues;

	}
    //Getting Filtered Information based on StoreIssueStatus,To date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByStatus(StoreFilters storeFilters) {
		List<StoreIssuesData> listStoreIssues = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeIssueStatus=:storeIssueStatus and p.storeIssueDate<=:toDate")
				.setParameter("storeIssueStatus", storeFilters.getStoreIssueStatus())
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return listStoreIssues;

	}
    //Getting Filtered Information based on StoreIssueStatus,From date,To date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByStatusFrom(StoreFilters storeFilters) {
		List<StoreIssuesData> listStoreIssues = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId)"
						+ " from StoreIssue p "
						+ "where p.storeIssueDate<=:toDate and p.storeIssueDate>=:fromDate and p.storeIssueStatus=:storeIssueStatus")
				.setParameter("storeIssueStatus", storeFilters.getStoreIssueStatus())
				.setParameter("toDate", storeFilters.getToDate()).setParameter("fromDate", storeFilters.getFromDate())
				.getResultList();
		return listStoreIssues;
	}
    //Getting Filtered data based on StoreID
	@Override
	public List<StoreIssuesData> getStoreIssuesListById(StoreFilters storeFilters) {
		List<StoreIssuesData> listStoreIssues = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeId=:storeId and p.storeIssueDate<=:toDate")
				.setParameter("storeId", storeFilters.getStoreId()).setParameter("toDate", storeFilters.getToDate())
				.getResultList();
		return listStoreIssues;

	}
   //Getting Filtered Information based on StoreId,From date
	@Override
	public List<StoreIssuesData> getStoreIssuesListByIdFrom(StoreFilters storeFilters) {
		List<StoreIssuesData> listStoreIssues = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeId=:storeId and p.storeIssueDate<=:toDate and p.storeIssueDate>=:fromDate")
				.setParameter("storeId", storeFilters.getStoreId()).setParameter("fromDate", storeFilters.getFromDate())
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return listStoreIssues;
	}
   //Getting Filtered Information based on storeId,StoreIssue Status 
	@Override
	public List<StoreIssuesData> getStoreIssuesListByIdStatus(StoreFilters storeFilters) {
		List<StoreIssuesData> listStoreIssues = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId) from StoreIssue p where p.storeId=:storeId and p.storeIssueDate<=:toDate and p.storeIssueStatus=:storeIssueStatus")
				.setParameter("storeId", storeFilters.getStoreId())
				.setParameter("storeIssueStatus", storeFilters.getStoreIssueStatus())
				.setParameter("toDate", storeFilters.getToDate()).getResultList();
		return listStoreIssues;
	}
   //Getting Filtered Information based on StoreId,StoreIssue Status,From Date 
	@Override
	public List<StoreIssuesData> getStoreIssuesListByIdStatusFrom(StoreFilters storeFilters) {
		List<StoreIssuesData> listStoreIssues = entityManager.createQuery(
				"select new main.models.storeIssueModels.outputModels.StoreIssuesData(p.storeIssueId,p.storeIssueDate,p.amount,p.storeIssueStatus,p.storeId)"
						+ " from StoreIssue p "
						+ "where p.storeIssueDate<=:toDate and p.storeIssueDate>=:fromDate and p.storeId=:storeId and p.storeIssueStatus=:storeIssueStatus")
				.setParameter("storeId", storeFilters.getStoreId())
				.setParameter("storeIssueStatus", storeFilters.getStoreIssueStatus())
				.setParameter("toDate", storeFilters.getToDate()).setParameter("fromDate", storeFilters.getFromDate())
				.getResultList();
		return listStoreIssues;
	}
   //Getting PRoductStock Information based on ProductId
	@Override
	@Transactional
	public List<ProductStock> getBatchNumbers(ProductsProductIdInputModel productId) {
		List<ProductStock> productStock = entityManager
				.createQuery("SELECT p FROM ProductStock p WHERE p.productId = :productId", ProductStock.class)
				.setParameter("productId", productId.getProductId()).getResultList();
		return productStock;
	}
    //Saving StoreIssue Information
	@Transactional
	public boolean saveStoreInfo(StoreIssues storeIssues) {
	    try {
	        storeIssues.setStatus("Approved"); // Set the status of the storeIssues object to "Approved"
	        entityManager.persist(storeIssues); // Save the storeIssues object in the database
	        List<main.models.storeIssueModels.entities.StoreIssueProducts> sild = storeIssues.getStoreProducts(); // Retrieve the list of StoreIssueProducts associated with the storeIssues object

	        for (main.models.storeIssueModels.entities.StoreIssueProducts storeIssueProducts : sild) {
	        	storeIssueProducts.setStoreIssueId(storeIssues.getStoreIssueId()); // Set the storeIssueId for each StoreIssueProducts object
	        	storeIssueProducts.setStoreIssues(storeIssues); // Set the relationship with the saved StoreIssues instance
	            entityManager.persist(storeIssueProducts); // Save each StoreIssueProducts object in the database

	            ProductStock productStock = entityManager
	                .createQuery("select e from ProductStock e where e.productId=:productId and e.batchNo=:batchNo",
	                        ProductStock.class)
	                .setParameter("productId", storeIssueProducts.getProductId())
	                .setParameter("batchNo", storeIssueProducts.getBatchNo())
	                .getSingleResult(); // Retrieve the ProductStock object associated with the productId and batchNo of the StoreIssueProducts

	            int productsStock = productStock.getProductStock(); // Retrieve the current product stock quantity
	            int issuedQuantity = storeIssueProducts.getQuantity(); // Retrieve the issued quantity from the StoreIssueProducts object

	            if (productsStock >= issuedQuantity) { // Check if there is sufficient product stock
	            	productStock.setProductStock(productsStock - issuedQuantity); // Update the product stock quantity
	            } else {
	                throw new IllegalArgumentException("Insufficient product stock for productId: " + storeIssueProducts.getProductId()
	                        + " and batchNo: " + storeIssueProducts.getBatchNo()); // Throw an exception if there is insufficient product stock
	            }
	        }
	        return true; // Return true if the storeIssues and associated StoreIssueProducts are successfully saved
	    } catch (Exception e) {
	        e.printStackTrace(); // Print the stack trace if an exception occurs
	        return false; // Return false if there was an error while saving the storeIssues and associated StoreIssueProducts
	    }
	}

}
