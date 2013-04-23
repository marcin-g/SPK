package pl.miasi2013.spring.lab2.dao;

import java.util.Collection;

import javax.sql.DataSource;

import org.apache.commons.lang.NotImplementedException;

import pl.miasi2013.spring.lab2.model.relations.Order;

public class OrderRepository implements OrderRepositoryInterface {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public OrderRepository() {
	}

	@Override
	public void insertOrder(Order order) {
		throw new NotImplementedException();
	}

	@Override
	public void updateOrder(Order order) {
		throw new NotImplementedException();
	}

	@Override
	public Order getOrderById(long orderId) {
		throw new NotImplementedException();
	}

	@Override
	public void deleteOrder(Order order) {
		throw new NotImplementedException();
	}

	@Override
	public Collection<Order> getAllOrders() {
		throw new NotImplementedException();
	}

}
