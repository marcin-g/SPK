package pl.miasi2013.spring.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.miasi2013.spring.lab2.model.relations.Queue;
import pl.miasi2013.spring.lab2.service.BookService;
import pl.miasi2013.spring.lab2.service.QueueService;

@Controller
@RequestMapping("/queue")
public class QueueController {
	@Autowired
	private QueueService queueService;
	@Autowired
	private BookService bookService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllQueues(Model model) {
		model.addAttribute("queues", queueService.getAllQueues());
		return "queuesList";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("queue", new Queue());
		return "createOrUpdateQueueForm";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processAddQueue(@ModelAttribute("queue") Queue queue,
			BindingResult result) {
		if (!QueueService.isQueueValid(queue,result)) {
			return "createOrUpdateQueueForm";
		}
		queueService.insertQueue(queue);
		return "redirect:/queues";
	}

	@RequestMapping(value = "/{queueId}", method = RequestMethod.GET)
	public String initEditQueue(@PathVariable("queueId") long queueId, Model model) {
		model.addAttribute("queue", queueService.getQueueById(queueId));		
		return "createOrUpdateQueueForm";
	}
	
	@RequestMapping(value = "/{queueId}", method = RequestMethod.PUT)
	public String updateQueue(@ModelAttribute("queue") Queue queue,BindingResult result) {
		if (!QueueService.isQueueValid(queue,result)) {
			return "createOrUpdateQueueForm";
		}
		queueService.updateQueue(queue);
		return "redirect:/queues";		
	}
	
	@RequestMapping(value = "/{queueId}", method = RequestMethod.DELETE)
	public String deleteQueue(@PathVariable("queueId") long queueId) {
		queueService.deleteQueueById(queueId);
		return "redirect:/queues";		
	}

	
	@RequestMapping(value = "show/{bookId}", method = RequestMethod.GET)
	public String showQueue(@PathVariable("bookId") long bookId, Model model) {
		model.addAttribute("users", queueService.getUsersFromQueueByBookId(bookId));		
		model.addAttribute("book",bookService.getBookById(bookId));
		return "queue";
	}
	
	@RequestMapping(value = "/rm/{bookId}/{userId}", method = RequestMethod.DELETE)
	public String deleteQueue(@PathVariable("bookId") long bookId,@PathVariable("userId") long userId) {
		queueService.deleteQueue(bookId,userId);
		return "redirect:/queues";
	}
}
