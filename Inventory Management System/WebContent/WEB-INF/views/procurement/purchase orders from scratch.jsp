<head>
<link rel="stylesheet"
	href="./HomeProcurement/styles/purchase orders from scratch Styles.css">
</head>
<script
	src="./HomeProcurement/scripts/purchase orders from scratch Script.js"></script>


<h3 align="center">Create Purchase Order(Scratch)</h3>
<br>
<br>
<table class="table bg-white rounded shadow-sm  table-hover"
	id="dataTable">
	<thead id="dt">
		<tr>
			<th>product_id</th>
			<th>purchase_order_quantity</th>
			<th>vendor_id</th>
			<th>negotiation_price</th>
			<th>Action</th>

		</tr>

	</thead>
	<tbody id="dataTable1">
	</tbody>
</table>


<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


<div class="form-table">
	<table>
		<tr>
			<td colspan="2"><label><b><h6>Add Products</h6></b></label></td>
		</tr>
		<tr>
			<td><label for="prcat">Product Categories</label></td>
			<td><select id="prcat" class="prClass" align="center">
					<option value="" disabled selected>Select a product
						Category</option>

			</select></td>
		</tr>
		<tr>
			<td><label for="prid">Product Id</label></td>
			<td><select id="prid" class="prClass" align="center">
					<option value="" disabled selected>Select a product id</option>

					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
			</select></td>
		</tr>
		<tr>
			<td><label for="qnty">purchase_order_quantity</label></td>
			<td><input type="number" id="qnty" name="qnty" required></td>
		</tr>
		<tr>
			<td><label for="vid">vendor_id</label></td>
			<td><select id="vid" class="vid" align="center"
				onchange="handleSelectChange()">
					<option value="" disabled selected>Select an option</option>
					<option>20001</option>
					<option>20002</option>
			</select> <span class="refresh-icon searchClass" onclick="enableOptions2()">&#x21bb;</span>
			</td>


		</tr>

		<tr>
			<td><label for="negPrice">negotiation_price</label></td>
			<td><input type="number" id="negPrice" name="negPrice" required></td>
		</tr>
		<tr>

			<td>
				<button id="addProductsId" class="addProductsClass">Add
					Product</button>
			</td>
			<td></td>
		</tr>
	</table>

</div>
<br>
<br>
<input type="button" value="Create Purchase" class="indentClass1"
	onclick="createpurchase()">
