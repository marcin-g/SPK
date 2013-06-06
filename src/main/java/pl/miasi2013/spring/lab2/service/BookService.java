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
import pl.miasi2013.spring.lab2.model.User;
import pl.miasi2013.spring.lab2.model.relations.Borrow;
import pl.miasi2013.spring.lab2.model.relations.Order;
import pl.miasi2013.spring.lab2.model.relations.Queue;
import pl.miasi2013.spring.lab2.service.exceptions.BookNotFoundException;

@Service
public class BookService {
	@Autowired
	private BookRepositoryInterface bookRepository;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private QueueService queueService;
	@Autowired
	private BorrowService borrowService;

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
	@Transactional
	public void setBookState(long bookId,BookState state){
		Book book=this.getBookById(bookId);
		book.setState(state);
		this.updateBook(book);
	}

	@Transactional
	public void updateBookReview(Book book) {
		Book original=getBookById(book.getId());
		original.setReviewURL(book.getReviewURL());
		updateBook(original);
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

	public int getBookStatus(long bookId) {
		int returnValue=-1;
		try{
			Book book=getBookById(bookId);
			if(book.getState()==BookState.AVAILABLE){
				return 0;
			}
			else{
				Borrow borrow=borrowService.getActualBorrowByBook(bookId);
				User user=userService.getLoggedUser();
				if(borrow!=null && user.getId()==borrow.getUserId()){
					return 1;
				}
				else{
					if(queueService.isQueueByUser(book.getId(), user.getId())){
						return 2;
					}
					else{
						return 3;
					}
				}
			}
			
		}
		catch(BookNotFoundException e){
			returnValue=-1;
		}
		return returnValue;
	}

	public void updateBookBorrow(long bookId) {
		Book book=getBookById(bookId);
		if(book.getState()==BookState.BORROWED){
			book.setState(BookState.AVAILABLE);
		}
		else{
			book.setState(BookState.BORROWED);
		}
	}

	public void updateBookQueue(long bookId) {
		long userId=userService.getLoggedUser().getId();
		if(queueService.isQueueByUser(bookId, userId)){
			Queue queue=queueService.getQueueByUserIdAndBookId(userId,bookId);
			queueService.deleteQueueById(queue.getId());
		}
		else{
			Queue queue=new Queue();
			queue.setBookId(bookId);
			queue.setUserId(userService.getLoggedUser().getId());
			queueService.insertQueue(queue);
		}
		
	}

}
