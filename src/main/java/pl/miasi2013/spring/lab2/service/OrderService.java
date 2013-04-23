package pl.miasi2013.spring.lab2.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.miasi2013.spring.lab2.dao.OrderRepositoryInterface;
import pl.miasi2013.spring.lab2.model.relations.Order;
import pl.miasi2013.spring.lab2.service.exceptions.OrderNotFoundException;

@Service
public class OrderService {
	@Autowired
	private OrderRepositoryInterface orderRepository;

	public void insertOrder(Order order) {
		orderRepository.insertOrder(order);
	}

	public Order getOrderById(long orderId) {
		Order order=orderRepository.getOrderById(orderId);
		if(order==null){
			throw new OrderNotFoundException(orderId);
		}
		return order;
	}

	public void updateOrder(Order order) {
		orderRepository.updateOrder(order);
	}

	public void deleteOrderById(long orderId) {
		Order order=orderRepository.getOrderById(orderId);
		if(order==null){
			throw new OrderNotFoundException(orderId);
		}
		else{
			orderRepository.deleteOrder(order);
		}
		
	}

	public Collection<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}
	
	

}
