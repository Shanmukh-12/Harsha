<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
   .center {
   display: flex;
   justify-content: center;
   align-items: center;
   height: 100vh;
   }
</style>
<script>
   $(document)
   		.ready(
   				function() {
   					$("#userId")
   							.change(
   									function() {
   										var userId = $(this).val();
   										if (userId) {
   											$
   													.ajax({
   														url : "getUserData",
   														type : "POST",
   														contentType : "application/json",
   														data : JSON
   																.stringify({
   																	"userId" : userId
   																}),
   														success : function(
   																response) {
   															console
   																	.log(response);
   															$("#userType")
   																	.val(
   																			response.userType);
   														},
   														error : function() {
   															alert("An error occurred while fetching vendor information.");
   														}
   													});
   										}
   									});
   				});
   
   function deleteUser() {
   	// Retrieve form data
   	var userId = $("#userId").val();
   
   	// Confirmation dialog
   	var confirmationMessage = "User ID: " + userId
   			+ "\n\nAre you sure you want to delete this user?";
   	var userConfirmed = window.confirm(confirmationMessage);
   
   	if (userConfirmed) {
   		// Send the data to the backend using AJAX
   		$.ajax({
   			url : "deleteUserData",
   			type : "POST",
   			contentType : "application/json",
   			data : JSON.stringify({
   				userId : userId,
   			}),
   			success : function(response) {
   				alert("User deleted successfully.");
   				// Perform any additional actions after deletion
   			},
   			error : function() {
   				alert("An error occurred while deleting the user.");
   			}
   		});
   	}
   
   	return false; // Prevent form submission
   }
</script>
<div class="center">
   <div class="col-md-6">
      <h2 class="mb-4">Delete User</h2>
      <form action="deleteUser" method="post" onsubmit="return deleteUser()">
         <div class="form-group">
            <label for="userId">User Id:</label> 
            <select class="form-control"
               id="userId" name="userId" required>
               <option value="">Select user Name</option>
               <c:forEach var="user" items="${users}">
                  <option value="${user.userId}">${user.userName}
                     (${user.userId})
                  </option>
               </c:forEach>
            </select>
         </div>
         <input type="text" class="form-control" id="userType" name="userType"
            placeholder="Enter User Type" required readonly>
         <button type="submit" class="btn btn-danger">Delete</button>
      </form>
   </div>
</div>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>