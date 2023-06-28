const selectedOptions = [];

document.getElementById("addProductsId").addEventListener("click", function(event) {

	handleSelectChangeprid()
	event.preventDefault();
	var prid = document.getElementById("prid").value;
	var qnty = document.getElementById("qnty").value;
	var vid = document.getElementById("vid").value;
	var negPrice = document.getElementById("negPrice").value;


	var table = document.getElementById("dataTable1");
	var row = table.insertRow(-1);
	var pridCell = row.insertCell(0);
	var qntyCell = row.insertCell(1);
	var vidCell = row.insertCell(2);
	var negPriceCell = row.insertCell(3);
	var actionCell = row.insertCell(4);

	pridCell.innerHTML = prid;
	qntyCell.innerHTML = qnty;
	vidCell.innerHTML = vid;
	negPriceCell.innerHTML = negPrice;
	actionCell.innerHTML = '<button class="delete-btn" onclick="deleteRow(this)">Delete</button>';

	document.getElementById("negPrice").value = '';
	document.getElementById("qnty").value = '';
	var selectElement = document.getElementById("prid");
	selectElement.selectedIndex = 0;
	console.log("hi" + selectedOptions);



});
$(document).ready(function() {
	function showVendors() {
		$.ajax({
			url: "showVendors",
			type: "GET",
			dataType: "json",
			success: function(response) {
				console.log(response);
				var dropdown = $('#vid');
				dropdown.empty();
				dropdown.append($('<option>').text("Select an option").prop('disabled', true).prop('selected', true));
				$.each(response, function(index, vendor) {
					var option = $('<option>').val(vendor.vendorId).text(vendor.vendorId + "(" + vendor.vendorName + ")");
					dropdown.append(option);
				});

			},
			error: function(xhr, status, error) {
				console.log("Error:", error);
			}
		});
	}

	// Call the function to initiate the AJAX request
	showVendors();
	function getProductCategories() {

		$.ajax({
			url: "getProductCategories",
			type: "POST",
			dataType: "json",
			success: function(response) {
				console.log(response);
				var dropdown = $('#prcat');
				// Clear existing options
				dropdown.find('option:not(:first)').remove();
				// Iterate over the response data and create dropdown options
				for (var i = 0; i < response.length; i++) {
					var category = response[i];
					var optionValue = category.productCategoryId;
					var optionLabel = category.productCategoryName + ' (' + category.productCategoryId + ')';
					var option = $('<option>').val(optionValue).text(optionLabel);
					dropdown.append(option);
				}

			},

			error: function(xhr, status, error) {
				console.log("Error:", error);
			}
		});
	}

	getProductCategories();
	function getProducts() {
		// ...

		var dropdown = $('#prcat');

		// Event listener for the dropdown change event
		dropdown.on('change', function() {
			// Get the selected option value from the dropdown
			var selectedOption = $(this).val();
			console.log(selectedOption);
			console.log("hi" + selectedOptions);

			// Extract the category ID from the selected option
			var categoryId = selectedOption;

			// Make the AJAX request to the "/getProducts" endpoint
			$.ajax({
				url: "getProductStockData",
				method: "POST",
				data: {
					"categoryId": categoryId
				},
				success: function(response) {
					// Handle the response data here
					console.log(response);
					console.log(this.data);
					var dropdown = $('#prid');
					var addedOptions = {};
					dropdown.empty();
					dropdown.append($('<option>').text("Select a product").prop('disabled', true).prop('selected', true));
					$.each(response, function(index, product) {
						var productId = product.productId.toString();
						var productName = product.productName;
						var optionText = productName + " (" + productId + ")";
						if (!addedOptions[optionText]) {
							var option = $('<option>').val(productId).text(optionText);
							dropdown.append(option);
							addedOptions[optionText] = true;
						}
					});
					handleSelectChangeprid()

					// ...
				},
				error: function(xhr, status, error) {
					// Handle error responses here
					console.log("Error:", error);
					console.log(this.data);

				}
			});
		});

		// ...
	}
	getProducts();

});

function getProducts() {
	// ...

	var dropdown = $('#prcat');

	// Event listener for the dropdown change event
	dropdown.on('change', function() {
		// Get the selected option value from the dropdown
		var selectedOption = $(this).val();


		// Extract the category ID from the selected option
		var categoryId = selectedOption.match(/\((\d+)\)/)[1];

		// Make the AJAX request to the "/getProducts" endpoint
		$.ajax({
			url: "getProducts",
			type: "POST",
			data: {
				categoryId: categoryId
			},
			dataType: "json",
			success: function(response) {
				// Handle the response data here
				console.log(response);
				// ...
			},
			error: function(xhr, status, error) {
				// Handle error responses here
				console.log("Error:", error);
			}
		});
	});

	// ...
}
function deleteRow(button) {
	var row = button.parentNode.parentNode;
	var productId = row.cells[0].textContent; // Assuming the product ID is in the first cell of the row

	// Find the index of the product ID in the selectedOptions array
	var index = selectedOptions.indexOf(productId);

	if (index !== -1) {
		// Remove the product ID from the selectedOptions array
		selectedOptions.splice(index, 1);
	}
	handleSelectChangeprid()
	console.log("hi" + selectedOptions);

	row.parentNode.removeChild(row);

}
function handleSelectChange() {
	console.log("hello");
	var selectElement = document.getElementById("vid");
	var options = selectElement.options;

	for (var i = 0; i < options.length; i++) {
		if (options[i].value !== selectElement.value) {
			options[i].disabled = true;
		}
	}
}

