package main.models.grnModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "im_grn_products")
public class ImGrnProducts implements Serializable {

	@Id
	@Column(name = "grn_id")
	private int grn_id;

	@Id
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private int product_id;

	@Id
	@Column(name = "batch_no")
	@JsonProperty("batch_no")
	private int batch_no;
	@JsonProperty("quantity")

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private double price;

	public int getGrn_id() {
		return grn_id;
	}

	public void setGrn_id(int grn_id) {
		this.grn_id = grn_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(int batch_no) {
		this.batch_no = batch_no;
	}

	public int getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(int hsn_code) {
		this.hsn_code = hsn_code;
	}

	@Column(name = "hsn_code")
	private int hsn_code;

	@Column(name = "bonus")
	private int bonus;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "grn_id", referencedColumnName = "grn_id", updatable = false, insertable = false)
	ImGrn g;

	public ImGrnProducts() {

	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
		return "ImGrnProducts [grn_id=" + grn_id + ", product_id=" + product_id + ", batch_no=" + batch_no
				+ ", quantity=" + quantity + ", price=" + price + ", hsn_code=" + hsn_code + ", bonus=" + bonus + ", g="
				+ g + "]";
	}

	public ImGrnProducts(int grnId, int product_id, int batch_no, int quantity, double price, int hsn_code, int bonus,
			ImGrn g) {
		super();
		this.grn_id = grnId;
		this.product_id = product_id;
		this.batch_no = batch_no;
		this.quantity = quantity;
		this.price = price;
		this.hsn_code = hsn_code;
		this.bonus = bonus;
		this.g = g;
	}

}