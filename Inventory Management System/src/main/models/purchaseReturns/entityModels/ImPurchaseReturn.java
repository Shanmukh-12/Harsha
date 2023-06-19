package main.models.purchaseReturns.entityModels;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "im_purchase_return")
@JsonIgnoreProperties(ignoreUnknown = true)

public class ImPurchaseReturn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "purchase_return_id")
	private int purchase_return_id;

	@Column(name = "purchase_return_date")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date purchase_return_date = Date.valueOf(LocalDate.now());

	@Column(name = "grn_no")
	private int grn_no;

	@Column(name = "grn_cost")
	private BigDecimal grn_cost;

	@Column(name = "vendor_id")
	private int vendor_id;

	@Column(name = "purchase_status")
	private String purchase_status = "Active";

	@Column(name = "purchase_return_description")
	private String purchase_return_description;

	@Column(name = "last_updated_user")
	private String last_updated_user = "tharak";

	@Column(name = "last_updated_date")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date last_updated_date = Date.valueOf(LocalDate.now());

	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "purchaseReturn", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ImPurchaseReturnProduct> purchaseReturnProducts;

	// Constructors, getters, and setters

	public ImPurchaseReturn() {
	}

	public ImPurchaseReturn(int purchase_return_id, Date purchase_return_date, int grn_no, BigDecimal grn_cost,
			int vendor_id, String purchase_status, String purchase_return_description, String last_updated_user,
			Date last_updated_date) {
		this.purchase_return_id = purchase_return_id;
		this.purchase_return_date = purchase_return_date;
		this.grn_no = grn_no;
		this.grn_cost = grn_cost;
		this.vendor_id = vendor_id;
		this.purchase_status = purchase_status;
		this.purchase_return_description = purchase_return_description;
		this.last_updated_user = last_updated_user;
		this.last_updated_date = last_updated_date;
	}

	public int getPurchase_return_id() {
		return purchase_return_id;
	}

	public void setPurchase_return_id(int purchase_return_id) {
		this.purchase_return_id = purchase_return_id;
	}

	public Date getPurchase_return_date() {
		return purchase_return_date;
	}

	public void setPurchase_return_date(Date purchase_return_date) {
		this.purchase_return_date = purchase_return_date;
	}

	public int getGrn_no() {
		return grn_no;
	}

	public void setGrn_no(int grn_no) {
		this.grn_no = grn_no;
	}

	public BigDecimal getGrn_cost() {
		return grn_cost;
	}

	public void setGrn_cost(BigDecimal grn_cost) {
		this.grn_cost = grn_cost;
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getPurchase_status() {
		return purchase_status;
	}

	public void setPurchase_status(String purchase_status) {
		this.purchase_status = purchase_status;
	}

	public String getPurchase_return_description() {
		return purchase_return_description;
	}

	public void setPurchase_return_description(String purchase_return_description) {
		this.purchase_return_description = purchase_return_description;
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

	public List<ImPurchaseReturnProduct> getPurchaseReturnProducts() {
		return purchaseReturnProducts;
	}

	public void setPurchaseReturnProducts(List<ImPurchaseReturnProduct> purchaseReturnProducts) {
		this.purchaseReturnProducts = purchaseReturnProducts;
	}

	@Override
	public String toString() {
		return "ImPurchaseReturn [purchase_return_id=" + purchase_return_id + ", purchase_return_date="
				+ purchase_return_date + ", grn_no=" + grn_no + ", grn_cost=" + grn_cost + ", vendor_id=" + vendor_id
				+ ", purchase_status=" + purchase_status + ", purchase_return_description="
				+ purchase_return_description + ", last_updated_user=" + last_updated_user + ", last_updated_date="
				+ last_updated_date + ", purchaseReturnProducts=" + purchaseReturnProducts + "]";
	}

}
