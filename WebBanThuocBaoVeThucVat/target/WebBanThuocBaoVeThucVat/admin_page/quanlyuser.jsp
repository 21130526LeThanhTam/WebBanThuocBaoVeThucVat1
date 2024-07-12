<%@ page import="java.util.List" %>
<%@ page import="bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<User> dsUser = (List<User>) request.getAttribute("dsUser");
    if (dsUser == null) dsUser = new ArrayList<>(); %>
<% Integer roleInt2 = (Integer) request.getAttribute("roleInt2");%>
<%--Tag này không biết để làm gì--%>
<% Integer tagAttribute = (Integer) request.getAttribute("tag");
    int tag = (tagAttribute != null) ? tagAttribute.intValue() : 1; %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit =no">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
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
        #togglePassword{
         cursor:pointer;
        }
    </style>
</head>
<body>
<div class="container my-5">
    <div class="text-center">
        <h3 class="page-title" style="color:black; font-weight: bold; margin-top: 30px; margin-bottom: 40px">
            <%= (roleInt2 == 1) ? "Quản lý nhân viên" : "Quản lý khách hàng" %>
        </h3>
    </div>
<<<<<<< HEAD

    <!-------sidebar--design- close----------->


    <!-------page-content start----------->

    <div id="content">

        <!------top-navbar-start----------->

        <div class="top-navbar">
            <div class="xd-topbar">
                <div class="row">
                    <div class="col-2 col-md-1 col-lg-1 order-2 order-md-1 align-self-center">
                        <div class="xp-menubar">
                            <span class="material-icons text-white">signal_cellular_alt</span>
                        </div>
                    </div>

                    <div class="col-md-5 col-lg-3 order-3 order-md-2">
                        <div class="xp-searchbar">
                            <form action="./maUser?" method="get">
                                <div class="input-group">
                                    <input type="search" name="search" class="form-control"
                                           placeholder="Search">
                                    <div class="input-group-append">
                                        <button class="btn" type="submit" id="button-addon2">Go
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <jsp:include page="include/header_content.jsp"/>

                </div>
                <div class="xp-breadcrumbbar text-center">
                    <h4 class="page-title">Quản lý <%= (roleInt2 == 1) ? "Nhân viên" : "Khách hàng" %>
                    </h4>
                    </h4>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#">Vishweb</a></li>
                        <li class="breadcrumb-item active" aria-curent="page">Dashboard</li>
                    </ol>
                </div>
            </div>
        </div>
        <!------top-navbar-end----------->
        <%
            String dialog = request.getAttribute("dialog")==null?"":request.getAttribute("dialog")+"";
        %>

        <!------main-content-start----------->

        <div class="main-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6 p-0 flex justify-content-lg-start justify-content-center">
                                    <h2 class="ml-lg-2">Quản lý người dùng</h2>
                                </div>
                                <div class="col-sm-6 p-0 flex justify-content-lg-end justify-content-center">
                                    <a href="#addEmployeeModal" class="btn btn-success"
                                       data-toggle="modal" <%= (roleInt2 == 1) ? "" : "hidden=\"hidden\"" %>>
                                        <i class="material-icons">&#xE147;</i>
                                        <span>Thêm người dùng</span>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <table id="quanlyUserTable" class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Mã</th>
                                <th>Tên</th>
                                <th>Email</th>
                                <th>Số điện thoại</th>
                                <th>Vai trò</th>
                                <th>Chỉnh</th>
                            </tr>
                            </thead>
                            <tbody>

                            <% for (User a : dsUser) {%>
                            <tr id="row_user_<%=a.getId()%>">
                                <th><a href=""><span class="material-icons">person</span></a></th>

                                <th><%=a.getId()%>
                                </th>
                                <th><%=a.getUsername()%>
                                </th>
                                <th><%=a.getEmail()%>
                                </th>
                                <th><%=a.getPhone()%>
                                </th>
                                <th><%=a.roleString()%>
                                </th>
                                <th>
                                    <a href="#editEmployeeModal<%=a.getId()%>" class="edit" data-toggle="modal">
                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                    </a>
                                    <a href="#deleteEmployeeModal<%=a.getId()%>" class="delete" data-toggle="modal">
                                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                    </a>
                                </th>
                            </tr>
                            <!---------------------------------Xóa người dùng-------------------------------------->
                            <div class="modal fade" tabindex="-1" id="deleteEmployeeModal<%=a.getId()%>" role="dialog">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Xóa thành viên</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Bạn có chắc muốn xóa <%=a.getUsername()%> ra khỏi danh sách ?</p>
                                            <p class="text-warning"><small>Bấm "hủy" để dừng lại</small></p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy
                                            </button>
                                            <button type="button" class="btn btn-success"
                                                    onclick="deleteUser(<%=a.getId()%>)">Xóa
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--Thêm người dùng-->
                            <div class="modal fade" tabindex="-1" id="editEmployeeModal<%=a.getId()%>" role="dialog">
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
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>


                <!-------------------------------------Thêm người dùng-------------------------------------->
                <div class="modal fade" tabindex="-1" id="addEmployeeModal" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Thêm thành viênbnnnnnnnnnn</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>

                            <form id="addUserForm" action="<%=request.getServletContext().getContextPath()%>/insertUser" method="post" accept-charset="UTF-8">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label>Tên tài khoản</label>
                                        <label>
                                            <input type="text" name="username" class="form-control" required>
                                        </label>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col">
                                            <label>Họ</label>
                                            <label>
                                                <input type="text" name="lastname" class="form-control" required>
                                            </label>
                                        </div>
                                        <div class="col">
                                            <label>Tên(Nhập tên lót)</label>
                                            <input type="text" name="surname" class="form-control" required>
                                        </div>
                                    </div>
                                    <!-- Các phần còn lại của biểu mẫu -->
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
                                                <i class="material-icons">visibility</i>
                                            </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>Số điện thoại</label>
                                        <input type="text" name="phone" class="form-control" required>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                    <button type="submit" class="btn btn-success">Thêm aaa</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!----edit-modal end--------->


                <!----edit-modal start--------->
<%--                <div class="modal fade" tabindex="-1" id="editEmployeeModal" role="dialog">--%>
<%--                    <div class="modal-dialog" role="document">--%>
<%--                        <div class="modal-content">--%>
<%--                            <div class="modal-header">--%>
<%--                                <h5 class="modal-title">Edit Employees</h5>--%>
<%--                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                                    <span aria-hidden="true">&times;</span>--%>
<%--                                </button>--%>
<%--                            </div>--%>
<%--                            <div class="modal-body">--%>
<%--                                <div class="form-group">--%>
<%--                                    <label>Tên</label>--%>
<%--                                    <input type="text" name="name" class="form-control" required>--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    <label>Email</label>--%>
<%--                                    <input type="email" name="email" class="form-control" required>--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    <label>Mật khẩu</label>--%>
<%--                                    <input type="password" name="pass"  class="form-control" required>--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    <label>Vai trò</label>--%>
<%--                                    <select class="form-control" name="role" required>--%>
<%--                                        <option value="user" >User</option>--%>
<%--                                        <option value="admin">Admin</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="modal-footer">--%>
<%--                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>--%>
<%--                                <button type="button" class="btn btn-success" id="saveButton">Lưu</button>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <!----edit-modal end--------->


                <!----delete-modal start--------->

                <!----edit-modal end--------->

            </div>
        </div>
        <footer class="footer">
            <div class="container-fluid">
                <div class="footer-in">
                    <p class="mb-0">&copy 2021 Vishweb Design . All Rights Reserved.</p>
                </div>
            </div>
        </footer>


=======
    <input type="hidden" id="roleInt2" value="<%= roleInt2 %>">
    <div class="mb-5 d-flex align-items-center">
        <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal" <%= (roleInt2 == 1) ? "" : "hidden=\"hidden\"" %>>+ Thêm người dùng</a>
        <button class="btn btn-info ml-3" style="color:white;" id="exportButton">=> Xuất file</button>
    </div>
    <div id="userContent">
        <table id="quanlyTable" class="table table-striped table-bordered" style="width:100%">
            <thead>
            <tr>
                <th>Mã</th>
                <th style="width: 100px">Tên</th>
                <th style="width: 100px">Email</th>
                <th style="width: 100px">Số điện thoại</th>
                <th style="width: 100px">Vai trò</th>
                <th style="width: 100px">Trạng Thái</th>
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
                <th><%= a.getActive() == 1 ? "Hoạt động" : "Vô Hiệu Hóa" %></th>
                <th>
                    <button class="btn <%= a.getActive() == 1 ? "btn-warning" : "btn-success" %>" data-toggle="modal" data-target="#toggleDisableModal<%=a.getId()%>">
                        <i class="fas <%= a.getActive() == 1 ? "fa-ban" : "fa-check" %>"></i>
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
            <!-- vô hiệu hóa, kích hoạt lại người dùng-->
            <div class="modal fade" id="toggleDisableModal<%=a.getId()%>" tabindex="-1" role="dialog" aria-labelledby="disableModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><%= a.getActive() == 1 ? "Xác nhận vô hiệu hóa" : "Xác nhận kích hoạt lại" %></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Bạn có chắc chắn muốn <%= a.getActive() == 1 ? "vô hiệu hóa" : "kích hoạt lại" %> tài khoản của <%=a.getUsername()%>?</p>
                            <p class="text-warning"><small>Bấm "Hủy" để dừng lại</small></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                            <button type="button" class="btn <%= a.getActive() == 1 ? "btn-danger" : "btn-success" %>" onclick="toggleDisableUser(<%=a.getId()%>, <%=a.getActive()%>)"><%= a.getActive() == 1 ? "Vô hiệu hóa" : "Kích hoạt lại" %></button>
                        </div>
                    </div>
                </div>
            </div>
            <% } %>
            </tbody>
        </table>
