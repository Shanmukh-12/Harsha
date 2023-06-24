package main.models.grnModels.dto;

public class GrnProductInfo {

	private int productId;

	private int totalprice;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	@Override
	public String toString() {
		return "GrnProductInfo [productId=" + productId + ", totalprice=" + totalprice + "]";
	}

	public GrnProductInfo(int productId, int totalprice) {
		super();
		this.productId = productId;
		this.totalprice = totalprice;
	}

	public GrnProductInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
