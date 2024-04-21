package dao;

import dao.IOrdersDAO;
import bean.Orders;

public class OrdersDAO extends AbstractDAO<Orders> implements IOrdersDAO {

	@Override
	public Integer insertOrder(Orders o) {
		String sql = "insert into orders(id_user, total_price, shipping_fee, address, phone_number, status_id) values(?,?,?,?,?,?)";
		return insert(sql, o.getIdUser(), o.getTotalPrice(), o.getShippingFee(), o.getAddress(), o.getPhoneNumber(), o.getStatus());
	}
	
	@Override
	public Integer insertOrdersDetail(Integer orderId, int productId, int amount) {
		String sql="insert into order_details(id_order, id_product, quantity) values(?,?,?)";
		return insert(sql, orderId, productId, amount);
	}
}