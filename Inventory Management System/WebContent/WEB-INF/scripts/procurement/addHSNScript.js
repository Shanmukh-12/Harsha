function submitForm() {
    event.preventDefault(); // Prevent the form from submitting normally

    // Get the HSN and GST values from the input fields
    var HSNcode = $('#hsn').val();
    var gst = $('#gst').val();

    // Create a URL-encoded string with the data
    var data = 'HSNcode=' + encodeURIComponent(HSNcode) + '&gst=' + encodeURIComponent(gst);

    // Send an AJAX POST request
    $.ajax({
      type: 'GET',
      url: 'createHSN',
      data: data,
      success: function(response) {
        alert("added successfully");
      },
      error: function(xhr, status, error) {
        // Handle the error here, if needed
        console.log(error);
      }
    });
  }