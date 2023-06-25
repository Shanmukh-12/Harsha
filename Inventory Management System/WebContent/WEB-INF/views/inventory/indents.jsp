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
            max-width: 800px;
           margin-top:20px;
            padding-top: 10px;
            margin-left: 170px;
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
            margin-top:10px;
            
        }

        .label ,.label1{
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
            margin-left: 210px;
        }
        .search-container{
        display:flex;
        margin-bottom:10px;
        margin-top:10px;
        margin-left:690px;
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
            url: "getInventoryIndentProductsList",
            method: "post",
            data:{"indentId":JSON.stringify(data)},
            success: function (response){
            	 $("#modalContent").html(response);
                 $("#productsModal").modal("show");
                 history.pushState(null, null, currentPageUrl);
            },
            error: function () {
                console.log("Failed to load Indents");
            }
        });
    }

        $(document).ready(function () {
            $(".filterButton").click(function () {
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
        	    		       const [year, month, day] = indent.indentDate;
       					       const indentD = new Date(year, month - 1, day).toISOString().split('T')[0];
       					       console.log(indentDate);
        	    		      var issueBlock = $('<div class="issues-block"></div>');
        	    		      var status = $('<h4 class="procurement-indent-id">Indent ID:<span class="bold">' +indent.indentId+ '</span></h4>');
        	    		      var issueDetails = $('<div class="issue-details"></div>');
        	    		      var indentId = $('<span class="label" style="margin-left:10px">Status :</span><span class="label" style="margin-left:5px">' + indent.indentStatus + '</span>');
        	    		      var indentDate = $('<span class="label1" style="margin-left:50px">Indent Date:</span><span class="label1" style="margin-left:50px">' + indentD+ '</span>');

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
  
            
        });
        function performSearch() {
            var searchTerm = document.getElementById("searchInput").value.toLowerCase();
            var issuesBlocks = document.getElementsByClassName("issues-block");

            // Iterate over the issues blocks and show/hide based on the search term
            for (var i = 0; i < issuesBlocks.length; i++) {
                var indentId = issuesBlocks[i].querySelector(".procurement-indent-id .bold").textContent.toLowerCase();

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

        
        
  var currentDate = new Date();

  // Format the date as YYYY-MM-DD
  var formattedDate = currentDate.toISOString().split("T")[0];

  // Set the max attribute to the date with one day added
  document.getElementById("toDate").setAttribute("max", formattedDate);
  document.getElementById("fromDate").setAttribute("max", formattedDate);


  document.getElementById("toDate").setAttribute("value", formattedDate);
     
     
 // Add event listener to the search button
  document.getElementById("searchInput").addEventListener("input", performSearch); 
     
        
        </script>
</head>
<body>
    <form method="post" action="">
            <h1 Style="margin-left:450px">Indents List</h1>
            <div class="filters">
                <select id="indentStatus" name="indentStatus">
                    <option value="" selected disabled>Select Indent Status</option>
                    <option value="Active">Active</option>
                    <option value="Closed"> Closed</option>
                    <!-- Add options dynamically from your data source -->
                </select>
                <b><label for="fromDate">From Date:</label></b>
                <input type="date" id="fromDate" name="fromDate">

                <b><label for="toDate">To Date:</label></b>
                <input type="date" id="toDate" name="toDate">
                
                <button type="button" class="filterButton">Apply Filters</button>
                <input type="reset">
            </div>
             <div class="search-container">
		     <input type="text" id="searchInput" placeholder="Search Indent ID">
		     <button type="button" id="searchButton">Search</button>
	       </div>
            <div class="container">
	       
            <c:forEach items="${indents}" var="indent">
                <div class="issues-block">
                    <h4 class="procurement-indent-id" style="margin-left:10px">Indent ID:<span class="bold">${indent.indentID}</span></h4>
                    <div class="issue-details">
                       <div style="margin-left:10px"> <span class="label">Status:</span><span class="label" style="margin-left:5px" >${indent.indentsStatus}</span></div>
                       <div style="margin-left:50px"> <span class="label">Indent Date:</span><span class="label" style="margin-left:5px">${indent.d}</span></div>
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