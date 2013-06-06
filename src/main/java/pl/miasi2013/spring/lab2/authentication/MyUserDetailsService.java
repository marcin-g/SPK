package pl.miasi2013.spring.lab2.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import pl.miasi2013.spring.lab2.dao.UserRepositoryInterface;
import pl.miasi2013.spring.lab2.model.User;

public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepositoryInterface userRepository;

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

}
