$(document).ready(function () {
   $.ajax({
      url: "getStore",
      method: "POST",
      dataType: "json",
      success: function (data) {
         console.log(data);
         var dropdown = $("#storeId");
         dropdown.empty(); // Clear existing options

         var defaultOption = $("<option>").val("").text("Select Store ID");
         dropdown.append(defaultOption);

         data.forEach(function (store) {
            var option = $("<option>").val(store.storeId).text("{" + store.storeId + "} - " + store.storeName);
            dropdown.append(option);
         });
      },
      error: function () {
         console.log("Error");
      }
   });
});

function deleteStore() {
   // Retrieve form data
   var storeId = $("#storeId").val();

   // Confirmation dialog
   var confirmationMessage = "Store ID: " + storeId +
      "\n\nAre you sure you want to delete this store?";
   var userConfirmed = window.confirm(confirmationMessage);

   if (userConfirmed) {
      // Send the data to the backend using AJAX
      $.ajax({
         url: "deleteStoreData",
         type: "POST",
         contentType: "application/json",
         data: JSON.stringify({
            storeId: storeId,
         }),
         success: function (response) {
            alert("store deleted successfully.");
            // Perform any additional actions after deletion
         },
         error: function () {
            alert("An error occurred while deleting the user.");
         }
      });
   }

   return false; // Prevent form submission
}