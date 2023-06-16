package main.dao.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import main.models.adminModels.Products;

@Component
public class ProductsDAO {

	private List<Products> productsList;

	public ProductsDAO() {
		productsList = new ArrayList<>();

		// Create dummy objects and add them to the list
		Products product1 = new Products(1, "Product 1", 123, 50, 10.99, 19.99, "category1");
		Products product2 = new Products(2, "Product 2", 456, 100, 15.99, 29.99, "category2");
		Products product3 = new Products(3, "Product 3", 789, 75, 8.99, 14.99, "category3");
		Products product4 = new Products(4, "Product 4", 126, 550, 110.99, 119.99, "category4");
		Products product5 = new Products(5, "Product 5", 486, 900, 115.99, 129.99, "category5");
		Products product6 = new Products(6, "Product 6", 689, 750, 18.99, 114.99, "category6");

		productsList.add(product1);
		productsList.add(product2);
		productsList.add(product3);
		productsList.add(product4);
		productsList.add(product5);
		productsList.add(product6);
	}

	public List<Products> getProducts() {
		return productsList;
	}

	// public List<ProductCategories> getProductCateogeryByCategory(String selectedCategory) {
	// List<ProductCategories> filteredList = new ArrayList<>();
	//
	// for (ProductCategories product : productCategoriesList) {
	// // Assuming 'selectedCategory' corresponds to a specific product category property
	// if (product.getProductName().equals(selectedCategory)) {
	// filteredList.add(product);
	// }
	// }
	//
	// return filteredList;
	// }
}
