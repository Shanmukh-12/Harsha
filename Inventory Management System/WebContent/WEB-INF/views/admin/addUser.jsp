<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./adminHome/scripts/addUserScripts.js"></script>
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



