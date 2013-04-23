package pl.miasi2013.spring.lab2.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.miasi2013.spring.lab2.dao.BookRepositoryInterface;
import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.service.exceptions.BookNotFoundException;

@Service
public class BookService {
	@Autowired
	private BookRepositoryInterface bookRepository;

	public Collection<Book> getAllBooks() {
		return bookRepository.getAllBooks();
	}

	public void insertBook(Book book) {
		bookRepository.insertBook(book);
	}

	public Book getBookById(long bookId) {
		Book book = bookRepository.getBookById(bookId);
		if (book == null) {
			throw new BookNotFoundException(bookId);
		}
		return book;
	}

	public void updateBook(Book book) {
		Book bookToUpdate = bookRepository.getBookById(book.getId());
		if (book == null) {
			throw new BookNotFoundException(book.getId());
		}
		bookToUpdate.setBookURL(book.getBookURL());
		bookToUpdate.setISBN(book.getISBN());
		bookToUpdate.setPublisher(book.getPublisher());
		bookToUpdate.setReviewURL(book.getReviewURL());
		bookToUpdate.setState(book.getState());
		bookToUpdate.setTitle(book.getTitle());
		bookToUpdate.setYear(book.getYear());
	}

	public void deleteBookById(long bookId) {
		Book book=this.getBookById(bookId);
		if(book!=null){
			bookRepository.deleteBook(book);
		}
		
	}

}
