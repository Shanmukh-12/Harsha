<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="./adminHome/scripts/updateVendorScripts.js"></script>
<div class="center">
	<div class="col-md-6">
		<h2 class="mb-4">Vendor Form</h2>
		<form>
			<div class="form-group">
				<label for="vendorId">Vendor ID:</label> <select
					class="form-control" id="vendorId" name="vendorId" required>
					<option value="">Select Vendor ID</option>
				</select>
			</div>

			<div class="form-group">
				<label for="vendorName">Vendor Name:</label> <input type="text"
					class="form-control" id="vendorName" name="vendorName"
					placeholder="Enter Vendor Name" required>
			</div>
			<div class="form-group">
				<label for="vendorAddress">Vendor Address:</label>
				<textarea class="form-control" id="vendorAddress"
					name="vendorAddress" placeholder="Enter Vendor Address" required></textarea>
			</div>
			<div>
				<label for="vendorContact">Vendor Contact Number:</label> <input
					type="text" class="form-control" id="vendorContact"
					name="vendorContact" pattern="[6-9]\d{9}"
					title="Contact number must start with 6 - 9 and have 10 digits"
					placeholder="Enter Vendor Contact Number" required>
			</div>


			<button type="submit" class="btn btn-primary">Update</button>
		</form>
	</div>
</div>
