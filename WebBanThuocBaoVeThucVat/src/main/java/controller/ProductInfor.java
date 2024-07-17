package controller;

// ... your code ...


import Service.IProductService;
import Service.ProductService;
import bean.Comment;
import bean.ProductReview;
import bean.Products;
import bean.User;
import dao.CommentDAO;
import dao.ProductReviewDao;

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

    // Thêm phương thức xử lý AJAX
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_product = Integer.parseInt(request.getParameter("id_product"));
        IProductService pro = new ProductService();
        Products proID = pro.findById(id_product);
        request.setAttribute("proID", proID);
        ProductReviewDao dao = new ProductReviewDao();
        List<ProductReview> productReviews;
        productReviews = dao.getListReview(id_product);
        double averageRating = averageRating(productReviews);
        request.setAttribute("productReviews", productReviews);
        request.setAttribute("averageRating", averageRating);
        if (request.getHeader("X-Requested-With") == null) {
            System.out.println("vào thông tin đơn hàng");
            request.getRequestDispatcher("thong-tin-don-hang.jsp").forward(request, response);
        } else {
            System.out.println("vào comment.jsp");
            request.getRequestDispatcher("productReviews.jsp").forward(request, response);
        }
    }

    public double averageRating(List<ProductReview>productReviews){
        double sum = 0;
        for (ProductReview review : productReviews){
            sum +=review.getRating();
        }
        return sum/ productReviews.size();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //chức năng cho người dùng đánh giá sản phẩm ở dạng comment
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        String commentText = request.getParameter("commentText");
//        int rating = Integer.parseInt(request.getParameter("rating"));
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        String id_product = request.getParameter("productId");
//        System.out.println(id_product);
//        int productId = Integer.parseInt(id_product);
//
//        if (user != null) {
//            ProductReview review = new ProductReview();
//            review.setUser_name(user.getUsername());
//            review.setContent(commentText);
//            review.setRating(rating);
//            review.setId_user(user.getId());
//            review.setId_product(productId);
//
//            ProductReviewDao dao = new ProductReviewDao();
//            dao.insertProductReview(review);
//
//            response.sendRedirect(request.getContextPath() + "/ProductInfor?id_product=" + productId);
//        } else {
//            response.sendRedirect(request.getContextPath() + "/login");
//        }
        doGet(request,response);
    }

}
