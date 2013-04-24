package pl.miasi2013.spring.lab2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import pl.miasi2013.spring.lab2.model.Movie;

public class FixedMovieRepository implements MovieRepository{

	private DataSource dataSource;
	
	private static List<Movie> result = new LinkedList<Movie>();;
	static {
		result.add(new Movie(1L,"Reksio - magik"));
		result.add(new Movie(2L,"Bolek i Lolek - W piaskach Gobi"));
		result.add(new Movie(3L,"Bolek i Lolek - Lampa Aladyna"));
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Collection<Movie> getAllMovies() {
		//return result;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select * from Movie", new RowMapper<Movie>(){
			public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
				String movieTitle = rs.getString("TITLE");
				long movieId = rs.getLong("ID");
				return new Movie(movieId, movieTitle);
			}
		});
	}

	@Override
	public void addMovie(Movie movie) {
		result.add(movie);
	}

	@Override
	public Movie getMovieById(long id) {
		for (Movie movie : result) {
			if(movie.getId().equals(id)){
				return movie;
			}
		}
		return null;
	}

	@Override
	public void deleteMovie(long movieId) {
		Movie movieById = getMovieById(movieId);
		if(movieById != null){
			result.remove(movieById);
		}
	}
}
