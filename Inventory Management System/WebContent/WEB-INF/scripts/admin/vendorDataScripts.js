$(document).ready(function () {
   $.ajax({
      url: "showVendors",
      method: "GET",
      dataType: "json",
      success: function (data) {
         var tableBody = $("#vendorTable tbody");
         tableBody.empty(); // Clear existing table data

         data.forEach(function (vendor) {
            var row = $("<tr>");
            $("<td>").text(vendor.vendorId).appendTo(row);
            $("<td>").text(vendor.vendorName).appendTo(row);
            $("<td>").text(vendor.vendorAddress).appendTo(row);
            $("<td>").text(vendor.vendorPhone).appendTo(row);
            $("<td>").text(vendor.status).appendTo(row);
            row.appendTo(tableBody);
         });
      },
      error: function () {
         console.log("Error");
      }
   });
});

function getVendors() {
   $.ajax({
      url: "showVendors",
      method: "GET",
      dataType: "json",
      success: function (data) {
         var tableBody = $("#vendorTable tbody");
         tableBody.empty(); // Clear existing table data

         data.forEach(function (vendor) {
            var row = $("<tr>");
            $("<td>").text(vendor.vendorId).appendTo(row);
            $("<td>").text(vendor.vendorName).appendTo(row);
            $("<td>").text(vendor.vendorAddress).appendTo(row);
            $("<td>").text(vendor.vendorPhone).appendTo(row);
            $("<td>").text(vendor.status).appendTo(row);
            row.appendTo(tableBody);
         });
      },
      error: function () {
         console.log("Error");
      }
   });
}
$(document).ready(function () {
   $("#vendor-status").change(function () {
      console.log('click status');
      var status = $("#vendor-status").val();
      $.ajax({
         url: "vendorStatus",
         method: "POST",
         dataType: "json",
         data: {
            vendorStatus: status
         },
         success: function (data) {
            var tableBody = $("#vendorTable tbody");
            tableBody.empty(); // Clear existing table data
            console.log(data);
            data.forEach(function (vendor) {
               var row = $("<tr>");
               $("<td>").text(vendor.vendorId).appendTo(row);
               $("<td>").text(vendor.vendorName).appendTo(row);
               $("<td>").text(vendor.vendorAddress).appendTo(row);
               $("<td>").text(vendor.vendorPhone).appendTo(row);
               $("<td>").text(vendor.status).appendTo(row);
               row.appendTo(tableBody);
            });
         },
         error: function () {
            console.log("Error");
         }
      });
   });
});