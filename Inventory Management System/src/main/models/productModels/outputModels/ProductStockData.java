package main.models.productModels.outputModels;

public class ProductStockData {

    private int productId;
    private String productName;
    private int batchNo;
    private int stock;
	private double purchasePrice;
	private double mrp;
	private double costPrice;
    
	
	public ProductStockData(int productId, String productName, int batchNo, int stock, double purchasePrice, double mrp,
			double costPrice) {
		this.productId = productId;
		this.productName = productName;
		this.batchNo = batchNo;
		this.stock = stock;
		this.purchasePrice = purchasePrice;
		this.mrp = mrp;
		this.costPrice = costPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public ProductStockData() {
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	
    

}
