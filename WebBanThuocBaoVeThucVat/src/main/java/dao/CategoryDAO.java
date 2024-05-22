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

public class CategoryDAO {
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
    public static void insertCategory(String nameCate){
        JDBIConnector.getJdbi().useHandle(handle ->
                handle.createUpdate("INSERT INTO categories(name_category) VALUES (?)")
                        .bind(0, nameCate)
                        .execute()
        );
    }
    // xóa danh mục
    public static void deleteCategory(int idCate){
        JDBIConnector.getJdbi().useHandle(handle ->
                handle.createUpdate("DELETE FROM categories WHERE id=?")
                        .bind(0,idCate)
                        .execute());
    }
    public static void updateCategory(String categoryName,int idCategory){
        JDBIConnector.getJdbi().useHandle(handle ->
                handle.createUpdate("UPDATE categories SET  \n" +
                        "name_category=? WHERE id=?")
                        .bind(0,categoryName)
                        .bind(1,idCategory)
                        .execute());
    }


    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
//        List<Category> list = dao.getList();
//        for(Category i : list){
//            System.out.println(i.toString());
//        }
//        System.out.println(CategoryDAO.listCategory("ô",0));
        CategoryDAO.updateCategory("Phân bón",3);
        System.out.println("ffewfewwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        System.out.println(CategoryDAO.getList());

    }

}
