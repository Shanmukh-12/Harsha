package main.models.storeIssueModels.outputModels;

public class StoreIssueIds {
	int storeIssueId;

	public int getStoreIssueId() {
		return storeIssueId;
	}

	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
	}

	public StoreIssueIds() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreIssueIds(int storeIssueId) {
		super();
		this.storeIssueId = storeIssueId;
	}

	@Override
	public String toString() {
		return "StoreIssueIds [storeIssueId=" + storeIssueId + "]";
	}

}
