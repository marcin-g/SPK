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

import pl.miasi2013.spring.lab2.model.relations.Borrow;
import pl.miasi2013.spring.lab2.model.relations.Queue;

public class QueueRepository implements QueueRepositoryInterface {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insertQueue(final Queue queue) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement("insert into Queue (book_id, user_id) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
		            ps.setLong(1, queue.getBookId());
		            ps.setLong(2, queue.getUserId());
		            return ps;
		        }
		    },
		    keyHolder);
		queue.setId(keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public Queue getQueueById(long queueId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {queueId};
		List<Queue> queues = jdbcTemplate.query("select * from Queue where id = (?)", parameters, new QueueMapper());
		if (queues.isEmpty()) {
			return null;
		}
		return queues.get(0);
	}

	@Override
	public void updateQueue(Queue queue) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("update Queue set book_id = ?, user_id = ? where id = ?",
				queue.getBookId(), queue.getUserId(), queue.getId());
	}

	@Override
	public void deleteQueue(Queue queue) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("delete from Queue where id = ?", queue.getId());
	}

	@Override
	public Collection<Queue> getAllQueues() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select * from Queue", new QueueMapper());
	}

	@Override
	public Collection<Queue> getQueuesByBookId(long bookId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {bookId};
		return jdbcTemplate.query("select * from Queue where book_id = (?)", parameters, new QueueMapper());
	}

}
