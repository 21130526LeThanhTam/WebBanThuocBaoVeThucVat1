package Service;

import java.util.List;

import bean.CartItem;
import dao.IOrdersDAO;
import dao.OrdersDAO;
import bean.Orders;
import bean.Product;
import Service.IOrdersService;

public class OrdersService implements IOrdersService {
	private final IOrdersDAO ordersDAO;
	
	public OrdersService() {
		this.ordersDAO = new OrdersDAO();
	}

	@Override
	public void insertOrder(Orders o) {
		Integer orderId = this.ordersDAO.insertOrder(o);
		List<CartItem> list = o.getLp();
		for (CartItem p : list) {
			this.ordersDAO.insertOrdersDetail(orderId, p.getProduct().getId(), p.getQuantity());
		}
	}
}