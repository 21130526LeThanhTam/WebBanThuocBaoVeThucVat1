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
    <link href='./login-register/css/login.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Boxicons CSS -->
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#btnLogin').click(function (event) {
                event.preventDefault(); // Prevent the default form submission.
                var email = $('#email').val();
                var password = $('#password').val();
                var rememberMe = $('#remember-me').is(':checked'); // Get the checkbox status

                // Proceed with AJAX call first
                $.ajax({
                    type: 'POST',
                    data: {
                        email: email,
                        password: password,
                        rememberMe: rememberMe
                    },
                    url: 'login',
                    success: function (result) {
                        try {
                            var data = JSON.parse(result);
                            if (data.error) {
                                $('#errorLogin').html(data.error);
                                $('#checkNull').html("");
                            } else {
                                // Check reCAPTCHA after successful user validation
                                var response = grecaptcha.getResponse();
                                if (response.length === 0) {
                                    $('#errorLogin').html("Làm ơn xác minh bạn không phải là robot");
                                } else {
                                    // Redirect based on user role if no error and reCAPTCHA is valid
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

</head>
<body>
<section class="container forms">
    <div class="form login">
        <div class="form-content">
            <header>Login</header>
            <form id="form">
                <% String passF = (String) session.getAttribute("action"); %>
                <% if(passF != null) { %>
                    <p class="text-success" id="checkNull"><%= passF %></p>
                <% } %>
                <div class="field input-field">
                    <% String emailCookie = ((String) request.getAttribute("email"))==null?"":((String) request.getAttribute("email"));%>
                    <input name="email" type="email" placeholder="Email" class="input" id="email" value="<%=emailCookie%>">
                </div>
                <div class="field input-field">
                    <% String passCookie = ((String) request.getAttribute("password"))==null?"":((String) request.getAttribute("password"));%>
                    <input name="password" type="password" placeholder="Mật khẩu" class="password" id="password" value="<%=passCookie%>">
                    <i class='bx bx-hide eye-icon'></i>
                </div>
                <div class="col-md-12">
                    <input type="checkbox" id="remember-me" name="remember-me">
                    <label for="remember-me">Remember-me</label>
                </div>
                <div class="form-link">
                    <a href="PasswordForgot" class="forgot-pass">Quên mật khẩu?</a>
                </div>
                <div class="field button-field">
                    <div class="container-capcha" style="margin-left:32px">
                        <div class="g-recaptcha" data-sitekey="6LeWqNkpAAAAANkqcg0zDmNz90pyG4FOLP4QiDQv"></div>
                    </div>
                    <span class="text-danger" id="errorLogin"></span><br>
                    <input type="submit" value="Đăng nhập" id="btnLogin" style="background-color:#FFCC33;color:white;border:none; font-weight: bold">
                </div>
                <div class="text-center mt-3">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=http://localhost:8081/loginByGoogle&response_type=code&client_id=383862284423-7n769c739crto335iam2jg9hk2hqiiu0.apps.googleusercontent.com&prompt=select_account">
                        <button type="button" class="btn btn-danger btn-block" style="padding:10px 102px; font-weight: bold">
                            <i class="fab fa-google mr-2"></i> Login with Google
                        </button>
                    </a>
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
            <div class="form-link">
                <span>Chưa có tài khoản? <a href="signup" class="<%-- link signup-link --%>">Đăng ký</a></span>
            </div>
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
</body>
</html>
