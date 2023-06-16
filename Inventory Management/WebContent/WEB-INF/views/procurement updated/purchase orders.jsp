<html>
   <head>
      <style>
         .indentClass
         {
         position:relative;
         left:500px;
         }
         .indentClass1
         {
         position:relative;
         left:620px;
         }
         .indentClass2
         {
         position:relative;
         left:610px;
         }
          .indentClass3
         {
         position:relative;
         left:1150px;
         
         }
         table {
         border-width: collapse;
         width: 100%;
         }
         #dt
         {
         color : green;
         }
         #indentTable
         {
         color : #4266f5;
         }
         th {
         text-align: left;
         padding: 8px;
         border-bottom: 1px solid #ccc; /* Add border-bottom for table header */
         }
      </style>
   </head>
   <body>
      <h1 align="center">Create Purchase</h1>
      <br>
      <label class="indentClass">Select IndentDate:</label>
      <input type="date" id="indentDate" class="indentClass">
	
	
	
	<label class="indentClass">IndentStatus:</label>
      <select id="vendorId" class="indentClass" align="center" >
         <option>Active</option>
      </select><br><br>
      
      <label class="indentClass">Select Indent ID:</label>
      <select id="indentId" class="indentClass" align="center">
         <option>782</option>
      </select>
      <br><br>
      <label class="indentClass1">
         <h4>Indent List</h4>
      </label>
      <table class="table bg-white rounded shadow-sm  table-hover"  >
         <thead id="indentTable">
            <tr>
               <th scope="col" width="50">#</th>
               <th scope="col">Product Name</th>
               <th scope="col"> Quantity</th>
               <th scope="col">Action</th>
            </tr>
         </thead>
         <tbody>
            <tr>
               <th scope="row">1</th>
               <td>Television</td>
               <td>200</td>
              <td><button onclick="moveToTable2(this)">Add</button></td>
            </tr>
         </tbody>
      </table>
      <br><br>
      
      <label class="indentClass1">
         <h4>Purchase List</h4>
      </label>
      <br>
      <label class="indentClass3">vendor id</label>
      <select id="" class="indentClass3" >
      <option>782</option>
      </select>     
      <br>
      <table class="table bg-white rounded shadow-sm  table-hover" id="dataTable" >
         <thead id="dt">
            <tr>
               <th>Product Name</th>
               <th>Ordered Quantity</th>
               
               <th>Negotiation Price </th>
               <th>Action</th>
               <th></th>
            </tr>
         </thead>
         <tbody id="dataTable1">
         </tbody>
      </table>
      <br><br><br><br>
      
      <input type="button"  value="Create Purchase" class="indentClass1">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
      <script>
        function ButtonAction(button) {
    		console.log("hello");
    	    var row = button.parentNode.parentNode;
    	    row.parentNode.removeChild(row);
    	  }
       
         
         var elementsArray = $("table thead th").toArray();
         
        
         function moveToTable2(button) {
        	  // Get the row of the clicked button
        	  const row = button.parentNode.parentNode;

        	  // Get the values from the row
        	  const product_id = row.cells[1].textContent;
        	  const quantity = row.cells[2].textContent;

        	  // Create a new row in table2 with the values
        	  const newRow = document.createElement("tr");
        	  const nameCell = document.createElement("td");
        	  const ageCell = document.createElement("td");
        	  const negCell = document.createElement("td");
        	  const actionCell = document.createElement("td");
        	  nameCell.textContent = product_id;
        	  const inputNegativePrice2 = document.createElement("input");
        	  inputNegativePrice2.type = "number";
        	  inputNegativePrice2.id = "order quantity";
        	  inputNegativePrice2.style.width = "80px";
        	  inputNegativePrice2.style.height = "20px";
        	  ageCell.appendChild(inputNegativePrice2);
        	  newRow.appendChild(nameCell);
        	  newRow.appendChild(ageCell);

        	  // Add the negative price input field
        	  const inputNegativePrice = document.createElement("input");
        	  inputNegativePrice.type = "number";
        	  inputNegativePrice.id = "negprice";
        	  inputNegativePrice.style.width = "80px";
        	  inputNegativePrice.style.height = "20px";
        	  negCell.appendChild(inputNegativePrice);

        	  // Add the delete button
        	  const deleteButton = document.createElement("button");
        	  deleteButton.textContent = "Delete";
        	  deleteButton.addEventListener("click", function () {
        	    ButtonAction(this);
        	  });
        	  actionCell.appendChild(deleteButton);

        	  newRow.appendChild(negCell);
        	  newRow.appendChild(actionCell);

        	  // Append the new row to table2
        	  const table2Body = document.querySelector("#dataTable tbody");
        	  table2Body.appendChild(newRow);

        	  // Remove the row from table1
        	  
        	}


      </script>

   </body>
</html>