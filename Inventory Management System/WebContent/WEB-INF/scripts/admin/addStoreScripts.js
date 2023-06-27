$(document)
   .ready(
      function () {
         function showSuccessMessage(event) {
            event.preventDefault();

            var storeId = document.getElementById("storeId").value;
            var storeName = document
               .getElementById("storeName").value;
            var storeAddress = document
               .getElementById("storeAddress").value;
            var storeContactNumber = document
               .getElementById("storeContactNumber").value;
            var status = document.getElementById("status").value;

            var confirmationMessage = "Store ID: " +
               storeId +
               "\nStore Name: " +
               storeName +
               "\nStore Address: " +
               storeAddress +
               "\nStore Contact Number: " +
               storeContactNumber +
               "\nStatus: " +
               status +
               "\n\nAre you sure you want to add this Store?";

            var userConfirmed = window
               .confirm(confirmationMessage);
            if (userConfirmed) {
               // Send the data to the backend using AJAX
               $
                  .ajax({
                     url: "saveStore",
                     type: "POST",
                     contentType: "application/json",
                     data: JSON
                        .stringify({
                           "storeId": storeId,
                           "storeName": storeName,
                           "storeAddress": storeAddress,
                           "storeContactNumber": storeContactNumber,
                           "status": status
                        }),
                     success: function (response) {
                        alert("Store added successfully.");
                        // Perform any additional actions after insertion
                     },
                     error: function () {
                        alert("An error occurred while saving the store.");
                     }
                  });
            }
         }

         $("form").submit(showSuccessMessage);
      });