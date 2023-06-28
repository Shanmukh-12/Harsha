
<head>
<link rel="stylesheet"
	href="./storeHome/styles/createStoreReturnStyles.css">
</head>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./storeHome/scripts/createStoreReturnScript.js"></script>
<div class="container" align="center">
	<h1>Store Returns</h1>
	<br />
	<br />
	<div>
		<div>
			<label for="storeId">Store ID:</label>
			<select id="storeId" name="StoreID" required>
				<option>Select a Store</option>
			</select><br> <br>
		</div>
		<div>
			<label for="issueId">Issue ID:</label>
			<select id="issueId" name="issueId" required>
				<option>Select an Issue ID</option>
			</select><br>
			<br>
		</div>
	</div>

	<table>
		<tr>
			<th>Product Id</th>
			<th>Batch No</th>
			<th>Issued Quantity</th>
		</tr>
	</table>

	<form id="storeReturnsForm" onsubmit="createReturn(); return false;">
		<div>
			<label for="productId">Product ID:</label> <input type="text"
				id="productId" name="productId" /><br />
			<br />
		</div>
		<div>
			<label for="BatchNo">Batch NO:</label> <input type="text"
				id="BatchNo" name="BatchNo" /><br />
			<br />
		</div>
		<div>
			<label for="quantity">Quantity:</label> <input type="text"
				id="quantity" name="quantity" /><br />
			<br />
		</div>
		<div>
			<label for="reason">Reason:</label>
			<textarea id="reason" name="reason"></textarea>
			<br />
			<br />
		</div>

		<button type="button" id="addButton">Add</button>
		<br />
		<br />
		<table id="returnsTable" border="1">
			<thead>
				<tr>
					<th id="productId">Product ID</th>
					<th id="batchNo">Batch NO</th>
					<th id="quantity">Quantity</th>
					<th id="reason">Reason</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr class="no-data">
					<td colspan="3">No data</td>
				</tr>
			</tbody>
		</table>
		<input type="submit" />
	</form>
</div>
