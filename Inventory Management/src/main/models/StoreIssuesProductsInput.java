package main.models.storeissuesModels;

public class StoreIssuesProductsInput {
	
	int storeIssueId;
	int indentId;
	int qty;
	
	StoreIssuesInput pil;

	public int getStoreIssueId() {
		return storeIssueId;
	}

	public void setStoreIssueId(int storeIssueId) {
		this.storeIssueId = storeIssueId;
	}

	public int getIndentId() {
		return indentId;
	}

	public void setIndentId(int indentId) {
		this.indentId = indentId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public StoreIssuesInput getPil() {
		return pil;
	}

	public void setPil(StoreIssuesInput pil) {
		this.pil = pil;
	}

}
