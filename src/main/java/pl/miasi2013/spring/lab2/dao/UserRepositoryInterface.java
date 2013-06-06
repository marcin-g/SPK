package pl.miasi2013.spring.lab2.dao;

import pl.miasi2013.spring.lab2.model.User;

public interface UserRepositoryInterface {
	public int insertUser(User user);
	public User getUserByUsername(String username);
	public void updateUser(User user);
}
