package main.models.productModels.inputModels;

public class CategoryRequest {
    private int categoryId;

    

	public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

	@Override
	public String toString() {
		return "CategoryRequest [categoryId=" + categoryId + "";
	}

	

	public CategoryRequest(int categoryId) {
		super();
		this.categoryId = categoryId;
	}

	public CategoryRequest() {
		super();
	}
    
}