function handleSelectChangeprid() {
	var selectElement = document.getElementById("prid");
	var selectedValue = selectElement.value;

	if (!selectedOptions.includes(selectedValue)) {
		selectedOptions.push(selectedValue);
	}

	var options = selectElement.options;
	for (var i = 0; i < options.length; i++) {
		if (selectedOptions.includes(options[i].value)) {
			options[i].disabled = true;
		} else {
			options[i].disabled = false;
		}
	}
}
function enableOptions() {
	console.log(selectedOptions);
	var selectElement = document.getElementById("vid");

	var options = selectElement.options;

	for (var i = 0; i < options.length; i++) {
		options[i].disabled = false;
	}
	selectElement.selectedIndex = 0;
}
function clear() {
	$("#prid").val("");


}

function enableOptions2() {
	console.log(selectedOptions);
	$("#prid option:not(:first)").remove();

	enableOptionspr();
	$("#prcat").val("");

	const table = document.getElementById('dataTable1');
	table.innerHTML = "";


	var selectElement = document.getElementById("vid");

	var options = selectElement.options;

	for (var i = 0; i < options.length; i++) {
		options[i].disabled = false;
	}
	selectElement.selectedIndex = 0;
	selectedOptions.length = 0;

}

function enableOptionspr() {
	var selectElement = document.getElementById("prid");

	var options = selectElement.options;

	for (var i = 0; i < options.length; i++) {
		options[i].disabled = false;
	}
	selectElement.selectedIndex = 0;
}

function createpurchase() {
	enableOptions();
	enableOptionspr();


	// Retrieve the table element
	const table = document.getElementById('dataTable');
	console.log(table.rows[1])
	var totalamount = 0;
	// Create an array to store the table data
	const tableData = [];

	// Get the index of the "product_name" column
	let productNameColumnIndex = -1;
	let negotiationpriceindex = -1;
	const headerRow = table.rows[0];
	for (let j = 0; j < headerRow.cells.length; j++) {
		if (headerRow.cells[j].textContent === "product_name") {
			productNameColumnIndex = j;
			break;
		}
	}
	for (let j = 0; j < headerRow.cells.length; j++) {
		if (headerRow.cells[j].textContent === "negotiation_price") {
			negotiationpriceindex = j;

			break;
		}
	}
	

	// Iterate through each row of the table
	for (let i = 1; i < table.rows.length; i++) {
		const row = table.rows[i];
		const rowData = {};

		// Iterate through each cell of the row excluding the "product_name" column
		for (let j = 0; j < row.cells.length - 1; j++) {
			if (j === productNameColumnIndex) continue; // Skip the "product_name" column
			const cell = row.cells[j];
			console.log(cell);
			const cellHeader = headerRow.cells[j].textContent; // Use textContent to get the header


			// Check if the cell contains an input element
			if (cell.firstChild && cell.firstChild.tagName === 'INPUT') {
				// Assign the input field value

				if (j === negotiationpriceindex - 1) {
					console.log("hii");
					console.log(cell.firstChild.value);
					const price = parseInt(cell.firstChild.value);
					if (!isNaN(price)) {
						totalamount += price;
					}
				}
				rowData[cellHeader] = cell.firstChild.value;
			} else {
				// Assign the cell text content
				rowData[cellHeader] = cell.textContent;
				console.log(cell.textContent);


			}
		}

		// Add row data to the tableData array
		tableData.push(rowData);
	}
	console.log(totalamount);

	var children = [];

	const rows = {};
	rows["vendor_id"] = "20001";
	rows["purchase_order_amount"] = totalamount;

	rows["children"] = tableData;
	children.push(rows);

	console.log(JSON.stringify(rows));


	var datas = { "purchase_order_date": "2023-06-13", "purchase_order_amount": "5000.0", "vendor_id": "1", "purchase_order_expected_date": "2023-06-20", "purchase_order_status": "Active", "user_id": 2, "last_updated_user": "John Doe", "last_updated_date": "2023-06-13", "children": [{ "product_id": 1, "purchase_order_quantity": 10, "negotiation_price": 100, "quantity_received": 5 }, { "product_id": 2, "purchase_order_quantity": 5, "negotiation_price": 50, "quantity_received": 0 }] }



	// Convert tableData to JSON string
	const jsonData = JSON.stringify(rows);
	$.ajax({
		url: "makePurchseOrder",
		method: "POST",
		contentType: "application/json",

		data: jsonData,
		success: function() {

			selectedOptions.length = 0;

			alert("Purchase order created successfuly");
			var table = document.getElementById('dataTable1');
			table.innerHTML = "";


		}
	});

}