package pl.miasi2013.spring.lab2.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{
	private final String username;
	private final Collection<String> roles;
	private final String password;
	
	public MyUserDetails(String username, Collection<String> roles,
			String password) {
		this.username = username;
		this.roles = roles;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();  
		for (String roleName : roles) {
			result.add(new SimpleGrantedAuthority(roleName));
		}
		return result;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



}
