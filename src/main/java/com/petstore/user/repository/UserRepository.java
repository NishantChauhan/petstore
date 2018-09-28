package com.petstore.user.repository;

import com.petstore.user.entities.User;
import com.petstore.user.exceptions.BadPasswordException;
import com.petstore.user.exceptions.UserNotFoundException;

public interface UserRepository {

	public User findUserByUsername(String userName) throws UserNotFoundException, BadPasswordException;
	public boolean validateUser(String userName,String password) throws UserNotFoundException, BadPasswordException;

}
