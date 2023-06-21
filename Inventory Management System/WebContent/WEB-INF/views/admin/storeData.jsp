<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
body {
	background-color: #fff;
}

.container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	height: 100vh;
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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
$(document).ready(function() {
    $.ajax({
        url: "showStores",
        method: "GET",
        dataType: "json",
        success: function(data) {
            var tableBody = $("<tbody>");

            data.forEach(function(store) {
                var row = $("<tr>");
                $("<td>").text(store.storeId).appendTo(row);
                $("<td>").text(store.storeName).appendTo(row);
                $("<td>").text(store.storeAddress).appendTo(row);
                $("<td>").text(store.storeContactNumber).appendTo(row);
                $("<td>").text(store.status).appendTo(row);
                row.appendTo(tableBody);
            });

            // Replace the existing table body with the new one
            $("#storeTable tbody").replaceWith(tableBody);
        },
        error: function() {
            console.log("Error");
        }
    });
});
</script>
<div class="container">
    <h1>Stores List</h1>
    <div class="table-container">
        <table id="storeTable">
            <thead>
                <tr>
                    <th>Store ID</th>
                    <th>Store Name</th>
                    <th>Store Address</th>
                    <th>Store Contact Number</th>
                    <th>Store Status</th>
                </tr>
            </thead>
            <!-- Empty tbody for initial loading -->
            <tbody></tbody>
        </table>
    </div>
</div>






