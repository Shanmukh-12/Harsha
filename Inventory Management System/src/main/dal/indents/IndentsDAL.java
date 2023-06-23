package main.dal.indents;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.models.indentModels.entities.IndentsList;
import main.models.indentModels.entities.ProcurementIndentProductsList;
import main.models.indentModels.entities.ProcurementIndentsList;
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
	public List<IndentsList> filterIndents(int indentId, String fromDate, String toDate) {
        String jpql = "SELECT i FROM IndentsList i WHERE 1=1";

        if (indentId != 0) {
            jpql += " AND i.indentID = :indentId";
        }

        if (fromDate != null && !fromDate.isEmpty()) {
            jpql += " AND i.d >= :fromDate";
        }

        if (toDate != null && !toDate.isEmpty()) {
            jpql += " AND i.d <= :toDate";
        }

        TypedQuery<IndentsList> query = entityManager.createQuery(jpql, IndentsList.class);

        if (indentId != 0) {
            query.setParameter("indentId", indentId);
        }

        if (fromDate != null && !fromDate.isEmpty()) {
            query.setParameter("fromDate", fromDate);
        }

        if (toDate != null && !toDate.isEmpty()) {
            query.setParameter("toDate", toDate);
        }

        List<IndentsList> filteredIndents = query.getResultList();
         System.out.println(filteredIndents);
        return filteredIndents;
    }
	    
	   
	    

	    

}