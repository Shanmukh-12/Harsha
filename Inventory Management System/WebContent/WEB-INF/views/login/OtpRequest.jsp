<!DOCTYPE html>
<html>
<head>
    <title>Email Entry</title>
</head>
<body>
    <form >
        <label for="email">Email:</label>
        <input type="text" id="email" name="mail" placeholder="Enter your email">
        <span id="emailError" style="color: red;"></span>
        <br>
        <input type="button" value="Submit" onclick="sendotp()">
    </form>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	function sendotp() {
		console.log("hii");
	    var email = $('#email').val();
	    $('#emailError').text("");
	    // Perform server-side check for user existence
	    $.ajax({
	        url: "check?Mail=" + email,
	        type: "GET",
	        dataType: "text",
	        success: function(response) {
	            if (response === "true") {
	                // User exists, proceed with sending OTP
	                $.ajax({
	                    url: "sendotp?Mail=" + email,
	                    type: "GET",
	                    success: function(response) {
	                        // Handle the response from the servlet if needed
	                        
	                        $('body').html(response);
	                    },
	                    error: function() {
	                        // Handle errors
	                        console.error("Sending OTP failed.");
	                    }
	                });
	            } else {
	                // User does not exist, display an error message or take appropriate action
	            	$('#emailError').text("User does not exist!");
	            }
	        },
	        error: function() {
	            // Handle errors
	            console.error("Checking user existence failed.");
	        }
	    });
	}
	
	


</script>
</body>
</html>
