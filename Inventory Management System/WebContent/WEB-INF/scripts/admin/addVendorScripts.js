$(document)
   .ready(
      function () {
         function showSuccessMessage(event) {
            event.preventDefault();

            var vendorName = document.getElementById("vendorName").value;
            var vendorAddress = document.getElementById("vendorAddress").value;
            var vendorPhone = document.getElementById("vendorPhone").value;
            var vendorStatus = document.getElementById("vendorStatus").value;

            var confirmationMessage =
               "Vendor Name: " + vendorName +
               "\nVendor Address: " + vendorAddress +
               "\nVendor Phone: " + vendorPhone +
               "\nVendor Status: " + vendorStatus +
               "\n\nAre you sure to add this vendor?";
            var userConfirmed = window
               .confirm(confirmationMessage);
            if (userConfirmed) {
               // Send the data to the backend using AJAX
               $
                  .ajax({
                     url: "saveVendor",
                     type: "POST",
                     contentType: "application/json",
                     data: JSON
                        .stringify({
                           "vendorName": vendorName,
                           "vendorAddress": vendorAddress,
                           "vendorPhone": vendorPhone,
                           "status": vendorStatus
                        }),
                     success: function (response) {
                        alert("vendor added successfully.");
                        // Perform any additional actions after insertion
                     },
                     error: function () {
                        alert("An error occurred while saving the vendor.");
                     }
                  });
            }
         }

         $("form").submit(showSuccessMessage);
      });