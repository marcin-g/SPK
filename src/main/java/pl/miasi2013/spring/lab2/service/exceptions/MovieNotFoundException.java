package pl.miasi2013.spring.lab2.service.exceptions;

public class MovieNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 243935834495710846L;
	private final long id;
	
	public MovieNotFoundException(long movieId) {
		super("unable to find movie with id" + movieId);
		this.id = movieId;
	}

	public long getId() {
		return id;
	}
	
	
}
