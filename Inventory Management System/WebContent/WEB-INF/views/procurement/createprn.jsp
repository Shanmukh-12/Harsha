<html>
<head>
    
	<style>
	.table {
        border-collapse: collapse;
        width: 90%;
        margin: 0 auto; /* Center the table */
        border: 1px solid #ccc; /* Add border for the table */
        border-radius: 4px; /* Add border-radius for a rounded look */
    }
  
	 th,
        td {
            padding: 8px;
            border: 1px solid #ccc; /* Add border for table cells */
                    text-align: center;
            
            border-bottom: 1px solid #ccc; /* Add border-bottom for table cells */
        }

        thead th {
            border-bottom-width: 2px; /* Increase border-bottom width for table header */
        }
        .table {
            border: 1px solid #ccc; /* Add border for the table */
            border-radius: 4px; /* Add border-radius for a rounded look */
        }

        .table-hover tbody tr:hover {
            background-color: #f5f5f5; /* Add hover effect for table rows */
        }
        .prnClass2
	{
	position:relative;
	left:560px;
	 font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
	}
	.prnClass
	{
	position:relative;
	left:500px;
	 font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
	}
	.prnfilterClass
	{
	position:relative;
	left:1040px;
	}
	.prnfilterClass2
	{
	position:relative;
	left:850px;
	
	
	}
	</style>
    
</head>

<body>
    <h1 align="center">Create PRN</h1>
	<br><br>
		<label class="prnClass">Order Recieved date</label>
	<input type="date" id="ordrecdate" class="prnClass">
	
	<label class="prnClass">Vendor Id</label>
	<select id="vendorId" class="prnClass" >
		<option>20001</option>
	</select>

	<label class="prnClass">Grn value</label>
	<select id="grnvalue" class="prnClass" >
		<option>34567</option>
		
	</select>
	<br><br>
	<label class="prnClass">Select GRN ID:</label>
	<select id="prnId" class="prnClass" align="center" >
		<option>3001</option>
		<option>3002</option>
	</select>
	<br><br><br>

	<label class="prnClass2"><h4>GRN Data</h4></label>
	<table class="table bg-white rounded shadow-sm  table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Product Id</th>
									<th scope="col">Batch NO</th>
									<th scope="col">Ordered Quantity</th>
									<th scope="col">Recieved Quantity</th>
									<th scope="col">Bonus</th>
									<th scope="col">Total Quantity</th>
									<th scope="col">Cost</th>
									<th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>1</td>
									<td>60</td>
									<td>30</td>
									<td>2</td>
									<td>32</td>
									<td>76836</td>
									<td><button onclick="moveToTable2(this)">Add</button></td>
                                </tr>
                                  <tr>
                                    <td>2</td>
                                    <td>1</td>
									<td>60</td>
									<td>30</td>
									<td>2</td>
									<td>32</td>
									<td>768</td>
									<td><button onclick="moveToTable2(this)">Add</button></td>
                                </tr>
                            </tbody>
                        </table><br><br>
   <label class="prnClass2"><h4>Returns List</h4></label>                      
<label class="prnfilterClass2">Enter Reason</label>
	<input type="text"  id="reason" name="reason" class="prnfilterClass2">
	
<table class="table bg-white rounded shadow-sm  table-hover" id="dataTable" >
	<thead id="dt">
         <tr>
			
			<th scope="col">product_id</th>
			<th scope="col">batch_no</th>
			<th scope="col">Ordered Quantity</th>
            <th scope="col">Recieved Quantity</th>
			<th scope="col">Bonus</th>
			
			<th scope="col">Total quantity</th>
            <th scope="col">Cost</th>
			
			<th scope="col">Returning quantity</th>
			
			

			<th>Action</th>
		</tr>                        
	</thead>   
  <tbody id="dataTable1">
</tbody>    </table>
  
  
  <br><br><br><br>
 <input type="button"  value="Confirm Returns" class="prnClass" onclick="createpurchasereturn()">
  <script>
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
	  const reasonCell = document.createElement("td");
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
  const selectedOption = grn_no.options[grn_no.selectedIndex].text;
  console.log(selectedOption);
  const vendor_id = document.getElementById("vendorId");
  const selectedOption2 = vendor_id.options[vendor_id.selectedIndex].text;
  console.log(selectedOption2);
  var reasonInput = document.getElementById("reason");
  var reasonValue = reasonInput.value;
  console.log(reasonValue);

  var data={};
  data["grn_cost"]=4000;
  data["vendor_id"]=selectedOption2;
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

  </script>

</body>

</html>