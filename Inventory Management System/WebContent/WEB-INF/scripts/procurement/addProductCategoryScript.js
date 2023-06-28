$(document).ready(function() {
	$('#addCategoryBtn').click(function() {
		var categoryId = $('#categoryId').val();
		var categoryName = $('#categoryName').val();

		var data = {
			productCategoryId: categoryId,
			productCategoryName: categoryName
		};

		$.ajax({
			type: 'POST',
			url: 'createCategory',
			data: data,
			success: function(response) {
				alert("product category added successfully");
			},
			error: function(xhr, status, error) {
				// Handle the error here, if needed
				console.log(error);
			}
		});
	});
});