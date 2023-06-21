<!DOCTYPE html>
<html>
<head>
  <title>Create Indent</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <style>
    #htag{
  position: relative;
  top: 20px;
  }
    table {
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    th, td {
    text-align:center;
      border: 1px solid black;
      padding: 8px;
    }

    .delete-button {
      background-color: #f44336;
      border: none;
      color: white;
      padding: 6px 12px;
       font-weight: bold;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 14px;
      margin: 4px 2px;
      cursor: pointer;
      border-radius:6px;
    }

    .table-container {
      display: flex;
      padding-bottom: 60px;
      
    }

    .table-container table {
      flex: 1;
      margin-right: 10px;
      float:right;
      
    }

    #product-form {
      padding-bottom: 50px;
    }

    .form-label {
      display: block;
      margin-bottom: 5px;
    }

    .form-input {
      width: 100px;
      padding: 5px;
      margin-bottom: 10px;
    }

    .form-submit:hover {
      background-color: #45a049;
      
    }

    .form-input-container {
      display: flex;
      gap: 10px;
      margin-bottom: 10px;
    }

     #products-table {
      background-color: white;
      height: 50px;
      overflow-y: scroll;
      width:1000px;
    }
    #total_indent
    {
    display: flex;
      gap: 10px;
      margin-bottom: 10px;
    }
    
        #first_table {
       height: 200px;
      
      overflow-y: scroll;
    }
    
        #Product-details-table {
       background-color: white;
      width:350px;

    }
           #reorder-level-table {
      background-color: white;
      width:350px;

    }
    
    #second_table {
       height: 200px;
      
      overflow-y: scroll;
    }

    
#addedproducts{

      justify-content: center;
      align-items: center;
      margin-left:70px;
      
    
    }
    .addProductsButtonClass{
    margin-left:50px;
    border-radius:6px;
    width:121px;
    height:40px;
    background-color: #4CAF50;
    border: none;
    color: white;
    cursor: pointer;
    font-weight: bold;
     box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
    
    }
    #product-form{
      display: flex;
      margin-left: 200px;
      
    
    }
    
    label {
      display: block;
      font-weight: bold;
      color: #333;
      margin-bottom: 5px;
    }
    #createIndentButtonId{
    margin-left:450px;
    border-radius:6px;
    width:80px;
    height:40px;
    background-color: #4CAF50;
    border: none;
    color: white;
    cursor: pointer;
    font-weight: bold;
     box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
     
    }
    #products-dropdown
    {
    display:flex;
     margin-left:750px;
     padding-bottom:10px;
     margin-bottom:10px;
     
    }
     .error-message {
      color: red;
      font-size: 12px;
      margin-top: 5px;
    }
    #products-table input{
    
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 3px;
    width: 80px;
    }
    .delete-btn{
    
        background-color: #ff0000;
      color: #fff;
      border: none;
       color: white;
      font-weight: bold;
      padding: 6px 12px;
      cursor: pointer;
      border-radius:6px;
      box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
    }
    .add-btn,.add-btn1{
    border-radius:6px;
    background-color: #4CAF50;
    border: none;
    color: white;
    cursor: pointer;
    font-weight: bold;
     box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
    }

  </style>
</head>
<body>
  <div id="htag">
    <h2 align="center" style="margin-bottom: 50px; margin-right:160px;">Create Indent</h2>
  </div>

  <div id="products-dropdown">
    <label for="product-category" style="padding-right:10px; font-size:16px">Product Category </label>
    <select id="product-category" style="width:100px">
      <option>select category</option>
    </select>
  </div>

  <div class="table-container">
    <div style="margin-right:50px; margin-left:150px">
      <h3 align="center">Reorder Level Details</h3>
      <div id="first_table">
        <table id="reorder-level-table">
          <thead>
            <tr>
              <th>Product ID</th>
              <th>Product Name</th>
              <th>Batch No.</th>
              <th>Stock</th>
              <th>Add</th>
            </tr>
          </thead>
        </table>
      </div>
    </div>

    <div style="margin-left:50px;">
      <h3 align="center">Products Details</h3>
      <div id="second_table">
        <table id="Product-details-table">
          <thead>
            <tr>
              <th>Product ID</th>
              <th>Product Name</th>
              <th>Batch No.</th>
              <th>Stock</th>
              <th>Add</th>
            </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
    </div>
  </div>

  <h2 align="center" style="margin-bottom:20px">Indents List</h2>
     <div id="addedproducts" >
       <form id="indentForm" onsubmit="createIndent();return false" method="post" >
          <table class="table bg-white rounded shadow-sm  table-hover" id="products-table">
           <thead>
              <tr>
                 <th id="productId">Product ID</th>
                 <th>Product Name</th>
                 <th id="batchId">Batch No.</th>
                 <th >Stock</th>
                 <th id="quantity">Required Stock</th>
                 <th>Action</th>
             </tr>
           </thead>
           <tbody></tbody>
          </table>
          <button type="submit" id="createIndentButtonId">Create</button>
  </form>
