<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bus List Page</title>
   <style>
        .container {
            max-width: 800px;
            margin: 100 auto;
            padding-top: 10px;
            margin-left:170px;
        }

        .issues-block {
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            background-color: #fff;
        }

        .issues-block:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color:#d8f2f0 ;
            
            
        }

        .issue-details{
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }

         .label {
            font-weight: bold;
            
        }


        .btn-issues{
               border-radius:6px;
                background-color: #4CAF50;
                 border: none;
                 color: white;
                 cursor: pointer;
                 font-weight: bold;
                box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
            margin-left: 600px;
 
        }

        .btn-issues:hover {
            background-color:#4CAF50 ;
            border-color: #4CAF50;
        }
        



.bold {
            font-weight: bold;
            font-size: 25px;
        }
        
           /* Custom styles for modal */
    .modal-content {
        background-color: #f1f1f1;
        border-radius: 10px;
    }

    .modal-header {
        background-color: #c1efde;
        border-radius: 10px 10px 0 0;
    }

    .modal-title {
        color: #333;
        font-weight: bold;
    }

    .modal-body {
        padding: 20px;
    }

    </style>
    <script>
    function loadIndentProducts(indentId) {
        var currentPageUrl = window.location.href;
        $.ajax({
          url: "indentProductsButton",
          method: "GET",
          success: function (response) {
            $("#modalContent").html(response);
            $("#productsModal").modal("show");
            history.pushState(null, null, currentPageUrl);
          },
          error: function () {
            console.log("Failed to load static page");
          }
        });
      }

    </script>
</head>
<body>

<form method="GET" action="">
    <div class="container">
        <h1 class="text-center mb-4">Indents List</h1>
        <c:forEach items="${indents}" var="indent">
            <div class="issues-block">
                <h4 class="store-indent-id">Status: <span class="bold">${indent.indentStatus}</span></h4>
                <div class="issue-details">
                    <span class="label">Indent ID:</span><span>${indent.indentId}</span>
                    <span class="label">Indent Date:</span><span>${indent.indentDate}</span>
                </div>
                <div>
                    <button type="button" class="btn-issues" onclick="loadIndentProducts('indentsProductsButton')">View Products</button>
                </div>
            </div>
        </c:forEach>
    </div>
</form>

<!-- Modal -->
<div class="modal fade" id="productsModal" tabindex="-1" aria-labelledby="productsModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" style="max-width: calc(100% - 200px);">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productsModalLabel">Products</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="modalContent">
                <!-- Modal content will be loaded dynamically here -->
            </div>
        </div>
    </div>
</div>


</body>
</html>