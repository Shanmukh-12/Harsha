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
             var productIds = [];

            $('#addButton').click(function () {
                var productId = $('#productId').val();
                var quantity = $('#quantity').val();

                if (!productId || productIds.includes(productId)) {
                    $('#productId').addClass('error');
                    return;
                } else {
                    $('#productId').removeClass('error');
                }

                var row = '<tr><td name="Indents_Products_ID" >' + productId + '</td><td name="Indents_Products_Quantity" >' + quantity + '</td><td><button class="deleteButton">Delete</button></td></tr>';
                $('#inventoryTable tbody').append(row);

                productIds.push(productId);
                $('#inventoryTable tbody tr.no-data').remove(); // Remove "No data" row if it exists
                $('#productId').val('');
                $('#quantity').val('');

            });

            $(document).on('click', '.deleteButton', function () {
                var productId = $(this).closest('tr').find('td:first').text();
                productIds = productIds.filter(function (id) {
                    return id !== productId;
                });
                $(this).closest('tr').remove();

                if ($('#inventoryTable tbody tr').length === 0) {
                    $('#inventoryTable tbody').append('<tr class="no-data"><td colspan="3">No data</td></tr>');
                }
            });
        });

        function getTableData(){
        	const table = document.getElementById('inventoryTable');
      	  console.log(table.rows[1])

      	  // Create an array to store the table data
      	  
      	  const tableData = [];

      	  const headerRow = table.rows[0];      	  
      	  
      	  // Iterate through each row of the table
      	  for (let i = 1; i < table.rows.length; i++) {
      	    const row = table.rows[i];
      	    const rowData = {};

      	    // Iterate through each cell of the row excluding the "product_name" column
      	    for (let j = 0; j < row.cells.length-1; j++) {

      	      const cell = row.cells[j];
      	      const cellHeader = headerRow.cells[j].id; // Use textContent to get the header

      	        // Assign the cell text content
      	        rowData[cellHeader] = cell.textContent;
      	    }

      	    // Add row data to the tableData array
      	    tableData.push(rowData);
      	  }
    	  const jsonData = {};
    	  jsonData["storeID"]=document.getElementById("storeId").value;
    	  jsonData["productsList"] = tableData;
    	  return jsonData;
      }
        
        function createIndent()
        {
            var table = document.getElementById('inventoryTable');
            var tbody = table.getElementsByTagName('tbody')[0];
            var rowCount = tbody.rows.length;
           	console.log(tbody);
			console.log(rowCount);
            // Check if the table has data
            if (rowCount <= 1) {
                alert("Table is empty. Add data to proceed.");
                return;
            }

        	var data = getTableData();
        	const jsonData = JSON.stringify(data);
			console.log(jsonData);
        	$.ajax({
        		url:"newCreateStoreIndent",
        		method:"post",
        		data:{"jsonData":jsonData},
        		success:function(page)
        		{
        			console.log("Success");
                    alert("Insertion is successful!"); // Display alert message

                    // Remove table data
                    const table = document.getElementById('inventoryTable');
                    const tbody = table.getElementsByTagName('tbody')[0];
                    tbody.innerHTML = '<tr class="no-data"><td colspan="3">No data</td></tr>';
                    $('#storeId').val('');
                    },
                    error:function(page)
                    {
                    	alert("Invalid Data");
                    }        	
        	});
        }