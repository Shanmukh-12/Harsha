package main.dal.products;

public class ProductsDAOException extends Exception {
	
	private final Throwable originalException;

    public ProductsDAOException(String message, Throwable originalException) {
        super(message);
        this.originalException = originalException;
    }

    public Throwable getOriginalException() {
        return originalException;
    }
}
