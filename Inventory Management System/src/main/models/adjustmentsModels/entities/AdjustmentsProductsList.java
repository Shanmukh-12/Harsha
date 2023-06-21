package main.models.adjustmentsModels.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_adjustment_items")
public class AdjustmentsProductsList implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "adjs_id")
	int adjs_id;
	@Id
	@Column(name = "product_id")
	int product_id;
	@Id
	@Column(name = "batch_no")
	int batch_no;

	@Column(name = "current_stock")
	int current_stock;

	@Column(name = "updated_stock")
	int updated_stock;

	@Column(name = "adjs_desc")
	String adjs_desc;

	@ManyToOne
	@JoinColumn(name = "adjs_id", referencedColumnName = "adjs_id", insertable = false, updatable = false)
	AdjustmentsList al;

	public int getAdjs_id() {
		return adjs_id;
	}

	public void setAdjs_id(int adjs_id) {
		this.adjs_id = adjs_id;
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

	public int getCurrent_stock() {
		return current_stock;
	}

	public void setCurrent_stock(int current_stock) {
		this.current_stock = current_stock;
	}

	public int getUpdated_stock() {
		return updated_stock;
	}

	public void setUpdated_stock(int updated_stock) {
		this.updated_stock = updated_stock;
	}

	public String getAdjs_desc() {
		return adjs_desc;
	}

	public void setAdjs_desc(String adjs_desc) {
		this.adjs_desc = adjs_desc;
	}

	public AdjustmentsList getAl() {
		return al;
	}

	public void setAl(AdjustmentsList al) {
		this.al = al;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
