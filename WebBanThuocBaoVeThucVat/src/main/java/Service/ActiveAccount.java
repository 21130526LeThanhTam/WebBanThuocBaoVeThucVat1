package Service;

import dao.AccountDAO;
import db.DBContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// đây là trang khi mà người dùng bấm link xác thực account thì sẽ chuyển hướng ra đâu đấy

@WebServlet(urlPatterns = "/ActiveAccount")
public class ActiveAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("key1"); // lấy ra giá trị của key1 và key 2 là số tài khoản và hash của tài khoản đó
        String hash = req.getParameter("key2");
        AccountDAO dao = new AccountDAO();
        //phương thức activeAccount được chuyển qua lớp AccountDao
        String str = dao.activeAccount(email, hash);
        if(str.equals("success")){
            //nếu người dùng đã bấm vào thì chuyển hướng sang trang login
            resp.sendRedirect("verify.jsp");
        }else{
            //nếu không thành công thì về lại trang đăng kí
            resp.sendRedirect("signup?action=register");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
