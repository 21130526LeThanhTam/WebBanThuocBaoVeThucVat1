package dao;

import bean.OrderDetail;
import bean.Orders;
import bean.User;

import java.util.List;

public interface IOrdersDAO {
	
	Integer insertOrder(Orders o);

	Integer insertOrdersDetail(Integer orderId, int productId, int amount);
	List<Orders> getOrder(Orders o);

	List<Orders> getOrdersByUser(User user);

	List<OrderDetail> getDetailsByOrder(List<Integer> ordersId);
}