package main.models.storeIssueModels.outputModels;

import java.util.List;

public class StoreIssueId {
	List<StoreIssueId> storeIssueIds;

	public List<StoreIssueId> getStoreIssueIds() {
		return storeIssueIds;
	}

	public void setStoreIssueIds(List<StoreIssueId> storeIssueIds) {
		this.storeIssueIds = storeIssueIds;
	}

	@Override
	public String toString() {
		return "StoreIssueIds [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
