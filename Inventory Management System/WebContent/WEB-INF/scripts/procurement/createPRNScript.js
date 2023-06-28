  function clearSelection() {
		 $("#vendorId").val("");
		    $("#cost").val("");
		    $("#fromDate").val("");
		    $("#toDate").val("");
		
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
  function addRecordsToTable(records) {
	  var dropdown = document.getElementById("prnId");
	  var selectedOption2 = dropdown.options[dropdown.selectedIndex].text;
	  var extractedText = selectedOption2.match(/\(([^)]+)\)/)[1];
	  $("#vid").val(extractedText);
	
	  var tableBody = $("#myTable tbody");
	  tableBody.empty();
	  $.each(records, function(index, record) {
	    var productId = record.product_id;
	    var batchNo = record.batch_no;
	    var orderedQuantity = record.quantity;
	    var receivedQuantity = record.quantity; // Set initial received quantity to 0
	    var bonus = record.bonus;
	    var totalQuantity = record.totalQuantity;
	    var cost = record.price;

	    var row = $("<tr>");
	    row.append("<td>" + productId + "</td>");
	    row.append("<td>" + batchNo + "</td>");
	    row.append("<td>" + orderedQuantity + "</td>");
	    row.append("<td>" + receivedQuantity + "</td>");
	    row.append("<td>" + bonus + "</td>");
	    row.append("<td>" + totalQuantity + "</td>");
	    row.append("<td>" + cost + "</td>");

	    var addButton = $("<button>").text("Add").click(function() {
	      moveToTable2(this);
	    });

	    var actionCell = $("<td>").append(addButton);
	    row.append(actionCell);

	    tableBody.append(row);
	  });
	}




	$(document).ready(function() {
		tk();
		$("#prnId").change(tk2);
		 $("#prnId").val();
		function tk2() {
			var grnId=$("#prnId").val();

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
					addRecordsToTable(response);
					

				}

				,
				error : function() {
					console.log(this.url);
					console.log("AJAX call error");
				}

			});

		};

		
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
			dataType: "json",
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
				  var prnId = $("#prnId");
				  prnId.empty(); // Clear existing options
				  prnId.append($('<option>').text("Select GRNId").prop('disabled', true).prop('selected', true));
				 
				  $.each(response, function(index, grn) {
				    prnId.append('<option value="' + grn.grnId + '">' + grn.grnId +"("+ grn.vendor_id+")"+ '</option>');
				  });
				},

			
			error : function() {
				console.log(this.url);
				console.log("AJAX call error");
			}

		});

	}
	function ButtonAction(button) {
		console.log("hello");
	    var row = button.parentNode.parentNode;
	    row.parentNode.removeChild(row);
	  }
 function clear()
 {
	 document.getElementById('reason').value = '';
 }
   
  function moveToTable2(button) {
	  // Get the row of the clicked button
	  const row = button.parentNode.parentNode;

	  // Get the values from the row
	  const product_id = row.cells[0].textContent;
	  const batch = row.cells[1].textContent;
	  const orderedq = row.cells[2].textContent;
	  const receivedq = row.cells[3].textContent;
	  const bonus = row.cells[4].textContent;
	  const cost = row.cells[6].textContent;

     const quantityreceived=row.cells[5].textContent;
	  // Create a new row in table2 with the values
	  const newRow = document.createElement("tr");
	  const idCell = document.createElement("td");
	  const batchCell = document.createElement("td");
	  const OrderquantityCell = document.createElement("td");
	  const receivedquantityCell = document.createElement("td");
	  const bonusCell = document.createElement("td");
	  const costCell = document.createElement("td");

	  
	  const quantityCell = document.createElement("td");
	  const quantityreceivedCell = document.createElement("td");
	  const actionCell = document.createElement("td");
	  OrderquantityCell.textContent=orderedq;
	  receivedquantityCell.textContent=receivedq;
	  bonusCell.textContent=bonus;
	  idCell.textContent = product_id;
	  batchCell.textContent = batch;
	  costCell.textContent = cost;

	  quantityreceivedCell.textContent=quantityreceived;
	  newRow.appendChild(idCell);
	  newRow.appendChild(batchCell);
	  newRow.appendChild(OrderquantityCell);
	  newRow.appendChild(receivedquantityCell);
	  newRow.appendChild(bonusCell);
	  newRow.appendChild(quantityreceivedCell);

	  newRow.appendChild(costCell);

	  

	  // Add the negative price input field
	  const inputNegativePrice = document.createElement("input");
inputNegativePrice.type = "number";
inputNegativePrice.id = "quantity";
inputNegativePrice.style.width = "80px";
inputNegativePrice.style.height = "20px";
inputNegativePrice.setAttribute("required", "true");
inputNegativePrice.setAttribute("pattern", "^-?[1-9][0-9]*$|^0$");
inputNegativePrice.setAttribute("title", "Please enter a valid number greater than 0.");
inputNegativePrice.value = "1";

	  quantityCell.appendChild(inputNegativePrice);
	  newRow.appendChild(quantityCell);
	  



	  // Add the delete button
	  const deleteButton = document.createElement("button");
	  deleteButton.textContent = "Delete";
	  deleteButton.addEventListener("click", function () {
	    ButtonActionback(this);
	  });
	  actionCell.appendChild(deleteButton);
	  newRow.appendChild(actionCell);
	  

	  // Append the new row to table2
	  const table2Body = document.querySelector("#dataTable1");
	  table2Body.appendChild(newRow);

	  // Remove the row from table1
	  row.parentNode.removeChild(row);
	}
    function deleteRow(button) {
      var row = button.parentNode.parentNode;
      row.parentNode.removeChild(row);
    }
    
    function createpurchasereturn() {
    	 const table = document.getElementById('dataTable');
    	    const table1 = document.querySelector('.table');

    	    // Move all rows from dataTable back to table
    	   
    	  // Retrieve the table element
  const tableData = [];

  // Get the column headers
  const headers = [];
  const headerRow = table.rows[0];
  for (let i = 0; i < headerRow.cells.length - 1; i++) {
    const headerText = headerRow.cells[i].textContent.trim();
    if (
      headerText !== 'Action' &&
      headerText !== 'Bonus' &&
      headerText !== 'Recieved Quantity' &&
      headerText !== 'Total quantity' &&
      headerText !== 'Ordered Quantity' &&
      headerText !== 'Cost'
    ) {
      headers.push(headerText);
    }
  }
  console.log(headers);

  // Iterate through each row of the table
  for (let i = 1; i < table.rows.length; i++) {
    const row = table.rows[i];
    const rowData = {};
     
    // Iterate through each cell of the row
    let headerIndex = 0;
    for (let j = 0; j < row.cells.length - 1; j++) {
      const headerText = headerRow.cells[j].textContent.trim();
      if (
        headerText !== 'Action' &&
        headerText !== 'Bonus' &&
        headerText !== 'Received Quantity' &&
        headerText !== 'Total quantity' &&
        headerText !== 'Ordered Quantity' &&
        headerText !== 'Cost'
      ) {
        const cell = row.cells[j];

        // Check if the cell contains an input element
        if (cell.firstChild && cell.firstChild.tagName === 'INPUT') {
          // Assign the input field value
          rowData["quantity"] = cell.firstChild.value.trim();
        } else {
          // Assign the cell text content
          
            rowData[headers[headerIndex]] = cell.textContent.trim();
       
        }

        headerIndex++;
      }
    }

    // Add row data to the tableData array
    tableData.push(rowData);
  }
  console.log(tableData);
  const grn_no = document.getElementById("prnId");
  const selectedOption = grn_no.options[grn_no.selectedIndex].value;
  console.log(selectedOption);
  var dropdown = document.getElementById("prnId");
  var selectedOption2 = dropdown.options[dropdown.selectedIndex].text;
  var extractedText = selectedOption2.match(/\(([^)]+)\)/)[1];
  console.log(extractedText);
  var reasonInput = document.getElementById("reason");
  var reasonValue = reasonInput.value;
  console.log(reasonValue);

  var data={};
  data["grn_cost"]=4000;
  data["vendor_id"]=extractedText;
  data["grn_no"]=selectedOption;
  data["purchase_return_description"]=reasonValue;
  data["pi"]=tableData;
  data["purchaseReturnProducts"]=tableData;
  console.log(JSON.stringify(data));
  const jsonData = JSON.stringify(data);
  $.ajax({
      url: "makePurchaseReturn",
      method:"POST",
      contentType: "application/json",
     	
     	    data: jsonData,
      success: function() {
    	 	clear();
    	  alert("products returned successfully");
     

    	  
         
      }
  });
  const tableDataRows = table.querySelectorAll('tbody tr');
  for (let i = 0; i < tableDataRows.length; i++) {
      const row = tableDataRows[i];
      const newRow = document.createElement('tr');
      for (let j = 0; j < row.cells.length - 2; j++) {
          const cellValue = row.cells[j].textContent;
          const newCell = document.createElement('td');
          newCell.textContent = cellValue;
          newRow.appendChild(newCell);
      }
      const actionCell = document.createElement('td');
      const addButton = document.createElement('button');
      addButton.textContent = 'Add';
      addButton.addEventListener('click', function () {
          moveToTable2(this);
      });
      actionCell.appendChild(addButton);
      newRow.appendChild(actionCell);
      table1.querySelector('tbody').appendChild(newRow);
  }

  // Clear dataTable
  const dataTableBody = table.querySelector('tbody');
  dataTableBody.innerHTML = '';

  
  
  
    }
    function ButtonActionback(button) {
    	  // Get the row of the clicked button
    	  var row = button.parentNode.parentNode;

    	  // Remove the row from dataTable
    	  row.parentNode.removeChild(row);

    	  // Retrieve the first table (table with class "table")
    	  var table1 = document.querySelector(".table");

    	  // Create a new row in table1 with the values from the deleted row
    	  var newRow = document.createElement("tr");
    	  for (var i = 0; i < row.cells.length-2; i++) {
    	    var cellValue = row.cells[i].textContent;
    	    var newCell = document.createElement("td");
    	    newCell.textContent = cellValue;
    	    newRow.appendChild(newCell);
    	  }

    	  // Create a new cell for the "Action" column
    	  var actionCell = document.createElement("td");
    	  var addButton = document.createElement("button");
    	  addButton.textContent = "Add";
    	  addButton.addEventListener("click", function() {
    	    moveToTable2(this);
    	  });
    	  actionCell.appendChild(addButton);
    	  newRow.appendChild(actionCell);

    	  // Append the new row to table1
    	  table1.querySelector("tbody").appendChild(newRow);
    	}
