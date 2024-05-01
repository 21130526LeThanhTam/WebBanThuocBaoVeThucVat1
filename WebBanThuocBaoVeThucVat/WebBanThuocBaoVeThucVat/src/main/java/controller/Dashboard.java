package controller;

import Service.ProductsService;
import Service.UserService;
import bean.Products;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Dashboard", value = "/admin_dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User>dsUser= UserService.getInstance().getDSUsers();
        List<Products>dsProduct = ProductsService.getInstance().numOfPro();
//        System.out.println(dsProduct);
        int numOfGuest= dsUser.size();
        int numOfPro=dsProduct.size();
        req.setAttribute("numUser",numOfGuest);
        req.setAttribute("numPro",numOfPro);
        req.getRequestDispatcher("admin_page/admin.jsp").forward(req,resp);
    }
}
