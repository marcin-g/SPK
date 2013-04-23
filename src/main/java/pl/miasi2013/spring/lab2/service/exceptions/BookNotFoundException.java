package pl.miasi2013.spring.lab2.service.exceptions;

public class BookNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 2520949990299826725L;
private final long id;
	
	public BookNotFoundException(long bookId) {
		super("unable to find book with id" + bookId);
		this.id = bookId;
	}

	public long getId() {
		return id;
	}
}
