package main.dao.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import main.models.adminModels.ProductsCategory;

@Component
public class ProductsCategoryDAO {

	private List<ProductsCategory> productsCategoriesList;

	public ProductsCategoryDAO() {
		productsCategoriesList = new ArrayList<>();

		// Create dummy objects and add them to the list
		ProductsCategory product1 = new ProductsCategory(1, "Soaps");
		ProductsCategory product2 = new ProductsCategory(2, "Groceries");
		ProductsCategory product3 = new ProductsCategory(3, "Electronics");
		ProductsCategory product4 = new ProductsCategory(4, "Home Care");
		ProductsCategory product5 = new ProductsCategory(5, "Personal");
		ProductsCategory product6 = new ProductsCategory(6, "Sports");

		productsCategoriesList.add(product1);
		productsCategoriesList.add(product2);
		productsCategoriesList.add(product3);
		productsCategoriesList.add(product4);
		productsCategoriesList.add(product5);
		productsCategoriesList.add(product6);
	}

	public List<ProductsCategory> getProductCategories() {
		return productsCategoriesList;
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
