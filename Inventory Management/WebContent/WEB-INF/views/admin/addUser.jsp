    <style>
        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
    </style>
    <script>
    function showSuccessMessage() {
        alert("User added successfully!");
        return true; // Allow the form submission to proceed
    }
</script>
    <div class="center">
        <div class="col-md-6">
            <h2 class="mb-4">User Form</h2>
            <form onSubmit="return showSuccessMessage()">
                <div class="form-group">
                    <label for="userId">User ID:</label>
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="Enter User ID" required>
                </div>
                <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" class="form-control" id="password" name="password" pattern="^(?=.*\d)(?=.*[A-Z])(?=.*\W)(?=.*[a-z]).{8,}$" title="Password must start with a capital letter, contain at least one lowercase letter, one uppercase letter, one special symbol, and one number" required>
</div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <textarea class="form-control" id="address" name="address" placeholder="Enter Address" required></textarea>
                </div>
              <div class="form-group">
    <label for="userContact">User Contact Number:</label>
    <input type="text" class="form-control" id="userContact" name="userContact" pattern="[6-9]\d{9}" title="Contact number must start with 6 - 9 and have 10 digits" placeholder="Enter User Contact Number" required>
</div>
                <div class="form-group">
                    <label for="userType">User Type:</label>
                    <select class="form-control" id="userType" name="userType">
                        <option value="procurement">Procurement</option>
                        <option value="inventory">Inventory</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>