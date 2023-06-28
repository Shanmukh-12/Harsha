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
	  