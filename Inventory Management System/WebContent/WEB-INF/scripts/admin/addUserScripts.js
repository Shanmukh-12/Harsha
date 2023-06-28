$(document)
   .ready(
      function () {
         function showSuccessMessage(event) {
            event.preventDefault();

            var userId = document.getElementById("userId").value;
            var userName = document.getElementById("userName").value;
            var userPassword = document
               .getElementById("userPassword").value;
            var userType = document.getElementById("userType").value;
            var status = document.getElementById("status").value;

            var confirmationMessage = "User Id:" + userId +
               "\User Name: " + userName +
               "\nUser Password: " + userPassword +
               "\nUser Type: " + userType +
               "\nUserStatus: " + status +
               "\n\nAre you sure to add this User?";
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
                  success: function (response) {
                     alert("User added successfully.");
                     // Perform any additional actions after insertion
                  },
                  error: function () {
                     alert("An error occurred while saving the user.");
                  }
               });

            }
         }

         $("form").submit(showSuccessMessage);
      });