package main.models.warehouseModels.outputmodels;

import org.springframework.stereotype.Component;

@Component
public class ProductCategoryCount {
         private int total_product_category_count;

		public int getTotal_product_category_count() {
			return total_product_category_count;
		}

		public void setTotal_product_category_count(int total_product_category_count) {
			this.total_product_category_count = total_product_category_count;
		}

		public ProductCategoryCount(int total_product_category_count) {
			super();
			this.total_product_category_count = total_product_category_count;
		}

		public ProductCategoryCount() {
			super();
		}

		@Override
		public String toString() {
			return "ProductCategoryCount [total_product_category_count=" + total_product_category_count + "]";
		}
         
}
