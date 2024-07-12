<%--
  Created by IntelliJ IDEA.
  User: uyent
  Date: 7/5/2024
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<div>
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
</body>
</html>
