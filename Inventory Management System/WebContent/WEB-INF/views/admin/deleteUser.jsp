<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    $.ajax({
        url: "getUser",
        method: "POST",
        dataType: "json",
        success: function(data) {
            console.log(data);
            var dropdown = $("#userType");
            dropdown.empty(); // Clear existing options

            var defaultOption = $("<option>").val("").text("Select User Type");
            dropdown.append(defaultOption);

            var inventoryFound = false;
            var procurementFound = false;

            data.forEach(function(user) {
                if (user.userType === "inventory" && !inventoryFound) {
                    var option = $("<option>").val(user.userType).text(user.userType);
                    dropdown.append(option);
                    inventoryFound = true;
                } else if (user.userType === "procurement" && !procurementFound) {
                    var option = $("<option>").val(user.userType).text(user.userType);
                    dropdown.append(option);
                    procurementFound = true;
                }
            });
        },
        error: function() {
            console.log("Error");
        }
    });
});

$(document).ready(function() {
    $("#userType").change(function() {
        var userType = $(this).val();
        if (userType) {
            $.ajax({
                url: "getUserData",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    "userType": userType
                }),
                success: function(response) {
                    console.log(response);
                    var userIdDropdown = $("#userId");
                    userIdDropdown.empty(); // Clear existing options

                    var defaultOption = $("<option>").val("").text("Select User Name");
                    userIdDropdown.append(defaultOption);

                    response.forEach(function(user) {
                        var option = $("<option>").val(user.userId).text(user.userId + " - " + user.userName);
                        userIdDropdown.append(option);
                    });
                },
                error: function() {
                    alert("An error occurred while fetching user information.");
                }
            });
        }
    });
});

function deleteUser() {
    // Retrieve form data
    var userId = $("#userId").val();

    // Confirmation dialog
    var confirmationMessage = "User ID: " + userId + "\n\nAre you sure you want to delete this user?";
    var userConfirmed = window.confirm(confirmationMessage);

    if (userConfirmed) {
        // Send the data to the backend using AJAX
        $.ajax({
            url: "deleteUserData",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "userId": userId,
            }),
            success: function(response) {
                alert("User deleted successfully.");
                // Perform any additional actions after deletion
            },
            error: function() {
                alert("An error occurred while deleting the user.");
            }
        });
    }

    return false; // Prevent form submission
}
</script>
<div class="center">
   <div class="col-md-6">
      <h2 class="mb-4">Delete User</h2>
      <form  method="post" onsubmit="return deleteUser()">
         <div class="form-group">
            <label for="userType">User Type</label> 
            <select class="form-control" id="userType" name="userType" required>
               <option value="">Select User Type</option>
            </select>
         </div>
         <div class="form-group">
            <label for="userId">User Id:</label> 
            <select class="form-control" id="userId" name="userId" required>
               <option value="">Select User Name</option>
            </select>
         </div>
         <button type="submit" class="btn btn-danger">Delete</button>
      </form>
   </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
