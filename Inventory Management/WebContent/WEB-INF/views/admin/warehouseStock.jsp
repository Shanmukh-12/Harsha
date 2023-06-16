<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>


<body>
            <div class="container-fluid px-4">
                <div class="row g-3 my-2">
                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="categories">320</h3>
                                <p class="fs-5">Categories Count</p>
                            </div>
                            <i class="fas fa-gift fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="products">4920</h3>
                                <p class="fs-5">Products Count</p>
                            </div>
                            <i class="fas fa-gift fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="warehouseValue">Rs.4,65,34,678</h3>
                                <p class="fs-5">Warehouse value</p>
                            </div>
                            <i
                                class="fas fa-hand-holding-usd fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="stores">25</h3>
                                <p class="fs-5">Stores</p>
                            </div>
                            <i class="fas fa-chart-line fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <h3 class="fs-2" id="vendors">5</h3>
                                <p class="fs-5">Vendors</p>
                            </div>
                            <i class="fas fa-chart-line fs-1 primary-text border rounded-full secondary-bg p-3"></i>
                        </div>
                    </div>
                </div>
		
			<canvas id="myChart"></canvas>
            </div>	
</body>

<script>



$(document).ready(function(){

	$.ajax({
		url:"https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js",
		method:"get",
		success:function(){console.log("Got one data");}
		
	});

	$.ajax({
		url:"https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js",
		method:"get",
		success:function(){console.log("Got two data");}		
	});
	$.ajax({
		url:"getWareHouseData",
		method:"post",
		success:function(responseData)
		{
			console.log(responseData);
			// Extracting product names and quantities from the response
			const labels = responseData.map((item) => item.productName);
			const data = responseData.map((item) => item.productQuantity);

			// Creating a bar chart
			const ctx = document.getElementById('myChart').getContext('2d');
			new Chart(ctx, {
			  type: 'bar',
			  data: {
			    labels: labels,
			    datasets: [{
			      label: 'Product Quantity',
			      data: data,
			      backgroundColor: 'rgba(75, 192, 192, 0.6)',
			      borderColor: 'rgba(75, 192, 192, 1)',
			      borderWidth: 1
			    }]
			  },
			  options: {
			    scales: {
			      y: {
			        beginAtZero: true
			      }
			    }
			  }
			});

		}
	
	});
	
	
});
</script>




</html>