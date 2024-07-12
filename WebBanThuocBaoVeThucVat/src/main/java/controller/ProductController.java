package controller;

import Service.IProductService;
import Service.ProductsService;
import bean.Products;
import dao.IProductDAO;
import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int VISIBLE_PAGES = 5;
    private ProductDAO dao = new ProductDAO();
    private int totalPageHome;

    @Override
    public void init() throws ServletException {
        super.init();
        totalPageHome = this.dao.getTotalPages();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String idCate = request.getParameter("id_category");
        String page = request.getParameter("page");
        String order = request.getParameter("order");
        List<Products> list;
        if (idCate == null) {
            idCate = "";
            list = ProductsService.getInstance().findAll1(idCate);
        } else {
            int cateId = Integer.parseInt(idCate);
            list = ProductsService.getInstance().findByCategory(cateId, "");
            session.setAttribute("idCate", idCate);
            return;
        }
        if(order != null) {
            int orderValue = Integer.parseInt(order);
            if(orderValue == 2) {
                list = dao.findByPriceMin("");
            } else if(orderValue == 3) {
                list = dao.findByPriceMax("");
            }
        }
        if (page == null || page.equals("home")) {
            int currentPage = 1;
            String numberPage = request.getParameter("currentPage");
            if (numberPage != null) {
                currentPage = Integer.parseInt(numberPage);
            }
            int startPage = Math.max(currentPage - VISIBLE_PAGES/2, 1);
            int endPage = Math.min(startPage + VISIBLE_PAGES - 1, totalPageHome);
            List<Products> products = this.dao.getProductsPerPage(currentPage);
            session.setAttribute("words", "");
            session.setAttribute("currentPageHome", currentPage);
            session.setAttribute("startPageHome", startPage);
            session.setAttribute("endPageHome", endPage);
            session.setAttribute("products_per_page", this.dao.getProductsPerPageConstant());
            session.setAttribute("ProductHome", products);
            session.setAttribute("totalPageHome", totalPageHome);
            request.getRequestDispatcher("cuahang.jsp?page=home").forward(request, response);
        }
//        request.setAttribute("products", list);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String search = request.getParameter("search");

        int id = 0;
        Object idCateObj = session.getAttribute("idCate");
        if (idCateObj != null) {
            id = Integer.parseInt(idCateObj.toString());
        }

        if (search == null) {
            search = "";
        }

        List<Products> list = new ArrayList<>();
        if (id == 0) {
            list = ProductsService.getInstance().findAll1(search);
        } else {
            list = ProductsService.getInstance().findByCategory(id, search);
        }

        request.setAttribute("products", list);
        request.getRequestDispatcher("cuahang.jsp").forward(request, response);
    }
}