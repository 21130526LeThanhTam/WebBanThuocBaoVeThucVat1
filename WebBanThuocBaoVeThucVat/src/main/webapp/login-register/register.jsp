<%@ page import="dao.AccountDAO" %>
<%@ page import="bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href='../login-register/css/login.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#btn_register').click(function (event) {
                event.preventDefault();

                // Check if reCAPTCHA is verified first
                var response = grecaptcha.getResponse();
                if (response.length === 0) {
                    $('#errorRegister').html("Làm ơn xác minh bạn không phải là robot");
                    return; // Stop further execution if reCAPTCHA is not verified
                }

                // If reCAPTCHA is verified, proceed with form submission
                var email = $('#email').val();
                var username = $('#username').val();
                var surname = $('#surname').val();
                var lastname = $('#lastname').val();
                var phone = $('#phone').val();
                var pass = $('#pass').val();
                var rePass = $('#rePass').val();

                $.ajax({
                    type: 'POST',
                    data: {
                        email: email,
                        username: username,
                        surname: surname,
                        lastname: lastname,
                        phone: phone,
                        pass: pass,
                        rePass: rePass
                    },
                    url: 'signup',
                    success: function (result) {
                        console.log(result);  // Log the result to see what is being returned
                        try {
                            var data = JSON.parse(result);
                            if (data.error) {
                                $('#errorRegister').html(data.error);
                            } else {
                                window.location.href = "login";
                            }
                        } catch (e) {
                            console.error("Error parsing JSON: ", e);
                            $('#errorRegister').html("Lỗi trong quá trình xử lý. Vui lòng thử lại sau.");
                        }
                    },
                    error: function() {
                        $('#errorRegister').html("Lỗi kết nối. Hãy kiểm tra mạng của bạn và thử lại!");
                    }
                });
            });
        });
    </script>
</head>
<body class="bg-success d-flex align-items-center justify-content-center" style="height: 1000px">
    <div class="form signup">
        <div class="form-content">
            <header>Signup</header>
            <form id="form">
                <% String error = (String) session.getAttribute("errorRegis"); %>
                <% if(error != null){ %>
                <p class="text-danger"><%= error %></p>
                <% } %>
                <% String error1 = (String) session.getAttribute("errorNumber"); %>
                <% if(error1 != null){ %>
                <p class="text-danger"><%= error1 %></p>
                <% } %>
                <div class="field input-field">
                    <input name="email" type="email" id="email" placeholder="Email" class="input">
                </div>
                <div class="field input-field">
                    <input name="username" type="text" id="username" placeholder="Tên người dùng" class="input">
                </div>
                <div class="field input-field">
                    <input name="surname" type="text" id="surname" placeholder="Họ" class="input">
                </div>
                <div class="field input-field">
                    <input name="lastname" type="text" id="lastname" placeholder="Tên" class="input">
                </div>
                <div class="field input-field">
                    <input name="phone" id="phone" type="tel" placeholder="Số điện thoại" class="input">
                </div>
                <div class="field input-field">
                    <input name="pass" type="password" id="pass" placeholder="Nhập mật khẩu" class="password">
                    <i class='bx bx-hide eye-icon'></i>
                </div>
                <div class="field input-field">
                    <input name="rePass" type="password" id="rePass" placeholder="Nhập lại mật khẩu" class="password">
                </div>
                <div class="container-capcha mt-5 d-flex justify-content-center align-items-center">
                    <div class="g-recaptcha" data-sitekey="6LeWqNkpAAAAANkqcg0zDmNz90pyG4FOLP4QiDQv"></div>
                </div>
                <span class="text-danger" id="errorRegister"></span><br>
                <div class="field button-field">
                    <input type="submit" id="btn_register" class="btn btn-success" value="Đăng ký">
                </div>
            </form>
            <div class="form-link">
                <span>Đã có tài khoản? <a href="login" class="link login-link">Đăng nhập</a></span>
            </div>
        </div>
    </div>
</body>
</html>
