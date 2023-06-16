<html>
<head>
	<style>
	.PurchasesClass
	{
	position:relative;
	left:620px;
	}
	.purchaseClass1
         {
         position:relative;
         left:620px;
         }
	</style>
	
    
</head>

<body>
    <h1 align="center">Purchases Orders Data</h1>
	<br><br>
	
	<label class="purchaseClass1">Select VendorID:</label>
      <select id="vendorId" class="purchaseClass1" align="center" >
         <option>897</option>
      </select>
      
      <label class="purchaseClass1">Select ExpectedDate:</label>
      <input type="date" id="expectedDate" class="purchaseClass1"><br><br>
	
	<label class="PurchasesClass">Select Purchase ID:</label>
	<select id="PurchasesId" class="PurchasesClass" align="center" >
		<option>782</option>
	</select>
	<br><br>
	<table class="table bg-white rounded shadow-sm  table-hover" id="example">
                            <thead>
                                <tr>
                                    <th scope="col">Purchase Date</th>
                                     <th scope="col">Purchase Amount</th>
				     <th scope="col">Vendor ID</th>
                                    <th scope="col">Expected Delivery</th>
                                    <th scope="col">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    
                                    <td>02-04-2006</td>
                                    <td>109333</td>
                                    <td>30</td>
                                    <td>08-04-2006</td>
                                    <td>process</td>
                                </tr>
                            </tbody>
                        </table>
</body>

</html>