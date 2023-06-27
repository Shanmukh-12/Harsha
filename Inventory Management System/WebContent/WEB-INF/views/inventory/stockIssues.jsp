
<head>

 <link rel="stylesheet" href="./inventoryHome/styles/stockIssuesStyles.css">
 <script src="./inventoryHome/scripts/stockIssuesScript.js"></script>


</head>
<form method="post" >
	<div class="dropdowns">
		<div class="twoDropdowns">
			<div class="dropdown">
				<select id="store-id-dropdown">
				</select>
			</div>
			<div class="dropdown">
				<select id="indent-status-dropdown">
					<option value="" selected>Select Issue Status</option>
					<option value="Approved">Approved</option>
					<option value="Pending">Pending</option>
				</select>
			</div>
		</div>
		<div class="twoDropdowns">
			<div class="dropdown">
				<label>From Date :</label>
				<input type="date" id="indent-date-dropdown-from"
					placeholder="Select Issue Date">
			</div>
			<div class="dropdown">
				<label>To  Date :</label>
				<input type="date" id="indent-date-dropdown-to"
					placeholder="Select Issue Date">
			</div>
		</div>
		
		<div class="twoDropdowns">
			<input type="reset">
			<button type="button" onclick="filterData()" >Apply Filters</button>
		</div>
	</div>
</form>


<div class="container">

	<h1 class="text-center mb-4">Stock Issues List</h1>

	<div class="search-container" align="right">
		<input type="text" id="searchInput" placeholder="Search StockIssue ID">
		<button type="button" id="searchButton">Search</button>
	</div><br>


</div>

<!-- Modal -->
<div class="modal fade" id="productsModal" tabindex="-1" aria-labelledby="productsModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" style="max-width: calc(100% - 200px);">
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
</body>
</html>