package pl.miasi2013.spring.lab2.service.exceptions;

public class BorrowNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619474521872333123L;
	long id;
	public BorrowNotFoundException(long borrowId) {
		super("unable to find borrow with id" + borrowId);
		this.id = borrowId;
	}

	public long getId() {
		return id;
	}

}
