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
	@Override
	public String toString() {
		return "HSNEntityModel [HSNcode=" + HSNcode + ", gst=" + gst + "]";
	}

	@Id
	@Column(name = "hsn_code")
	private int HSNcode;

	@Column(name = "gst")
	private double gst;

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public int getHSNcode() {
		return HSNcode;
	}

	public void setHSNcode(int hSNode) {
		HSNcode = hSNode;
	}

	public HSNEntityModel(int hSNode, double gst) {
		super();
		HSNcode = hSNode;
		this.gst = gst;
	}

	public HSNEntityModel() {
		super();
	}
	

	

}
