<%@ page import="java.util.List" %>
<%@ page import="bean.Products" %>
<%@ page import="Service.ProductsService" %>
<%@ page import="java.util.ArrayList" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Quản lý đơn hàng</title>
    <style>
        .dataTables_wrapper .dataTables_paginate .paginate_button {
            padding: 0.5rem 0.75rem;
            margin-left: -1px;
            border: 1px solid #dee2e6;
            border-radius: 0.25rem;
            color: #007bff;
            background-color: #fff;
            text-decoration: none;
            cursor: pointer;
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
        input[type="search"] {
            background-color: #fff;
        }
        .modal .modal-footer {
            background-color: #fff;
        }
        table.table td:last-child {
            font-size: 14px;
        }
        .btn {
            margin-right: 5px;
        }
    </style>
</head>
<%List<Products> getTenPro = (List<Products>) request.getAttribute("getTenPro");
    if (getTenPro == null) getTenPro = new ArrayList<>();%>
<%int pageValue = (int) request.getAttribute("page");%>
<%Integer tagAttribute = (Integer) request.getAttribute("tag");
    int tag = (tagAttribute != null) ? tagAttribute.intValue() : 1;%>

<body>
<div class="container my-5">
    <div class="text-center">
        <h3 class="page-title" style="color:black; font-weight: bold; margin-top: 30px; margin-bottom: 40px">Quản Lý Sản Phẩm</h3>
    </div>
    <div class="mb-5 d-flex align-items-center">
        <a href="./insertPro?finalPage=<%=pageValue%>" class="btn btn-success">+ Thêm sản phẩm mới</a>
        <button class="btn btn-info ml-3" id="exportButton">=> Xuất file</button>
    </div>
    <table id="productTable" class="table table-striped table-bordered" style="width:100%">
        <thead>
        <tr>
            <th></th>
            <th style="width: 200px;">Tên</th>
            <th style="width:175px">Loại sản phẩm</th>
            <th>Ảnh</th>
            <th>Giá</th>
            <th style="width: 150px;">Tính Năng</th>
        </tr>
        </thead>
        <tbody>
        <%for (Products a : getTenPro) {%>
        <tr>
            <th>
                <i class="fa-solid fa-circle-info"></i>
            </th>
            <th><%= a.getProduct_name() %></th>
            <th><%= a.cateOfProduct() %></th>
            <th><img src="<%= a.getImage() %>" alt="" style="width: 110px;height: 110px"></th>
            <th><%= a.formatPrice() %></th>
            <th>
                <a href="./editPro?proID=<%= a.getId() %>" class="btn btn-primary">
                    <i class="fa-solid fa-pen-to-square"></i>
                </a>
                <a href="#deleteModal<%= a.getId() %>" class="btn btn-danger" data-toggle="modal">
                    <i class="fa-solid fa-trash"></i>
                </a>
            </th>
        </tr>
        <div class="modal fade" id="deleteModal<%= a.getId() %>" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Xóa sản phẩm</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Bạn chắc muốn xóa sản phẩm <%= a.getProduct_name() %>?</p>
                        <p class="text-warning"><small>Bấm "hủy" để dừng lại</small></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="button" class="btn btn-success" onclick="deleteUser(<%= a.getId() %>, <%= tag %>)">Xóa</button>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        </tbody>
    </table>
</div>


<script type="text/javascript">
    <%--    Datatable--%>
    $(document).ready(function() {
        // Áp dụng DataTables cho bảng
        $('#productTable').DataTable({
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
    });
    // Xuất file excel
    document.getElementById('exportButton').addEventListener('click', function() {
        var table = document.getElementById('productTable');
        var wb = XLSX.utils.table_to_book(table, {sheet: "Sheet1"});
        XLSX.writeFile(wb, 'Product.xlsx');
    });
    function deleteUser(proID, page) {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "./deletePro");

        var inputProID = document.createElement("input");
        inputProID.setAttribute("type", "hidden");
        inputProID.setAttribute("name", "proID");
        inputProID.setAttribute("value", proID);

        var inputPage = document.createElement("input");
        inputPage.setAttribute("type", "hidden");
        inputPage.setAttribute("name", "page");
        inputPage.setAttribute("value", page);

        form.appendChild(inputProID);
        form.appendChild(inputPage);
        document.body.appendChild(form);

        form.submit();
    }
</script>
</body>
</html>
