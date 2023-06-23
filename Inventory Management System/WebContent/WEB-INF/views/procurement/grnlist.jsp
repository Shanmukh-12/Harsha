<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://unpkg.com/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <style>
         .grnClass1
         {
         position:relative;
         right:290px;	
         top:50px;
         font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
         }
         .grnClass2
         {
         position:relative;
         left:440px;
         font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
         }
         .container {
         position:relative;
         left:130px;
         max-width: 800px;
         margin: 100 auto;
         padding-top: 10px;
         margin-left:170px;
         }
         .issues-block {
         border: 1px solid #ccc;
         border-radius: 10px;
         padding: 20px;
         margin-bottom: 20px;
         background-color: #fff;
         }
         .issues-block:hover {
         box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
         background-color:#d8f2f0 ;
         }
         .issue-details{
         display: flex;
         justify-content: space-between;
         align-items: center;
         margin-bottom: 10px;
         }
         .label {
         font-weight: bold;
         }
         .btn-issues{
         border-radius:6px;
         background-color: #4CAF50;
         border: none;
         color: white;
         cursor: pointer;
         font-weight: bold;
         box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
         margin-left: 600px;
         }
         .btn-issues:hover {
         background-color:#4CAF50 ;
         border-color: #4CAF50;
         }
         .bold {
         font-weight: bold;
         font-size: 25px;
         }
         .searchClass
         {
         position:relative;
         left:740px;
         top:55px;
         }
         .refresh-icon {
         display: inline-block;
         cursor: pointer;
         }
         .data-table {
  width: 100%;
  border-collapse: collapse;
}

.data-table th,
.data-table td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.data-table th {
  font-weight: bold;
}

