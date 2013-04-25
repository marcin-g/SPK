package pl.miasi2013.spring.lab2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.Book.BookState;

public class BookMapper implements RowMapper<Book> {
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("ISBN"),
				rs.getInt("year"), rs.getString("PUBLISHER"), rs.getString("REVIEW_URL"),
				rs.getString("BOOK_URL"), rs.getString("author"),
				BookState.valueOf(rs.getString("STATE"))) ;
	}
	
}
