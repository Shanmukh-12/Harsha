package main.models.grnModels.inputModels;

import java.time.LocalDate;

public class GrnInputFilters {
	private int vendor_id = 0;
	private double grn_amount = 0;

	LocalDate grnFromDate;
	LocalDate grnToDate;

	public GrnInputFilters() {
		super();
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public double getGrn_amount() {
		return grn_amount;
	}

	public void setGrn_amount(double grn_amount) {
		this.grn_amount = grn_amount;
	}

	@Override
	public String toString() {
		return "GrnInputFilters [vendor_id=" + vendor_id + ", grn_amount=" + grn_amount + ", grnFromDate=" + grnFromDate
				+ ", grnToDate=" + grnToDate + "]";
	}

	public GrnInputFilters(int vendor_id, double grn_amount, LocalDate grnFromDate, LocalDate grnToDate) {
		super();
		this.vendor_id = vendor_id;
		this.grn_amount = grn_amount;
		this.grnFromDate = grnFromDate;
		this.grnToDate = grnToDate;
	}

	public LocalDate getGrnFromDate() {
		return grnFromDate;
	}

	public void setGrnFromDate(LocalDate grnFromDate) {
		this.grnFromDate = grnFromDate;
	}

	public LocalDate getGrnToDate() {
		return grnToDate;
	}

	public void setGrnToDate(LocalDate grnToDate) {
		this.grnToDate = grnToDate;
	}

}
