<style>
   table {
   border-collapse: collapse;
   width: 100%;
   }
   table, th, td {
   border: 1px solid black;
   padding: 5px;
   }
   /* Styling for Add button in the purchasedItemsTable */
   #purchasedItemsTable button {
   background-color: #4CAF50;
   color: white;
   border: none;
   padding: 5px 10px;
   border-radius: 3px;
   cursor: pointer;
   }
   .filters {
   display: flex;
   justify-content: space-evenly;
   }
   .purchaseClass1 {
   position: relative;
   right: 100px;
   top: 50px;
   font-size: 18px;
   font-weight: bold;
   color: #333;
   text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
   }
   .purchaseClass2 {
   position: relative;
   left: 440px;
   font-size: 18px;
   font-weight: bold;
   color: #333;
   text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
   }
   /* Styling for Delete button in the grnListTable */
   #grnListTable button {
   background-color: #FF0000;
   color: white;
   border: none;
   padding: 5px 10px;
   border-radius: 3px;
   cursor: pointer;
   }
   /* Styling for createGRN button */
   #createGRNButton {
   background-color: #2ECC71;
   color: white;
   border: none;
   padding: 10px 20px;
   border-radius: 5px;
   font-size: 16px;
   cursor: pointer;
   margin: 0 auto;
   display: block;
   }
   /* Center align the button container */
   .button-container {
   text-align: center;
   }
   /* Center align the table headings */
   th {
   text-align: center;
   }
   #bonusId, #currentReceivedId, #cgst, #sgst, #igst {
   width: 56px;
   }
   #totalprice, #batchno, #mrp, #product_id {
   width: 80px;
   }
   #grnListTable {
   margin-left: 200px;
   width: 900px;
   }
   #purchasedItemsTable {
   margin-left: 200px;
   width: 900px;
   }
