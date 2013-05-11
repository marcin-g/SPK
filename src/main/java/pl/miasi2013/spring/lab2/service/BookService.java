package pl.miasi2013.spring.lab2.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import pl.miasi2013.spring.lab2.dao.BookRepositoryInterface;
import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.Book.BookState;
import pl.miasi2013.spring.lab2.model.relations.Order;
import pl.miasi2013.spring.lab2.service.exceptions.BookNotFoundException;

@Service
public class BookService {
	@Autowired
	private BookRepositoryInterface bookRepository;
	@Autowired
	private OrderService orderService;

	public Collection<Book> getAllBooks() {
		return bookRepository.getAllBooks();
	}

	@Transactional
	public long insertBook(Book book) {
		return bookRepository.insertBook(book);
	}

	public Book getBookById(long bookId) {
		Book book = bookRepository.getBookById(bookId);
		if (book == null) {
			throw new BookNotFoundException(bookId);
		}
		return book;
	}

	@Transactional
	public void updateBook(Book book) {
		bookRepository.updateBook(book);
	}

	@Transactional
	public void deleteBookById(long bookId) {
		Book book=this.getBookById(bookId);
		if(book==null){
			throw new BookNotFoundException(bookId);
		}
		else{
			bookRepository.deleteBook(book);
		}
		
	}
	
	public Collection<Book> getAllVisibleBooks() {
		return bookRepository.getAllVisibleBooks();
	}

	@Transactional
	public void setBookStateByOrder(long orderId,BookState state){
		Order order=orderService.getOrderById(orderId);
		Book book=this.getBookById(order.getBookId());
		book.setState(state);
		this.updateBook(book);
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
