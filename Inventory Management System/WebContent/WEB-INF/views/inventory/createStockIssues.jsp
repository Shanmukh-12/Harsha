<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create Store Issue</title>
  
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
     <link rel="stylesheet" href="./inventoryHome/styles/createStockIssuesStyles.css">
     <script src="./inventoryHome/scripts/createStockIssuesScript.js"></script>
</head>
<body>
  <h2 id="htag">Create Stock Issue</h2>
    <div id="dropdown-div">
   
    <div id="stores-dropdown" align="right" >
    <label for="stores-list">Stores List: </label>
    <select id="stores-list">
    </select>
    </div>
    
	   <div id="indents-dropdown" align="right">
	    <label for="indents-list">Indents List: </label>
	    <select id="indents-list">
	    </select>
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

  

  <h2 id="h1tag">Indents List</h2>

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
 
</body>
</html>