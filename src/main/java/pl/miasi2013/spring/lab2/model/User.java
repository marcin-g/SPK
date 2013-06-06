package pl.miasi2013.spring.lab2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	private long id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private Collection<String> roles;
	private String password;

	public User() {
	}

	public User(long id,String username, String firstname, String lastname, String email, Collection<String> roles, String password) {
		super();
		this.setId(id);
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.roles = roles;
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<String> getRoles() {
		return roles;
	}

	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role) {
		this.roles.add(role);
	}
	
	public boolean hasRole(String role) {
		return this.roles.contains(role);
	}

	public void setPassword(String password) {
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
