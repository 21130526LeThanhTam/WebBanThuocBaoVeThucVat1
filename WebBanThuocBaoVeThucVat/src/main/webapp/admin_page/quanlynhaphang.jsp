<%@ page import="bean.Import" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Quản lý Nhập hàng</title>
    <link rel="icon" type="image/x-icon" href="assets/img/logo.png">

    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- DataTables CSS with Bootstrap -->
    <link href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<%--    fontawsome--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- DataTables JS with Bootstrap -->
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
<%--    Excel--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.0/xlsx.full.min.js"></script>
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
    </style>
</head>
<body>
<div class="container my-5">
    <div class="mb-5 d-flex align-items-center">
        <button class="btn btn-success" data-toggle="modal" data-target="#createModal">+ Tạo mới nhập hàng</button>
        <button class="btn btn-info ml-3" id="exportButton">=> Xuất file</button>
    </div>
    <table id="orderDetailsTable" class="table table-striped table-bordered" style="width:100%">
        <thead>
        <tr class="ex">
            <th>Id</th>
            <th>Id product</th>
            <th>Số lượng</th>
            <th>Tên sản phẩm</th>
            <th>Giá tiền</th>
            <th>Ngày nhập</th>
            <th>Tình Trạng</th>
            <th>Tính Năng</th>
        </tr>
        </thead>
        <tbody id='tableBody'>
        <%
            List<Import> importOrders = (List<Import>) request.getAttribute("importOrders");
            for (Import order : importOrders) {
        %>
        <tr>
            <td><%= order.getId() %></td>
            <td><%= order.getId_product() %></td>
            <td><%= order.getQuantity() %></td>
            <td><%= order.getProduct_name() %></td>
            <td><fmt:formatNumber value="<%= order.getPrice() %>" pattern="#,##0 VND"/></td>
            <td><fmt:formatDate value="<%= order.getDate_import() %>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><%= order.getStatus() %></td>
            <td>
                <button class="btn btn-primary update-btn" data-toggle="modal" data-target="#updateModal" data-order-id="<%= order.getId() %>"><i class="fa-solid fa-pen-to-square"></i></button>
                <button class="btn btn-danger delete-btn" data-toggle="modal" data-target="#deleteModal" data-order-id="<%= order.getId() %>"><i class="fa-solid fa-trash"></i></button>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
<!-- Tạo đơn hàng mới -->
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="createModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/importManagement" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="createModalLabel">Tạo mới nhập hàng</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="productId">Id sản phẩm</label>
                        <input type="text" class="form-control" id="productId" name="productId" required>
                    </div>
                    <div class="form-group">
                        <label for="quantity">Số lượng</label>
                        <input type="text" class="form-control" id="quantity" name="quantity" required>
                    </div>
                    <div class="form-group">
                        <label for="price">Giá tiền</label>
                        <input type="text" class="form-control" id="price" name="price" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary" name="action" value="create">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal Cập nhật -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/importManagement" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateModalLabel">Cập nhật tình trạng</h5>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="orderId" id="updateOrderId">
                    <div class="form-group">
                        <label for="status">Tình trạng mới</label>
                        <input type="text" class="form-control" id="status" name="status" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary">Cập nhật</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal Xóa -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/importManagement" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="orderId" id="deleteOrderId">
                    <p>Bạn có chắc chắn muốn xóa đơn hàng này không?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-danger">Xóa</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
<%--    Datatable--%>
    $(document).ready(function() {
        // Áp dụng DataTables cho bảng
        $('#orderDetailsTable').DataTable({
            "language": {
                "lengthMenu": "Hiển thị _MENU_ bản ghi mỗi trang",
                "zeroRecords": "Không tìm thấy kết quả",
                "info": "Hiển thị trang _PAGE_ trong tổng số _PAGES_",
                "infoEmpty": "Không có bản ghi nào",
                "infoFiltered": "(lọc từ _MAX_ bản ghi)",
                "search": "Tìm kiếm:",
                "paginate": {
                    "first": "Đầu",
                    "last": "Cuối",
                    "next": "Sau",
                    "previous": "Trước"
                },
            }
        });

        // Xử lý khi nhấn vào nút "Cập nhật"
        $('.update-btn').on('click', function() {
            var orderId = $(this).data('order-id');
            $('#updateOrderId').val(orderId);
        });

        // Xử lý khi nhấn vào nút "Xóa"
        $('.delete-btn').on('click', function() {
            var orderId = $(this).data('order-id');
            $('#deleteOrderId').val(orderId);
        });
    });
 // Xuất file excel
    document.getElementById('exportButton').addEventListener('click', function() {
    // Lấy bảng HTML
    var table = document.getElementById('orderDetailsTable');
    // Chuyển đổi bảng HTML thành worksheet của Excel
    var wb = XLSX.utils.table_to_book(table, {sheet: "Sheet1"});
    // Xuất workbook thành file Excel
    XLSX.writeFile(wb, 'OrderDetails.xlsx');
});
</script>
</body>
</html>
