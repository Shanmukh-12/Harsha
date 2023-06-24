package main.models.grnModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "im_grn_products")
public class ImGrnProducts implements Serializable {

	@Id
	@Column(name = "grn_id")
	private int grnId;

	@Id
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private int productId;

	@Id
	@Column(name = "batch_no")
	@JsonProperty("batch_no")
	private int batchNo;
	@JsonProperty("quantity")

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "cgst")
	private double cgst;

	@Column(name = "sgst")
	private double sgst;

	@Column(name = "igst")
	private double igst;

	@Column(name = "total_price")
	private double totalPrice;

	@Column(name = "hsn_code")
	private int hsn_code;

	@Column(name = "bonus")
	private int bonus;

	@ManyToOne
	@JoinColumn(name = "grn_id", referencedColumnName = "GRN_ID", updatable = false, insertable = false)
	ImGrn g;

	public int getGrnId() {
		return grnId;
	}

	public void setGrnId(int grnId) {
		this.grnId = grnId;
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

	public int getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(int hsn_code) {
		this.hsn_code = hsn_code;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public ImGrn getG() {
		return g;
	}

	public void setG(ImGrn g) {
		this.g = g;
	}

	@Override
	public String toString() {
		return "ImGrnProducts [grnId=" + grnId + ", productId=" + productId + ", batchNo=" + batchNo + ", quantity="
				+ quantity + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", totalPrice=" + totalPrice
				+ ", hsn_code=" + hsn_code + ", bonus=" + bonus + "]";
	}

	public ImGrnProducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImGrnProducts(int grnId, int productId, int batchNo, int quantity, double cgst, double sgst, double igst,
			double totalPrice, int hsn_code, int bonus, ImGrn g) {
		super();
		this.grnId = grnId;
		this.productId = productId;
		this.batchNo = batchNo;
		this.quantity = quantity;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.totalPrice = totalPrice;
		this.hsn_code = hsn_code;
		this.bonus = bonus;
		this.g = g;
	}

}