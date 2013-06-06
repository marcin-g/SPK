package pl.miasi2013.spring.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.miasi2013.spring.lab2.dao.UserRepositoryInterface;
import pl.miasi2013.spring.lab2.model.User;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepositoryInterface userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	public long addUser(User user) {
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
		return userRepository.insertUser(user);
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("User not found");
		}
		return user;
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

}
