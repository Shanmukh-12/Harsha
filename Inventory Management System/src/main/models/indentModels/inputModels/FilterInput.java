package main.models.indentModels.inputModels;

import java.time.LocalDate;

public class FilterInput {
    private String indentStatus;
    private LocalDate fromDate;
    private LocalDate toDate;

    // Getters and setters
	public LocalDate getFromDate() {
		return fromDate;
	}

	public String getIndentStatus() {
		return indentStatus;
	}

	public void setIndentStatus(String indentStatus) {
		this.indentStatus = indentStatus;
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
		return "FilterInput [indentStatus=" + indentStatus + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}


	

}
