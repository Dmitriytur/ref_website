$( "#authorize" ).click(function() {

    var username = $("#username").val();
    var password = $("#password").val();

    var data = {
                 username: username,
                 password: password
                };
    $.ajax({
    url: '/login',
    type: 'post',
    contentType: "application/json",
    dataType: 'text',
    data: JSON.stringify(data),
    success: function (response) {
        window.location.pathname = "/home";
    }
});
});