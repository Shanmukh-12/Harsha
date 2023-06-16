<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Inventory</title>
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

                var row = '<tr><td>' + productId + '</td><td>' + quantity + '</td><td><button class="deleteButton">Delete</button></td></tr>';
                $('#inventoryTable tbody').append(row);

                productIds.push(productId);
                $('#inventoryTable tbody tr.no-data').remove(); // Remove "No data" row if it exists
            });

            $(document).on('click', '.deleteButton', function () {
                var productId = $(this).closest('tr').find('td:first').text();
                productIds = productIds.filter(function (id) {
                    return id !== productId;
                });
                $(this).closest('tr').remove();

                if ($('#inventoryTable tbody tr').length === 0) {
                    $('#inventoryTable tbody').append('<tr class="no-data"><td colspan="3">No data</td></tr>');
                }
            });
        });
    </script>
</head>
<body>
    <div class="container" align="center">
        <h1>Store Returns</h1><br><br>

        <form id="indentForm">
            <div>
                <label for="storeId">Store ID:</label>
                <input type="text" id="storeId" name="storeId" required><br><br>
            </div>
            <table id="inventoryTable" border="1">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="no-data">
                        <td colspan="3">No data</td>
                    </tr>
                </tbody>
            </table>
            <div>
                <label for="productId">Product ID:</label>
                <input type="text" id="productId" name="productId" required><br><br>
            </div>
            <div>
                <label for="quantity">Quantity:</label>
                <input type="text" id="quantity" name="quantity" required><br><br>
            </div>
            <button type="button" id="addButton">Add</button>
        </form>
    </div>
</body>
</html>
