package main.bll.inventory;

import java.util.List;

import org.springframework.stereotype.Component;

import main.models.storeIssueModels.inputModels.StoreIssuesList;
import main.models.storeIssueModels.inputModels.StoreIssuesListData;

@Component
public class StoreIssuesBLL {
	
	//calculating totalStock issue Amount
	public double calculateTotalPurchaseAmount(StoreIssuesList storeIssuesList) {
		double totalPurchaseAmount = 0.0;
		List<StoreIssuesListData> storeProducts = storeIssuesList.getStoreProducts();
		if (storeProducts != null) {
			for (StoreIssuesListData storeProduct : storeProducts) {
				totalPurchaseAmount += storeProduct.getPurchaseAmount();
			}
		}
		return totalPurchaseAmount;
	}
}
