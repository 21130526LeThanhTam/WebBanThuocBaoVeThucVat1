package controller;

// ... your code ...


import Service.IProductService;
import Service.ProductService;
import bean.*;
import dao.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/ProductInfor")
public class ProductInfor extends HttpServlet {
    IProductService pro = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        String id = request.getParameter("id_product");
        if (id != null && !id.isEmpty()) {
            try {
                String ip = request.getHeader("X-FORWARDED-FOR");
                if (ip == null) ip = request.getRemoteAddr();

                Products prod = pro.findById(Integer.parseInt(id));
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                User user = (User) session.getAttribute("auth");
                int remain = prod.getInventory_quantity();
                if (cart != null && user != null) {
                    for (CartItem item : cart.getCartItemList()) {
                        if (item.getProduct().getId() == prod.getId()) {
                            remain = prod.getInventory_quantity() - item.getQuantity();
                            break;
                        }
                    }
                }
                request.setAttribute("proID", prod);
                if (remain > 0) {
                    request.setAttribute("remain", remain);
                } else {
                    request.setAttribute("error", "Bạn đã thêm số lượng sản phẩm tối đa vào giỏ!");
                }
                CommentDAO dao = new CommentDAO();
                List<Comment> comments = dao.getAllComments();
                int count = comments.size();
                request.setAttribute("comments", comments);
                request.setAttribute("commentCount", count);
                request.getRequestDispatcher("thong-tin-don-hang.jsp").forward(request,response);
            } catch (Exception e) {
                out.println(e.getLocalizedMessage());
            }
        } else {
            out.println("Product ID is missing");
        }
        out.flush();
        out.close();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String commentText = request.getParameter("commentText");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null){
            Comment comment = new Comment();
            comment.setUsername(username);
            comment.setCommentText(commentText);
            comment.setEmail(user.getEmail());

            CommentDAO dao = new CommentDAO();
            dao.addComment(comment);

            response.sendRedirect(request.getContextPath() + "/ProductInfor");
        }else{
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
