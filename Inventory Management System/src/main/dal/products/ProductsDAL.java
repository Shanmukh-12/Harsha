package main.dal.products;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import main.dao.products.ProductsDAO;
import main.models.productModels.dto.ProductProfit;
import main.models.productModels.entities.HSNEntityModel;
import main.models.productModels.entities.ProductsCategory;
import main.models.productModels.inputModels.ProductsProductIdInputModel;
import main.models.productModels.outputModels.ProductStockData;

@Component
public class ProductsDAL implements ProductsDAO {

	@PersistenceContext
	private EntityManager entityManager;
    //Getting Product Information Based on Category Id
	@Transactional
	public List<ProductStockData> getProductsByCategory(int categoryId) {
		String queryString = "SELECT new main.models.productModels.outputModels.ProductStockData(p.productId,p.productName,ps.batchNo,ps.productStock,p.productReorderLevel,p.productHsnCode,ps.productSalePrice,ps.productMrp,ps.productCost) FROM Products p JOIN  p.productStocks ps  WHERE p.category = :categoryId";
		TypedQuery<ProductStockData> query = entityManager.createQuery(queryString, ProductStockData.class);
		query.setParameter("categoryId", categoryId);
		List<ProductStockData> productsList = query.getResultList();

		return productsList;
	}

	@Override
	public List<ProductStockData> getProductsByProductId(int selectedProductId) {

		String queryString = "SELECT new main.models.productModels.outputModels.ProductStockData(p.productId, p.productName, ps.batchNo, p.productReorderLevel,p.productHsnCode,ps.productStock, ps.productSalePrice, ps.productMrp, ps.productCost) FROM Products p JOIN p.productStocks ps WHERE  p.productId = :productId";
		TypedQuery<ProductStockData> query = entityManager.createQuery(queryString, ProductStockData.class);
		query.setParameter("productId", selectedProductId);

		return query.getResultList();

	}

	@Override
	public ProductStockData getQuantityandpriceByProductIdOrBatchNo(int selectedProductId, int selectedBatchNo) {
		String queryString = "SELECT new main.models.productModels.outputModels.ProductStockData(p.productId, p.productName, ps.batchNo, ps.productStock,p.productReorderLevel,p.productHsnCode, ps.productSalePrice, ps.productMrp, ps.productCost) FROM Products p JOIN p.productStocks ps WHERE  p.productId = :productId AND ps.batchNo=:batchNo";
		TypedQuery<ProductStockData> query = entityManager.createQuery(queryString, ProductStockData.class);
		query.setParameter("productId", selectedProductId);
		query.setParameter("batchNo", selectedBatchNo);
		return query.getSingleResult();
	}

	@Override
	public List<ProductStockData> getReOrderLevelProducts() {
		String queryString = "SELECT new main.models.productModels.outputModels.ProductStockData(p.productId, p.productName, ps.batchNo, p.productReorderLevel,p.productHsnCode,ps.productStock, ps.productSalePrice, ps.productMrp, ps.productCost) FROM Products p JOIN p.productStocks ps WHERE  p.productId = :productId";
		TypedQuery<ProductStockData> query = entityManager.createQuery(queryString, ProductStockData.class);
		return query.getResultList();
	}

	@Transactional
	public boolean saveCategory(ProductsCategory productsCategory) {
		entityManager.persist(productsCategory);
		return true;
	}

	@Transactional
	public boolean saveHSN(HSNEntityModel hsnEntityModel) {

		entityManager.persist(hsnEntityModel);

		return false;
	}

	@Override
	@Transactional
	public ProductProfit getProfitPercentage(ProductsProductIdInputModel pp) {
		ProductProfit pf = (ProductProfit) entityManager
				.createQuery("select new main.models.productModels.dto.ProductProfit(p.profitPercentage)"
						+ " from Products p where p.productId=:id")
				.setParameter("id", pp.getProductId()).getSingleResult();

		return pf;

	}

}