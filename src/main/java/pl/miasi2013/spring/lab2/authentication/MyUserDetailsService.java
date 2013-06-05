package pl.miasi2013.spring.lab2.authentication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
	private static Map<String, MyUserDetails> users = new HashMap<String, MyUserDetails>(); 
	static{
		addUser("user", Arrays.asList("ROLE_USER"));
		addUser("user2", Arrays.asList("ROLE_USER"));
		addUser("admin", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
	}
	
	private static void addUser(String userName, List<String> roleNames) {
		MyUserDetails myUserDetails = new MyUserDetails(userName,roleNames , "newpass");
		users.put(myUserDetails.getUsername(), myUserDetails);
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		MyUserDetails userDetails = users.get(username);
		if(userDetails == null){
			throw new UsernameNotFoundException("User not found");
		}
		return userDetails;
	}

}
