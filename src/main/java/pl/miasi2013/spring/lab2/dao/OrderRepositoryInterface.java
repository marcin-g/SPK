package pl.miasi2013.spring.lab2.dao;

import java.util.Collection;

import pl.miasi2013.spring.lab2.model.relations.Order;

public interface OrderRepositoryInterface {
	public int insertOrder(Order order);
	public void updateOrder(Order order);
	public Order getOrderById(long orderId);
	public void deleteOrder(Order order);
	public Collection<Order> getAllOrders();
	public Collection<Order> getAllReportedOrders();
	public Order getOrderByBookId(long bookId);
	public boolean isBookQueuedByUser(long bookId, long userId);
}
