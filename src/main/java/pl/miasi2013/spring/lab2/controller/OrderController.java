package pl.miasi2013.spring.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.relations.Order;
import pl.miasi2013.spring.lab2.service.BookService;
import pl.miasi2013.spring.lab2.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllOrders(Model model) {
		model.addAttribute("orders", orderService.getAllOrdersWithBooks());
		return "ordersList";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("order", new Order());
		model.addAttribute("book", new Book());
		return "createOrUpdateOrderForm";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processAddOrder(@ModelAttribute("order") Order order,
			@ModelAttribute("book") Book book, BindingResult result) {

		order.setTime(System.currentTimeMillis());
		order.setBookURL(book.getBookURL());
		order.setBookId(book.getId());
		if (!isOrderValid(order, result)
				&& !BookController.isBookValid(book, result)) {
			return "createOrUpdateOrderForm";
		}
		orderService.insertOrder(order);
		bookService.insertBook(book);
		return "redirect:/orders";
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public String initEditOrder(@PathVariable("orderId") long orderId,
			Model model) {
		Order order=orderService.getOrderById(orderId);
		model.addAttribute("order", order);
		model.addAttribute("book", bookService.getBookById(order.getBookId()));
		return "createOrUpdateOrderForm";
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
	public String updateOrder(@ModelAttribute("order") Order order,
			@ModelAttribute("book") Book book, BindingResult result) {
		order.setTime(System.currentTimeMillis());
		order.setBookURL(book.getBookURL());
		order.setBookId(book.getId());
		if (!isOrderValid(order, result)
				&& !BookController.isBookValid(book, result)) {
			return "createOrUpdateOrderForm";
		}
		orderService.updateOrder(order);
		bookService.updateBook(book);
		return "redirect:/orders";
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
	public String deleteOrder(@PathVariable("orderId") long orderId) {
		orderService.deleteOrderById(orderId);
		return "redirect:/orders";
	}

	private boolean isOrderValid(Order order, BindingResult result) {
		boolean valid = true;
		return valid;
	}
}
