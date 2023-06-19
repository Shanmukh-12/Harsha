<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
 
  <style>
    form {
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    
    label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }
    
    select, input[type="text"], input[type="number"], textarea {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
      margin-bottom: 10px;
    }
    
    textarea {
      resize: vertical;
    }
    
    input[type="button"] {
      background-color: #4CAF50;
      color: #fff;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    
    input[type="button"]:hover {
      background-color: #45a049;
    }
    .product
    {
    	margin-top:50px;
    }
  </style>
</head>
<body style="height: 851px; width: 1096px">
<div id="product" align="centre">
  <h4>Add Product</h4>
  <form style="width: 1086px; height: 1065px">
    <label for="categoryName">Category Name:</label>
    <select name="categoryName" id="categoryName" style="width: 1042px; ">
      <option value="volvo">select</option>
      <option value="mobiles">mobiles</option>
      <option value="mercedes">Mercedes</option>
      <option value="audi">Audi</option>
    </select>
    <br><br>
    <label>Product Name :</label>
    <input type="text" id="productName" style="width: 1047px; ">
    <br><br>
    <label>Product ID :</label>
    <input type="number" id="productId" style="width: 1054px; ">
    <br><br>
    <label>HSN Code :</label>
    <input type="number" style="width: 1052px; ">
    <br><br>
    <label>Product Type :</label>
    <input type="text" style="width: 1058px; ">
    <br><br>
    <label>Reorder Level:</label>
    <input type="number" style="width: 1058px; ">
    <br><br>
    <label>Description :</label>
    <textarea id="about" name="about" rows="5" cols="30" style="height: 72px; width: 1062px"></textarea>
    <br><br>
    <label for="image">Product Image:</label>
    <input type="file" id="image" name="image" style="width: 247px; "><br><br><input type="button" value="submit">
    
  </form>
  </div>
</body>
</html>
