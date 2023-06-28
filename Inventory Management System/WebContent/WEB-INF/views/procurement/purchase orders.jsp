<head>
    <link rel="stylesheet" href="./HomeProcurement/styles/purchaseOrdersStyles.css">
</head>
<script src="./HomeProcurement/scripts/purchaseOrdersScript.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
      <h1 align="center">Create Purchase</h1><br>
       <label for="indentStatus" class="indentClass5">Indent Status:</label>
                <select id="indentStatus" name="indentStatus" class="indentClass5">
                    <option value="Active">Active</option>
                    <option value="Closed"> Closed</option>
                    <!-- Add options dynamically from your data source -->
                </select>
      <br><br>
      <label class="indentClass">Select Indent From Date:</label>
      <input type="date" id="fromDate" class="indentClass">
	
	
	
	

                <label for="toDate" class="indentClass">Select Indent To Date:</label>
                <input type="date" id="toDate" name="toDate" class="indentClass">
                <button type="button" class="filterButton indentClass6">Apply Filters</button>
                <button onclick="clearSelections()" class="filterButton indentClass6">Clear</button>
                
                <br><br><br>
      
      <label class="indentClass1">Select Indent ID:</label>
      <select id="indentId" class="indentClass1" align="center">
         <option value="">Select Indent id</option>
      </select>
      <br><br><br><br>
      <label class="indentClass4">
         <h4>Indent List</h4>
      </label>
      <br><br>
      <table class="table bg-white rounded shadow-sm  table-hover"  id="indentTable">
         <thead id="indentTable">
            <tr>
             
               <th scope="col">Product Id</th>
               <th scope="col">Product Name</th>
               <th scope="col"> Quantity</th>
               <th scope="col">Action</th>
            </tr>
         </thead>
         <tbody id="tbody">
            <tr>
               
               <td>1</td>
               <td>Television</td>
               <td>200</td>
              <td><button onclick="moveToTable2(this)" disabled="true">Add</button></td>
            </tr>
            <tr>
             
               <td>2</td>
               <td>santoor</td>
               <td>200</td>
              <td><button onclick="moveToTable2(this)" disabled="true">Add</button></td>
            </tr>
         </tbody>
      </table>
      <br><br>
      
      <label class="indentClass4">
         <h4>Purchase List</h4>
      </label>
      <br>
      <label class="indentClass3">vendor id</label>
      <select id="vendorId" class="indentClass3" onchange="handleSelectChange()" >
   <option value="">Select Vendor</option>
      </select>     
       <span class="refresh-icon indentClass3" onclick="enableOptions2()">&#x21bb;</span>
      <br>
      <table class="table bg-white rounded shadow-sm  table-hover" id="dataTable" >
         <thead id="dt">
            <tr>
            <th >product_id</th>
               <th>product_name</th>
               <th>indent quantity</th>
               <th>purchase_order_quantity</th>
               <th>negotiation_price</th>
               
               
               <th>Action</th>
            </tr>
         </thead>
         <tbody id="dataTable1">
         </tbody>
      </table>
      <br><br><br><br>
      
      <input type="button"  value="Create Purchase" class="indentClass4" onclick="createpurchase()">
      
