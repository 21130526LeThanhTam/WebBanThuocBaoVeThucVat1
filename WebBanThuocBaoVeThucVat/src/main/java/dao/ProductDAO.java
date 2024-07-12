package dao;

import bean.Product;
import bean.Products;
import db.JDBIConnector;
import org.jdbi.v3.core.Jdbi;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDAO implements IProductDAO {
    private static final int PRODUCTS_PER_PAGE = 6;

    private Connection conn;
    @Override
    public List<Products> findAll1() {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Products> products = jdbi.withHandle(handle -> {
            String sql = "SELECT id, product_name, image, price, id_category, status, des, create_at FROM products";
            return handle.createQuery(sql).mapToBean(Products.class).stream().collect(Collectors.toList());
        });
        return products;
    }

    @Override
    public List<Product> findAll2() {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Product> products2 = jdbi.withHandle(handle -> {
            String sql = "SELECT id, product_name, image, price, id_category, status, des, create_at FROM products";
            return handle.createQuery(sql).mapToBean(Product.class).stream().collect(Collectors.toList());
        });
        return products2;
    }

    @Override
    public List<Products> findNewPro1() {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Products> findNewPro1 = jdbi.withHandle(handle -> {
            String sql = "SELECT id, product_name, image, price, id_category, status, des, create_at FROM products\n" +
                    "order by create_at desc\n" +
                    "LIMIT 3;";
            return handle.createQuery(sql).mapToBean(Products.class).stream().collect(Collectors.toList());
        });
        return findNewPro1;
    }

    @Override
    public List<Products> findNewPro2() {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Products> findNewPro2 = jdbi.withHandle(handle -> {
            String sql = "SELECT id, product_name, image, price, id_category, status, des, create_at FROM products\n" +
                    "order by create_at desc\n" +
                    "LIMIT 3 OFFSET 3;";
            return handle.createQuery(sql).mapToBean(Products.class).stream().collect(Collectors.toList());
        });
        return findNewPro2;
    }

    @Override
    public List<Products> findDiscountPro1() {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Products> findNewPro1 = jdbi.withHandle(handle -> {
            String sql = "SELECT id, id_category, product_name, image, price, des, create_at FROM products\n" +
                    "where status = 1 and id_discount is not null\n"
                    + "LIMIT 3;";
            return handle.createQuery(sql).mapToBean(Products.class).stream().collect(Collectors.toList());
        });
        return findNewPro1;
    }

    @Override
    public List<Products> findDiscountPro2() {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Products> findNewPro1 = jdbi.withHandle(handle -> {
            String sql = "SELECT id, id_category, product_name, image, price, des, create_at FROM products\n" +
                    "where status = 1 and id_discount is not null\n"
                    + "LIMIT 3 OFFSET 3;";
            return handle.createQuery(sql).mapToBean(Products.class).stream().collect(Collectors.toList());
        });
        return findNewPro1;
    }

    @Override
    public List<Products> findById(int id) {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Products> products = jdbi.withHandle(handle -> {
            String sql = "SELECT id, product_name, image, price, id_category, status, des, create_at FROM products where id=?";
            return handle.createQuery(sql).bind(0, id).mapToBean(Products.class).stream().collect(Collectors.toList());
        });
        return products;
    }

    @Override
    public List<Products> findByPriceMax(String id) {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Products> products = jdbi.withHandle(handle -> {
            String sql = "SELECT id, product_name, image, price, id_category, status, des, create_at FROM products\n" +
                    "where id_category like ? " + // Added space here
                    "order by price desc";
            return handle.createQuery(sql).bind(0, "%" + id + "%").mapToBean(Products.class).list();
        });
        return products;
    }

    @Override
    public List<Products> findByPriceMin(String id) {
        Jdbi jdbi = JDBIConnector.getJdbi();
        return jdbi.withHandle(handle -> {
            String sql = "SELECT id, product_name, image, price, id_category, status, des, create_at FROM products\n" +
                    "where id_category like ? " + // Added space here
                    "order by price asc";
            return handle.createQuery(sql).bind(0, "%" + id + "%").mapToBean(Products.class).list();
        });
    }

    @Override
    public int getProductsPerPageConstant() {
        return PRODUCTS_PER_PAGE;
    }

    @Override
    public int getTotalPages() {
        Jdbi jdbi = JDBIConnector.getJdbi();
        int totalProducts = jdbi.withHandle(handle -> {
            String sql = "select count(*) as count from products";
            return handle.createQuery(sql).mapTo(Integer.class).one();
        });
        return (int) Math.ceil((double) totalProducts / getProductsPerPageConstant());
    }


    @Override
    public List<Products> getProductsPerPage(int currentPage) {
        int offset = (currentPage - 1) * getProductsPerPageConstant();
        Jdbi jdbi = JDBIConnector.getJdbi();
        return jdbi.withHandle(handle -> {
            String sql = "select id, product_name, image, price, id_category, status, des, create_at from products WHERE status=1 limit ?, ?";
            return handle.createQuery(sql).bind(0, offset).bind(1, getProductsPerPageConstant())
                    .mapToBean(Products.class).stream().collect(Collectors.toList());
        });
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.getProductsPerPage(1));
    }
}