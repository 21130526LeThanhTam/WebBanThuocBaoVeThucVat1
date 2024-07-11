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
                <a href="#editCategoryModal<%= a.getId() %>" class="btn btn-primary" data-toggle="modal">
                    <i class="fa-solid fa-pen-to-square"></i>
                </a>
                <a href="#deleteCategoryModal<%= a.getId() %>" class="btn btn-danger" data-toggle="modal">
                    <i class="fa-solid fa-trash"></i>
                </a>
            </th>
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
                        <button type="button" class="btn btn-success" onclick="deleteCategory(<%=a.getId()%>)">Xóa</button>
                    </div>
                </div>
            </div>
        </div>
        <%--edit danh mục--%>
        <div class="modal fade" id="editCategoryModal<%= a.getId() %>" tabindex="-1" role="dialog" aria-labelledby="editCategoryModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editCategoryModalLabel">Chỉnh sửa danh mục</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form id="editCategoryForm" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="editCategoryName<%= a.getId() %>">Tên danh mục</label>
                                <input type="text" class="form-control" id="editCategoryName<%= a.getId() %>" name="categoryName" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                            <button type="submit" class="btn btn-success" onclick="submitEditForm(<%=a.getId()%>)">Thay Đổi</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <% } %>
        </tbody>
    </table>
</div>
<%--THêm mới danh mục--%>
<div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addCategoryModalLabel">Thêm mới danh mục</h5>
            </div>
            <form action="./insertCate" method="post" accept-charset="UTF-8" id="addCategoryForm">
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


<script type="text/javascript">
    // Xuất file excel
    document.getElementById('exportButton').addEventListener('click', function() {
        var table = document.getElementById('quanlyTable');
        var wb = XLSX.utils.table_to_book(table, {sheet: "Sheet1"});
        XLSX.writeFile(wb, 'category.xlsx');
    });
    //xóa danh mục
    function deleteCategory(cateID) {
        $.ajax({
            type: "POST",
            url: "./deleteCate",
            data: { cateID: cateID },
            success: function(data) {
                if (data.status === "success") {
                    alert(data.message);
                    loadContent($('#categoryManagementLink'));
                } else {
                    alert(data.message);
                }
            },
            error: function(xhr, error) {
                alert('Lỗi xảy ra khi xóa danh mục! Lỗi: ' + xhr.responseText);
            }
        });
    }
    // chỉnh sửa danh mục
    function submitEditForm(cateID) {
        var categoryName = $('#editCategoryName' + cateID).val();
        var formData = {
            cateID: cateID,
            categoryName: categoryName
        };
        console.log()
        $.ajax({
            type: "POST",
            url: "./editCategory",
            data: formData,
            success: function(data) {
                if (data.status === "success") {
                    alert(data.message);
                    loadContent($('#categoryManagementLink'));
                } else {
                    alert(data.message);
                }
            },
            error: function(xhr, error) {
                alert('Lỗi xảy ra khi xóa danh mục! Lỗi: ' + xhr.responseText);
            }
        });
    }
    // thêm danh mục sử dụng ajax
    $(document).ready(function() {
        $('#addCategoryForm').on('submit', function(e) {
            e.preventDefault();
            $.ajax({
                type: "POST",
                url: "./insertCate",
                data: $(this).serialize(),
                success: function(data) {
                    if (data.status === "success") {
                        alert(data.message);
                        $('#addCategoryModal').modal('hide');
                        loadContent($('#categoryManagementLink'));
                    } else {
                        alert(data.message);
                    }
                },
                error: function(xhr, error) {
                    alert('Lỗi xảy ra khi thêm danh mục mới! Lỗi: ' + xhr.responseText);
                }
            });
        });
    });
</script>
</body>
</html>
