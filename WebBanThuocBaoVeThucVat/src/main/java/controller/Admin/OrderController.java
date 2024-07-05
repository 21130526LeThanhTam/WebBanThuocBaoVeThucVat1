//package controller.Admin;
//
//import bean.Orders;
//import dao.OrdersDAO;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "OrderManagement", value = "/orderManagement")
//public class OrderController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Orders> orders = OrdersDAO.; // lấy ra danh sách đơn hàng
//        req.setAttribute("orders", orders); // đặt biến orders vào request scope
//        req.getRequestDispatcher("admin_page/quanlydonhang.jsp").forward(req, resp); // chuyển tiếp đến JSP
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//
//        if (action != null) {
//            switch (action) {
//                case "create":
//                    Order newOrder = new Order();
//                    newOrder.setCustomerName(req.getParameter("customerName"));
//                    newOrder.setProductId(Integer.parseInt(req.getParameter("productId")));
//                    newOrder.setQuantity(Integer.parseInt(req.getParameter("quantity")));
//                    newOrder.setPrice(Double.parseDouble(req.getParameter("price")));
//                    newOrder.setOrderDate(java.sql.Date.valueOf(req.getParameter("orderDate")));
//                    newOrder.setStatus(req.getParameter("status"));
//                    OrderDao.insertOrder(newOrder);
//                    break;
//                case "delete":
//                    int orderIdToDelete = Integer.parseInt(req.getParameter("orderId"));
//                    OrderDao.deleteOrder(orderIdToDelete);
//                    break;
//                case "update":
//                    Order orderToUpdate = new Order();
//                    orderToUpdate.setId(Integer.parseInt(req.getParameter("orderId")));
//                    orderToUpdate.setCustomerName(req.getParameter("customerName"));
//                    orderToUpdate.setProductId(Integer.parseInt(req.getParameter("productId")));
//                    orderToUpdate.setQuantity(Integer.parseInt(req.getParameter("quantity")));
//                    orderToUpdate.setPrice(Double.parseDouble(req.getParameter("price")));
//                    orderToUpdate.setOrderDate(java.sql.Date.valueOf(req.getParameter("orderDate")));
//                    orderToUpdate.setStatus(req.getParameter("status"));
//                    OrderDao.updateOrder(orderToUpdate);
//                    break;
//            }
//        }
//
//        resp.sendRedirect(req.getContextPath() + "/orderManagement"); // chuyển hướng lại servlet để cập nhật danh sách
//    }
//}
