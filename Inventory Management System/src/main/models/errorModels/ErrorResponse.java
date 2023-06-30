package main.models.errorModels;

public class ErrorResponse {
    private String message;
    private String originalException;
    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOriginalException() {
		return originalException;
	}
	public void setOriginalException(String originalException) {
		this.originalException = originalException;
	}

    
}

