package main.models.indentModels.outputModels;

import java.time.LocalDate;

public class FilteredIndent {
    private int indentId;
    private LocalDate indentDate;
    private String indentStatus;

    // Constructor, getters, and setters
    
    
    

    public int getIndentId() {
        return indentId;
    }

    public FilteredIndent(int indentId, LocalDate indentDate, String indentStatus) {
		super();
		this.indentId = indentId;
		this.indentDate = indentDate;
		this.indentStatus = indentStatus;
	}

	public void setIndentId(int indentId) {
        this.indentId = indentId;
    }

    public LocalDate getIndentDate() {
        return indentDate;
    }

    public void setIndentDate(LocalDate indentDate) {
        this.indentDate = indentDate;
    }

    public String getIndentStatus() {
        return indentStatus;
    }

    public void setIndentStatus(String indentStatus) {
        this.indentStatus = indentStatus;
    }
}