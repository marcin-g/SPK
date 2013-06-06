package pl.miasi2013.spring.lab2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.miasi2013.spring.lab2.model.relations.Borrow;

public class BorrowMapper implements RowMapper<Borrow> {
	@Override
	public Borrow mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Borrow(rs.getInt("ID"), rs.getInt("BOOK_ID"), rs.getInt("USER_ID"),
				rs.getLong("BEGIN"), rs.getLong("END")) ;
	}
	
}
