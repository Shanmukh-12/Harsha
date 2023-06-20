    <style>
        .container {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0 auto;
            max-width: 600px;
        }

        h1 {
            margin-bottom: 20px;
        }

        #indentForm{
            display: flex;
            margin-bottom: 20px;
            align-items: center;
            flex-direction: column;
        }

        label {
            display: inline-block;
            width: 100px;
            text-align: right;
            vertical-align: middle;
        }

        input[type="text"] {
            width: 200px;
            padding: 5px;
            vertical-align: middle;
        }

        button {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            vertical-align: middle;
        }

        table {
            width: auto;
            border-collapse: collapse;
            margin-bottom: 20px;
            margin-left: auto;
            margin-right: auto;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        .deleteButton {
            padding: 5px 10px;
            background-color: #f44336;
            color: white;
            border: none;
            cursor: pointer;
            vertical-align: middle;
        }

        .error {
            color: red;
            font-weight: bold;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  function getTableData() {
    const table = document.getElementById('returnsTable');
    console.log(table.rows[1]);

    // Create an array to store the table data
    const tableData = [];

    const headerRow = table.rows[0];

    // Iterate through each row of the table
    for (let i = 1; i < table.rows.length; i++) {
      const row = table.rows[i];
      const rowData = {};

      // Iterate through each cell of the row excluding the "product_name" column
      for (let j = 0; j < row.cells.length - 1; j++) {
        const cell = row.cells[j];
        const cellHeader = headerRow.cells[j].id; // Use textContent to get the header

        // Assign the cell text content
        rowData[cellHeader] = cell.textContent;
      }

      // Add row data to the tableData array
      tableData.push(rowData);
    }

    const jsonData = {};
    jsonData['storeID'] = document.getElementById('storeId').value;
    jsonData['productsList'] = tableData;
    return jsonData;
  }

  function createReturn() {
    var table = document.getElementById('returnsTable');
    var tbody = table.getElementsByTagName('tbody')[0];
    var rowCount = tbody.rows.length;
    console.log(tbody);
    console.log(rowCount);
    // Check if the table has data
    if (rowCount <= 1) {
      alert('Table is empty. Add data to proceed.');
      return;
    }

    var data = getTableData();
    const jsonData = JSON.stringify(data);
    console.log(jsonData);
    $.ajax({
      url: 'newCreateStoreReturn',
      method: 'post',
      data: { jsonData: jsonData },
      success: function (page) {
        console.log('Success');
        alert('Return successful!'); // Display alert message

        // Remove table data
        const table = document.getElementById('returnsTable');
        const tbody = table.getElementsByTagName('tbody')[0];
        tbody.innerHTML = '<tr class="no-data"><td colspan="3">No data</td></tr>';
        $('#storeId').val('');
      },
      error: function (page) {
        alert('Invalid Data');
      },
    });
  }

  $(document).ready(function () {
    var productIds = [];

    $('#addButton').click(function () {
      var productId = $('#productId').val();
      var quantity = $('#quantity').val();

      if (!productId || productIds.includes(productId)) {
        $('#productId').addClass('error');
        return;
      } else {
        $('#productId').removeClass('error');
      }

      var row =
        '<tr><td>' +
        productId +
        '</td><td>' +
        quantity +
        '</td><td><button class="deleteButton">Delete</button></td></tr>';
      $('#returnsTable tbody').append(row);

      productIds.push(productId);
      $('#returnsTable tbody tr.no-data').remove(); // Remove "No data" row if it exists
      $('#productId').val('');
      $('#quantity').val('');
    });

    $(document).on('click', '.deleteButton', function () {
      var productId = $(this).closest('tr').find('td:first').text();
      productIds = productIds.filter(function (id) {
        return id !== productId;
      });
      $(this).closest('tr').remove();

      if ($('#returnsTable tbody tr').length === 0) {
        $('#returnsTable tbody').append(
          '<tr class="no-data"><td colspan="3">No data</td></tr>'
        );
      }
    });
  });
</script>
<div class="container" align="center">
  <h1>Store Returns</h1><br /><br />

  <form id="storeReturnsForm" onsubmit="createReturn(); return false;">
    <div>
      <label for="storeId">Store ID:</label>
      <input type="text" id="storeId" name="storeId" required /><br /><br />
    </div>
    <div>
      <label for="productId">Product ID:</label>
      <input type="text" id="productId" name="productId"  /><br /><br />
    </div>
    <div>
      <label for="quantity">Quantity:</label>
      <input type="text" id="quantity" name="quantity" /><br /><br />
    </div>
    <button type="button" id="addButton">Add</button><br /><br />
    <table id="returnsTable" border="1">
      <thead>
        <tr>
          <th id="productId">Product ID</th>
          <th id="quantity">Quantity</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr class="no-data">
          <td colspan="3">No data</td>
        </tr>
      </tbody>
    </table>
    <input type="submit" />
  </form>
</div>
    