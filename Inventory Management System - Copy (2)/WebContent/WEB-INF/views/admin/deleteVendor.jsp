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
function deleteVendor() {
	// Retrieve form data
	var vendorId = $("#vendorId").val();

	// Confirmation dialog
	var confirmationMessage = "Vendor ID: " + vendorId
			+ "\n\nAre you sure you want to delete this vendor?";
	var userConfirmed = window.confirm(confirmationMessage);

	if (userConfirmed) {
		// Send the data to the backend using AJAX
		$.ajax({
			url : "delete",
			type : "POST",
			contentType : "application/json",
			data : JSON.stringify({
				"vendorId" : vendorId,
			}),
			success : function(response) {
				alert("vendor deleted successfully.");
				// Perform any additional actions after deletion
			},
			error : function() {
				alert("An error occurred while deleting the vendor.");
			}
		});
	}

	return false; 
}
</script>
<div class="center">
	<div class="col-md-6">
		<h2 class="mb-4">Delete Vendor</h2>
		<form method="post" onsubmit="deleteVendor()") >
			<div class="form-group">
				<label for="vendorId">Vendor ID:</label> <select
					class="form-control" id="vendorId" name="vendorId" required>
					<option value="">Select Vendor Name</option>
					<c:forEach var="vendor" items="${vendors}">
						<option value="${vendor.vendorId}">${vendor.vendorName}
							(${vendor.vendorId})</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-danger">Delete</button>
		</form>
	</div>
</div>

