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
import pl.miasi2013.spring.lab2.model.Book.BookState;
import pl.miasi2013.spring.lab2.model.User;
import pl.miasi2013.spring.lab2.model.relations.Order;
import pl.miasi2013.spring.lab2.service.BookService;
import pl.miasi2013.spring.lab2.service.OrderService;
import pl.miasi2013.spring.lab2.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllOrders(Model model) {
		User user = userService.getLoggedUser();
		if (user.hasRole("ROLE_ADMIN")) {
			model.addAttribute("orders", orderService
					.getOrdersWithBooks(orderService.getReportedOrders()));
		} else {
			model.addAttribute("orders", orderService
					.getOrdersWithBooks(orderService.getAllOrdersByUserId(user
							.getId())));
		}
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
		
		order.setUserId(userService.getLoggedUser().getId());
		orderService.createOrderNewBook(order, book);
		return "redirect:/order";
	}

	@RequestMapping(value = "/confirm/{orderId}", method = RequestMethod.POST)
	public String confirmOrder(@PathVariable("orderId") long orderId) {
		bookService.setBookStateByOrder(orderId, BookState.AWAITING_RECEPTION);
		return "redirect:/order";
	}
	

	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public String initEditOrder(@PathVariable("orderId") long orderId,
			Model model) {
		Order order = orderService.getOrderById(orderId);
		model.addAttribute("order", order);
		model.addAttribute("book", bookService.getBookById(order.getBookId()));
		return "createOrUpdateOrderForm";
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
	public String updateOrder(@ModelAttribute("order") Order order,
			@ModelAttribute("book") Book book, BindingResult result) {

		orderService.updateOrderWithBook(order, book);
		return "redirect:/order";
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
	public String deleteOrder(@PathVariable("orderId") long orderId) {
		orderService.deleteOrderById(orderId);
		return "redirect:/orders";
	}

}
