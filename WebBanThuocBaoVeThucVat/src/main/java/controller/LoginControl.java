package controller;

import bean.User;
import dao.AccountDAO;
import org.springframework.util.DigestUtils;
import utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {
    private static int count;

    @Override
    public void init() throws ServletException {
        super.init();
        count = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login-register/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=utf-8");
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        if (email == null || email.isEmpty() || pass == null || pass.isEmpty()){
            out.println("{\"error\":\"Tài khoản hoặc mật khẩu không được để trống.\"}");
////            resp.sendRedirect("login");
        } else {
            User user = AccountDAO.login(email, DigestUtils.md5DigestAsHex(pass.getBytes()));
            if (user == null) {
                count++;
                if(count==5) {
/*                    User user1 = AccountDAO.lockUser(email);*/
                    out.println("{\"error\":\"Tài khoản cua ban da bi khoa\"}");
                }
                else {
                    out.println("{\"error\":\"Tài khoản hoặc mật khẩu không đúng, vui lòng kiểm tra lại.\"}");
////                resp.sendRedirect("login");
                }
            } else {
                HttpSession session = req.getSession();
                if (user.getRole() == 0) {
                    session.setAttribute("user", user);
                    out.println("{\"role\":0}");
                } else if (user.getRole() == 1) {
                    session.setAttribute("admin", user);
                    out.println("{\"role\":1}");
                }

////                session.setAttribute("uslogin", user);
////                session.removeAttribute("errorlogin");
//                // phân quyền để chuyển trang
//                if (user.getRole() == 0) {
//                    session.setAttribute("user", user);
////                    session.removeAttribute("passF");
//                    resp.sendRedirect("HomePageController");
//                }
//                if (user.getRole() == 1) {
//                    session.setAttribute("admin", user);
//                    resp.sendRedirect("admin_dashboard");
//                }
            }
            out.close();
        }
    }
}
