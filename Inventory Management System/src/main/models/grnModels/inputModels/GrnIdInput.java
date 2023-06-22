package main.models.grnModels.inputModels;

public class GrnIdInput {

	private int grnId;

	public GrnIdInput(int grnId) {
		super();
		this.grnId = grnId;
	}

	public GrnIdInput() {
		super();
	}

	public int getGrnId() {
		return grnId;
	}

	public void setGrnId(int grnId) {
		this.grnId = grnId;
	}

	@Override
	public String toString() {
		return "GrnIdInput [grnId=" + grnId + "]";
	}

}
