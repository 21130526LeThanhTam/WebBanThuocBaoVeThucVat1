# 🚀 WebBanThuocBaoVeThucVat - E-Commerce Platform

[![Java](https://img.shields.io/badge/Java-11-red?style=flat-square)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8.1-blue?style=flat-square)](https://maven.apache.org/)
[![Tomcat](https://img.shields.io/badge/Tomcat-9.0-orange?style=flat-square)](https://tomcat.apache.org/)

Website bán thuốc bảo vệ thực vật chuyên nghiệp — hỗ trợ mua hàng trực tuyến, thanh toán VNPay và quản trị toàn diện dành cho đồ án môn học.

---

## 📝 Tổng Quan Dự Án

* **Mục tiêu:** Phát triển hệ thống bán hàng trực tuyến tối ưu cho sản phẩm nông nghiệp, quản lý kho hàng và tích hợp các phương thức thanh toán hiện đại.
* **Công nghệ cốt lõi:**
  * **Core:** Java 11, JSP, Servlet, JDBI (tối ưu truy vấn database).
  * **Database:** MySQL.
  * **Frontend:** HTML, CSS, JavaScript, JQuery.
  * **Tích hợp:** VNPay (Thanh toán), Cloudinary (Quản lý ảnh), Google/Facebook OAuth2 (Đăng nhập), SendingEmail (Gửi mã xác thực).
  * **Bảo mật:** Mã hóa mật khẩu, bảo mật Session/Cookie.

---

## ✨ Tính Năng Chính

### 👤 Khách hàng (End-User)
* **Xác thực:** Đăng ký, đăng nhập và quên mật khẩu (qua Email). Hỗ trợ Login qua Google/Facebook.
* **Mua sắm:** Tìm kiếm sản phẩm, lọc theo danh mục, thêm giỏ hàng và danh sách yêu thích (Wishlist).
* **Thanh toán:** Tích hợp cổng thanh toán VNPay Sandbox.
* **Cá nhân hóa:** Theo dõi trạng thái đơn hàng, lịch sử mua hàng và quản lý thông tin cá nhân.

### 👨‍💼 Quản trị viên (Admin Dashboard)
* **Quản lý nội dung:** CRUD Sản phẩm, Danh mục, Thương hiệu.
* **Quản lý vận hành:** Phê duyệt đơn hàng, quản lý người dùng.
* **Báo cáo:** Thống kê doanh thu và sản phẩm bán chạy.

---
## 📂 Cấu Trúc Thư Mục
```text
WebBanThuocBaoVeThucVat/
├── src/main/java/       # Xử lý logic (Controllers, Services, Config)
├── src/main/resources/  # File cấu hình (DB.properties)
├── src/main/webapp/     # Giao diện (JSP, HTML, CSS, JS)
├── pom.xml              # Cấu hình Maven & Dependencies
└── README.md            # Tài liệu dự án
```
