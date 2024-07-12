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
<%@page%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href='../login-register/css/login.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Boxicons CSS -->
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#btnLogin').click(function (event) {
                event.preventDefault();
                var email = $('#email').val();
                var password = $('#password').val();
                $.ajax({
                    type: 'POST',
                    data: {
                        email: email,
                        password: password
                    },
                    url: 'login',
                    success: function (result) {
                        try {
                            var data = JSON.parse(result);
                            if (data.error) {
                                $('#errorLogin').html(data.error);
                            } else {
                                var response = grecaptcha.getResponse();
                                if (response.length === 0) {
                                    $('#errorLogin').html("Làm ơn xác minh bạn không phải là robot");
                                } else {

                                    if (data.role === 1) {
                                        window.location.href = "admin_dashboard";
                                    } else if (data.role === 0) {
                                        window.location.href = "HomePageController";
                                    }
                                }
                            }
                        } catch (e) {
                            $('#errorLogin').html("Lỗi trong quá trình tải request,Vui lòng thử lại");
                        }
                    },

                    error: function() {
                        $('#errorLogin').html("Lỗi kết nối. Hãy kiểm tra mạng của bạn và thử lại!");
                    }
                });
            });
        });
    </script>
    <style>
        .social-login {
            text-align: center;
            display:flex;
            margin-top:25px;
            justify-content: space-between;
        }
        .social-login2{
            text-align: center;
            display:flex;
            justify-content: space-around;
        }
        .btn-google{
            background-color: #db4437;
        }
        .btn-discord{
            background-color: #7289da;
        }
        .btn-facebook{
            background-color: #3b5998;
        }
        .btn-twitter{
            background-color: #1DA1F2;
        }
        .btn-github{
            background-color: #181717;
        }
        .custom-btn{
            font-size:15px;
            color:white;
            border:none;
        }
        .custom-btn:hover{
            background-color: rgba(204, 204, 100, 0.9);
            color:black;
        }

    </style>
</head>
<body>
<section class="container forms">
    <div class="form login">
        <div class="form-content">
            <header>Login</header>
            <form id="form" class="shadow p-3 mb-2 bg-white rounded">
                <% String error = (String) session.getAttribute("errorlogin"); %>
                <% if(error != null){ %>
                <span class="text-danger"><%= error %></span>
                <% } %>
                <% String passF = (String) session.getAttribute("passF"); %>
                <% if(passF != null){ %>
                <p class="text-success"><%= passF %></p>
                <% } %>
                <% String reg = (String) session.getAttribute("errorRegis"); %>
                <% if(reg != null){ %>
                <p class="text-success"><%= reg %></p>
                <% } %>
                <div class="mb-3">
                    <input name="email" type="email" placeholder="Email" class="form-control" id="email">
                </div>
                <div class="mb-3" style="position: relative">
                    <input name="password" type="password" placeholder="Mật khẩu" class="form-control" id="password">
                    <i class='fas fa-eye eye-icon' id="togglePassword"></i>
                </div>
                <div class="form-link mb-3">
                    <a href="PasswordForgot" class="forgot-pass">Quên mật khẩu?</a>
                </div>
<<<<<<< HEAD
                <div class="field button-field">
                    <div class="container-capcha" style="margin-left:32px">
                        <div class="g-recaptcha" data-sitekey="6LeWqNkpAAAAANkqcg0zDmNz90pyG4FOLP4QiDQv"></div>
                    </div>
=======
                <div class="mb-3">
                    <div class="g-recaptcha d-flex justify-content-center" data-sitekey="6LeWqNkpAAAAANkqcg0zDmNz90pyG4FOLP4QiDQv"></div>
>>>>>>> origin/main
                    <span class="text-danger" id="errorLogin"></span><br>
                    <input type="submit" value="Đăng nhập" id="btnLogin" class="btn btn-warning w-100 custom-btn ">
                </div>
                <div class="social-login">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=http://localhost:8081/loginByGoogle&response_type=code&client_id=383862284423-7n769c739crto335iam2jg9hk2hqiiu0.apps.googleusercontent.com&prompt=select_account" class="d-block mb-2">
                        <div class="btn btn-google custom-btn">
                            <i class="fab fa-google"></i> Google
                        </div>
                    </a>
                    <a href="https://discord.com/oauth2/authorize?client_id=1256117175874228275&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8081%2FloginByDiscord&scope=identify+guilds+gdm.join+email+guilds.join+connections" class="d-block mb-2">
                        <div class="btn btn-discord custom-btn">
                            <i class="fab fa-discord"></i> Discord
                        </div>
                    </a>
                    <a href="https://www.facebook.com/v19.0/dialog/oauth?fields=id,name,first_name,last_name,email,picture&client_id=487765543790855&redirect_uri=http://localhost:8081/loginByFacebook" class="d-block">
                        <div class="btn btn-facebook custom-btn">
                            <i class="fab fa-facebook"></i> Facebook
                        </div>
                    </a>
                </div>
                <div class="social-login2">
                    <div class="text-center mt-3">
                        <a href="https://twitter.com/i/oauth2/authorize?response_type=code&client_id=Q09mU0dCSXJtMWtLMGVsVDh0V0s6MTpjaQ&redirect_uri=http://localhost:8081/loginByTwitter&scope=tweet.read%20users.read%20follows.read%20offline.access&state=state&code_challenge=challenge&code_challenge_method=plain">
                            <div class="btn btn-twitter custom-btn">
                                <i class="fa-brands fa-twitter"></i> Twitter
                            </div>
                        </a>
                    </div>
                    <div class="text-center mt-3">
                        <a href="https://github.com/login/oauth/authorize?client_id=Ov23li6js9Ba8yvQj3aA">
                            <div class="btn btn-github custom-btn">
                                <i class="fa-brands fa-github"></i> Github
                            </div>
                        </a>
                    </div>
                </div>
                <div class="form-link mt-4">
                    <span>Chưa có tài khoản? <a href="signup" class="<%-- link signup-link --%>">Đăng ký</a></span>
                </div>
            </form>
            <script src="https://www.google.com/recaptcha/api.js" async defer></script>
            <script>
                window.onload = function() {
                    const form = document.getElementById("form");
                    const error = document.getElementById("errorLogin");

                    form.addEventListener("submit", function (event){
                        event.preventDefault(); // Keep this to ensure the form does not submit traditionally.
                        // Now the reCAPTCHA validation is handled in the AJAX call as above.
                    });
                }
            </script>
        </div>
    </div>
</section>
<script src="../login-register/js/signup_signin.js">
    function validatePassword() {
        var passwordInput = document.getElementById("password");
        var password = passwordInput.value;
        if (password.length < 6) {
            alert("Password must be at least 6 characters long.");
            return false; // Prevent form submission
        }
        return true; // Allow form submission
    }
</script>
<script>
    const togglePassword = document.getElementById('togglePassword');
    const password = document.getElementById('password');

    togglePassword.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        // toggle the eye icon
        this.classList.toggle('fa-eye');
        this.classList.toggle('fa-eye-slash');
    });
</script>
</body>
</html>