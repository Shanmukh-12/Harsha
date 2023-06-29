$(document).ready(function() {
	Myfunction2('warehousestock');
});

function logout() {
	$.ajax({
		url: "logout",
		method: "GET",
		success: function(response) {
			console.log("Logout successful");
			console.log(response);
			location.href = "/inventory/";
		},
		error: function() {
			console.log("Logout error");
		}
	});
}
function ButtonAction(button) {
	console.log("hello");
	var row = button.parentNode.parentNode;
	row.parentNode.removeChild(row);
}

$("document").ready(
	function() {

		$.ajax({
			url: "https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js",
			dataType: "script",
			success: function() {

			}
		});
		$.ajax({
			url: "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js",
			dataType: "script",
			success: function() {

			}
		});



	}

);

function Myfunction(containerurl) {
	$.ajax({
		url: containerurl,
		method: "GET",
		success: function(response) {
			console.log("AJAX call successful");

			$("#content").html(response);

			var table = $("#example");
			var elementsArray = $("table thead th").toArray();

			for (var j = 0; j < 6; j++) {
				var tr = $("<tr>");
				for (var i = 0; i < (elementsArray.length); i++) {

					if (i === (elementsArray.length) - 1) {
						var td1 = $("<td>");
						var btn = $("<button>");
						btn.text("Delete");
						btn.on("click", function() {
							ButtonAction(this);

						}


						);
						td1.append(btn);
					}
					else {

						var td1 = $("<td>").text("hello");

					}
					tr.append(td1);
				}

				table.append(tr);
			}
			table.DataTable();
			console.log("afterwer");
		},
		error: function() {
			console.log("AJAX call error");
		}
	});
}
function Myfunction2(containerurl) {
	$.ajax({
		url: containerurl,
		method: "GET",
		success: function(response) {
			console.log("AJAX call successful");

			$("#content").html(response);
			var ctx1 = document.getElementById('myChart');
			ctx1.width = 300; // Set the width
			ctx1.height = 130;
			var ctx = ctx1.getContext('2d');

			$.ajax({
				url: "getStock",
				method: "GET",
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
						responsive: true,
						maintainAspectRatio: false,
						width: 100,
						height: 100,
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



		},
		error: function() {
			console.log("AJAX call error");
		}
	});
}

function indentsFunction(controllerUrl) {
	$.ajax({
		url: controllerUrl,
		method: "POST",
		success: function(response) {
			$("#content").html(response);
		},
		error: function() {
			console.log("AJAX call error");
		}
	});

}
var el = document.getElementById("wrapper");
var toggleButton = document.getElementById("menu-toggle");

toggleButton.onclick = function() {
	el.classList.toggle("toggled");
};