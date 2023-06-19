<html>

<head>
	<style>

	.indentClass
	{
	position:relative;
	left:500px;
	 font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
	}
	.indentClass1
	{
	position:relative;
	left:500px;
	 font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
	}
	
	</style>
    
</head>

<body>
    <h1 align="center">Indents</h1>
	<br><br>
	
	
      
      <label class="indentClass1">Select IndentDate:</label>
      <input type="date" id="indentDate" class="indentClass1">
	
	
	
	<label class="indentClass1">IndentStatus:</label>
      <select id="vendorId" class="indentClass1" align="center" >
         <option>Active</option>
      </select><br><br>
      
      <label class="indentClass">Select Indent ID:</label>
	<select id="indentId" class="indentClass" align="center" >
		<option>782</option>
	</select><br>
	
	<br><br>
	<table class="table bg-white rounded shadow-sm  table-hover" id="indentstableid">
         <thead>
              <tr>
                 <th scope="col" width="50">#</th>
                 <th scope="col">Product ID</th>
                 <th scope="col">Quantity Required</th>
              </tr>
         </thead>
         <tbody>
              <tr>
                 <th scope="row">1</th>
                 <td>Food</td>
                 <td>30</td>
              </tr>
         </tbody>
    </table>
</body>

</html>