<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <title>Bootstrap 5 Responsive Admin Dashboard</title>

    <style>
        :root {
            --main-bg-color: #009d63;
            --main-text-color: #009d63;
            --second-text-color: #bbbec5;
            --second-bg-color: #c1efde;
        }

        .dropdown-menu {
            background-color: #c1efde;
        }
        


        .dropdown-menu::hover {
            background-color: white;
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

        @media (min-width: 768px) {
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

<script>
function ButtonAction(button) {
	console.log("hello");
    var row = button.parentNode.parentNode;
    row.parentNode.removeChild(row);
  }
  
  
$("document").ready(
		function()
		{
			  $.ajax({
                  url: "https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css",
                  dataType: "script",
                  success: function() {
                     
                  }
              });
			  $.ajax({
                  url: "https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js",
                  dataType: "script",
                  success: function() {
                	  
                  }
              });
			  $.ajax({
                  url: "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js",
                  dataType: "script",
                  success: function() {
                     
                  }
              });
			  $.ajax({
                  url: "https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js",
                  dataType: "script",
                  success: function() {
                     
                  }
              });
	
		
		}
		
		);

    function Myfunction(containerurl) {
        $.ajax({
            url: containerurl,
            method: "GET",
            success: function (response) {
                console.log("AJAX call successful");
              
                $("#content").html(response);
               	
                var table=$("#example");
                var elementsArray = $("table thead th").toArray();
               
                for(var j=0;j<6;j++)
                {
                	var tr=$("<tr>");
               		for(var i=0;i<(elementsArray.length);i++)
                	{
                		
                		if(i===(elementsArray.length)-1){
                			var td1=$("<td>");
                		var btn=$("<button>");
                		btn.text("Delete");
                		btn.on("click",function()
                		{
                			ButtonAction(this);
                			
                		}		
                		
                		
                		);
                		td1.append(btn);
                		}
                		else
                			{
                		
                		var td1=$("<td>").text("hello");
                		
                			}
                		tr.append(td1);
                		
                		
                	}
          
                     table.append(tr);
                	}
               table.DataTable();
                console.log("afterwer");
            },
            error: function () {
                console.log("AJAX call error");
            }
        });
    }
    function Myfunction2(containerurl) {
        $.ajax({
            url: containerurl,
            method: "GET",
            success: function (response) {
                console.log("AJAX call successful");
              
                $("#content").html(response);
                var ctx1 = document.getElementById('myChart');
                ctx1.width = 300; // Set the width
                ctx1.height = 130;
                var ctx = ctx1.getContext('2d');

            	$.ajax({
            		  url: "getStock",
            		  type: "GET",
            		  dataType:"json",
            		  success: function(response) {
            		    // Handle the response from the servlet if needed
            		     console.log(response);
            		    var name=[];
            		    var stock=[];
            		    response.forEach(function(value)
            		    {
            		    stock.push(value.stock.product_stock);
            		    name.push(value.product.product_name);
            		    	
            		    })
            		    
            		    

            		 // Define chart data
            		 var data = {
            		   labels: name,
            		   datasets: [{
            		     label: 'stock',
            		     data: stock,
            		     
            		     backgroundColor: 'rgba(54, 162, 235, 0.5)', // Bar color
            		     borderColor: 'rgba(54, 162, 235, 1)', // Border color
            		     borderWidth: 1 // Border width
            		   }]
            		 };
            	
            	var myChart = new Chart(ctx, {
            		  type: 'bar',
            		  data: data,
            		  responsive: true,
            		    maintainAspectRatio: false,
            		  width: 100,
         		     height: 100,
            		  options: {
            		    scales: {
            		    	
            		      y: {
            		        beginAtZero: true
            		      }
            		    }
            		  }
            		});
            		  },
            		  error: function() {
            		    // Handle errors
            		    console.error("AJAX request failed.");
            		  }
            		});

               	
               
            },
            error: function () {
                console.log("AJAX call error");
            }
        });
    }
</script>

<body>
    <div class="d-flex" id="wrapper">
        <!-- Sidebar -->
        <div class="bg-white" id="sidebar-wrapper">
            <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom"><i
                    class="fas fa-user-secret me-2"></i>Procurement</div>
            <div class="list-group list-group-flush my-3">
                <button  id="dashboard"
                    class="list-group-item list-group-item-action bg-transparent second-text active"
                    onclick="Myfunction('dashboard')"><i class="fas fa-tachometer-alt me-2"></i>Dashboard</button>
                <div class="dropdown">
                    <button  class="list-group-item list-group-item-action bg-transparent second-text fw-bold" onclick="Myfunction2('warehousestock')">
                        <i class="fas fa-project-diagram me-2"></i>WareHouse Stock
                    </button>
                    <button  class="list-group-item list-group-item-action bg-transparent second-text fw-bold"
                        id="indents" onclick="Myfunction('indents')"><i class="fas fa-chart-line me-2"></i>Indents</button>
                    <button  class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
                        role="button" id="purchaseDropdown" data-bs-toggle="dropdown" aria-expanded="false"><i
                            class="fas fa-gift me-2"></i>Purchases</button>
                    <ul class="dropdown-menu" aria-labelledby="purchaseDropdown">
                        <li><button class="dropdown-item"  id="purchaseOrderData"
                                onclick="Myfunction('purchaseOrderData')">Purchase Orders Data</button></li>
                        <li><button class="dropdown-item"  id="createPurchaseOrder"
                                onclick="Myfunction('createPurchaseOrder')">Create Purchase Order</button></li>
                        <li><button class="dropdown-item"  id="createNewPurchaseOrders"
                                onclick="Myfunction('createNewPurchaseOrders')">Create New Purchase Order</button></li>
                    </ul>
                </div>

                <div class="dropdown">
                    <button  class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
                        role="button" id="grnDropdown" data-bs-toggle="dropdown" aria-expanded="false"><i
                            class="fas fa-gift me-2"></i>GRN</button>
                    <ul class="dropdown-menu" aria-labelledby="grnDropdown">
                        <li><button class="dropdown-item"  id="grnData"
                                onclick="Myfunction('grnData')">GRN Data</button></li>
                        <li><button class="dropdown-item"  id="createGRN"
                                onclick="Myfunction('createGRN')">Create GRN</button></li>
                        <li><button class="dropdown-item" ></button></li>
                    </ul>
                </div>
                <div class="dropdown">
                    <button  class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
                        role="button" id="returnsDropdown" data-bs-toggle="dropdown" aria-expanded="false"><i
                            class="fas fa-gift me-2"></i>Purchase Returns</button>
                    <ul class="dropdown-menu" aria-labelledby="returnsDropdown">
                        <li><button class="dropdown-item"  id="prnData"
                                onclick="Myfunction('prnData')">PRN Data</button></li>
                        <li><button class="dropdown-item"  id="createPRN"
                                onclick="Myfunction('createPRN')">Create PRN</button></li>
                    </ul>
                </div>

                <div class="dropdown">
                    <button  class="list-group-item list-group-item-action bg-transparent second-text fw-bold dropdown-toggle"
                        role="button" id="returnsDropdown" data-bs-toggle="dropdown" aria-expanded="false"><i
                            class="fas fa-gift me-2"></i>Products</button>
                    <ul class="dropdown-menu" aria-labelledby="returnsDropdown">
                        <li><button class="dropdown-item"  id="addHSN"
                                onclick="Myfunction('addHSN')">Add HSN</button></li>
                        <li><button class="dropdown-item"  id="addProductCategory"
                                onclick="Myfunction('addProductCategory')">Add Product Category</button></li>
                        <li><button class="dropdown-item"  id="addProduct"
                                onclick="Myfunction('addProduct')">Add Product</button></li>
                        <li><button class="dropdown-item"  id="del"
                                onclick="Myfunction('del')">del</button></li>
                    </ul>
                </div>
                <button class="list-group-item list-group-item-action bg-transparent text-danger fw-bold"><i
                        class="fas fa-power-off me-2"></i>Logout</button>
            </div>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div id="content"></div>

        </div>
        <!-- /#page-content-wrapper -->
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        var el = document.getElementById("wrapper");
        var toggleButton = document.getElementById("menu-toggle");

        toggleButton.onclick = function () {
            el.classList.toggle("toggled");
        };
        
        
       
       
        
    </script>
</body>

</html>
