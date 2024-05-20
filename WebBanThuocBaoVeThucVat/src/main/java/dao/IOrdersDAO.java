package dao;

import bean.OrderDetail;
import bean.Orders;
import bean.User;

import java.util.List;

public interface IOrdersDAO {
	
	Integer insertOrder(Orders o);

	Integer insertOrdersDetail(OrderDetail od);
	List<Orders> getOrder();

	List<Orders> getOrdersByUser(User user);

	List<OrderDetail> getDetailsByOrder(List<Integer> ordersId);
}