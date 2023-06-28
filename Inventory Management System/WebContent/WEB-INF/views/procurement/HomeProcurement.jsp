<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link rel="stylesheet"
	href="./HomeProcurement/styles/homeProcurementStyles.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
<script src="./HomeProcurement/scripts/homeProcurementScript.js"></script>

<title>Procurement</title>

</head>

<body>
	<div class="d-flex" id="wrapper">
		<!-- Sidebar -->
		<div class="bg-white" id="sidebar-wrapper">
			<div
				class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">
				<i class="fas fa-user-secret me-2"></i>Procurement
			</div>
			<div class="list-group list-group-flush my-3">
				<div class="dropdown">
					<button
						class="list-group-item list-group-item-action bg-transparent second-text fw-bold"
						onclick="Myfunction2('warehousestock')">
						<i class="fas fa-project-diagram me-2"></i>WareHouse Stock
					</button>
					<button
						class="list-group-item list-group-item-action bg-transparent second-text fw-bold"
						id="indents" onclick="indentsFunction('indentsButton')">
						<i class="fas fa-chart-line me-2"></i>Indents
					</button>
					<button
						class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
						role="button" id="purchaseDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">
						<i class="fas fa-gift me-2"></i>Purchases
					</button>
					<ul class="dropdown-menu" aria-labelledby="purchaseDropdown">
						<li><button class="dropdown-item" id="purchaseOrderData"
								onclick="Myfunction('purchaseOrderData')">Purchase
								Orders Data</button></li>
						<li><button class="dropdown-item" id="createPurchaseOrder"
								onclick="Myfunction('createPurchaseOrder')">Create
								Purchase Order</button></li>
						<li><button class="dropdown-item"
								id="createNewPurchaseOrders"
								onclick="Myfunction('createNewPurchaseOrders')">Create
								New Purchase Order</button></li>
					</ul>
				</div>

				<div class="dropdown">
					<button
						class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
						role="button" id="grnDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">
						<i class="fas fa-gift me-2"></i>GRN
					</button>
					<ul class="dropdown-menu" aria-labelledby="grnDropdown">
						<li><button class="dropdown-item" id="grnData"
								onclick="Myfunction('grnData')">GRN Data</button></li>
						<li><button class="dropdown-item" id="createGRN"
								onclick="Myfunction('createGRN')">Create GRN</button></li>
						<li><button class="dropdown-item"></button></li>
					</ul>
				</div>
				<div class="dropdown">
					<button
						class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
						role="button" id="returnsDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">
						<i class="fas fa-gift me-2"></i>Purchase Returns
					</button>
					<ul class="dropdown-menu" aria-labelledby="returnsDropdown">
						<li><button class="dropdown-item" id="prnData"
								onclick="Myfunction('prnData')">PRN Data</button></li>
						<li><button class="dropdown-item" id="createPRN"
								onclick="Myfunction('createPRN')">Create PRN</button></li>
					</ul>
				</div>

				<div class="dropdown">
					<button
						class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
						role="button" id="returnsDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">
						<i class="fas fa-gift me-2"></i>Products
					</button>
					<ul class="dropdown-menu" aria-labelledby="returnsDropdown">
						<li><button class="dropdown-item" id="addHSN"
								onclick="Myfunction('addHSN')">Add HSN</button></li>
						<li><button class="dropdown-item" id="addProductCategory"
								onclick="Myfunction('addProductCategory')">Add Product
								Category</button></li>
						<li><button class="dropdown-item" id="addProduct"
								onclick="Myfunction('addProduct')">Add Product</button></li>
						<li><button class="dropdown-item" id="del"
								onclick="Myfunction('del')">del</button></li>
					</ul>
				</div>
				<button
					class="list-group-item list-group-item-action bg-transparent text-danger fw-bold"
					onclick="logout()">
					<i class="fas fa-power-off me-2"></i>Logout
				</button>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div id="content"></div>

		</div>
		<!-- /#page-content-wrapper -->
	</div>
</body>
</html>
