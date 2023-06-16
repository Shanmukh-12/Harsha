<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Form</title>
</head>
<body>
    <h1>Registration Form</h1>
    <form>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="username" name="email" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="button" value="register" onclick="register()">
    </form>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
function login()
{
	var x=$('#username').val();
	var y=$('#password').val();
	$.ajax({
		  url: "login",
		  type: "POST",
		  data:{
	            username: x,
	            password: y
	        },
		  success: function(response) {
		    // Handle the response from the servlet if needed
		    $('body').html(response);
		  },
		  error: function() {
		    // Handle errors
		    console.error("AJAX request failed.");
		  }
		});
}
</script>
</html>
