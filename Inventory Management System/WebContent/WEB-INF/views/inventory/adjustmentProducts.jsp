<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="./inventoryHome/styles/adjustmentProductsStyles.css">
</head>
<body>
  <h2 align="center">Adjustment Products List</h2>
  <div id="adjustmentsProductsid">

  
  <div id="first_table">
    <table id="product-details-table">
    <thead>
         <tr>
          <th>Product ID</th>
          <th>Product Name</th>
          <th>Product Category</th>
          <th>Batch No</th>
          <th>Original Stock</th>
          <th>Current Stock</th>
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
                <td>${item.current_stock}</td>
                <td>${item.updated_stock}</td>
                <td>${item.adjs_desc}</td>
            </tr>
        </c:forEach>  
      </tbody>
    </table>
  </div>
</div>
</body>
</html>