package pl.miasi2013.spring.lab2.service.exceptions;

public class OrderNotFoundException extends RuntimeException {
	private final long id;
	
	public OrderNotFoundException(long orderId) {
		super("unable to find order with id" + orderId);
		this.id = orderId;
	}

	public long getId() {
		return id;
	}
	
	
}
