<head>
<link rel="stylesheet"
	href="./HomeProcurement/styles/purchaseOrderDataStyles.css">
</head>
<script src="./HomeProcurement/scripts/purchaseOrderDataScript.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<h1 align="center">Purchases Orders Data</h1>
<br>
<br>

<label class="purchaseClass1">Select VendorID:</label>
<select id="vendorId" class="purchaseClass1" align="center">
	<option>20001</option>
	<option>20002</option>
</select>

<label class="purchaseClass1">Select ExpectedDate:</label>
<input type="date" id="expectedDate" class="purchaseClass1">
<br>
<br>

<label class="PurchasesClass">Select Purchase ID:</label>
<select id="PurchasesId" class="PurchasesClass" align="center">

</select>
<br>
<br>
<table class="table bg-white rounded shadow-sm  table-hover">
	<thead>
		<tr>
			<th scope="col">product_id</th>
			<th scope="col">purchase_order_quantity</th>
			<th scope="col">purchase_order_date</th>
			<th scope="col">Vendor ID</th>
			<th scope="col">Expected Delivery</th>
			<th scope="col">Negotiation Price</th>
			<th scope="col">Status</th>
		</tr>
	</thead>
	<tbody id="purchaseBody">
		<tr>


		</tr>
	</tbody>
</table>
