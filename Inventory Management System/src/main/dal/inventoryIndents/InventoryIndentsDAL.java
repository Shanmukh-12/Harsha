package main.dal.inventoryIndents;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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

	@Transactional
	public boolean saveInventoryIndent(InventoryIndentsList inventoryIndentsList) {
		System.out.println("Inside storeIndentsDao");
		System.out.println(inventoryIndentsList);
		entityManager.persist(inventoryIndentsList);
		List<InventoryIndentProductsList> pipl;
		pipl = inventoryIndentsList.getProductsList();
		for (InventoryIndentProductsList s : pipl) {
			s.setIndentID(inventoryIndentsList.getIndentID());
			System.out.println(s);
			entityManager.persist(s);
		}
		System.out.println(inventoryIndentsList);
		return true;
	}

	@Transactional
	public List<InventoryIndentsList> getAllIndents() {
		List<InventoryIndentsList> l = entityManager.createQuery("SELECT v FROM InventoryIndentsList v")
				.getResultList();
		for (InventoryIndentsList v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

	@Transactional
	public List<InventoryIndentProductListData> getInventoryIndentProductsList(IndentId indentid) {
		int data = indentid.getIndentId();
		System.out.println(data);
		List<InventoryIndentProductListData> s = entityManager.createQuery(
				"SELECT NEW main.models.indentModels.outputModels.InventoryIndentProductListData(e.productId, p.productName, pc.productCategoryName, e.quantity) "
						+ "FROM InventoryIndentProductsList e "
						+ "JOIN main.models.productModels.entities.Products p ON e.productId = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.indentID = :data",
				InventoryIndentProductListData.class).setParameter("data", data).getResultList();
		for (InventoryIndentProductListData p : s)
			System.out.println("Inside " + p);

		return s;

	}
	
	@Transactional
	public List<FilteredIndent> getfilterIndents(FilterInput filterInput) {
	    // ...

	    String hql = "SELECT NEW main.models.indentModels.outputModels.FilteredIndent(i.indentID, i.d, i.indentsStatus) FROM IndentsList i WHERE 1 = 1";

	    if (filterInput.getIndentStatus()!= null) {
	        hql += " AND i.indentsStatus = :indentStatus";
	    }

	    if (filterInput.getFromDate() != null) {
	        hql += " AND i.d >= :fromDate";
	    }

	    if (filterInput.getToDate() != null) {
	        hql += " AND i.d <= :toDate";
	    }

	    TypedQuery<FilteredIndent> query = entityManager.createQuery(hql, FilteredIndent.class);

	    if (filterInput.getIndentStatus()!= null) {
	        query.setParameter("indentStatus", filterInput.getIndentStatus());
	    }

	    if (filterInput.getFromDate() != null) {
	        query.setParameter("fromDate", filterInput.getFromDate());
	    }

	    if (filterInput.getToDate() != null) {
	        query.setParameter("toDate", filterInput.getToDate());
	    }

	    return query.getResultList();
	}

	    

	    

}