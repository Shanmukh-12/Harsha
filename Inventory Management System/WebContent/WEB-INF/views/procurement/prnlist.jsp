<html>
<head>
	<style>
	
	.prnClass
	{
	position:relative;
		left:500px;
	 font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
    
</head>

<body>
    <h1 align="center">PRN Data</h1>
	<br><br>
		<label class="prnClass">Return Date</label>
	<input type="date" id="returnDate" class="prnClass">
	
	<label class="prnClass">Vendor Id</label>
	<select id="vendorId" class="prnClass" >
		<option >20001</option>
	</select>

	<label class="prnClass">Grn value</label>
	<select id="prnvalue" class="prnClass" >
		<option>4000</option>
		
	</select>
	<br><br>
	<label class="prnClass">Select PRN ID:</label>
	<select id="prnId" class="prnClass" align="center" >
		
	</select>
	<br>
	


	
	<br><br>
	<label class="prnfilterClass2">Vendor ID</label>
	<input type="text" value="678" id="vid" name="vid" class="prnfilterClass2">
	
	<label class="prnfilterClass1">Return Date</label>
	<input type="text"  id="rdt" name="rdt" class="prnfilterClass1">
	<br>
	<table class="table bg-white rounded shadow-sm  table-hover" id="dataTable">
            <thead>
                <tr>
                    <th scope="col" width="50">#</th>
                    <th scope="col">GRN Id</th>
									<th scope="col">Product Id</th>
									<th scope="col">Quantity</th>
									<th scope="col">Cost</th>
									<th scope="col">purchase_return_description</th>
                                </tr>
                            </thead>
                            <tbody id="dataBody">
                                <tr>
                                    
									
                                </tr>
                            </tbody>
                        </table><br><br>
		<label class="prnfilterClass2">Total Value</label>
	<input type="text" value="678" id="tv" name="tv" class="prnfilterClass2">
</body>
<script>
$(document).ready(function(){
var returnDate=$("#returnDate").val();
var vendorId=$("#vendorId").val();
var prnvalue=$("#prnvalue").val();
$("#returnDate").change(tk);
$("#vendorId").click(tk);
$("#prnvalue").click(tk);
var prnId=$("#prnId");
$("#prnId").click(products);
function tk(){
	var returnDate=$("#returnDate").val();


$.ajax({
	            
                 url: "getPurchaseReturnsList",
                 method:"GET",
                	    data: {
                	    	"purchase_return_date":returnDate,
                	    	"vendor_id":vendorId,
                	    	"grn_value":prnvalue
                	    	
                	    },
                 success: function(response) {
                	 console.log(response);
                	 var PurchasesId= $("#prnId");
                	 $("#prnId").empty();
                	 response.forEach(function(optionval)
                     		{
                     		  var newop=$("<option></option>");
                     		  newop.val(optionval.purchase_return_id);
                     		  newop.text(optionval.purchase_return_id);
                     		  PurchasesId.append(newop);
                     		  
                     		});
                	 
                    
                 }
             });



};
});
function products()
{
	var prnId=$("#prnId").val();
	
$.ajax({
    
    url: "getPurchaseReturnProducts",
    method:"GET",
   	    data: {
   	    	"purchase_return_id":prnId,
   	    	
   	    },
    success: function(response) {
    	console.log(this.url);
    	console.log("hi");
    	console.log(response);
    	var table = document.getElementById("dataTable");
        var tbody = document.getElementById("dataBody");
        $("#dataBody").html("");
       

        // Iterate over the data and create table rows
   
        response.forEach(function (row) {
            // Create a new row
        	 var newRow = document.createElement("tr");

        	  // Create and populate cells for each column
        	  var indexCell = document.createElement("td");
        	  indexCell.textContent = "#";

        	  var grnIdCell = document.createElement("td");
        	  grnIdCell.textContent = row.pr.grn_no;

        	  var productIdCell = document.createElement("td");
        	  productIdCell.textContent = row.prp.product_id;

        	  var quantityCell = document.createElement("td");
        	  quantityCell.textContent = row.prp.quantity;

        	  var costCell = document.createElement("td");
        	  costCell.textContent = row.pr.grn_cost;

        	  var reasonCell = document.createElement("td");
        	  reasonCell.textContent = row.pr.purchase_return_description;

        	  // Append cells to the row
        	  newRow.appendChild(indexCell);
        	  newRow.appendChild(grnIdCell);
        	  newRow.appendChild(productIdCell);
        	  newRow.appendChild(quantityCell);
        	  newRow.appendChild(costCell);
        	  newRow.appendChild(reasonCell);

        	  // Append the row to the table body
        	  var tableBody = document.getElementById("dataBody");
        	  tableBody.appendChild(newRow);
        	  
        	  $("#rdt").text(row.pr.purchase_return_date);
              $("#rdt").val(row.pr.purchase_return_date);
              $("#vid").text(row.pr.vendor_id);
              $("#vid").val(row.pr.vendor_id);
        	  $("#tv").text(row.pr.grn_cost);
        	  $("#tv").val(row.pr.grn_cost);

              
        });
       
    }
});
}



</script>


</html>