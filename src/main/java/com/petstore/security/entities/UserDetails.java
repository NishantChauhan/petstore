package com.petstore.security.entities;

public class UserDetails extends User {

	public UserDetails(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setRole(user.getRole());
	}
}
