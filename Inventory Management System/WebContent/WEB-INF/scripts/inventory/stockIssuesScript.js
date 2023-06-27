	 function filterData()
	{
		 
		var storeId = document.getElementById("store-id-dropdown").value;
	    var storeIssueStatus = document.getElementById("indent-status-dropdown").value;
	    var fromDate = document.getElementById("indent-date-dropdown-from").value;
	    var toDate = document.getElementById("indent-date-dropdown-to").value;
	    var url = null;
		if(storeId)
		{
			if(storeIssueStatus)
			{
				if(fromDate)
				{
					url="getIssuesFilterDataIdStatusFrom";				
				}
				else
				{
					url="getIssuesFilterDataIdStatus";
				}
			}
			else
			{
				if(fromDate)
				{
					url="getIssuesFilterDataIdFrom";
				}
				else
				{
					url="getIssuesFilterDataId";
				}
				
			}
		}
		else
		{
			if(storeIssueStatus)
			{
				if(fromDate)
				{
					url="getIssuesFilterDataStatusFrom";				
				}
				else
				{
					url="getIssuesFilterDataStatus";
				}
			}
			else
			{
				if(fromDate)
				{
					url="getIssuesFilterDataFrom";
				}
				else
				{
					console.log("TO only");
					url="getIssuesFilterDataTo";
				}
				
			}
			
		}
		console.log(storeId);
		console.log(storeIssueStatus);
		console.log(fromDate);
		console.log(toDate);
		console.log(url);
	    $.ajax({
			
	    	url:url,
	    	method:"POST",
	    	dataType:"json",
	    	data:{
	    		"filters":JSON.stringify(
	    				{
	    					"storeId":storeId,
	    					"storeIssueStatus":storeIssueStatus,
	    					"fromDate":fromDate,
	    					"toDate":toDate
	    				})
	    	},
	    	 success: function(response) {
	        	   console.log(response);

	        	   // Remove existing issues blocks and "No Indents Found" message
	        	   $('.issues-block').remove();
	        	   $('.not-found-message').remove();

	        	   if (response.length === 0) {
	        	     // Response is empty, display "No Indents Found" message
	        	     var noDataMessage = $('<h5 class="not-found-message" style="color:red" align="center">No Indents Found</h5>');
	        	     $('.container > h1').after(noDataMessage);
	        	   } else {
	        	     // Iterate over the response array
	        	     response.forEach(function(issue) {
	        	       var issueBlock = $('<div class="issues-block"></div>');
	        	       var status = $('<h4 class="store-indent-id">StoreIssue Id: <span class="bold">' + issue.storeIssueId + '</span></h4>');
	        	       var issueDetails = $('<div class="issue-details"></div>');
	        	       var storeId = $('<span class="label">Store ID:</span><span>' + issue.storeId + '</span>');
	        	       var amount = $('<span class="label">Amount:</span><span>' + issue.amount + '</span>');
	        	       var storeIssueStatus = $('<span class="label">Status:</span><span>' + issue.storeIssueStatus + '</span>');
	        	       var storeIssueDate = $('<span class="label">Issue Date:</span><span>' + new Date(issue.storeIssueDate).toLocaleDateString() + '</span>');
	        	       var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadIndentProducts(\'' + issue.storeIssueId + '\')">View Products</button>');

	        	       issueDetails.append(storeId, amount, storeIssueDate, storeIssueStatus);
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
           url: "getStoreIssuesList",
           method: "POST",
           dataType: "json",
           success: function(response) {
        	   console.log(response);

        	   // Remove existing issues blocks and "No Indents Found" message
        	   $('.issues-block').remove();
        	   $('.not-found-message').remove();

        	   if (response.length === 0) {
        	     // Response is empty, display "No Indents Found" message
        	     var noDataMessage = $('<h5 class="not-found-message" style="color:red" align="center">No Indents Found</h5>');
        	     $('.container > h1').after(noDataMessage);
        	   } else {
        	     // Iterate over the response array
        	     response.forEach(function(issue) {
        	       var issueBlock = $('<div class="issues-block"></div>');
        	       var status = $('<h4 class="store-indent-id">StoreIssue Id: <span class="bold">' + issue.storeIssueId + '</span></h4>');
        	       var issueDetails = $('<div class="issue-details"></div>');
        	       var storeId = $('<span class="label">Store ID:</span><span>' + issue.storeId + '</span>');
        	       var amount = $('<span class="label">Amount:</span><span>' + issue.amount + '</span>');
        	       var storeIssueStatus = $('<span class="label">Status:</span><span>' + issue.storeIssueStatus + '</span>');
        	       var storeIssueDate = $('<span class="label">Issue Date:</span><span>' + new Date(issue.storeIssueDate).toLocaleDateString() + '</span>');
        	       var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadIndentProducts(\'' + issue.storeIssueId + '\')">View Products</button>');

        	       issueDetails.append(storeId, amount, storeIssueDate, storeIssueStatus);
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
           var indentId = issuesBlocks[i].querySelector(".store-indent-id .bold").textContent.toLowerCase();

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
   
   function loadIndentProducts(storeIssueId) {
       var currentPageUrl = window.location.href;
       console.log(storeIssueId);
       var data = {}
       data["storeIssueId"]=storeIssueId;
       $.ajax({
         url: "getStoreIssueProductsList",
         method: "post",
         data:{"storeIssueId":JSON.stringify(data)},
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
document.getElementById("indent-date-dropdown-to").setAttribute("max", formattedDate);
document.getElementById("indent-date-dropdown-from").setAttribute("max", formattedDate);


   document.getElementById("indent-date-dropdown-to").setAttribute("value", formattedDate);