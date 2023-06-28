<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
 <link rel="stylesheet" href="./inventoryHome/styles/priceReviewProductsStyles.css">
    <script src="./inventoryHome/scripts/priceReviewProductsScript.js"></script>
</head>
<body>
  <h2 align="center">Price Review Products List</h2>
  <div id="price-review-products-list">

  
  <div id="first_table">
    <table id="product-details-table">
    <thead>
         <tr>
          <th>Product ID</th>
          <th>Product Name</th>
          <th>Product Category</th>
          <th>Batch No</th>
          <th>Original Cost</th>
          <th>Modified Cost</th>
          <th>Reason</th>
        </tr>
      </thead>
      <tbody>
      	<c:forEach var="item" items="${productsList}">
            <tr>
                <td>${item.productId}</td>
                <td>${item.productName}</td>
                <td>${item.productCategoryName}</td>
                <td>${item.batch_no}</td>
                <td>${item.old_price}</td>
                <td>${item.new_price}</td>
                <td>${item.review_desc}</td>
            </tr>
        </c:forEach>  
      </tbody>
    </table>
  </div>
</div>
</body>
</html>