<html>

<head>
	<style>
	.indentClass
	{
	position:relative;
	left:620px;
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
      
  .refresh-icon {
  display: inline-block;
  cursor: pointer;
}
	</style>
    
</head>

<body>
    <h3 align="center">Create Purchase Order(Scratch)</h3><br><br>
<table class="table bg-white rounded shadow-sm  table-hover" id="dataTable" >
<thead id="dt">
                            <tr>
      <th>product_id</th>
      <th>purchase_order_quantity</th>
	  <th>vendor_id</th>
	  <th>negotiation_price </th>
	  <th>Action</th>
      <th></th>
    </tr>
                                
         </thead>   
  <tbody id="dataTable1">
</tbody>                        </table>
  
  
  <br><br><br><br>

  <label><b><h6>Add Products</h6></b></label>
 <div class="form-table">
    <table>
      <tr>
        <td><label for="prid">Product Id</label></td>
        <td>
			<select id="prid" class="prClass" align="center" >
			    <option value="" disabled selected>Select a product</option>
			
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
			</select>
		</td>
      </tr>
      <tr>
        <td><label for="qnty">purchase_order_quantity</label></td>
        <td><input type="number" id="qnty" name="qnty" required></td>
      </tr>
      <tr>
        <td><label for="vid">vendor_id</label></td>
        <td>
          <select id="vid" class="vid" align="center" onchange="handleSelectChange()">
            <option value="" disabled selected>Select an option</option>
            <option>20001</option>
            <option>20002</option>
          </select>
            <span class="refresh-icon searchClass" onclick="enableOptions()">&#x21bb;</span>
        </td>
    
        
      </tr>
      
      <tr>
        <td><label for="negPrice">negotiation_price</label></td>
        <td><input type="number" id="negPrice" name="negPrice" required></td>
      </tr>
      <tr>
        
        <td>
          <button id="addProductsId" class="addProductsClass" >Add Product</button>
        </td>
      </tr>
    </table>
   
  </div><br><br>
 <input type="button"  value="Create Purchase" class="indentClass1" onclick="createpurchase()">
 
  <script>
    document.getElementById("addProductsId").addEventListener("click", function(event) {
    	handleSelectChangeprid()
      event.preventDefault();
      var prid = document.getElementById("prid").value;
      var qnty = document.getElementById("qnty").value;
	  var vid = document.getElementById("vid").value;
	  var negPrice = document.getElementById("negPrice").value;
	  
	  
      var table = document.getElementById("dataTable1");
      var row = table.insertRow(-1);
      var pridCell = row.insertCell(0);
      var qntyCell = row.insertCell(1);
      var vidCell = row.insertCell(2);
	  var negPriceCell = row.insertCell(3);
	   var actionCell = row.insertCell(4);

      pridCell.innerHTML = prid;
      qntyCell.innerHTML = qnty;
	  vidCell.innerHTML = vid;
      negPriceCell.innerHTML = negPrice;
      actionCell.innerHTML = '<button class="delete-btn" onclick="deleteRow(this)">Delete</button>';

      document.getElementById("myForm").reset();
    });
   
    function deleteRow(button) {
      var row = button.parentNode.parentNode;
      row.parentNode.removeChild(row);
    }
    function handleSelectChange() {
    	console.log("hello");
        var selectElement = document.getElementById("vid");
        var options = selectElement.options;
        
        for (var i = 0; i < options.length; i++) {
          if (options[i].value !== selectElement.value) {
            options[i].disabled = true;
          }
        }
      }
    var selectedOptions = [];

    function handleSelectChangeprid() {
      var selectElement = document.getElementById("prid");
      var selectedValue = selectElement.value;

      if (!selectedOptions.includes(selectedValue)) {
        selectedOptions.push(selectedValue);
      }

      var options = selectElement.options;
      for (var i = 0; i < options.length; i++) {
        if (selectedOptions.includes(options[i].value)) {
          options[i].disabled = true;
        } else {
          options[i].disabled = false;
        }
      }
    }
    function enableOptions() {
        var selectElement = document.getElementById("vid");
       
        var options = selectElement.options;
        
        for (var i = 0; i < options.length; i++) {
          options[i].disabled = false;
        }
        selectElement.selectedIndex = 0;
      }

      function enableOptionspr() {
        var selectElement = document.getElementById("prid");
       
        var options = selectElement.options;
        
        for (var i = 0; i < options.length; i++) {
          options[i].disabled = false;
        }
        selectElement.selectedIndex = 0;
      }
    
    function createpurchase() {
    	enableOptions();
    	enableOptionspr();
    

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