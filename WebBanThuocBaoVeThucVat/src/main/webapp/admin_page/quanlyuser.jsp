<%@ page import="java.util.List" %>
<%@ page import="bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<User> dsUser = (List<User>) request.getAttribute("dsUser");
    if (dsUser == null) dsUser = new ArrayList<>(); %>
<% Integer roleInt2 = (Integer) request.getAttribute("roleInt2");
    if (roleInt2 != null) {
        int roleInt2Value = roleInt2.intValue();
    } else {
        // Xử lý khi roleInt2 là null...
    } %>
<% Integer tagAttribute = (Integer) request.getAttribute("tag");
    int tag = (tagAttribute != null) ? tagAttribute.intValue() : 1; %>
<% int endPage = (int) request.getAttribute("endPage"); %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/v/bs5/jq-3.7.0/dt-2.0.5/datatables.min.css" rel="stylesheet">
    <title>Quản lý người dùng</title>
    <style>
        a {
            text-decoration: none !important;
        }
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
    </style>
</head>
<body>
<div class="container my-5">
    <div class="text-center">
        <h3 class="page-title" style="color:black; font-weight: bold; margin-top: 30px; margin-bottom: 40px">Quản lý người dùng</h3>
    </div>
    <div class="mb-5 d-flex align-items-center">
        <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal" <%= (roleInt2 == 1) ? "" : "hidden=\"hidden\"" %>>+ Thêm người dùng</a>
        <button class="btn btn-info ml-3" style="color:white;" id="exportButton">=> Xuất file</button>
    </div>
    <table id="quanlyUserTable" class="table table-striped table-bordered" style="width:100%">
        <thead>
        <tr>
            <th>Mã</th>
            <th style="width: 100px">Tên</th>
            <th style="width: 100px">Email</th>
            <th style="width: 100px">Số điện thoại</th>
            <th style="width: 100px">Vai trò</th>
            <th style="width: 150px">Tính Năng</th>
        </tr>
        </thead>
        <tbody>
        <% for (User a : dsUser) { %>
        <tr id="row_user_<%=a.getId()%>">
            <th><%=a.getId()%></th>
            <th><%=a.getUsername()%></th>
            <th><%=a.getEmail()%></th>
            <th><%=a.getPhone()%></th>
            <th><%=a.roleString()%></th>
            <th>
                <button class="btn btn-primary update-btn" data-toggle="modal" data-target="#editEmployeeModal<%=a.getId()%>">
                    <i class="fa-solid fa-pen-to-square"></i>
                </button>
                <button class="btn btn-danger delete-btn" data-toggle="modal" data-target="#deleteEmployeeModal<%=a.getId()%>">
                    <i class="fa-solid fa-trash"></i></button>
                </button>
            </th>
        </tr>
        <!-- Xóa người dùng -->
        <div class="modal fade" id="deleteEmployeeModal<%=a.getId()%>" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Xác nhận xóa</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc chắn muốn xóa <%=a.getUsername()%> ra khỏi danh sách?</p>
                        <p class="text-warning"><small>Bấm "hủy" để dừng lại</small></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="button" class="btn btn-danger" onclick="deleteUser(<%=a.getId()%>)">Xóa</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Cập nhật người dùng -->
        <div class="modal fade" id="editEmployeeModal<%=a.getId()%>" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Cập nhật người dùng</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tên</label>
                            <input type="text" name="name" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Mật khẩu</label>
                            <input type="password" name="pass"  class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Vai trò</label>
                            <select class="form-control" name="role" required>
                                <option value="user" >User</option>
                                <option value="admin">Admin</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-success" id="saveButton">Lưu</button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        </tbody>
    </table>
</div>

<!-- Thêm người dùng -->
<div class="modal fade" id="addEmployeeModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm người dùng</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="addUserForm" action="<%=request.getServletContext().getContextPath()%>/insertUser" method="post" accept-charset="UTF-8">
                    <div class="form-group">
                        <label>Tên tài khoản</label>
                        <input type="text" name="username" class="form-control" required>
                    </div>
                    <div class="form-group row">
                        <div class="col">
                            <label>Họ</label>
                            <input type="text" name="lastname" class="form-control" required>
                        </div>
                        <div class="col">
                            <label>Tên (Nhập tên lót)</label>
                            <input type="text" name="surname" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <div class="input-group">
                            <input type="password" name="pass" id="passwordInput" class="form-control" required>
                            <div class="input-group-append">
                                    <span class="input-group-text" id="togglePassword">
                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                    </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="phone" class="form-control" required>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-success">Thêm</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('#quanlyUserTable').DataTable({
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
    //Xuất file excel
    document.getElementById('exportButton').addEventListener('click', function() {
        // Lấy bảng hiện tại (người dùng)
        var table = document.getElementById('quanlyUserTable');

        // Chuyển đổi bảng HTML thành worksheet của Excel
        var wb = XLSX.utils.table_to_book(table, {sheet: "Sheet1"});
        // Tên file Excel
        var fileName = 'UserDetails.xlsx';

        // Xuất workbook thành file Excel
        XLSX.writeFile(wb, fileName);
    });



    // logic add
    $('#addUserForm').submit(function(e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function(response) {
                alert('Người dùng đã được thêm thành công!');
                location.reload();
            },
            error: function() {
                alert('Lỗi xảy ra khi thêm người dùng!');
            }
        });
    });

    function deleteUser(userID) {
        $.ajax({
            url: "/WebBanThuocBaoVeThucVat/deleteUser",
            type: "POST",
            data: { 'userID': userID },
            success: function(data) {
                alert('Người dùng đã được xóa thành công!');
                $("#row_user_" + userID).remove();
            },
            error: function(xhr, error) {
                alert('Lỗi xảy ra khi xóa người dùng!');
            }
        });
    }

    const passwordInput = document.getElementById('passwordInput');
    const togglePassword = document.getElementById('togglePassword');
    togglePassword.addEventListener('click', function() {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
        togglePassword.innerHTML = type === 'password' ? '<i class="fa fa-eye" aria-hidden="true"></i>' : '<i class="fa fa-eye-slash" aria-hidden="true"></i>';
    });
</script>
</body>
</html>
