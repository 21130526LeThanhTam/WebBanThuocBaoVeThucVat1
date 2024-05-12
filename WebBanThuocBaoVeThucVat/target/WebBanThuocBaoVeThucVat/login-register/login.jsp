<%@ page import="dao.AccountDAO" %>
<%@ page import="bean.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows 10
  Date: 16-12-2023
  Time: 5:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href='login-register/css/login.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Boxicons CSS -->
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#btnLogin').click(function () {
                var email = $('#email').val();
                var password = $('#password').val();
                $.ajax({
                    type: 'POST',
                    data: {
                        email: email,
                        password: password
                    },
                    url:'login',
                    success: function (result) {
                        var data = JSON.parse(result);
                        if (data.error) {
                            $('#errorLogin').html(data.error);

                        }
                        else if (data.role===1)  window.location.href = "admin_dashboard";
                        else if(data.role===0) window.location.href ="HomePageController";

                    }
                });
            });
        });
    </script>
</head>
<body>
<section class="container forms">
    <div class="form login">
        <div class="form-content">
            <header>Login</header>
            <form>
                <% String error = (String) session.getAttribute("errorlogin"); %>
                <% if(error != null){ %>

                <% } %>
                <% String passF = (String) session.getAttribute("passF"); %>
                <% if(passF != null){ %>
                <p class="text-success"><%= passF %></p>
                <% } %>
                <% String reg = (String) session.getAttribute("errorRegis"); %>
                <% if(reg != null){ %>
                <p class="text-success"><%= reg %></p>
                <% } %>
                <div class="field input-field">
                    <input name="email" type="email" placeholder="Email" class="input" id="email">
                </div>
                <div class="field input-field">
                    <input name="password" type="password" placeholder="Mật khẩu" class="password" id="password">
                    <i class='bx bx-hide eye-icon'></i>
                </div>
                <div class="form-link">
                    <a href="PasswordForgot" class="forgot-pass">Quên mật khẩu?</a>
                </div>
                <div class="text-center mt-3">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=http://localhost:8081/WebBanThuocBaoVeThucVat/loginByGoogle&response_type=code&client_id=383862284423-7n769c739crto335iam2jg9hk2hqiiu0.apps.googleusercontent.com&prompt=select_account">
                        <button type="button" class="btn btn-danger btn-block">
                            <i class="fab fa-google mr-2"></i> Login with Google
                        </button>
                    </a>
                </div>
                <span class="text-danger" id="errorLogin"></span><br>
                <div class="field button-field">
                    <input type="button" value="Đăng nhập" id="btnLogin">
                </div>
            </form>
            <div class="form-link">
                <span>Chưa có tài khoản? <a href="signup" class="<%-- link signup-link --%>">Đăng ký</a></span>
            </div>
        </div>
    </div>
</section>
<%--<script src="../login-register/js/signup_signin.js">--%>
<%--    function validatePassword() {--%>
<%--        var passwordInput = document.getElementById("password");--%>
<%--        var password = passwordInput.value;--%>
<%--        if (password.length < 6) {--%>
<%--            alert("Password must be at least 6 characters long.");--%>
<%--            return false; // Prevent form submission--%>
<%--        }--%>
<%--        return true; // Allow form submission--%>
<%--    }--%>
<%--</script>--%>
</body>
</html>
