package pl.miasi2013.spring.lab2.dao;

import java.util.Collection;

import pl.miasi2013.spring.lab2.model.Book;

public interface BookRepositoryInterface {
	public int insertBook(Book book);

	public Collection<Book> getAllBooks();

	public Book getBookById(long bookId);

	public void deleteBook(Book book);

	public void updateBook(Book book);

}
