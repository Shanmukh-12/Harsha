   
    function Myfunction(controllerUrl) {
            $.ajax({
                url: controllerUrl,
                method: "POST",
                success: function(response) {
                    $("#content").html(response);
                },
                error: function() {
                    console.log("AJAX call error");
                }
            });  
        
    }

    var el = document.getElementById("wrapper");
    var toggleButton = document.getElementById("menu-toggle");

    toggleButton.onclick = function () {
        el.classList.toggle("toggled");
    };