<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="./adminHome/scripts/userDataScripts.js"></script>
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
