package pl.miasi2013.spring.lab2.dao;

import pl.miasi2013.spring.lab2.model.relations.Order;

public interface OrderRepositoryInterface {
	public void insertOrder(Order order);
	public void updateOrder(Order order);
}
