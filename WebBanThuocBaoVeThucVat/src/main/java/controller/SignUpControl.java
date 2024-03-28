package controller;

import Service.SendingEmail;

import bean.User;
import dao.AccountDAO;
import org.apache.commons.codec.cli.Digest;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = {"/signup"})
public class SignUpControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login-register/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String surname = req.getParameter("surname");
        String lastname = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        String pass = req.getParameter("pass");
        String re_pass = req.getParameter("rePass");
        //mã hóa mật khẩu sang md5
        String hashpass = DigestUtils.md5DigestAsHex(pass.getBytes());

        HttpSession session = req.getSession();
        //Tạo mã xác nhận ngẫu nhiên bằng cách sử dụng md5 và số ngẫu nhiên để tạo ra đường link đăng kí cho mỗi người
        String myHash ;
        Random random = new Random();
        random.nextInt(999999);
        myHash = DigestUtils.md5DigestAsHex((""+random).getBytes());
    // khởi tạo trước một đối tượng user
        User user = new User();
    // kiểm tra user có tồn tại trước đó hay không
    AccountDAO acc = new AccountDAO();
    user = acc.checkAccountExist(email);
    String error;
    //nếu user khác null thì đăng kí
        // không thì sẽ chuyền về là tài khoản đã đăng kí
        if(user == null){
            //Tên tài khoản và dài hơn 3 kí tự và kí tự đầu tiên phải là chữ cái
            if(username.length() <= 3 || !(Character.isLetter(username.charAt(0)))){
                error = "Tên tài khoản phải trên 3 kí tự và * Kí tự đầu tiên phải là chữ cái";

                session.setAttribute("errorNumber", error);
                resp.sendRedirect("signup");
            }
            // số điện thoại phải là 10 chữ số
            else if(phone.length() != 10) {
                error = "Số điện thoại phải là 10 chữ số";

                session.setAttribute("errorNumber", error);
                resp.sendRedirect("signup");
            }
            else if (!pass.equals(re_pass)) {
                error = "Mật khẩu không trùng khớp";

                session.setAttribute("errorNumber", error);
                resp.sendRedirect("signup");
            }
            else{

                String str = acc.signUp(email, hashpass, username, surname, lastname, phone, myHash);
                    if(str.equals("success")){
                        SendingEmail se = new SendingEmail(email, myHash);
                        se.sendMail();
                        error = "Kích hoạt email để đăng nhập";
                        session.setAttribute("errorRegis", error);
                        resp.sendRedirect("login");
                    }

            }
        }
        else{
            error = "Email này đã được đăng kí, vui lòng sử dụng email khác";
            session.setAttribute("errorNumber", error);
            resp.sendRedirect("signup");

        }
    }
}

