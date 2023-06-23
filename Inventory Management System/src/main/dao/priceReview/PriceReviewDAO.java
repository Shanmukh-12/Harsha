package main.dao.priceReview;

import java.util.List;

import main.models.priceReviewModels.entities.PriceReviewList;
import main.models.priceReviewModels.inputModels.PriceReviewFilterInput;
import main.models.priceReviewModels.inputModels.PriceReviewInputList;
import main.models.priceReviewModels.outputModels.PriceReviewFilterOutput;
import main.models.priceReviewModels.outputModels.PriceReviewProductsListData;

public interface PriceReviewDAO {

	public List<PriceReviewList> getPriceReview();

	public boolean savePriceReview(PriceReviewList priceReviewList);

	public List<PriceReviewProductsListData> getPriceReviewProductsList(PriceReviewInputList pricereviewid);

	List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductIdFrom(PriceReviewFilterInput priceReviewFilterInput);

	List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductId(PriceReviewFilterInput priceReviewFilterInput);

	List<PriceReviewFilterOutput> getFilterDataByCategoryIdFrom(PriceReviewFilterInput priceReviewFilterInput);

	List<PriceReviewFilterOutput> getFilterDataByCategoryId(PriceReviewFilterInput priceReviewFilterInput);

	List<PriceReviewFilterOutput> getFilterDataByFrom(PriceReviewFilterInput priceReviewFilterInput);

	List<PriceReviewFilterOutput> getFilterDataByTo(PriceReviewFilterInput priceReviewFilterInput);

}