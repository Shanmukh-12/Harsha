package main.models.procurementModels.inputmodels;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import main.models.purchaseOrder.entityModels.Im_Purchase_Order_Products;

public class PurchaseOrderInput {

	private int purchase_order_id;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date purchase_order_date = Date.valueOf(LocalDate.now());

	private BigDecimal purchase_order_amount = BigDecimal.valueOf(3000);

	private int vendor_id;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date purchase_order_expected_date = Date.valueOf(LocalDate.now().plusWeeks(1));

	private String purchase_order_status = "Active";

	private int user_id = 2;
	private String last_updated_user = "tharak";

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date last_updated_date = Date.valueOf(LocalDate.now());

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

	public List<Im_Purchase_Order_Products> getChildren() {
		return children;
	}

	public void setChildren(List<Im_Purchase_Order_Products> children) {
		this.children = children;
	}

	private List<Im_Purchase_Order_Products> children;

	// Getters and Setters

	@Override
	public String toString() {
		return "Im_Purchase_Order [purchase_order_id=" + purchase_order_id + ", purchase_order_date="
				+ purchase_order_date + ", purchase_order_amount=" + purchase_order_amount + ", vendor_id=" + vendor_id
				+ ", purchase_order_expected_date=" + purchase_order_expected_date + ", purchase_order_status="
				+ purchase_order_status + ", user_id=" + user_id + ", last_updated_user=" + last_updated_user
				+ ", last_updated_date=" + last_updated_date + ", children=" + children + "]";
	}

	public PurchaseOrderInput(int purchase_order_id, Date purchase_order_date, BigDecimal purchase_order_amount,
			int vendor_id, Date purchase_order_expected_date, String purchase_order_status, int user_id,
			String last_updated_user, Date last_updated_date, List<Im_Purchase_Order_Products> children) {
		super();
		this.purchase_order_id = purchase_order_id;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_amount = purchase_order_amount;
		this.vendor_id = vendor_id;
		this.purchase_order_expected_date = purchase_order_expected_date;
		this.purchase_order_status = purchase_order_status;
		this.user_id = user_id;
		this.last_updated_user = last_updated_user;
		this.last_updated_date = last_updated_date;
		this.children = children;
	}

	public PurchaseOrderInput() {
		super();
	}
}
