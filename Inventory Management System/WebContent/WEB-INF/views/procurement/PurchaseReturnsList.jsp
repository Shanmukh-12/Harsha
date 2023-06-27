<head>
    <link rel="stylesheet" href="./HomeProcurement/styles/PurchaseReturnsListStyles.css">
</head>
<script src="./HomeProcurement/scripts/PurchaseReturnsListScript.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://unpkg.com/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

	<label class="purchaseClass2">Vendor Id</label>
	<select id="vendorId" class="purchaseClass2" >
	<option value="">Select Vendor</option>
		<option>20001</option>
		<option>20002</option>
	</select>
	<label class="purchaseClass2">Grn value</label>
	<select id="prnvalue" class="purchaseClass2" >
		<option value="">Select Grn Value</option>
	
		<option>4000</option>
   </select>
   
	<label class="purchaseClass1">Return Date From</label>
	<input type="date" id="returnDate" class="purchaseClass1">
		<label class="purchaseClass1">Return Date To</label>
	<input type="date" id="returnDate1" class="purchaseClass1">
<input type="button" value="filter" onclick="tk()" class="purchaseClass1" style="color: white; background-color: green;">
	      <input type="button" value="clear" onclick="clearSelection()" class="purchaseClass1" style="color: white; background-color: green;">

	<br><br><br><br>
	<input type="text" id="purchasereturnid" placeholder="Enter purch ret id" class="searchClass" style="width:140px;">
	<input type="button" onclick="tk3()" value="search" class="searchClass">
	<span class="refresh-icon searchClass" onclick="tk()">&#x21bb;</span>
	
	
	
<form method="Get">

    <div class="container" >
    
        <h1 class="text-center mb-4" align="center">Purchase Returns Data</h1>
        
            <div class="issues-block">
                <h4 class="store-indent-id" id="PurchasesReturnId">Purchase Return ID: <span class="bold">5001</span></h4>
                <div class="issue-details" >
                    <span class="label">Vendor ID:</span><span >A2345</span>
                        <span class="label">Grn ID:</span><span >20001</span>
                         <span class="label">Purchase Return Date:</span><span >2023-12-29</span>
                        
                        <span class="label ">Grn Cost:</span> <span >123EW</span>
                        <span class="label ">Reason:</span> <span >123EW</span>
                </div>
                
                
                <div>
                <button type="button" value="view products" class="btn-issues" onclick="tk2(5001)">View Products</button>
                </div>
            </div>
         
    </div>
</form>
<!-- Modal -->
<div class="modal fade" id="productsModal" tabindex="-1" aria-labelledby="productsModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" style="max-width: calc(100% - 200px);">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productsModalLabel">Products</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="modalContent">
                <!-- Modal content will be loaded dynamically here -->
            </div>
        </div>
    </div>
</div>
