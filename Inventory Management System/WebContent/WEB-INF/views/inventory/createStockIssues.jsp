<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create Store Issue</title>
 
  
  <style>
  #product-details-table{
    background-color: white;
      height: 50px;
      overflow-y: scroll;
      width:1000px;
  
  }
  
    #htag{
  position: relative;
  top: 10px;
  margin-left: 450px;

  }
  
  #h1tag
  {   
     position: relative;
    top: 10px;
     margin-left: 500px;
  }

   #addButton
    {
        margin-left: 450px;
        border-radius:6px;
       width:120px;
      height:40px;
      background-color: #4CAF50;
      border: none;
      color: white;
      cursor: pointer;
      font-weight: bold;
      box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
      
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

    .form-label {
      display: block;
      margin-bottom: 5px;
    }

    .form-input {
      width: 100px;
      padding: 5px;
      margin-bottom: 10px;
    }

     #products-table {
      background-color: white;
      height: 50px;
      overflow-y: scroll;
      width:1000px;
  
    }
    
    #first_table {
       height: 200px;
        margin-left:50px;
      overflow-y: scroll;
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
     margin-left:650px;
     padding-bottom:10px;
     
    }
     #products-dropdown1
    {
     display:flex;
     margin-left:640px;
     padding-bottom:10px;
     
    }
    #product-category{
    
     padding-left:20px;
     padding-right:20px;
     
    
    }
    #products-dropdown label{
     display: block;
      font-weight: bold;
      font-size: 20px;
      color: #333;
      margin-bottom: 10px;
      margin-left:200px;
      margin-right: 10px;
    }
     #products-dropdown1 label{
     display: block;
      font-weight: bold;
      font-size: 20px;
      color: #333;
      margin-bottom: 10px;
      margin-left:200px;
      margin-right: 10px;
    }
    #products-table input,select{
    
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
    .add-btn{
    border-radius:6px;
    background-color: #4CAF50;
    border: none;
    color: white;
    cursor: pointer;
    font-weight: bold;
     box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
    }
    #addedproducts{
    margin-left:50px;
    }
  .no-data {
  text-align: center;
  background-color: #f8f8f8;
  color: #555;
  font-weight: bold;
  padding: 10px;
}

.no-data:before {

  font-size: 18px;
}


   
  </style>
