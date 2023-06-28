<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="./adminHome/scripts/addVendorScripts.js"></script>
<div class="container center">
        <div class="col-md-6">
            <h2 class="mb-4">Vendor Form</h2>
          <form method="POST">
               
                <div class="form-group">
                    <label for="vendorName">Vendor Name:</label>
                    <input type="text" class="form-control" id="vendorName" name="vendorName" placeholder="Enter Vendor Name" required>
                </div>
                <div class="form-group">
                    <label for="vendorAddress">Vendor Address:</label>
                    <textarea class="form-control" id="vendorAddress" name="vendorAddress" placeholder="Enter Vendor Address" required></textarea>
                </div>
                <div>
                    <label for="vendorContact">Vendor Contact Number:</label>
                    <input type="text" class="form-control" id="vendorPhone" name="vendorPhone" pattern="[6-9]\d{9}" title="Contact number must start with 6 - 9 and have 10 digits" placeholder="Enter Vendor Contact Number" required>
                </div>
                <div class="form-group">
                    <label for="vendorStatus">Vendor Status:</label>
                    <select class="form-control" id="vendorStatus" name="status">
                        <option value="active">Active</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>


