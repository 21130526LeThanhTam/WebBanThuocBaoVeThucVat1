<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="bean.Product" %>
<%@ page import="bean.ShoppingCart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="bean.CartItem" %>
<%@ page import="controller.ShoppingCartCL" %>
<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
    <title>Vườn phố</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="assets/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="assets/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/style.css" type="text/css">
    <link rel="stylesheet" href="assets/css/Log_Regis.css">
    <script src="assets/js/log_reg.js" defer></script>
</head>

<body>
<%
    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
    List<CartItem> cartItems = shoppingCart.getCartItemList();
%>

<jsp:include page="layout/header.jsp"/>

<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="assets/img/breadcrumb.jpg">

    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>Thủ tục thanh toán</h2>
                    <div class="breadcrumb__option">
                        <a href="HomePageController">Trang chủ</a>
                        <span>Thủ tục thanh toán</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Checkout Section Begin -->
<section class="checkout spad">

    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h6><span class="icon_tag_alt"></span> Có mã giảm giá? <a href="#">Bấm vào đây</a> để áp dụng mã giảm giá
                </h6>
            </div>
        </div>
        <div class="checkout__form">
            <h4>Thông tin thanh toán</h4>
            <form action="ThanhToanCL" method="post">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="row">
<%--                            <div class="col-lg-6">--%>
<%--                                <div class="checkout__input">--%>
<%--                                    <p>Tên<span>*</span></p>--%>
<%--                                    <input type="text" name = "firstname" value="<%=request.getAttribute("firstname")%>">--%>
<%--                                </div>--%>
<%--                            </div>--%>
                            <div class="col-lg-12">
                                <div class="checkout__input">
                                    <p>Họ Tên<span>*</span></p>
                                    <input type="text" name = "username" required value="<%=request.getAttribute("username")%>">
                                </div>
                            </div>
                        </div>
                        <div class="checkout__input">
                            <p>Tỉnh / Thành phố<span>*</span></p>
                            <select id="tinh" name="tinh" title="Chọn Tỉnh Thành">
                                <option value="0">Tỉnh Thành</option>
                            </select>
                        </div>
                        <div class="checkout__input">
                            <p>Quận/ Huyện<span>*</span></p>
                            <select class="css_select" id="quan" name="quan" title="Chọn Quận Huyện">
                                <option value="0">Quận Huyện</option>
                            </select>
                        </div>
                        <div class="checkout__input">
                            <p>Phường, xã<span>*</span></p>
                            <select class="css_select" id="phuong" name="phuong" title="Chọn Phường Xã">
                                <option value="0">Phường Xã</option>
                            </select>
                        </div>
                        <div class="checkout__input">

                            <p>Địa chỉ<span>*</span></p>
                            <input type="text" name ="homeNumber" required placeholder="Số nhà" class="checkout__input__add">

                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="checkout__input">
                                    <p>Số điện thoại<span>*</span></p>
                                    <input type="tel" pattern="^\+?[0-9]{1,3}?[-. ]?\(?[0-9]{3}\)?[-. ]?[0-9]{3}[-. ]?[0-9]{4}$" required name="phone" value="<%=request.getAttribute("phone")%>">
                                </div>
                            </div>
                        </div>
                        <div class="checkout__input__checkbox">
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="checkout__order">
                            <h4>Đơn hàng của bạn</h4>
                            <div class="checkout__order__products d-flex align-items-center justify-content-between"><span>Ảnh</span><span>Tên sản phẩm</span><span>Số lượng</span><span>Tổng tiền</span></div>
                            <ul>
                                <%
                                        if(cartItems!=null){
                                            for (CartItem i :  cartItems) {
                                %>
                                    <li class="d-flex align-items-center justify-content-between my-4">
                                        <img src="<%=i.getProduct().getImage()%>" alt="" width="40" height="40"/>
                                       <span class="w-25 text-truncate d-block"> <%=i.getProduct().getProduct_name()%></span>
                                       <span> <%=i.getQuantity()%></span>
                                        <span>
                                            <%=i.getTotalPrice()%>
                                        </span>
                                    </li>
                                <%
                                       }
                                   } else {
                                %>
                                    <li>Trống</li>
                                <%
                                    }
                                %>
                            </ul>
                            <!-- <div class="checkout__order__subtotal">Thuế <span>0₫</span></div> -->
                            <div class="checkout__order__total">Tổng cộng: <%=shoppingCart.getTotalPrice()%></div>
