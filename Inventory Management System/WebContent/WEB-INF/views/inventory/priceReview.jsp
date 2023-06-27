<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Price Review</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
   <link rel="stylesheet" href="./inventoryHome/styles/priceReviewStyles.css">
    <script src="./inventoryHome/scripts/priceReviewScript.js"></script>
  
  
</head>
<body>
<h1 id ="htag" class="single-line-heading" >Price Review</h1>
<div id="adjustmenttable">
<div id="form-div-id" >
   <form onsubmit="return validateForm()">
    <label for="productcategoryid">Product Category:</label>
    <select id="productcategoryid" name="ProductCategory" onblur="validateProductCategory()">
      <option value="" selected disabled>Select Category</option>

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
    <input type="text" id="originalcostid" name="OriginalStock" onblur="validateOriginalCost()" readonly>
    <span id="originalcost-error" class="error-message"></span>
    
     <label for="maxretailpriceid">Maximum Retail Price(MRP):</label>
    <input type="text" id="maxretailpriceid" name="MRP" readonly>
    <span id="maxretailprice-error" class="error-message"></span>
    
    <label for="modifiedcostid">Modified Cost:</label>
    <input type="text" id="modifiedcostid" name="ModifiedCost" onblur="validateModifiedCost()">
    <span id="modifiedcost-error" class="error-message"></span>
    
    <label for="reasonid">Reason:</label>
    <textarea id="reasonid" name="Reason" rows="4" cols="42" onblur="validateReason()"></textarea>
    <span id="reason-error" class="error-message"></span>
    
    <button type="button" id="addButton" onclick="addProduct()">Add Cost</button>
    </form>
   </div>
   
   <div id="addedproducts">
   <div id="heading-price-review">
    <h4>Price Review List</h4>
    </div>
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