>>>>>>> origin/main
    </div>
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
        // Xuất file excel
        $('#exportButton').on('click', function() {
            var tableElement = document.getElementById('quanlyTable');
            var wb = XLSX.utils.table_to_book(tableElement, {sheet: "Sheet1"});
            var roleInt2 = $('#roleInt2').val(); // Lấy giá trị roleInt2 từ input ẩn
            var fileName = (roleInt2 == 1 ? 'EmployeeDetails.xlsx' : 'CustomerDetails.xlsx');
            XLSX.writeFile(wb, fileName);
        });
        // thêm người dùng
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

        //xóa người dùng
        window.deleteUser = function(userID) {
            $.ajax({
                url: "/deleteUser",
                type: "POST",
                data: { 'userID': userID },
                success: function(data) {
                    alert('Người dùng đã được xóa thành công!');
                    $("#row_user_" + userID).remove();
                    // Đóng hộp thoại sau khi xóa thành công
                    $('#deleteEmployeeModal' + userID).modal('hide');
                },
                error: function(xhr, error) {
                    alert('Lỗi xảy ra khi xóa người dùng! Lỗi: ' + xhr.responseText);
                }
            });
        };
        // logic toggle disable user
        window.toggleDisableUser = function(userID, currentState) {
            var action = currentState == 1 ? 'disableUser' : 'cancelDisableUser';
            $.ajax({
                url: '/' + action,
                type: "POST",
                data: { 'userID': userID },
                success: function(data) {
                    alert(currentState == 1 ? 'Tài khoản đã được vô hiệu hóa!' : 'Tài khoản đã được kích hoạt lại!');
                    loadUserTable();
                },
                error: function(xhr, error) {
                    alert('Lỗi xảy ra khi ' + (currentState == 1 ? 'vô hiệu hóa' : 'kích hoạt lại') + ' tài khoản! Lỗi: ' + xhr.responseText);
                }
            });
        };

        // Load user table
        function loadUserTable() {
            var roleInt2 = $('#roleInt2').val();
            $.ajax({
                url: '/maUser?roleID=' + roleInt2 + '&uid=1',
                type: "GET",
                success: function(data) {
                    $('#userContent').html($(data).find('#userContent').html());
                    // Re-initialize the DataTable
                    $('#quanlyTable').DataTable();
                },
                error: function(xhr, error) {
                    alert('Lỗi xảy ra khi tải lại bảng người dùng! Lỗi: ' + xhr.responseText);
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
    });
</script>
</body>
</html>
