$(document).ready(function() {
	applyFilter();
	displayVendors();
});

function applyFilter() {

	var vendorIdf = $("#vendorId option:selected").val();
	var vendorId = parseInt(vendorIdf || 0);
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	console.log(vendorId);
	$.ajax({
		url: "getGrnList",
		method: "GET",

		data: {"filters":JSON.stringify({
			"vendor_id": vendorId,
			"grn_amount": 0,
			"grnFromDate": fromDate,
			"grnToDate": toDate})

		},

		success: function(response) {

			var PurchasesId = $("#PurchasesId");
			appendGRNDataToContainer(response);


		},
		error: function() {

			console.log("AJAX call error");
		}

	});

}

function displayVendors() {
	$.ajax({
		url: "showVendors",
		method: "GET",
		success: function(response) {
			populateVendorOptions(response);

		},
		error: function() {
			console.log("error");
		}
	});
}

function populateVendorOptions(vendors) {
	var selectElement = document.getElementById('vendorId');

	// Clear any existing options
	selectElement.innerHTML = '';

	// Add the default option
	var defaultOption = document.createElement('option');
	defaultOption.value = '';
	defaultOption.text = 'Select Vendor';
	selectElement.appendChild(defaultOption);

	// Iterate over each vendor object
	for (var i = 0; i < vendors.length; i++) {
		var vendor = vendors[i];

		// Extract vendor ID and vendor name
		var vendorId = vendor.vendorId;
		var vendorName = vendor.vendorName;


		// Create an option element
		var option = document.createElement('option');
		option.value = vendorId;
		option.text = vendorId + '-' + vendorName;

		// Append the option to the select element
		selectElement.appendChild(option);
	}
}





function PurchaseOrderData(data) {
	var container = $(".container");

	// Clear existing data
	container.empty();
	var h1Element = $('<h1 class="text-center mb-4" align="center">Purchases Data</h1>');
	$('.container').append(h1Element);

	// Create the main container div
	var mainDiv = $('<div class="issues-block"></div>');

	// Create the Purchase ID heading
	var purchaseIdHeading = $('<h4 class="store-indent-id" id="PurchasesId">Purchase ID: <span class="bold">' +
		data.purchase_order_id + '</span></h4>');

	// Create the issue details div
	var issueDetailsDiv = $('<div class="issue-details"></div>');

	// Create and append the span elements for the details
	var vendorIdSpan = $('<span class="label">Vendor ID:</span><span>' +
		data.vendor_id + '</span>');
	var expectedDateSpan = $('<span class="label">Purchase Order Expected Date:</span><span>' +
		data.purchase_order_expected_date + '</span>');
	var amountSpan = $('<span class="label">Purchase Order amount:</span><span>' +
		data.purchase_order_amount + '</span>');

	// Append the detail span elements to the issue details div
	issueDetailsDiv.append(vendorIdSpan, expectedDateSpan,
		amountSpan);

	// Create the "View Products" button
	var viewProductsButton = $('<button type="button" value="view products" class="btn-issues" onclick="getGRNProducts(' +
		data.purchase_order_id +
		')">View Products</button>');

	// Append all the elements to the main container div
	mainDiv.append(purchaseIdHeading, issueDetailsDiv,
		viewProductsButton);

	// Append the main container div to the overall container
	container.append(mainDiv);
}


function getPurchaseId() {

	var pid = $("#grnid").val();

	console.log(pid)
	$.ajax({
		url: "getPurchaseId3",
		method: "GET",

		data: {

			"purchase_order_id": pid
		},

		success: function(response) {

				mapJSONToHTML(response);

			}

			,
		error: function() {
			console.log(this.url);
			console.log("AJAX call error");
		}

	});

};

function refresh() {
	applyFilter();
}

