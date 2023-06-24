package main.models.grnModels.inputModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class GrnInputProductsList {

	int productId;
	int batchNo;
	int quantity;
	int bonus;
	double cgst;
	double sgst;
	double igst;
	double totalPrice;
	double mrp;

	public GrnInputProductsList() {
	}

	public GrnInputProductsList(int productId, int batchNo, int quantity, int bonus, double cgst, double sgst,
			double igst, double totalPrice, double mrp) {
		super();
		this.productId = productId;
		this.batchNo = batchNo;
		this.quantity = quantity;
		this.bonus = bonus;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.totalPrice = totalPrice;
		this.mrp = mrp;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public double getCgst() {
		return cgst;
	}

	public void setCgst(double cgst) {
		this.cgst = cgst;
	}

	public double getSgst() {
		return sgst;
	}

	public void setSgst(double sgst) {
		this.sgst = sgst;
	}

	public double getIgst() {
		return igst;
	}

	public void setIgst(double igst) {
		this.igst = igst;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	@Override
	public String toString() {
		return "GrnInputProductsList [productId=" + productId + ", batchNo=" + batchNo + ", quantity=" + quantity
				+ ", bonus=" + bonus + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", totalPrice="
				+ totalPrice + ", mrp=" + mrp + "]";
	}

}
