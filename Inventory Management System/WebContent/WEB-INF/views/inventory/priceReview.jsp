<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Price Review</title>
  <style>
  #htag{
  position: relative;
  top: 10px;
  margin-left: 450px;

  }

 

    #adjustmenttable
    {
     display:flex;
    }

    #products-table {
      background-color: white;
      height: 50px;
      overflow-y: scroll;
      width:350px;
     margin-left:0px;
     margin-right:500px;
    }
  #addedproducts
    {
      position:relative;

      margin-top:62px;
      margin-left:150px;
    }
     #headingtag
    {
      position:relative;
      right:1000px;
    }
    
      table {
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    th, td {
    text-align:center;
      border: 1px solid black;
      padding: 8px;
      vertical-align: middle;
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
    

    form {
      max-width: 400px;
      margin-top: 60px;
       margin-left: 50px;
      padding: 20px;
      background-color: #ffffff;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }

    select,
    input[type="text"] {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
      margin-bottom: 10px;
    }

    button[type="submit"] {
      background-color: #4CAF50;
      color: #ffffff;
      padding: 10px 16px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
    }

    button[type="submit"]:hover {
      background-color: #45a049;
    }
     .error-message {
      color: red;
      font-size: 12px;
      margin-top: 5px;
    }
    
     .single-line-heading {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
    
     #updateButton
    {
       margin-left: 160px;
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
    
    #addButton
    {
        margin-left: 120px;
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
    
    
    
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script>
    function validateProductCategory() {
      var productCategory = document.getElementById("productcategoryid").value;
      var errorElement = document.getElementById("productcategory-error");
      
      if (productCategory === "") {
        errorElement.innerText = "Please select a Product Category";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateProductName() {
      var productName = document.getElementById("productnameid").value;
      var errorElement = document.getElementById("productname-error");
      
      if (productName === "") {
        errorElement.innerText = "Please select a Product Name";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateBatchNo() {
      var batchNo = document.getElementById("batchnoid").value;
      var errorElement = document.getElementById("batchno-error");
      
      if (batchNo === "") {
        errorElement.innerText = "Please select a Batch No";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateOriginalCost() {
      var originalCost  = document.getElementById("originalcostid").value;
      var errorElement = document.getElementById("originalcost-error");
      
      if (originalCost === "") {
        errorElement.innerText = "Please enter the Original Cost";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateModifiedCost() {
      var modifiedCost = document.getElementById("modifiedcostid").value;
      var errorElement = document.getElementById("modifiedcost-error");
      
      if (modifiedCost === "") {
        errorElement.innerText = "Please enter the Modified Cost";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateReason() {
      var reason = document.getElementById("reasonid").value;
      var errorElement = document.getElementById("reason-error");
      
      if (reason === "") {
        errorElement.innerText = "Please enter a Reason";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateForm() {
      validateProductCategory();
      validateProductName();
      validateBatchNo();
      validateOriginalCost();
      validateModifiedCost();
      validateReason();
      
      // Form is valid if no error messages exist
      var errorMessages = document.getElementsByClassName("error-message");
      for (var i = 0; i < errorMessages.length; i++) {
        if (errorMessages[i].innerText !== "") {
          return false;
        }
      }
      
      return true;
    }
    
    function addProduct() {
        validateProductName();
        validateOriginalCost();
        validateModifiedCost();
        validateReason();

      

        // Get input values
        var productname = document.getElementById("productnameid").value;
        var batchno = document.getElementById("batchnoid").value;
        var originalcost = document.getElementById("originalcostid").value;
        var modifiedcost = document.getElementById("modifiedcostid").value;
        var reason =  document.getElementById("reasonid").value;
        

        // Create new row in the table
        var tableBody = document.getElementById("products-table-body");
        var newRow = tableBody.insertRow();

        // Insert cells with product details
        var productnameCell = newRow.insertCell();
        productnameCell.innerHTML = productname;
        
        var batchnoCell = newRow.insertCell();
        batchnoCell.innerHTML = batchno;

        var originalcostCell = newRow.insertCell();
        originalcostCell.innerHTML = originalcost;
        
        var modifiedcostCell = newRow.insertCell();
        modifiedcostCell.innerHTML =  modifiedcost;
        
        var reasonCell = newRow.insertCell();
        reasonCell.innerHTML = reason;

        var deleteCell = newRow.insertCell();
        deleteCell.innerHTML = '<button class="delete-button" onclick="deleteProduct(this)">Delete</button>';

        // Clear input fields
        document.getElementById("productnameid").value = "";
        document.getElementById("batchnoid").value = "";
        document.getElementById("originalcostid").value = "";
        document.getElementById("modifiedcostid").value = "";
        document.getElementById("reasonid").value = "";
      }

      function deleteProduct(button) {
        var row = button.parentNode.parentNode;
        row.parentNode.removeChild(row);
      }
      
      
      
      $(document).ready(function(){
        	$.ajax({
        	     url :"getProductCategories",
        	     method :"post",
        	   success : function(data) {
                 $.each(data, function(index, category) {
                     var option = '<option value="' + category.productCategoryId + '">' + category.productCategoryName + '</option>';
                     $('#productcategoryid').append(option);
                 });
             },
             error: function() {
                 alert('Error occurred while retrieving categories.');
             }
        	    	  
        	});
        	    	 
    
          $("#productcategoryid").change(function() {
          	
              $.ajax({
                  url : "getProductStockData",
                  method : "post",
                  data:{
                  	
                  	categoryId : $("#productcategoryid").val()
                  	
                  },
                  success: function(data) {
                      $('#productnameid').empty();
                      var option = '<option >' + "select Product" + '</option>';
                      $('#productnameid').append(option);
                      $.each(data, function(index, products) {
                          var option = '<option value="' + products.productId + '">' + products.productName + '</option>';
                          $('#productnameid').append(option);
                      });
                  },
                  error: function() {
                      alert('Error occurred while retrieving products.');
                  }
              });
          });

          $("#productnameid").change(function() {
              $.ajax({
                  url : "getProductBatchNos",
                  method : "post",
                  data : {
                      "productId": $('#productnameid').val()
                  },
                  success: function(data) {
                      $('#batchnoid').empty();
                      var option = '<option >' + "select batchNo" + '</option>';
                      $('#batchnoid').append(option);
                      $.each(data, function(index, batches) {
                      	
                          var option = '<option value="' + batches.batchNo + '">' + batches.batchNo + '</option>';
                          $('#batchnoid').append(option);
                      });
                  },
                  error: function() {
                      alert('Error occurred while retrieving batchnos.');
                  }
              });
          });

          $("#batchnoid").change(function() {
              $.ajax({
                  url : "getProductQuantityOrPrice",
                  method : "post",
                  data:{
                  	"productId": $('#productnameid').val(),
                  	"batchNo": $('#batchnoid').val()
                  	
                  },
                  success : function(Price) {
                  	console.log("hi");
                  	$('#originalcostid').val(Price.costPrice);
                  },
                  error: function(error) {
                	  alert('Error occurred while retrieving cost price');
                  }
              });
          });
          
          function getTableData() {
        	  const table = document.getElementById('products-table');
        	  const tableData = [];
        	  
        	  for (let i = 1; i < table.rows.length; i++) {
        	    const row = table.rows[i];
        	    const rowData = {};

        	    rowData["product_id"] = row.cells[0].innerText;
        	    rowData["batch_no"] = row.cells[1].innerText;
        	    rowData["old_Price"] = row.cells[2].innerText;
        	    rowData["new_price"] = row.cells[3].innerText;
        	    rowData["review_desc"] = row.cells[4].innerText;
        	    

        	    tableData.push(rowData);
        	  }

        	  const jsonData = {
        			  
        	    "productsList": tableData
        	  };

        	  return jsonData;
        	}
        
        
        
        
        $("#updateButton").click(function() {
         	var data = getTableData();
           	const jsonData = JSON.stringify(data);
          		console.log(jsonData);
            $.ajax({
                url : "createPriceReview",
                method : "post",
                data:{"jsonData":jsonData},
                success : function(data) {
                	alert('Prices updated successfully');
                	
                },
                error: function(error) {
                	 alert('Error occurred while saving prices');
                }
            });
        });
          
        });
  </script>
  
</head>
<body>
<h1 id ="htag" class="single-line-heading" >Price Review</h1>
<div id="adjustmenttable">
<div style="margin-left:70px; margin-top:50px">

   <form onsubmit="return validateForm()">
    <label for="productcategoryid">Product Category:</label>
    <select id="productcategoryid" name="ProductCategory" onblur="validateProductCategory()">
      <option value="">Select Category</option>

    </select>
    <span id="productcategory-error" class="error-message"></span>
    
    <label for="productnameid">Product Name:</label>
    <select id="productnameid" name="ProductName" onblur="validateProductName()">
   
    
    </select>
    <span id="productname-error" class="error-message"></span>
    
    <label for="batchnoid">Batch No:</label>
    <select id="batchnoid" name="BatchNo" onblur="validateBatchNo()">
   
    </select>
    <span id="batchno-error" class="error-message"></span>
    
    <label for="originalcostid">Original Cost:</label>
    <input type="text" id="originalcostid" name="OriginalStock" onblur="validateOriginalCost()">
    <span id="originalcost-error" class="error-message"></span>
    
    <label for="modifiedcostid">Modified Cost:</label>
    <input type="text" id="modifiedcostid" name="ModifiedCost" onblur="validateModifiedCost()">
    <span id="modifiedcost-error" class="error-message"></span>
    
    <label for="reasonid">Reason:</label>
    <textarea id="reasonid" name="Reason" rows="4" cols="42" onblur="validateReason()"></textarea>
    <span id="reason-error" class="error-message"></span>
    
    <button type="button" id="addButton" onclick="addProduct()">Add Cost</button>
   </div>
   
   <div id="addedproducts">
    <h4 style="margin-left:100px; margin-bottom:20px;  font-weight:bold;">Price Review List</h4>
     <form>
  <table class="table bg-white rounded shadow-sm  table-hover" id="products-table">
    <thead>
      <tr>
        <th>Product ID</th>
        <th>Batch No</th>
        <th>Original Cost</th>
        <th>Modified Cost</th>
        <th>Reason</th>
         <th>Action</th>
      </tr>
    </thead>
    <tbody id="products-table-body">
    </tbody>
  </table>
  <button type="button" id="updateButton">Update</button>
</form>
   </div> 
    



 </div>
  </body>
</html>