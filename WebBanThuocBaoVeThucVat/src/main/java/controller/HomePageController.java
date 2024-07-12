package controller;

import Service.IProductService;
import Service.ProductService;
import bean.Category;
import bean.Product;
import bean.Products;
import bean.ShoppingCart;
import dao.CategoryDAO;
import dao.IProductDAO;
import dao.ProductDAO;

import javax.mail.Session;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePageController", value = "/HomePageController")
public class HomePageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IProductService productService = new ProductService();
        IProductDAO proDAO = new ProductDAO();
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
            session.setAttribute("cart", shoppingCart);
        }
        List<Category> cateList = CategoryDAO.getList();
        List<Products> products = productService.findAll1();
        List<Products> productsNew1 = proDAO.findNewPro1();
        List<Products> productsNew2 = proDAO.findNewPro2();
        List<Products> findDiscountPro1 = proDAO.findDiscountPro1();
        List<Products> findDiscountPro2 = proDAO.findDiscountPro2();
        request.setAttribute("category", cateList);
        request.setAttribute("products", products);
//        request.setAttribute("products2", products2);
        request.setAttribute("productsNew1", productsNew1);
        request.setAttribute("productsNew2", productsNew2);
        request.setAttribute("findDiscountPro1", findDiscountPro1);
        request.setAttribute("findDiscountPro2", findDiscountPro2);
        String url = "index.jsp";

        //request deparcher nó có thể là forward hoặc inclue nó
        request.getRequestDispatcher(url).forward(request, response); // dùng getRequestDipascher để chuyển hướng sang jsp

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}