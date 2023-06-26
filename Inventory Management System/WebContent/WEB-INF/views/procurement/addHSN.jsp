<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
  
  .addHSN{
  
  margin-top:50px;
  
  }
</style>
</head>
<body>
<div class="addHSN">
<label align="center">Add HSN</h4>
  <form>
    <label>HSN Code:</label>
    <input type="number" id="hsn">
    <br><br>
    <label>GST:</label>
    <input type="numeric" id="gst">
    <br><br>
    <input type="button" value="Submit" onclick="submitForm()">
  </form>
 </div>
</body>
</html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  function submitForm() {
    event.preventDefault(); // Prevent the form from submitting normally

    // Get the HSN and GST values from the input fields
    var HSNcode = $('#hsn').val();
    var gst = $('#gst').val();

    // Create a URL-encoded string with the data
    var data = 'HSNcode=' + encodeURIComponent(HSNcode) + '&gst=' + encodeURIComponent(gst);

    // Send an AJAX POST request
    $.ajax({
      type: 'GET',
      url: 'createHSN',
      data: data,
      success: function(response) {
        alert("added successfully");
      },
      error: function(xhr, status, error) {
        // Handle the error here, if needed
        console.log(error);
      }
    });
  }
</script>