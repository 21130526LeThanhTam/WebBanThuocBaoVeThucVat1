package dao;

import bean.Category;
import bean.Product;
import db.DBContext;
import db.JDBIConnector;
import org.jdbi.v3.core.Jdbi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class
CategoryDAO {
    public static List<Category> getList(){
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Category> cateList = jdbi.withHandle(handle -> {

            String sql = "SELECT id, name_category FROM categories";

            return handle.createQuery(sql).mapToBean(Category.class).list();
        });
        return cateList;
    }
    // đây là danh sách category có thể tìm theo tên và chia số trang.
    public static List<Category> listCategory(String name, int index) {
        return JDBIConnector.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT id, name_category " +
                                "FROM categories " +
                                "WHERE name_category LIKE :name " +
                                "ORDER BY id " +
                                "LIMIT 5 OFFSET :offset")
                        .bind("name", "%" + name + "%")
                        .bind("offset", index)
                        .mapToBean(Category.class)
                        .collect(Collectors.toList())
        );
    }
    // thêm doanh mục
    public static boolean insertCategory(String nameCate) {
        try {
            int rowsAffected = JDBIConnector.getJdbi().withHandle(handle ->
                    handle.createUpdate("INSERT INTO categories(name_category) VALUES (?)")
                            .bind(0, nameCate)
                            .execute()
            );
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // xóa danh mục
    public static boolean deleteCategory(int idCate) {
        return JDBIConnector.getJdbi().withHandle(handle ->
                handle.createUpdate("DELETE FROM categories WHERE id = ?")
                        .bind(0, idCate)
                        .execute() > 0
        );
    }
    public static boolean updateCategory(String categoryName, int idCategory) {
        int rowsAffected = JDBIConnector.getJdbi().withHandle(handle ->
                handle.createUpdate("UPDATE categories SET name_category=? WHERE id=?")
                        .bind(0, categoryName)
                        .bind(1, idCategory)
                        .execute()
        );
        return rowsAffected > 0;
    }



    public static void main(String[] args) {

        System.out.println(CategoryDAO.getList());

    }

}
