package dao;

import bean.*;

import java.util.List;

public interface IOrdersDAO {
	
	Integer insertOrder(Orders o);

	Integer insertOrdersDetail(OrderDetail od);
	List<Orders> getOrder();

	List<Orders> getOrdersByUser(User user);

	List<OrderDetail> getDetailsByOrder(List<Integer> ordersId);
	 List<OrderTable> getOrderforAdmin();
	 List<OrderDetailTable> getOrderDetailsByOrderId(int orderId);

	OrderTable getOrderById(int orderId);
}