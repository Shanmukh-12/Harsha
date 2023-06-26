<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Adjustments List</title>
   <style>
#filter-container {
   
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 20px;
}

.filter-group {
  margin-right: 20px;
  margin-bottom: 10px;
}

.filter-group label {
  margin-right: 5px;
}

.filter-group select,
.filter-group input[type="date"] {
  width: 200px;
}

   .container {
            max-width: 800px;
            margin: 100 auto;
            padding-top: 10px;
            margin-left:170px;
        }

        .issues-block {
            margin-top:20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            background-color: #fff;
        }

        .issues-block:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color:#d8f2f0 ;
            
            
        }

        .issue-details{

            margin-top:10px;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }

         .label {
            font-weight: bold;
            
        }


        .btn-issues{
               border-radius:6px;
                background-color: #4CAF50;
                 border: none;
                 color: white;
                 cursor: pointer;
                 font-weight: bold;
                box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
            margin-left: 600px;
 
        }

        .btn-issues:hover {
            background-color:#4CAF50 ;
            border-color: #4CAF50;
        }
        



.bold {
            font-weight: bold;
            font-size: 25px;
        }
        
           /* Custom styles for modal */
    .modal-content {
        background-color: #f1f1f1;
        border-radius: 10px;
    }

    .modal-header {
        background-color: #c1efde;
        border-radius: 10px 10px 0 0;
    }

    .modal-title {
        color: #333;
        font-weight: bold;
    }

    .modal-body {
        padding: 20px;
    }
    
 .twoDropdowns {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
}

.dropdowns {
	display: flex;
	padding-left: 300px;
    height: 100px;
}

.dropdown {
	display: inline-block;
	margin-right: 10px;
}  

    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <script>
    
	function filterData()
	{
		var productCategoryId = $("#product-category-dropdown").val();
	    var productId =$("#product-name-dropdown").val()
	    var fromDate = document.getElementById("adjustment-date-dropdown-from").value;
	    var toDate = document.getElementById("adjustment-date-dropdown-to").value;

	    var url = null;
		if(productCategoryId)
		{
			if(productId)
			{
				if(fromDate)
				{
					url="getFilterDataByCategoryIdProductIdFrom";				
				}
				else
				url="getFilterDataByCategoryIdProductId";
			}
			else
			{
				if(fromDate)
				{	
					url="getFilterDataByCategoryIdFrom";
				}
				else
				url="getFilterDataByCategoryId";
			}
				
		}
		else
		{
			if(fromDate)
			{	
				url="getFilterDataByFrom";
			}
			else
			url="getFilterDataByTo";
				
		}
		
		
		
		
		console.log(productCategoryId);
		console.log(productId);
		console.log(fromDate);
		console.log(toDate);
		console.log(url);
	    $.ajax({
			
	    	url:url,
	    	method:"POST",
	    	dataType:"json",
	    	data:{
	    		"filters":JSON.stringify(
	    				{
	    					"productCategoryId":productCategoryId,
	    					"productId":productId,
	    					"fromDate":fromDate,
	    					"toDate":toDate
	    				})
	    	},
	    	success: function(response) {
	    		console.log(response);
	    		  // Remove existing issues blocks and "No Indents Found" message
	    		  $('.issues-block').remove();
	    		  $('.not-found-message').remove();

	    		  if (Object.keys(response).length === 0) {
	    		    // Response is empty, display "No Indents Found" message
	    		    var noDataMessage = $('<h5 class="not-found-message" style="color:red" align="center">No Adjustments Found</h5>');
	    		    $('.container > h1').after(noDataMessage);
	    		  } else {
	    		    // Iterate over the response object
	    		    Object.values(response).forEach(function(adjustment) {
       	       var issueBlock = $('<div class="issues-block"></div>');
       	       var status = $('<h4 class="store-indent-id">Adjustment Id: <span class="bold">' + adjustment.adjs_id + '</span></h4>');
       	       var issueDetails = $('<div class="issue-details"></div>');
       	    <%-- var storeId = $('<span class="label">Store ID:</span><span>' + indent.storeId + '</span>');
       	       var indentId = $('<span class="label">Status:</span><span>' + indent.indentStatus + '</span>'); --%>
       	       var adjustmentDate = $('<span class="label">Adjustment Date:</span><span>' + new Date(adjustment.adjs_date[0], adjustment.adjs_date[1] - 1, adjustment.adjs_date[2]).toLocaleDateString() + '</span>');
       	       var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadAdjustmentsProducts(\'' + adjustment.adjs_id + '\')">View Products</button>');

       	       issueDetails.append( adjustmentDate);
       	       issueBlock.append(status, issueDetails, viewProductsButton);
 		      $('.container').append(issueBlock);

	    		    });
	    		  }
	    		}
	       });
	}
	
	 function loadAdjustmentsProducts(adjs_id) {
	        var currentPageUrl = window.location.href;
	        var data = {}
	        data["adjs_id"]=adjs_id;
	        $.ajax({
	          url: "getAdjustmentProductsList",
	          method: "post",
	          data:{"adjs_id":JSON.stringify(data)},
	          success: function (response) {
	            $("#modalContent").html(response);
	            $("#productsModal").modal("show");
	            history.pushState(null, null, currentPageUrl);
	          },
	          error: function () {
	            console.log("Failed to load static page");
	          }
	        });
	      }

	
   $(document).ready(function(){
	   
	   
	   
	   
	   $.ajax({
		   url:"getProductCategories",
		   method:"POST",
		   dataType:"json",
		   success:function(response){
			   $('#product-category-dropdown').empty();

		        $('#product-category-dropdown').append($('<option value="" selected disabled >Select Product Category</option>'));
		        
		       
		        response.forEach(function(item) {
		        	 $('<option>', {
		        		    value: item.productCategoryId,
		        		    text: item.productCategoryName+" ("+item.productCategoryId+")"
		        		  }).appendTo('#product-category-dropdown');
		        	 });
		   },
	   error:function(error){
		   console.log(error);
	   }
	   });
	  	

    
	   $("#product-category-dropdown").change(function(){
			
			
		   	$.ajax({
		   	     url :"getProductsIds",
		   	     method :"post",
		   	  data:{
		   	    	
		   	    categoryId : $("#product-category-dropdown").val()
		   	     },
          success: function (response) {
        	  console.log(response);
        	  $('#product-name-dropdown').empty();
        	  $('#product-name-dropdown').append($('<option value="" selected disabled >Select Product</option>'));
       		 $.each(response, function(index, product) {
                 var option = '<option value="' + product.productId + '">' + product.productName + '</option>';
                 $('#product-name-dropdown').append(option);
             });
       	   }
       	 });
      });
  });
   
   var currentDate = new Date();

	
	var formattedDate = currentDate.toISOString().split("T")[0];

	document.getElementById("adjustment-date-dropdown-to").setAttribute("max", formattedDate);
	document.getElementById("adjustment-date-dropdown-from").setAttribute("max", formattedDate);


	   document.getElementById("adjustment-date-dropdown-to").setAttribute("value", formattedDate);

 
