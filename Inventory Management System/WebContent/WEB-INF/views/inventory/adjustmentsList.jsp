<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Adjustments List</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="./inventoryHome/styles/adjustmentsList.css">
<script src="./inventoryHome/scripts/adjustmentsListScript.js"></script>
</head>
<body>
<form method=post onsubmit="filterData(); return false">
	<div class="dropdowns">
		<div class="twoDropdowns">
			<div class="dropdown">
				<select id="product-category-dropdown" >
				
				
				</select>
			</div>
			<div class="dropdown">
				<select id="product-name-dropdown">
					<option value="" selected>Select Product Name</option>
					
				</select>
			</div>
		</div>
		<div class="twoDropdowns">
			<div class="dropdown">
				<label>From Date :</label>
				<input type="date" id="adjustment-date-dropdown-from"
					placeholder="Select Indent Date">
			</div>
			<div class="dropdown">
				<label>To  Date :</label>
				<input type="date" id="adjustment-date-dropdown-to"
					placeholder="Select Indent Date">
			</div>
		</div>
		
		<div class="twoDropdowns">
			<input type="reset">
			<button type="submit" >Apply Filters</button>
			
		</div>
	</div>

	
</form>

<form method="post" action="">
    <div class="container">
       <h1 class="text-center mb-4">Adjustments List</h1>
     <c:forEach items="${adjustments}" var="adjustments">
            <div class="issues-block" >
                <h4 class="store-indent-id">Adjustment ID: <span class="bold">${adjustments.adjustmentID}</span></h4>
                <div class="issue-details" >
                    <span class="label">Adjustment Date :  </span><span>${adjustments.adjustmentDate}</span>
                
                    <button type="button" class="btn-issues" onclick="loadAdjustmentsProducts(${adjustments.adjustmentID})">View Products</button>
                </div>
            </div>
        </c:forEach>
    </div>
</form>


<div class="modal fade" id="productsModal" tabindex="-1" aria-labelledby="productsModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" style="max-width: calc(100% - 200px);">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productsModalLabel">Products</h5>
               <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="modalContent">
               
            </div>
        </div>
    </div>
</div>


</body>
</html>