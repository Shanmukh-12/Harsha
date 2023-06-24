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
        position:relative;
        left:200px;
            max-width: 800px;
           margin-top:20px;
            padding-top: 10px;
            margin-left: 170px;
        }
 .indentClass
         {
         position:relative;
         left:380px;
          font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
         }
         .indentClass1
         {
         position:relative;
         left:600px;
           font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
         }
         .indentClass5
         {
         position:relative;
         left:650px;
           font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
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
            max-width: 900px;
            margin-top:20px;
            padding-top: 10px;
            margin-left: 250px;
        }
        .search-container{
        margin-top:20px;
        margin-left:800px;
        }
    </style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <script>

        $(document).ready(function () {
        	tk();
            $(".filterButton").click(function tk() {
                console.log("sjnsjns");
                var indentStatus = $("#indentStatus").val();
                var fromDate = $("#fromDate").val();
                var toDate = $("#toDate").val();

                var data = {
                    indentStatus: indentStatus,
                    fromDate: fromDate,
                    toDate: toDate
                };
                console.log(data);

                $.ajax({
                    url: "filterIndents",
                    method: "post",
                    data: {"filters" :JSON.stringify(data)},
                    success: function(response) {
        	    		console.log(response);
        	    		  // Remove existing issues blocks and "No Indents Found" message
        	    		  $('.issues-block').remove();
        	    		  $('.not-found-message').remove();


        	                if (Object.keys(response).length === 0) {
        	                    // Response is empty, display "No Indents Found" message
        	                    var noDataMessage = $('<h5 class="not-found-message" style="color:red" align="center">No Indents Found</h5>');
        	                    $('.container').html(noDataMessage);
        	                } else {
        	    		    // Iterate over the response object
        	    		    Object.values(response).forEach(function(indent) {
        	    		      var issueBlock = $('<div class="issues-block"></div>');
        	    		      var status = $('<h4 class="procurement-indent-id">Indent ID:<span class="bold">' +indent.indentId+ '</span></h4>');
        	    		      var issueDetails = $('<div class="issue-details"></div>');
        	    		      var indentId = $('<span class="label">Status :</span><span>' + indent.indentStatus + '</span>');
        	    		      var indentDate = $('<span class="label">Indent Date:</span><span>' + formatDate(indent.indentDate)+ '</span>');

        	    		      var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadIndentProducts(\'' + indent.indentId + '\')">View Products</button>');

        	    		      issueDetails.append( indentId, indentDate);
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
            function tk() {
                console.log("sjnsjns");
                var indentStatus = $("#indentStatus").val();
                var fromDate = $("#fromDate").val();
                var toDate = $("#toDate").val();

                var data = {
                    indentStatus: indentStatus,
                    fromDate: fromDate,
                    toDate: toDate
                };
                console.log(data);

                $.ajax({
                    url: "filterIndents",
                    method: "post",
                    data: {"filters" :JSON.stringify(data)},
                    success: function(response) {
        	    		console.log(response);
        	    		  // Remove existing issues blocks and "No Indents Found" message
        	    		  $('.issues-block').remove();
        	    		  $('.not-found-message').remove();


        	                if (Object.keys(response).length === 0) {
        	                    // Response is empty, display "No Indents Found" message
        	                    var noDataMessage = $('<h5 class="not-found-message" style="color:red" align="center">No Indents Found</h5>');
        	                    $('.container').html(noDataMessage);
        	                } else {
        	    		    // Iterate over the response object
        	    		    Object.values(response).forEach(function(indent) {
        	    		      var issueBlock = $('<div class="issues-block"></div>');
        	    		      var status = $('<h4 class="procurement-indent-id">Indent ID:<span class="bold">' +indent.indentId+ '</span></h4>');
        	    		      var issueDetails = $('<div class="issue-details"></div>');
        	    		      var indentId = $('<span class="label">Status :</span><span>' + indent.indentStatus + '</span>');
        	    		      var indentDate = $('<span class="label">Indent Date:</span><span>' + formatDate(indent.indentDate)+ '</span>');

        	    		      var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadIndentProducts(\'' + indent.indentId + '\')">View Products</button>');

        	    		      issueDetails.append( indentId, indentDate);
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
            }
            function formatDate(date) {
                var formattedDate = new Intl.DateTimeFormat('en-US', {
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit'
                }).format(new Date(date[0], date[1] - 1, date[2]));
                return formattedDate.replace(/\//g, '-');
            }
  
            
        });
        
        
        function performSearch() {
            var searchTerm = document.getElementById("searchInput").value.toLowerCase();
            var issuesBlocks = document.getElementsByClassName("issues-block");

            // Iterate over the issues blocks and show/hide based on the search term
            for (var i = 0; i < issuesBlocks.length; i++) {
                var indentId = issuesBlocks[i].querySelector(".-indent-id .bold").textContent.toLowerCase();

                if (indentId.includes(searchTerm)) {
                    issuesBlocks[i].style.display = "block";  // Show the matching block
                } else {
                    issuesBlocks[i].style.display = "none";   // Hide the non-matching block
                }
            }

            // Show "No data" message if no matching blocks found
            var noDataMessage = document.getElementById("noDataMessage");
            noDataMessage.style.display = "none";   // Hide the message by default

            if (document.querySelectorAll(".issues-block[style*='display: block']").length === 0) {
                noDataMessage.style.display = "block";  // Show the message if no matching blocks found
            }
        }

        // Add event listener to the search button
     document.getElementById("searchInput").addEventListener("input", performSearch);
        
        </script>
</head>
<body>
    <form method="post" action="">
            <h1 class="text-center">Indents List</h1>
            
                <label for="indentStatus" class="indentClass5">Indent Status:</label>
                <select id="indentStatus" name="indentStatus" class="indentClass5">
                    <option value="Active">Active</option>
                    <option value="Closed"> Closed</option>
                    <!-- Add options dynamically from your data source -->
                </select>
      <br><br>
      <label class="indentClass">Select Indent From Date:</label>
      <input type="date" id="fromDate" class="indentClass">
	
	
	
	

                <label for="toDate" class="indentClass">Select Indent To Date:</label>
                <input type="date" id="toDate" name="toDate" class="indentClass">
                <button type="button" class="filterButton indentClass">Apply Filters</button>
                <button onclick="clearSelections()" class="filterButton indentClass">Clear</button>
                
                <br><br><br>
     
            <div class="container">
            <c:forEach items="${indents}" var="indent">
                <div class="issues-block">
                    <h4 class="procurement-indent-id" style="margin-left:10px">Indent ID:<span class="bold">${indent.indentID}</span></h4>
                    <div class="issue-details">
                       <div> <span class="label">Status:</span><span style="margin-left:10px">${indent.indentsStatus}</span></div>
                       <div  style="margin-left:100px"> <span class="label">Indent Date:</span><span style="margin-left:10px">${indent.d}</span></div>
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