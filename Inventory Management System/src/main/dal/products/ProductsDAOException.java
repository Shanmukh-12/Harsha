package main.dal.products;

public class ProductsDAOException extends Exception {
    // Constructors
    public ProductsDAOException() {
        super();
    }

    public ProductsDAOException(String message) {
        super(message);
    }

    public ProductsDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductsDAOException(Throwable cause) {
        super(cause);
    }
}

