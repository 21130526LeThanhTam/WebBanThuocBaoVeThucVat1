package mapper;

import bean.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailTableMapper implements RowMapper<OrderDetailTable> {
    @Override
    public OrderDetailTable mapRow(ResultSet resultSet) {
        try {
            OrderDetailTable orderDetailTable = new OrderDetailTable();
            orderDetailTable.setId(resultSet.getInt("id"));
            orderDetailTable.setProduct_name(resultSet.getString("product_name"));
            orderDetailTable.setImg(resultSet.getString("img"));
            orderDetailTable.setQuantity(resultSet.getInt("quantity"));
            orderDetailTable.setPriceDetails(resultSet.getInt("priceDetails"));
            return orderDetailTable;

        } catch (SQLException e) {
            return null;
        }
    }
}