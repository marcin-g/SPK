package pl.miasi2013.spring.lab2.service.exceptions;

public class QueueNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5362979319227576224L;
	long id;
	public QueueNotFoundException(long queueId) {
		super("unable to find queue with id" + queueId);
		this.id = queueId;
	}

	public long getId() {
		return id;
	}
}
