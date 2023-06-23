<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Indents List</title>
    <style>
        /* Styles for the page */
        .container {
            max-width: 900px;
            margin: 100px auto;
            padding-top: 10px;
            margin-left: 170px;;
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
            background-color: #d8f2f0;
        }

        .issue-details {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }

        .label {
            font-weight: bold;
        }

        .btn-issues {
            border-radius: 6px;
            background-color: #4CAF50;
            border: none;
            color: white;
            cursor: pointer;
            font-weight: bold;
            box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
            margin-left: 600px;
        }

        .btn-issues:hover {
            background-color: #4CAF50;
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
        .filters{
        margin-bottom:30px;
        margin-left:50px;
        }
    </style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <script>
        function loadIndentProducts(indentId) {
            var currentPageUrl = window.location.href;
            console.log(indentId);
            var data = { "indentId": indentId };
            console.log(data);
            $.ajax({
                url: "getProcurementIndentProductsList",
                method: "post",
                data:{"indentId":JSON.stringify(data)},
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
        


        $(document).ready(function () {
            $(".filterButton").click(function () {
                console.log("sjnsjns");
                var selectedIndentId = $("#indentId").val();
                var fromDate = $("#fromDate").val();
                var toDate = $("#toDate").val();

                var data = {
                    indentId: selectedIndentId,
                    fromDate: fromDate,
                    toDate: toDate
                };
                console.log(data);

                $.ajax({
                    url: "filterIndents",
                    method: "post",
                    data: JSON.stringify(data),
                    contentType: "application/json",

                    	success: function(response) {
            	    		console.log(response);
            	    		  // Remove existing issues blocks and "No Indents Found" message
            	    		  $('.issues-block').remove();
            	    		  $('.not-found-message').remove();

            	    		  if (Object.keys(response).length === 0) {
            	    		    // Response is empty, display "No Indents Found" message
            	    		    var noDataMessage = $('<h5 class="not-found-message" style="color:red" align="center">No Indents Found</h5>');
            	    		    $('.container > h1').after(noDataMessage);
            	    		  } else {
            	    		    // Iterate over the response object
            	    		    Object.values(response).forEach(function(indent) {
            	    		      var issueBlock = $('<div class="issues-block"></div>');
            	    		      var indentId = $('<span class="label">Status:</span><span>' + indent.indentStatus + '</span>');
            	    		      var issueDetails = $('<div class="issue-details"></div>');
            	    		      var status = $('<h4 class="procurement-indent-id">Indent Id: <span class="bold">' + indent.indentId + '</span></h4>');
            	    		      var indentDate = $('<span class="label">Indent Date:</span><span>' + new Date(indent.d[0], indent.d[1] - 1, indent.d[2]).toLocaleDateString() + '</span>');
            	    		      var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadIndentProducts(\'' + indent.indentId + '\')">View Products</button>');

            	    		      issueDetails.append(storeId, indentId, indentDate);
            	    		      issueBlock.append(status, issueDetails, viewProductsButton);
            	    		      // Insert the issue block after the h1 tag
            	    		      $('.container').append(issueBlock);
            	    		    });
            	    		  }
            	    		},
                    error: function () {
                        console.log("Failed to apply filters");
                    }
                });
                
                
            });
            
          
            
            
            
            
            
            
            
        });
    </script>
</head>
<body>
    <form method="post" action="">
            <h1 class="text-center mb-4">Indents List</h1>
            <div class="filters">
                <label for="indentId">Indent ID:</label>
                <select id="indentId" name="indentId">
                    <option value="">Select Indent</option>
                    <c:forEach items="${indents}" var="indent">
                  <option value="${indent.indentID}">${indent.indentID}</option>
                     </c:forEach>
                    
                    <!-- Add options dynamically from your data source -->
                </select>

                <label for="fromDate">From Date:</label>
                <input type="date" id="fromDate" name="fromDate">

                <label for="toDate">To Date:</label>
                <input type="date" id="toDate" name="toDate">

                <button type="button" class="filterButton">Apply Filters</button>
            </div>
            <div class="container">
            <c:forEach items="${indents}" var="indent">
                <div class="issues-block">
                    <h4 class="procurement-indent-id">Status: <span class="bold">${indent.indentsStatus}</span></h4>
                    <div class="issue-details">
                        <span class="label">Indent ID:</span><span>${indent.indentID}</span>
                        <span class="label">Indent Date:</span><span>${indent.d}</span>
                    </div>
                    <div>
                        <button type="button" class="btn-issues" onclick="loadIndentProducts(${indent.indentID})">View Products</button>
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
               <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="modalContent">
                <!-- Modal content will be loaded dynamically here -->
            </div>
        </div>
    </div>
</div>
</body>
</html>