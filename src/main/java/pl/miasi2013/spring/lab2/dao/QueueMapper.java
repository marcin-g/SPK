package pl.miasi2013.spring.lab2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.miasi2013.spring.lab2.model.relations.Queue;

public class QueueMapper implements RowMapper<Queue> {
	@Override
	public Queue mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Queue(rs.getInt("ID"), rs.getInt("BOOK_ID"), rs.getInt("USER_ID")) ;
	}
	
}
