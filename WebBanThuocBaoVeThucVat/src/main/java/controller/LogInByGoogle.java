//package controller;
//
//import bean.User;
//import controller.GoogleService.Service;
//import dao.UserDAO;
//
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/user/loginbygoogle")
//public class LogInByGoogle extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String code = request.getParameter("code");
//        String accessToken = Service.getToken(code);
//        User user = null;
//        boolean userExist = false;
//
//        try {
//            user = Service.getUserInfo(accessToken);
//            userExist = UserDAO.getInstance().isUserExists(user.getEmail());
//        } catch (SQLException var9) {
//            throw new RuntimeException(var9);
//        }
//
//        try {
//            if (user != null && !userExist) {
//                UserDAO.getInstance().signUp(user.getUsername(), user.getEmail(), (String) null, "verified", 1);
//            }
//
//            HttpSession session = request.getSession();
//            session.setAttribute("auth", user);
//            response.sendRedirect("./home");
//        } catch (SQLException var8) {
//            throw new RuntimeException(var8);
//        }
//    }
//}
