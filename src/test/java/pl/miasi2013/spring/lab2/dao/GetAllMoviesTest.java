package pl.miasi2013.spring.lab2.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.miasi2013.spring.lab2.dao.MovieRepository;
import pl.miasi2013.spring.lab2.model.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-repository-context.xml" })
public class GetAllMoviesTest {
	@Autowired
	private MovieRepository sut;

	@Test
	public void shouldReturnAllMovies() {
		Collection<Movie> movies = sut.getAllMovies();

		assertThat("movies must not be null", movies, notNullValue());
		assertThat("invalid movies size", movies,
				hasSize(greaterThanOrEqualTo(3)));
		assertMoviesContainMovieWithTitle(movies,
				"Bolek i Lolek - Lampa Aladyna");
		assertMoviesContainMovieWithTitle(movies,
				"Bolek i Lolek - W piaskach Gobi");
		assertMoviesContainMovieWithTitle(movies, "Reksio - magik");
	}

	private void assertMoviesContainMovieWithTitle(Collection<Movie> movies,
			String expectedMovieTitle) {
		assertThat(movies, Matchers.<Movie> hasItem(hasProperty("title",
				equalTo(expectedMovieTitle))));
	}

}
