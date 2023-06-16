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
        });
        
        $("#categorySelect").on("change", function() {
            var selectedCategoryId = $(this).val();
            
            // Make AJAX request to retrieve products based on selected category
            $.ajax({
                url: "getProducts", 
                method: "POST",
                data: { "categoryId": selectedCategoryId }, 
                dataType: "json",
                success: function(data) {
                    console.log(data);
                    
                 // Clear previous table rows
                    $("#productTableBody").empty();

                    // Iterate over each product and create table rows
                    data.forEach(function(product) {
                        var row = "<tr>";
                        row += "<td>" + product.productId + "</td>";
                        row += "<td>" + product.productName + "</td>";
                        row += "<td>" + product.batchNo + "</td>";
                        row += "<td>" + product.stock + "</td>";
                        row += "<td>" + product.purchasePrice + "</td>";

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
                <c:forEach var="category" items="${productCategories}">
                    <option value="${category.productCategory}">${category.productCategory}</option>
                </c:forEach>
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
                    </tr>
                </thead>
                <tbody id="productTableBody">
                    <!-- Product data will be dynamically populated here -->
                </tbody>
            </table>
    </div>
</div>
</div>
