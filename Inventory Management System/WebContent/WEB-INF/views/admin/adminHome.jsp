
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<title>Admin Dashboard</title>
<style>
:root {
	--main-bg-color: #009d63;
	--main-text-color: #009d63;
	--second-text-color: #bbbec5;
	--second-bg-color: #c1efde;
}

.primary-text {
	color: var(--main-text-color);
}

.second-text {
	color: var(--second-text-color);
}

.primary-bg {
	background-color: var(--main-bg-color);
}

.secondary-bg {
	background-color: var(--second-bg-color);
}

.rounded-full {
	border-radius: 100%;
}

#wrapper {
	overflow-x: hidden;
	background-image: linear-gradient(to right, #baf3d7, #c2f5de, #cbf7e4, #d4f8ea, #ddfaef);
}

#sidebar-wrapper {
	min-height: 100vh;
	margin-left: -15rem;
	-webkit-transition: margin 0.25s ease-out;
	-moz-transition: margin 0.25s ease-out;
	-o-transition: margin 0.25s ease-out;
	transition: margin 0.25s ease-out;
}

#sidebar-wrapper .sidebar-heading {
	padding: 0.875rem 1.25rem;
	font-size: 1.2rem;
}

#sidebar-wrapper .list-group {
	width: 15rem;
}

#page-content-wrapper {
	min-width: 100vw;
}

#wrapper.toggled #sidebar-wrapper {
	margin-left: 0;
}

#menu-toggle {
	cursor: pointer;
}

.list-group-item {
	border: none;
	padding: 20px 30px;
}

.list-group-item.active {
	background-color: transparent;
	color: var(--main-text-color);
	font-weight: bold;
	border: none;
}

@media ( min-width : 768px) {
	#sidebar-wrapper {
		margin-left: 0;
	}
	#page-content-wrapper {
		min-width: 0;
		width: 100%;
	}
	#wrapper.toggled #sidebar-wrapper {
		margin-left: -15rem;
	}
}
</style>
</head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
function logout() {
    $.ajax({
        url: "logout",
        method: "GET",
        success: function(response) {
            console.log("Logout successful");
            console.log(response);
            location.href= "/inventory/";
        },
        error: function() {
            console.log("Logout error");
        }
    });
}

	function clicked(data) {
		console.log("Clicked");
		$.ajax({
			url : data,
			method : "GET",
			success : function(response) {
				console.log("AJAX call successful");
				$("#content").html(response);
			},
			error : function() {
				console.log("AJAX call error");
			}
		});
	}
</script>

<body>
	<div class="d-flex" id="wrapper">
		<div class="bg-white" id="sidebar-wrapper">
			<div
				class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">
				<i class="fas fa-user-secret me-2"></i>Inventory
			</div>
			<div class="list-group list-group-flush my-3">
				<button
					class="list-group-item list-group-item-action bg-transparent second-text active"
					onclick="clicked('warehouseStock')">
					<i class="fas fa-tachometer-alt me-2"></i>WareHouse Stock
				</button>
				<div class="dropdown">
					<button
						class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
						role="button" id="grnDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">
						<i class="fas fa-gift me-2"></i>Vendors
					</button>
					<ul class="dropdown-menu" aria-labelledby="grnDropdown">
						<li><button class="dropdown-item"
								onclick="clicked('addVendor')">New Vendor</button></li>
						<li><button class="dropdown-item" onclick="clicked('update')">Update
								Vendor</button></li>
						<li><button class="dropdown-item"
								onclick="clicked('deleteVendor')">Delete Vendor</button></li>
						<li><button class="dropdown-item"
								onclick="clicked('showVendors')">Get Vendors</button></li>

					</ul>
				</div>

				<div class="dropdown">
					<button
						class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
						role="button" id="usersDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">
						<i class="fas fa-gift me-2"></i>Users
					</button>
					<ul class="dropdown-menu" aria-labelledby="usersDropdown">
						<li><button class="dropdown-item"
								onclick="clicked('addUser')">New User</button></li>
						<li><button class="dropdown-item"
								onclick="clicked('deleteUser')">Delete User</button></li>
						<li><button class="dropdown-item"
								onclick="clicked('showUsers')">Get User</button></li>
					</ul>
				</div>
				<div class="dropdown">
					<button
						class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
						role="button" id="usersDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">
						<i class="fas fa-gift me-2"></i>Stores
					</button>
					<ul class="dropdown-menu" aria-labelledby="usersDropdown">
						<li><button class="dropdown-item"
								onclick="clicked('addStore')">Add Store</button></li>
						<li><button class="dropdown-item"
								onclick="clicked('deleteStores')">Delete Store</button></li>
						<li><button class="dropdown-item"
								onclick="clicked('showStores')">Get Store</button></li>
					</ul>
				</div>

				<button
					class="list-group-item list-group-item-action bg-transparent second-text fw-bold"
					onclick="clicked('productCategories')">
					<i class="fas fa-paperclip me-2"></i>Product Categories
				</button>
				<button
					class="list-group-item list-group-item-action bg-transparent second-text fw-bold"
					onclick="clicked('reports')">
					<i class="fas fa-shopping-cart me-2"></i>Reports
				</button>
				<button
					class="list-group-item list-group-item-action bg-transparent text-danger fw-bold" onclick="logout()">
					<i class="fas fa-power-off me-2"></i>Logout
				</button>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<nav
				class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
				<div class="d-flex align-items-center">
					<i class="fas fa-align-left primary-text fs-4 me-3"
						id="menu-toggle"></i>
					<h2 class="fs-2 m-0">Dashboard</h2>
				</div>

				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
						<li class="nav-item dropdown">
							<button class="nav-link dropdown-toggle second-text fw-bold"
								role="button" data-bs-toggle="dropdown" aria-expanded="false">
								<i class="fas fa-user me-2"></i>Admin
							</button>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><button class="dropdown-item"> Edit Profile</button></li>
								<li><button class="dropdown-item">Logout</button></li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>

			<div class="container-fluid px-4" id="content">


				<!--Page to be inserted -->


			</div>
		</div>
	</div>
	<!-- /#page-content-wrapper -->
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		var el = document.getElementById("wrapper");
		var toggleButton = document.getElementById("menu-toggle");

		toggleButton.onclick = function() {
			el.classList.toggle("toggled");
		};
	</script>
</body>

</html>
