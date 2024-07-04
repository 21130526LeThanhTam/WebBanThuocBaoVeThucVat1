package controller.Admin;

import bean.Import;
import dao.admin.ImportDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ImportManagement",value = "/importManagement")
public class ImportController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Import> importOrders = ImportDao.getList(); // lấy ra danh sách import
        req.setAttribute("importOrders", importOrders);
        req.getRequestDispatcher("admin_page/quanlynhaphang.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    Import newOrder = new Import();
                    newOrder.setId_product(Integer.parseInt(req.getParameter("productId")));
                    newOrder.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                    newOrder.setPrice(Double.parseDouble(req.getParameter("price")));
                    ImportDao.insertImport(newOrder);
                    break;
                case "delete":
                    int orderIdToDelete = Integer.parseInt(req.getParameter("orderId"));
                    ImportDao.deleteImport(orderIdToDelete);
                    break;
                case "update":
                    int orderIdToUpdate = Integer.parseInt(req.getParameter("orderId"));
                    String status = req.getParameter("status");
                    ImportDao.updateImport(orderIdToUpdate,status);
                    break;
            }
        }

        resp.sendRedirect("/importManagement");
    }
    }

