package com.petstore.security.repository;

import com.petstore.security.entities.User;
import com.petstore.security.exceptions.BadPasswordException;
import com.petstore.security.exceptions.UserNotFoundException;

public interface UserRepository {

	public User findUserByUsername(String userName) throws UserNotFoundException, BadPasswordException;
	public boolean validateUser(String userName,String password) throws UserNotFoundException, BadPasswordException;

}
