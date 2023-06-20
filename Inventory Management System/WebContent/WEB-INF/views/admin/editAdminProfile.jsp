<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <!-- Your existing head section -->
</head>
<body>
    <h1>Edit Profile</h1>
    <form id="editProfileForm">
        <input type="hidden" id="userId" value="${admin.userId}" />
        <div>
            <label>User Name:</label>
            <input type="text" id="userName" value="${admin.userName}" />
        </div>
        <div>
            <label>Password:</label>
            <input type="password" id="password" value="${admin.password}" />
        </div>
        <!-- Add more fields as required -->
        <div>
            <button type="button" onclick="saveProfile()">Save</button>
        </div>
    </form>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script>
        function saveProfile() {
            var userId = $("#userId").val();
            var userName = $("#userName").val();
            var password = $("#password").val();

            var updatedUser = {
                userId: userId,
                userName: userName,
                password: password
            };

            $.ajax({
                url: "/editProfile",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(updatedUser),
                success: function(response) {
                    alert(response);
                    // Handle success response
                },
                error: function(xhr, status, error) {
                    alert(xhr.responseText);
                    // Handle error response
                }
            });
        }
    </script>
</body>
</html>
