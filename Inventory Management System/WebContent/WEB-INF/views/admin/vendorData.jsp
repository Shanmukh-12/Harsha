<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="./adminHome/scripts/vendorDataScripts.js"></script>
<div class="container1">
<select id="vendor-status">
<option value="" disabled selected>Select Vendor</option>
<option value="Active">Active</option>
<option value="NotActive">InActive</option>
</select>
<button onclick="getVendors()">reset</button>
</div>
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
