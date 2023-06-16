<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h1>Login Page</h1>
    <form method="get" action="login.jsp">
        <label for="username">Username:</label>
        <input type="text" id="username" name="user_name" required><br><br>
        
        <label for="password">Password:</label>
          <input type="password" id="password" name="password" autocomplete="new-password" />
           <input type="hidden" id="realPassword" name="realPassword" /><br><br>
        <input type="radio" name="userType" value="procurement" id="procurement">
<label for="procurement">Procurement</label>

<input type="radio" name="userType" value="inventory" id="inventory">
<label for="inventory">Inventory</label>

<input type="radio" name="userType" value="admin" id="admin">
<label for="admin">Admin</label><br>
        
        <input type="button" value="Login" onclick="login()">
        
    </form>
    <form >
    <input type="button" value="forgot password" onclick="forgotrequest()">
    </form>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
function forgotrequest()
{
	$.ajax({
		  url: "forgotrequest",
		  type: "GET",
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
function login()
{
	var x=$('#username').val();
	var y=$('#password').val();
	var z = document.querySelector('input[name="userType"]:checked').value;
	$.ajax({
		  url: "login",
		  type: "POST",
		  data:{
	            username: x,
	            password: y,
	            user_type:z
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
