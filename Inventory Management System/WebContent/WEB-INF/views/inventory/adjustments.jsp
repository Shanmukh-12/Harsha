<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Adjustments</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>  
   <link rel="stylesheet" href="./inventoryHome/styles/adjustmentsStyles.css">
<script src="./inventoryHome/scripts/adjustmentsScript.js"></script>

</head>
<body>
<h1 id ="htag" class="single-line-heading" >Adjustments</h1>

<div id="adjustmenttable">
<div id="adjustmentsformdivid">

   <form onsubmit="return validateForm()" id="adjustments-form">
    <label for="productcategoryid">Product Category:</label>
    <select id="productcategoryid" name="ProductCategory" onblur="validateProductCategory()">
      <option value="" >Select Category</option>

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
    
    <label for="originalstockid">Original Stock:</label>
    <input type="text" id="originalstockid" name="OriginalStock" onblur="validateOriginalStock()" readonly>
    <span id="originalstock-error" class="error-message"></span>
    
    <label for="currentstockid">Current Stock:</label>
    <input type="text" id="currentstockid" name="CurrentStock" onblur="validateCurrentStock()">
    <span id="currentstock-error" class="error-message"></span>
    
    <label for="reasonid">Reason:</label>
    <textarea id="reasonid" name="Reason" rows="4" cols="45" onblur="validateReason()"></textarea>
    <span id="reason-error" class="error-message"></span>
    
    <button type="button" id="addButton" onclick="addProduct()">Add Stock</button>
    </form>
   </div>
   
   <div id="addedproducts">
   <div id="headingid">
    <h4><b>Adjustments List</b></h4>
    </div>
     <form id="added-products-form">
  <table class="table bg-white rounded shadow-sm  table-hover" id="products-table">
    <thead>
      <tr>
        <th>Product ID</th>
        <th>Batch No</th>
        <th>Original Stock</th>
        <th>Current Stock</th>
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