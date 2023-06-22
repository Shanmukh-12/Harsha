package main.dao.priceReview;

import java.util.List;

import main.models.priceReviewModels.entities.PriceReviewList;
import main.models.priceReviewModels.inputModels.PriceReviewInputList;
import main.models.priceReviewModels.outputModels.PriceReviewProductsListData;

public interface PriceReviewDAO {

	public List<PriceReviewList> getPriceReview();

	public boolean savePriceReview(PriceReviewList priceReviewList);

	public List<PriceReviewProductsListData> getPriceReviewProductsList(PriceReviewInputList pricereviewid);

}