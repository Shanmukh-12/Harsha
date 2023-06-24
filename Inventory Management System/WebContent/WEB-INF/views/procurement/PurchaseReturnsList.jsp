<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://unpkg.com/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>




<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
    
    .PurchasesClass
	{
	position:relative;
	left:500px;
	 font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
	}
	.purchaseClass1
         {
         position:relative;
    	right:200px;
         top:60px;
	     font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
         }
         .purchaseClass2
         {
          position:relative;
         left:490px;
         top:10px;
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
       .flex-container {
    display: flex;
    justify-content: center; /* Center the h1 element */
    align-items: center;
  }
  .searchClass
  {
  position:relative;
  z-index: 1;
 left:920px;
 top:55px;
  }
  
   

        .issues-block {
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 10px;
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
            margin-right: 5px;
            
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
        
        .refresh-icon {
  display: inline-block;
  cursor: pointer;
}
        


.bold {
            font-weight: bold;
            font-size: 25px;
        }
    
        

    </style>
    
         
    
    
</head>
<body>

		
	<label class="purchaseClass2">Vendor Id</label>
	<select id="vendorId" class="purchaseClass2" >
	<option value="">Select Vendor</option>
		<option>20001</option>
		<option>20002</option>
	</select>
	<label class="purchaseClass2">Grn value</label>
	<select id="prnvalue" class="purchaseClass2" >
		<option value="">Select Grn Value</option>
	
		<option>4000</option>
   </select>
   
	<label class="purchaseClass1">Return Date From</label>
	<input type="date" id="returnDate" class="purchaseClass1">
		<label class="purchaseClass1">Return Date To</label>
	<input type="date" id="returnDate1" class="purchaseClass1">
<input type="button" value="filter" onclick="tk()" class="purchaseClass1" style="color: white; background-color: green;">
	      <input type="button" value="clear" onclick="clearSelection()" class="purchaseClass1" style="color: white; background-color: green;">

	<br><br><br><br>
	<input type="text" id="purchasereturnid" placeholder="Enter purch ret id" class="searchClass" style="width:140px;">
	<input type="button" onclick="tk3()" value="search" class="searchClass">
	<span class="refresh-icon searchClass" onclick="tk()">&#x21bb;</span>
	
	
	
<form method="Get">

    <div class="container" >
    
        <h1 class="text-center mb-4" align="center">Purchase Returns Data</h1>
        
            <div class="issues-block">
                <h4 class="store-indent-id" id="PurchasesReturnId">Purchase Return ID: <span class="bold">5001</span></h4>
                <div class="issue-details" >
                    <span class="label">Vendor ID:</span><span >A2345</span>
                        <span class="label">Grn ID:</span><span >20001</span>
                         <span class="label">Purchase Return Date:</span><span >2023-12-29</span>
                        
                        <span class="label ">Grn Cost:</span> <span >123EW</span>
                        <span class="label ">Reason:</span> <span >123EW</span>
                </div>
                
                
                <div>
                <button type="button" value="view products" class="btn-issues" onclick="tk2(5001)">View Products</button>
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
		          var option = $('<option>').val(vendor.vendorId).text(vendor.vendorId +"(" + vendor.vendorName+")");
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
    	 var vendorIdf=$("#vendorId option:selected").val();
   	  var vendorId=parseInt(vendorIdf || 0);
   	var prnvaluef=$("#prnvalue option:selected").val();
   	var prnvalue=parseInt(prnvaluef || 0);
   	console.log(vendorId);

      
    	var returnDate=$("#returnDate").val();
    	var returnDate1=$("#returnDate1").val();



    	$.ajax({
    		            
    	                 url: "getPurchaseReturnsListDetails",
    	                 method:"GET",
    	                	    data: {
    	                	    	"purchase_return_date":returnDate,
    	                	    	"purchase_return_date1":returnDate1,
    	                	    	"vendor_id":vendorId,
    	                	    	"grn_value":prnvalue
    	                	    	
    	                	    },
    	                 success: function(response) {
    	                	 console.log(this.url);
    	                	 console.log(response);
    	                	 mapPurchaseReturnsData(response);
    	                	
    	                	 
    	                    
    	                 }
    	             });



    	};
    	});
  function clearSelection() {
	  $('#vendorId').val('');
	  $('#prnvalue').val('');
	  $('#returnDate').val('');
	  $('#returnDate1').val('');
	  tk();
	}

  function tk() {
 	 var vendorIdf=$("#vendorId").val();
	  var vendorId=parseInt(vendorIdf || 0);
	var prnvaluef=$("#prnvalue").val();
	var prnvalue=parseInt(prnvaluef || 0);
	console.log(vendorId);

   
 	var returnDate=$("#returnDate").val();
 	var returnDate1=$("#returnDate1").val();



 	$.ajax({
 		            
 	                 url: "getPurchaseReturnsListDetails",
 	                 method:"GET",
 	                	    data: {
 	                	    	"purchase_return_date":returnDate,
 	                	    	"purchase_return_date1":returnDate1,
 	                	    	"vendor_id":vendorId,
 	                	    	"grn_value":prnvalue
 	                	    	
 	                	    },
 	                 success: function(response) {
 	                	 console.log(this.url);
 	                	 console.log(response);
 	                	 mapPurchaseReturnsData(response);
 	                	
 	                	 
 	                    
 	                 }
 	             });



 	};
 	function mapPurchaseReturnData2(data) {
 		  var container = $(".container");

 		  // Clear existing data
 		  container.empty();
 		 var flexContainer = $('<div class="flex-container"></div>');

 	    // Append the h1 element, input field, and button to the flex container
 	    var h1Element = $('<h1 class="text-center" align="center">Purchase Returns Data</h1>');
 	    flexContainer.append(h1Element)
         container.append(flexContainer);
 		  // Create the main container div
 		  var mainDiv = $('<div class="issues-block"></div>');

 		  // Create the Purchase Return ID heading
 		  var purchaseReturnIdHeading = $('<h4 class="store-indent-id" id="PurchasesReturnId">Purchase Return ID: <span class="bold">' + data.purchase_return_id + '</span></h4>');

 		  // Create the issue details div
 		  var issueDetailsDiv = $('<div class="issue-details"></div>');

 		  // Create and append the span elements for the details
 		  var vendorIdSpan = $('<span class="label">Vendor ID:</span><span>' + data.vendor_id + '</span>');
 		  var grnIdSpan = $('<span class="label">Grn ID:</span><span>' + data.grn_no + '</span>');
 		  var returnDateSpan = $('<span class="label">Purchase Return Date:</span><span>' + data.purchase_return_date + '</span>');
 		  var grnCostSpan = $('<span class="label">Grn Cost:</span><span>' + data.grn_cost + '</span>');
 		  var reasonSpan = $('<span class="label">Reason:</span><span>' + data.purchase_return_description + '</span>');

 		  // Append the detail span elements to the issue details div
 		  issueDetailsDiv.append(vendorIdSpan, grnIdSpan, returnDateSpan, grnCostSpan, reasonSpan);

 		  // Create the "View Products" button
 		  var viewProductsButton = $('<button type="button" value="view products" class="btn-issues" onclick="tk2(' + data.purchase_return_id + ')">View Products</button>');

 		  // Append all the elements to the main container div
 		  mainDiv.append(purchaseReturnIdHeading, issueDetailsDiv, viewProductsButton);

 		  // Append the main container div to the overall container
 		  container.append(mainDiv);
 		}


 	function tk3()
 	{
 		var pid=$("#purchasereturnid").val();
 		 $.ajax({
 		      
 		      url: "getPurchaseReturnsListDetailsById",
 		      method:"GET",
 		     	    data: {
 		     	    	"purchase_return_id":pid,
 		     	    	
 		     	    },
 		      success: function(response) {
 		      	console.log(this.url);
 		      	console.log("hi");
 		      	console.log(response);
 		      	mapPurchaseReturnData2(response);
 		      	
 		      }
 		  })
 		     
 		
 	}
 	
  function mapPurchaseReturnsData(data) {
	    var container = $(".container");
	  
	    // Clear existing data
	    container.empty();
	    var flexContainer = $('<div class="flex-container"></div>');

	    // Append the h1 element, input field, and button to the flex container
	    var h1Element = $('<h1 class="text-center" align="center">Purchase Returns Data</h1>');
	    flexContainer.append(h1Element)
        container.append(flexContainer);

	    // Iterate over each purchase return
	    $.each(data, function(index, purchaseReturn) {
	        var purchaseReturnId = purchaseReturn.purchase_return_id;
	        var purchaseReturnDate = new Date(purchaseReturn.purchase_return_date).toLocaleDateString();
	        var grnNo = purchaseReturn.grn_no;
	        var grnCost = purchaseReturn.grn_cost;
	        var vendorId = purchaseReturn.vendor_id;
	        var purchaseReturnDescription = purchaseReturn.purchase_return_description;

	        // Create the main div element for each purchase return
	        var issuesBlockDiv = $("<div>").addClass("issues-block");

	        // Create the HTML elements for the purchase return details
	        var purchaseReturnIdHeader = $("<h4>")
	            .addClass("store-indent-id")
	            .attr("id", "PurchasesReturnId")
	            .text("Purchase Return ID: " + purchaseReturnId);

	        var issueDetailsDiv = $("<div>").addClass("issue-details");
	        var vendorIdSpan = $("<span>").addClass("label").text("Vendor ID:");
	        var vendorIdValue = $("<span>").text(vendorId);
	        var grnNoSpan = $("<span>").addClass("label").text("GRN ID:");
	        var grnNoValue = $("<span>").text(grnNo);
	        var purchaseReturnDateSpan = $("<span>").addClass("label").text("Purchase Return Date:");
	        var purchaseReturnDateValue = $("<span>").text(purchaseReturnDate);
	        var grnCostSpan = $("<span>").addClass("label").text("GRN Cost:");
	        var grnCostValue = $("<span>").text(grnCost);
	        var reasonSpan = $("<span>").addClass("label").text("Reason:");
	        var reasonValue = $("<span>").text(purchaseReturnDescription);

	        // Append the purchase return details to the issueDetailsDiv
	        issueDetailsDiv.append(
	            vendorIdSpan,
	            vendorIdValue,
	            grnNoSpan,
	            grnNoValue,
	            purchaseReturnDateSpan,
	            purchaseReturnDateValue,
	            grnCostSpan,
	            grnCostValue,
	            reasonSpan,
	            reasonValue
	        );

	        // Create the button for viewing products
	        var button = $("<button>")
	            .attr("type", "button")
	            .addClass("btn-issues")
	            .text("View Products")
	            .click(function() {
	                tk2(purchaseReturnId);
	            });

	        // Append the elements to the issuesBlockDiv
	        issuesBlockDiv.append(purchaseReturnIdHeader, issueDetailsDiv, button);

	        // Append the issuesBlockDiv to the container
	        container.append(issuesBlockDiv);
	    });
	}

  

  function tk2(prnId)
  {
  	
  $.ajax({
      
      url: "getPurchaseReturnProducts",
      method:"GET",
     	    data: {
     	    	"purchase_return_id":prnId,
     	    	
     	    },
      success: function(response) {
      	console.log(this.url);
      	console.log("hi");
      	console.log(response);
      	createTable(response);
      	
      }
  })
     
          
  }
  function createTable(jsonData) {
	  var table = $("<table>")
	    .addClass("table bg-white rounded shadow-sm table-hover")
	    .attr("id", "dataTable");
	  var thead = $("<thead>");
	  var tbody = $("<tbody>").attr("id", "dataBody");

	  // Create table headers
	  var headers = [
	    "#",
	    "GRN Id",
	    "Product Id",
	    "Quantity",
	    "Cost",
	    "Purchase Return Description",
	  ];
	  var headerRow = $("<tr>");
	  $.each(headers, function(index, header) {
	    var th = $("<th>").text(header);
	    headerRow.append(th);
	  });
	  thead.append(headerRow);

	  // Create table rows from JSON data
	  $.each(jsonData, function(index, data) {
	    var pr = data.pr;
	    var prp = data.prp;
	    var purchaseReturnId = pr.purchase_return_id;
	    var grnId = pr.grn_no;
	    var productId = prp.product_id;
	    var quantity = prp.quantity;
	    var cost = pr.grn_cost.toFixed(2);
	    var purchaseReturnDescription = pr.purchase_return_description;

	    var row = $("<tr>");
	    var indexCell = $("<td>").text(index + 1);
	    var grnIdCell = $("<td>").text(grnId);
	    var productIdCell = $("<td>").text(productId);
	    var quantityCell = $("<td>").text(quantity);
	    var costCell = $("<td>").text(cost);
	    var descriptionCell = $("<td>").text(purchaseReturnDescription);

	    row.append(
	      indexCell,
	      grnIdCell,
	      productIdCell,
	      quantityCell,
	      costCell,
	      descriptionCell
	    );
	    tbody.append(row);
	  });

	  table.append(thead, tbody);

	  // Append the table to the modal
	  $("#modalContent").empty().append(table);
	  showProductsModal();
	}


    function showProductsModal() {
        // Show the modal
        $('#productsModal').modal('show');
    }
     
</script>
</html>