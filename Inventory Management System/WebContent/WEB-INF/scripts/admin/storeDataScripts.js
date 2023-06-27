$(document).ready(function () {
   $.ajax({
      url: "showStores",
      method: "GET",
      dataType: "json",
      success: function (data) {
         var tableBody = $("<tbody>");

         data.forEach(function (store) {
            var row = $("<tr>");
            $("<td>").text(store.storeId).appendTo(row);
            $("<td>").text(store.storeName).appendTo(row);
            $("<td>").text(store.storeAddress).appendTo(row);
            $("<td>").text(store.storeContactNumber).appendTo(row);
            $("<td>").text(store.status).appendTo(row);
            row.appendTo(tableBody);
         });

         // Replace the existing table body with the new one
         $("#storeTable tbody").replaceWith(tableBody);
      },
      error: function () {
         console.log("Error");
      }
   });
});

function getStores() {
   $.ajax({
      url: "showStores",
      method: "GET",
      dataType: "json",
      success: function (data) {
         var tableBody = $("<tbody>");

         data.forEach(function (store) {
            var row = $("<tr>");
            $("<td>").text(store.storeId).appendTo(row);
            $("<td>").text(store.storeName).appendTo(row);
            $("<td>").text(store.storeAddress).appendTo(row);
            $("<td>").text(store.storeContactNumber).appendTo(row);
            $("<td>").text(store.status).appendTo(row);
            row.appendTo(tableBody);
         });

         // Replace the existing table body with the new one
         $("#storeTable tbody").replaceWith(tableBody);
      },
      error: function () {
         console.log("Error");
      }
   });
}
$(document).ready(function () {
   $("#store-status").change(function () {
      console.log('click status');
      var status = $("#store-status").val();
      $.ajax({
         url: "storeStatus",
         method: "POST",
         dataType: "json",
         data: {
            storeStatus: status
         },
         success: function (data) {
            var tableBody = $("<tbody>");

            data.forEach(function (store) {
               var row = $("<tr>");
               $("<td>").text(store.storeId).appendTo(row);
               $("<td>").text(store.storeName).appendTo(row);
               $("<td>").text(store.storeAddress).appendTo(row);
               $("<td>").text(store.storeContactNumber).appendTo(row);
               $("<td>").text(store.status).appendTo(row);
               row.appendTo(tableBody);
            });

            // Replace the existing table body with the new one
            $("#storeTable tbody").replaceWith(tableBody);
         },
         error: function () {
            console.log("Error");
         }
      });
   });
});