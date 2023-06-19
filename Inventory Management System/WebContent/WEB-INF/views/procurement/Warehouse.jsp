<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>


</head>


<body>
            <div class="container-fluid px-4">
                <div class="row g-3 my-2">
                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="products">720</h3>
                                <p class="fs-5">Products Count</p>
                            </div>
                            <i class="fas fa-gift fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="categories">4920</h3>
                                <p class="fs-5">Categories Count</p>
                            </div>
                            <i
                                class="fas fa-hand-holding-usd fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="value">3899</h3>
                                <p class="fs-5">Warehouse Value</p>
                            </div>
                            <i class="fas fa-truck fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="stores">%25</h3>
                                <p class="fs-5">Stores</p>
                            </div>
                            <i class="fas fa-chart-line fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="vendors">%25</h3>
                                <p class="fs-5">Vendors</p>
                            </div>
                            <i class="fas fa-chart-line fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>
                </div>
	        
			<canvas id="myChart" ></canvas>
			
            </div>	
</body>

<script>

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
		
		
	

	
</script>




</html>