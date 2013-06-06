package pl.miasi2013.spring.lab2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.miasi2013.spring.lab2.model.User;


public class UserRepository implements UserRepositoryInterface {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public UserRepository() {
	}

	@Override
	public void updateUser(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("update UserU set surname = ?, email = ?, roles = ?, password = ? where id = ?",
				user.getSurname(), user.getEmail(), StringUtils.join(user.getRoles(), ", "),
				user.getPassword());
	}

	@Override
	public int insertUser(final User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement("insert into UserU (usename, surname, email, roles, password) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		            ps.setString(1, user.getUsername());
		            ps.setString(2, user.getSurname());
		            ps.setString(3, user.getEmail());
		            ps.setString(4, StringUtils.join(user.getRoles(), ", "));
		            ps.setString(5, user.getPassword());
		            return ps;
		        }
		    },
		    keyHolder);
		user.setId(keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public User getUserByUsername(String username) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {username};
		List<User> users = jdbcTemplate.query("select * from UserU where username = (?)", parameters, new UserMapper());
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

}