<%--                            <div class="checkout__input__checkbox">--%>
<%--                                <label for="acc-or">--%>
<%--                                    Tạo tài khoản mới?--%>
<%--                                    <input type="checkbox" id="acc-or">--%>
<%--                                    <span class="checkmark"></span>--%>
<%--                                </label>--%>
<%--                            </div>--%>

<%--                            <div class="checkout__input__checkbox">--%>
<%--                                <label for="payment">--%>
<%--                                    Chấp nhận thanh toán--%>
<%--                                    <input type="checkbox" id="payment">--%>
<%--                                    <span class="checkmark"></span>--%>
<%--                                </label>--%>
<%--                            </div>--%>
<%--                            <div class="checkout__input__checkbox">--%>
<%--                                <label for="paypal">--%>
<%--                                    Paypal--%>
<%--                                    <input type="checkbox" id="paypal">--%>
<%--                                    <span class="checkmark"></span>--%>
<%--                                </label>--%>
<%--                            </div>--%>
                            <input id="hidden" type="hidden" name="action" value="order">
                            <button type="submit" class="site-btn">Đặt hàng</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<!-- Checkout Section End -->

<!-- Footer Section Begin -->
<jsp:include page="layout/footer.jsp"/>
<!-- Footer Section End -->
<%--<div class="blur-bg-overlay"></div>--%>
<%--<div class="form-popup">--%>
<%--    <span class="close-btn material-symbols-rounded"><svg xmlns="http://www.w3.org/2000/svg" height="1em"--%>
<%--                                                          viewBox="0 0 384 512"><path--%>
<%--            d="M376.6 84.5c11.3-13.6 9.5-33.8-4.1-45.1s-33.8-9.5-45.1 4.1L192 206 56.6 43.5C45.3 29.9 25.1 28.1 11.5 39.4S-3.9 70.9 7.4 84.5L150.3 256 7.4 427.5c-11.3 13.6-9.5 33.8 4.1 45.1s33.8 9.5 45.1-4.1L192 306 327.4 468.5c11.3 13.6 31.5 15.4 45.1 4.1s15.4-31.5 4.1-45.1L233.7 256 376.6 84.5z"/></svg></span>--%>
<%--    <div class="form-box login">--%>
<%--        <div class="form-details">--%>
<%--            <h2>Chào mừng quý khách quay lại</h2>--%>
<%--            <p>Hãy điền địa chỉ email và mật khẩu vào các ô bên phải</p>--%>
<%--        </div>--%>
<%--        <div class="form-content">--%>
<%--            <h2>ĐĂNG NHẬP</h2>--%>
<%--            <form action="#">--%>
<%--                <div class="input-field">--%>
<%--                    <input type="text" name="name" required>--%>
<%--                    <label>Email</label>--%>
<%--                </div>--%>
<%--                <div class="input-field">--%>
<%--                    <input type="password" name="password" required>--%>
<%--                    <label>Password</label>--%>
<%--                </div>--%>
<%--                <a href="#" class="forgot-pw">Quên mật khẩu?</a>--%>
<%--                <button type="submit">Login</button>--%>
<%--            </form>--%>
<%--            <div class="bottom-link">--%>
<%--                Chưa có tài khoản--%>
<%--                <a href="#" id="signup-link">Đăng ký</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <!--ĐĂNG KÝ-->--%>
<%--    <div class="form-box signup">--%>
<%--        <div class="form-details">--%>
<%--            <h2>Tạo tài khoản</h2>--%>
<%--            <p>Điền thông tin theo yêu cầu bên phải</p>--%>
<%--        </div>--%>
<%--        <div class="form-content">--%>
<%--            <h2>ĐĂNG KÝ</h2>--%>
<%--            <form action="#">--%>
<%--                <div class="input-field">--%>
<%--                    <input type="text" name="name" required>--%>
<%--                    <label>Nhập email</label>--%>
<%--                </div>--%>
<%--                <div class="input-field">--%>
<%--                    <input type="password" name="password" required>--%>
<%--                    <label>Tạo Password</label>--%>
<%--                </div>--%>
<%--                <div class="policy-text">--%>
<%--                    <input type="checkbox" id="policy">--%>
<%--                    <label for="policy">--%>
<%--                        Tôi đồng ý với--%>
<%--                        <a href="">Điều khoản</a>--%>
<%--                    </label>--%>

