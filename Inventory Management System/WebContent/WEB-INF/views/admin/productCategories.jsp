  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
  <style>

        .table-container {
            margin-top: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        @media (max-width: 576px) {
            .form-group {
                margin-bottom: 10px;
            }
        }
    </style>
    <script>
    $(document).ready(function() {
    	console.log("Inside product Categories");
        $.ajax({
            url: "getProductCategories",
            method: "POST",
            dataType: "json",
            success: function(data) {
                console.log(data);
                
                // Populate options from response data
                data.forEach(function(category) {
                    $("<option>").val(category.productCategoryId).text(category.productCategoryName).appendTo("#categorySelect");
                });
            },
        error:function(){
        	console.log("Error");
        }
        })
    });
        
    $(document).ready(function() {
        $("#categorySelect").on("change", function() {
            var selectedCategoryId = $(this).val();
            var categoryId = {};
            categoryId["categoryId"] = selectedCategoryId;
            console.log(categoryId);
            $.ajax({
                url: "getProducts",
                method: "POST",
                data: { "categoryId":JSON.stringify(categoryId)},
                dataType: "json",
                success: function(data) {
                    console.log(data);
                    $("#productTableBody").empty();
                    data.forEach(function(product) {
                        var row = "<tr>";
                        row += "<td>" + product.productId + "</td>";
                        row += "<td>" + product.productName + "</td>";
                      	row += "<td>" + product.batchNo + "</td>";
                      	row += "<td>" + product.stock + "</td>";
                      	row += "<td>" + product.purchasePrice + "</td>";
                      	row += "<td>" + product.costPrice+ "</td>";
                      	row += "<td>" + product.mrp + "</td>";
                        row += "</tr>";

                        $("#productTableBody").append(row);
                    });
                },
                error: function() {
                    console.log("Error");
                }
            });
        });
    });



    </script>
    
<div class="container">
    <h2 class="text-center">Product Categories</h2>

        <div class="form-group">
            <label for="categorySelect">Select Category:</label>
            <select class="form-control" id="categorySelect" name="selectedCategory">
                <option value="" hidden selected>Select a category</option>
            </select>
        </div>


    <div class="table-container">
    <div class="table-responsive">
        <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Batch Number</th>
                        <th>Stock</th>
                        <th>Purchase Price</th>
                        <th>Product MRP</th>
                        <th>Product Cost</th>
              
                    </tr>
                </thead>
                <tbody id="productTableBody">
                    <!-- Product data will be dynamically populated here -->
                </tbody>
            </table>
    </div>
</div>
</div>
