<head>
    <link rel="stylesheet" href="./HomeProcurement/styles/createGrnStyles.css">
</head>
<script src="./HomeProcurement/scripts/createGrnScript.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<!-- #f2f2f2 -->
<h2 style="font-size: 24px; margin-bottom: 10px;" align="center">Create
   GRN
</h2>
<br>
<div class="purchaseClass1">
  <label>Select VendorID:</label>
  <select id="vendorId">
    <option value="">Select Vendor</option>
    <option>20001</option>
    <option>20002</option>
    <option>20003</option>
  </select>
  <label>Select Expected From Date:</label>
  <input type="date" id="expectedDate">
  <label class="purchaseClass1">Select Expected To Date:</label>
  <input type="date" id="expectedDate1">
  <input type="button" value="filter" onclick="applyFilter()" style="color: white; background-color: green;">
  <input type="button" value="clear" onclick="clearSelection()" style="color: white; background-color: green;">
</div>
<!--  
<div class="purchaseClass1" >
<label >Select VendorID:</label>
<select id="vendorId" >
   <option value="">Select Vendor</option>
   <option>20001</option>
   <option>20002</option>
   <option>20003</option>
</select>
<br>
<label >Select Expected From Date:</label>
<input type="date" id="expectedDate">
<label class="purchaseClass1">Select Expected To Date:</label>
<input type="date" id="expectedDate1" >
<input type="button" value="filter" onclick="tk()"
   style="color: white; background-color: green;">
<input type="button" value="clear" onclick="clearSelection()"
   style="color: white; background-color: green;">
</div>
-->

<div style="display: flex; justify-content: center;">
   <div style="width: 45%">
      <label
         style="font-weight: bold; font-size: 16px; display: block; margin-bottom: 5px;">Purchase
      ID:</label> 
      <select id="purchaseId"
         style="padding: 10px; font-size: 14px; border-radius: 5px; border: 1px solid #ccc; width: 100%; margin-bottom: 10px;">
         <option value="5001">5001</option>
         <option value="5002">5002</option>
         <option value="5003">5003</option>
      </select>
   </div>
</div>
</div>
<br>
<br>
<h3 align="center">Purchased Items</h3>
<table id="purchasedItemsTable">
   <thead>
      <tr>
         <th>Product ID</th>
         <th>Ordered Quantity</th>
         <th>Received Quantity (till Now)</th>
         <th>Cost</th>
         <th>Action</th>
      </tr>
   </thead>
   <tbody>
      <!-- Data will be added dynamically -->
   </tbody>
</table>
<br>
<br>
<h3 align="center">GRN List</h3>
<table id="grnListTable">
   <thead>
      <tr>
         <th id="productId">Product ID</th>
         <th>Ordered Quantity</th>
         <th>Received Quantity (till Now)</th>
         <th>Cost</th>
         <th id="cgst">cgst</th>
         <th id="sgst">sgst</th>
         <th id="igst">igst</th>
         <th id="totalPrice">total price</th>
         <th id="batchNo">Batch No</th>
         <th id="quantity">current Received Quantity</th>
         <th id="bonus">bonus</th>
         <th id="mrp">MRP</th>
         <th>Action</th>
      </tr>
   </thead>
   <tbody>
   </tbody>
</table>
<br>
<br>
<div class="button-container">
   <button id="createGRNButton" onclick="createGRN()">Create GRN</button>
</div>