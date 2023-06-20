package main.models.productModels.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_hsn_code")
public class HSNEntityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hsn_code")
	private int HSNCode;

	@Column(name = "gst")
	private double gst;

	public int getHSNCode() {
		return HSNCode;
	}

	public void setHSNCode(int hSNCode) {
		HSNCode = hSNCode;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	@Override
	public String toString() {
		return "HSNEntityModel [HSNCode=" + HSNCode + ", gst=" + gst + "]";
	}

}
