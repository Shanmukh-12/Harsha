$(document).ready(function() {
	$("#vendorId").click(tk);
	$("#PurchasesId").click(tk2);
	$("#expectedDate").change(tk);
	function tk() {

		var vendorId = $("#vendorId").val();
		var expectedDate = $("#expectedDate").val();

		console.log(vendorId);
		console.log(expectedDate);
		$.ajax({
			url: "getPurchaseId",
			method: "GET",

			data: {
				"vendor_id": vendorId,
				"purchase_order_expected_date": expectedDate
			},


			success: function(response) {

				console.log(this.url);
				console.log(response);
				var PurchasesId = $("#PurchasesId");
				PurchasesId.empty();
				response.forEach(function(optionval) {
					var newop = $("<option></option>");
					newop.val(optionval.purchase_order_id);
					newop.text(optionval.purchase_order_id);
					PurchasesId.append(newop);

				}

				)



			},
			error: function() {
				console.log(this.url);
				console.log("AJAX call error");
			}



		});


	};
});
function tk2() {

	var PurchasesId = $("#PurchasesId").val();

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



			var table = document.getElementById("purchaseBody");
			$("#purchaseBody").html("");
			response.forEach(function(item) {
				var purchaseOrder = item.po;
				var pop = item.pop;
				var row = document.createElement("tr");
				var productidCell = document.createElement("td");
				productidCell.textContent = pop.product_id;
				row.appendChild(productidCell);

				var purchaseQuantityCell = document.createElement("td");
				purchaseQuantityCell.textContent = pop.purchase_order_quantity;
				row.appendChild(purchaseQuantityCell);

				var purchaseDateCell = document.createElement("td");
				purchaseDateCell.textContent = purchaseOrder.purchase_order_date;
				row.appendChild(purchaseDateCell);

				var vendorIdCell = document.createElement("td");
				vendorIdCell.textContent = purchaseOrder.vendor_id;
				row.appendChild(vendorIdCell);






				var expectedDeliveryCell = document.createElement("td");
				expectedDeliveryCell.textContent = purchaseOrder.purchase_order_expected_date;
				row.appendChild(expectedDeliveryCell);
				var negotiationCell = document.createElement("td");
				negotiationCell.textContent = pop.negotiation_price;
				row.appendChild(negotiationCell);


				var statusCell = document.createElement("td");
				statusCell.textContent = purchaseOrder.purchase_order_status;
				row.appendChild(statusCell);

				table.appendChild(row);
			});




		},
		error: function() {
			console.log(this.url);
			console.log("AJAX call error");
		}



	});


};