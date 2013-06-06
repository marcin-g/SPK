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

public class BorrowRepository implements BorrowRepositoryInterface {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insertBorrow(final Borrow borrow) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement("insert into Borrow (book_id, user_id, begin, end) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		            ps.setLong(1, borrow.getBookId());
		            ps.setLong(2, borrow.getUserId());
		            ps.setLong(3, borrow.getBegin());
		            ps.setLong(4, borrow.getEnd());
		            return ps;
		        }
		    },
		    keyHolder);
		borrow.setId(keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public Borrow getBorrowById(long borrowId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {borrowId};
		List<Borrow> borrows = jdbcTemplate.query("select * from Borrow where id = (?)", parameters, new BorrowMapper());
		if (borrows.isEmpty()) {
			return null;
		}
		return borrows.get(0);
	}

	@Override
	public void updateBorrow(Borrow borrow) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("update Borrow set book_id = ?, user_id = ?, begin = ?, end = ? where id = ?",
				borrow.getBookId(), borrow.getUserId(), borrow.getBegin(), borrow.getEnd(), borrow.getId());
	}

	@Override
	public void deleteBorrow(Borrow borrow) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("delete from Borrow where id = ?", borrow.getId());
	}

	@Override
	public Collection<Borrow> getAllBorrows() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select * from Borrow", new BorrowMapper());
	}

	@Override
	public Collection<Borrow> getUserBorrows(long userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {userId};
		return jdbcTemplate.query("select * from Borrow where user_id = (?)", parameters, new BorrowMapper());
	}

	@Override
	public Collection<Borrow> getBorrowsByBookId(long bookId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {bookId};
		return jdbcTemplate.query("select * from Borrow where book_id = (?)", parameters, new BorrowMapper());
	}

	@Override
	public Borrow getActualBorrowByBook(long bookId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] parameters = {bookId};
		List<Borrow> borrows = jdbcTemplate.query("select * from Borrow where end is null and book_id = (?)", parameters, new BorrowMapper());
		if (borrows.isEmpty()) {
			return null;
		}
		return borrows.get(0);
	}

}
