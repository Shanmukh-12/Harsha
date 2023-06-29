package main.dal.priceReview;

@SuppressWarnings("serial")
public class PriceReviewException extends Exception {
	public PriceReviewException(String message) {
		super(message);
	}

	public PriceReviewException(String message, Throwable cause) {
		super(message, cause);
	}

}
