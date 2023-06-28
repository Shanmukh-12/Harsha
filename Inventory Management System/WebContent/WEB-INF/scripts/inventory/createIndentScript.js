 var addedProductIds = [];
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
    	        url: "getReOrderProductsData",
    	        method: "post",
    	        success: function(reorderlist) {
    	        	
    	      // Create a new row with the received data
    	          $.each(reorderlist, function(index, data) {
    	            var newRow = '<tr>' +
    	              '<td>' + data.productId + '</td>' +
    	              '<td>' + data.productName + '</td>' +
    	              '<td>' + data.sumOfProducts + '</td>' +
    	              '<td>' + data.productReorderLevel + '</td>' +
    	              '<td><button class="add-btn">Add</button></td>' +
    	              '</tr>';

    	            // Append the new row to the table body
    	            $('#reorder-level-table tbody').append(newRow); 
    	          });
    	          // Add event listeners to the dynamically created buttons
    	          var addButtons = $('#reorder-level-table .add-btn');
    	          addButtons.click(function() {
    	            var row = $(this).closest('tr');
    	            var productId = row.find('td:first').text();

    	            // Check if the product ID is already added
    	            if (addedProductIds.includes(productId)) {
    	              alert('This product is already added.');
    	              return;
    	            }

    	            addRowToSelectedTable(row);
    	            addedProductIds.push(productId);
    	          });


    	        },
    	        error: function() {
    	          alert('Error occurred while retrieving Products by categoryId.');
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
            var productId = row.find('td:first').text();

            // Check if the product ID is already added
            if (addedProductIds.includes(productId)) {
              alert('This product is already added.');
              return;
            }

            addRowToSelectedTable(row);
            addedProductIds.push(productId);
          });

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
		            var productId = row.find('td:first').text();

    	            // Check if the product ID is already added
    	            if (addedProductIds.includes(productId)) {
    	              alert('This product is already added.');
    	              return;
    	            }

    	            addRowToSelectedTable(row);
    	            addedProductIds.push(productId);
		          });

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
  var selectedTable = $('#products-table');
  var cells = row[0].cells;
  var field1 = cells[0].textContent;
  var field2 = cells[1].textContent;
  var field3 = cells[2].textContent;

  var newRow = '<tr>' +
    '<td>' + field1 + '</td>' +
    '<td>' + field2 + '</td>' +
    '<td>' + field3 + '</td>' +
    '<td><input type="number" value="1"></td>' +
    '<td><button class="delete-btn">Delete</button></td>' +
    '</tr>';

  selectedTable.find('tbody').append(newRow);

      }
  
 // Function to delete a row from the selected table
      function deleteRowFromSelectedTable(row) {
    	    var productId = row.find('td:first').text();
    	    console.log(productId);
    	    var index = addedProductIds.indexOf(productId);
    	    if (index > -1) {
    	      addedProductIds.splice(index, 1);
    	    }

    	    row.remove();
    	  
      }

      
     
    });
 //Getting the tables data to convert the data into JSON format. 
    function getTableData() {
    	  const table = document.getElementById('products-table');
    	  const tableData = [];
    	  
    	  for (let i = 1; i < table.rows.length; i++) {
    	    const row = table.rows[i];
    	    const rowData = {};

    	    rowData["productId"] = row.cells[0].innerText;
    	    rowData["quantity"] = row.cells[3].querySelector('input').value;

    	    tableData.push(rowData);
    	  }

    	  const jsonData = {
    	    "productsList": tableData
    	  };

    	  return jsonData;
    	}

// Ajax call to create an Inventory Indent.    
     function createIndent()
     {
     	var data = getTableData();
     	const jsonData = JSON.stringify(data);
     	$.ajax({
     		url:"createInventoryIndent",
     		method:"post",
     		data:{"jsonData":jsonData},
     		success:function(page)
     		{
     			$('#products-table tbody').empty();
     			alert('Succesfully created Indents to the Procurement');
     			 
     			
     		}
     	
     	});
     }
    