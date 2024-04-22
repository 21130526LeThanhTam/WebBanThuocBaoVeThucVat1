package controller;

import Service.IProductService;
import Service.OrdersService;
import Service.ProductService;
import bean.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ShoppingCartCL", value = "/ShoppingCartCL")
public class ShoppingCartCL extends HttpServlet {
    IProductService productService = new ProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ShoppingCart shoppingCart;
        HttpSession session = request.getSession(true);
        shoppingCart = (ShoppingCart) session.getAttribute("cart");
        if(shoppingCart==null){
            shoppingCart = new ShoppingCart();
        }
        session.setAttribute("cart", shoppingCart);
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCart shoppingCart;
        HttpSession session = request.getSession();
        shoppingCart = (ShoppingCart) session.getAttribute("cart");
        User auth = (User) session.getAttribute("user");
        String action = request.getParameter("action");
        switch (action) {
            case "get":
                request.getRequestDispatcher("gio-hang.jsp").forward(request, response);
                break;
            case "delete":
                Delete(request, response);
                break;
            case "put":
//                for (CartItem item : shoppingCart.getCartItemList()) {
//                    Put(item.getProduct().getId(), request, response);
//                }
                Put(request, response);
                break;
            case "post":
                if(auth!=null){
                    int id = Integer.parseInt(request.getParameter("id"));
                    int type = Integer.parseInt(request.getParameter("type"));
                    Products product = productService.findById(id);
                    if(type==0){
                        CartItem cartItem = new CartItem(product, 1);
                        shoppingCart.add(cartItem);
                        session.setAttribute("cart", shoppingCart);
                    }
                    if(type==1){
                        int quantity = Integer.parseInt(request.getParameter("quantity"));
                        CartItem cartItem = new CartItem(product, quantity);
                        shoppingCart.add(cartItem);
                        session.setAttribute("cart", shoppingCart);
                    }

                    if(type==1){
                        response.sendRedirect("gio-hang.jsp");
                    }else{
                        // Kiểm tra nếu đang ở trang ProductController thì chuyển hướng đến trang ProductController,
                        // nếu đang ở trang HomePageController thì chuyển hướng đến trang HomePageController.
                        String referer = request.getHeader("referer");
                        if (referer != null && referer.contains("HomePageController")) {
                            response.sendRedirect("HomePageController");
                        } else {
                            response.sendRedirect("ProductController");
                        }
                    }
                }else{
                    response.sendRedirect("login");
                }
                break;
            default:
                // Xử lý trường hợp khác nếu cần
        }
    }



    protected void Put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart;
        HttpSession session = req.getSession(true);
        shoppingCart = (ShoppingCart) session.getAttribute("cart");
        int id = Integer.parseInt(req.getParameter("id"));
        Products p = productService.findById(id);
        String i = req.getParameter("quantity" + id);
        System.out.println(i);
        System.out.println("quantity" + id);
        int quantity = Integer.parseInt(i);
        String e = "";

        if(quantity>0){
            shoppingCart.update(p, quantity);
        } else if(quantity == 0){
            shoppingCart.remove(id);
        }
        req.setAttribute("error", e);
        session.setAttribute("cart", shoppingCart);
        req.getRequestDispatcher("ShoppingCartCL?action=get").forward(req,resp);
    }


    protected void Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ShoppingCart shoppingCart;
        HttpSession session = req.getSession();
        shoppingCart = (ShoppingCart) session.getAttribute("cart");
        int id = Integer.parseInt(req.getParameter("id"));
        shoppingCart.remove(id);
        session.setAttribute("cart", shoppingCart);
        resp.sendRedirect("gio-hang.jsp");
    }
}
