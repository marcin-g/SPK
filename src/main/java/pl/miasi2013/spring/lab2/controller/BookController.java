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
import pl.miasi2013.spring.lab2.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllBooks(Model model) {
		model.addAttribute("books", bookService.getAllVisibleBooks());
		return "booksList";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("book", new Book());
		return "createOrUpdateBookForm";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processAddBook(@ModelAttribute("book") Book book,
			BindingResult result) {
		if (!isBookValid(book,result)) {
			return "createOrUpdateBookForm";
		}
		bookService.insertBook(book);
		return "redirect:/books";
	}
	
	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
	public String initEditBook(@PathVariable("bookId") long bookId, Model model) {
		model.addAttribute("book", bookService.getBookById(bookId));		
		return "createOrUpdateBookForm";
	}
	
	@RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
	public String updateBook(@ModelAttribute("book") Book book,BindingResult result) {
		if (!isBookValid(book,result)) {
			return "createOrUpdateBookForm";
		}
		bookService.updateBook(book);
		return "redirect:/books";		
	}
	
	@RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
	public String deleteBook(@PathVariable("bookId") long bookId) {
		bookService.deleteBookById(bookId);
		return "redirect:/books";		
	}

	public static boolean isBookValid(Book book, BindingResult result) {
		boolean valid=true;
		if(!StringUtils.hasLength(book.getISBN())){
			result.rejectValue("ISBN", "required", "required");
			valid=false;
		}
		if(!StringUtils.hasLength(book.getTitle())){
			result.rejectValue("title", "required", "required");
			valid=false;
		}
		if(!StringUtils.hasLength(book.getPublisher())){
			result.rejectValue("publisher", "required", "required");
			valid=false;
		}
		return valid;
	}
	

}
