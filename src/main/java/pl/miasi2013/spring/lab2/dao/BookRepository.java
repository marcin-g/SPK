package pl.miasi2013.spring.lab2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.Book.BookState;

class BookMapper implements RowMapper<Book> {
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("ISBN"),
				rs.getInt("year"), rs.getString("PUBLISHER"), rs.getString("REVIEW_URL"),
				rs.getString("BOOK_URL"), BookState.valueOf(rs.getString("STATE"))) ;
	}
	
}

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
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select * from Book", new BookMapper());
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
