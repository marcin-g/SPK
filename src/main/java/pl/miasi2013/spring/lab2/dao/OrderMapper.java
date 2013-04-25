package pl.miasi2013.spring.lab2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.miasi2013.spring.lab2.model.relations.Order;

public class OrderMapper implements RowMapper<Order> {
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Order(rs.getInt("ID"), rs.getInt("BOOK_ID"), rs.getInt("USER_ID"),
				rs.getLong("TIME"), rs.getString("BOOK_URL"));
	}
	
}
