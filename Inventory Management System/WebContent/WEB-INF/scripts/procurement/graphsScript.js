function graph() {
	var ctx = document.getElementById('myChart').getContext('2d');
	$.ajax({
		url: "getStock",
		type: "GET",
		dataType: "json",
		success: function(response) {
			// Handle the response from the servlet if needed
			console.log(response);
			var name = [];
			var stock = [];
			response.forEach(function(value) {
				stock.push(value.stock.product_stock);
				name.push(value.product.product_name);

			})



			// Define chart data
			var data = {
				labels: name,
				datasets: [{
					label: 'stock',
					data: stock,
					backgroundColor: 'rgba(54, 162, 235, 0.5)', // Bar color
					borderColor: 'rgba(54, 162, 235, 1)', // Border color
					borderWidth: 1 // Border width
				}]
			};

			var myChart = new Chart(ctx, {
				type: 'bar',
				data: data,
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			});
		},
		error: function() {
			// Handle errors
			console.error("AJAX request failed.");
		}
	});



}