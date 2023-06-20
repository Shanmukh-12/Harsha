<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Adjustments</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>  
  <style>
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
      margin-left:150px;
      margin-top:55px;
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
      
    vertical-align: middle; /* Adjust the alignment as needed (top, middle, bottom) */
  
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
  </style>
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
    
    function validateOriginalStock() {
      var originalStock = document.getElementById("originalstockid").value;
      var errorElement = document.getElementById("originalstock-error");
      
      if (originalStock === "") {
        errorElement.innerText = "Please enter the Original Stock";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateCurrentStock() {
      var currentStock = document.getElementById("currentstockid").value;
      var errorElement = document.getElementById("currentstock-error");
      
      if (currentStock === "") {
        errorElement.innerText = "Please enter the Current Stock";
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
      validateOriginalStock();
      validateCurrentStock();
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
        validateOriginalStock();
        validateCurrentStock();
        validateReason();

      

        // Get input values
        var productname = document.getElementById("productnameid").value;
        var originalstock = document.getElementById("originalstockid").value;
        var currentstock = document.getElementById("currentstockid").value;
        var reason =  document.getElementById("reasonid").value;
        

        // Create new row in the table
        var tableBody = document.getElementById("products-table-body");
        var newRow = tableBody.insertRow();

        // Insert cells with product details
        var productnameCell = newRow.insertCell();
        productnameCell.innerHTML = productname;

        var originalstockCell = newRow.insertCell();
        originalstockCell.innerHTML = originalstock;
        
        var currentstockCell = newRow.insertCell();
        currentstockCell.innerHTML = currentstock;
        
        var reasonCell = newRow.insertCell();
        reasonCell.innerHTML = reason;

        var deleteCell = newRow.insertCell();
        deleteCell.innerHTML = '<button class="delete-button" onclick="deleteProduct(this)">Delete</button>';

        // Clear input fields
        document.getElementById("productcategoryid").value = "";
        document.getElementById("batchnoid").value = "";
        document.getElementById("productnameid").value = "";
        document.getElementById("originalstockid").value = "";
        document.getElementById("currentstockid").value = "";
        document.getElementById("reasonid").value = "";
      }

      function deleteProduct(button) {
        var row = button.parentNode.parentNode;
        row.parentNode.removeChild(row);
      }
      
      $(document).ready(function(){
      	$.ajax({
      	     url :"getProductCategories",
      	     method :"get",
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
                success : function(Stock) {
                	console.log("hi");
                	$('#originalstockid').val(Stock.stock);
                },
                error: function(error) {
                    // Handle error
                }
            });
        });
        
      });
    </script>
</head>
 <body> 
<h1 style="margin-left:450px ; margin-top:10px">Adjustments</h1>
<div id="adjustmenttable">

<div style="margin-left:70px; margin-top:40px">

   <form onsubmit="return validateForm()" method="post" >
    <label for="productcategoryid">Product Category:</label>
    <select id="productcategoryid" name="ProductCategory" onblur="validateProductCategory()">
      <option value="">Select Category</option>
 
    </select>
    <span id="productcategory-error" class="error-message"></span>
    
    <label for="productnameid">Product Name:</label>
    <select id="productnameid" name="ProductName" onblur="validateProductName()">
    <option value="">Select Product</option>
    </select>
    <span id="productname-error" class="error-message"></span>
    
    <label for="batchnoid">Batch No:</label>
    <select id="batchnoid" name="BatchNo" onblur="validateBatchNo()">
    <option value="">Select BatchNo</option>
    </select>
    <span id="batchno-error" class="error-message"></span>
    
    <label for="originalstockid">Original Stock:</label>
    <input type="text" id="originalstockid" name="OriginalStock" onblur="validateOriginalStock()">
    <span id="originalstock-error" class="error-message"></span>
    
    <label for="currentstockid">Current Stock:</label>
    <input type="text" id="currentstockid" name="CurrentStock" onblur="validateCurrentStock()">
    <span id="currentstock-error" class="error-message"></span>
    
    <label for="reasonid">Reason:</label>
    <textarea id="reasonid" name="Reason" rows="4" cols="42" onblur="validateReason()"></textarea>
    <span id="reason-error" class="error-message"></span>
    
    <button type="button" style="margin-left: 120px;  background-color: #4CAF50; color:white; border: none; border-radius: 5px; width: 120px; height: 40px;" onclick="addProduct()">Update Stock</button>
  </form>
  </div>
   
   <div id="addedproducts">

         <h4 style="margin-left:100px; margin-bottom:20px; font-weight:bold">Adjustments List</h4>
     <form>
  <table class="table bg-white rounded shadow-sm  table-hover" id="products-table">
    <thead>
      <tr>
        <th>Product Name</th>
        <th>Original Stock</th>
        <th>Current Stock</th>
        <th>Reason</th>
         <th>Action</th>
      </tr>
    </thead>
    <tbody id="products-table-body">
    </tbody>
  </table>
</form>
   </div> 

 </div>
 
 </body>
 </html>