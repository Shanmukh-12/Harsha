<head>
    <link rel="stylesheet" href="./HomeProcurement/styles/addHSNStyles.css">
</head>
<script src="./HomeProcurement/scripts/addHSNScript.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
