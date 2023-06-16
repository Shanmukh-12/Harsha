<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    // Send the data to the backend using AJAX
    $.ajax({
        url: "delete",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            vendorId: vendorId,

        }),
        success: function(response) {
        	 var confirmationMessage = "Vendor ID: " + vendorId +
             "\n\nAre you sure to delete this vendor?";
        	 
        	 return confirm(confirmationMessage);
        },
        error: function() {
            alert("An error occurred while updating the vendor.");
        }
    });

    return false; // Prevent form submission
}
</script>
<div class="center">
    <div class="col-md-6">
        <h2 class="mb-4">Delete Vendor</h2>
        <form action="deleteVendor" method="post" onsubmit="return deleteVendor()">
            <div class="form-group">
                <label for="vendorId">Vendor ID:</label>
                <select class="form-control" id="vendorId" name="vendorId" required>
                    <option value="">Select Vendor Name</option>
                    <c:forEach var="vendor" items="${vendors}">
                        <option value="${vendor.vendorId}">${vendor.vendorName} (${vendor.vendorId})</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

