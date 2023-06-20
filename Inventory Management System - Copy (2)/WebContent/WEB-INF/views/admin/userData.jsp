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

<div class="container">
	<h1>User List</h1>
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
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.userId}</td>
						<td>${user.userName}</td>
						<td>${user.userType}</td>
						<td>${user.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
