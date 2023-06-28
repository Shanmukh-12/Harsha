<head>
<link rel="stylesheet"
	href="./HomeProcurement/styles/purchasesListStyles.css">
</head>
<script src="./HomeProcurement/scripts/purchasesListScript.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://unpkg.com/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>



<label class="purchaseClass2">Select VendorID:</label>
<select id="vendorId" class="purchaseClass2">
	<option value="">Select Vendor</option>
</select>


<label class="purchaseClass1">Select Expected From Date:</label>
<input type="date" id="expectedDate" class="purchaseClass1">
<label class="purchaseClass1">Select Expected To Date:</label>
<input type="date" id="expectedDate1" class="purchaseClass1">
<input type="button" value="filter" onclick="tk()"
	class="purchaseClass1" style="color: white; background-color: green;">

<input type="button" value="clear" onclick="clearSelection()"
	class="purchaseClass1" style="color: white; background-color: green;">

<br>
<br>
<br>
<br>
<input type="text" id="purchaseorderid" placeholder="Enter purchase id"
	class="searchClass" style="width: 140px;">
<input type="button" onclick="tk3()" value="search" class="searchClass">
<span class="refresh-icon searchClass" onclick="refresh()">&#x21bb;</span>


<form method="Get">
	<div class="container">
		<h1 class="text-center mb-4">Purchases Data</h1>

		<div class="issues-block">
			<h4 class="store-indent-id" id="PurchasesId">
				Purchase ID: <span class="bold">5001</span>
			</h4>
			<div class="issue-details">
				<span class="label">Vendor ID:</span><span>A2345</span> <span
					class="label">Purchase Order Expected Date:</span><span>2023-12-29</span>
				<span class="label ">Purchase Order amount</span> <span>123EW</span>
			</div>


			<div>
				<button type="button" value="view products" class="btn-issues"
					onclick="tk2(5001)">View Products</button>
			</div>
		</div>

	</div>
</form>
<!-- Modal -->
<div class="modal fade" id="productsModal" tabindex="-1"
	aria-labelledby="productsModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg"
		style="max-width: calc(100% - 200px);">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="productsModalLabel">Products</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body" id="modalContent">
				<!-- Modal content will be loaded dynamically here -->
			</div>
		</div>
	</div>
</div>
