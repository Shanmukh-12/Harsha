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

							  var vendorName = document.getElementById("vendorName").value;
						        var vendorAddress = document.getElementById("vendorAddress").value;
						        var vendorContact = document.getElementById("vendorContact").value;
						        var vendorStatus = document.getElementById("vendorStatus").value;

						        var confirmationMessage =
						            "Vendor Name: " + vendorName +
						            "\nVendor Address: " + vendorAddress +
						            "\nVendor Contact: " + vendorContact +
						            "\nVendor Status: " + vendorStatus +
						            "\n\nAre you sure to add this vendor?";
							var userConfirmed = window
									.confirm(confirmationMessage);
							if (userConfirmed) {
								// Send the data to the backend using AJAX
								$
										.ajax({
											url : "saveVendor",
											type : "POST",
											contentType : "application/json",
											data : JSON
													.stringify({
														"vendorName" : vendorName,
														"vendorAddress" : vendorAddress,
														"vendorPhone" : vendorContact,
														"status" : vendorStatus
													}),
											success : function(response) {
												alert("vendor added successfully.");
												// Perform any additional actions after insertion
											},
											error : function() {
												alert("An error occurred while saving the vendor.");
											}
										});
							}
						}

						$("form").submit(showSuccessMessage);
					});
</script>
<div class="container center">
        <div class="col-md-6">
            <h2 class="mb-4">Vendor Form</h2>
          <form method="POST">
               
                <div class="form-group">
                    <label for="vendorName">Vendor Name:</label>
                    <input type="text" class="form-control" id="vendorName" name="vendorName" placeholder="Enter Vendor Name" required>
                </div>
                <div class="form-group">
                    <label for="vendorAddress">Vendor Address:</label>
                    <textarea class="form-control" id="vendorAddress" name="vendorAddress" placeholder="Enter Vendor Address" required></textarea>
                </div>
                <div>
                    <label for="vendorContact">Vendor Contact Number:</label>
                    <input type="text" class="form-control" id="vendorContact" name="vendorPhone" pattern="[6-9]\d{9}" title="Contact number must start with 6 - 9 and have 10 digits" placeholder="Enter Vendor Contact Number" required>
                </div>
                <div class="form-group">
                    <label for="vendorStatus">Vendor Status:</label>
                    <select class="form-control" id="vendorStatus" name="status">
                        <option value="active">Active</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

