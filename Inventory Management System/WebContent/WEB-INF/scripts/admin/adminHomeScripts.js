$(document).ready(function () {
  wareHouse('warehousestock');
});

function clicked(data) {
   console.log("Clicked");
   $.ajax({
      url: data,
      method: "GET",
      success: function (response) {
         console.log("AJAX call successful");
         $("#content").html(response);
      },
      error: function () {
         console.log("AJAX call error");
      }
   });
}

function logout() {
   $.ajax({
      url: "logout",
      method: "GET",
      success: function (response) {
         console.log("Logout successful");
         console.log(response);
         location.href = "/inventory/";
      },
      error: function () {
         console.log("Logout error");
      }
   });
}

function wareHouse(containerurl) {
   $.ajax({
      url: containerurl,
      method: "GET",
      success: function (response) {
         console.log("AJAX call successful");

         $("#content").html(response);
         var ctx1 = document.getElementById('myChart');
         ctx1.width = 300; // Set the width
         ctx1.height = 130;
         var ctx = ctx1.getContext('2d');

         $.ajax({
            url: "getStock",
            type: "GET",
            dataType: "json",
            success: function (response) {
               // Handle the response from the servlet if needed
               console.log(response);
               var name = [];
               var stock = [];
               response.forEach(function (value) {
                  stock.push(value.stock.product_stock);
                  name.push(value.product.product_name);

               })


               // Define chart data
               var data = {
                  labels: name,
                  datasets: [{
                     label: 'stock',
                     data: stock,

                     backgroundColor: 'rgba(54, 162, 235, 0.5)', // Bar color
                     borderColor: 'rgba(54, 162, 235, 1)', // Border color
                     borderWidth: 1 // Border width
                  }]
               };

               var myChart = new Chart(ctx, {
                  type: 'bar',
                  data: data,
                  responsive: true,
                  maintainAspectRatio: false,
                  width: 100,
                  height: 100,
                  options: {
                     scales: {

                        y: {
                           beginAtZero: true
                        }
                     }
                  }
               });
            },
            error: function () {
               // Handle errors
               console.error("AJAX request failed.");
            }
         });


      },
      error: function () {
         console.log("AJAX call error");
      }
   });
}
var el = document.getElementById("wrapper");
var toggleButton = document.getElementById("menu-toggle");

toggleButton.onclick = function () {
   el.classList.toggle("toggled");
};