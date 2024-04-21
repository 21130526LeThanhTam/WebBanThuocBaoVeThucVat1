package dao;

import bean.Orders;

public interface IOrdersDAO {
	
	Integer insertOrder(Orders o);

	Integer insertOrdersDetail(Integer orderId, int productId, int amount);

}