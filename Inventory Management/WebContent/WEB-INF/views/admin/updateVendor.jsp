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
$(document).ready(function() {
    $("#vendorId").change(function() {
        var vendorId = $(this).val();
        if (vendorId) {
            $.ajax({
                url: "getVendorData",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({
                    "vendorId": vendorId
                }),
                success: function(response) {
                    $("#vendorName").val(response.vendorName);
                    $("#vendorAddress").val(response.vendorAddress);
                    $("#vendorContact").val(response.vendorPhone);
                },
                error: function() {
                    alert("An error occurred while fetching vendor information.");
                }
            });
        }
    });
});


    function updateVendor() {
        // Retrieve form data
        var vendorId = $("#vendorId").val();
        var vendorName = $("#vendorName").val();
        var vendorAddress = $("#vendorAddress").val();
        var vendorContact = $("#vendorContact").val();
       
        // Send the data to the backend using AJAX
        $.ajax({
            url: "updateVendor",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                vendorId: vendorId,
                vendorName: vendorName,
                vendorAddress: vendorAddress,
                vendorPhone: vendorContact

            }),
            success: function(response) {
            	 var confirmationMessage = "Vendor ID: " + vendorId +
                 "\nVendor Name: " + vendorName +
                 "\nVendor Address: " + vendorAddress +
                 "\nVendor Contact: " + vendorContact +
                 "\n\nAre you sure to update this vendor?";
            	 
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
        <h2 class="mb-4">Vendor Form</h2>
        <form onsubmit="return updateVendor()">
            <div class="form-group">
                <label for="vendorId">Vendor ID:</label>
                <select class="form-control" id="vendorId" name="vendorId" required>
                    <option value="">Select Vendor ID</option>
                    <c:forEach var="vendor" items="${vendors}">
                        <option value="${vendor.vendorId}">${vendor.vendorId}</option>
                    </c:forEach>
                </select>
            </div>

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
                <input type="text" class="form-control" id="vendorContact" name="vendorContact" pattern="[6-9]\d{9}" title="Contact number must start with 6 - 9 and have 10 digits" placeholder="Enter Vendor Contact Number" required>
            </div>

           
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
