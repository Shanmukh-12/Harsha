<head>
    <link rel="stylesheet" href="./HomeProcurement/styles/indentsStyles.css">
</head>
<script src="./HomeProcurement/scripts/indentsScript.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


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