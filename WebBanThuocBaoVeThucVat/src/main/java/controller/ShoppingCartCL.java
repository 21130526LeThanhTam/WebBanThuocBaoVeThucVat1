package controller;

import Service.IProductService;
import Service.ProductService;
import bean.CartItem;
import bean.Products;
import bean.ShoppingCart;
import bean.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
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
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }
        session.setAttribute("cart", shoppingCart);
        request.getRequestDispatcher("gio-hang.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("cart", shoppingCart);
        }
        String action = request.getParameter("action");
        switch (action) {
        case "get": request.getRequestDispatcher("gio-hang.jsp").forward(request, response);
            break;
        case "delete": Delete(request, response);
            break;
        case "put": Put(request, response);
            break;
        case "add":
            int id = Integer.parseInt(request.getParameter("id"));
            int type = Integer.parseInt(request.getParameter("type"));
            Products product = productService.findById(id);
            CartItem cartItem = null;
            if (type == 0) {
                cartItem = new CartItem(product, 1);
            } else if (type == 1) {
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cartItem = new CartItem(product, quantity);
            }
            shoppingCart.add(cartItem);
            session.setAttribute("cart", shoppingCart);
            // Tạo một Map để chứa dữ liệu phản hồi
            Map<String, Object> responseData = new HashMap<>();

            responseData.put("totalItems", shoppingCart.getCartItemList().size());
            responseData.put("items", shoppingCart.getCartItemList());

            // Chuyển đổi Map responseData thành chuỗi JSON
            String jsonResponse = new Gson().toJson(responseData);

            // Ghi chuỗi JSON vào response để gửi về client
            response.getWriter().write(jsonResponse);
            session.setAttribute("totalItems", shoppingCart.getCartItemList().size());
            break;
        }
    }

    protected void Put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart;
        HttpSession session = req.getSession(true);
        shoppingCart = (ShoppingCart) session.getAttribute("cart");
        int id = Integer.parseInt(req.getParameter("id"));
        Products p = productService.findById(id);
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        if (quantity > 0) {
            shoppingCart.update(p, quantity);
        } else if (quantity == 0) {
            shoppingCart.remove(id);
        }
        session.setAttribute("cart", shoppingCart);
        // Tạo một Map để chứa dữ liệu phản hồi
        Map<String, Object> responseData = new HashMap<>();

        responseData.put("totalItems", shoppingCart.getCartItemList().size());
        responseData.put("items", shoppingCart.getCartItemList());

        // Chuyển đổi Map responseData thành chuỗi JSON
        String jsonResponse = new Gson().toJson(responseData);

        // Ghi chuỗi JSON vào response để gửi về client
        resp.getWriter().write(jsonResponse);
        session.setAttribute("totalItems", shoppingCart.getCartItemList().size());
    }


    protected void Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart;
        HttpSession session = req.getSession();
        shoppingCart = (ShoppingCart) session.getAttribute("cart");
        int id = Integer.parseInt(req.getParameter("id"));
        shoppingCart.remove(id);
        session.setAttribute("cart", shoppingCart);
        // Tạo một Map để chứa dữ liệu phản hồi
        Map<String, Object> responseData = new HashMap<>();

        responseData.put("totalItems", shoppingCart.getCartItemList().size());
        responseData.put("items", shoppingCart.getCartItemList());

        // Chuyển đổi Map responseData thành chuỗi JSON
        String jsonResponse = new Gson().toJson(responseData);

        // Ghi chuỗi JSON vào response để gửi về client
        resp.getWriter().write(jsonResponse);
        session.setAttribute("totalItems", shoppingCart.getCartItemList().size());
    }
}
