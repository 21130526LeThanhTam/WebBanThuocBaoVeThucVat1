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
    <title>Quản lý người dùng</title>
    <link rel="icon" type="image/x-icon" href="assets/img/logo.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="admin_page/css/bootstrap.min.admin.css">
    <link rel="stylesheet" href="admin_page/css/custom.css">

    <!--google fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
    <<<<<<< HEAD
    <style>
        a{
            text-decoration: none !important;
        }
    </style>
    =======
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
        a {
            text-decoration: none;
        }
        /*.col-md-auto .dt-search {*/
        /*    padding-right: 20px;*/
        /*}*/
        .col-md-auto .form-control {
            background-color: whitesmoke;
        }
    </style>
    >>>>>>> 21130526-LeThanhTam
</head>
<body>
<div class="wrapper">
    <div class="body-overlay"></div>
    <!-------sidebar--design------------>
    <div id="sidebar">
        <div class="sidebar-header">
            <img src="./assets/img/logo.png" class="img-fluid"/>
        </div>
        <ul class="list-unstyled component m-0">
            <li>
                <a href="./admin_dashboard" class="dashboard"><i class="material-icons">dashboard</i>Trang chủ </a>
            </li>

            <li class="dropdown active">
                <a href="#homeSubmenu1" data-toggle="collapse" aria-expanded="false"
                   class="dropdown-toggle">
                    <i class="material-icons">aspect_ratio</i>Quản lý người dùng
                </a>
                <ul class="collapse list-unstyled menu" id="homeSubmenu1">
                    <li><a href="./maUser?roleID=0&uid=1">Quản lý khách hàng</a></li>
                    <li><a href="./maUser?roleID=1&uid=1">Quản lý nhân viên</a></li>
                    <li><a href="#">Chủ cửa hàng</a></li>
                </ul>
            </li>

            <li class="dropdown">
                <a href="#homeSubmenu2" data-toggle="collapse" aria-expanded="false"
                   class="dropdown-toggle">
                    <i class="material-icons">apps</i>Quản lý sản phẩm
                </a>
                <ul class="collapse list-unstyled menu" id="homeSubmenu2">
                    <li><a href="./maCategory">Quản lý doanh mục</a></li>
                    <li><a href="./maProduct">Quản lý sản phẩm</a></li>
                    <li><a href="#">Quản lý mã giảm giá</a></li>
                </ul>
            </li>

            <li class="dropdown">
                <a href="#homeSubmenu3" data-toggle="collapse" aria-expanded="false"
                   class="dropdown-toggle">
                    <i class="material-icons">equalizer</i>charts
                </a>
                <ul class="collapse list-unstyled menu" id="homeSubmenu3">
                    <li><a href="#">Pages 1</a></li>
                    <li><a href="#">Pages 2</a></li>
                    <li><a href="#">Pages 3</a></li>
                </ul>
            </li>


            <li class="dropdown">
                <a href="#homeSubmenu4" data-toggle="collapse" aria-expanded="false"
                   class="dropdown-toggle">
                    <i class="material-icons">extension</i>UI Element
                </a>
                <ul class="collapse list-unstyled menu" id="homeSubmenu4">
                    <li><a href="#">Pages 1</a></li>
                    <li><a href="#">Pages 2</a></li>
                    <li><a href="#">Pages 3</a></li>
                </ul>
            </li>

            <li class="dropdown">
                <a href="#homeSubmenu5" data-toggle="collapse" aria-expanded="false"
                   class="dropdown-toggle">
                    <i class="material-icons">border_color</i>forms
                </a>
                <ul class="collapse list-unstyled menu" id="homeSubmenu5">
                    <li><a href="#">Pages 1</a></li>
                    <li><a href="#">Pages 2</a></li>
                    <li><a href="#">Pages 3</a></li>
                </ul>
            </li>

            <li class="dropdown">
                <a href="#homeSubmenu6" data-toggle="collapse" aria-expanded="false"
                   class="dropdown-toggle">
                    <i class="material-icons">grid_on</i>tables
                </a>
                <ul class="collapse list-unstyled menu" id="homeSubmenu6">
                    <li><a href="#">table 1</a></li>
                    <li><a href="#">table 2</a></li>
                    <li><a href="#">table 3</a></li>
                </ul>
            </li>


            <li class="dropdown">
                <a href="#homeSubmenu7" data-toggle="collapse" aria-expanded="false"
                   class="dropdown-toggle">
                    <i class="material-icons">content_copy</i>Pages
                </a>
                <ul class="collapse list-unstyled menu" id="homeSubmenu7">
                    <li><a href="#">Pages 1</a></li>
                    <li><a href="#">Pages 2</a></li>
                    <li><a href="#">Pages 3</a></li>
                </ul>
            </li>


            <li class="">
                <a href="#" class=""><i class="material-icons">date_range</i>copy </a>
            </li>
            <li class="">
                <a href="#" class=""><i class="material-icons">library_books</i>calender </a>
            </li>

        </ul>
    </div>

    <!-------sidebar--design- close----------->


    <!-------page-content start----------->

    <div id="content">
    </div>
</div>

<script src="admin_page/js/adminJS/jquery-3.3.1.slim.min.js"></script>
<script src="admin_page/js/adminJS/popper.min.js"></script>
<script src="admin_page/js/adminJS/jquery-3.3.1.min.js"></script>
<script src="admin_page/js/adminJS/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.datatables.net/v/bs5/jq-3.7.0/dt-2.0.5/datatables.min.css" rel="stylesheet">

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

<script type="text/javascript">
    $(document).ready(function () {
        $(".xp-menubar").on('click', function () {
            $("#sidebar").toggleClass('active');
            $("#content").toggleClass('active');
        });

        $('.xp-menubar,.body-overlay').on('click', function () {
            $("#sidebar,.body-overlay").toggleClass('show-nav');
        });
    });
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
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.datatables.net/v/bs5/jq-3.7.0/dt-2.0.5/datatables.min.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/v/bs5/jq-3.7.0/dt-2.0.5/datatables.min.js"></script>
<script>
    let table=new DataTable('#quanlyUserTable', {
        order: [[3, 'desc']],

    });
</script>


</body>

</html>