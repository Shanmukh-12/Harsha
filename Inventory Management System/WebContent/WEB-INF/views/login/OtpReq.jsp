<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Email Entry</title>
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

    input[type="text"] {
      padding: 10px;
      width: 250px;
      border-radius: 5px;
      border: 1px solid #ccc;
    }

    span#errorMsg {
      display: block;
      color: red;
      margin-top: 10px;
    }

    input[type="button"] {
      padding: 10px 20px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
  </style>
</head>
<body>

  <form>
    <label for="email">Email:</label>
    <input type="text" id="email" name="mail" placeholder="Enter your email"><br>
    <span id="emailError"></span>
    <br>
    <input type="button" value="Submit" onclick="sendotp()">
  </form>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    function sendotp() {
      var email = $('#email').val();
      console.log(email);
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
