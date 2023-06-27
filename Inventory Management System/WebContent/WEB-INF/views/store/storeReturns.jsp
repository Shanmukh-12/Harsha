<style>
.container {
	max-width: 800px;
	margin: 100 auto;
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

.dropdowns {
	display: flex;
	padding-right: 235px;
    height: 100px;
    flex-direction: column;
    align-items: center;

.dropdown {
	display: inline-block;
	margin-right: 10px;
}

select, button, #searchInput, input {
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.twoDropdowns {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
}
</style>
<!-- Add the following CSS link in the head section of your HTML -->

<!-- Add the following script tags before your JavaScript code -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script>

	function filterData()
	{
		var storeId = document.getElementById("store-id-dropdown").value;
	    var fromDate = document.getElementById("return-date-dropdown-from").value;
	    var toDate = document.getElementById("return-date-dropdown-to").value;
	    var url = null;
		console.log(storeId);
		console.log(fromDate);
		console.log(toDate);
		console.log(url);
	    $.ajax({
			
	    	url:"getStoreReturnsDataBasedOnFilters",
	    	method:"POST",
	    	dataType:"json",
	    	data:{
	    		"filters":JSON.stringify(
	    				{
	    					"storeId":storeId,
	    					"fromDate":fromDate,
	    					"toDate":toDate
	    				})
	    	},
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
	        	     Object.values(response).forEach(function(returns) {
	        	       var issueBlock = $('<div class="issues-block"></div>');
	        	       var status = $('<h4 class="store-return-id">Return Id: <span class="bold">' + returns.returnId + '</span></h4>');
	        	       var issueDetails = $('<div class="issue-details"></div>');
	        	       var storeId = $('<span class="label">Store Id:</span><span>' + returns.storeId + '</span>');
	        	       var indentId = $('<span class="label">Store Issue Id:</span><span>' + returns.storeIssueId + '</span>');
	        	       var indentDate = $('<span class="label">Return Date:</span><span>' + new Date(returns.date[0], returns.date[1] - 1, returns.date[2]).toLocaleDateString() + '</span>');
	        	       var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadReturnProducts(\'' + returns.returnId + '\')">View Products</button>');

	        	       issueDetails.append(storeId,indentId, indentDate);
	        	       issueBlock.append(status, issueDetails, viewProductsButton);
	        	       // Insert the issue block after the h1 tag
	        	       $('.container').append(issueBlock);
	        	     });
	        	   }
	        	 }
	       });
	}
	

	
   $(document).ready(function(){
	   $.ajax({
		   url:"getStoreIds",
		   method:"POST",
		   dataType:"json",
		   success:function(response){
			   $('#store-id-dropdown').empty();
		        
		        // Add default option
		        $('#store-id-dropdown').append($('<option value="" selected >Select Store ID</option>'));
		        
		        // Add options based on the response
		        response.forEach(function(item) {
		        	 $('<option>', {
		        		    value: item.storeId,
		        		    text: item.storeName+" ("+item.storeId+")"
		        		  }).appendTo('#store-id-dropdown');
		        	 });
		   },
	   error:function(error){
		   console.log(error);
	   }
	   });
	   
	   
       $.ajax({
           url: "getStoreReturnsList",
           method: "POST",
           dataType: "json",
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
        	     Object.values(response).forEach(function(returns) {
        	       var issueBlock = $('<div class="issues-block"></div>');
        	       var status = $('<h4 class="store-return-id">Return Id: <span class="bold">' + returns.returnId + '</span></h4>');
        	       var issueDetails = $('<div class="issue-details"></div>');
        	       var storeId = $('<span class="label">Store Id:</span><span>' + returns.storeId + '</span>');
        	       var indentId = $('<span class="label">Store Issue Id:</span><span>' + returns.storeIssueId + '</span>');
        	       var indentDate = $('<span class="label">Return Date:</span><span>' + new Date(returns.date[0], returns.date[1] - 1, returns.date[2]).toLocaleDateString() + '</span>');
        	       var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadReturnProducts(\'' + returns.returnId + '\')">View Products</button>');

        	       issueDetails.append(storeId,indentId, indentDate);
        	       issueBlock.append(status, issueDetails, viewProductsButton);
        	       // Insert the issue block after the h1 tag
        	       $('.container').append(issueBlock);
        	     });
        	   }
        	 }
       });
   });


   function performSearch() {
       var searchTerm = document.getElementById("searchInput").value.toLowerCase();
       var issuesBlocks = document.getElementsByClassName("issues-block");

       // Iterate over the issues blocks and show/hide based on the search term
       for (var i = 0; i < issuesBlocks.length; i++) {
           var indentId = issuesBlocks[i].querySelector(".store-return-id .bold").textContent.toLowerCase();

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
   
   function loadReturnProducts(returnId) {
       var currentPageUrl = window.location.href;
       console.log(returnId);
       var data = {}
       data["returnId"]=returnId;
       console.log("Hehree");
       $.ajax({
         url: "getStoreReturnProductsList",
         method: "post",
         data:{"returnId":JSON.stringify(data)},
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
   // Get the current date
var currentDate = new Date();

// Format the date as YYYY-MM-DD
var formattedDate = currentDate.toISOString().split("T")[0];

// Set the max attribute to the date with one day added
document.getElementById("return-date-dropdown-to").setAttribute("max", formattedDate);
document.getElementById("return-date-dropdown-from").setAttribute("max", formattedDate);


   document.getElementById("return-date-dropdown-to").setAttribute("value", formattedDate);
</script>
<form method=post onsubmit="filterData(); return false">
	<div class="dropdowns">
		<div class="threeDropdowns">
			<div class="dropdown">
				<select id="store-id-dropdown">
				</select>
			</div>
			<div class="dropdown">
				<label>From Date :</label>
				<input type="date" id="return-date-dropdown-from"
					placeholder="Select Indent Date">
			</div>
			<div class="dropdown">
				<label>To  Date :</label>
				<input type="date" id="return-date-dropdown-to"
					placeholder="Select Indent Date">
			</div>
		</div>
		
		<div class="threeDropdowns">
			<input type="reset">
			<button type="submit" >Apply Filters</button>
		</div>
	</div>
</form>

<div class="container">

	<h1 class="text-center mb-4">Store Returns List</h1>

	<div class="search-container" align="right">
		<input type="text" id="searchInput" placeholder="Search Store Return ID">
		<button type="button" id="searchButton">Search</button>
	</div><br>


</div>
<!-- Modal -->
<div class="modal fade" id="productsModal" tabindex="-1"
	aria-labelledby="productsModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg"
		style="max-width: calc(100% - 200px);">
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