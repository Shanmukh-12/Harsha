$(document).ready(function () {
   $.ajax({
      url: "getVendor",
      method: "POST",
      dataType: "json",
      success: function (data) {
         console.log(data);
         var dropdown = $("#vendorId");
         dropdown.empty(); // Clear existing options

         var defaultOption = $("<option>").val("").text("Select Vendor ID");
         dropdown.append(defaultOption);

         data.forEach(function (vendor) {
            var option = $("<option>").val(vendor.vendorId).text("{" + vendor.vendorId + "} - " + vendor.vendorName);
            dropdown.append(option);
         });
      },
      error: function () {
         console.log("Error");
      }
   });
});
$(document).ready(function () {
   $("#vendorId").change(function () {
      var vendorId = $(this).val();
      if (vendorId) {
         $.ajax({
            url: "getVendorData",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
               "vendorId": vendorId
            }),
            success: function (response) {
               $("#vendorName").val(response.vendorName);
               $("#vendorAddress").val(response.vendorAddress);
               $("#vendorContact").val(response.vendorPhone);
            },
            error: function () {
               alert("An error occurred while fetching vendor information.");
            }
         });
      }
   });
});

$(document)
   .ready(
      function () {
         console.log("Hellooo");

         function showSuccessMessage(event) {
            event.preventDefault();

            var vendorId = $("#vendorId").val();
            var vendorName = $("#vendorName").val();
            var vendorAddress = $("#vendorAddress").val();
            var vendorContact = $("#vendorContact").val();

            var confirmationMessage = "Vendor ID: " + vendorId +
               "\nVendor Name: " + vendorName +
               "\nVendor Address: " + vendorAddress +
               "\nVendor Contact: " + vendorContact +
               "\n\nAre you sure to update this vendor?";
            var userConfirmed = window
               .confirm(confirmationMessage);

            // Send the data to the backend using AJAX
            $.ajax({
               url: "updateVendor",
               type: "POST",
               contentType: "application/json",
               data: JSON.stringify({
                  "vendorId": vendorId,
                  "vendorName": vendorName,
                  "vendorAddress": vendorAddress,
                  "vendorPhone": vendorContact

               }),
               success: function (response) {
                  alert("vendor updated successfully.");

               },
               error: function () {
                  alert("An error occurred while updating the vendor.");
               }
            });

         }


         $("form").submit(showSuccessMessage);
      });