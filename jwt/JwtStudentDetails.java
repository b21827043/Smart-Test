package com.brsanlr.brsanlr.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.brsanlr.brsanlr.entities.User;

public class JwtStudentDetails implements UserDetails {

	private static final long serialVersionUID = -5477625305490955489L;
	public Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
    private JwtStudentDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtStudentDetails create(User user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("student"));
        return new JwtStudentDetails(user.getId(), user.getEmail(), user.getPassword(), authoritiesList);
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	
	
}
