<!DOCTYPE html>
<html>

<head>
	<style>
		.grnClass
		{
			position:relative;
			left:620px;
		}
		.grnfilterClass
		{
			position:relative;
			left:1040px;
		}
	</style>
    
	</head>

<body>
    <h1 align="center">GRN Data</h1>
	<br><br>
	<br><br>
	<label class="grnClass">Order Recieved date</label>
	<input type="date" id="ordrecdate" class="grnClass">
	
	<label class="grnClass">Vendor Id</label>
	<select id="vendorId" class="grnClass" >
		<option>677</option>
	</select>

	<label class="grnClass">Grn value</label>
	<select id="grnvalue" class="grnClass" >
		<option>34567</option>
		
	</select>
	<br><br>
	<label class="grnClass">Select GRN ID:</label>
	<select id="grnId" class="grnClass" align="center" >
		<option>7983834</option>
	</select>
	
	
	
	<table class="table bg-white rounded shadow-sm  table-hover">
                            <thead>
                                <tr>
                                    <th scope="col" width="50">#</th>
                                    <th scope="col">Product Id</th>
									<th scope="col">Batch NO</th>
									<th scope="col">Ordered Quantity</th>
									<th scope="col">Recieved Quantity</th>
									<th scope="col">Bonus</th>
									<th scope="col">Total Quantity</th>
									<th scope="col">Cost</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>1</td>
                                    <td>8634</td>
									<td>60</td>
									<td>30</td>
									<td>2</td>
									<td>32</td>
									<td>76836</td>
                                </tr>
                            </tbody>
                        </table>
</body>

</html>