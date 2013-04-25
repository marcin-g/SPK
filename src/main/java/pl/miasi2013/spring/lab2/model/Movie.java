package pl.miasi2013.spring.lab2.model;

public class Movie {
	private Long id;
	private String title;

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Movie(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Movie() {
	}

}