<%--                </div>--%>
<%--                <button type="submit">Register</button>--%>
<%--            </form>--%>
<%--            <div class="bottom-link">--%>
<%--                Đã có sẵn tài khoản?--%>
<%--                <a href="#" id="login-link">Login</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<!-- Js Plugins -->
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.nice-select.min.js"></script>
<script src="assets/js/jquery-ui.min.js"></script>
<script src="assets/js/jquery.slicknav.js"></script>
<script src="assets/js/mixitup.min.js"></script>
<script src="assets/js/owl.carousel.min.js"></script>
<script src="assets/js/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://esgoo.net/scripts/jquery.js"></script>
<script>
    $(document).ready(function () {
        // Lấy tỉnh thành
        $.getJSON('https://esgoo.net/api-tinhthanh/1/0.htm', function (data_tinh) {

            if (data_tinh.error == 0) {
                $.each(data_tinh.data, function (key_tinh, val_tinh) {
                    $("#tinh").append('<option value="' + val_tinh.id + '" data-full-name="' + val_tinh.full_name + '">' + val_tinh.full_name + '</option>');
                    console.log('<option value="' + val_tinh.id + '" data-full-name="' + val_tinh.full_name + '">' + val_tinh.full_name + '</option>')
                });
                console.log($("#tinh").value)
            }
        });

        $("#tinh").change(function (e) {
            var idtinh = $(this).val(); // lấy ID của tỉnh
            var fullNameTinh = $("#tinh option:selected").data('full-name'); // lấy full name của tỉnh đã chọn
            // Lấy quận huyện
            $.getJSON('https://esgoo.net/api-tinhthanh/2/' + idtinh + '.htm', function (data_quan) {
                if (data_quan.error == 0) {
                    $("#quan").empty().append('<option value="0">--Chọn Quận Huyện--</option>');
                    $("#phuong").empty().append('<option value="0">--Chọn Phường/ Xã/ Thị trấn--</option>');
                    $.each(data_quan.data, function (key_quan, val_quan) {
                        $("#quan").append('<option value="' + val_quan.id + '" data-full-name="' + val_quan.full_name + '">' + val_quan.full_name + '</option>');
                    });
                }
            });
        });

        $("#quan").change(function (e) {
            var idquan = $(this).val(); // lấy ID của quận/huyện
            var fullNameQuan = $("#quan option:selected").data('full-name'); // lấy full name của quận/huyện đã chọn
            // Lấy phường xã
            $.getJSON('https://esgoo.net/api-tinhthanh/3/' + idquan + '.htm', function (data_phuong) {
                if (data_phuong.error == 0) {
                    $("#phuong").empty().append('<option value="0">--Chọn Phường/ Xã/ Thị trấn--</option>');
                    $.each(data_phuong.data, function (key_phuong, val_phuong) {
                        $("#phuong").append('<option value="' + val_phuong.id + '" data-full-name="' + val_phuong.full_name + '">' + val_phuong.full_name + '</option>');
                    });
                }
            });
        });
    });
</script>


</body>

</html>