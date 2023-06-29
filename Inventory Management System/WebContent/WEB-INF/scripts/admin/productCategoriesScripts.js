$(document).ready(function () {
   $.ajax({
      url: "getProductCategories",
      method: "POST",
      dataType: "json",
      success: function (data) {
         console.log(data);

         // Populate options from response data
         data.forEach(function (category) {
            $("<option>").val(category.productCategoryId).text(category.productCategoryName).appendTo("#categorySelect");
         });
      },
      error: function () {
         console.log("Error");
      }
   })
});

$(document).ready(function () {
   $("#categorySelect").on("change", function () {
      var selectedCategoryId = $(this).val();
      var categoryId = {};
      categoryId["categoryId"] = selectedCategoryId;
      console.log(categoryId);
      $.ajax({
         url: "getProductStockData",
         method: "POST",
         data: {
           categoryId : $(this).val()
         },
         success: function (data) {
            console.log(data);
            $("#productTableBody").empty();
            data.forEach(function (product) {
               var row = "<tr>";
               row += "<td>" + product.productId + "</td>";
               row += "<td>" + product.productName + "</td>";
               row += "<td>" + product.batchNo + "</td>";
               row += "<td>" + product.stock + "</td>";
               row += "<td>" + product.purchasePrice + "</td>";
               row += "<td>" + product.costPrice + "</td>";
               row += "<td>" + product.mrp + "</td>";
               row += "</tr>";

               $("#productTableBody").append(row);
            });
         },
         error: function () {
            console.log("Error");
         }
      });
   });
});