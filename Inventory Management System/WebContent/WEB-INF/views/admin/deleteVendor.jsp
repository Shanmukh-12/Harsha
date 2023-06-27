<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="./adminHome/scripts/deleteVendorScripts.js"></script>
<div class="center">
    <div class="col-md-6">
        <h2 class="mb-4">Delete Vendor</h2>
        <form method="post" onsubmit="return deleteVendor();">
            <div class="form-group">
                <label for="vendorId">Vendor ID:</label>
                <select class="form-control" id="vendorId" name="vendorId" required>
                </select>
            </div>
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
    </div>
</div>
