<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.center {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}
</style>
<script>
	function deleteStore() {
		// Retrieve form data
		var storeId = $("#storeId").val();

		// Confirmation dialog
		var confirmationMessage = "Store ID: " + storeId
				+ "\n\nAre you sure you want to delete this store?";
		var userConfirmed = window.confirm(confirmationMessage);

		if (userConfirmed) {
			// Send the data to the backend using AJAX
			$.ajax({
				url : "deleteStore",
				type : "POST",
				contentType : "application/json",
				data : JSON.stringify({
					storeId : storeId,
				}),
				success : function(response) {
					alert("store deleted successfully.");
					// Perform any additional actions after deletion
				},
				error : function() {
					alert("An error occurred while deleting the user.");
				}
			});
		}

		return false; // Prevent form submission
	}
</script>
<div class="center">
	<div class="col-md-6">
		<h2 class="mb-4">Delete Store</h2>
		<form  method="post"
			onsubmit="return deleteStore()">
			<div class="form-group">
				<label for="storeId">Store Id:</label> <select class="form-control"
					id="storeId" name="storeId" required>
					<option value="">Select Store Name</option>
					<c:forEach var="store" items="${stores}">
						<option value="${store.storeId}">${store.storeName}
							(${store.storeId})</option>
					</c:forEach>
				</select>
			</div>

			<button type="submit" class="btn btn-danger">Delete</button>
		</form>
	</div>
</div>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
