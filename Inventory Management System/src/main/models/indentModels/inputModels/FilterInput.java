package main.models.indentModels.inputModels;

import java.time.LocalDate;

public class FilterInput {
    private int indentId;
    private LocalDate fromDate;
    private LocalDate toDate;

    // Getters and setters


    public int getIndentId() {
		return indentId;
	}

	public void setIndentId(int indentId) {
		this.indentId = indentId;
	}




	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "FilterInput [indentId=" + indentId + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	

}
