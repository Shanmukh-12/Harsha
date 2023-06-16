<html>
<head>
    
	<style>
	
	.prnClass
	{
	position:relative;
	left:620px;
	}
	.prnfilterClass
	{
	position:relative;
	left:1040px;
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
		<option>677</option>
	</select>

	<label class="prnClass">Grn value</label>
	<select id="grnvalue" class="prnClass" >
		<option>34567</option>
		
	</select>
	<br><br>
	<label class="prnClass">Select GRN ID:</label>
	<select id="prnId" class="prnClass" align="center" >
		<option>7983834</option>
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
                                    <td>8634</td>
									<td>60</td>
									<td>30</td>
									<td>2</td>
									<td>32</td>
									<td>76836</td>
									<td><button onclick="moveToTable2(this)">Add</button></td>
                                </tr>
                            </tbody>
                        </table><br><br>
<label class="prnClass"><h4>Returns List</h4></label>						
<table class="table bg-white rounded shadow-sm  table-hover" id="dataTable" >
	<thead id="dt">
         <tr>
			
			<th scope="col">Product Id</th>
			<th scope="col">Batch No</th>
			<th scope="col">Quantity</th>
			<th scope="col">Reason</th>
			<th>Action</th>
		</tr>                        
	</thead>   
  <tbody id="dataTable1">
</tbody>    </table>
  
  
  <br><br><br><br>
 <input type="button"  value="Confirm Returns" class="prnClass">
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
	  const product_id = row.cells[0].textContent;
	  const batch = row.cells[1].textContent;

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
	  inputNegativePrice.id = "negprice";
	  inputNegativePrice.style.width = "80px";
	  inputNegativePrice.style.height = "20px";
	  quantityCell.appendChild(inputNegativePrice);
	  newRow.appendChild(quantityCell);
	  
	  const inputNegativePrice2 = document.createElement("input");
	  inputNegativePrice2.type = "number";
	  inputNegativePrice2.id = "total Quantity";
	  inputNegativePrice2.style.width = "80px";
	  inputNegativePrice2.style.height = "20px";
	  reasonCell.appendChild(inputNegativePrice2);
	  newRow.appendChild(reasonCell);

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
  </script>

</body>

</html>