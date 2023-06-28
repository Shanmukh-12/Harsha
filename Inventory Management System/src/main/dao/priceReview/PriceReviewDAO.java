package main.dao.priceReview;

import java.util.List;

import main.dal.priceReview.PriceReviewException;
import main.models.priceReviewModels.entities.PriceReviewList;
import main.models.priceReviewModels.inputModels.PriceReviewFilterInput;
import main.models.priceReviewModels.inputModels.PriceReviewInputList;
import main.models.priceReviewModels.outputModels.PriceReviewFilterOutput;
import main.models.priceReviewModels.outputModels.PriceReviewProductsListData;

public interface PriceReviewDAO {

	// This method is used to retrieve list of all price reviews
	public List<PriceReviewList> getPriceReview() throws PriceReviewException;

	/*
	 * This method is responsible for persisting price review details (product category, product name, batch no, old
	 * price, new price and reason) to DB
	 */

	public boolean savePriceReview(PriceReviewList priceReviewList) throws PriceReviewException;

	// This method is for displaying the list of all the products in each price review ID
	public List<PriceReviewProductsListData> getPriceReviewProductsList(PriceReviewInputList pricereviewid)
			throws PriceReviewException;

	// This method filters price review ID's by product category Id, product Id and From date
	List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductIdFrom(PriceReviewFilterInput priceReviewFilterInput)
			throws PriceReviewException;

	// This method filters price review ID's by product category Id and product Id
	List<PriceReviewFilterOutput> getFilterDataByCategoryIdProductId(PriceReviewFilterInput priceReviewFilterInput)
			throws PriceReviewException;

	// This method filters price review ID's by product category Id and From Date
	List<PriceReviewFilterOutput> getFilterDataByCategoryIdFrom(PriceReviewFilterInput priceReviewFilterInput)
			throws PriceReviewException;

	// This method filters price review ID's by product category Id
	List<PriceReviewFilterOutput> getFilterDataByCategoryId(PriceReviewFilterInput priceReviewFilterInput)
			throws PriceReviewException;

	// This method filters price review ID's by From date
	List<PriceReviewFilterOutput> getFilterDataByFrom(PriceReviewFilterInput priceReviewFilterInput)
			throws PriceReviewException;

	// This method filters price review ID's by To date
	List<PriceReviewFilterOutput> getFilterDataByTo(PriceReviewFilterInput priceReviewFilterInput)
			throws PriceReviewException;

}
