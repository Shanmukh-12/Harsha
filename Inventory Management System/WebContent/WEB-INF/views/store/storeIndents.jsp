<style>
.container {
	max-width: 800px;
	margin: 100 auto;
	padding-top: 10px;
	margin-left: 170px;
}

.issues-block {
	border: 1px solid #ccc;
	border-radius: 10px;
	padding: 20px;
	margin-bottom: 20px;
	background-color: #fff;
}

.issues-block:hover {
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	background-color: #d8f2f0;
}

.issue-details {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

.label {
	font-weight: bold;
}

.btn-issues {
	border-radius: 6px;
	background-color: #4CAF50;
	border: none;
	color: white;
	cursor: pointer;
	font-weight: bold;
	box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
	margin-left: 600px;
}

.btn-issues:hover {
	background-color: #4CAF50;
	border-color: #4CAF50;
}

.bold {
	font-weight: bold;
	font-size: 25px;
}
/* Custom styles for modal */
.modal-content {
	background-color: #f1f1f1;
	border-radius: 10px;
}

.modal-header {
	background-color: #c1efde;
	border-radius: 10px 10px 0 0;
}

.modal-title {
	color: #333;
	font-weight: bold;
}

.modal-body {
	padding: 20px;
}

.dropdowns {
	display: flex;
	padding-left: 300px;
    height: 100px;
}

.dropdown {
	display: inline-block;
	margin-right: 10px;
}

select, button, #searchInput, input {
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.twoDropdowns {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="./storeHome/scripts/storeIndentScript.js"></script>
<form method=post onsubmit="filterData(); return false">
	<div class="dropdowns">
		<div class="twoDropdowns">
			<div class="dropdown">
				<select id="store-id-dropdown">
				</select>
			</div>
			<div class="dropdown">
				<select id="indent-status-dropdown">
					<option value="" selected>Select Indent Status</option>
					<option value="Active">Active</option>
					<option value="Inactive">Inactive</option>
				</select>
			</div>
		</div>
		<div class="twoDropdowns">
			<div class="dropdown">
				<label>From Date :</label>
				<input type="date" id="indent-date-dropdown-from"
					placeholder="Select Indent Date">
			</div>
			<div class="dropdown">
				<label>To  Date :</label>
				<input type="date" id="indent-date-dropdown-to"
					placeholder="Select Indent Date">
			</div>
		</div>
		
		<div class="twoDropdowns">
			<input type="reset">
			<button type="submit" >Apply Filters</button>
		</div>
	</div>
</form>

<div class="container">

	<h1 class="text-center mb-4">Store Indents List</h1>

	<div class="search-container" align="right">
		<input type="text" id="searchInput" placeholder="Search Indent ID">
		<button type="button" id="searchButton">Search</button>
	</div><br>


</div>
<!-- Modal -->
<div class="modal fade" id="productsModal" tabindex="-1"
	aria-labelledby="productsModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg"
		style="max-width: calc(100% - 200px);">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="productsModalLabel">Products</h5>
               <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body" id="modalContent">
				<!-- Modal content will be loaded dynamically here -->
			</div>
		</div>
	</div>
</div>