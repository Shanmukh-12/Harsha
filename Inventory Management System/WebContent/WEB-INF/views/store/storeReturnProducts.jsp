<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet"
	href="./storeHome/styles/storeReturnProductsStyles.css">
</head>


<body>
	<h2 align="center">Returns List</h2>
	<div style="margin-bottom: 20px; margin-left: 50px;">

		<div id="first_table">
			<table id="product-details-table">
				<thead>
					<tr>
						<th>Product ID</th>
						<th>Batch NO</th>
						<th>Quantity</th>
						<th>Reason</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${productsList}">
						<tr>
							<td>${item.productId}</td>
							<td>${item.batchNo}</td>
							<td>${item.quantity}</td>
							<td>${item.reason}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>