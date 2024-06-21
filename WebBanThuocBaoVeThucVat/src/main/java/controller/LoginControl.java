package controller;

import bean.User;
import dao.AccountDAO;
import dao.UserDAO;
import utils.PasswordUtils;

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

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(pass);
        // địa chỉ ip
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }

        if (email == null || email.isEmpty() || pass == null || pass.isEmpty()){
            out.println("{\"error\":\"Tài khoản hoặc mật khẩu không được để trống.\"}");
        } else {
            User user = AccountDAO.getInstance().loginAccount(userLogin, ipAddress,1, ipAddress);
            User checkEmail = AccountDAO.getInstance().checkAccountExist(userLogin.getEmail());
            if (user == null) {
                if(checkEmail != null && checkEmail.getActive()!=0 && checkEmail.getActive()!=2) {
                    count++;
                    if(count%5==0) {
                        UserDAO.getInstance().LockUser(email);
                        out.println("{\"error\":\"chúng tôi đã khóa tài khoản "+email + ".\"}");
                        return;
                    } else {
                        out.println("{\"error\":\"Bạn đã còn "+ (5 - (count % 5)) +" lần đăng nhập.\"}");
                    }
                } else {
                    if(checkEmail == null) out.println("{\"error\":\"Tài khoản không đúng, vui lòng kiểm tra lại!\"}");
                    else if(checkEmail.getActive()==0) out.println("{\"error\":\"Tài khoản chưa được kích hoạt để đăng nhập!\"}");
                    else if(checkEmail.getActive()==2) out.println("{\"error\":\"chúng tôi đã khóa tài khoản "+email+"\"}");
                }
            } else {
                count = 0;
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