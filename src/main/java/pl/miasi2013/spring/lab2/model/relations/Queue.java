package pl.miasi2013.spring.lab2.model.relations;

public class Queue {
	private long id;
	private long bookId;
	private long userId;

	public Queue() {
	}

	public Queue(long id, long bookId, long userId) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
