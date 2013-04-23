package pl.miasi2013.spring.lab2.dao;

import java.util.Collection;

import javax.sql.DataSource;

import org.apache.commons.lang.NotImplementedException;

import pl.miasi2013.spring.lab2.model.Book;

public class BookRepository implements BookRepositoryInterface {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public BookRepository() {
	}

	@Override
	public void insertBook(Book book) {
		throw new NotImplementedException();
	}

	@Override
	public Collection<Book> getAllBooks() {
		throw new NotImplementedException();
	}

	@Override
	public Book getBookById(long bookId) {
		throw new NotImplementedException();
	}

	@Override
	public void deleteBook(Book book) {
		throw new NotImplementedException();
	}

	@Override
	public void updateBook(Book book) {
		throw new NotImplementedException();
	}

}
