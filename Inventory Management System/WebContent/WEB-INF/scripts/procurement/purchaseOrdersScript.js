function clearSelections() {
	// Clear the selected option of the <select> element
	tk();
	document.getElementById("indentStatus").selectedIndex = 0;

	// Clear the value of the <input> elements
	document.getElementById("fromDate").value = "";
	document.getElementById("toDate").value = "";
}

$(document).ready(function() {
	function showVendors() {
		$.ajax({
			url: "showVendors",
			type: "GET",
			dataType: "json",
			success: function(response) {
				console.log(response);
				var dropdown = $('#vendorId');
				$("#vendorId option:not(:first)").remove();
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
});
function enableOptions() {
	var selectElement = document.getElementById("vendorId");

	var options = selectElement.options;

	for (var i = 0; i < options.length; i++) {
		options[i].disabled = false;
	}
	selectElement.selectedIndex = 0;
}
function enableOptions2() {
	const table = document.getElementById('dataTable');
	const table1 = document.querySelector('.table');

	const tableDataRows = table.querySelectorAll('tbody tr');
	for (let i = 0; i < tableDataRows.length; i++) {
		const row = tableDataRows[i];
		const newRow = document.createElement('tr');
		for (let j = 0; j < row.cells.length - 3; j++) {
			const cellValue = row.cells[j].textContent;
			const newCell = document.createElement('td');
			newCell.textContent = cellValue;
			newRow.appendChild(newCell);
		}
		const actionCell = document.createElement('td');
		const addButton = document.createElement('button');
		addButton.textContent = 'Add';
		addButton.addEventListener('click', function() {
			moveToTable2(this);
		});
		actionCell.appendChild(addButton);
		newRow.appendChild(actionCell);
		table1.querySelector('tbody').appendChild(newRow);
	}

	// Clear dataTable
	const dataTableBody = table.querySelector('tbody');
	dataTableBody.innerHTML = '';
	var selectElement = document.getElementById("vendorId");

	var options = selectElement.options;

	for (var i = 0; i < options.length; i++) {
		options[i].disabled = false;
	}
	selectElement.selectedIndex = 0;
}
function handleSelectChange() {
	console.log("hello");
	var selectElement = document.getElementById("vendorId");
	var options = selectElement.options;

	for (var i = 0; i < options.length; i++) {
		if (options[i].value !== selectElement.value) {
			options[i].disabled = true;
		}
	}
}
function loadIndentProducts() {
	var indentId = $("#indentId").val();
	console.log(indentId);
	var data = { "indentId": indentId };
	console.log(data);
	$.ajax({
		url: "getInventoryIndentProductsListData",
		method: "get",
		dataType: "json",
		data: { "indentId": JSON.stringify(data) },
		success: function(response) {
			console.log(response);
			mapJsonToTable(response);

		},
		error: function() {
			console.log("Failed to load static page");
			console.log(this.url);
		}
	});
}

function mapJsonToTable(jsonData) {
	var indentTable = $("#indentTable tbody"); // Get the table body element

	// Clear the existing table rows
	indentTable.empty();

	// Iterate over the jsonData and create table rows
	for (var key in jsonData) {
		if (jsonData.hasOwnProperty(key)) {
			var product = jsonData[key];

			// Create a new table row
			var row = $("<tr>");

			// Create table cells and set their content
			var productIdCell = $("<td>").text(product.productId);
			var productNameCell = $("<td>").text(product.productName);
			var quantityCell = $("<td>").text(product.quantity);
			var actionCell = $("<td>").html('<button onclick="moveToTable2(this)">Add</button>');

			// Append the cells to the row
			row.append(productIdCell, productNameCell, quantityCell, actionCell);

			// Append the row to the table body
			indentTable.append(row);
		}
	}
}

$(document).ready(function() {

	tk();
	$("#indentId").change(loadIndentProducts)
	$(".filterButton").click(function() {
		console.log("sjnsjns");
		var indentStatus = $("#indentStatus").val();
		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();

		var data = {
			indentStatus: indentStatus,
			fromDate: fromDate,
			toDate: toDate
		};
		console.log(data);

		$.ajax({
			url: "filterIndents",
			method: "POST",
			data: { filters: JSON.stringify(data) },
			success: function(response) {
				console.log(response);

				var indentIdSelect = $("#indentId"); // Get the select element
				$("#indentId option:not(:first)").remove();
				// Iterate over the response data and append the indentId values as options
				for (var key in response) {
					if (response.hasOwnProperty(key)) {
						var optionValue = response[key].indentId;
						var optionText = response[key].indentId;

						// Create a new option element
						var option = $("<option>").attr("value", optionValue).text(optionText);

						// Append the option to the select element
						indentIdSelect.append(option);
					}
				}
			},
			error: function() {
				console.log("Failed to apply filters");
				console.log(this.data);
			}
		});
	});
});
function tk() {
	console.log("sjnsjns");
	var indentStatus = $("#indentStatus").val();
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	var data = {
		indentStatus: indentStatus,
		fromDate: fromDate,
		toDate: toDate
	};
	console.log(data);

	$.ajax({
		url: "filterIndents",
		method: "POST",
		data: { filters: JSON.stringify(data) },
		success: function(response) {
			console.log(response);

			var indentIdSelect = $("#indentId"); // Get the select element
			$("#indentId option:not(:first)").remove();
			// Iterate over the response data and append the indentId values as options
			for (var key in response) {
				if (response.hasOwnProperty(key)) {
					var optionValue = response[key].indentId;
					var optionText = response[key].indentId;

					// Create a new option element
					var option = $("<option>").attr("value", optionValue).text(optionText);

					// Append the option to the select element
					indentIdSelect.append(option);
				}
			}
		},
		error: function() {
			console.log("Failed to apply filters");
			console.log(this.data);
		}
	});
}
function ButtonAction(button) {
	console.log("hello");
	var row = button.parentNode.parentNode;
	row.parentNode.removeChild(row);
}

function moveToTable2(button) {
	// Get the row of the clicked button
	const row = button.parentNode.parentNode;

	// Get the values from the row
	const product_id = row.cells[0].textContent;
	const product_name = row.cells[1].textContent;
	const quantity = row.cells[2].textContent;

	// Create a new row in table2 with the values
	const newRow = document.createElement("tr");
	const idCell = document.createElement("td");

	const nameCell = document.createElement("td");
	const qtyCell = document.createElement("td");
	const indentqtyCell = document.createElement("td");
	const negCell = document.createElement("td");
	const actionCell = document.createElement("td");
	nameCell.textContent = product_name;
	nameCell.value = product_name;
	idCell.textContent = product_id;
	indentqtyCell.textContent = quantity;
	idCell.value = product_id;
	const inputNegativePrice2 = document.createElement("input");
	inputNegativePrice2.type = "number";
	inputNegativePrice2.id = "order quantity";
	inputNegativePrice2.style.width = "80px";
	inputNegativePrice2.style.height = "20px";
	inputNegativePrice2.setAttribute("required", true);
	inputNegativePrice2.setAttribute("min", "1");
	inputNegativePrice2.setAttribute("value", "1");
	qtyCell.appendChild(inputNegativePrice2);
	newRow.appendChild(idCell);
	newRow.appendChild(nameCell);
	newRow.appendChild(indentqtyCell);
	newRow.appendChild(qtyCell);

	// Add the negative price input field
	const inputNegativePrice = document.createElement("input");
	inputNegativePrice.type = "number";
	inputNegativePrice.id = "negprice";
	inputNegativePrice.style.width = "80px";
	inputNegativePrice.style.height = "20px";
	inputNegativePrice.setAttribute("required", true);
	inputNegativePrice.setAttribute("min", "1");
	inputNegativePrice.setAttribute("value", "1");
	negCell.appendChild(inputNegativePrice);

	// Add the delete button
	const deleteButton = document.createElement("button");
	deleteButton.textContent = "Delete";
	deleteButton.addEventListener("click", function() {
		ButtonActionback(this);
	});
	actionCell.appendChild(deleteButton);

	newRow.appendChild(negCell);

	newRow.appendChild(actionCell);

	// Append the new row to table2
	const table2Body = document.querySelector("#dataTable tbody");
	table2Body.appendChild(newRow);
	row.parentNode.removeChild(row);
	// Remove the row from table1

}

function createpurchase() {
	enableOptions();
	const table = document.getElementById('dataTable');
	const table1 = document.querySelector('.table');
	console.log(table.rows[1]);
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
		} else if (headerRow.cells[j].textContent === "negotiation_price") {
			negotiationpriceindex = j;
		}
		if (productNameColumnIndex !== -1 && negotiationpriceindex !== -1) {
			break;
		}
	}
	console.log(productNameColumnIndex);
	console.log(negotiationpriceindex);

	// Iterate through each row of the table
	for (let i = 1; i < table.rows.length; i++) {
		const row = table.rows[i];
		const rowData = {};

		// Iterate through each cell of the row excluding the "product_name" and "indent quantity" columns
		for (let j = 0; j < row.cells.length - 1; j++) {
			if (j === productNameColumnIndex || headerRow.cells[j].textContent === "indent quantity") continue; // Skip the "product_name" and "indent quantity" columns
			const cell = row.cells[j];
			console.log(cell);
			const cellHeader = headerRow.cells[j].textContent; // Use textContent to get the header

			// Check if the cell contains an input element
			if (cell.firstChild && cell.firstChild.tagName === 'INPUT') {
				// Assign the input field value
				rowData[cellHeader] = cell.firstChild.value;
				if (j === negotiationpriceindex) {
					console.log("hii");
					console.log(cell.firstChild.value);
					const price = parseInt(cell.firstChild.value);
					if (!isNaN(price)) {
						totalamount += price;
					}
				}
			} else {
				// Assign the cell text content
				rowData[cellHeader] = cell.textContent;
				console.log(cell.textContent);
			}
		}

		// Add row data to the tableData array
		tableData.push(rowData);
	}

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
			alert("Purchase order created successfuly");
			$("#indentId").val("");
			loadIndentProducts();
		}
	});


	const tableDataRows = table.querySelectorAll('tbody tr');
	for (let i = 0; i < tableDataRows.length; i++) {
		const row = tableDataRows[i];
		const newRow = document.createElement('tr');
		for (let j = 0; j < row.cells.length - 3; j++) {
			const cellValue = row.cells[j].textContent;
			const newCell = document.createElement('td');
			newCell.textContent = cellValue;
			newRow.appendChild(newCell);
		}
		const actionCell = document.createElement('td');
		const addButton = document.createElement('button');
		addButton.textContent = 'Add';
		addButton.addEventListener('click', function() {
			moveToTable2(this);
		});
		actionCell.appendChild(addButton);
		newRow.appendChild(actionCell);
		table1.querySelector('tbody').appendChild(newRow);
	}

	// Clear dataTable
	const dataTableBody = table.querySelector('tbody');
	dataTableBody.innerHTML = '';
	// Output the JSON data

}

