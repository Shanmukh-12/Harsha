$(document).ready(function() {

var el = document.getElementById("wrapper");
        var toggleButton = document.getElementById("menu-toggle");

        toggleButton.onclick = function () {
            el.classList.toggle("toggled");
        };
});


function clicked(data){
        $.ajax({
            url: data,
            method: "POST",
            success: function(response) {
                console.log("AJAX call successful");
                $("#content").html(response);
                
            },
            error: function() {
                console.log("AJAX call error");
            }
        });
 	}
 	function logout() {
 	    $.ajax({
 	        url: "logout",
 	        method: "GET",
 	        success: function(response) {
 	            console.log("Logout successful");
 	            console.log(response);
 	            location.href= "/inventory/";
 	        },
 	        error: function() {
 	            console.log("Logout error");
 	        }
 	    });
 	}