$( "#register" ).click(function() {
    var firstname = $("#first_name").val();
    var secondname = $("#second_name").val();
    var username = $("#username").val();
    var email = $("#email").val();
    var password = $("#password").val();
    var confirm_password = $("#confirm_password").val();
    var firstname = $("#first_name").val();

    if (password != confirm_password){
        $("#errorMessage").text("Wrong password");
        $("#errorBox").show();
        return;
    }
    var data = {
                 firstname: firstname, 
                 secondname: secondname,
                 username: username,
                 email: email,
                 password: password
                };
    $.ajax({
    url: '/register',
    type: 'post',
    contentType: "application/json",
    dataType: 'text',
    data: JSON.stringify(data),
    success: function (response) {
        $('#successMessage').animate({opacity: "1"}, 0);
        $('#successMessage').animate({opacity: "0"}, 5000);
    }
});
});