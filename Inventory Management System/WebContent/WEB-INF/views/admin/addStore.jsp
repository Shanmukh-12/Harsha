<script src="./adminHome/scripts/addStoreScripts.js"></script>
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


