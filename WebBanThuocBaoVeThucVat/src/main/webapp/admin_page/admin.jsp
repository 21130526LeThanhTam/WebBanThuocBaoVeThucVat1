<%@ page import="bean.User" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="vi">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin vườn phố</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin_page/css/custom.css">
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
    <style>
        .card-custom {
            margin-bottom: 20px;
        }
        .chart-container {
            margin-top: 30px;
            margin-bottom:15px;
        }
        .text-info-custom {
            color: #17a2b8;
        }
        .card-icon {
            font-size: 40px;
        }
        .card-title {
            font-size: 22px;
            font-weight: bold;
        }
        .card-text {
            font-size: 18px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<%
    int numUser = (request.getAttribute("numUser") != null) ? (int) request.getAttribute("numUser") : 0;
    int numPro = (request.getAttribute("numPro") != null) ? (int) request.getAttribute("numPro") : 0;
%>
<div class="wrapper">
    <!-- Sidebar -->
    <nav id="sidebar" class="bg-light">
        <div class="sidebar-header text-center">
            <a href="/HomePageController">
                <img src="./assets/img/logo.png" class="img-fluid"/>
            </a>
        </div>
        <ul class="list-unstyled components">
            <li class="active">
                <a href="./admin_dashboard" class="dashboard">
                    <i class="material-icons">dashboard</i> Trang chủ
                </a>
            </li>
            <li>
                <a href="#userSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                    <i class="material-icons">aspect_ratio</i> Quản lý người dùng
                </a>
                <ul class="collapse list-unstyled" id="userSubmenu">
                    <li>
                        <a href="./maUser?roleID=0&uid=1">
                            <i class="fas fa-user"></i> Quản lý khách hàng
                        </a>
                    </li>
                    <li>
                        <a href="./maUser?roleID=1&uid=1">
                            <i class="fas fa-user-tie"></i> Quản lý nhân viên
                        </a>
                    </li>
                    <li>
                        <a href="./logAdmin">
                            <i class="fas fa-history"></i> Lịch sử hoạt động
                        </a>
                    </li>
                </ul>

            </li>
            <li>
                <a href="./maProduct">
                    <i class="fas fa-boxes"></i> Quản lý Sản Phẩm
                </a>
            </li>
            <li>
                <a href="./maCategory">
                    <i class="fas fa-list-alt"></i> Quản lý Danh Mục
                </a>
            </li>
            <li>
                <a href="./importManagement">
                    <i class="fas fa-truck-loading"></i> Quản lý Nhập Hàng
                </a>
            </li>
            <li>
                <a href="./maDiscount">
                    <i class="fas fa-tags"></i> Quản lý Mã Giảm Giá
                </a>
            </li>
        </ul>
    </nav>
    <!-- Page Content -->
    <div id="content">
        <!-- Top Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <button type="button" id="sidebarCollapse" class="btn btn-info">
                    <i class="material-icons ">signal_cellular_alt</i>
                </button>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">
                                <i class="material-icons">person</i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="#">
                                    <i class="material-icons">person_outline</i> Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="material-icons">settings</i> Settings
                                </a>
                                <%
                                    User auth = (User) session.getAttribute("admin");
                                    if (auth != null) {
                                %>
                                <a class="dropdown-item" href="logout">
                                    <i class="material-icons">logout</i> Đăng xuất
                                </a>
                                <% } %>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Main Content -->
        <div class="container-fluid">
            <div class="text-center">
                <h3 class="page-title" style="color:black; font-weight: bold; margin-top: 30px">Trang Chủ</h3>
            </div>
            <div class="row chart-container">
                <div class="col-lg-6">
                    <div class="card">
                        <div class="card-title">
                            Biểu đồ doanh thu
                        </div>
                        <div class="card-body">
                            <canvas id="revenueChart"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="card">
                        <div class="card-title">
                            Biểu đồ đơn hàng
                        </div>
                        <div class="card-body">
                            <canvas id="orderChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="card text-center card-custom">
                        <div class="card-header bg-warning">
                            <i class="fas fa-shopping-cart card-icon"></i>
                        </div>
                        <div class="card-body">
                            <p class="card-title"><strong>Đơn hàng</strong></p>
                            <h3 class="card-text">70,340</h3>
                        </div>
                        <div class="card-footer">
                            <a href="#" class="text-info-custom">Xem chi tiết báo cáo</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="card text-center card-custom">
                        <div class="card-header bg-danger">
                            <i class="fas fa-box-open card-icon"></i>
                        </div>
                        <div class="card-body">
                            <p class="card-title"><strong>Sản phẩm</strong></p>
                            <h3 class="card-text"><%=numPro%></h3>
                        </div>
                        <div class="card-footer">
                            <a href="#" class="text-info-custom">Doanh thu sản phẩm</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="card text-center card-custom">
                        <div class="card-header bg-success">
                            <i class="fas fa-dollar-sign card-icon"></i>
                        </div>
                        <div class="card-body">
                            <p class="card-title"><strong>Doanh thu</strong></p>
                            <h3 class="card-text">$23,100</h3>
                        </div>
                        <div class="card-footer">
                            <a href="#" class="text-info-custom">Doanh thu hàng tuần</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="card text-center card-custom">
                        <div class="card-header bg-info">
                            <i class="fas fa-users card-icon"></i>
                        </div>
                        <div class="card-body">
                            <p class="card-title"><strong>Khách hàng</strong></p>
                            <h3 class="card-text"><%=numUser%></h3>
                        </div>
                        <div class="card-footer">
                            <a href="#" class="text-info-custom">Mới cập nhật</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="card text-center card-custom">
                        <div class="card-header bg-danger">
                            <i class="fas fa-exclamation-triangle card-icon"></i>
                        </div>
                        <div class="card-body">
                            <p class="card-title"><strong>Sắp hết hàng</strong></p>
                            <h5 class="card-text">4</h5>
                        </div>
                        <div class="card-footer">
                            <a href="#" class="text-info-custom">Cần nhập hàng</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="card text-center card-custom">
                        <div class="card-header bg-primary">
                            <i class="fas fa-chart-line card-icon"></i>
                        </div>
                        <div class="card-body">
                            <p class="card-title"><strong>Lợi nhuận</strong></p>
                            <h3 class="card-text">$10,000</h3>
                        </div>
                        <div class="card-footer">
                            <a href="#" class="text-info-custom">Chi tiết lợi nhuận</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    var ctx = document.getElementById('revenueChart').getContext('2d');
    var revenueChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [{
                label: 'Doanh thu',
                data: [12, 19, 3, 5, 2, 3, 9],
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var ctx2 = document.getElementById('orderChart').getContext('2d');
    var orderChart = new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [{
                label: 'Đơn hàng',
                data: [15, 25, 10, 20, 30, 40, 50],
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
            $('#content').toggleClass('active');
        });
    });
</script>
</body>
</html>
