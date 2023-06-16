<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        form {
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="password"],
        input[type="text"] {
            padding: 10px;
            width: 250px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type="button"] {
            padding: 10px 20px;
            
            color: white;
            background-color:grey;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        #timer {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        span#errorMsg {
            display: block;
            color: red;
            margin-top: 10px;
        }

        span.error {
            color: red;
            font-size: 12px;
            display: block;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <form>
        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" placeholder="Enter new password"><br>
        <br>
        <label for="retypePassword">Confirm New Password:</label>
        <input type="password" id="retypePassword" name="retypePassword" placeholder="Retype new password"><br>
        <br>
        <label for="otp">OTP:</label>
        <input type="text" id="otp" name="otp" placeholder="Enter OTP"><br>
        <br>
        <input type="button" value="Change" id="tk" onclick="changepass()" disabled class="butt">
        <input type="button" id="resendOTPButton" value="Resend OTP" disabled onclick="resend()" class="butt">
        <span id="timer"></span>
        <br>
        <span id="emailError"></span>
    </form>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

$(document).ready(function() {
    var countdownInterval; // Variable to hold the countdown interval
    var countdownDuration = 60; // Duration in seconds (2 minutes)
    clearInterval(countdownInterval);
    function startTimer(duration, display) {
        var timer = duration;
        display.text(formatTime(timer)); // Display initial time

        countdownInterval = setInterval(function() {
            timer--;

            if (timer < 0) {
                clearInterval(countdownInterval);
                console.log(timer);
                display.text("00:00"); // Display "00:00" when timer reaches 0
                $('#resendOTPButton').prop('disabled', false).css('background-color', 'green'); // Enable the "Resend OTP" button
            } else {
                display.text(formatTime(timer)); // Update the display with the current time
            }
        }, 1000);
    }

    function formatTime(seconds) {
        var minutes = Math.floor(seconds / 60);
        var remainingSeconds = seconds % 60;
        return (minutes < 10 ? "0" : "") + minutes + ":" + (remainingSeconds < 10 ? "0" : "") + remainingSeconds;
    }

    function resendOTP() {
        $('#resendOTPButton').prop('disabled', true).css('background-color', 'grey'); // Disable the "Resend OTP" button
         // Clear the existing countdown interval
         clearInterval(countdownInterval);
        var display = $('#timer');
        startTimer(countdownDuration, display); // Start the timer with the specified duration
    }

    // Disable the "Resend OTP" button initially
    $('#resendOTPButton').prop('disabled', true).css('background-color', 'grey');

    // Bind the click event handler to the "Resend OTP" button
    $('#resendOTPButton').click(function() {
        resendOTP();
    });

    // Start the timer initially
    var display = $('#timer');
    startTimer(countdownDuration, display);
});



$(document).ready(function() {

	
    // Disable the "change" button initially
    $('input[type="button"]').prop('disabled', true);

    // Password and retype password input fields keyup event handlers
    $('#newPassword, #retypePassword').on('keyup', function() {
        validatePasswords();
    });

    // OTP input field keyup event handler
    $('#otp').on('keyup', function() {
        validateOTP();
    });

    function validatePasswords() {
        var newPassword = $('#newPassword').val();
        var retypePassword = $('#retypePassword').val();

        // Remove any existing password mismatch error message
        $('#passwordMismatchError').remove();

        // Check if passwords match
        if (newPassword !== retypePassword) {
            var errorMessage = $('<span id="passwordMismatchError" class="error">Password does not match.</span>');
            $('#retypePassword').after(errorMessage); // Add the password mismatch error message
        }

        enableButton();
    }

    function validateOTP() {
        var otp = $('#otp').val();

        // Remove any existing invalid OTP error message
        $('#otpError').remove();

        // Check if OTP is a 4-digit number
        if (!/^\d{4}$/.test(otp)) {
            var errorMessage = $('<span id="otpError" class="error">Invalid OTP.</span>');
            $('#otp').after(errorMessage); // Add the invalid OTP error message
        }

        enableButton();
    }

    function enableButton() {
        var newPassword = $('#newPassword').val();
        var retypePassword = $('#retypePassword').val();
        var otp = $('#otp').val();
        
        // Enable the "change" button if all fields are valid and not empty
        if (newPassword === retypePassword && /^\d{4}$/.test(otp) && newPassword !== '') {
        	$('#tk').prop('disabled', false).css('background-color', 'green');
        
        } else {
        	$('#tk').prop('disabled', true).css('background-color', 'grey');
        }
    }
});


function changepass()
{
	$('#emailError').text("");
	var password = $(retypePassword).val();
	console.log(password);
	var otps = $(otp).val();
	console.log(otps);
	var encodedPassword = encodeURIComponent(password);
	var encodedotp = encodeURIComponent(otps);
	var url= "changepass?pass="+encodedPassword+"&otp="+encodeURIComponent(otps)+"&mail=${mail}";
	console.log(url);
	
	$.ajax({
		  url: "changepass?pass="+encodedPassword+"&otp="+encodedotp+"&mail=${mail}",
		  type: "GET",
		  dataType: "text",
		  success: function(response) {
		    // Handle the response from the servlet if needed
		    console.log(response);
		    if(response==="success")
		    	{
		    	console.log("success");
		    	$('#emailError').css('color', 'black');
		    	$('#emailError').css('font-weight', 'bold');
		    	
		    	$('#emailError').text("password changed successfully and redirecting to login page in 3 seconds");
		    	
		    	setTimeout(function() {
		    		console.log("inside redirect");
		    	    $.ajax({
		    	        url: "",
		    	        type: "GET",
		    	        
		    	        success: function(response) {
		    	        	$('body').html(response);
		    	        },
		    	        error: function() {
		    	            // Handle errors
		    	            
		    	        }
		    	    });
		    	}, 3000);
		    	
		    console.log("hello");
		    	}
		    else
		    	{
		    	$('#emailError').css('color', 'red');
		    	$('#emailError').text("otp expired or invalid otp");
		    	console.log("hii");
		    	}
		    
		    
		  },
		  error: function() {
		    // Handle errors
			  $('#emailError').text(response);
		  }
		});

	
	
}
function resend()
{
	$.ajax({
        url: "sendotp?Mail=${mail}",
        type: "GET",
        success: function(response) {
            // Handle the response from the servlet if needed
            
            
        },
        error: function() {
            // Handle errors
            console.error("Sending OTP failed.");
        }
    })
	
	}

</script>

</html>