$(document).ready(function() {
	function showVendors() {
		$.ajax({
			url: "showVendors",
			method: "GET",
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
$(document).ready(function() {
	applyFilter();
	$("#purchaseId").click(getPurchaseItems);
	$("#vendorId").on('click', function() {
		$.ajax({
			url: "getGrnList",
			method: "POST",
			success: function(data) {

				// Clear existing options
				$("#vendorId").empty();

				// Populate options from response data
				data.forEach(function(vendor) {
					$("<option>").val(vendor.vendor_id).text(vendor.vendor_name).appendTo("#vendorId");
				});
			},
			error: function() {
				console.log("Error111");
			}
		});
	});
});







function addToGRNList(button) {
	var row = button.parentNode.parentNode;
	var table = document.getElementById("grnListTable");
	var newRow = table.tBodies[0].insertRow(-1);

	for (var i = 0; i < row.cells.length + 8; i++) {
		var newCell = newRow.insertCell(i);
		if (i === row.cells.length + 7) {
			newCell.innerHTML = '<button onclick="deleteFromGRNList(this)">Delete</button>';
		} else if (i === 10) {
			newCell.innerHTML = '<input id="bonusId" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';
		} else if (i === 4) {
			newCell.innerHTML = '<input id="cgst" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';
		} else if (i === 5) {
			newCell.innerHTML = '<input id="sgst" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';
		} else if (i === 6) {
			newCell.innerHTML = '<input id="igst" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';


		} else if (i === 7) {
			newCell.innerHTML = '<input id="totalprice" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';


		} else if (i === 11) {
			newCell.innerHTML = '<input id="mrp" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';


		} else if (i === 8) {
			newCell.innerHTML = '<input id="batchno" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';


		} else if (i === 9) {
			newCell.innerHTML = '<input id="currentReceivedId" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';
		} else {
			newCell.innerHTML = row.cells[i].innerText;
		}
	}
	row.remove();
}

function deleteFromGRNList(button) {
	var row = button.parentNode.parentNode;
	var table = document.getElementById("purchasedItemsTable");
	var originalRow = document.createElement("tr");

	// Extract the data from the row being deleted
	var rowData = [];
	for (var i = 0; i < row.cells.length - 9; i++) {
		rowData.push(row.cells[i].innerHTML);
	}
	console.log(rowData);
	// Create cells in the original row and populate with the extracted data
	for (var j = 0; j < rowData.length; j++) {
		var newCell = originalRow.insertCell(j);
		newCell.innerHTML = rowData[j];
	}
	var newCell = originalRow.insertCell(rowData.length);
	newCell.innerHTML = '<button onclick="addToGRNList(this)">Add</button>';

	// Append the original row to the purchasedItemsTable
	table.tBodies[0].appendChild(originalRow);

	// Remove the row from the grnListTable
	row.remove();
}




function getTableData() {
	const table = document.getElementById('grnListTable');

	const tableData = [];
	const headerRow = table.rows[0];

	// Iterate through each row of the table
	for (let i = 1; i < table.rows.length; i++) {
		const row = table.rows[i];
		const rowData = {};

		// Iterate through each cell of the row excluding the "product_name" column
		for (let j = 0; j < row.cells.length - 1; j++) {
			if (headerRow.cells[j].id) {
				// Check if the cell contains an input tag or plain text
				const cell = row.cells[j];
				const cellHeader = headerRow.cells[j].id; // Use textContent to get the header

				if (cell.querySelector("input")) {
					rowData[cellHeader] = cell.querySelector("input").value; // Get input value
				} else {
					rowData[cellHeader] = cell.textContent.trim(); // Get plain text content
				}
			}
		}

		// Add row data to the tableData array
		tableData.push(rowData);
	}
	const jsonData = {};
	jsonData["purchaseOrderId"] = document.getElementById("purchaseId").value;
	jsonData["productsList"] = tableData;

	return jsonData;
}

function createGRN() {
	var data = getTableData();
	const jsonData = JSON.stringify(data);

	$.ajax({
		url: "makeGrn",
		method: "post",
		data: {
			"jsonData": jsonData
		},
		success: function(page) {
			alert("created successfully");
		}

	});
}

function applyFilter() {

	var vendorIdf = $("#vendorId option:selected").val();
	var vendorId = parseInt(vendorIdf || 0);
	var expectedDate = $("#expectedDate").val();
	var expectedDate1 = $("#expectedDate1").val();

	$.ajax({
		url: "getPurchaseIdDetails",
		method: "GET",

		data: {
			"vendor_id": vendorId,
			"purchase_order_expected_date": expectedDate,
			"purchase_order_expected_date1": expectedDate1

		},


		success: function(response) {

			populatePurchaseOrderIds(response);


		},
		error: function() {

			console.log("AJAX call error");
		}



	});


};

function populatePurchaseOrderIds(jsonArray) {
	var selectElement = document.getElementById("purchaseId");
	$("#purchaseId").empty();

	for (var i = 0; i < jsonArray.length; i++) {
		var purchaseOrder = jsonArray[i];
		var option = document.createElement("option");
		option.value = purchaseOrder.purchase_order_id;
		option.text = purchaseOrder.purchase_order_id;
		selectElement.appendChild(option);
	}
}

// Getting purchase items
function getPurchaseItems() {
	var purchaseIdSelect = document.getElementById("purchaseId");
	var PurchasesId = purchaseIdSelect.value;

	$.ajax({
		url: "getPurchaseProducts",
		method: "GET",

		data: {

			"purchase_order_id": PurchasesId
		},


		success: function(response) {

				populatePurchasedItemsTable(response);

			}




			,
		error: function() {
			console.log("AJAX call error");
		}



	});


};

function populatePurchasedItemsTable(data) {
	var purchasedItemsTable = document.getElementById("purchasedItemsTable");
	var tbody = purchasedItemsTable.getElementsByTagName("tbody")[0];
	tbody.innerHTML = ""; // Clear existing table body

	for (var i = 0; i < data.length; i++) {
		var po = data[i].po;
		var pop = data[i].pop;

		var productID = pop.product_id;
		var orderedQuantity = po.children.find(function(child) {
			return child.product_id === productID;
		}).purchase_order_quantity;
		var receivedQuantity = pop.quantity_received;
		var cost = pop.negotiation_price * receivedQuantity;

		var row = document.createElement("tr");

		var productIDCell = document.createElement("td");
		productIDCell.textContent = productID;
		row.appendChild(productIDCell);

		var orderedQuantityCell = document.createElement("td");
		orderedQuantityCell.textContent = orderedQuantity;
		row.appendChild(orderedQuantityCell);

		var receivedQuantityCell = document.createElement("td");
		receivedQuantityCell.textContent = receivedQuantity;
		row.appendChild(receivedQuantityCell);

		var costCell = document.createElement("td");
		costCell.textContent = cost;
		row.appendChild(costCell);

		var actionCell = document.createElement("td");
		var addButton = document.createElement("button");
		addButton.textContent = "Add";
		addButton.onclick = function() {
			addToGRNList(this);
		};
		actionCell.appendChild(addButton);
		row.appendChild(actionCell);

		tbody.appendChild(row);
	}
}