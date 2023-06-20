<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <style>
  #product-details-table{
    background-color: white;
      height: 50px;
      overflow-y: scroll;
      width:1000px;
  
  }
    #htag{
  position: relative;
  top: 20px;
  }
    table {
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    th, td {
    text-align:center;
      border: 1px solid black;
      padding: 8px;
    }


    
    #first_table {
       height: 200px;
      
      overflow-y: scroll;
    }
    
    

    #products-dropdown
    {
    display:flex;
     margin-left:650px;
     padding-bottom:5px;
     
    }
    #products-dropdown label{
    display: block;
      font-weight: bold;
      color: #333;
      margin-bottom: 5px;
    }

  </style>
<body>
  <h2 align="center">Indents List</h2>
  <div style="margin-bottom:20px; margin-left:50px;">
   <div id="products-dropdown" align="right">
    <label for="product-category">Product Category</label>
    <select id="product-category">
      <option value="category1">Soaps & Lotions</option>
      <option value="category2">Category 2</option>
      <option value="category3">Category 3</option>
    </select>
  </div>
  
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