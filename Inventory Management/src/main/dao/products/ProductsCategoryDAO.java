package main.dao.products;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import main.models.adminModels.ProductsCategory;
import main.models.indentModels.ProcurementIndentsList;

@Component
public class ProductsCategoryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public List<ProductsCategory> getProductCategories() {
		List<ProductsCategory> l = entityManager.createQuery("SELECT v FROM ProductsCategory v").getResultList();
		for (ProductsCategory v : l) {
			System.out.println(v.toString());
		}
		return l;
	}


}
