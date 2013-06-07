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
import pl.miasi2013.spring.lab2.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllBooks(Model model) {
		model.addAttribute("books", bookService.getAllBooks());
		return "booksList";
	}

	@RequestMapping(value = "/reviewing/{bookId}", method = RequestMethod.POST)
	public String confirmReviewing(@PathVariable("bookId") long bookId) {
		bookService.setBookState(bookId, BookState.REVIEWED);
		return "redirect:/books";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String initProfileForm(Model model) {
		model.addAttribute("user", new User());
		return "profile";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("book", new Book());
		return "createOrUpdateBookForm";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processAddBook(@ModelAttribute("book") Book book,
			BindingResult result) {
		if (!BookService.isBookValid(book,result)) {
			return "createOrUpdateBookForm";
		}
		bookService.insertBook(book);
		return "redirect:/books";
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
	public String initShowBook(@PathVariable("bookId") long bookId, Model model) {
		model.addAttribute("book", bookService.getBookById(bookId));
		model.addAttribute("status", bookService.getBookStatus(bookId));
//		model.addAttribute("book", bookService.getBookById(bookId));
		return "showBook";
	}
	
	@RequestMapping(value = "/edit/{bookId}", method = RequestMethod.GET)
	public String initEditBook(@PathVariable("bookId") long bookId, Model model) {
		model.addAttribute("book", bookService.getBookById(bookId));		
		return "createOrUpdateBookForm";
	}

	@RequestMapping(value = "/edit/{bookId}", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute("book") Book book,BindingResult result) {
		if (!BookService.isBookValid(book,result)) {
			return "createOrUpdateBookForm";
		}
		bookService.updateBook(book);
		return "redirect:/books";		
	}
	
	@RequestMapping(value = "/review/{bookId}", method = RequestMethod.GET)
	public String initEditBookReview(@PathVariable("bookId") long bookId, Model model) {
		model.addAttribute("book", bookService.getBookById(bookId));	
		return "createOrUpdateReviewForm";
	}
	@RequestMapping(value = "/review/{bookId}", method = RequestMethod.POST)
	public String updateBookReview(@ModelAttribute("book") Book book, Model model) {
		bookService.updateBookReview(book);	
		return "redirect:/books/{bookId}";	
	}

	@RequestMapping(value = "/borrow/{bookId}", method = RequestMethod.POST)
	public String updateBookBorrow(@PathVariable("bookId") long bookId, Model model) {
		bookService.updateBookBorrow(bookId);
		return "redirect:/books/{bookId}";	
	}
	
	@RequestMapping(value = "/queue/{bookId}", method = RequestMethod.POST)
	public String updateBookQueue(@PathVariable("bookId") long bookId, Model model) {
		bookService.updateBookQueue(bookId);
		return "redirect:/books/{bookId}";	
	}
	
	
	@RequestMapping(value = "/edit/{bookId}", method = RequestMethod.DELETE)
	public String deleteBook(@PathVariable("bookId") long bookId) {
		bookService.deleteBookById(bookId);
		return "redirect:/books";		
	}

	
	

}
