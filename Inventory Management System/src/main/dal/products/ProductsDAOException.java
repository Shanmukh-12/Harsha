package main.dal.products;

public class ProductsDAOException extends Exception {
	
    public ProductsDAOException(String message) {
        super(message);
    }

    public ProductsDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
