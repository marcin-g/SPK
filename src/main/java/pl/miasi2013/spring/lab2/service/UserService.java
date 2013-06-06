package pl.miasi2013.spring.lab2.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import pl.miasi2013.spring.lab2.dao.UserRepositoryInterface;
import pl.miasi2013.spring.lab2.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepositoryInterface userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	public long addUser(User user) {
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
		return userRepository.insertUser(user);
	}
	
	
	@Transactional
	public User getUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

	@Transactional
	public User getLoggedUser(){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName(); 
		User user=getUserByUsername(name);
		
		return user;
	}

	@Transactional
	public Collection<User> getAdmins() {
		return userRepository.getAdmins();
	}


	@Transactional
	public Collection<User> getAllUsers() {
		return userRepository.getAllUsers();
	}


	@Transactional
	public void insertUser(User user) {
		userRepository.insertUser(user);
	}


	public static boolean isUserValid(User user, BindingResult result) {
		return true;
	}


	@Transactional
	public User getUserById(long userId) {
		return userRepository.getUserById(userId);
	}


	@Transactional
	public void updateUser(User user) {
		userRepository.updateUser(user);
		
	}

}
