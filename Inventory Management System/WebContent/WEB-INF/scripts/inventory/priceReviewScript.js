  function validateProductCategory() {
      var productCategory = document.getElementById("productcategoryid").value;
      var errorElement = document.getElementById("productcategory-error");
      
      if (productCategory === "") {
        errorElement.innerText = "Please select a Product Category";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateProductName() {
      var productName = document.getElementById("productnameid").value;
      var errorElement = document.getElementById("productname-error");
      
      if (productName === "") {
        errorElement.innerText = "Please select a Product Name";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateBatchNo() {
      var batchNo = document.getElementById("batchnoid").value;
      var errorElement = document.getElementById("batchno-error");
      
      if (batchNo === "") {
        errorElement.innerText = "Please select a Batch No";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateOriginalCost() {
      var originalCost  = document.getElementById("originalcostid").value;
      var errorElement = document.getElementById("originalcost-error");
      
      if (originalCost === "") {
        errorElement.innerText = "Please enter the Original Cost";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateModifiedCost() {
    	var modifiedCost = document.getElementById("modifiedcostid").value;
    	  var maxRetailPrice = document.getElementById("maxretailpriceid").value;
    	  var errorElement = document.getElementById("modifiedcost-error");
      
      if (modifiedCost === "") {
    	    errorElement.innerText = "Please enter the Modified Cost";
    	  } else if (parseFloat(modifiedCost) > parseFloat(maxRetailPrice)) {
    	    errorElement.innerText = "Modified Cost cannot be greater than Maximum Retail Price";
    	  } else {
    	    errorElement.innerText = "";
    	  }
    }
    
    function validateReason() {
      var reason = document.getElementById("reasonid").value;
      var errorElement = document.getElementById("reason-error");
      
      if (reason === "") {
        errorElement.innerText = "Please enter a Reason";
      } else {
        errorElement.innerText = "";
      }
    }
    
    function validateForm() {
      validateProductCategory();
      validateProductName();
      validateBatchNo();
      validateOriginalCost();
      validateModifiedCost();
      validateReason();
      
      // Form is valid if no error messages exist
      var errorMessages = document.getElementsByClassName("error-message");
      for (var i = 0; i < errorMessages.length; i++) {
        if (errorMessages[i].innerText !== "") {
          return false;
        }
      }
      
      return true;
    }
    
    function addProduct() {
    
    	if(validateForm())
    	{
     
      

        // Get input values
        var productname = document.getElementById("productnameid").value;
        var batchno = document.getElementById("batchnoid").value;
        var originalcost = document.getElementById("originalcostid").value;
        var modifiedcost = document.getElementById("modifiedcostid").value;
        var reason =  document.getElementById("reasonid").value;
        

        // Create new row in the table
        var tableBody = document.getElementById("products-table-body");
        var newRow = tableBody.insertRow();

        // Insert cells with product details
        var productnameCell = newRow.insertCell();
        productnameCell.innerHTML = productname;
        
        var batchnoCell = newRow.insertCell();
        batchnoCell.innerHTML = batchno;

        var originalcostCell = newRow.insertCell();
        originalcostCell.innerHTML = originalcost;
        
        var modifiedcostCell = newRow.insertCell();
        modifiedcostCell.innerHTML =  modifiedcost;
        
        var reasonCell = newRow.insertCell();
        reasonCell.innerHTML = reason;

        var deleteCell = newRow.insertCell();
        deleteCell.innerHTML = '<button class="delete-button" onclick="deleteProduct(this)">Delete</button>';

        // Clear input fields
        document.getElementById("productnameid").value = "";
        document.getElementById("batchnoid").value = "";
        document.getElementById("originalcostid").value = "";
        document.getElementById("modifiedcostid").value = "";
        document.getElementById("maxretailpriceid").value = "";
        document.getElementById("reasonid").value = "";
    	}
      }

      function deleteProduct(button) {
        var row = button.parentNode.parentNode;
        row.parentNode.removeChild(row);
      }
      
      
      
      $(document).ready(function(){
        	$.ajax({
        	     url :"getProductCategories",
        	     method :"post",
        	   success : function(data) {
                 $.each(data, function(index, category) {
                     var option = '<option value="' + category.productCategoryId + '">' + category.productCategoryName + '</option>';
                     $('#productcategoryid').append(option);
                 });
             },
             error: function() {
                 alert('Error occurred while retrieving categories.');
             }
        	    	  
        	});
        	    	 
    
          $("#productcategoryid").change(function() {
          	
              $.ajax({
                  url : "getProductsIds",
                  method : "post",
                  data:{
                  	
                  	categoryId : $("#productcategoryid").val()
                  	
                  },
                  success: function(data) {
                      $('#productnameid').empty();
                      var option = '<option selected disabled>' + "select Product" + '</option>';
                      $('#productnameid').append(option);
                      $.each(data, function(index, products) {
                          var option = '<option value="' + products.productId + '">' + products.productName + '</option>';
                          $('#productnameid').append(option);
                      });
                  },
                  error: function() {
                      alert('Error occurred while retrieving products.');
                  }
              });
          });

          $("#productnameid").change(function() {
              $.ajax({
                  url : "getProductBatchNos",
                  method : "post",
                  data : {
                      "productId": $('#productnameid').val()
                  },
                  success: function(data) {
                      $('#batchnoid').empty();
                      var option = '<option selected disabled>' + "select batchNo" + '</option>';
                      $('#batchnoid').append(option);
                      $.each(data, function(index, batches) {
                      	
                          var option = '<option value="' + batches.batchNo + '">' + batches.batchNo + '</option>';
                          $('#batchnoid').append(option);
                      });
                  },
                  error: function() {
                      alert('Error occurred while retrieving batchnos.');
                  }
              });
          });

          $("#batchnoid").change(function() {
              $.ajax({
                  url : "getProductQuantityOrPrice",
                  method : "post",
                  data:{
                  	"productId": $('#productnameid').val(),
                  	"batchNo": $('#batchnoid').val()
                  	
                  },
                  success : function(Price) {
                  	console.log("hi");
                  	$('#originalcostid').val(Price.costPrice);
                  	$('#maxretailpriceid').val(Price.mrp);
                  },
                  error: function(error) {
                	  alert('Error occurred while retrieving cost price');
                  }
              });
          });
          
     
          function getTableData() {
        	  const table = document.getElementById('products-table');
        	  const tableData = [];
        	  
        	  for (let i = 1; i < table.rows.length; i++) {
        	    const row = table.rows[i];
        	    const rowData = {};

        	    rowData["product_id"] = row.cells[0].innerText;
        	    rowData["batch_no"] = row.cells[1].innerText;
        	    rowData["old_Price"] = row.cells[2].innerText;
        	    rowData["new_price"] = row.cells[3].innerText;
        	    rowData["review_desc"] = row.cells[4].innerText;
        	    

        	    tableData.push(rowData);
        	  }

        	  const jsonData = {
        			  
        	    "productsList": tableData
        	  };

        	  return jsonData;
        	}
        
        
        
        
        $("#updateButton").click(function() {
         	var data = getTableData();
           	const jsonData = JSON.stringify(data);
          		console.log(jsonData);
            $.ajax({
                url : "createPriceReview",
                method : "post",
                data:{"jsonData":jsonData},
                success : function(data) {
                	alert('Prices updated successfully');
                	
                },
                error: function(error) {
                	 alert('Error occurred while saving prices');
                }
            });
        });
          
        });