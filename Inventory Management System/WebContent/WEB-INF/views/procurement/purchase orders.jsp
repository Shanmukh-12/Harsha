<html>
   <head>
      <style>
         .indentClass
         {
         position:relative;
         left:400px;
          font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
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
         left:1040px;
         
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
               <th scope="col">Product Id</th>
               <th scope="col">Product Name</th>
               <th scope="col"> Quantity</th>
               <th scope="col">Action</th>
            </tr>
         </thead>
         <tbody>
            <tr>
               <th scope="row">1</th>
               <td>1</td>
               <td>Television</td>
               <td>200</td>
              <td><button onclick="moveToTable2(this)">Add</button></td>
            </tr>
            <tr>
               <th scope="row">2</th>
               <td>2</td>
               <td>santoor</td>
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
      <option>20001</option>
      </select>     
      <br>
      <table class="table bg-white rounded shadow-sm  table-hover" id="dataTable" >
         <thead id="dt">
            <tr>
            <th >product_id</th>
               <th>product_name</th>
               <th>purchase_order_quantity</th>
               
               <th>negotiation_price</th>
               <th>Action</th>
               <th></th>
            </tr>
         </thead>
         <tbody id="dataTable1">
         </tbody>
      </table>
      <br><br><br><br>
      
      <input type="button"  value="Create Purchase" class="indentClass1" onclick="createpurchase()">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
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
        	  const product_name = row.cells[2].textContent;
        	  const quantity = row.cells[3].textContent;

        	  // Create a new row in table2 with the values
        	  const newRow = document.createElement("tr");
        	  const idCell = document.createElement("td");

        	  const nameCell = document.createElement("td");
        	  const qtyCell = document.createElement("td");
        	  const negCell = document.createElement("td");
        	  const actionCell = document.createElement("td");
        	  nameCell.textContent = product_name;
        	  nameCell.value = product_name;
        	  idCell.textContent=product_id;
        	  idCell.value=product_id;
        	  const inputNegativePrice2 = document.createElement("input");
        	  inputNegativePrice2.type = "number";
        	  inputNegativePrice2.id = "order quantity";
        	  inputNegativePrice2.style.width = "80px";
        	  inputNegativePrice2.style.height = "20px";
        	  qtyCell.appendChild(inputNegativePrice2);
        	  newRow.appendChild(idCell);
        	  newRow.appendChild(nameCell);
        	  newRow.appendChild(qtyCell);

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
        	  row.parentNode.removeChild(row);
        	  // Remove the row from table1
        	  
        	}
         function createpurchase() {
        	  // Retrieve the table element
        	  const table = document.getElementById('dataTable');
        	  console.log(table.rows[1])
              var totalamount=0;
        	  // Create an array to store the table data
        	  const tableData = [];

        	  // Get the index of the "product_name" column
        	  let productNameColumnIndex = -1;
        	  let negotiationpriceindex = -1;
        	  const headerRow = table.rows[0];
        	  for (let j = 0; j < headerRow.cells.length; j++) {
        	    if (headerRow.cells[j].textContent === "product_name") {
        	      productNameColumnIndex = j;
        	      break;
        	    }
        	  }
        	  console.log(productNameColumnIndex);
        	  for (let j = 0; j < headerRow.cells.length; j++) {
          	    if (headerRow.cells[j].textContent === "negotiation_price") {
          	    	negotiationpriceindex = j;
          	    	
          	      break;
          	    }
          	  }
        	  console.log(negotiationpriceindex);

        	  // Iterate through each row of the table
        	  for (let i = 1; i < table.rows.length; i++) {
        	    const row = table.rows[i];
        	    const rowData = {};

        	    // Iterate through each cell of the row excluding the "product_name" column
        	    for (let j = 0; j < row.cells.length-1; j++) {
        	      if (j === productNameColumnIndex) continue; // Skip the "product_name" column
        	      const cell = row.cells[j];
        	      console.log(cell);
        	      const cellHeader = headerRow.cells[j].textContent; // Use textContent to get the header
        	    

        	      // Check if the cell contains an input element
        	      if (cell.firstChild && cell.firstChild.tagName === 'INPUT') {
        	        // Assign the input field value
        	        rowData[cellHeader] = cell.firstChild.value;
        	        if (j === negotiationpriceindex) {
            	    	  console.log("hii");
            	    	  console.log(cell.firstChild.value);
            	    	  const price = parseInt(cell.firstChild.value);
            	    	  if (!isNaN(price)) {
            	    	    totalamount += price;
            	    	  }
            	    	}
        	      } else {
        	        // Assign the cell text content
        	        rowData[cellHeader] = cell.textContent;
        	    	  console.log(cell.textContent);

        	       
        	      }
        	    }
                  
        	    // Add row data to the tableData array
        	    tableData.push(rowData);
        	  }
        	

        	  var children=[];
        	
             const rows={};
             rows["vendor_id"]="20001";
             rows["purchase_order_amount"]=totalamount;
             
              rows["children"]=tableData;
              children.push(rows);
       	      
       	  console.log( JSON.stringify(rows));
             

        	  var datas={"purchase_order_date":"2023-06-13","purchase_order_amount":"5000.0","vendor_id":"1","purchase_order_expected_date":"2023-06-20","purchase_order_status":"Active","user_id":2,"last_updated_user":"John Doe","last_updated_date":"2023-06-13","children":[{"product_id":1,"purchase_order_quantity":10,"negotiation_price":100,"quantity_received":5},{"product_id":2,"purchase_order_quantity":5,"negotiation_price":50,"quantity_received":0}]}
        	  


        	  // Convert tableData to JSON string
        	  const jsonData = JSON.stringify(rows);
        	  $.ajax({
                  url: "makePurchseOrder",
                  method:"POST",
                  contentType: "application/json",
                 	
                 	    data: jsonData,
                  success: function() {
                	  alert("Purchase order created successfuly");
                     
                  }
              });

        	  // Output the JSON data
        	 
        	}



      </script>

   </body>
</html>