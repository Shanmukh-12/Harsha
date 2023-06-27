
<head>
<link rel="stylesheet" href="./storeHome/styles/storeReturnsStyles.css">
</head>
<script src="./storeHome/scripts/storeReturnsScript.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<form method=post onsubmit="filterData(); return false">
	<div class="dropdowns">
		<div class="threeDropdowns">
			<div class="dropdown">
				<select id="store-id-dropdown">
				<option>Select Store Id</option>
				</select>
			</div>
			<div class="dropdown">
				<label>From Date :</label> <input type="date"
					id="return-date-dropdown-from" placeholder="Select Indent Date">
			</div>
			<div class="dropdown">
				<label>To Date :</label> <input type="date"
					id="return-date-dropdown-to" placeholder="Select Indent Date">
			</div>
		</div>

		<div class="threeDropdowns">
			<input type="reset">
			<button type="submit">Apply Filters</button>
		</div>
	</div>
</form>

<div class="container">

	<h1 class="text-center mb-4">Store Returns List</h1>

	<div class="search-container" align="right">
		<input type="text" id="searchInput"
			placeholder="Search Store Return ID">
		<button type="button" id="searchButton">Search</button>
	</div>
	<br>


</div>
<!-- Modal -->
<div class="modal fade" id="productsModal" tabindex="-1"
	aria-labelledby="productsModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg"
		style="max-width: calc(100% - 200px);">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="productsModalLabel">Products</h5>
				<button type="button" class="btn-close" data-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body" id="modalContent">
				<!-- Modal content will be loaded dynamically here -->
			</div>
		</div>
	</div>
</div>