</style>
<script>
   $(document).ready(function() {
   	tk();
   	$("#purchaseId").click(tk2);
       $("#vendorId").on('click',function() {
           $.ajax({
               url: "getGrnList",
               method: "POST",
               success: function(data) {
                   console.log(data);
   
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
      
        for (var i = 0; i < row.cells.length+8; i++) {
          var newCell = newRow.insertCell(i);
          if (i === row.cells.length + 7) {
            newCell.innerHTML = '<button onclick="deleteFromGRNList(this)">Delete</button>';
          }else if (i === 10) {
            newCell.innerHTML = '<input id="bonusId" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';     
      }
          else if (i === 4) {
              newCell.innerHTML = '<input id="cgst" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">'; 
        }
          else if (i === 5) {
              newCell.innerHTML = '<input id="sgst" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">'; 
        }
          else if (i === 6) {
              newCell.innerHTML = '<input id="igst" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">'; 
        
            
        }
          else if (i === 7) {
              newCell.innerHTML = '<input id="totalprice" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">'; 
        
            
        }
   	 else if (i === 11) {
              newCell.innerHTML = '<input id="mrp" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">'; 
        
            
        }
          
          else if (i === 8 ) {
            newCell.innerHTML = '<input id="batchno" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';
      
          
      } else if (i === 9) {
            newCell.innerHTML = '<input id="currentReceivedId" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';
      }else {
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
      
      function createGRN()
      {
	      var data = getTableData();
	      const jsonData = JSON.stringify(data);
	      console.log(jsonData);
	      $.ajax({
		      url:"makeGrn",
		      method:"post",
		      data:{"jsonData":jsonData},
		      success:function(page)
		      {
		      	console.log("Success");
		      }
		      
	      });
      }
         
         function tk() {
             
       	  	var vendorIdf=$("#vendorId option:selected").val();
       	   	  var vendorId=parseInt(vendorIdf || 0);
       	    var expectedDate = $("#expectedDate").val();
       	    var expectedDate1 = $("#expectedDate1").val();
       	    console.log(expectedDate1)
       	   
       	    console.log(vendorId);
       	    console.log(expectedDate);
       	    $.ajax({
       	        url: "getPurchaseId2",
       	        method:"GET",
       	        
       	        data: {
       	          "vendor_id": vendorId,
       	          "purchase_order_expected_date": expectedDate,
       	          "purchase_order_expected_date1": expectedDate1
   
       	        },
       	       	
       	       	  
       	        success: function(response) {
       	      	  
       	      	  console.log(this.url);
       	      	  console.log(response);
       	      	populatePurchaseOrderIds(response);
       	      	  
       	           
       	        },
       	    error: function () {
       	  	  console.log(this.url);
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
       	  function tk2() {
       		  var purchaseIdSelect = document.getElementById("purchaseId");
       		  var PurchasesId = purchaseIdSelect.value;
       		  console.log("Purchase Id is "+PurchasesId);
   
       		     
       	      console.log(vendorId);
       	      console.log(expectedDate);
       	      $.ajax({
       	          url: "getPurchaseProducts",
       	          method:"GET",
       	          
       	          data: {
       	           
       	            "purchase_order_id":PurchasesId
       	          },
       	         	
       	         	  
       	          success: function(response) {
       	        	  
       	        	  console.log(this.url);
       	        	  console.log(response);
       	        	      
       	        	  populatePurchasedItemsTable(response);
       	                 
       	                  }
       	              
       	        	  
       	        	  
       	             
       	          ,
       	      error: function () {
       	    	  console.log(this.url);
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
       	    	    var orderedQuantity = po.children.find(function (child) {
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
       	    	    addButton.onclick = function () {
       	    	      addToGRNList(this);
       	    	    };
       	    	    actionCell.appendChild(addButton);
       	    	    row.appendChild(actionCell);
   
       	    	    tbody.appendChild(row);
       	    	  }
       	    	}
   
         
</script>
<!-- #f2f2f2 -->
<h2 style="font-size: 24px; margin-bottom: 10px;" align="center">Create
   GRN
</h2>
<br>
<label class="purchaseClass2">Select VendorID:</label>
<select id="vendorId" class="purchaseClass2">
   <option value="">Select Vendor</option>
   <option>20001</option>
   <option>20002</option>
   <option>20003</option>
</select>
<label class="purchaseClass1">Select Expected From Date:</label>
<input type="date" id="expectedDate" class="purchaseClass1">
<label class="purchaseClass1">Select Expected To Date:</label>
<input type="date" id="expectedDate1" class="purchaseClass1">
<input type="button" value="filter" onclick="tk()"
   class="purchaseClass1" style="color: white; background-color: green;">
<input type="button" value="clear" onclick="clearSelection()"
   class="purchaseClass1" style="color: white; background-color: green;">
<br>
<br>
<br>
<br>
<br>
<div style="display: flex; justify-content: center;">
   <div style="width: 45%">
      <label
         style="font-weight: bold; font-size: 16px; display: block; margin-bottom: 5px;">Purchase
      ID:</label> 
      <select id="purchaseId"
         style="padding: 10px; font-size: 14px; border-radius: 5px; border: 1px solid #ccc; width: 100%; margin-bottom: 10px;">
         <option value="5001">5001</option>
         <option value="5002">5002</option>
         <option value="5003">5003</option>
      </select>
   </div>
</div>
</div>
<br>
<br>
<h3 align="center">Purchased Items</h3>
<table id="purchasedItemsTable">
   <thead>
      <tr>
         <!--<th>S.No</th>-->
         <th>Product ID</th>
         <th>Ordered Quantity</th>
         <th>Received Quantity (till Now)</th>
         <th>Cost</th>
         <th>Action</th>
      </tr>
   </thead>
   <tbody>
      <!-- Data will be added dynamically -->
   </tbody>
</table>
<br>
<br>
<h3 align="center">GRN List</h3>
<table id="grnListTable">
   <thead>
      <tr>
         <th id="productId">Product ID</th>
         <th>Ordered Quantity</th>
         <th>Received Quantity (till Now)</th>
         <th>Cost</th>
         <th id="cgst">cgst</th>
         <th id="sgst">sgst</th>
         <th id="igst">igst</th>
         <th id="totalPrice">total price</th>
         <th id="batchNo">Batch No</th>
         <th id="quantity">current Received Quantity</th>
         <th id="bonus">bonus</th>
         <th id="mrp">MRP</th>
         <th>Action</th>
      </tr>
   </thead>
   <tbody>
   </tbody>
</table>
<br>
<br>
<div class="button-container">
   <button id="createGRNButton" onclick="createGRN()">Create GRN</button>
</div>