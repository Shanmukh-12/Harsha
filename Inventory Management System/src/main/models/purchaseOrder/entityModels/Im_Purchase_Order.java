package main.models.purchaseOrder.entityModels;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
@Table(name = "im_purchase_order")
@JsonIgnoreProperties(ignoreUnknown = true)

public class Im_Purchase_Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_order_id")
	int purchase_order_id;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	Date purchase_order_date = Date.valueOf(LocalDate.now());

	private BigDecimal purchase_order_amount = BigDecimal.valueOf(3000);

	int vendor_id;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	Date purchase_order_expected_date = Date.valueOf(LocalDate.now().plusWeeks(1));

	String purchase_order_status = "Active";

	int user_id;

	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<Im_Purchase_Order_Products> children;

	public int getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(int purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public Date getPurchase_order_date() {
		return purchase_order_date;
	}

	public void setPurchase_order_date(Date purchase_order_date) {
		this.purchase_order_date = purchase_order_date;
	}

	public BigDecimal getPurchase_order_amount() {
		return purchase_order_amount;
	}

	public void setPurchase_order_amount(BigDecimal purchase_order_amount) {
		this.purchase_order_amount = purchase_order_amount;
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public Date getPurchase_order_expected_date() {
		return purchase_order_expected_date;
	}

	public void setPurchase_order_expected_date(Date purchase_order_expected_date) {
		this.purchase_order_expected_date = purchase_order_expected_date;
	}

	public String getPurchase_order_status() {
		return purchase_order_status;
	}

	public void setPurchase_order_status(String purchase_order_status) {
		this.purchase_order_status = purchase_order_status;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public List<Im_Purchase_Order_Products> getChildren() {
		return children;
	}

	public void setChildren(List<Im_Purchase_Order_Products> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Im_Purchase_Order [purchase_order_id=" + purchase_order_id + ", purchase_order_date="
				+ purchase_order_date + ", purchase_order_amount=" + purchase_order_amount + ", vendor_id=" + vendor_id
				+ ", purchase_order_expected_date=" + purchase_order_expected_date + ", purchase_order_status="
				+ purchase_order_status + ", user_id=" + user_id + ", last_updated_user=" + ", children=" + children
				+ "]";
	}

	public Im_Purchase_Order(int purchase_order_id, Date purchase_order_date, BigDecimal purchase_order_amount,
			int vendor_id, Date purchase_order_expected_date, String purchase_order_status, int user_id,
			List<Im_Purchase_Order_Products> children) {
		super();
		this.purchase_order_id = purchase_order_id;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_amount = purchase_order_amount;
		this.vendor_id = vendor_id;
		this.purchase_order_expected_date = purchase_order_expected_date;
		this.purchase_order_status = purchase_order_status;
		this.user_id = user_id;
		this.children = children;
	}

	public Im_Purchase_Order() {
		super();
	}

}
