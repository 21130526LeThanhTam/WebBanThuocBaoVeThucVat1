<%@ page import="java.util.List" %>
<%@ page import="bean.Products" %>
<%@ page import="Service.ProductsService" %>
<%@ page import="java.util.ArrayList" %>
<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Quan ly don hang</title>

</head>
<%List<Products>getTenPro = (List<Products>) request.getAttribute("getTenPro");
if(getTenPro == null) getTenPro=new ArrayList<>();%>
<%int pageValue = (int) request.getAttribute("page");%>
<%Integer tagAttribute= (Integer) request.getAttribute("tag");
    int tag = (tagAttribute != null) ? tagAttribute.intValue() : 1;%>

<body>
        <div class="main-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6 p-0 flex justify-content-lg-start justify-content-center">
                                    <h2 class="ml-lg-2">Quản lý sản phẩm</h2>
                                </div>
                                <div class="col-sm-6 p-0 flex justify-content-lg-end justify-content-center">
                                    <a href="./insertPro?finalPage=<%=pageValue%>" class="btn btn-success">
                                        <i class="material-icons">&#xE147;</i>
                                        <span>Thêm sản phẩm mới</span>
                                    </a>
<%--                                    <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal">--%>
<%--                                        <i class="material-icons">&#xE15C;</i>--%>
<%--                                        <span>Delete</span>--%>
<%--                                    </a>--%>
                                </div>
                            </div>
                        </div>

                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Tên</th>
                                <th>Loại sản phẩm</th>
                                <th>Ảnh</th>
                                <th>Giá</th>
                                <th>Chỉnh</th>
                            </tr>
                            </thead>

                            <tbody>
                            <%for(Products a: getTenPro){%>
                            <tr>
                                <th><svg xmlns="http://www.w3.org/2000/svg" height="16" width="14" viewBox="0 0 448 512"><path d="M210.6 5.9L62 169.4c-3.9 4.2-6 9.8-6 15.5C56 197.7 66.3 208 79.1 208H104L30.6 281.4c-4.2 4.2-6.6 10-6.6 16C24 309.9 34.1 320 46.6 320H80L5.4 409.5C1.9 413.7 0 419 0 424.5c0 13 10.5 23.5 23.5 23.5H192v32c0 17.7 14.3 32 32 32s32-14.3 32-32V448H424.5c13 0 23.5-10.5 23.5-23.5c0-5.5-1.9-10.8-5.4-15L368 320h33.4c12.5 0 22.6-10.1 22.6-22.6c0-6-2.4-11.8-6.6-16L344 208h24.9c12.7 0 23.1-10.3 23.1-23.1c0-5.7-2.1-11.3-6-15.5L237.4 5.9C234 2.1 229.1 0 224 0s-10 2.1-13.4 5.9z"/></svg></th>
                                <th><%=a.getProduct_name()%></th>
                                <th><%=a.cateOfProduct()%></th>
                                <th><img src="<%=a.getImage()%>" alt="" style="width: 110px;height: 110px"></th>
                                <th><%=a.formatPrice()%></th>
                                <th>
                                    <a href="./editPro?proID=<%=a.getId()%>" class="edit">
                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                    </a>
                                    <a href="#deleteEmployeeModal<%=a.getId()%>" class="delete" data-toggle="modal">
                                        <i class="material-icons" data-toggle="tooltip" title="Delete" >&#xE872;</i>
                                    </a>
                                </th>
                            </tr>
                            <div class="modal fade" tabindex="-1" id="deleteEmployeeModal<%=a.getId()%>" role="dialog">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Xóa sản phẩm</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Bạn chắc muốn xóa sản phẩm <%=a.getProduct_name()%>?</p>
                                            <p class="text-warning"><small>Bấm "hủy" để dừng lại</small></p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                            <button type="button" class="btn btn-success" onclick="deleteUser(<%=a.getId()%>,<%=tag%>)">Xóa</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>




<%--                            <tr>--%>
<%--                                <th><span class="custom-checkbox">--%>
<%--							 <input type="checkbox" id="checkbox2" name="option[]" value="1">--%>
<%--							 <label for="checkbox2"></label></span></th>--%>
<%--                                <th>Dominique Perrier</th>--%>
<%--                                <th>dominiquePerrier@gmail.com</th>--%>
<%--                                <th>90r ser57, Berlin poland Bermany.</th>--%>
<%--                                <th>(78-5235-2-9)</th>--%>
<%--                                <th>--%>
<%--                                    <a href="#editEmployeeModal" class="edit" data-toggle="modal">--%>
<%--                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>--%>
<%--                                    </a>--%>
<%--                                    <a href="#deleteEmployeeModal" class="delete" data-toggle="modal">--%>
<%--                                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>--%>
<%--                                    </a>--%>
<%--                                </th>--%>
<%--                            </tr>--%>


<%--                            <tr>--%>
<%--                                <th><span class="custom-checkbox">--%>
<%--							 <input type="checkbox" id="checkbox3" name="option[]" value="1">--%>
<%--							 <label for="checkbox3"></label></span></th>--%>
<%--                                <th>Marai Andres</th>--%>
<%--                                <th>MarariAndres@gmail.com</th>--%>
<%--                                <th>90r ser57, Berlin poland Bermany.</th>--%>
<%--                                <th>(78-239-669)</th>--%>
<%--                                <th>--%>
<%--                                    <a href="#edit" class="edit" data-toggle="modal">--%>
<%--                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>--%>
<%--                                    </a>--%>
<%--                                    <a href="#deleteEmployeeModal" class="delete" data-toggle="modal">--%>
<%--                                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>--%>
<%--                                    </a>--%>
<%--                                </th>--%>
<%--                            </tr>--%>

<%--                            <tr>--%>
<%--                                <th><span class="custom-checkbox">--%>
<%--							 <input type="checkbox" id="checkbox4" name="option[]" value="1">--%>
<%--							 <label for="checkbox4"></label></span></th>--%>
<%--                                <th>Vishweb Design</th>--%>
<%--                                <th>vishwebdesign@gmail.com</th>--%>
<%--                                <th> B-2 ser57 Nodia East Delhi,India.</th>--%>
<%--                                <th>(78-239-669)</th>--%>
<%--                                <th>--%>
<%--                                    <a href="#editEmployeeModal" class="edit" data-toggle="modal">--%>
<%--                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>--%>
<%--                                    </a>--%>
<%--                                    <a href="#deleteEmployeeModal" class="delete" data-toggle="modal">--%>
<%--                                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>--%>
<%--                                    </a>--%>
<%--                                </th>--%>
<%--                            </tr>--%>

<%--                            <tr>--%>
<%--                                <th><span class="custom-checkbox">--%>
<%--							 <input type="checkbox" id="checkbox5" name="option[]" value="1">--%>
<%--							 <label for="checkbox5"></label></span></th>--%>
<%--                                <th>Vishwajeet Kumar</th>--%>
<%--                                <th>vishkumar234@gmail.com</th>--%>
<%--                                <th> B-2 ser57 Nodia East Delhi,India.</th>--%>
<%--                                <th>(78-555-229)</th>--%>
<%--                                <th>--%>
<%--                                    <a href="#editEmployeeModal" class="edit" data-toggle="modal">--%>
<%--                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>--%>
<%--                                    </a>--%>
<%--                                    <a href="#deleteEmployeeModal" class="delete" data-toggle="modal">--%>
<%--                                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>--%>
<%--                                    </a>--%>
<%--                                </th>--%>
<%--                            </tr>--%>

                            </tbody>
                        </table>

                        <div class="clearfix">
                            <div class="hint-text">showing <b>5</b> out of <b>25</b></div>
                            <ul class="pagination">
                                <li class="page-item disabled"><a href="#">Previous</a></li>
                                <%  for (int i = 1; i <= pageValue; i++) {
                                      String classValue = (tag == i) ? "page-item active" : "page-item";
                                %>
                                <li class="<%=classValue%>">
                                    <a href="./maProduct?proID=<%=i%>" class="page-link"><%=i%>
                                    </a>
                                </li>
                                <%}%>
                                <li class="page-item "><a href="#" class="page-link">Next</a></li>
                            </ul>
                        </div>
                    </div>
                </div>


                <!----add-modal start--------->


                <!----edit-modal end--------->


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

                <!----edit-modal end--------->
                <!----delete-modal start--------->
                <!----edit-modal end--------->
            </div>
        </div>
        <!------main-content-end----------->






<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="admin_page/js/adminJS/jquery-3.3.1.slim.min.js"></script>
<script src="admin_page/js/adminJS/popper.min.js"></script>
<script src="admin_page/js/adminJS/jquery-3.3.1.min.js"></script>
<script src="admin_page/js/adminJS/bootstrap.min.js"></script>


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
    function deleteUser(proID, page) {
        // Tạo một biểu mẫu và thêm input ẩn để chứa thông tin người dùng
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "./deletePro"); // Sửa chính tả ở đây

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

        // Gửi yêu cầu POST
        form.submit();
    }
</script>


</body>

</html>


