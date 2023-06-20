    <style>
        .container {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0 auto;
            max-width: 600px;
        }

        h1 {
            margin-bottom: 20px;
        }

        #indentForm{
            display: flex;
            margin-bottom: 20px;
            align-items: center;
            flex-direction: column;
        }

        label {
            display: inline-block;
            width: 100px;
            text-align: right;
            vertical-align: middle;
        }

        input[type="text"],select {
            width: 200px;
            padding: 5px;
            vertical-align: middle;
        }

        button {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            vertical-align: middle;
        }

        table {
            width: auto;
            border-collapse: collapse;
            margin-bottom: 20px;
            margin-left: auto;
            margin-right: auto;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        .deleteButton {
            padding: 5px 10px;
            background-color: #f44336;
            color: white;
            border: none;
            cursor: pointer;
            vertical-align: middle;
        }

        .error {
            color: red;
            font-weight: bold;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

$(document).ready(function () {
 	$.ajax({
		url:"getStoreIds",
		method:"post",
		dataType:"json",
		success:function(storeData){
		    var select = document.getElementById('storeId');
		    
		    // Clear any existing options
		    select.innerHTML = '';

	           var nonSelectableOption = document.createElement('option');
	            nonSelectableOption.disabled = true;
	            nonSelectableOption.selected = true;
	            nonSelectableOption.textContent = 'Select a Store';
	            select.appendChild(nonSelectableOption);

	            // Iterate over the store data and create options
		    for (var i = 0; i < storeData.length; i++) {
		        var option = document.createElement('option');
		        option.value = storeData[i].storeId;
		        option.textContent = storeData[i].storeName+" ("+storeData[i].storeId+")";
		        select.appendChild(option);
		    }
		},
		error:function(){
			console.log("Unable to load store Data");
		}
	});
 	
 	$('#storeId').change(function(){
 	    var storeId = document.getElementById('storeId').value;
 	    console.log(storeId);
 		$.ajax({
 			url:'getStoreIssueIds',
 			method:"post",
 			dataType:"json",
 			data:{"storeId":JSON.stringify({"storeId":storeId})},
 			success:function(response){
 				console.log(response);
 			},
 			error:function(response){
 				console.log("Error");
 				console.log(response);
 			}
 				
 		});
 	});
});

  function getTableData() {
    const table = document.getElementById('returnsTable');
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
        const cell = row.cells[j];
        const cellHeader = headerRow.cells[j].id; // Use textContent to get the header

        // Assign the cell text content
        rowData[cellHeader] = cell.textContent;
      }

      // Add row data to the tableData array
      tableData.push(rowData);
    }

    const jsonData = {};
    jsonData['storeID'] = document.getElementById('storeId').value;
    jsonData['productsList'] = tableData;
    return jsonData;
  }

  function createReturn() {
    var table = document.getElementById('returnsTable');
    var tbody = table.getElementsByTagName('tbody')[0];
    var rowCount = tbody.rows.length;
    console.log(tbody);
    console.log(rowCount);
    // Check if the table has data
    if (rowCount <= 1) {
      alert('Table is empty. Add data to proceed.');
      return;
    }

    var data = getTableData();
    const jsonData = JSON.stringify(data);
    console.log(jsonData);
    $.ajax({
      url: 'newCreateStoreReturn',
      method: 'post',
      data: { jsonData: jsonData },
      success: function (page) {
        console.log('Success');
        alert('Return successful!'); // Display alert message

        // Remove table data
        const table = document.getElementById('returnsTable');
        const tbody = table.getElementsByTagName('tbody')[0];
        tbody.innerHTML = '<tr class="no-data"><td colspan="3">No data</td></tr>';
        $('#storeId').val('');
      },
      error: function (page) {
        alert('Invalid Data');
      },
    });
  }
  $(document).ready(function () {
	  var productBatchData = []; // Array to store product and batch data

	  $('#addButton').click(function () {
	    var productId = $('#productId').val();
	    var batchNo = $('#BatchNo').val();
	    var quantity = $('#quantity').val();
	    var reason = $('#reason').val();

	    // Check if productId or batchNo already exists in the table
	    if (productId === '' || batchNo === '' || quantity===''||reason==='') {
	      return;
	    }

	    // Create a new table row
	    var newRow = $('<tr></tr>');

	    // Add table cells for the form data
	    newRow.append('<td>' + productId + '</td>');
	    newRow.append('<td>' + batchNo + '</td>');
	    newRow.append('<td>' + quantity + '</td>');
	    newRow.append('<td>' + reason + '</td>');

	    // Add a delete button cell
	    newRow.append('<td><button class="deleteButton">Delete</button></td>');

	    // Append the new row to the table body
	    $('#returnsTable tbody').append(newRow);

	    // Remove the "No data" row if it exists
	    $('#returnsTable tbody tr.no-data').remove();

	    // Add product and batch data to the array
	    productBatchData.push({ productId: productId, batchNo: batchNo });

	    // Clear the form fields
	    $('#productId').val('');
	    $('#BatchNo').val('');
	    $('#quantity').val('');
	    $('#reason').val('');
	  });

	  // Delete button click handler
	  $(document).on('click', '.deleteButton', function () {
	    var row = $(this).closest('tr');
	    var productId = row.find('td:nth-child(1)').text();
	    var batchNo = row.find('td:nth-child(2)').text();

	    // Remove the row from the table
	    row.remove();

	    // Remove the product and batch data from the array
	    productBatchData = productBatchData.filter(function (data) {
	      return data.productId !== productId || data.batchNo !== batchNo;
	    });

	    // Add back the "No data" row if the table is empty
	    if ($('#returnsTable tbody tr').length === 0) {
	      $('#returnsTable tbody').append(
	        '<tr class="no-data"><td colspan="5">No data</td></tr>'
	      );
	    }
	  });
	});

</script>
<div class="container" align="center">
  <h1>Store Returns</h1><br /><br />

  <form id="storeReturnsForm" onsubmit="createReturn(); return false;">
	<div>
		<label for="storeId">Store ID:</label> <select id="storeId"
			name="StoreID" required></select><br>
		<br>
	</div>
    <div>
      <label for="issueId">Issue ID:</label>
      <input type="text" id="issueId" name="issueId" required /><br /><br />
    </div>
    <div>
      <label for="productId">Product ID:</label>
      <input type="text" id="productId" name="productId"  /><br /><br />
    </div>
    <div>
      <label for="BatchNo">Batch NO:</label>
      <input type="text" id="BatchNo" name="BatchNo"  /><br /><br />
    </div>
    <div>
      <label for="quantity">Quantity:</label>
      <input type="text" id="quantity" name="quantity" /><br /><br />
    </div>
    <div>
      <label for="reason">Reason:</label>
      <textarea id="reason" name="reason" ></textarea><br /><br />
    </div>

    <button type="button" id="addButton" >Add</button><br /><br />
    <table id="returnsTable" border="1">
      <thead>
        <tr>
          <th id="productId">Product ID</th>
          <th id="batchNo">Batch NO</th>
          <th id="quantity">Quantity</th>
          <th id="reason">Reason</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr class="no-data">
          <td colspan="3">No data</td>
        </tr>
      </tbody>
    </table>
    <input type="submit" />
  </form>
</div>
    