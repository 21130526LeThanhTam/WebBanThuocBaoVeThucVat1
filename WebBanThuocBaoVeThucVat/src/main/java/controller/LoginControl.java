package controller;

import bean.User;
import dao.AccountDAO;
import dao.UserDAO;
import utils.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("userC")) {
                    request.setAttribute("email", o.getValue());
                }
                if (o.getName().equals("passC")) {
                    request.setAttribute("password", o.getValue());
                }
            }
        }
        request.getRequestDispatcher("login-register/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=utf-8");
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String remember = req.getParameter("rememberMe");

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
                    int loginFail = AccountDAO.getInstance().getLoginFail(email);
                    AccountDAO.getInstance().updateLoginFail(email, loginFail + 1);
                    if((loginFail+1)==5) {
                        UserDAO.getInstance().LockUser(email);
                        out.println("{\"error\":\"chúng tôi đã khóa tài khoản "+ email + ".\"}");
                        return;
                    } else {
                        out.println("{\"error\":\"Bạn đã còn "+ (5 - (loginFail+1)) +" lần đăng nhập.\"}");
                    }
                } else {
                    if(checkEmail == null) out.println("{\"error\":\"Tài khoản không đúng, vui lòng kiểm tra lại!\"}");
                    else if(checkEmail.getActive()==0) out.println("{\"error\":\"Tài khoản chưa được kích hoạt để đăng nhập!\"}");
                    else if(checkEmail.getActive()==2) out.println("{\"error\":\"chúng tôi đã khóa tài khoản "+email+"\"}");
                }
            } else {
                AccountDAO.getInstance().updateLoginFail(email, 0);
                HttpSession session = req.getSession();
                Cookie u = new Cookie("userC", email);
                Cookie p = new Cookie("passC", pass);
                u.setMaxAge(30 * 24 * 60 * 60);
                if (remember != null) {
                    if(Boolean.parseBoolean(remember)) {
                        p.setMaxAge(30 * 24 * 60 * 60);
                    } else {
                        p.setMaxAge(0);
                    }
                } else {
                    p.setMaxAge(0);
                }
                resp.addCookie(u);
                resp.addCookie(p);
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