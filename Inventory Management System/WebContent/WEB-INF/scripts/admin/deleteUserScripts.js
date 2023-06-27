$(document).ready(function () {
   $.ajax({
      url: "getUser",
      method: "POST",
      dataType: "json",
      success: function (data) {
         console.log(data);
         var dropdown = $("#userType");
         dropdown.empty(); // Clear existing options

         var defaultOption = $("<option>").val("").text("Select User Type");
         dropdown.append(defaultOption);

         var inventoryFound = false;
         var procurementFound = false;

         data.forEach(function (user) {
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
      error: function () {
         console.log("Error");
      }
   });
});

$(document).ready(function () {
   $("#userType").change(function () {
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
            success: function (response) {
               console.log(response);
               var userIdDropdown = $("#userId");
               userIdDropdown.empty(); // Clear existing options

               var defaultOption = $("<option>").val("").text("Select User Name");
               userIdDropdown.append(defaultOption);

               response.forEach(function (user) {
                  var option = $("<option>").val(user.userId).text(user.userId + " - " + user.userName);
                  userIdDropdown.append(option);
               });
            },
            error: function () {
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
         success: function (response) {
            alert("User deleted successfully.");
            // Perform any additional actions after deletion
         },
         error: function () {
            alert("An error occurred while deleting the user.");
         }
      });
   }

   return false; // Prevent form submission
}