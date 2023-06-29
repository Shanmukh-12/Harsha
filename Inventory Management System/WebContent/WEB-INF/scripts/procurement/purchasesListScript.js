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
$(document).ready(function() {
	tk();

	function tk() {

		var vendorIdf = $("#vendorId option:selected").val();
		var vendorId = parseInt(vendorIdf || 0);
		var expectedDate = $("#expectedDate").val();
		var expectedDate1 = $("#expectedDate1").val();
		console.log(expectedDate1)

		console.log(vendorId);
		console.log(expectedDate);
		$.ajax({
			url: "getPurchaseIdDetails",
			method: "GET",

			data: {
				"vendor_id": vendorId,
				"purchase_order_expected_date": expectedDate,
				"purchase_order_expected_date1": expectedDate1

			},


			success: function(response) {

				console.log(this.url);
				console.log(response);
				var PurchasesId = $("#PurchasesId");
				setPurchaseOrderData(response);



			},
			error: function() {
				console.log(this.url);
				console.log("AJAX call error");
			}



		});


	};
});
function tk() {

	var vendorIdf = $("#vendorId option:selected").val();
	var vendorId = parseInt(vendorIdf || 0);
	var expectedDate = $("#expectedDate").val();
	var expectedDate1 = $("#expectedDate1").val();
	console.log(expectedDate1)

	console.log(vendorId);
	console.log(expectedDate);
	$.ajax({
		url: "getPurchaseIdDetails",
		method: "GET",

		data: {
			"vendor_id": vendorId,
			"purchase_order_expected_date": expectedDate,
			"purchase_order_expected_date1": expectedDate1

		},


		success: function(response) {

			console.log(this.url);
			console.log(response);
			var PurchasesId = $("#PurchasesId");
			setPurchaseOrderData(response);



		},
		error: function() {
			console.log(this.url);
			console.log("AJAX call error");
		}



	});


};
function mapPurchaseOrderData2(data) {
	var container = $(".container");

	// Clear existing data
	container.empty();
	var h1Element = $('<h1 class="text-center mb-4" align="center">Purchases Data</h1>');
	$('.container').append(h1Element);

	// Create the main container div
	var mainDiv = $('<div class="issues-block"></div>');

	// Create the Purchase ID heading
	var purchaseIdHeading = $('<h4 class="store-indent-id" id="PurchasesId">Purchase ID: <span class="bold">' + data.purchase_order_id + '</span></h4>');

	// Create the issue details div
	var issueDetailsDiv = $('<div class="issue-details"></div>');

	// Create and append the span elements for the details
	var vendorIdSpan = $('<span class="label">Vendor ID:</span><span>' + data.vendor_id + '</span>');
	var expectedDateSpan = $('<span class="label">Purchase Order Expected Date:</span><span>' + data.purchase_order_expected_date + '</span>');
	var amountSpan = $('<span class="label">Purchase Order amount:</span><span>' + data.purchase_order_amount + '</span>');

	// Append the detail span elements to the issue details div
	issueDetailsDiv.append(vendorIdSpan, expectedDateSpan, amountSpan);

	// Create the "View Products" button
	var viewProductsButton = $('<button type="button" value="view products" class="btn-issues" onclick="tk2(' + data.purchase_order_id + ')">View Products</button>');

	// Append all the elements to the main container div
	mainDiv.append(purchaseIdHeading, issueDetailsDiv, viewProductsButton);

	// Append the main container div to the overall container
	container.append(mainDiv);
}

function tk3() {


	var pid = $("#purchaseorderid").val();

	console.log(pid)
	$.ajax({
		url: "getPurchaseIdDetailsById",
		method: "GET",

		data: {

			"purchase_order_id": pid
		},


		success: function(response) {

			console.log(this.url);
			console.log(response);
			mapPurchaseOrderData2(response);


		}




		,
		error: function() {
			console.log(this.url);
			console.log("AJAX call error");
		}



	});


};
function refresh() {
	console.log("hii babu");
	$('#vendorId').val('');
	$('#expectedDate').val('');
	$('#expectedDate1').val('');
	$("#purchaseorderid").val('');

	tk();
}