.data-table tbody tr:nth-child(even) {
  background-color: #f2f2f2;
}
         
      </style>
   </head>
   <body>
      <label class="grnClass2">Select VendorID:</label>
      <select id="vendorId" class="grnClass2"  >
         <option value="">Select Vendor</option>
         <option>20001</option>
         <option>20002</option>
         <option>20003</option>
      </select>
      <label class="grnClass2">GRN Cost</label>
      <select id="cost" class="grnClass2"  >
         <option value="">Select Cost</option>
         <option>2000000</option>
         <option>2000001</option>
         <option>2000002</option>
      </select>
      <label class="grnClass1">Select Order Received From Date:</label>
      <input type="date" id="fromDate" class="grnClass1">
      <label class="grnClass1">Select Order Received To Date:</label>
      <input type="date" id="toDate" class="grnClass1">
      <input type="button" value="filter" onclick="tk()" class="grnClass1" style="color: white; background-color: green;">
      <input type="button" value="clear" onclick="clearSelection()" class="grnClass1" style="color: white; background-color: green;">
      <br><br><br><br>
      <form method="Get">
         <div class="container" >
            <h1 class="text-center mb-4">GRN's Data</h1>
            <div class="issues-block">
               <h4 class="store-indent-id" id="grnid">GRN ID: <span class="bold">5001</span></h4>
               <div class="issue-details" >
                  <span class="label">Vendor ID:</span><span >A2345</span>
                  <span class="label">Purchase Order ID:</span><span >5001</span>
                  <span class="label">GRN Received Date:</span><span >2023-12-29</span>
                  <span class="label ">GRN Cost</span> <span >123EW</span>
               </div>
               <div>
                  <button type="button" value="view products" class="btn-issues" onclick="">View Products</button>
               </div>
            </div>
         </div>
      </form>
      <!-- Modal -->
      <div class="modal fade" id="productsModal" tabindex="-1" aria-labelledby="productsModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered modal-lg" style="max-width: calc(100% - 200px);">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="productsModalLabel">Products</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body" id="modalContent">
                  <!-- Modal content will be loaded dynamically here -->
               </div>
            </div>
         </div>
      </div>
   </body>
   <script>
      
				$(document).ready(function() {
					tk();
					
				});
				
				function tk() {

					var vendorIdf = $("#vendorId option:selected").val();
					var vendorId = parseInt(vendorIdf || 0);
					var costidf = $("#cost option:selected").val();
					var cost = parseInt(costidf || 0);
					var fromDate = $("#fromDate").val();
					var toDate = $("#toDate").val();
					console.log(fromDate)

					console.log(vendorId);
					console.log(toDate);
					$.ajax({
						url : "getGrnList",
						method : "GET",

						data : {
							"vendor_id" : vendorId,
							"grn_amount" : cost,
							"grnFromDate" : fromDate,
							"grnToDate" : toDate

						},

						success : function(response) {

							console.log(this.url);
							console.log(response);
							var PurchasesId = $("#PurchasesId");
							appendGRNDataToContainer(response);
							appendVendorIdsToSelect(response);

						},
						error : function() {
							console.log(this.url);
							console.log("AJAX call error");
						}

					});

				}
				//########
				function appendVendorIdsToSelect(jsonData) {
					  var selectTag = document.getElementById("vendorId");

					  for (var i = 0; i < jsonData.length; i++) {
					    var vendorId = jsonData[i].vendor_id;
					    var option = document.createElement("option");
					    option.value = vendorId;
					    option.text = vendorId;
					    selectTag.appendChild(option);
					  }
					}

				
				
				function mapPurchaseOrderData2(data) {
					var container = $(".container");

					// Clear existing data
					container.empty();
					var h1Element = $('<h1 class="text-center mb-4" align="center">Purchases Data</h1>');
					$('.container').append(h1Element);

					// Create the main container div
					var mainDiv = $('<div class="issues-block"></div>');

					// Create the Purchase ID heading
					var purchaseIdHeading = $('<h4 class="store-indent-id" id="PurchasesId">Purchase ID: <span class="bold">'
							+ data.purchase_order_id + '</span></h4>');

					// Create the issue details div
					var issueDetailsDiv = $('<div class="issue-details"></div>');

					// Create and append the span elements for the details
					var vendorIdSpan = $('<span class="label">Vendor ID:</span><span>'
							+ data.vendor_id + '</span>');
					var expectedDateSpan = $('<span class="label">Purchase Order Expected Date:</span><span>'
							+ data.purchase_order_expected_date + '</span>');
					var amountSpan = $('<span class="label">Purchase Order amount:</span><span>'
							+ data.purchase_order_amount + '</span>');

					// Append the detail span elements to the issue details div
					issueDetailsDiv.append(vendorIdSpan, expectedDateSpan,
							amountSpan);

					// Create the "View Products" button
					var viewProductsButton = $('<button type="button" value="view products" class="btn-issues" onclick="tk2('
							+ data.purchase_order_id
							+ ')">View Products</button>');

					// Append all the elements to the main container div
					mainDiv.append(purchaseIdHeading, issueDetailsDiv,
							viewProductsButton);

					// Append the main container div to the overall container
					container.append(mainDiv);
				}
				

				function tk3() {

					var pid = $("#grnid").val();

					console.log(pid)
					$.ajax({
						url : "getPurchaseId3",
						method : "GET",

						data : {

							"purchase_order_id" : pid
						},

						success : function(response) {

							console.log(this.url);
							console.log(response);
							mapJSONToHTML(response);

						}

						,
						error : function() {
							console.log(this.url);
							console.log("AJAX call error");
						}

					});

				};
				function refresh() {
					tk();
				}
				function appendGRNDataToContainer(data) {
					  const container = document.querySelector('.container');
					  $(".container").empty();

					  var h1Element = $('<h1 class="text-center mb-4" align="center">GRN Data</h1>');
					  $('.container').append(h1Element);

					  // Clear existing data
					  data.forEach((item) => {
					    const grnId = item.grnId;
					    const vendorId = item.vendor_id;
					    const purchaseOrderId = item.purchase_order_id;
					    const grnDate = new Date(...item.grnDate).toISOString().split('T')[0];
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
					      tk2(item.grnId);
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
					 $("#vendorId").val("");
					    $("#cost").val("");
					    $("#fromDate").val("");
					    $("#toDate").val("");
					
				}
				function tk2(grnId) {

					console.log(vendorId);
					
					$.ajax({
						url : "getGrnProducts",
						method : "GET",

						data : {

							"grnId" : grnId
						},

						success : function(response) {

							console.log(this.url);
							console.log(response);
							createTable(response);

						}

						,
						error : function() {
							console.log(this.url);
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
				
</script>
</html>