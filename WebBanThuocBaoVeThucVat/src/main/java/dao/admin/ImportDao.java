package dao.admin;

import bean.Import;
import db.JDBIConnector;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

public class ImportDao {
    // Lấy danh sách các đơn nhập hàng
    public static List<Import> getList() {
        String sql = "SELECT imports.id,imports.id_product,imports.quantity,products.product_name,imports.price,imports.date_import,imports.status FROM imports join " +
                "products on imports.id_product = products.id";
        Jdbi jdbi = JDBIConnector.getJdbi();
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Import.class)
                        .list()
        );
    }

    // Thêm đơn nhập hàng
    public static void insertImport(Import order) {
        String sql = "INSERT INTO imports (id_product, quantity, price, status) VALUES (?,?,?,'Chưa Giao')";
        Jdbi jdbi = JDBIConnector.getJdbi();
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind(0, order.getId_product())
                        .bind(1, order.getQuantity())
                        .bind(2, order.getPrice())
                        .execute()
        );
    }

    // Xóa đơn nhập hàng
    public static void deleteImport(int orderId) {
        Jdbi jdbi = JDBIConnector.getJdbi();
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM imports WHERE id = ?")
                        .bind(0, orderId)
                        .execute()
        );
    }

    // Cập nhật đơn nhập hàng dựa trên id import
    public static void updateImport(int importId,String status) {
        Jdbi jdbi = JDBIConnector.getJdbi();
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE imports SET status = ? WHERE id = ?")
                        .bind(0,status)
                        .bind(1, importId)
                        .execute()
        );
    }

    public static void main(String[] args) {
        ImportDao importDao = new ImportDao();

        System.out.println(ImportDao.getList());

//      importDao.updateImportOrder(1,"Đã Giao");
    }
}


