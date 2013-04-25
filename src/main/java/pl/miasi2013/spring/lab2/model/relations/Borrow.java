package pl.miasi2013.spring.lab2.model.relations;

public class Borrow {
	private long id;
	private long bookId;
	private long userId;
	private long begin;
	private long end;

	public Borrow() {
	}

	public Borrow(long id, long bookId, long userId, long begin, long end) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.begin = begin;
		this.end = end;
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

	public long getBegin() {
		return begin;
	}

	public void setBegin(long begin) {
		this.begin = begin;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

}
