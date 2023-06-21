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

<script>
$(document).ready(function() {
    $.ajax({
        url: "showVendors",
        method: "GET",
        dataType: "json",
        success: function(data) {
            var tableBody = $("#vendorTable tbody");
            tableBody.empty(); // Clear existing table data

            data.forEach(function(vendor) {
                var row = $("<tr>");
                $("<td>").text(vendor.vendorId).appendTo(row);
                $("<td>").text(vendor.vendorName).appendTo(row);
                $("<td>").text(vendor.vendorAddress).appendTo(row);
                $("<td>").text(vendor.vendorPhone).appendTo(row);
                $("<td>").text(vendor.status).appendTo(row);
                row.appendTo(tableBody);
            });
        },
        error: function() {
            console.log("Error");
        }
    });
});
</script>

<div class="container">
    <h1>Vendor List</h1>
    <div class="table-container">
        <table id="vendorTable">
            <thead>
                <tr>
                    <th>Vendor ID</th>
                    <th>Vendor Name</th>
                    <th>Vendor Address</th>
                    <th>Vendor Phone Number</th>
                    <th>Vendor Status</th>
                </tr>
            </thead>
            <tbody>
                <!-- Table body will be populated dynamically using AJAX -->
            </tbody>
        </table>
    </div>
</div>
