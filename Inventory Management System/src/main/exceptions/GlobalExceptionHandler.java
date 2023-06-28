package main.exceptions;
import org.springframework.web.servlet.ModelAndView;

import main.dal.products.ProductsDAOException;
import main.models.errorModels.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductsDAOException.class)
    public ResponseEntity<ErrorResponse> handleProductsDAOException(ProductsDAOException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("An error occurred while processing the request.");
        errorResponse.setOriginalException(ex.getOriginalException().getMessage());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("errorMessage", "An unexpected error occurred.");
        modelAndView.addObject("originalException", ex.getMessage());
        
        return modelAndView;
    }
}


