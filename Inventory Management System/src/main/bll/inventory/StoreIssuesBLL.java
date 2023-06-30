package main.bll.inventory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import main.models.storeIssueModels.inputModels.StoreIssuesList;
import main.models.storeIssueModels.inputModels.StoreIssuesListData;

@Component
public class StoreIssuesBLL {
	private static final Logger logger = LoggerFactory.getLogger(StoreIssuesBLL.class);

	// calculating totalStock issue Amount
	public double calculateTotalPurchaseAmount(StoreIssuesList storeIssuesList) {
		double totalPurchaseAmount = 0.0;
		List<StoreIssuesListData> storeProducts = storeIssuesList.getStoreProducts();

		// Check if the storeProducts list is not null
		if (storeProducts != null) {
			// Iterate through each storeProduct in the storeProducts list
			for (StoreIssuesListData storeProduct : storeProducts) {
				// Add the purchase amount of each storeProduct to the totalPurchaseAmount
				totalPurchaseAmount += storeProduct.getPurchaseAmount();
			}
		}

		logger.info("Total Purchase Amount: {}", totalPurchaseAmount);

		// Return the calculated totalPurchaseAmount
		return totalPurchaseAmount;
	}
}
