$(document).ready(function() {
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
			data: { "filters": JSON.stringify(data) },
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
						var status = $('<h4 class="procurement-indent-id">Indent ID:<span class="bold">' + indent.indentId + '</span></h4>');
						var issueDetails = $('<div class="issue-details"></div>');
						var indentId = $('<span class="label">Status :</span><span>' + indent.indentStatus + '</span>');
						var indentDate = $('<span class="label">Indent Date:</span><span>' + formatDate(indent.indentDate) + '</span>');

						var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadIndentProducts(\'' + indent.indentId + '\')">View Products</button>');

						issueDetails.append(indentId, indentDate);
						issueBlock.append(status, issueDetails, viewProductsButton);
						// Insert the issue block after the h1 tag
						$('.container').append(issueBlock);
					});
				}
			},
			error: function() {
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
			data: { "filters": JSON.stringify(data) },
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
						var status = $('<h4 class="procurement-indent-id">Indent ID:<span class="bold">' + indent.indentId + '</span></h4>');
						var issueDetails = $('<div class="issue-details"></div>');
						var indentId = $('<span class="label">Status :</span><span>' + indent.indentStatus + '</span>');
						var indentDate = $('<span class="label">Indent Date:</span><span>' + formatDate(indent.indentDate) + '</span>');

						var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadIndentProducts(\'' + indent.indentId + '\')">View Products</button>');

						issueDetails.append(indentId, indentDate);
						issueBlock.append(status, issueDetails, viewProductsButton);
						// Insert the issue block after the h1 tag
						$('.container').append(issueBlock);
					});
				}
			},
			error: function() {
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