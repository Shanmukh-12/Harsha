<style>
.center {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	$(document)
			.ready(
					function() {
						console.log("Hellooo");

						function showSuccessMessage(event) {
							event.preventDefault();

							var userId = document.getElementById("userId").value;
							var userName = document.getElementById("userName").value;
							var userPassword = document
									.getElementById("userPassword").value;
							var userType = document.getElementById("userType").value;
							var status = document.getElementById("status").value;

							var confirmationMessage = "User Id:" + userId
									+ "\User Name: " + userName
									+ "\nUser Password: " + userPassword
									+ "\nUser Type: " + userType
									+ "\nUserStatus: " + status
									+ "\n\nAre you sure to add this User?";
							var userConfirmed = window
									.confirm(confirmationMessage);
							if (userConfirmed) {
								// Send the data to the backend using AJAX
								$.ajax({
									  url: "saveUser",
									  type: "POST",
									  data: JSON.stringify({
									    "userId": userId,
									    "userPassword": userPassword,
									    "userName": userName,
									    "userType": userType,
									    "status": status
									  }),
									  contentType: "application/json", // Set the content type to JSON
									  success: function(response) {
									    alert("User added successfully.");
									    // Perform any additional actions after insertion
									  },
									  error: function() {
									    alert("An error occurred while saving the user.");
									  }
									});

							}
						}

						$("form").submit(showSuccessMessage);
					});
</script>
<div class="center">
	<div class="col-md-6">
		<h2 class="mb-4">User Form</h2>
		<form method="POST">
			<div class="form-group">
				<label for="userId">User ID:</label> <input type="text"
					class="form-control" id="userId" name="userId"
					placeholder="Enter User ID" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="userPassword" name="userPassword"
					pattern="^(?=.*\d)(?=.*[A-Z])(?=.*\W)(?=.*[a-z]).{8,}$"
					title="Password must start with a capital letter, contain at least one lowercase letter, one uppercase letter, one special symbol, and one number"
					required>
			</div>
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="userName" name="userName"
					placeholder="Enter Username" required>
			</div>
			<div class="form-group">
				<label for="userType">User Type:</label> <select
					class="form-control" id="userType" name="userType">
					<option value="procurement">Procurement</option>
					<option value="inventory">Inventory</option>
				</select>
			</div>
			<div class="form-group">
				<label for="Status">User Status:</label> <select
					class="form-control" id="status" name="status">
					<option value="active">Active</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</div>



