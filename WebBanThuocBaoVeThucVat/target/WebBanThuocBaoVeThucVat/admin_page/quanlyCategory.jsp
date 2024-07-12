<%@ page import="java.util.List" %>
<%@ page import="bean.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Quản lý danh mục</title>
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
<% List<Category> listCategory = (List<Category>) request.getAttribute("listCategory");
    if (listCategory == null) listCategory = new ArrayList<>();%>
<body>
<div class="container my-5">
    <div class="text-center">
        <h3 class="page-title" style="color:black; font-weight: bold; margin-top: 30px; margin-bottom: 40px">Quản Lý Danh Mục</h3>
    </div>
    <div class="mb-5 d-flex align-items-center">
        <button class="btn btn-success" data-toggle="modal" data-target="#addCategoryModal">+ Thêm mới danh mục</button>
        <button class="btn btn-info ml-3" id="exportButton">=> Xuất file</button>
    </div>
    <table id="quanlyTable" class="table table-striped table-bordered" style="width:100%">
        <thead>
        <tr>
            <th>Mã</th>
            <th>Tên danh mục</th>
            <th style="width:100px">Tính Năng</th>
        </tr>
        </thead>
        <tbody>
        <% for (Category a : listCategory) { %>
        <tr>
            <th style="text-align: center; width: 20px"><%= a.getId() %></th>
            <th style="width:150px;"><%= a.getNameCategory() %></th>
            <th>
                <a href="javascript:void(0);" class="btn btn-primary" data-toggle="modal" onclick="submitEditForm(<%= a.getId() %>)">
                    <i class="fa-solid fa-pen-to-square"></i>
                </a>
                <a href="#deleteCategoryModal<%= a.getId() %>" class="btn btn-danger" data-toggle="modal">
                    <i class="fa-solid fa-trash"></i>
                </a>
            </th>
            <form id="editForm<%= a.getId() %>" action="./editCategory" method="post" accept-charset="UTF-8"></form>
        </tr>
<%--        xóa danh mục--%>
        <div class="modal fade" id="deleteCategoryModal<%= a.getId() %>" tabindex="-1" role="dialog" aria-labelledby="deleteCategoryModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteCategoryModalLabel">Xóa danh mục</h5>
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc muốn xóa <%= a.getNameCategory() %>?</p>
                        <p class="text-warning"><small>Bấm "hủy" để dừng lại</small></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="button" class="btn btn-success" onclick="deleteCate(<%=a.getId()%>)">Xóa</button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        </tbody>
    </table>
</div>
<%--THêm mới danh mục--%>
<div class="modal fade" id="addEmployeeModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addCategoryModalLabel">Thêm mới danh mục</h5>
            </div>
            <form action="./insertCate" method="post" accept-charset="UTF-8">
                <div class="modal-body">
                    <div class="form-group">
                        <label>Tên danh mục</label>
                        <input type="text" class="form-control" name="nameCate" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-success">Thêm</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%--edit danh mục--%>
<!----edit-modal start--------->
<div class="modal fade" tabindex="-1" id="editEmployeeModal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit Employees</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="emil" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Address</label>
                    <textarea class="form-control" required></textarea>
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" class="form-control" required>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-success">Save</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    // Xuất file excel
    document.getElementById('exportButton').addEventListener('click', function() {
        var table = document.getElementById('quanlyTable');
        var wb = XLSX.utils.table_to_book(table, {sheet: "Sheet1"});
        XLSX.writeFile(wb, 'category.xlsx');
    });

    function deleteCategory(cateID) {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "./deleteCate");

        var inputCateID = document.createElement("input");
        inputCateID.setAttribute("type", "hidden");
        inputCateID.setAttribute("name", "cateID");
        inputCateID.setAttribute("value", cateID);

        form.appendChild(inputCateID);
        document.body.appendChild(form);

        form.submit();
    }

    function submitEditForm(categoryId) {
        var form = document.getElementById('editForm' + categoryId);

        var inputCateID = document.createElement("input");
        inputCateID.type = "hidden";
        inputCateID.name = "categoryId";
        inputCateID.value = categoryId;
        form.appendChild(inputCateID);

        form.submit();
    }
</script>
</body>
</html>
