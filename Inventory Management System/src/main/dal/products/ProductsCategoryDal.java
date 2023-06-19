package main.dal.products;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.products.ProductCategoryDAO;
import main.models.productModels.entities.ProductsCategory;

@Component
public class ProductsCategoryDal implements ProductCategoryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public List<ProductsCategory> getProductCategories() {
		return entityManager.createQuery("SELECT pc FROM ProductsCategory pc", ProductsCategory.class).getResultList();
	}
}
