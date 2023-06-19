package main.models.procurementModels.inputmodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductInputMapping {
	List<ProductInput> pi;

	public List<ProductInput> getPi() {
		return pi;
	}

	public void setPi(List<ProductInput> pi) {
		this.pi = pi;
	}

	public ProductInputMapping(List<ProductInput> pi) {
		super();
		this.pi = pi;
	}

	public ProductInputMapping() {
		
	}

	@Override
	public String toString() {
		return "ProductInputMapping [pi=" + pi + "]";
	}
	

}
