package controller;

import Service.IProductService;
import Service.ProductService;
import Service.ProductsService;
import bean.Pagination;
import bean.Product;
import bean.Products;
import dao.IProductDAO;
import dao.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int PRODUCTS_PER_PAGE = 3;
    private static final int VISIBLE_PAGES = 5;
    private int totalPageHome;
    IProductDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dao = new ProductDAO();
        this.totalPageHome = ProductsService.getInstance().getTotalPages();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);

        String page = request.getParameter("currentPage");
        String order = request.getParameter("order");
        String name = request.getParameter("search");
        String action = request.getParameter("action");
        String idCate = request.getParameter("id_category");
        List<Products> list = new ArrayList<>();
        String url = null;
        if(action != null && !action.equals("")) {
            if (action.equalsIgnoreCase("By Name")) {
                list = ProductsService.getInstance().searchByName(name);
            } else if (action.equalsIgnoreCase("By Description")) {
                list = ProductsService.getInstance().searchByDescription(name);
            } else if (action.equalsIgnoreCase("By Price")) {
                list = ProductsService.getInstance().searchByPrice(name);
            }
            session.setAttribute("action", action);
            session.setAttribute("name", name);
            session.setAttribute("words", name);
            url = "cuahang.jsp?page=search";
        } else {
            if (idCate == null) {
                idCate = "";
                list = ProductsService.getInstance().searchByName(idCate);
            } else {
                int cateId = Integer.parseInt(idCate);
                list = ProductsService.getInstance().findByCategory(cateId, "");
            }
            session.setAttribute("idCate", idCate);
            url = "cuahang.jsp";
        }
        if(order != null) {
            int orderValue = Integer.parseInt(order);
            if(orderValue == 2){
                dao.sortByPrice(list, true);
            } else if(orderValue == 3){
                dao.sortByPrice(list, false);
            }
        }
        int totalPageSearch = (int) Math.ceil((double) list.size() / PRODUCTS_PER_PAGE);
        Pagination pagination = new Pagination(PRODUCTS_PER_PAGE, list);
        int currentPage = 1;
        if (page != null) {
            currentPage = Integer.parseInt(page);
        }
        int startPage = Math.max(currentPage - VISIBLE_PAGES / 2, 1);
        int endPage = Math.min(startPage + VISIBLE_PAGES - 1, totalPageSearch);
        List<Products> products = pagination.getProductPerPage(currentPage);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("startPage", startPage);
        session.setAttribute("endPage", endPage);
        session.setAttribute("products_per_page", PRODUCTS_PER_PAGE);
        session.setAttribute("Product", products);
        session.setAttribute("listProducts", list);
        session.setAttribute("totalPage", totalPageSearch);
        request.getRequestDispatcher(url).forward(request, response);
    }
}