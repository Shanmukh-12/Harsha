<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Pagination Example</title>
    <style>
        /* Styles for pagination buttons */
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination button {
            margin: 0 5px;
            padding: 5px 10px;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
            cursor: pointer;
        }

        .pagination button.active {
            background-color: #e6e6e6;
        }
    </style>
</head>
<body>
    <div id="items-container"></div>
    <div class="pagination"></div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
function getItems(pageNumber, pageSize) {
	  var pageRequest = {
	    page: pageNumber,
	    pageSize: pageSize
	  };

	  $.ajax({
	    url: 'items',
	    type: 'GET',
	    data: pageRequest,
	    success: function(response) {
	      // Process the response and update the UI with the items
	      displayItems(response);
	      updatePagination(response.totalPages, response.currentPage);
	    },
	    error: function(error) {
	      console.error('Error retrieving items:', error);
	    }
	  });
	}

	function displayItems(response) {
	  var items = response.items;
	  var totalPages = response.totalPages;

	  // Get the container element where you want to display the items
	  var container = document.getElementById("items-container");

	  // Clear the container before adding new elements
	  container.innerHTML = "";

	  // Loop through the items and create HTML elements to display them
	  items.forEach(function(item) {
	    // Create a div element for each item
	    var itemDiv = document.createElement("div");

	    // Create and populate the content for the item
	    var content = "<p>Purchase Order ID: " + item.purchase_order_id + "</p>";
	    content += "<p>Purchase Order Date: " + item.purchase_order_date + "</p>";
	    content += "<p>Purchase Order Amount: " + item.purchase_order_amount + "</p>";
	    content += "<p>Vendor ID: " + item.vendor_id + "</p>";
	    content += "<p>Purchase Order Expected Date: " + item.purchase_order_expected_date + "</p>";

	    // Set the content of the item div
	    itemDiv.innerHTML = content;

	    // Append the item div to the container
	    container.appendChild(itemDiv);
	  });

	  // Display pagination buttons
	  var paginationContainer = document.getElementsByClassName("pagination")[0];
	  paginationContainer.innerHTML = "";

	  for (var i = 1; i <= totalPages; i++) {
	    var button = document.createElement("button");
	    button.innerText = i;
	    button.addEventListener("click", function() {
	      // Handle button click event to load items for the selected page
	      getItems(parseInt(this.innerText), pageSize);
	    });

	    if (i === response.currentPage) {
	      button.classList.add("active");
	    }

	    paginationContainer.appendChild(button);
	  }
	}

	// Function to update the pagination UI
	function updatePagination(totalPages, currentPage) {
	  var paginationContainer = document.getElementsByClassName("pagination")[0];
	  paginationContainer.innerHTML = "";

	  var maxVisibleButtons = 5; // Maximum number of visible buttons
	  var startPage = Math.max(currentPage - Math.floor(maxVisibleButtons / 2), 1);
	  var endPage = Math.min(startPage + maxVisibleButtons - 1, totalPages);

	  // "Previous" button
	  var previousButton = document.createElement("button");
	  previousButton.innerText = "Previous";
	  if (currentPage === 1) {
	    previousButton.disabled = true;
	  } else {
	    previousButton.addEventListener("click", function() {
	      getItems(currentPage - 1, defaultPageSize);
	    });
	  }
	  paginationContainer.appendChild(previousButton);

	  // Visible page buttons
	  for (var i = startPage; i <= endPage; i++) {
	    var button = document.createElement("button");
	    button.innerText = i;
	    if (i === currentPage) {
	      button.classList.add("active");
	    }
	    button.addEventListener("click", function() {
	      var page = parseInt(this.innerText);
	      getItems(page, defaultPageSize);
	    });
	    paginationContainer.appendChild(button);
	  }

	  // "Next" button
	  var nextButton = document.createElement("button");
	  nextButton.innerText = "Next";
	  if (currentPage === totalPages) {
	    nextButton.disabled = true;
	  } else {
	    nextButton.addEventListener("click", function() {
	      getItems(currentPage + 1, defaultPageSize);
	    });
	  }
	  paginationContainer.appendChild(nextButton);
	}

	// Initial page load
	var defaultPageSize = 3; // Set your desired default page size
	getItems(1, defaultPageSize);

</script>
</body>
</html>
