package dao;

import bean.Product;
import bean.Products;
import db.JDBIConnector;
import org.jdbi.v3.core.Jdbi;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDAO implements IProductDAO {
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
    public void sortByPrice(List<Products> products, boolean isAscending) {
        Collections.sort(products, (o1, o2) -> {
            if (isAscending) {
                return Integer.compare(o1.getPrice(), o2.getPrice());
            } else {
                return Integer.compare(o2.getPrice(), o1.getPrice());
            }
        });
    }


    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
//        dao.findByPriceMin("").forEach(System.out::println);
        System.out.println(dao.findDiscountPro1().size());
        System.out.println(dao.findDiscountPro2().size());
    }
}