function setPurchaseOrderData(data) {
	var container = $(".container");

	// Clear existing data
	container.empty();
	var h1Element = $('<h1 class="text-center mb-4" align="center">Purchases Data</h1>');
	$('.container').append(h1Element);

	// Iterate over each purchase order
	$.each(data, function(index, order) {
		var purchaseId = order.purchase_order_id;
		var vendorId = order.vendor_id;
		var expectedDate = order.purchase_order_expected_date;
		var amount = order.purchase_order_amount;

		// Create the issue block
		var issueBlock = $("<div>").addClass("issues-block");

		var purchaseIdHeader = $("<h4>")
			.addClass("store-indent-id")
			.attr("id", "PurchasesId")
			.text("Purchase ID: ");
		var purchaseIdSpan = $("<span>")
			.addClass("bold")
			.text(purchaseId);
		purchaseIdHeader.append(purchaseIdSpan);
		issueBlock.append(purchaseIdHeader);

		var detailsDiv = $("<div>").addClass("issue-details");
		var vendorIdSpan = $("<span>")
			.addClass("label")
			.text("Vendor ID: ");
		var vendorIdValue = $("<span>").text(vendorId);
		var expectedDateSpan = $("<span>")
			.addClass("label")
			.text("Purchase Order Expected Date: ");
		var expectedDateValue = $("<span>").text(expectedDate);
		var amountSpan = $("<span>")
			.addClass("label")
			.text("Purchase Order Amount: ");
		var amountValue = $("<span>").text(amount);
		detailsDiv.append(
			vendorIdSpan,
			vendorIdValue,
			expectedDateSpan,
			expectedDateValue,
			amountSpan,
			amountValue
		);
		issueBlock.append(detailsDiv);

		var button = $("<button>")
			.attr("type", "button")
			.addClass("btn-issues")
			.text("View Products")
			.click(function() {
				tk2(purchaseId);
			});
		issueBlock.append(button);

		// Append the issue block to the container
		container.append(issueBlock);
	});
}

function clearSelection() {
	$('#vendorId').val('');
	$('#expectedDate').val('');
	$('#expectedDate1').val('');
	tk();
}
function tk2(PurchasesId) {


	console.log(vendorId);
	console.log(expectedDate);
	$.ajax({
		url: "getPurchaseProducts",
		method: "GET",

		data: {

			"purchase_order_id": PurchasesId
		},


		success: function(response) {

			console.log(this.url);
			console.log(response);

			createProductsTable(response);


		}




		,
		error: function() {
			console.log(this.url);
			console.log("AJAX call error");
		}



	});


};
function createProductsTable(data) {
	var table = $("<table>")
		.addClass("table bg-white rounded shadow-sm table-hover")
		.append(
			$("<thead>").append(
				$("<tr>").append(
					$("<th>").text("product_id"),
					$("<th>").text("purchase_order_quantity"),
					$("<th>").text("purchase_order_date"),
					$("<th>").text("Vendor ID"),
					$("<th>").text("Expected Delivery"),
					$("<th>").text("Negotiation Price"),
					$("<th>").text("Status")
				)
			),
			$("<tbody>").attr("id", "purchaseBody")
		);

	// Iterate over each purchase order
	$.each(data, function(index, order) {
		var po = order.po;
		var product = order.pop;

		var row = $("<tr>").append(
			$("<td>").text(product.product_id),
			$("<td>").text(product.purchase_order_quantity),
			$("<td>").text(po.purchase_order_date),
			$("<td>").text(po.vendor_id),
			$("<td>").text(po.purchase_order_expected_date),
			$("<td>").text(product.negotiation_price),
			$("<td>").text(po.purchase_order_status)
		);

		// Append the row to the table body
		table.find("tbody").append(row);
	});

	// Append the table to the modal content
	$("#modalContent").html(table);
	showProductsModal();
}

function showProductsModal() {
	// Show the modal
	$('#productsModal').modal('show');
}