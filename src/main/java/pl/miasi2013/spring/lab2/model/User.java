package pl.miasi2013.spring.lab2.model;

public class User {
	private long id;
	private String name;
	private String surname;
	private UserType type;
	private String email;

	public enum UserType {

	}

	public User() {
	}

	public User(long id,String name, String surname, UserType type, String email) {
		super();
		this.setId(id);
		this.name = name;
		this.surname = surname;
		this.type = type;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

}
