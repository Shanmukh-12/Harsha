package main.models.productModels.inputModels;

public class HSNInputModel {

	private int HSNcode;
	private double gst;

	public int getHSNcode() {
		return HSNcode;
	}

	public void setHSNcode(int hSNcode) {
		HSNcode = hSNcode;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	@Override
	public String toString() {
		return "HSNInputModel [HSNcode=" + HSNcode + ", gst=" + gst + "]";
	}

}
