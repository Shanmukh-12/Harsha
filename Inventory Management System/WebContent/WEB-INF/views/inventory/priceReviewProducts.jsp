<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
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
</head>
<body>
  <h2 align="center">Price Review Products List</h2>
  <div style="margin-bottom:20px; margin-left:50px;">

  
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