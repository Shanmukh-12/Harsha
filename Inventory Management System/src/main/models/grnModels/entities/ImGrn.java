package main.models.grnModels.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "im_grn")
public class ImGrn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRN_ID")
	private int grnId;

	@Column(name = "GRN_Date")
	private LocalDate grnDate = LocalDate.now();

	@Column(name = "Purchase_Order_ID")
	private int purchaseOrderId;

	@Column(name = "GRN_Amount")
	private double grnAmount;

	@Column(name = "GRN_Status")
	private String grnStatus = "Active";

	// constructor
	public ImGrn() {

	}

	@OneToMany(mappedBy = "g")
	@JsonProperty("product_id")
	List<ImGrnProducts> productsList;

	public int getGrnId() {
		return grnId;
	}

	public void setGrnId(int grnId) {
		this.grnId = grnId;
	}

	public LocalDate getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(LocalDate grnDate) {
		this.grnDate = grnDate;
	}

	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public double getGrnAmount() {
		return grnAmount;
	}

	public void setGrnAmount(double grnAmount) {
		this.grnAmount = grnAmount;
	}

	public String getGrnStatus() {
		return grnStatus;
	}

	public void setGrnStatus(String grnStatus) {
		this.grnStatus = grnStatus;
	}

	public List<ImGrnProducts> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<ImGrnProducts> productsList) {
		this.productsList = productsList;
	}

	@Override
	public String toString() {
		return "ImGrn [grnId=" + grnId + ", grnDate=" + grnDate + ", purchaseOrderId=" + purchaseOrderId
				+ ", grnAmount=" + grnAmount + ", grnStatus=" + grnStatus + ", productsList=" + productsList + "]";
	}

}