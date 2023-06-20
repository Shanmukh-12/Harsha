package main.dao.priceReview;

import java.util.List;

import main.models.priceReviewModels.entities.PriceReviewList;

public interface PriceReviewDAO {

	public List<PriceReviewList> getPriceReview();

	public boolean savePriceReview(PriceReviewList priceReviewList);

}