function appendGRNDataToContainer(data) {
	const container = document.querySelector('.container');
	$(".container").empty();

	//var h1Element = $('<h1 class="text-center mb-4" align="center">GRN Data</h1>');
	// $('.container').append(h1Element);

	// Clear existing data
	data.forEach((item) => {
		const grnId = item.grnId;
		const vendorId = item.vendor_id;
		const purchaseOrderId = item.purchase_order_id;
		const [year, month, day] = item.grnDate;
		const grnDate = new Date(year, month - 1, day).toISOString().split('T')[0];
		const grnAmount = item.grnAmount.toFixed(2);

		const issueBlock = document.createElement('div');
		issueBlock.classList.add('issues-block');

		const issueDetails = document.createElement('div');
		issueDetails.classList.add('issue-details');

		const grnIdHeader = document.createElement('h4');
		grnIdHeader.classList.add('store-indent-id');
		grnIdHeader.innerHTML = 'GRN ID: <span class="bold">' + item.grnId + '</span>';

		const vendorIdLabel = document.createElement('span');
		vendorIdLabel.classList.add('label');
		vendorIdLabel.innerHTML = 'Vendor ID:';

		const vendorIdValue = document.createElement('span');
		vendorIdValue.innerHTML = vendorId;

		const purchaseOrderIdLabel = document.createElement('span');
		purchaseOrderIdLabel.classList.add('label');
		purchaseOrderIdLabel.innerHTML = 'Purchase Order ID:';

		const purchaseOrderIdValue = document.createElement('span');
		purchaseOrderIdValue.innerHTML = purchaseOrderId;

		const grnDateLabel = document.createElement('span');
		grnDateLabel.classList.add('label');
		grnDateLabel.innerHTML = 'GRN Received Date:';

		const grnDateValue = document.createElement('span');
		grnDateValue.innerHTML = grnDate;

		const grnAmountLabel = document.createElement('span');
		grnAmountLabel.classList.add('label');
		grnAmountLabel.innerHTML = 'GRN Cost:';

		const grnAmountValue = document.createElement('span');
		grnAmountValue.innerHTML = grnAmount;

		const viewProductsButton = document.createElement('button');
		viewProductsButton.setAttribute('type', 'button');
		viewProductsButton.setAttribute('value', 'view products');
		viewProductsButton.classList.add('btn-issues');
		viewProductsButton.innerHTML = 'View Products';

		// Add onclick attribute with grnId as function parameter
		viewProductsButton.onclick = function() {
			getGRNProducts(item.grnId);
		};

		issueDetails.appendChild(vendorIdLabel);
		issueDetails.appendChild(vendorIdValue);
		issueDetails.appendChild(purchaseOrderIdLabel);
		issueDetails.appendChild(purchaseOrderIdValue);
		issueDetails.appendChild(grnDateLabel);
		issueDetails.appendChild(grnDateValue);
		issueDetails.appendChild(grnAmountLabel);
		issueDetails.appendChild(grnAmountValue);

		issueBlock.appendChild(grnIdHeader);
		issueBlock.appendChild(issueDetails);
		issueBlock.appendChild(viewProductsButton);

		container.appendChild(issueBlock);
	});
}

// Example usage



function clearSelection() {
	$('#vendorId').val('');

}

function getGRNProducts(grnId) {

	console.log(vendorId);

	$.ajax({
		url: "getGrnProducts",
		method: "GET",

		data: {

			"grnId": grnId
		},

		success: function(response) {

				createTable(response);

			}

			,
		error: function() {

			console.log("AJAX call error");
		}

	});

};

function createTable(data) {
	// Create the table element
	const table = document.createElement('table');
	table.classList.add('data-table', 'table', 'bg-white', 'rounded', 'shadow-sm', 'table-hover');

	// Create the table headers
	const headers = ['Product ID', 'Batch No', 'Quantity', 'Bonus', 'Total Quantity', 'Price'];
	const thead = document.createElement('thead');
	const headerRow = document.createElement('tr');

	headers.forEach((headerText) => {
		const th = document.createElement('th');
		th.textContent = headerText;
		headerRow.appendChild(th);
	});

	thead.appendChild(headerRow);
	table.appendChild(thead);

	// Create the table body
	const tbody = document.createElement('tbody');

	data.forEach((item) => {
		const row = document.createElement('tr');

		// Populate the row with data
		const columns = [
			'product_id',
			'batch_no',
			'quantity',
			'bonus',
			'totalQuantity',
			'price'
		];

		columns.forEach((column) => {
			const cell = document.createElement('td');
			cell.textContent = item[column];
			row.appendChild(cell);
		});

		tbody.appendChild(row);
	});

	table.appendChild(tbody);

	// Append the table to the container or document body
	$("#modalContent").html(table);
	showProductsModal();
}

function showProductsModal() {
	// Show the modal
	$('#productsModal').modal('show');
}


 // Get the current date
var currentDate = new Date();

// Format the date as YYYY-MM-DD
var formattedDate = currentDate.toISOString().split("T")[0];

// Set the max attribute to the date with one day added
document.getElementById("toDate").setAttribute("max", formattedDate);
document.getElementById("fromDate").setAttribute("max", formattedDate);


   document.getElementById("toDate").setAttribute("value", formattedDate);

