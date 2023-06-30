  function showBufferingLayer() {
            document.getElementById('buffering-layer').style.display = 'flex';
        }

        // Hide buffering layer
        function hideBufferingLayer() {
            document.getElementById('buffering-layer').style.display = 'none';
        }
    
    function loadIndentProducts(indentId) {
		showBufferingLayer();
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
                 hideBufferingLayer();
            },
            error: function () {
                console.log("Failed to load Indents");
                hideBufferingLayer();
            }
        });
    }

        $(document).ready(function () {
			
            $(".filterButton").click(function () {
				showBufferingLayer();
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
        	    		   hideBufferingLayer();
        	    		},
                    error: function () {
                        console.log("Failed to apply filters");
                         hideBufferingLayer();
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
  
  setTimeout(hideBufferingLayer, 3000);