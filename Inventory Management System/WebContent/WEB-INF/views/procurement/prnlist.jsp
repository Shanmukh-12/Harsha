<head>
<link rel="stylesheet" href="./HomeProcurement/styles/prnListStyles.css">
</head>
<script src="./HomeProcurement/scripts/prnListScript.js"></script>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<h1 align="center">PRN Data</h1>
<br>
<br>
<label class="prnClass">Return Date</label>
<input type="date" id="returnDate" class="prnClass">

<label class="prnClass">Vendor Id</label>
<select id="vendorId" class="prnClass">
	<option>20001</option>
</select>

<label class="prnClass">Grn value</label>
<select id="prnvalue" class="prnClass">
	<option>4000</option>

</select>
<br>
<br>
<label class="prnClass">Select PRN ID:</label>
<select id="prnId" class="prnClass" align="center">

</select>
<br>




<br>
<br>
<label class="prnfilterClass2">Vendor ID</label>
<input type="text" value="678" id="vid" name="vid"
	class="prnfilterClass2">

<label class="prnfilterClass1">Return Date</label>
<input type="text" id="rdt" name="rdt" class="prnfilterClass1">
<br>
<table class="table bg-white rounded shadow-sm  table-hover"
	id="dataTable">
	<thead>
		<tr>
			<th scope="col" width="50">#</th>
			<th scope="col">GRN Id</th>
			<th scope="col">Product Id</th>
			<th scope="col">Quantity</th>
			<th scope="col">Cost</th>
			<th scope="col">purchase_return_description</th>
		</tr>
	</thead>
	<tbody id="dataBody">
		<tr>


		</tr>
	</tbody>
</table>
<br>
<br>
<label class="prnfilterClass2">Total Value</label>
<input type="text" value="678" id="tv" name="tv" class="prnfilterClass2">

