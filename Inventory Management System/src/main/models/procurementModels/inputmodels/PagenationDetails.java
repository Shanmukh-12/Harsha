package main.models.procurementModels.inputmodels;

public class PagenationDetails {
	private int page;
	private int pageSize;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PagenationDetails(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public PagenationDetails() {
		super();
	}

	@Override
	public String toString() {
		return "PagenationDetails [page=" + page + ", pageSize=" + pageSize + "]";
	}

}