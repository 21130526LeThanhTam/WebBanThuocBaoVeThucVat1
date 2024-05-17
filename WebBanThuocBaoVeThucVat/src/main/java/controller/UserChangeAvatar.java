package controller;

import bean.Product;
import bean.User;
import db.JDBIConnector;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig
public class UserChangeAvatar extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tạo thư mục để lưu trữ ảnh nếu chưa tồn tại
        String uploadPath = getServletContext().getRealPath("") + File.separator + "upload";
      //  String uploadPath= "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Lấy phần file từ request
        Part filePart = request.getPart("profilePic");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String relativePath ="upload" + File.separator +fileName;
        // Lưu file
        HttpSession session = request.getSession();
        session.setAttribute("profilePic", relativePath);
        filePart.write(uploadPath + File.separator + fileName);
        //tạo ra phương thức lưu đường dẫn vào cơ sở dữ liệu
        User user = (User)session.getAttribute("user");
        int id_user = user.getId();
        saveFilePathToDatabase(relativePath, id_user);
        response.sendRedirect("user-profile.jsp");
    }
    private void saveFilePathToDatabase(String filePath,int idUser) {
        Jdbi jdbi = JDBIConnector.getJdbi();
        // Thay đổi kết nối cơ sở dữ liệu và câu lệnh SQL phù hợp với cơ sở dữ liệu của bạn
        String sql = "UPDATE users SET picture = ? WHERE id = ?";
        jdbi.withHandle(handle -> handle.createUpdate(sql)
                .bind(0,filePath)
                .bind(1,idUser)
                .execute());
    }
}
