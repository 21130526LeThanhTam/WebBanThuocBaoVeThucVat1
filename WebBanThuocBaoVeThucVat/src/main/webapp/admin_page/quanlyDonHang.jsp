<%@ page import="bean.Import" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.OrderTable" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Quản lý Đơn hàng</title>
    <style>
        /* Tùy chỉnh CSS cho DataTables */
        .dataTables_wrapper .dataTables_paginate .paginate_button {
            padding: 0.5rem 0.75rem;
            margin-left: -1px;
            border: 1px solid #dee2e6;
            border-radius: 0.25rem;
            color: #007bff;
            background-color: #fff;
            text-decoration: none;
            cursor:pointer;
        }
        .dataTables_wrapper .dataTables_paginate .paginate_button:hover {
            color: #0056b3;
            background-color: #e9ecef;
            border-color: #dee2e6;
        }
        .dataTables_wrapper .dataTables_filter input {
            border: 1px solid #dee2e6;
            border-radius: 0.25rem;
            padding: 0.375rem 0.75rem;
        }
        input[type="search"]{
            background-color:#fff;
        }

        .modal .modal-footer{
            background-color:#fff;

        }
        table.table td:last-child{
            font-size:14px;
        }
        .btn-view  {
            color:#ff6347;
            background-color: #ffe4e1;
        }

        .btn-view:hover {
            background-color: #ffcccb;
            color:#dc3545;
        }
    </style>
</head>
<body>
<div class="container my-5">
    <div class="text-center">
        <h3 class="page-title" style="color:black; font-weight: bold; margin-top: 30px; margin-bottom: 40px">Quản Lý Đơn Hàng</h3>
    </div>
    <div class="mb-5 d-flex align-items-center">
        <button class="btn btn-info ml-3" id="exportButton">=> Xuất file</button>
    </div>
    <table id="quanlyTable" class="table table-striped table-bordered" style="width:100%">
        <thead>
        <tr class="ex">
            <th style="font-weight: bold">Id</th>
            <th style="font-weight: bold">Tên Người Mua</th>
            <th style="font-weight: bold">Địa Chỉ</th>
            <th style="font-weight: bold">Số Điện Thoại</th>
            <th style="font-weight: bold">Tổng tiền</th>
            <th style="font-weight: bold">Ngày Tạo</th>
            <th style="font-weight: bold">Thanh Toán</th>
            <th style="font-weight: bold">Tình Trạng Đơn Hàng</th>
            <th style="width:100px;font-weight: bold">Tính Năng</th>
        </tr>
        </thead>
        <tbody id='tableBody'>
        <%
            List<OrderTable> listOrderTables = (List<OrderTable>) request.getAttribute("listOrder");
            for (OrderTable order : listOrderTables) {
        %>
        <tr>
            <td><%= order.getId() %></td>
            <td><%= order.getUsername() %></td>
            <td><%= order.getAddress() %></td>
            <td><%= order.getPhone_number() %></td>
            <td><fmt:formatNumber value="<%= order.getTotal_price() %>" pattern="#,##0 VND"/></td>
            <td><fmt:formatDate value="<%= order.getCreateAt() %>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><%= order.getPayment_status() %></td>
            <td><%= order.getOrder_status() %></td>
            <td>
                <button class="btn btn-view view" data-toggle="modal" data-target="#orderDetailModal" data-id="<%= order.getId() %>">
                    <i class="fas fa-eye" data-toggle="tooltip" title="Xem chi tiết"></i>
                </button>
                <button class="btn btn-primary update-btn" data-toggle="modal" data-target="#updateModal" data-order-id="<%= order.getId() %>"><i class="fa-solid fa-pen-to-square"></i></button>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<!-- Modal Chi tiết đơn hàng -->
<div class="modal fade " id="orderDetailModal" tabindex="-1" role="dialog" aria-labelledby="orderDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog " role="document">
        <div class="modal-content" style="width:max-content">
            <div class="modal-header">
                <h5 class="modal-title" id="orderDetailModalLabel">Chi tiết đơn hàng</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                </button>
            </div>
            <div class="modal-body">
                <!-- Nội dung chi tiết đơn hàng sẽ được cập nhật tại đây -->
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th style="font-weight: bold">ID Chi tiết</th>
                        <th style="font-weight: bold">Tên sản phẩm</th>
                        <th style="font-weight: bold">Ảnh</th>
                        <th style="font-weight: bold">Số lượng</th>
                        <th style="font-weight: bold">Giá</th>
                    </tr>
                    </thead>
                    <tbody id="orderDetailsContent">
                    <!-- Nội dung sẽ được thêm vào đây qua AJAX -->
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal Cập nhật -->
<script>
    $(document).ready(function() {
        // Xử lý khi nhấn vào nút "Cập nhật", lấy ra trường id
        $(document).on('click', '.update-btn', function() {
            var orderId = $(this).data('order-id');
            $('#updateOrderId').val(orderId);
        });

        // Hiển thị chi tiết đơn hàng trong modal
        $('.view').on('click', function() {
            var orderId = $(this).data('id');
            $.ajax({
                type: 'GET',
                url: 'orderManagement',
                data: { action: 'view', orderId: orderId },
                success: function(response) {
                    var orderDetailsHtml = '';
                    response.forEach(function(detail) {
                        orderDetailsHtml += '<tr>';
                        orderDetailsHtml += '<td>' + detail.id + '</td>';
                        orderDetailsHtml += '<td>' + detail.product_name + '</td>';
                        orderDetailsHtml += '<td><img src="' + detail.img + '" alt="' + detail.product_name + '" style="width: 50px; height: 50px;"></td>';
                        orderDetailsHtml += '<td>' + detail.quantity + '</td>';
                        orderDetailsHtml += '<td>' + detail.priceDetails + '</td>';
                        orderDetailsHtml += '</tr>';
                    });
                    $('#orderDetailsContent').html(orderDetailsHtml);
                },
                error: function() {
                    alert('Có lỗi xảy ra khi lấy chi tiết đơn hàng');
                }
            });
        });



        // Xuất file excel
        $('#exportButton').on('click', function() {
            var tableElement = document.getElementById('quanlyTable');
            var wb = XLSX.utils.table_to_book(tableElement, {sheet: "Sheet1"});
            XLSX.writeFile(wb, 'OrderDetails.xlsx');
        });
    });

</script>
</body>
</html>