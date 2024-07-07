<%@ page import="java.util.List" %>
<%@ page import="bean.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Admin1
  Date: 11/24/2023
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<User> dsUser = (List<User>) request.getAttribute("dsUser");
    if (dsUser == null) dsUser = new ArrayList<>();%>
<% Integer roleInt2 = (Integer) request.getAttribute("roleInt2");
    if (roleInt2 != null) {
        int roleInt2Value = roleInt2.intValue();
        // Tiếp tục sử dụng roleInt2Value...
    } else {
        // Xử lý khi roleInt2 là null...
    }%>
<% Integer tagAttribute = (Integer) request.getAttribute("tag");
    int tag = (tagAttribute != null) ? tagAttribute.intValue() : 1;%>
<% int endPage = (int) request.getAttribute("endPage");%>
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
        /*.col-md-auto .dt-search {*/
        /*    padding-right: 20px;*/
        /*}*/
        .col-md-auto .form-control {
            background-color: whitesmoke;
        }
    </style>

</head>
<body>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.datatables.net/v/bs5/jq-3.7.0/dt-2.0.5/datatables.min.js"></script>
<script>
    new DataTable('#quanlyUserTable', {
        layout: {
            bottomEnd: {
                paging: {
                    boundaryNumbers: false
                }
            }
        }
    });
    $(document).ready(function(){
        $('#addUserForm').submit(function(e){
            e.preventDefault(); // Ngăn chặn việc gửi biểu mẫu một cách thông thường
            $.ajax({
                type: "POST",
                url: $(this).attr('action'), // URL của biểu mẫu
                data: $(this).serialize(), // Dữ liệu biểu mẫu được gửi đi
                success: function(response){
                    // Xử lý phản hồi từ máy chủ
                    // Ví dụ: Hiển thị thông báo thành công, làm mới bảng người dùng, vv.
                },
                error: function(){
                    // Xử lý lỗi nếu có
                }
            });
        });
    });
    function deleteUser(userId, tag, roleInt) {
        $.ajax({
            type: "POST",
            url: "./deleteUser", // URL để xóa người dùng
            data: { userId: userId, tag: tag, roleInt: roleInt }, // Dữ liệu gửi đi (id của người dùng cần xóa)
            success: function(response){
                alert("Người dùng đã được xóa thành công!");
            },
            error: function(){
                // Xử lý lỗi nếu có
            }
        });
    }
</script>


<script>
    function deleteUser(userID) {
        $.ajax({
            url: "/WebBanThuocBaoVeThucVat/deleteUser",
            type: "POST",
            data: { 'userID': userID },
            success: function(data) {
                alert('Cuộc gọi AJAX thành công!');
                $("#row_user_" + userID).remove();
            },
            error: function(xhr, error) {
                alert('Lỗi xảy ra!');
            }
        });
    }
</script>

<script>
    const passwordInput = document.getElementById('passwordInput');
    const togglePassword = document.getElementById('togglePassword');

    togglePassword.addEventListener('click', function () {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);

        // Toggle icon based on the password visibility
        togglePassword.innerHTML = type === 'password' ? '<i class="fa fa-eye" aria-hidden="true"></i>' : '<i class="fa fa-eye-slash" aria-hidden="true"></i>';
    });
</script>

<%--<script>--%>
<%--    let table=new DataTable('#quanlyUserTable', {--%>
<%--        order: [[3, 'desc']],--%>

<%--    });--%>
<%--</script>--%>


</body>

</html>