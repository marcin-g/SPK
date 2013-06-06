package pl.miasi2013.spring.lab2.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import pl.miasi2013.spring.lab2.controller.BookController;
import pl.miasi2013.spring.lab2.dao.OrderRepositoryInterface;
import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.Book.BookState;
import pl.miasi2013.spring.lab2.model.relations.Order;
import pl.miasi2013.spring.lab2.service.exceptions.OrderNotFoundException;

@Service
public class OrderService {
	@Autowired
	private OrderRepositoryInterface orderRepository;
	@Autowired
	private BookService bookService;
	@Autowired
	private SimpleMailService simpleMailService;

	@Transactional
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

	@Transactional
	public void updateOrder(Order order) {
		orderRepository.updateOrder(order);
	}

	@Transactional
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
	
	public Collection<Order> getReportedOrders() {
		return orderRepository.getAllReportedOrders();
	}
	
	public Map<Order,Book> getOrdersWithBooks(Collection<Order> orders) {
		HashMap<Order,Book> map=new HashMap<Order,Book>();
		for(Order order:orders){
			map.put(order, bookService.getBookById(order.getBookId()));
		}
		return map;
	}

	@Transactional
	public void createOrderNewBook(Order order, Book book) {
		order.setTime(System.currentTimeMillis());
		order.setBookURL(book.getBookURL());
		order.setUserId(0); 
		book.setState(BookState.REPORTED);
		long bookId = bookService.insertBook(book);
		order.setBookId(bookId);
		this.insertOrder(order);
		simpleMailService.sendBookInfo("aa");
		
	}
	
	@Transactional
	public void updateOrderWithBook(Order order,Book book){
		order.setTime(System.currentTimeMillis());
		order.setBookURL(book.getBookURL());
		order.setBookId(book.getId());
		bookService.updateBook(book);
		this.updateOrder(order);
	}
	
	public Order getOrderByBookId(long bookId){
		return orderRepository.getOrderByBookId(bookId);
	}
	
	public static  boolean isOrderValid(Order order, BindingResult result) {
		boolean valid = true;
		return valid;
	}
	
	
}
