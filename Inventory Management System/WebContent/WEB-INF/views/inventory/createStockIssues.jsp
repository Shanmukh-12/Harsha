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
    <form>
      <table class="table bg-white rounded shadow-sm table-hover" id="products-table">
        <thead>
          <tr>
            <th>Indent ID</th>
          <th>Product ID</th>
          <th>Product Name</th>
          <th>Requested Quantity</th>
          <th>Issued Quantity</th>
          <th>Action</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
      <button type="button" id="addButton" >Issue Stock</button>
    </form>
  </div>
  
  
   <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    var addButtons;
  $(document).ready(function(){
	  
	  
    	$.ajax({
    	     url :"getStoreIds",
    	     method :"post",
    	     success: function(data) {
                 $('#stores-list').empty();
                 var option = '<option >' + "select StoreID" + '</option>';
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

    
    $("#indents-list").change(function(){
    	var data = {}
        data["indentId"]=$("#indents-list").val();
    	$.ajax({
   	     url :"getStoreIndentProductsListData",
   	     method :"post",
   	     data: {"indentId":JSON.stringify(data) 
   	     },
   	  success : function(prodlist) {
   		$('#product-details-table-body').html('');
	   		$.each(prodlist, function(index, data1) {
	   			
	   			
	   			console.log(index);
	   			console.log(data1);
	   			var newRow = '<tr>' +
	   	        '<td>' + $("#indents-list").val()+ '</td>' +
	   	        '<td>' + data1.productId + '</td>' +
	   	        '<td>' + data1.productName + '</td>' +
	   	        '<td>' + data1.quantity + '</td>' +
	   	        '<td><button class = "add-btn" >Add</button></td>' +
	   	        '</tr>';

	   	    // Append the new row to the table body
	   	    $('#product-details-table tbody').append(newRow);
	   		  console.log("Completed");
	   		});
	
	   	 var addButtons = $('#product-details-table .add-btn');
         addButtons.click(function() {
           var row = $(this).closest('tr');
           addRowToSelectedTable(row);
         });
         
	          
   	  },
        error: function() {
            alert('Error occurred while retrieving Products Data');
        }
   	    	  
   	});
    	
    	
 	}); 
    	
    $(document).on('click', '#products-table .delete-btn', function() {
        var row = $(this).closest('tr');
        deleteRowFromSelectedTable(row);
      });
     
        function addRowToSelectedTable(row) {
      	  console.log("hello");
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
    

	    	    
  </script>
  
  
  
  
</body>
</html>