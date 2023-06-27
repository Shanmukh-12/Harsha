<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
 <link rel="stylesheet" href="./inventoryHome/styles/stockIssuedProductsStyles.css">
    <script src="./inventoryHome/scripts/stockIssuedProductsScript.js"></script>

</head>
<body>
  <h2 align="center">Indents List</h2>
  <div id="indent-list-div">
  <div id="first_table">
    <table id="product-details-table">
      <thead>
        <tr>
          <th>Product ID</th>
          <th>Product Name</th>
          <th>Product Category</th>
          <th>Quantity</th>
        </tr>
      </thead>
      <tbody>
      	<c:forEach var="item" items="${productsList}">
            <tr>
                <td>${item.productId}</td>
                <td>${item.productName}</td>
                <td>${item.productCategoryName}</td>
                <td>${item.quantity}</td>
            </tr>
        </c:forEach>      		
      </tbody>
    </table>
  </div>
</div>
</body>
</html>