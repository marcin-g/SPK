package pl.miasi2013.spring.lab2.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.miasi2013.spring.lab2.dao.MovieRepository;
import pl.miasi2013.spring.lab2.model.Movie;
import pl.miasi2013.spring.lab2.service.exceptions.MovieNotFoundException;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;

	public Collection<Movie> getAllMovies() {
		return movieRepository.getAllMovies();
	}

	public void addMovie(Movie movie) {
		movie.setId(System.currentTimeMillis());
		movieRepository.addMovie(movie);
	}

	public Movie getMovieById(long movieId) {
		Movie movieById = movieRepository.getMovieById(movieId);
		if(movieById == null){
			throw new MovieNotFoundException(movieId);
		}
		return movieById;
		
	}

	public void updateMovie(Movie movie) {
		Movie movieById = movieRepository.getMovieById(movie.getId());
		movieById.setTitle(movie.getTitle());
	}

	public void deleteMovie(long movieId) {
		movieRepository.deleteMovie(movieId);
	}
}
