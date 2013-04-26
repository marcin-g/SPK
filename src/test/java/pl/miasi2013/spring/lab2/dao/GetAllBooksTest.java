package pl.miasi2013.spring.lab2.dao;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.miasi2013.spring.lab2.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-repository-context.xml", "/spring-context.xml" })
public class GetAllBooksTest {
	@Autowired
	private BookRepository sut;

	@Test
	public void shouldReturnAllMovies() {
		Collection<Book> books = sut.getAllBooks();

		assertThat("books must not be null", books, notNullValue());
		assertThat("invalid movies size", books,
				hasSize(greaterThanOrEqualTo(1)));
	}

}
