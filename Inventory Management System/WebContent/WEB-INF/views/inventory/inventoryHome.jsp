<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <title>Inventory Home Page</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script> <!-- Include the Bootstrap JavaScript library -->
    <script src="./inventoryHome/scripts/inventoryHomeScript.js"></script>
    <link rel="stylesheet" href="./inventoryHome/styles/inventoryHomeStyles.css">
   


    
</head>


<body>

<div class="d-flex" id="wrapper">
    <!-- Sidebar -->
    <div class="bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">
            <span class="material-symbols-outlined">warehouse</span>Inventory
        </div>
        <div class="list-group list-group-flush my-3">
            <button class="list-group-item list-group-item-action bg-transparent second-text active"
                    onclick="Myfunction('wareHouseButton')">
                <span class="material-symbols-outlined">inventory</span>Warehouse Stock
            </button>

            <div class="dropdown">
                <button class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
                        role="button" id="indentsDropDownId" data-bs-toggle="dropdown" aria-expanded="false">
                    <i>
                        <span class="material-symbols-outlined">order_approve</span>
                    </i>
                    Indents
                </button>
                <ul class="dropdown-menu" aria-labelledby="indentsDropDownId">
                    <li><button class="dropdown-item" onclick="Myfunction('indentsButton')">List Indents</button></li>
                    <li><button class="dropdown-item" onclick="Myfunction('createIndentButton')">Create Indent</button></li>
                </ul>
            </div>

           
           <div class="dropdown">
            <button class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
                    id="adjustmentsButton" data-bs-toggle="dropdown" aria-expanded="false">
                <span class="material-symbols-outlined">equalizer</span>
                Adjustments
            </button>
             <ul class="dropdown-menu" aria-labelledby="adjustmentsDropDownId">
                    <li><button class="dropdown-item" onclick="Myfunction('adjustmentsButton')">Create Adjustment</button></li>
                    <li><button class="dropdown-item" onclick="Myfunction('adjustmentsListButton')">List Adjustments</button></li>
                </ul>
             </div>
             <div class="dropdown">
            <button class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
                    id="priceReviewButton" data-bs-toggle="dropdown" aria-expanded="false">
                <i>
                    <span class="material-symbols-outlined">currency_rupee</span>
                </i>
                Price Review
            </button>
             <ul class="dropdown-menu" aria-labelledby="priceReviewDropDownId">
                    <li><button class="dropdown-item" onclick="Myfunction('priceReviewButton')">Create Price Review</button></li>
                    <li><button class="dropdown-item" onclick="Myfunction('priceReviewListButton')">List Price Review</button></li>
                </ul>
            </div>
            <div class="dropdown">
                <button class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
                        role="button" id="storeDropDownId" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="fas fa-shopping-cart me-2"></i>Store
                </button>
                <ul class="dropdown-menu" aria-labelledby="storeDropDownId">
                    <li><button class="dropdown-item" onclick="Myfunction('getStoreIndents')">List Store Indents</button></li>
                     <li><button class="dropdown-item" onclick="Myfunction('createStockIssueButton')">Create Store Issue</button></li>
                    <li><button class="dropdown-item" onclick="Myfunction('stockIssuesButton')">List Store Issues</button></li>
                    <li><button class="dropdown-item" onclick="Myfunction('getStoreReturns')">List Store Returns</button></li>
                </ul>
            </div>

            <button class="list-group-item list-group-item-action bg-transparent text-danger fw-bold"><i
                    class="fas fa-power-off me-2"></i>Logout
            </button>
        </div>
    </div>
    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
            <div class="d-flex align-items-center">
                <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                <h2 class="fs-2 m-0">InventoryHome</h2>
            </div>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <button class="nav-link dropdown-toggle second-text fw-bold" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user me-2"></i>John Doe
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><button class="dropdown-item">Profile</button></li>
                            <li><button class="dropdown-item">Settings</button></li>
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


</body>
</html>
