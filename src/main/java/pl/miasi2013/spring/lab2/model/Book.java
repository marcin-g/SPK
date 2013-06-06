package pl.miasi2013.spring.lab2.model;

public class Book {
	private long id;
	private String title;
	private String ISBN;
	private int year;
	private String publisher;
	private String reviewURL;
	private String bookURL;
	private String author;
	private BookState state;

	public enum BookState {
		REPORTED, REVIEWED, AVAILABLE, BORROWED, UNAVAILABLE, AWAITING_RETURN, AWAITING_RECEPTION
	}
//	BOOK_STATUSES = (‘realizacja zamówienia’, ‘u recenzenta’, ‘na półce’, ‘wypożyczona’, ‘niedostępna’, ‘oczekiwanie na zwrot’, ‘oczekiwanie na odbiór’)

	public Book() {
	}

	public Book(long id, String title, String iSBN, int year, String publisher,
			String reviewURL, String bookURL, String author, BookState state) {
		super();
		this.id = id;
		this.title = title;
		ISBN = iSBN;
		this.year = year;
		this.publisher = publisher;
		this.reviewURL = reviewURL;
		this.bookURL = bookURL;
		this.author = author;
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getReviewURL() {
		return reviewURL;
	}

	public void setReviewURL(String reviewURL) {
		this.reviewURL = reviewURL;
	}

	public String getBookURL() {
		return bookURL;
	}

	public void setBookURL(String bookURL) {
		this.bookURL = bookURL;
	}

	public BookState getState() {
		return state;
	}

	public void setState(BookState state) {
		this.state = state;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}
