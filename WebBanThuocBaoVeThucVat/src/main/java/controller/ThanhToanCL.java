package controller;

import Service.IOrdersService;
import Service.OrderDetailService;
import Service.OrdersService;
import bean.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet(name = "ThanhToanCL", value = "/ThanhToanCL")
public class ThanhToanCL extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IOrdersService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = new OrdersService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        User user = (User) session.getAttribute("user");
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
//        System.out.println(shoppingCart.getSize());
//        System.out.println(shoppingCart.getTotalPrice());
        if (user != null) {
            request.setAttribute("firstname", user.getSurName());
            request.setAttribute("username", user.getUsername());
            request.setAttribute("phone", user.getPhone());

        } else {
            request.setAttribute("firstname", "");
            request.setAttribute("username", "");
            request.setAttribute("phone", "");
        }
        if (action != null && action.equals("checkout")) {
            session.setAttribute("list", shoppingCart.getCartItemList());
            session.setAttribute("cart", shoppingCart);
            request.getRequestDispatcher("thanh-toan.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        ShoppingCart c = (ShoppingCart) session.getAttribute("cart");
        String action = request.getParameter("action");
        if(action!=null && action.equals("order")) {
            String firstName = request.getParameter("firstname");
            String username = request.getParameter("username");
            String city = request.getParameter("city");
            String district = request.getParameter("district");
            String homeNumber = request.getParameter("homeNumber");
            String phone = request.getParameter("phone");
            User user = (User) session.getAttribute("user");
            String address = homeNumber + ", " + district + ", " + city;
            List<CartItem> products = c.getCartItemList();
            Orders order = new Orders(user.getId(), (float) c.getTotalPrice(),
                    0, address, phone);
            order.setLp(products);
            this.orderService.insertOrderDetail(order);
            double total = c.getTotalPrice();
            session.setAttribute("total", total);
            session.removeAttribute("cart");
            response.sendRedirect("HomePageController");

        }
    }

}
