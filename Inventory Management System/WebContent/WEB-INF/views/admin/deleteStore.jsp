<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="./adminHome/scripts/deleteStoreScripts.js"></script>
<div class="center">
	<div class="col-md-6">
		<h2 class="mb-4">Delete Store</h2>
		<form  method="post"
			onsubmit="return deleteStore()">
			<div class="form-group">
				<label for="storeId">Store Id:</label> <select class="form-control"
					id="storeId" name="storeId" required>
					<option value="">Select Store Name</option>
				</select>
			</div>

			<button type="submit" class="btn btn-danger">Delete</button>
		</form>
	</div>
</div>