</head>
<body>
  <h2 id="htag">Create Stock Issue</h2>
    <div style="margin-bottom:20px">
    <div id="products-dropdown" align="right" >
    <label for="stores-list">Stores List: </label>
    <select id="stores-list">
    </select>
    </div>
    
	     <div style="margin-bottom:20px">
	   <div id="products-dropdown1" align="right" >
	    <label for="indents-list">Indents List: </label>
	    <select id="indents-list">
	    </select>
	    </div>
	  </div>
	 
	  
	  <div id="first_table">
	    <table id="product-details-table">
	      <thead>
	        <tr>
	           <th>Indent ID</th>
	          <th>Product ID</th>
	          <th>Product Name</th>
	          <th>Requested Quantity</th>
	          <th>Action</th>
	        </tr>
	      </thead>
	      <tbody id="product-details-table-body">
	      </tbody>
	    </table>
	  </div>
	</div>

  

  <h2 id="h1tag" style="margin-bottom:30px">Indents List</h2>

  <div id="addedproducts">
    <form onsubmit="createIssue(); return false">
      <table class="table bg-white rounded shadow-sm table-hover" id="products-table">
        <thead>
          <tr>
            <th>Indent ID</th>
          <th id="productId">Product ID</th>
          <th>Product Name</th>
          <th id="batchNo">Batch No</th>
          <th>Amount</th>
          <th id="issuedQuantity">Issued Quantity</th>
          <th id="purchaseAmount">Purchase Amount</th>
          <th>Action</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
      <input type="submit" id="addButton" value="Issue Stock">
    </form>
  </div>
  
  
   <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
  
  $(document).ready(function() {
    $.ajax({
      url: "getStoreIds",
      method: "post",
      success: function(data) {
    	  console.log(data);
        $('#stores-list').empty();
        var option = '<option>' + "select StoreID" + '</option>';
        $('#stores-list').append(option);
        $.each(data, function(index, products) {
          var option = '<option value="' + products.storeId + '">' + products.storeName + '</option>';
          $('#stores-list').append(option);
        });
      },
      error: function() {
        alert('Error occurred while retrieving categories.');
      }
    });

    $("#stores-list").change(function() {
      $.ajax({
        url: "getIndentsListByStoreID",
        method: "post",
        data: {
          "storeId": $("#stores-list").val()
        },
        success: function(data) {
          $('#product-details-table-body').empty(); // Remove existing rows
          console.log(data);
          $('#indents-list').empty();
          var option = '<option>' + "select IndentID" + '</option>';
          $('#indents-list').append(option);
          $.each(data, function(index, products) {
            var option = '<option value="' + products.indentId + '">' + products.indentId + '</option>';
            $('#indents-list').append(option);
          });
        },
        error: function() {
          alert('Error occurred while retrieving categories.');
        }
      });
    });

    $("#indents-list").change(function() {
      var data = {}
      data["indentId"] = $("#indents-list").val();
      $.ajax({
        url: "getStoreIndentProductsListData",
        method: "post",
        data: {
          "indentId": JSON.stringify(data)
        },
        success: function(prodlist) {
        	console.log(prodlist);
          $('#product-details-table-body').html('');
          $.each(prodlist, function(index, data1) {
            console.log(index);
            console.log(data1);
            var newRow = '<tr>' +
              '<td>' + $("#indents-list").val() + '</td>' +
              '<td>' + data1.productId + '</td>' +
              '<td>' + data1.productName + '</td>' +
              '<td>' + data1.quantity + '</td>' +
              '<td><button class="add-btn" value="' + data1.productId + '">Add</button></td>' +
              '</tr>';

            // Append the new row to the table body
            $('#product-details-table tbody').append(newRow);
            console.log("Completed");
          });

          var addButtons = $('#product-details-table .add-btn');
          addButtons.click(function() {
            var row = $(this).closest('tr');
            var value = $(this).val();

            console.log(value);
            addRowToSelectedTable(row);
          });
        },
        error: function() {
          alert('Error occurred while retrieving Products Data');
        }
      });

    });

    function addRowToSelectedTable(row) {
      var selectedTable = $('#products-table');
      selectedTable.find('.add-btn').prop('disabled', false);
      selectedTable.find('.no-data').remove();
      var cells = row[0].cells;
      var field1 = cells[0].textContent;
      var field2 = cells[1].textContent;
      var field3 = cells[2].textContent;
      var field4 = cells[3].textContent;
      var field5 =cells[4].textContent;
      var batchNoSelect = '<select class="batch-no-select" required>';
      var batchNumber;
      var productId = field2;
      $.ajax({
        url: 'getBatchNumbers',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
          productId: productId
        }),
        success: function(batchNumbers) {
        	batchNumber=batchNumbers;
          console.log(batchNumbers);
          batchNoSelect+='<option selected disabled value="">Select Batch No</option>';
          batchNumbers.forEach(function(batch) {
            batchNoSelect += '<option value="' + batch.batchNo + '">' + batch.batchNo + '</option>';
            productSalePrice = batch.productSalePrice;
          });

          batchNoSelect += '</select>';

          var newRow = '<tr>' +
            '<td>' + field1 + '</td>' +
            '<td class="product-no">' + field2 + '</td>' +
            '<td>' + field3 + '</td>' +
            '<td class="batch-no">' + batchNoSelect + '</td>' +
            '<td class="purchase-sale-price-cell"></td>' +
            '<td><input type="number" class="issued-quantity-input" value="1" min="1"></td>'+
            '<td class="purchase-amount-cell"></td>'+
            '<td><button class="delete-btn">Delete</button></td>' +
            '</tr>';

          selectedTable.find('tbody').append(newRow);
          $(document).on('change', '.batch-no-select', function() {
        	  var BatchNo = $(this).val(); 
        	  console.log(BatchNo);
        	  var purchaseSalePrice;
        	  var productId = $(this).closest('tr').find('td:eq(1)').text();
        	  console.log(productId);
        	  batchNumber.forEach(function(batch) {
        	    if ( batch.batchNo == BatchNo && batch.productId == productId) {
        	      purchaseSalePrice = batch.productSalePrice;
        	      
        	    }
        	  });
        	  console.log(purchaseSalePrice);
        	  var tableRow = $(this).closest('tr');
        	  var priceCell = tableRow.find('.purchase-sale-price-cell');
        	  var price=tableRow.find('.purchase-amount-cell');
        	  priceCell.text(purchaseSalePrice);
        	  price.text(purchaseSalePrice);
        	});
          $(document).on('change', '.issued-quantity-input', function() {
        	  var row = $(this).closest('tr');
        	  var issuedQuantity = $(this).val();
        	  var purchaseSalePrice = parseFloat(row.find('.purchase-sale-price-cell').text());
        	  var amount = purchaseSalePrice * issuedQuantity;
        	  row.find('.purchase-amount-cell').text(amount);
        	});
        },
        error: function() {
          alert('Error occurred while retrieving batch numbers.');
        }
      });
    }
    function deleteRowFromSelectedTable(row) {
      row.remove();
      var selectedTable = $('#products-table');
      var rowCount = selectedTable.find('tbody tr').length;
      
      if (rowCount === 0) {
        var noDataField = '<tr class="no-data"><td colspan="3">No data</td></tr>';
        selectedTable.find('tbody').append(noDataField);
      }
    }

    $(document).on('click', '#products-table .delete-btn', function() {
      var row = $(this).closest('tr');
      deleteRowFromSelectedTable(row);
    });

  });
  function getTableData() {
	    const table = document.getElementById('products-table');
	    console.log(table.rows[1]);

	    // Create an array to store the table data
	    const tableData = [];

	    const headerRow = table.rows[0];

	    // Iterate through each row of the table
	    for (let i = 1; i < table.rows.length; i++) {
	      const row = table.rows[i];
	      const rowData = {};

	      // Iterate through each cell of the row excluding the "product_name" column
 		for (let j = 0; j < row.cells.length - 1; j++) {
  			if (row.cells[j] !== null) {
   			 const cell = row.cells[j];
    		const cellHeader = headerRow.cells[j].id; // Use textContent to get the header
    		let cellValue;

    // Check if the cell contains an input field
    	const inputField = cell.querySelector('input');
    	if (inputField !== null) {
      	cellValue = inputField.value;
    	} else {
      // Check if the cell contains a dropdown
     	const dropdown = cell.querySelector('select');
      	if (dropdown !== null) {
        	cellValue = dropdown.value;
      	} else {
        // If neither input field nor dropdown, assign the cell text content
        	cellValue = cell.textContent;
     	 }
   	 }

   		 // Assign the cell value
    		rowData[cellHeader] = cellValue;
 	 }
	}

	      // Add row data to the tableData array
	      tableData.push(rowData);
	    }
	    console.log(tableData);

	    const jsonData = {};
	    jsonData['storeId'] = $('#stores-list').val();
	    jsonData['storeProducts'] = tableData;
	    return jsonData;
	  }
  function createIssue() {
	    var table = document.getElementById('products-table');
	    var tbody = table.getElementsByTagName('tbody')[0];
	    var rowCount = tbody.rows.length;
	    console.log(tbody);
	    console.log(rowCount);

	    var data = getTableData();
	    const jsonData = JSON.stringify(data);
	    console.log(jsonData);
	    $.ajax({
	      url: 'issueStock',
	      method: 'post',
	      contentType:'application/json',
	      data: jsonData ,
	      success: function (page) {
	        console.log('Success');
	        alert('Stock Issued successful!'); // Display alert message
	        // Remove table data
	        const table = document.getElementById('products-table');
	        const tbody = table.getElementsByTagName('tbody')[0];
	        tbody.innerHTML = '<tr class="no-data"><td colspan="8">No data</td></tr>';
	        $('#storeId').val('');
	      },
	      error: function (page) {
	        alert('Invalid Data');
	      },
	    });
	  }
	  

</script>  
</body>
</html>