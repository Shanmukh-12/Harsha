<head>
    <link rel="stylesheet" href="./HomeProcurement/styles/addProductStyles.css">
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

