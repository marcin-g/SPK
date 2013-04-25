package pl.miasi2013.spring.lab2.dao;

import java.util.Collection;

import pl.miasi2013.spring.lab2.model.Movie;

public interface MovieRepository {

	Collection<Movie> getAllMovies();

	void addMovie(Movie movie);

	Movie getMovieById(long id);

	void deleteMovie(long movieId);

}
