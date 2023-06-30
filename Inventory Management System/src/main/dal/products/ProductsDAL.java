package main.dal.products;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import main.dao.products.ProductsDAO;
import main.models.productModels.dto.ProductProfit;
import main.models.productModels.entities.HSNEntityModel;
import main.models.productModels.entities.ProductsCategory;
import main.models.productModels.inputModels.CategoryRequest;
import main.models.productModels.inputModels.ProductsProductIdInputModel;
import main.models.productModels.inputModels.ProductsProductIdandBatchNoInputModel;
import main.models.productModels.outputModels.ProductIdListOutput;
import main.models.productModels.outputModels.ProductStockData;
import main.models.productModels.outputModels.ProductsReOrderList;

@Component
public class ProductsDAL implements ProductsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Getting Product Information Based on Category Id
    public List<ProductStockData> getProductsByCategory(CategoryRequest categoryInputModel) {
        try {
            String queryString = "SELECT new main.models.productModels.outputModels.ProductStockData(p.productId,p.productName,ps.batchNo,ps.productStock,p.productReorderLevel,p.productHsnCode,ps.productSalePrice,ps.productMrp,ps.productCost) FROM Products p JOIN  p.productStocks ps  p.category = :categoryId";
            TypedQuery<ProductStockData> query = entityManager.createQuery(queryString, ProductStockData.class);
            query.setParameter("categoryId", categoryInputModel.getCategoryId());
            // Returns the Products Data as a List.
            return query.getResultList();
        } catch (NullPointerException e) {
            throw new NullPointerException("NullPointerException occurred while retrieving Products by CategoryId");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("IllegalArgumentException occurred while retrieving Products by CategoryId");
        } catch (NoResultException e) {
            throw new NoResultException("NoResultException occurred while retrieving Products by CategoryId");
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("NonUniqueResultException occurred while retrieving Products by CategoryId");
        } catch (PersistenceException e) {
            throw new PersistenceException("PersistenceException occurred while retrieving Products by CategoryId");
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve Products by CategoryId", e);
        }
    }



    // Getting ProductId and Product Name Based on Category Id
    @Transactional
    public List<ProductIdListOutput> getProductsByCategoryId(CategoryRequest categoryInputModel) {
        try {
            String queryString = "SELECT new main.models.productModels.outputModels.ProductIdListOutput(productId,productName) FROM Products  WHERE category = :categoryId";
            TypedQuery<ProductIdListOutput> query = entityManager.createQuery(queryString, ProductIdListOutput.class);
            query.setParameter("categoryId", categoryInputModel.getCategoryId());
            // Returns the ProductsID and Products Names as a List.
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("IllegalArgumentException occurred while retrieving Products by CategoryId");
        } catch (NoResultException e) {
            throw new NoResultException("NoResultException occurred while retrieving Products by CategoryId");
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("NonUniqueResultException occurred while retrieving Products by CategoryId");
        } catch (PersistenceException e) {
            throw new PersistenceException("PersistenceException occurred while retrieving Products by CategoryId");
    }
    }


    // It returns Products Data by taking the productId as an input
    @Override
    public List<ProductStockData> getProductsByProductId(int selectedProductId) {
        try {
            String queryString = "SELECT new main.models.productModels.outputModels.ProductStockData(p.productId, p.productName, ps.batchNo, p.productReorderLevel, p.productHsnCode, ps.productStock, ps.productSalePrice, ps.productMrp, ps.productCost) FROM Products p JOIN p.productStocks ps WHERE p.productId = :productId";
            TypedQuery<ProductStockData> query = entityManager.createQuery(queryString, ProductStockData.class);
            query.setParameter("productId", selectedProductId);
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid selectedProductId");
        } catch (NoResultException e) {
            throw new NoResultException("No results found for the selectedProductId");
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("Multiple results found for the selectedProductId");
        } catch (PersistenceException e) {
            throw new PersistenceException("PersistenceException occurred while retrieving product data by productId");
        }
    }


    // It returns products Data by taking the BatchNo and ProductId as an input
    @Override
    public ProductStockData getQuantityandpriceByProductIdOrBatchNo(ProductsProductIdandBatchNoInputModel productsProductIdandBatchNoInputModel) {
        try {
            String queryString = "SELECT new main.models.productModels.outputModels.ProductStockData(p.productId, p.productName, ps.batchNo, ps.productStock, p.productReorderLevel, p.productHsnCode, ps.productSalePrice, ps.productMrp, ps.productCost) FROM Products p JOIN p.productStocks ps WHERE p.productId = :productId AND ps.batchNo = :batchNo";
            TypedQuery<ProductStockData> query = entityManager.createQuery(queryString, ProductStockData.class);
            query.setParameter("productId", productsProductIdandBatchNoInputModel.getProductId());
            query.setParameter("batchNo", productsProductIdandBatchNoInputModel.getBatchNo());
            return query.getSingleResult();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid productId or batchNo");
        } catch (NoResultException e) {
            throw new NoResultException("No results found for the given productId and batchNo");
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("Multiple results found for the given productId and batchNo");
        } catch (PersistenceException e) {
            throw new PersistenceException("PersistenceException occurred while retrieving product data by productId or batchNo");
        }
    }


    // It returns a List of Re-order Products
    @Override
    public List<ProductsReOrderList> getReOrderLevelProducts() {
        try {
            String queryString = "SELECT new main.models.productModels.outputModels.ProductsReOrderList(p.productId, p.productName, p.productReorderLevel, SUM(ps.productStock) as sumOfProducts) "
                    + "FROM Products p " + "JOIN ProductStock ps ON p.productId = ps.productId "
                    + "WHERE p.productStatus = 'Active'" + "GROUP BY p.productId, p.productName, p.productReorderLevel "
                    + "HAVING p.productReorderLevel >= SUM(ps.productStock)";
            TypedQuery<ProductsReOrderList> query = entityManager.createQuery(queryString, ProductsReOrderList.class);
            return query.getResultList();
        } catch (NoResultException e) {
            throw new NoResultException("No results found for the re-order level products");
        } catch (PersistenceException e) {
            throw new PersistenceException("PersistenceException occurred while retrieving re-order level products");
        }
    }


    // It persists the category created by the procurement team
    @Transactional
    public boolean saveCategory(ProductsCategory productsCategory) {
        try {
            entityManager.persist(productsCategory);
            return true;
        } catch (EntityExistsException e) {
            throw new EntityExistsException("Category already exists");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category data");
        } catch (TransactionRequiredException e) {
            throw new TransactionRequiredException("Transaction is required for saving category");
        } catch (Exception e) {
            throw new RuntimeException("Failed to save category");
        }
    }


    // It persists the HSN created by the procurement team
    @Transactional
    public boolean saveHSN(HSNEntityModel hsnEntityModel) {
        try {
            entityManager.persist(hsnEntityModel);
            return true;
        } catch (EntityExistsException e) {
            throw new EntityExistsException("HSN already exists");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid HSN data");
        } catch (TransactionRequiredException e) {
            throw new TransactionRequiredException("Transaction is required for saving HSN");
        } catch (Exception e) {
            throw new RuntimeException("Failed to save HSN");
        }
    }


    // Return Product Profit by taking Product Id as Input.
    @Override
    @Transactional
    public ProductProfit getProfitPercentage(ProductsProductIdInputModel productsProductIds)  {
        try {
            ProductProfit productProfit = (ProductProfit) entityManager
                    .createQuery("select new main.models.productModels.dto.ProductProfit(p.profitPercentage)"
                            + " from Products p where p.productId=:productId")
                    .setParameter("productId", productsProductIds.getProductId()).getSingleResult();

            return productProfit;
        } catch (NoResultException e) {
            throw new NoResultException("No profit percentage found for the given product ID");
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("Multiple profit percentages found for the given product ID");
        }
    }
}
