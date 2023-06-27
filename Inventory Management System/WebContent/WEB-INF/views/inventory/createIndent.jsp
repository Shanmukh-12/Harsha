<!DOCTYPE html>
<html>
<head>
  <title>Create Indent</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  
  <link rel="stylesheet" href="./inventoryHome/styles/adjustmentsList.css">
  <script src="./inventoryHome/scripts/adjustmentsListScript.js"></script>
</head>
<body>
  <div id="htag">
    <h2 align="center">Create Indent</h2>
  </div>

  <div id="products-dropdown">
    <label for="product-category" id="product-category-heading">Product Category </label>
    <select id="product-category" >
      <option>select category</option>
    </select>
  </div>

  <div class="table-container">
    <div id="reorder-level-container">
      <h3 align="center">Reorder Level Details</h3>
      <div id="first_table">
        <table id="reorder-level-table">
          <thead>
            <tr>
              <th>Product ID</th>
              <th>Product Name</th>
              <th>Stock</th>
              <th>Re-Order Level</th>
              <th>Add</th>
            </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
    </div>

    <div id="product-details-container">
      <h3 align="center">Products Details</h3>
      <div id="second_table">
        <table id="Product-details-table">
          <thead>
            <tr>
              <th>Product ID</th>
              <th>Product Name</th>
              <th>Stock</th>
              <th>Add</th>
            </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
    </div>
  </div>

  <h2 align="center">Indents List</h2>
     <div id="addedproducts" >
       <form id="indentForm" onsubmit="createIndent();return false" method="post" >
          <table class="table bg-white rounded shadow-sm  table-hover" id="products-table">
           <thead>
              <tr>
                 <th id="productId">Product ID</th>
                 <th>Product Name</th>
                 <th >Stock</th>
                 <th id="quantity">Required Stock</th>
                 <th>Action</th>
             </tr>
           </thead>
           <tbody></tbody>
          </table>
          <button type="submit" id="createIndentButtonId">Create</button>
  </form>
</div>
</body>
</html>