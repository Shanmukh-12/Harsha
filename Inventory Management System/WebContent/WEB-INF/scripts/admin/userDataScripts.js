$(document).ready(function () {
   function applyFilter() {
      var userTypeFilter = $("#userTypeFilter").val();
      var userStatusFilter = $("#userStatusFilter").val();
      var url;
      if (userTypeFilter && userStatusFilter) {
         url = "getFilteredDataByTypeAndStatus";
      } else if (userTypeFilter) {
         url = "getFilteredDataByType";
      } else if (userStatusFilter) {
         url = "getFilteredDataByStatus";
      } else {
         url = "showUsers";
      }

      console.log(userTypeFilter);
      console.log(userStatusFilter);
      console.log(url);

      $.ajax({
         url: url,
         method: "POST",
         dataType: "json",
         data: {
            userType: userTypeFilter,
            userStatus: userStatusFilter
         },
         success: function (data) {
            // Handle the success response
            var userTableBody = $("#userTableBody");
            userTableBody.empty();

            data.forEach(function (user) {
               var row = $("<tr>");
               row.append($("<td>").text(user.userId));
               row.append($("<td>").text(user.userName));
               row.append($("<td>").text(user.userType));
               row.append($("<td>").text(user.status));
               userTableBody.append(row);
            });
         },
         error: function () {
            console.log("Error");
         }
      });
   }

   $("#applyFilterBtn").click(function () {
      applyFilter();
   });

   // Initial data load
   applyFilter();
});