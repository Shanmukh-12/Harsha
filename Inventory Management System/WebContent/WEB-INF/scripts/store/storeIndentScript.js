
	function filterData()
	{
		var storeId = document.getElementById("store-id-dropdown").value;
	    var indentStatus = document.getElementById("indent-status-dropdown").value;
	    var fromDate = document.getElementById("indent-date-dropdown-from").value;
	    var toDate = document.getElementById("indent-date-dropdown-to").value;
		console.log(storeId);
		console.log(indentStatus);
		console.log(fromDate);
		console.log(toDate);
	    $.ajax({
			
	    	url:"getIndentsByFilterData",
	    	method:"POST",
	    	dataType:"json",
	    	data:{
	    		"filters":JSON.stringify(
	    				{
	    					"storeId":storeId,
	    					"indentStatus":indentStatus,
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
	    		    Object.values(response).forEach(function(indent) {
	    		      var issueBlock = $('<div class="issues-block"></div>');
	    		      var status = $('<h4 class="store-indent-id">Indent Id: <span class="bold">' + indent.indentId + '</span></h4>');
	    		      var issueDetails = $('<div class="issue-details"></div>');
	    		      var storeId = $('<span class="label">Store ID:</span><span>' + indent.storeId + '</span>');
	    		      var indentId = $('<span class="label">Status:</span><span>' + indent.indentStatus + '</span>');
	    		      var indentDate = $('<span class="label">Indent Date:</span><span>' + new Date(indent.date[0], indent.date[1] - 1, indent.date[2]).toLocaleDateString() + '</span>');
	    		      var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadIndentProducts(\'' + indent.indentId + '\')">View Products</button>');

	    		      issueDetails.append(storeId, indentId, indentDate);
	    		      issueBlock.append(status, issueDetails, viewProductsButton);
	    		      // Insert the issue block after the h1 tag
	    		      $('.container').append(issueBlock);
	    		    });
	    		  }
	    		},
	    	error:function(){
	    		console.log("Error");
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
           url: "getStoreIndentsList",
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
        	     Object.values(response).forEach(function(indent) {
        	       var issueBlock = $('<div class="issues-block"></div>');
        	       var status = $('<h4 class="store-indent-id">Indent Id: <span class="bold">' + indent.indentId + '</span></h4>');
        	       var issueDetails = $('<div class="issue-details"></div>');
        	       var storeId = $('<span class="label">Store ID:</span><span>' + indent.storeId + '</span>');
        	       var indentId = $('<span class="label">Status:</span><span>' + indent.indentStatus + '</span>');
        	       var indentDate = $('<span class="label">Indent Date:</span><span>' + new Date(indent.date[0], indent.date[1] - 1, indent.date[2]).toLocaleDateString() + '</span>');
        	       var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadIndentProducts(\'' + indent.indentId + '\')">View Products</button>');

        	       issueDetails.append(storeId, indentId, indentDate);
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
   
   function loadIndentProducts(indentId) {
       var currentPageUrl = window.location.href;
       console.log(indentId);
       var data = {}
       data["indentId"]=indentId;
       console.log("Hehree");
       $.ajax({
         url: "getStoreIndentProductsList",
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
   // Get the current date
var currentDate = new Date();

// Format the date as YYYY-MM-DD
var formattedDate = currentDate.toISOString().split("T")[0];

// Set the max attribute to the date with one day added
document.getElementById("indent-date-dropdown-to").setAttribute("max", formattedDate);
document.getElementById("indent-date-dropdown-from").setAttribute("max", formattedDate);


   document.getElementById("indent-date-dropdown-to").setAttribute("value", formattedDate);