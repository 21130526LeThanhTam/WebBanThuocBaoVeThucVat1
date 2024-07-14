package dao;

import bean.*;
import mapper.*;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersDAO extends AbstractDAO<Orders> implements IOrdersDAO {

	public Products findImageBy(int productId) {
		String sql = "select * from products where id = ?";
		return query(sql, new ProductMapper(), productId).get(0);
	}

	@Override
	public Integer insertOrder(Orders o) {
		String sql = "insert into orders(id_user, total_price, shipping_fee, address, phone_number) values(?,?,?,?,?)";
		return insert(sql, o.getIdUser(), o.getTotalPrice(), o.getShippingFee(), o.getAddress(), o.getPhoneNumber());
	}

	@Override
	public List<Orders> getOrder() {
		String sql = "select * from orders";
		return query(sql, new OrderMapper());
	}

	public List<OrderTable> getOrderforAdmin() {
		String sql = "SELECT o.id AS id, u.user_name AS username, o.create_at AS create_at, " +
				"o.total_price AS total_price, o.shipping_fee AS shipping_fee, " +
				"o.address AS address, o.phone_number AS phone_number, " +
				"o.payment_status AS payment_status, o.order_status AS order_status " +
				"FROM orders o " +
				"JOIN users u ON o.id_user = u.id";
		return query(sql, new OrderTableMapper());
	}

	public List<OrderDetailTable> getOrderDetailsByOrderId(int orderId) {
		String sql = "SELECT od.id as id, p.product_name AS product_name,p.image as img, od.quantity as quantity, (od.quantity * p.price) AS priceDetails " +
				"FROM order_details od " +
				"JOIN products p ON od.id_product = p.id " +
				"WHERE od.id_order = ?";
		return query(sql, new OrderDetailTableMapper(), orderId);
	}

	@Override
	public OrderTable getOrderById(int orderId) {
		String sql = "SELECT o.id AS id, u.user_name AS username, o.create_at AS create_at, " +
				"o.total_price AS total_price, o.shipping_fee AS shipping_fee, " +
				"o.address AS address, o.phone_number AS phone_number, " +
				"o.payment_status AS payment_status, o.order_status AS order_status " +
				"FROM orders o " +
				"JOIN users u ON o.id_user = u.id " +
				"WHERE o.id = ?";
		return query(sql, new OrderTableMapper(), orderId).get(0);
	}
//update trạng thái đơn hàng
	@Override
	public void updateOrderStatus(int orderId, int orderStatus) {
		String sql = "UPDATE orders SET order_status = ? WHERE id = ?";
		update(sql, orderStatus, orderId);
	}

// Update trạng thái thanh toán
	@Override
	public void updatePaymentStatus(int orderId, String paymentStatus) {
		String sql = "UPDATE orders SET payment_status = ? WHERE id = ?";
		update(sql, paymentStatus, orderId);
	}

	@Override
	public List<Orders> getOrdersByUser(User user) {
		String sql = "select * from orders where id_user =?";
		return query(sql, new OrderMapper(), user.getId());
	}

	@Override
	public  Integer insertOrdersDetail(OrderDetail od) {
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

	public static void main(String[] args) {
		IOrdersDAO order = new OrdersDAO();
		//System.out.println(order.getOrderforAdmin());
		System.out.println(order.getOrderById(1	));

	}
}