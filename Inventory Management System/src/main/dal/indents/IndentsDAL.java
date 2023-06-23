package main.dal.indents;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.models.indentModels.entities.ProcurementIndentProductsList;
import main.models.indentModels.entities.ProcurementIndentsList;
import main.models.indentModels.inputModels.FilterInput;
import main.models.indentModels.outputModels.FilteredIndent;
import main.models.indentModels.outputModels.ProcurementIndentProductListData;
import main.models.storeModels.inputmodels.IndentId;

@Component
public class IndentsDAL {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public boolean saveProcurementIndent(ProcurementIndentsList procurementIndentsList) {
		System.out.println("Inside storeIndentsDao");
		System.out.println(procurementIndentsList);
		entityManager.persist(procurementIndentsList);
		List<ProcurementIndentProductsList> pipl;
		pipl = procurementIndentsList.getProductsList();
		for (ProcurementIndentProductsList s : pipl) {
			s.setIndentID(procurementIndentsList.getIndentID());
			System.out.println(s);
			entityManager.persist(s);
		}
		System.out.println(procurementIndentsList);
		return true;
	}

	@Transactional
	public List<ProcurementIndentsList> getAllIndents() {
		List<ProcurementIndentsList> l = entityManager.createQuery("SELECT v FROM ProcurementIndentsList v")
				.getResultList();
		for (ProcurementIndentsList v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

	@Transactional
	public List<ProcurementIndentProductListData> getProcurementIndentProductsList(IndentId indentid) {
		int data = indentid.getIndentId();
		System.out.println(data);
		List<ProcurementIndentProductListData> s = entityManager.createQuery(
				"SELECT NEW main.models.indentModels.outputModels.ProcurementIndentProductListData(e.productId, p.productName, pc.productCategoryName, e.quantity) "
						+ "FROM ProcurementIndentProductsList e "
						+ "JOIN main.models.productModels.entities.Products p ON e.productId = p.productId "
						+ "JOIN main.models.productModels.entities.ProductsCategory pc ON p.category = pc.productCategoryId "
						+ "WHERE e.indentID = :data",
				ProcurementIndentProductListData.class).setParameter("data", data).getResultList();
		for (ProcurementIndentProductListData p : s)
			System.out.println("Inside " + p);

		return s;

	}
	
	@Transactional
	public List<FilteredIndent> getfilterIndents(FilterInput filterInput) {
	    // ...

	    String hql = "SELECT NEW main.models.indentModels.outputModels.FilteredIndent(i.indentID, i.d, i.indentsStatus) FROM IndentsList i WHERE 1 = 1";

	    if (filterInput.getIndentId() != 0) {
	        hql += " AND i.indentID = :indentId";
	    }

	    if (filterInput.getFromDate() != null) {
	        hql += " AND i.d >= :fromDate";
	    }

	    if (filterInput.getToDate() != null) {
	        hql += " AND i.d <= :toDate";
	    }

	    TypedQuery<FilteredIndent> query = entityManager.createQuery(hql, FilteredIndent.class);

	    if (filterInput.getIndentId() != 0) {
	        query.setParameter("indentId", filterInput.getIndentId());
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