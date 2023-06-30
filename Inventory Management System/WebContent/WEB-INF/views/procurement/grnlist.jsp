<head>
<link rel="stylesheet" href="./HomeProcurement/styles/grnListStyles.css">
</head>
<script src="./HomeProcurement/scripts/grnListScript.js"></script>
<!-- Include jQuery library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Include Bootstrap CSS and JS -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



<h1 class="text-center mb-4">GRN's Data</h1>

<form onsubmit="applyFilter(); return false">
<div class="grnFilters">

	<div>
		<label>Select VendorID:</label>
		<select id="vendorId">
			<option>Select Vendor Id</option>
		</select><br><br>
	</div>
	<div class="grnDate">
		<label>Received From Date:</label> <input type="date" id="fromDate">
		<label>Received To Date:</label> <input type="date" id="toDate"><br><br>
	</div>
	<div>
	<input type="submit" value="filter" style="color: white; background-color: green;">
	<input type="reset" style="color: white; background-color: green;">
	</div>
</div>
</form>


<form method="Get">
	<div class="container">
		<div class="issues-block">
			<h4 class="store-indent-id" id="grnid">
				GRN ID: <span class="bold">5001</span>
			</h4>
			<div class="issue-details">
				<span class="label">Vendor ID:</span><span>A2345</span> <span
					class="label">Purchase Order ID:</span><span>5001</span> <span
					class="label">GRN Received Date:</span><span>2023-12-29</span> <span
					class="label ">GRN Cost</span> <span>123EW</span>
			</div>
			<div>
				<button type="button" value="view products" class="btn-issues"
					onclick="">View Products</button>
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