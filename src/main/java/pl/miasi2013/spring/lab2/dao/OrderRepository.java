package pl.miasi2013.spring.lab2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.miasi2013.spring.lab2.model.relations.Order;
import pl.miasi2013.spring.lab2.service.SimpleMailService;

public class OrderRepository implements OrderRepositoryInterface {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public OrderRepository() {
	}

	@Override
	public int insertOrder(final Order order) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement("insert into OrderO (book_id, user_id, time, book_url) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		            ps.setLong(1, order.getBookId());
		            ps.setLong(2, order.getUserId());
		            ps.setLong(3, order.getTime());
		            ps.setString(4, order.getBookURL());
		            return ps;
		        }
		    },
		    keyHolder);
		order.setId(keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.update("insert into OrderO (book_id, user_id, time, book_url) values (?, ?, ?, ?)",
//				order.getBookId(), order.getUserId(), order.getTime(), order.getBookURL());
	}

	@Override
	public void updateOrder(Order order) {
		throw new NotImplementedException();
	}

	@Override
	public Order getOrderById(long orderId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {orderId};
		List<Order> orders = jdbcTemplate.query("select * from OrderO where id = (?)", parameters, new OrderMapper());
		if (orders.isEmpty()) {
			return null;
		}
		return orders.get(0);
	}

	@Override
	public void deleteOrder(Order order) {
		throw new NotImplementedException();
	}

	@Override
	public Collection<Order> getAllOrders() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select * from OrderO", new OrderMapper());
	}
	
	@Override
	public Collection<Order> getAllReportedOrders() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select * from OrderO o join Book b on b.id = o.book_id where b.state = 'REPORTED'", new OrderMapper());
	}
	@Override
	public Order getOrderByBookId(long bookId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {bookId};
		List<Order> orders = jdbcTemplate.query("select * from OrderO where book_id = (?)", parameters, new OrderMapper());
		if (orders.isEmpty()) {
			return null;
		}
		return orders.get(0);
	}
	@Override
	public Collection<Order> getOrdersByUserId(long userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {userId};
		return jdbcTemplate.query("select * from OrderO where user_id = (?)", parameters, new OrderMapper());
	}

}
