package pl.miasi2013.spring.lab2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.Book.BookState;
import pl.miasi2013.spring.lab2.model.relations.Order;

class OrderMapper implements RowMapper<Order> {
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Order(rs.getInt("ID"), rs.getInt("BOOK_ID"), rs.getInt("UER_ID"),
				rs.getInt("TIME"), rs.getString("BOOK_URL"));
	}
	
}

public class OrderRepository implements OrderRepositoryInterface {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public OrderRepository() {
	}

	@Override
	public void insertOrder(Order order) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("insert into OrderO (book_id, user_id, time, book_url) values (?, ?, ?, ?)",
				order.getBookId(), order.getUserId(), order.getTime(), order.getBookURL());
	}

	@Override
	public void updateOrder(Order order) {
		throw new NotImplementedException();
	}

	@Override
	public Order getOrderById(long orderId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {orderId};
		List<Order> orders = jdbcTemplate.query("select * from ORderO where id = (?)", parameters, new OrderMapper());
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

}
