package controller;

// ... your code ...


import Service.IProductService;
import Service.ProductService;
import bean.Comment;
import bean.Products;
import bean.User;
import dao.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ProductInfor")
public class ProductInfor extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_product = request.getParameter("id_product");
        int id = Integer.parseInt(id_product);
        if(id_product != null){
            IProductService pro = new ProductService();
            Products proID = pro.findById(id);
            request.setAttribute("proID", proID);
        }
        CommentDAO dao = new CommentDAO();
        List<Comment> comments = dao.getAllComments();
        int count = comments.size();
        request.setAttribute("comments", comments);
        request.setAttribute("commentCount", count);

        request.getRequestDispatcher("thong-tin-don-hang.jsp").forward(request,response);
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
