package main.models.productModels.inputModels;


public class CategoryRequest2 {
    private int ProductCategoryId;
    private String ProductCategoryName;
	public int getProductCategoryId() {
		return ProductCategoryId;
	}
	public void setProductCategoryId(int productCategoryId) {
		ProductCategoryId = productCategoryId;
	}
	public String getProductCategoryName() {
		return ProductCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		ProductCategoryName = productCategoryName;
	}
	public CategoryRequest2(int productCategoryId, String productCategoryName) {
		super();
		ProductCategoryId = productCategoryId;
		ProductCategoryName = productCategoryName;
	}
	public CategoryRequest2() {
		super();
	}
	@Override
	public String toString() {
		return "CategoryRequest [ProductCategoryId=" + ProductCategoryId + ", ProductCategoryName="
				+ ProductCategoryName + "]";
	}

    
}
