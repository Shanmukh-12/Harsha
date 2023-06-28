<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Indents List</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>       
<link rel="stylesheet" href="./inventoryHome/styles/indentsStyles.css">
<script src="./inventoryHome/scripts/indentsScript.js"></script>
</head>
<body>
<div class="overlay" id="buffering-layer">
        <div class="spinner"></div>
    </div>
    <form method="post" action="">
            <h1 id="heading-indent" >Indents List</h1>
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
                    <h4 class="procurement-indent-id">Indent ID:<span class="bold">${indent.indentID}</span></h4>
                    <div class="issue-details">
                       <div id="statusdivid"> <span class="label">Status:</span><span class="label">${indent.indentsStatus}</span></div>
                       <div id="indentdateid"> <span class="label">Indent Date:</span><span class="label">${indent.d}</span></div>
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