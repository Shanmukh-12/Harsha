<html>
   <head>
      <style>
         .purchaseClass
         {
         position:relative;
         left:620px;
         }
         .purchaseClass1
         {
         position:relative;
         left:500px;
          font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
         }
         .purchaseClass2
         {
         position:relative;
         left:610px;
         }
         table {
         border-width: collapse;
         width: 100%;
         }
         #dataTable {
         width: 1000px; /* Change the width value to your desired width */
         border-collapse: collapse;
         }
         #dataTable td,
         #dataTable th {
         border: 1px solid #000; /* Change the border style and color as desired */
         padding: 8px; /* Adjust the padding as needed */
         }
         th {
         text-align: left;
         padding: 8px;
         border-bottom: 1px solid #ccc; /* Add border-bottom for table header */
         }
         label {
         font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
         padding: 10px;
         border-radius: 5px;
         }
         .purchaseClass {
         font-size: 16px;
         font-weight: bold;
         color: #fff;
         background-color: #6fbf73;
         padding: 10px 20px;
         border: none;
         border-radius: 5px;
         box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
         }
         .purchaseClass:hover {
         background-color: #4b9d54;
         }
         .purchaseClass:active {
         background-color: #3e7c46;
         }
         .addProductsClass{
         font-size: 16px;
         font-weight: bold;
         color: #fff;
         background-color: #6fbf73;
         padding: 10px 20px;
         border: none;
         border-radius: 5px;
         box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
         }
         .addProductsClass:hover {
         background-color: #4b9d54;
         }
         .addProductsClass:active {
         background-color: #3e7c46;
         }
      </style>
   </head>
   <body>
      <h1 align="center">Create GRN</h1>
      <br>
      <label class="purchaseClass1">Select VendorID:</label>
      <select id="vendorId" class="purchaseClass1" align="center" >
         <option>897</option>
      </select>
      
      <label class="purchaseClass1">Select OrderedDate:</label>
      <input type="date" id="orderedDate" class="purchaseClass1">
      
      
      <br>
      <label class="purchaseClass1">Select PurchaseID:</label>
      <select id="purchaseId" class="purchaseClass1" align="center" >
         <option>782</option>
      </select>
      
      
      
      <br><br>
      <label class="purchaseClass1">
         <h4> Purchased Items</h4>
      </label>
      <table class="table bg-white rounded shadow-sm  table-hover"  id="dataTable" align="center" >
         <thead>
            <tr>
               <th scope="col">Product Id</th>
               <th scope="col">Batch NO</th>
               <th scope="col">Ordered Quantity</th>
               <th scope="col">Recieved Quantity</th>
               <th scope="col">Cost</th>
               <th scope="col">Action</th>
            </tr>
         </thead>
         <tbody>
            <tr>
               <td>1</td>
               <td>2468</td>
               <td>60</td>
               <td>30</td>
               <td>76836</td>
               <td><button onclick="moveToTable2(this)">Add</button></td>
            </tr>
         </tbody>
      </table>
      <br><br>
      <label class="purchaseClass1">
         <h4>GRN List</h4>
      </label>
      <br>
      <table class="table bg-white rounded shadow-sm  table-hover" id="dataTable" align="center" >
         <thead id="dt">
            <tr>
               <th scope="col">Product Id</th>
               <th scope="col">Batch NO</th>
               <th scope="col">Ordered Quantity</th>
               <th scope="col">Recieved Quantity</th>
               <th scope="col">Bonus</th>
               <th scope="col">Total Quantity</th>
               <th scope="col">Cost</th>
               <th>Action</th>
            </tr>
         </thead>
         <tbody id="dataTable1"></tbody>
      </table>
      <br><br><br><br>
     
      <input type="button"  value="Create GRN" class="purchaseClass">
      <script>
      function ButtonAction(button) {
  		console.log("hello");
  	    var row = button.parentNode.parentNode;
  	    row.parentNode.removeChild(row);
  	  }
     
       
       
      
         
         function deleteRow(button) {
           var row = button.parentNode.parentNode;
           row.parentNode.removeChild(row);
         }
         
         function moveToTable2(button) {
        	  // Get the row of the clicked button
        	  const row = button.parentNode.parentNode;

        	  // Get the values from the row
        	  const product_id = row.cells[0].textContent;
        	  const batch = row.cells[1].textContent;
        	  const oq = row.cells[2].textContent;
        	  const rq = row.cells[3].textContent;
        	  const cost = row.cells[4].textContent;
        	  // Create a new row in table2 with the values
        	  const newRow = document.createElement("tr");
        	  const idCell = document.createElement("td");
        	  const batchCell = document.createElement("td");
        	  
        	  const oqCell = document.createElement("td");
        	  const rqCell = document.createElement("td");
        	  const bonusCell = document.createElement("td");
        	  const tqCell = document.createElement("td");
        	  const costCell = document.createElement("td");
        	  const actionCell = document.createElement("td");
        	  idCell.textContent = product_id;
        	  batchCell.textContent = batch;
        	  oqCell.textContent = oq;
        	  rqCell.textContent = rq;
        	  costCell.textContent = cost;
        	  newRow.appendChild(idCell);
        	  newRow.appendChild(batchCell);
        	  newRow.appendChild(oqCell);
        	  newRow.appendChild(rqCell);
        	  

        	  // Add the negative price input field
        	  const inputNegativePrice = document.createElement("input");
        	  inputNegativePrice.type = "number";
        	  inputNegativePrice.id = "negprice";
        	  inputNegativePrice.style.width = "80px";
        	  inputNegativePrice.style.height = "20px";
        	  bonusCell.appendChild(inputNegativePrice);
        	  newRow.appendChild(bonusCell);
        	  
        	  const inputNegativePrice2 = document.createElement("input");
        	  inputNegativePrice2.type = "number";
        	  inputNegativePrice2.id = "total Quantity";
        	  inputNegativePrice2.style.width = "80px";
        	  inputNegativePrice2.style.height = "20px";
        	  tqCell.appendChild(inputNegativePrice2);
        	  newRow.appendChild(tqCell);
        	  newRow.appendChild(costCell);

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

      </script>
   </body>
</html>