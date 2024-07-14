package controller.Admin;

import bean.OrderDetailTable;
import bean.OrderTable;
import com.google.gson.Gson;
import dao.IOrdersDAO;
import dao.OrdersDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderManagement", value = "/orderManagement")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IOrdersDAO orderDao;

    @Override
    public void init() throws ServletException {
        super.init();
        orderDao = new OrdersDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            listOrders(req, resp);
        } else if ("view".equals(action)) {
            viewOrderDetails(req, resp);
        }
    }

    private void listOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderTable> listOrder = orderDao.getOrderforAdmin();
        for (OrderTable order : listOrder) {
            List<OrderDetailTable> listOrderDetail = orderDao.getOrderDetailsByOrderId(order.getId());
            order.setListDetails(listOrderDetail);
        }
        req.setAttribute("listOrder", listOrder);
        req.getRequestDispatcher("admin_page/quanlyDonHang.jsp").forward(req, resp);
    }

    private void viewOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        List<OrderDetailTable> listOrderDetail = orderDao.getOrderDetailsByOrderId(orderId);
        // Trả về JSON của chi tiết đơn hàng
        Gson gson = new Gson();
        String json = gson.toJson(listOrderDetail);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
