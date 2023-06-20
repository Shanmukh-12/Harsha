package main.models.purchaseReturns.entityModels;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import main.models.purchaseReturns.entityModels.ImPurchaseReturn;

@Entity
@Table(name = "im_purchase_return_products")
@JsonIgnoreProperties(ignoreUnknown = true)

public class ImPurchaseReturnProduct implements Serializable {

	@Id
	private int purchase_return_id;

	@Id
	private int product_id;

	private int quantity;

	@Column(name = "last_updated_user")
	private String last_updated_user = "tharak";

	@Column(name = "last_updated_date")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date last_updated_date = Date.valueOf(LocalDate.now());

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "purchase_return_id", referencedColumnName = "purchase_return_id", insertable = false, updatable = false)
	private ImPurchaseReturn purchaseReturn;

	// Constructors, getters, and setters

	public ImPurchaseReturnProduct() {
	}

	public ImPurchaseReturnProduct(int purchase_return_id, int product_id, int quantity, String last_updated_user,
			Date last_updated_date) {
		this.purchase_return_id = purchase_return_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.last_updated_user = last_updated_user;
		this.last_updated_date = last_updated_date;
	}

	public int getPurchase_return_id() {
		return purchase_return_id;
	}

	public void setPurchase_return_id(int purchase_return_id) {
		this.purchase_return_id = purchase_return_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getLast_updated_user() {
		return last_updated_user;
	}

	public void setLast_updated_user(String last_updated_user) {
		this.last_updated_user = last_updated_user;
	}

	public Date getLast_updated_date() {
		return last_updated_date;
	}

	public void setLast_updated_date(Date last_updated_date) {
		this.last_updated_date = last_updated_date;
	}

	public ImPurchaseReturn getPurchaseReturn() {
		return purchaseReturn;
	}

	public void setPurchaseReturn(ImPurchaseReturn purchaseReturn) {
		this.purchaseReturn = purchaseReturn;
	}

	@Override
	public String toString() {
		return "ImPurchaseReturnProduct [purchase_return_id=" + purchase_return_id + ", product_id=" + product_id
				+ ", quantity=" + quantity + ", last_updated_user=" + last_updated_user + ", last_updated_date="
				+ last_updated_date + "]";
	}
}
