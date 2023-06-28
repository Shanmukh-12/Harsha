<head>
<link rel="stylesheet" href="./storeHome/styles/createStoreIndentStyles.css">
</head>
<script src="./storeHome/scripts/createStoreIndentScript.js"></script>
    <div class="container" align="center">
        <h1>Store Indents</h1><br><br>

	<form id="indentForm" onsubmit="createIndent();return false">
		<div>
			<label for="storeId">Store ID:</label> <select id="storeId"
				name="StoreID" required></select><br>
			<br>
		</div>
		<div>
			<label for="productId">Product ID:</label> <input type="text"
				id="productId" name="Indents_Products_ID"><br>
			<br>
		</div>
		<div>
			<label for="quantity">Quantity:</label> <input type="text"
				id="quantity" name="Indents_Products_Quantity"><br>
			<br>
		</div>
		<button type="button" id="addButton">Add</button>
		<br>
		<br>
		<table id="inventoryTable" border="1">
			<thead>
				<tr>
					<th id="productId">Product ID</th>
					<th id="quantity">Quantity</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr class="no-data">
					<td colspan="3">No data</td>
				</tr>
			</tbody>
		</table>
		<input type="submit">

	</form>
</div>
