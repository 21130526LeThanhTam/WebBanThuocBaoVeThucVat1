package controller;

import bean.User;
import dao.AccountDAO;
import org.springframework.util.DigestUtils;

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


        String newPword = DigestUtils.md5DigestAsHex(pass.getBytes());
        User user = new User();

        AccountDAO acc = new AccountDAO();
        HttpSession session = req.getSession();

        if((email == null || email.isEmpty()) || (pass == null || pass.isEmpty())){
            String error = "Không để trống thông tin đăng nhập";
            session.setAttribute("errorlogin", error);
            resp.sendRedirect("login");
        }else if((email != null || !email.isEmpty()) && (pass != null || !pass.isEmpty())) {
            user = acc.login(email, newPword,"", count, "","Login");
            if (user == null) {
                String error = "Tài khoản hoặc mật khẩu không đúng,vui lòng kiểm tra lại.";
                session.setAttribute("errorlogin", error);
                count++;
                resp.sendRedirect("login");
            } else {
                session.removeAttribute("errorlogin");
                // phân quyền để chuyển trang

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
