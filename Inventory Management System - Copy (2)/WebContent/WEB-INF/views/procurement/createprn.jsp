<html>
<head>
    
	<style>
	
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
	left:1020px;
	
	
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

	<label class="prnClass"><h4>GRN Data</h4></label>
	<table class="table bg-white rounded shadow-sm  table-hover">
                            <thead>
                                <tr>
                                    <th scope="col" width="50">#</th>
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
                                    <th scope="row">1</th>
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
                                    <th scope="row">2</th>
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
                        
<label class="prnfilterClass2">Enter Reason</label>
	<input type="text"  id="reason" name="reason" class="prnfilterClass2">
 
<label class="prnClass"><h4>Returns List</h4></label>						
<table class="table bg-white rounded shadow-sm  table-hover" id="dataTable" >
	<thead id="dt">
         <tr>
			
			<th scope="col">product_id</th>
			<th scope="col">batch_no</th>
			<th scope="col">quantity</th>
			
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
 
   
  function moveToTable2(button) {
	  // Get the row of the clicked button
	  const row = button.parentNode.parentNode;

	  // Get the values from the row
	  const product_id = row.cells[1].textContent;
	  const batch = row.cells[2].textContent;

	  // Create a new row in table2 with the values
	  const newRow = document.createElement("tr");
	  const idCell = document.createElement("td");
	  const batchCell = document.createElement("td");
	  
	  
	  const quantityCell = document.createElement("td");
	  const reasonCell = document.createElement("td");
	  const actionCell = document.createElement("td");
	  idCell.textContent = product_id;
	  batchCell.textContent = batch;
	
	  newRow.appendChild(idCell);
	  newRow.appendChild(batchCell);

	  

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
	    ButtonAction(this);
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
    	  // Retrieve the table element
    	  const table = document.getElementById('dataTable');
  const tableData = [];

  // Get the column headers
  const headers = [];
  const headerRow = table.rows[0];
  for (let i = 0; i < headerRow.cells.length - 1; i++) {
    const headerText = headerRow.cells[i].textContent.trim();
    if (headerText !== 'Action') {
      headers.push(headerText);
    }
  }

  // Iterate through each row of the table
  for (let i = 1; i < table.rows.length; i++) {
    const row = table.rows[i];
    const rowData = {};

    // Iterate through each cell of the row
    let headerIndex = 0;
    for (let j = 0; j < row.cells.length - 1; j++) {
      const headerText = headerRow.cells[j].textContent.trim();
      if (headerText !== 'Action') {
        const cell = row.cells[j];

        // Check if the cell contains an input element
        if (cell.firstChild && cell.firstChild.tagName === 'INPUT') {
          // Assign the input field value
          rowData[headers[headerIndex]] = cell.firstChild.value.trim();
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
    	  alert("products returned successfully");
         
      }
  });

  
  
  
    }

  </script>

</body>

</html>