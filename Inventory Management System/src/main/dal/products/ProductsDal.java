package main.dal.products;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.products.ProductsDao;
import main.models.productModels.outputModels.ProductStockData;

@Component
public class ProductsDal implements ProductsDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public List<ProductStockData> getProductsByCategory(int categoryId) {
		String queryString = "SELECT new main.models.productModels.outputModels.ProductStockData(p.productId,p.productName,ps.batchNo,ps.productStock,ps.productSalePrice,ps.productMrp,ps.productCost) FROM Products p JOIN  p.productStocks ps  WHERE p.category = :categoryId";
		TypedQuery<ProductStockData> query = entityManager.createQuery(queryString, ProductStockData.class);
		query.setParameter("categoryId", categoryId);
		List<ProductStockData> productsList = query.getResultList();

		return productsList;
	}

}
