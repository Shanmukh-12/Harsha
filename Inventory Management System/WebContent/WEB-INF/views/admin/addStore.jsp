<style>
.center {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}
</style>
<script>
	$(document)
			.ready(
					function() {
						console.log("Hellooo");

						function showSuccessMessage(event) {
							event.preventDefault();

							var storeId = document.getElementById("storeId").value;
							var storeName = document
									.getElementById("storeName").value;
							var storeAddress = document
									.getElementById("storeAddress").value;
							var storeContactNumber = document
									.getElementById("storeContactNumber").value;
							var status = document.getElementById("status").value;

							var confirmationMessage = "Store ID: "
									+ storeId
									+ "\nStore Name: "
									+ storeName
									+ "\nStore Address: "
									+ storeAddress
									+ "\nStore Contact Number: "
									+ storeContactNumber
									+ "\nStatus: "
									+ status
									+ "\n\nAre you sure you want to add this Store?";

							var userConfirmed = window
									.confirm(confirmationMessage);
							if (userConfirmed) {
								// Send the data to the backend using AJAX
								$
										.ajax({
											url : "saveStore",
											type : "POST",
											contentType : "application/json",
											data : JSON
													.stringify({
														"storeId" : storeId,
														"storeName" : storeName,
														"storeAddress" : storeAddress,
														"storeContactNumber" : storeContactNumber,
														"status" : status
													}),
											success : function(response) {
												alert("Store added successfully.");
												// Perform any additional actions after insertion
											},
											error : function() {
												alert("An error occurred while saving the store.");
											}
										});
							}
						}

						$("form").submit(showSuccessMessage);
					});
</script>
<div class="center">
	<div class="col-md-6">
		<h2 class="mb-4">Store Form</h2>
		<form>
			<div class="form-group">
				<label for="storeId">Store ID:</label> <input type="text"
					class="form-control" id="storeId" name="storeId"
					placeholder="Enter Store ID" required>
			</div>
			<div class="form-group">
				<label for="storeName">Store Name:</label> <input type="text"
					class="form-control" id="storeName" name="storeName"
					placeholder="Enter Store Name" required>
			</div>
			<div class="form-group">
				<label for="storeAddress">Store Address:</label>
				<textarea class="form-control" id="storeAddress" name="storeAddress"
					placeholder="Enter Store Address" required></textarea>
			</div>
			<div class="form-group">
				<label for="storeContact">Store Contact Number:</label> <input
					type="text" class="form-control" id="storeContactNumber"
					name="storeContactNumber" pattern="[6-9]\d{9}"
					title="Contact number must start with 6 - 9 and have 10 digits"
					placeholder="Enter Store Contact Number" required>
			</div>
			<div class="form-group">
				<label for="status">Store Status:</label> <select
					class="form-control" id="status" name="status">
					<option value="Active">Active</option>
				</select>
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</div>


