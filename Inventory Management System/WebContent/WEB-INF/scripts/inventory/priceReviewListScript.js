        
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
    					url="getFilterDataByCategoryIdProductIdFrom1";				
    				}
    				else
    				url="getFilterDataByCategoryIdProductId1";
    			}
    			else
    			{
    				if(fromDate)
    				{	
    					url="getFilterDataByCategoryIdFrom1";
    				}
    				else
    				url="getFilterDataByCategoryId1";
    			}
    				
    		}
    		else
    		{
    			if(fromDate)
    			{	
    				url="getFilterDataByFrom1";
    			}
    			else
    			url="getFilterDataByTo1";
    				
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
    	    		    var noDataMessage = $('<h5 class="not-found-message" style="color:red" align="center">No Price Reviews Found</h5>');
    	    		    $('.container > h1').after(noDataMessage);
    	    		  } else {
    	    		    // Iterate over the response object
    	    		    Object.values(response).forEach(function(priceReview) {
           	       var issueBlock = $('<div class="issues-block"></div>');
           	       var status = $('<h4 class="store-indent-id">Price Review Id: <span class="bold">' + priceReview.pr_id + '</span></h4>');
           	       var issueDetails = $('<div class="issue-details"></div>');
           	       var priceReviewDate = $('<span class="label">Adjustment Date:</span><span>' + new Date(priceReview.pr_date[0], priceReview.pr_date[1] - 1, priceReview.pr_date[2]).toLocaleDateString() + '</span>');
           	       var viewProductsButton = $('<button type="button" class="btn-issues" onclick="loadPriceReviewProducts(\'' + priceReview.pr_id + '\')">View Products</button>');

           	       issueDetails.append( priceReviewDate);
           	       issueBlock.append(status, issueDetails, viewProductsButton);
     		      $('.container').append(issueBlock);

    	    		    });
    	    		  }
    	    		}
    	       });
    	}  
    
    
    
    
    function loadPriceReviewProducts(pr_id) {
        var currentPageUrl = window.location.href;
        var data = {}
        data["pr_id"]=pr_id;
        $.ajax({
          url: "getPriceReviewProductsList",
          method: "post",
          data:{"pr_id":JSON.stringify(data)},
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
 		        
 		        // Add options based on the response
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

 	// Format the date as YYYY-MM-DD
 	var formattedDate = currentDate.toISOString().split("T")[0];

 	// Set the max attribute to the date with one day added
 	document.getElementById("adjustment-date-dropdown-to").setAttribute("max", formattedDate);
 	document.getElementById("adjustment-date-dropdown-from").setAttribute("max", formattedDate);


 	   document.getElementById("adjustment-date-dropdown-to").setAttribute("value", formattedDate);
   
    
    