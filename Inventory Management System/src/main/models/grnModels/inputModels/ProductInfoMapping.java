package main.models.grnModels.inputModels;

import java.util.List;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

@Component
public class ProductInfoMapping {
	private List<ProductInfo> productsList;


	


	@Override
	public String toString() {
		return "ProductInfoMapping [productsList=" + productsList + "]";
	}


	public ProductInfoMapping(List<ProductInfo> productsList) {
		super();
		this.productsList = productsList;
	}


	public List<ProductInfo> getProductsList() {
		return productsList;
	}


	public void setProductsList(List<ProductInfo> productsList) {
		this.productsList = productsList;
	}


	public ProductInfoMapping() {
		super();
	}
	
	

}
