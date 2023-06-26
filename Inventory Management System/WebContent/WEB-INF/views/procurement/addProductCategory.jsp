<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  form {
    max-width: 400px;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  label {
    display: block;
    margin-bottom: 10px;
    font-weight: bold;
  }
  
  input[type="number"],
  input[type="numeric"] {
    width: 100%;
    padding: 10px;
    border-radius: 4px;
    border: 1px solid #ccc;
    transition: border-color 0.3s ease-in-out;
  }
  
  input[type="number"]:focus,
  input[type="numeric"]:focus {
    border-color: #4c9aff;
    outline: none;
  }
  
  input[type="button"] {
    display: block;
    width: 100%;
    padding: 10px;
    margin-top: 20px;
    background-color: green;
    color: #fff;
    font-size: 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
  }
  
  input[type="button"]:hover {
    background-color: #0080ff;
  }
  .category
  {
    margin-top:50px;
  }
</style>
</head>
<body>
<div id="category" align="center">
  <h4>Add Category</h4>
  <form>
    <label>Category Id:</label>
    <input type="number" id="categoryId">
    <br><br>
    <label>Category Name:</label>
    <input type="numeric" id="categoryName">
    <br><br>
    <input type="button" id="addCategoryBtn" value="Add">
  </form>
</div>
</body>
</html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function() {
    $('#addCategoryBtn').click(function() {
      var categoryId = $('#categoryId').val();
      var categoryName = $('#categoryName').val();

      var data = {
        productCategoryId: categoryId,
        productCategoryName: categoryName
      };

      $.ajax({
        type: 'POST',
        url: 'createCategory',
        data: data,
        success: function(response) {
        alert("product category added successfully");
        },
        error: function(xhr, status, error) {
          // Handle the error here, if needed
          console.log(error);
        }
      });
    });
  });
</script>

