package main.dal.inventoryIndents;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import main.dao.inventoryIndents.InventoryIndentsDAO;
import main.models.indentModels.entities.InventoryIndentProductsList;
import main.models.indentModels.entities.InventoryIndentsList;
import main.models.indentModels.inputModels.FilterInput;
import main.models.indentModels.outputModels.FilteredIndent;
import main.models.indentModels.outputModels.InventoryIndentProductListData;
import main.models.storeModels.inputmodels.IndentId;
@Component
public class InventoryIndentsDAL implements InventoryIndentsDAO {

	@PersistenceContext
	private EntityManager entityManager;

//It persist the indents created by the inventory team to the procurement team.
	@Transactional
	public boolean saveInventoryIndent(InventoryIndentsList inventoryIndentsList) throws Exception{
	    try {
	        System.out.println("Inside storeIndentsDao");
	        System.out.println(inventoryIndentsList);
	        entityManager.persist(inventoryIndentsList);
	        List<InventoryIndentProductsList> indentProductList;
	        indentProductList = inventoryIndentsList.getProductsList();
	        for (InventoryIndentProductsList inventoryIndentProductListObject : indentProductList) {
	            inventoryIndentProductListObject.setIndentID(inventoryIndentsList.getIndentID());
	            entityManager.persist(inventoryIndentProductListObject);
	        }
	        return true;
	    } catch (PersistenceException e) {
	        throw new PersistenceException("Failed to persist inventory indent data", e);
	
	}
	}



// It Returns All Indents In the Indents Created by the Inventory.
	@Transactional
	public List<InventoryIndentsList> getAllIndents() throws Exception {
	try {
	List<InventoryIndentsList> indentsList = entityManager.createQuery("SELECT v FROM InventoryIndentsList v")
	.getResultList();
	return indentsList;
	} catch (PersistenceException ex) {
	throw new Exception("Failed to retrieve all indents", ex);


	}
	}

// It Returns Indents Product List Data By taking Input Indent ID.
	@Transactional
	public List<InventoryIndentProductListData> getInventoryIndentProductsList(IndentId indentId) throws Exception {
	try {
	int data = indentId.getIndentId();
	List<InventoryIndentProductListData> indentsProductListData = entityManager.createQuery(
	"SELECT NEW main.models.indentModels.outputModels.InventoryIndentProductListData(e.productId, p.productName, pc.productCategoryName, e.quantity) "
	+ "FROM InventoryIndentProductsList e "
	+ "JOIN main.models.productModels.entities.Products p ON e.productId = p.productId "
	+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
	+ "WHERE e.indentID = :data",
	InventoryIndentProductListData.class).setParameter("data", data).getResultList();
	return indentsProductListData;
	} catch (PersistenceException ex) {
	throw new Exception("Failed to retrieve indent products list", ex);
	} catch (IllegalArgumentException ex) {
	throw new Exception("Invalid argument for indent ID", ex);
	}
	}

	
// It Returns the Filtered Indents by Taking Filtered Inputs those are IndentStatus,FromDate and ToDate.
	@Transactional
	public List<FilteredIndent> getfilterIndents(FilterInput filterInput) throws Exception{
	    // ...
try {
	    String hibernetQuery = "SELECT NEW main.models.indentModels.outputModels.FilteredIndent(i.indentID, i.d, i.indentsStatus) FROM IndentsList i WHERE 1 = 1";

	  // Taking only Indent Status as Input.
	    if (filterInput.getIndentStatus()!= null) {
	    	hibernetQuery += " AND i.indentsStatus = :indentStatus";
	    }
    
	 // Taking Input as FromDate 
	    if (filterInput.getFromDate() != null) {
	    	hibernetQuery += " AND i.d >= :fromDate";
	    }
	    
     // Taking Input ToDate
	    if (filterInput.getToDate() != null) {
	    	hibernetQuery += " AND i.d <= :toDate";
	    }

	    TypedQuery<FilteredIndent> query = entityManager.createQuery(hibernetQuery, FilteredIndent.class);

	    //Setting the IndentStatus as the Parameter
	    if (filterInput.getIndentStatus()!= null) {
	        query.setParameter("indentStatus", filterInput.getIndentStatus());
	    }

	    //Setting the From Date as the Parameter
	    if (filterInput.getFromDate() != null) {
	        query.setParameter("fromDate", filterInput.getFromDate());
	    }
       
	    //Setting the To Date as the Parameter
	    if (filterInput.getToDate() != null) {
	        query.setParameter("toDate", filterInput.getToDate());
	    }

	    return query.getResultList();
	    
	} catch (Exception ex) {
	    throw new Exception("Failed to retrieve filtered indents", ex);
	}
	}

	    

	    

}