package main.exceptions;
import org.springframework.web.servlet.ModelAndView;

import main.dal.products.ProductsDAOException;
import main.models.errorModels.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductsDAOException.class)
    public String handleCustomException(ProductsDAOException e, Model model) {
        // Get the error message and original exception
        String errorMessage = e.getMessage();


        // Pass the error message and original exception to the JSP page
        model.addAttribute("errorMessage", errorMessage);

        // Return the JSP page to display the error message
        return "errorPage";
    }
}


