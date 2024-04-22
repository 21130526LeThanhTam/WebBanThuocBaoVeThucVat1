<%--
  Created by IntelliJ IDEA.
  User: 84828
  Date: 4/21/2024
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="bo.CategoryBO" %>
<%@ page import="bean.*" %>
<%@ page import="dao.OrdersDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <script src="js/log_reg.js" defer></script>

</head>
<body>
<jsp:include page="layout/header.jsp"/>
<main>
    <div class="container mt-5">
        <h2 class="mb-4">Lịch sử mua hàng</h2>

        <div class="row mb-3">
            <div class="col-md-2">
                <h4>Ảnh</h4>
            </div>
            <div class="col-md-4">
                <h4>Tên sản phẩm</h4>
            </div>
            <div class="col-md-2">
                <h4>Số lượng</h4>
            </div>
            <div class="col-md-2">
                <h4>Tổng giá</h4>
            </div>
            <div class="col-md-2">
                <h4>Mã đơn hàng</h4>
            </div>
        </div>
        <%
            List<OrderDetail> details = (List<OrderDetail>) session.getAttribute("details");
            if(!details.isEmpty()) {
                for(OrderDetail detail : details) {
                    OrdersDAO dao = new OrdersDAO();
                    Products p = dao.findImageBy(detail.getProduct_id());
                    Orders o = dao.findBy(detail.getOrder_id());
        %>
        <div class="row mb-3">
            <div class="col-md-2">
                <img src="<%=request.getServletContext().getContextPath()%>/<%=p.getImage()%>" width="100" height="100" alt="">
            </div>
            <div class="col-md-4">
                <p><%=p.getProduct_name()%></p>
            </div>
            <div class="col-md-2">
                <p><%=detail.getQuantity()%></p>
            </div>
            <div class="col-md-2">
                <p><%=o.getTotalPrice()%>
                </p>
            </div>
            <div class="col-md-2">
                <p><%=detail.getOrder_id()%></p>
            </div>

        </div>
        <%}}%>

    </div>
</main>
</body>
</html>
