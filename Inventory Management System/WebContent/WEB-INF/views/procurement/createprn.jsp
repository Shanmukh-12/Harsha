<head>
    <link rel="stylesheet" href="./HomeProcurement/styles/createPRNStyles.css">
</head>
<script src="./HomeProcurement/scripts/createPRNScript.js"></script>


<h1 align="center">Create Purchase Return</h1>
<br>
<label class="grnClass2">Select VendorID:</label>
<select id="vendorId" class="grnClass2">
	<option value="">Select Vendor</option>
	<option>20001</option>
	<option>20002</option>
	<option>20003</option>
</select>
<label class="grnClass2">GRN Cost</label>
<select id="cost" class="grnClass2">
	<option value="">Select Cost</option>
	<option>2000000</option>
	<option>2000001</option>
	<option>2000002</option>
</select>

<label class="grnClass1">Select Order Received From Date:</label>
<input type="date" id="fromDate" class="grnClass1">
<label class="grnClass1">Select Order Received To Date:</label>
<input type="date" id="toDate" class="grnClass1">
<input type="button" value="filter" onclick="tk()" class="grnClass3"
	style="color: white; background-color: green;">
<input type="button" value="clear" onclick="clearSelection()"
	class="grnClass3" style="color: white; background-color: green;">
<br>
<br>
<br>
<br>
<br>
<label class="prnClass">Select GRN ID:</label>
<select id="prnId" class="prnClass" align="center">
	<option value="">Select GRNID</option>
	<option>3001</option>
	<option>3002</option>
</select>
<br>
<br>
<br>

<label align="center" class="prnClass2"><h4>GRN Data</h4></label>
<label class="prnfilterClass2">Vendor id</label>
<input type="text" id="vid" class="prnfilterClass2">
<table class="table bg-white rounded shadow-sm  table-hover"
	id="myTable">
	<thead>
		<tr>
			<th scope="col">Product Id</th>
			<th scope="col">Batch NO</th>
			<th scope="col">Ordered Quantity</th>
			<th scope="col">Recieved Quantity</th>
			<th scope="col">Bonus</th>
			<th scope="col">Total Quantity</th>
			<th scope="col">Cost</th>
			<th scope="col">Action</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1</td>
			<td>45</td>
			<td>60</td>
			<td>30</td>
			<td>2</td>
			<td>32</td>
			<td>76836</td>
			<td><button onclick="moveToTable2(this)" disabled="true">Add</button></td>
		</tr>
		<tr>
			<td>2</td>
			<td>1</td>
			<td>60</td>
			<td>30</td>
			<td>2</td>
			<td>32</td>
			<td>768</td>
			<td><button onclick="moveToTable2(this)" disabled="true">Add</button></td>
		</tr>
	</tbody>
</table>
<br>
<br>
<label class="prnClass2"><h4>Returns List</h4></label>
<label class="prnfilterClass2">Enter Reason</label>
<input type="text" id="reason" name="reason" class="prnfilterClass2">

<table class="table bg-white rounded shadow-sm  table-hover"
	id="dataTable">
	<thead id="dt">
		<tr>

			<th scope="col">product_id</th>
			<th scope="col">batch_no</th>
			<th scope="col">Ordered Quantity</th>
			<th scope="col">Recieved Quantity</th>
			<th scope="col">Bonus</th>

			<th scope="col">Total quantity</th>
			<th scope="col">Cost</th>

			<th scope="col">Returning quantity</th>



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
<input type="button" value="Confirm Returns" class="prnClass"
	onclick="createpurchasereturn()">
