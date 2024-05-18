package dao;

import bean.OrderDetail;
import bean.Orders;
import bean.Products;
import bean.User;
import mapper.OrderDetailMapper;
import mapper.OrderMapper;
import mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersDAO extends AbstractDAO<Orders> implements IOrdersDAO {

	public Products findImageBy(int productId) {
		String sql = "select * from products where id = ?";
		return query(sql, new ProductMapper(), productId).get(0);
	}

	@Override
	public Integer insertOrder(Orders o) {
		String sql = "insert into orders(id_user, total_price, shipping_fee, address, phone_number) values(?,?,?,?,?,?)";
		return insert(sql, o.getIdUser(), o.getTotalPrice(), o.getShippingFee(), o.getAddress(), o.getPhoneNumber());
	}

	@Override
	public List<Orders> getOrder() {
		String sql = "select * from orders";
		return query(sql, new OrderMapper());
	}

	@Override
	public List<Orders> getOrdersByUser(User user) {
		String sql = "select * from orders where id_user =?";
		return query(sql, new OrderMapper(), user.getId());
	}

	@Override
	public Integer insertOrdersDetail(OrderDetail od) {
		String sql="insert into order_details(id_order, id_product, quantity) values(?,?,?)";
		return insert(sql, od.getOrder_id(), od.getProduct_id(), od.getQuantity());
	}

	@Override
	public List<OrderDetail> getDetailsByOrder(List<Integer> ordersId) {

		// Tạo chuỗi các tham số
		String params = ordersId.stream()
				.map(Object::toString)
				.collect(Collectors.joining(", "));
		// Tạo câu truy vấn SQL với chuỗi các tham số
		String sql = "SELECT * FROM order_details WHERE id_order IN (" + params + ")";

		return query(sql, new OrderDetailMapper());
	}

	public Orders findBy(int orderId) {
		String sql = "select * from orders where id = ?";
		return query(sql, new OrderMapper(), orderId).get(0);
	}
}