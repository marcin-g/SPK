package pl.miasi2013.spring.lab2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.relations.Borrow;
import pl.miasi2013.spring.lab2.model.relations.Order;


public class BookRepository implements BookRepositoryInterface {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public BookRepository() {
	}

	@Override
	public int insertBook(final Book book) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement("insert into Book (title, isbn, year, publisher, review_url, book_url, author, state) values (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		            ps.setString(1, book.getTitle());
		            ps.setString(2, book.getISBN());
		            ps.setInt(3, book.getYear());
		            ps.setString(4, book.getPublisher());
		            ps.setString(5, book.getReviewURL());
		            ps.setString(6, book.getBookURL());
		            ps.setString(7, book.getAuthor());
		            ps.setString(8, book.getState()==null ? "" : book.getState().toString());
		            return ps;
		        }
		    },
		    keyHolder);
		book.setId(keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.update("insert into Book (title, isbn, year, publisher, review_url, book_url, author, state) values (?, ?, ?, ?, ?, ?, ?, ?)",
//				book.getTitle(), book.getISBN(), book.getYear(), book.getPublisher(),
//				book.getReviewURL(), book.getBookURL(), book.getAuthor(), book.getState()==null ? "" : book.getState().toString());
	}

	@Override
	public Collection<Book> getAllBooks() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select * from Book", new BookMapper());
	}
	
	@Override
	public Collection<Book> getAllVisibleBooks() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select * from Book where state != 'REPORTED'", new BookMapper());
	}

	@Override
	public Book getBookById(long bookId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {bookId};
		List<Book> books = jdbcTemplate.query("select * from Book where id = (?)", parameters, new BookMapper());
		if (books.isEmpty()) {
			return null;
		}
		return books.get(0);
	}

	@Override
	public void deleteBook(Book book) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {book.getId()};
		List<Order> orders = jdbcTemplate.query("select * from OrderO where book_id = (?)", parameters, new OrderMapper());
		if (!orders.isEmpty()) {
			Order order = orders.get(0);
			jdbcTemplate.update("delete from OrderO where id = ?", order.getId());
		}
		List<Borrow> borrows = jdbcTemplate.query("select * from Borrow where book_id = (?)", parameters, new BorrowMapper());
		for (Borrow borrow : borrows) {
			jdbcTemplate.update("delete from Borrow where id = ?", borrow.getId());
		}
		jdbcTemplate.update("delete from Book where id = ?", book.getId());
	}

	@Override
	public void updateBook(Book book) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("update Book set title = ?, isbn = ?, year = ?, publisher = ?, review_url = ?, book_url = ?, author = ?, state = ? where id = ?",
				book.getTitle(), book.getISBN(), book.getYear(), book.getPublisher(),
				book.getReviewURL(), book.getBookURL(), book.getAuthor(), book.getState()==null ? "" : book.getState().toString(), book.getId());
	}

}
