package controller;

import bean.User;
import dao.AccountDAO;
import dao.UserDAO;
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
        String hashPass= DigestUtils.md5DigestAsHex(pass.getBytes());
        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(hashPass);
        // địa chỉ ip
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        if (email == null || email.isEmpty() || pass == null || pass.isEmpty()){
            out.println("{\"error\":\"Tài khoản hoặc mật khẩu không được để trống.\"}");
        } else {
            User user = AccountDAO.getInstance().loginAccount(userLogin,ipAddress,1,ipAddress);
            User exist = AccountDAO.getInstance().checkAccountExist(email);// kiểm tra email có ytoonf tại hay ko.
            if (user == null && exist!=null) {
                count++;
                if(count==3) {
                    out.println("{\"error\":\"Bạn đã còn 2 lần đăng nhập.\"}");
                }
                else
                    if (count==5){
                        UserDAO.getInstance().LockUser(exist);
                        out.println("{\"error\":\"chúng tôi đã khóa tài khoản "+exist.getEmail()+"\"}");
                    }

                else {
                    out.println("{\"error\":\"Tài khoản hoặc mật khẩu không đúng, vui lòng kiểm tra lại.\"}");
                    System.out.println(count);
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
            }
            out.close();
        }
    }
}