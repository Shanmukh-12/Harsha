package main.models.storeissuesModels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_StoreIssues_Products")

public class StoreIssuesProducts implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "StoreIssue_ID")
	int storeIssueId;
	@Id
	@Column(name = "Product_ID")
	int productId;
	@Column(name = "Quantity")
	int qty;

	@ManyToOne
	@JoinColumn(name = "StoreIssue_ID", referencedColumnName = "StoreIssue_ID", insertable = false, updatable = false)
	ProcurementIndentsList pil;

	
}
