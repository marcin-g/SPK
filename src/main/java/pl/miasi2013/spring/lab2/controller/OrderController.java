package pl.miasi2013.spring.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.relations.Order;
import pl.miasi2013.spring.lab2.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllOrders(Model model) {
		model.addAttribute("books", orderService.getAllOrders());
		return "ordersList";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("order", new Order());
		return "createOrUpdateOrderForm";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processAddOrder(@ModelAttribute("order") Order order,
			BindingResult result) {
		if (!isOrderValid(order,result)) {
			return "createOrUpdateOrderForm";
		}
		orderService.insertOrder(order);
		return "redirect:/orders";
	}
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public String initEditOrder(@PathVariable("orderId") long orderId, Model model) {
		model.addAttribute("order", orderService.getOrderById(orderId));		
		return "createOrUpdateOrderForm";
	}
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
	public String updateOrder(@ModelAttribute("order") Order order,BindingResult result) {
		if (!isOrderValid(order,result)) {
			return "createOrUpdateOrderForm";
		}
		orderService.updateOrder(order);
		return "redirect:/orders";		
	}
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
	public String deleteOrder(@PathVariable("orderId") long orderId) {
		orderService.deleteOrderById(orderId);
		return "redirect:/orders";		
	}

	private boolean isOrderValid(Order order, BindingResult result) {
		boolean valid=true;
		return valid;
	}
}
