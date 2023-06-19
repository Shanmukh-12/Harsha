<!DOCTYPE html>
  <html>
  <head>
    <title></title>
     <style type="text/css">
  #htag{
  position: relative;
  top: 20px;
  
  }
    
 .login-form select {
      width: 100%;
      height: 100%;
      padding: 10px;
      margin-bottom: 10px;
      border: 1px solid #ccc;
      border-radius: 3px;
    }
  * {
  margin: 0;
  padding: 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-transition: 0.3s;
  -o-transition: 0.3s;
  transition: 0.3s;
}

body {
  background-color: #fff;
  font-family: Montserrat;
  overflow-x: hidden;
}

article,
aside,
details,
figure,
footer,
header,
main,
menu,
nav,
section,
summary {
  display: block;
}

h1,
h2,
h3,
h4,
h5,
h6,
p,
a {
  -ms-word-wrap: break-word;
  word-wrap: break-word;
  text-decoration: none;
}

img {
  border: none;
}

*:focus {
  outline: none;
}

.clearfix::after {
  content: "";
  display: table;
  clear: both;
}

.bg-illustration {
  position: relative;
  margin-top:0px;
  margin-left:0px;
  height: 580px;
  background: url("https://images.pexels.com/photos/2701434/pexels-photo-2701434.jpeg?auto=compress&cs=tinysrgb&w=600") no-repeat center center scroll;
  background-size: cover;
  float: left;
  -webkit-animation: bgslide 2.3s forwards;
          animation: bgslide 2.3s forwards;
}
.bg-illustration img {
  width: 248px;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  height: auto;
  margin: 19px 0 0 25px;
}

@-webkit-keyframes bgslide {
  from {
    left: -100%;
    width: 0;
    opacity: 0;
  }
  to {
    left: 0;
    width: 1194px;
    opacity: 1;
  }
}

@keyframes bgslide {
  from {
    left: -100%;
    width: 0;
    opacity: 0;
  }
  to {
    left: 0;
    width: 1194px;
    opacity: 1;
  }
}



