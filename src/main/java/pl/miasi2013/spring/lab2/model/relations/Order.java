package pl.miasi2013.spring.lab2.model.relations;

public class Order {
	private long id;
	private long bookId;
	private long userId;
	private long time;
	private String bookURL;

	public Order() {
	};

	public Order(long id, long bookId, long userId, long time, String bookURL) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.time = time;
		this.bookURL = bookURL;
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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getBookURL() {
		return bookURL;
	}

	public void setBookURL(String bookURL) {
		this.bookURL = bookURL;
	}

}
