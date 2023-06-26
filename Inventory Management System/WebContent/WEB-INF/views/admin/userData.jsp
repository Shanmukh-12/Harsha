<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.container1
    {
    position:relative;
    margin-top: 50px;
    display: flex;
    flex-direction: column;
    align-items: center;
    }
.container {
	display: flex;
    align-items: center;
    height: 100vh;
    flex-direction: column;
}

table {
	border-collapse: collapse;
	width: 100%; /* Set the table width to 100% */
	max-width: 800px; /* Set a maximum width for the table */
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

/* Add a scrollable container for the table */
.table-container {
	max-height: 400px; /* Adjust the height as needed */
	overflow-y: scroll; /* Enable scrolling */
	scrollbar-width: none; /* Hide scrollbar for Firefox */
	-ms-overflow-style: none; /* Hide scrollbar for IE and Edge */
}

/* Hide scrollbar for Chrome, Safari, and Opera */
.table-container::-webkit-scrollbar {
	display: none;
}
</style>
<div class="container1">
	<h1>User List</h1><br>
    <div class="filter-container">
        <label for="userTypeFilter">User Type:</label>
        <select id="userTypeFilter">
            <option value="">Select UserType</option>
            <option value="inventory">Inventory</option>
            <option value="procurement">Procurement</option>
        </select>

        <label for="userStatusFilter">User Status:</label>
        <select id="userStatusFilter">
            <option value="">Select User Status</option>
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
        </select>

        <button id="applyFilterBtn">Apply Filter</button>
    </div>
<div class="container">
	<div class="table-container">
		<table>
			<thead>
				<tr>
					<th>User ID</th>
					<th>User Name</th>
					<th>User Type</th>
					<th>User Status</th>
				</tr>
			</thead>
			<tbody id="userTableBody">
			</tbody>
		</table>
	</div>
</div>

<script>
$(document).ready(function() {
    function applyFilter() {
        var userTypeFilter = $("#userTypeFilter").val();
        var userStatusFilter = $("#userStatusFilter").val();
        var url;
        if (userTypeFilter && userStatusFilter) {
            url = "getFilteredDataByTypeAndStatus";
        } else if (userTypeFilter) {
            url = "getFilteredDataByType";
        } else if (userStatusFilter) {
            url = "getFilteredDataByStatus";
        } else {
            url = "showUsers";
        }

        console.log(userTypeFilter);
        console.log(userStatusFilter);
        console.log(url);

        $.ajax({
            url: url,
            method: "POST",
            dataType: "json",
            data: {
                userType: userTypeFilter,
                userStatus: userStatusFilter
            },
            success: function(data) {
                // Handle the success response
                var userTableBody = $("#userTableBody");
                userTableBody.empty();

                data.forEach(function(user) {
                    var row = $("<tr>");
                    row.append($("<td>").text(user.userId));
                    row.append($("<td>").text(user.userName));
                    row.append($("<td>").text(user.userType));
                    row.append($("<td>").text(user.status));
                    userTableBody.append(row);
                });
            },
            error: function() {
                console.log("Error");
            }
        });
    }

    $("#applyFilterBtn").click(function() {
        applyFilter();
    });

    // Initial data load
    applyFilter();
});

/* $(document).ready(function() {
    $.ajax({
        url: "showUsers",
        method: "GET",
        dataType: "json",
        success: function(data) {
            console.log(data);
            var userTableBody = $("#userTableBody");

            data.forEach(function(user) {
                var row = $("<tr>");
                row.append($("<td>").text(user.userId));
                row.append($("<td>").text(user.userName));
                row.append($("<td>").text(user.userType));
                row.append($("<td>").text(user.status));
                userTableBody.append(row);
            });
        },
        error: function() {
            console.log("Error");
        }
    });
}); */
</script>