</script>
</head>
<body>
<form method=post onsubmit="filterData(); return false">
	<div class="dropdowns">
		<div class="twoDropdowns">
			<div class="dropdown">
				<select id="product-category-dropdown" >
				
				
				</select>
			</div>
			<div class="dropdown">
				<select id="product-name-dropdown">
					<option value="" selected>Select Product Name</option>
					
				</select>
			</div>
		</div>
		<div class="twoDropdowns">
			<div class="dropdown">
				<label>From Date :</label>
				<input type="date" id="adjustment-date-dropdown-from"
					placeholder="Select Indent Date">
			</div>
			<div class="dropdown">
				<label>To  Date :</label>
				<input type="date" id="adjustment-date-dropdown-to"
					placeholder="Select Indent Date">
			</div>
		</div>
		
		<div class="twoDropdowns">
			<input type="reset">
			<button type="submit" >Apply Filters</button>
			
		</div>
	</div>
	
	
	
	
	
	
</form>

<form method="post" action="">
    <div class="container">
       <h1 class="text-center mb-4">Adjustments List</h1>
     <c:forEach items="${adjustments}" var="adjustments">
            <div class="issues-block" >
                <h4 class="store-indent-id">Adjustment ID: <span class="bold">${adjustments.adjustmentID}</span></h4>
                <div class="issue-details" >
                    <span class="label">Adjustment Date :  </span><span>${adjustments.adjustmentDate}</span>
                
                    <button type="button" class="btn-issues" onclick="loadAdjustmentsProducts(${adjustments.adjustmentID})">View Products</button>
                </div>
            </div>
        </c:forEach>
    </div>
</form>


<div class="modal fade" id="productsModal" tabindex="-1" aria-labelledby="productsModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" style="max-width: calc(100% - 200px);">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productsModalLabel">Products</h5>
               <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="modalContent">
               
            </div>
        </div>
    </div>
</div>


</body>
</html>