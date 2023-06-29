package main.dal.products;
public class ProductsDAOException extends Exception {
	private String errorMessage;

    public ProductsDAOException(String message) {
    	
        super(message);
        this.errorMessage=message;
    }

    public ProductsDAOException(String message, Throwable cause) {
        super(message, cause);
    }

	public String getErrorMessage() {
		return errorMessage;
	}
    
    
}
