package controller;

import Service.IOrdersService;
import Service.OrdersService;
import bean.CartItem;
import bean.Orders;
import bean.ShoppingCart;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "payment", value = "/payment")
public class Payment extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IOrdersService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = new OrdersService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
        String txt_inv_addr1 = (String) session.getAttribute("txt_inv_addr1");
        String txt_bill_city = (String) session.getAttribute("txt_bill_city");
        String txt_bill_country = (String) session.getAttribute("txt_bill_country");
        String txt_inv_customer = (String) session.getAttribute("txt_inv_customer");
        String txt_billing_mobile = (String) session.getAttribute("txt_billing_mobile");
        String address = txt_inv_addr1 + ", " + txt_bill_city + ", " + txt_bill_country;
        List<CartItem> products = shoppingCart.getCartItemList();
        Orders order = new Orders(user.getId(), (float) shoppingCart.getTotalPrice(),
                0, address, txt_billing_mobile);
        order.setLp(products);
        this.orderService.insertOrderDetail(order);
        double total = shoppingCart.getTotalPrice();
        session.setAttribute("total", total);
        session.removeAttribute("cart");
        resp.sendRedirect("OrderHistoryCL");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

    }
}
