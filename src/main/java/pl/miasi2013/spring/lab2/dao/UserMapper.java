package pl.miasi2013.spring.lab2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.jdbc.core.RowMapper;

import pl.miasi2013.spring.lab2.model.User;

public class UserMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		Collection<String> roles = Arrays.asList(rs.getString("ROLES").split(","));
		return new User(rs.getInt("ID"), rs.getString("USERNAME"), rs.getString("FIRSTNAME"),
				rs.getString("LASTNAME"), rs.getString("EMAIL"), roles, rs.getString("PASSWORD")) ;
	}
	
}
