<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
   <head>
      <meta charset="UTF-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
      <title>Admin Dashboard</title>
      <link rel="stylesheet" href="./adminHome/styles/adminHomeStyles.css">
      <script src="./adminHome/scripts/adminHomeScripts.js"></script>
   </head>
   <body>
      <div class="d-flex" id="wrapper">
         <!-- Sidebar -->
         <div class="bg-white" id="sidebar-wrapper">
            <div
               class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">
               <i class="fas fa-user-secret me-2"></i>Inventory
            </div>
            <div class="list-group list-group-flush my-3">
               <button
                  class="list-group-item list-group-item-action bg-transparent second-text active"
                  onclick="wareHouse('warehousestock')">
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
                     <li><button class="dropdown-item" onclick="clicked('updateVendor')">Update
                        Vendor</button>
                     </li>
                     <li><button class="dropdown-item"
                        onclick="clicked('deleteVendorPage')">Delete Vendor</button></li>
                     <li><button class="dropdown-item"
                        onclick="clicked('displayVendors')">Get Vendors</button></li>
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
                        onclick="clicked('addUserPage')">New User</button></li>
                     <li><button class="dropdown-item"
                        onclick="clicked('deleteUserPage')">Delete User</button></li>
                     <li><button class="dropdown-item"
                        onclick="clicked('displayUserPage')">Get User</button></li>
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
                        onclick="clicked('addStorePage')">Add Store</button></li>
                     <li><button class="dropdown-item"
                        onclick="clicked('deleteStorePage')">Delete Store</button></li>
                     <li><button class="dropdown-item"
                        onclick="clicked('displayStorePage')">Get Store</button></li>
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
   </body>
</html>