.login {
  max-height: 100vh;
  overflow-Y: auto;
  float: left;
  margin: 0 auto;
  width: calc(100% - 1194px);
}
.login .container {
  width: 505px;
  margin: 90px auto;
  position: relative;
}
.login .container h1 {
  margin-top: 55px;
  font-size: 35px;
  font-weight: bolder;
}
.login .container .login-form {
  margin-top: 75px;
}
.login .container .login-form form {
  display: -ms-grid;
  display: grid;
}
.login .container .login-form form input {
  font-size: 16px;
  font-weight: normal;
  background: rgba(57, 57, 57, 0.07);
  margin: 12.5px 0;
  height: 68px;
  border: none;
  padding: 0 30px;
  border-radius: 10px;
}
.login .container .login-form form button[type=submit] {
  background: -webkit-linear-gradient(110deg, #f794a4 0%, #fdd6bd 100%);
  background: -o-linear-gradient(110deg, #f794a4 0%, #fdd6bd 100%);
  background: linear-gradient(-20deg, #f794a4 0%, #fdd6bd 100%);
  border: none;
  margin-top: 50px;
  margin-bottom: 20px;
  margin-left: 125px;
  width: 241px;
  height: 58px;
  text-transform: uppercase;
  color: white;
  border-radius: 10px;
  position: relative;
  z-index: 2;
  font-weight: bold;
  font-size: 20px;
}
.login .container .login-form form button[type=submit]:hover::after {
  opacity: 1;
}
button:hover{
  cursor:pointer;
}
.login .container .login-form form button[type=submit]::after {
  content: "";

  position: absolute;
  z-index: -1;
  border-radius: 10px;
  opacity: 0;
  top: 0;
  left: 0;
  -webkit-transition: 0.3s ease-in-out;
  -o-transition: 0.3s ease-in-out;
  transition: 0.3s ease-in-out;
  right: 0;
  bottom: 0;
  background: -webkit-gradient(linear, left bottom, left top, from(#09203f), to(#537895));
  background: -webkit-linear-gradient(bottom, #09203f 0%, #537895 100%);
  background: -o-linear-gradient(bottom, #09203f 0%, #537895 100%);
  background: linear-gradient(-20deg, #3e9d00 0%, #7ee23c 100%)
}
.login .container .remember-form {
  position: relative;
  margin-top: -30px;
}
.login .container .remember-form input[type=checkbox] {
  margin-top: 9px;
}
.login .container .remember-form span {
  font-size: 18px;
  font-weight: normal;
  position: absolute;
  top: 32px;
  color: #3B3B3B;
  margin-left: 15px;
}
/* Style for the dropdown */
  .login-form select {
    width: 100%;
    height: 68px; /* Adjust the height as needed */
    padding: 0 30px;
    margin: 12.5px 0;
    border: none;
    border-radius: 10px;
    font-size: 16px;
    background: rgba(57, 57, 57, 0.07);
  }

  /* Style for the "Forgot Password" link */
  .login-form .forget-pass {
    text-align: right;
    margin-top: 10px;
  }

  .login-form .forget-pass a {
    color: red;
    text-decoration: none;
  }
@media only screen and (min-width: 1024px) and (max-width: 1680px) {
  .bg-illustration {
   
    width: 45%;
    height: 789px;
    -webkit-animation: none;
    animation: none;
  }

  .login {
    width: 50%;
  }
}
/* Display 12", iPad PRO Portrait, iPad landscape */
@media only screen and (max-width: 1024px) {
  body {
    overflow-x: hidden;
  }

  @-webkit-keyframes slideIn {
    from {
      left: -100%;
      opacity: 0;
    }
    to {
      left: 0;
      opacity: 1;
    }
  }

  @keyframes slideIn {
    from {
      left: -100%;
      opacity: 0;
    }
    to {
      left: 0;
      opacity: 1;
    }
  }
  .bg-illustration {
    float: none;
    background: url("https://c3.ai/wp-content/uploads/2020/06/blog-hero-override-inventory-optimization.jpg") center center;
    background-size: cover;
    -webkit-animation: slideIn 0.8s ease-in-out forwards;
            animation: slideIn 0.8s ease-in-out forwards;
    width: 100%;
    height: 190px;
    text-align: center;
  }
  .bg-illustration img {
    width: 100px;
    height: auto;
    margin: 20px auto !important;
    text-align: center;
  }
  .bg-illustration .burger-btn {
    left: 33px;
    top: 29px;
    display: block;
    position: absolute;
  }
  .bg-illustration .burger-btn span {
    display: block;
    height: 4px;
    margin: 6px;
    background-color: #fff;
  }
  .bg-illustration .burger-btn span:nth-child(1) {
    width: 37px;
  }
  .bg-illustration .burger-btn span:nth-child(2) {
    width: 28px;
  }
  .bg-illustration .burger-btn span:nth-child(3) {
    width: 20px;
  }

  .login {
    float: none;
    margin: 0 auto;
    width: 100%;
  }
  .login .container {
    -webkit-animation: slideIn 0.8s ease-in-out forwards;
            animation: slideIn 0.8s ease-in-out forwards;
    width: 85%;
    float: none;
  }
  .login .container h1 {
    font-size: 25px;
    margin-top: 40px;
  }
  .login .container .login-form {
    margin-top: 90px;
  }
  .login .container .login-form form input {
    height: 45px;
  }
  .login .container .login-form form button[type=submit] {
    height: 45px;
    margin-top: 100px;
  }
  .login .container .login-form .remember-form {
    position: relative;
    margin-top: -14px;
  }
  .login .container .login-form .remember-form span {
    font-size: 16px;
    margin-top: 22px;
    top: inherit;
  }

 
}
</style>
  </head>
  <body>
  <div class="parent clearfix">
    <div class="bg-illustration">


      <div class="burger-btn">
        <span></span>
        <span></span>
        <span></span>
      </div>

    </div>
    
    <div class="login">
      <div class="container">
        <h1 id="htag" align="center">Pennant Inventory</h1>
    
        <div class="login-form">
          <form >
            <input type="email" placeholder="E-mail Address" id="username" name="username">
            <input type="password" placeholder="Password" id="password" name="password">
             <select name="userType" id="userType">
		<option value="" selected disabled>Select User Type</option>
              <option value="procurement">Procurement</option>
              <option value="inventory">Inventory</option>
              <option value="admin">Admin</option>
            </select>
            
             <div class="forget-pass" align="right" >
              <input type="button" style="color:red" onclick="forgotrequest()" value="Forgot Password ?"> 
            </div>
            <input type="button" value="Login" onclick="login()">

          </form>
        </div>
    
      </div>
      </div>
  </div>
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
	var z=document.getElementById("userType").value;
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
		    if(response==="login success")
			  $.ajax({
				  url: "HomeProcurement",
				  type: "GET",
				  
				  success: function(response) {
				    // Handle the response from the servlet if needed
				    $("body").html(response);
				    
				  },
				  error: function() {
				    // Handle errors
				    console.error("AJAX request failed.");
				  }
				});
		  },
		  error: function() {
		    // Handle errors
		    console.error("AJAX request failed.");
		  }
		});
}

</script>
</html>