<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    #product-details-table{
      background-color: white;
      height: 50px;
      overflow-y: scroll;
      width: 1000px;
    }
    
    #htag{
      position: relative;
      top: 20px;
    }
    
    table {
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    th, td {
      text-align: center;
      border: 1px solid black;
      padding: 8px;
    }

    #first_table {
      height: 200px;
      overflow-y: scroll;
    }

    #products-dropdown {
      display: flex;
      margin-left: 650px;
      padding-bottom: 5px;
    }
    
    #products-dropdown label {
      display: block;
      font-weight: bold;
      color: #333;
      margin-bottom: 5px;
    }
  </style>
</head>
<body>
  <h2 align="center">Indents List</h2>
  <div style="margin-bottom: 20px; margin-left: 50px;">
    <div id="products-dropdown" align="right">
      <label for="product-category">Product Category</label>
      <select id="product-category" onchange="filterTable()">
        <option value="">All</option>
      </select>
    </div>
    
    <div id="first_table">
      <table id="product-details-table">
        <thead>
          <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Batch No.</th>
            <th>Category Name</th>
            <th>Stock</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>101</td>
            <td>Pears Soap</td>
            <td>14268</td>
            <td>Soaps</td>
            <td>50</td>
          </tr>
          <tr>
            <td>102</td>
            <td>Santoor Soap</td>
            <td>14267</td>
            <td>Soaps</td>
            <td>50</td>
          </tr>
          <tr>
            <td>103</td>
            <td>iqoo z7</td>
            <td>14266</td>
            <td>Mobiles</td>
            <td>50</td>
          </tr>
          <tr>
            <td>104</td>
            <td>Sunflower oil</td>
            <td>14269</td>
            <td>Oils</td>
            <td>50</td>
          </tr>
          <tr>
            <td>101</td>
            <td>Kurnool oil</td>
            <td>14268</td>
            <td>Oils</td>
            <td>50</td>
          </tr>
          <tr>
            <td>101</td>
            <td>Pears Soap</td>
            <td>14268</td>
            <td>Soaps</td>
            <td>50</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

  <script>
    // Populate dropdown options from specific table column
    function populateDropdown() {
      var dropdown = document.getElementById("product-category");
      var table = document.getElementById("product-details-table");
      var columnIndex = 3; // Specify the column index to retrieve values from (zero-based)

      var options = new Set();

      // Collect unique values from specific table column
      for (var i = 1; i < table.rows.length; i++) {
        var cell = table.rows[i].cells[columnIndex];
        if (cell) {
          options.add(cell.textContent.trim());
        }
      }

      // Clear existing options
      dropdown.innerHTML = "";

      // Populate dropdown with options
      var defaultOption = document.createElement("option");
      defaultOption.value = "";
      defaultOption.textContent = "All";
      dropdown.appendChild(defaultOption);

      options.forEach(function (value) {
        var option = document.createElement("option");
        option.value = value;
        option.textContent = value;
        dropdown.appendChild(option);
      });
    }

    // Filter table rows based on dropdown selection
    function filterTable() {
      var dropdown = document.getElementById("product-category");
      var selectedValue = dropdown.value.toLowerCase();
      var table = document.getElementById("product-details-table");

      // Iterate through table rows
      for (var i = 1; i < table.rows.length; i++) {
        var row = table.rows[i];
        var visible = false;

        // Get the cell value of the "Category Name" column
        var cell = row.cells[3];
        var cellValue = cell.textContent.toLowerCase();

        if (cellValue.includes(selectedValue) || selectedValue === "") {
          visible = true;
        }

        // Show/hide row based on visibility
        row.style.display = visible ? "" : "none";
      }
    }

    // Initial population of dropdown options
    populateDropdown();
  </script>
</body>
</html>
