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
        alert("Store added successfully!");
        return true; // Allow the form submission to proceed
    }
</script>
    <div class="center">
        <div class="col-md-6">
            <h2 class="mb-4">Store Form</h2>
            <form onsubmit="return showSuccessMessage()">
                <div class="form-group">
                    <label for="storeId">Store ID:</label>
                    <input type="text" class="form-control" id="storeId" name="storeId" placeholder="Enter Store ID" required>
                </div>
      <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" class="form-control" id="password" name="password" pattern="^(?=.*\d)(?=.*[A-Z])(?=.*\W)(?=.*[a-z]).{8,}$" title="Password must start with a capital letter, contain at least one lowercase letter, one uppercase letter, one special symbol, and one number" required>
</div>


                <div class="form-group">
                    <label for="storeName">Store Name:</label>
                    <input type="text" class="form-control" id="storeName" name="storeName" placeholder="Enter Store Name" required>
                </div>
                <div class="form-group">
                    <label for="storeAddress">Store Address:</label>
                    <textarea class="form-control" id="storeAddress" name="storeAddress" placeholder="Enter Store Address" required></textarea>
                </div>
<div class="form-group">
    <label for="storeContact">Store Contact Number:</label>
    <input type="text" class="form-control" id="storeContact" name="storeContact" pattern="[6-9]\d{9}" title="Contact number must start with 6 - 9 and have 10 digits" placeholder="Enter Store Contact Number" required>
</div>

             <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>