function ButtonActionback(button) {
	var row = button.parentNode.parentNode;
	var tableData = [];

	// Get the values from the row
	var product_id = row.cells[0].textContent;
	var product_name = row.cells[1].textContent;
	var purchase_order_quantity = row.cells[3].textContent;
	var negotiation_price = row.cells[4].textContent;
	var indent_quantity = row.cells[2].textContent;

	// Create a new row in the "indentTable" table with the values
	var newRow = document.createElement("tr");
	var idCell = document.createElement("td");
	var nameCell = document.createElement("td");
	var qtyCell = document.createElement("td");
	var indentqtyCell = document.createElement("td");
	var negCell = document.createElement("td");
	var actionCell = document.createElement("td");
	var newCell = document.createElement("td");
	newCell.textContent = "#";
	idCell.textContent = product_id;
	nameCell.textContent = product_name;
	qtyCell.textContent = purchase_order_quantity;
	indentqtyCell.textContent = indent_quantity;

	newRow.appendChild(idCell);
	newRow.appendChild(nameCell);
	newRow.appendChild(indentqtyCell);
	newRow.appendChild(actionCell);

	// Add the delete button
	var deleteButton = document.createElement("button");
	deleteButton.textContent = "Add";
	deleteButton.addEventListener("click", function() {
		moveToTable2(this)
	});
	actionCell.appendChild(deleteButton);

	// Append the new row to the "indentTable" table
	var indentTableBody = document.querySelector("#indentTable tbody");
	indentTableBody.appendChild(newRow);

	// Remove the row from the "dataTable" table
	row.parentNode.removeChild(row);

	// Construct the data object with the values
	var data = {
		product_id: product_id,
		product_name: product_name,
		purchase_order_quantity: purchase_order_quantity,
		negotiation_price: negotiation_price,
		indent_quantity: indent_quantity
	};

	// Add the data object to the tableData array
	tableData.push(data);

	// Output the tableData array (you can replace this with your desired logic)
	console.log(tableData);
}