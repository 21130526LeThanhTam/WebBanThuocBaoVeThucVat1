package dao;

import bean.ProductReview;
import bean.Products;
import db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class ProductReviewDao {
    // Lấy ra danh sách các comment thuộc về sản phẩm nào đó.
    public List<ProductReview> getListReview(int productId) {
        String sql = "SELECT id, id_product, id_user, user_name, rating, content, create_at FROM product_reviews WHERE id_product = ?";
        List<ProductReview> productReviewList = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind(0, productId)
                        .mapToBean(ProductReview.class)
                        .collect(Collectors.toList()));
        return productReviewList;
    }
    // Lấy ra danh sách các comment có rating cụ thể
    public List<ProductReview> getListReviewByRating(int productId, int rating) {
        String sql = "SELECT id, id_product, id_user, user_name, rating, content, create_at FROM product_reviews WHERE id_product = ? AND rating = ?";
        List<ProductReview> productReviewList = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind(0, productId)
                        .bind(1, rating)
                        .mapToBean(ProductReview.class)
                        .collect(Collectors.toList()));
        return productReviewList;
    }

    public List<ProductReview> getListReviewByRating1(int productId) {
        return getListReviewByRating(productId, 1);
    }

    public List<ProductReview> getListReviewByRating2(int productId) {
        return getListReviewByRating(productId, 2);
    }

    public List<ProductReview> getListReviewByRating3(int productId) {
        return getListReviewByRating(productId, 3);
    }

    public List<ProductReview> getListReviewByRating4(int productId) {
        return getListReviewByRating(productId, 4);
    }

    public List<ProductReview> getListReviewByRating5(int productId) {
        return getListReviewByRating(productId, 5);
    }
}
