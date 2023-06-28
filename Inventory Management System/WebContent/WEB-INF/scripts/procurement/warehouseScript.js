$(document).ready(function(){

	$.ajax({
		
		  url: "getTotalProductsCount",
		  type: "GET",
		  dataType:"json",
		  success: function(response) {
			  console.log("hii");
			  console.log(response);
			  var data=response;
			  
			  document.getElementById("products").textContent = data.total_product_stock;
	
		    // Handle the response from the servlet if needed
	          
		  },
		  error: function() {
		    // Handle errors
		    console.error("AJAX request failed.");
		  }
		});
	$.ajax({
		
		  url: "getCategoriesCount",
		  type: "GET",
		  dataType:"json",
		  success: function(response) {
			  console.log("hii");
			  console.log(response);
			  var data=response;
			  
			  document.getElementById("categories").textContent = data.total_product_category_count;
	
		    // Handle the response from the servlet if needed
	          
		  },
		  error: function() {
		    // Handle errors
		    console.error("AJAX request failed.");
		  }
		});
	$.ajax({
		
		  url: "getWarehouseValue",
		  type: "GET",
		  dataType:"json",
		  success: function(response) {
			  console.log("hii");
			  console.log(response);
			  var data=response;
			  
			  document.getElementById("value").textContent = data.total_warehouse_value;
	
		    // Handle the response from the servlet if needed
	          
		  },
		  error: function() {
		    // Handle errors
		    console.error("AJAX request failed.");
		  }
		});
	$.ajax({
		
		  url: "getVendorsCount",
		  type: "GET",
		  dataType:"json",
		  success: function(response) {
			  console.log("hii");
			  console.log(response);
			  var data=response;
			  
			  document.getElementById("vendors").textContent = data.vendorcount;
	
		    // Handle the response from the servlet if needed
	          
		  },
		  error: function() {
		    // Handle errors
		    console.error("AJAX request failed.");
		  }
		});
	
	
	
});