</div>

  <script>
  var addButtons;
    $(document).ready(function() {
    
    	 $.ajax({
     	     url :"getProductCategories",
     	     method :"post",
     	   success : function(data) {
              $.each(data, function(index, category) {
                  var option = '<option value="' + category.productCategoryId + '">' + category.productCategoryName + '</option>';
                  $('#product-category').append(option);
              });
          },
          error: function() {
              alert('Error occurred while retrieving categories.');
          }
     	    	  
     	});	
    	
    	
      $.ajax({
        url: "getProductStockData",
        method: "post",
        data: {
          categoryId: "101"
        },
        success: function(reorderlist) {
          // Create a new row with the received data
          $.each(reorderlist, function(index, data) {
            var newRow = '<tr>' +
              '<td>' + data.productId + '</td>' +
              '<td>' + data.productName + '</td>' +
              '<td>' + data.batchNo + '</td>' +
              '<td>' + data.stock + '</td>' +
              '<td><button class="add-btn">Add</button></td>' +
              '</tr>';

            // Append the new row to the table body
            $('#Product-details-table tbody').append(newRow); 
          });
          // Add event listeners to the dynamically created buttons
          var addButtons = $('#Product-details-table .add-btn');
          addButtons.click(function() {
            var row = $(this).closest('tr');
            addRowToSelectedTable(row);
          });
          
          // Place your code here that needs to be executed after the data is loaded in the table
          // ...
          console.log("Data loaded successfully!");

        },
        error: function() {
          alert('Error occurred while retrieving Products by categoryId.');
        }
      });
      
      
      $("#product-category").change(function(){
			
			
		   	$.ajax({
		   	     url :"getProductStockData",
		   	     method :"post",
		   	  data:{
		   	    	
		   	    categoryId : $("#product-category").val()
		   	     },
		   	   success : function(reorderlist) {
		   		// Create a new row with the received data
		   		$('#Product-details-table tbody').empty();
		   		$.each(reorderlist, function(index, data) {
		   			
		   		  var newRow = '<tr>' +
		   		    '<td>' + data.productId + '</td>' +
		   		    '<td>' + data.productName + '</td>' +
		   		    '<td>' + data.batchNo + '</td>' +
		   		    '<td>' + data.stock + '</td>' +
		   		    '<td><button class="add-btn">Add</button></td>' +
		   		    '</tr>';

		   		  // Append the new row to the table body
		   		  $('#Product-details-table tbody').append(newRow); 
		   		});
		
		   	 // Add event listeners to the dynamically created buttons
		          var addButtons = $('#Product-details-table .add-btn');
		          addButtons.click(function() {
		            var row = $(this).closest('tr');
		            addRowToSelectedTable(row);
		          });

		          // Place your code here that needs to be executed after the data is loaded in the table
		          // ...
		          console.log("Data loaded successfully!");
		        },
		        error: function() {
		            alert('Error occurred while retrieving categories.');
		        
		   	   }
		   	    	  
		   	});
			});
      
      
      $(document).on('click', '#products-table .delete-btn', function() {
          var row = $(this).closest('tr');
          deleteRowFromSelectedTable(row);
        });

      // Function to add a row to the selected table
  function addRowToSelectedTable(row) {
  console.log(addButtons);
  var selectedTable = $('#products-table');
  var cells = row[0].cells;
  var field1 = cells[0].textContent;
  var field2 = cells[1].textContent;
  var field3 = cells[2].textContent;
  var field4 = cells[3].textContent;

  var newRow = '<tr>' +
    '<td>' + field1 + '</td>' +
    '<td>' + field2 + '</td>' +
    '<td>' + field3 + '</td>' +
    '<td>' + field4 + '</td>' +
    '<td><input type="number" value="1"></td>' +
    '<td><button class="delete-btn">Delete</button></td>' +
    '</tr>';

  selectedTable.find('tbody').append(newRow);

      }
      // Function to delete a row from the selected table
      function deleteRowFromSelectedTable(row) {
    	  row.remove();
    	  
      }

      
     
    });
    
    function getTableData() {
    	  const table = document.getElementById('products-table');
    	  const tableData = [];
    	  
    	  for (let i = 1; i < table.rows.length; i++) {
    	    const row = table.rows[i];
    	    const rowData = {};

    	    rowData["productId"] = row.cells[0].innerText;
    	    rowData["quantity"] = row.cells[4].querySelector('input').value;

    	    tableData.push(rowData);
    	  }

    	  const jsonData = {
    	    "productsList": tableData
    	  };

    	  return jsonData;
    	}

     
     function createIndent()
     {
     	var data = getTableData();
     	const jsonData = JSON.stringify(data);
    		console.log(jsonData);
     	$.ajax({
     		url:"createProcurementIndent",
     		method:"post",
     		data:{"jsonData":jsonData},
     		success:function(page)
     		{
     			console.log("Success");
     			$('#products-table tbody').empty();
     			 alert('Succesfully created Indents to the Procurement');
     			 
     			
     		}
     	
     	});
     }
    
    
  </script>
</body>
</html>
