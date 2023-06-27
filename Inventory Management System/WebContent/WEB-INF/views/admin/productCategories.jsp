  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="./adminHome/scripts/productCategoriesScripts.js"></script>     
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
