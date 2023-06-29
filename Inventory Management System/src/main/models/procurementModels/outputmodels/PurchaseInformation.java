package main.models.procurementModels.outputmodels;

import java.util.List;

public class PurchaseInformation {
	private List<PurchaseOrderDetails> items;
	private int totalPages;
	private int currentPage;

	public List<PurchaseOrderDetails> getItems() {
		return items;
	}

	public void setItems(List<PurchaseOrderDetails> items) {
		this.items = items;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public PurchaseInformation(List<PurchaseOrderDetails> items, int totalPages, int currentPage) {
		super();
		this.items = items;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}

	@Override
	public String toString() {
		return "PurchaseInformation [items=" + items + ", totalPages=" + totalPages + ", currentPage=" + currentPage
				+ "]";
	}

}