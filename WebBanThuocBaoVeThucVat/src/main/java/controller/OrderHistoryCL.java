package controller;

import Service.IOrdersService;
import bean.OrderDetail;
import bean.Orders;
import bean.User;
import dao.IOrdersDAO;
import dao.OrdersDAO;
import debug.LoggingConfig;

import javax.persistence.criteria.Order;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "OrderHistoryCL", value = "/OrderHistoryCL")
public class OrderHistoryCL extends HttpServlet {
    private IOrdersDAO dao;
    private static final Logger LOGGER = Logger.getLogger(OrderHistoryCL.class.getName());
    @Override
    public void init() throws ServletException {
        super.init();
        this.dao = new OrdersDAO();
        LoggingConfig.setup(); // Thiết lập logging
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if(user == null){
            request.getRequestDispatcher("/login").forward(request, response);
        }else{
            List<Orders> orders = this.dao.getOrdersByUser(user);
            List<Integer> ordersId = new ArrayList<Integer>();
            for (Orders o : orders) {
                ordersId.add(o.getId());
            }
            LOGGER.warning(session.getAttribute("details")+"");
            List<OrderDetail> details = this.dao.getDetailsByOrder(ordersId);
            session.setAttribute("details", details);
            LOGGER.warning(session.getAttribute("details")+"");
            request.getRequestDispatcher("history.jsp").forward(request, response);

        }

    }
}
