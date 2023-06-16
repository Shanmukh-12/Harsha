<html>
<head>
	<style>
	.prnClass
	{
	position:relative;
	left:620px;
	}
	.prnfilterClass
	{
	position:relative;
	left:1040px;
	}
	.prnfilterClass2
	{
	position:relative;
	left:1020px;
	}
	.prnfilterClass1
	{
	position:relative;
	left:460px;
	}
	</style>
    
</head>

<body>
    <h1 align="center">PRN Data</h1>
	<br><br>
		<label class="prnClass">Order Recieved date</label>
	<input type="date" id="ordrecdate" class="prnClass">
	
	<label class="prnClass">Vendor Id</label>
	<select id="vendorId" class="prnClass" >
		<option>677</option>
	</select>

	<label class="prnClass">Grn value</label>
	<select id="prnvalue" class="prnClass" >
		<option>34567</option>
		
	</select>
	<br><br>
	<label class="prnClass">Select PRN ID:</label>
	<select id="prnId" class="prnClass" align="center" >
		<option>7983834</option>
	</select>
	<br>
	


	
	<br><br>
	<label class="prnfilterClass2">Vendor ID</label>
	<input type="text" value="678" id="vid" name="vid" class="prnfilterClass2">
	
	<label class="prnfilterClass1">Return Date</label>
	<input type="text"  id="rdt" name="rdt" class="prnfilterClass1">
	<br>
	<table class="table bg-white rounded shadow-sm  table-hover" id="example">
            <thead>
                <tr>
                    <th scope="col" width="50">#</th>
                    <th scope="col">GRN Id</th>
									<th scope="col">Product Id</th>
									<th scope="col">Batch No</th>
									<th scope="col">Quantity</th>
									<th scope="col">Cost</th>
									<th scope="col">Reason</th>
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
									
                                </tr>
                            </tbody>
                        </table><br><br>
		<label class="prnfilterClass2">Total Value</label>
	<input type="text" value="678" id="tv" name="tv" class="prnfilterClass2">
</body>
</html>