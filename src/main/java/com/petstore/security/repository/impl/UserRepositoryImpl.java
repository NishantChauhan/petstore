package com.petstore.security.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.petstore.security.entities.User;
import com.petstore.security.exceptions.BadPasswordException;
import com.petstore.security.exceptions.UserNotFoundException;
import com.petstore.security.repository.UserRepository;

@Repository
@Transactional
@PropertySource("classpath:application.properties")
public class UserRepositoryImpl implements UserRepository {

	final static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@Autowired(required = true)
	SessionFactory sessionFactory;

	@Override
	public User findUserByUsername(String userName) throws UserNotFoundException {
		logger.debug("findUserByUsername ENTRY");
		
		logger.debug("Username = " + userName);
		User user = null;
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<User> query = session.getNamedQuery("findUserByUsername");
		query.setParameter("username", userName);
		List<User> users = query.getResultList();

		if (users == null || users.size() != 1) {
			throw new UserNotFoundException("User "+ userName + " was not found");
		}
		user = users.get(0);			
		logger.info("Fetched user with username: " + users.get(0).getUsername());
		logger.info("Fetched user with password: " + users.get(0).getPassword());
		
		logger.debug("EXIT");
		
		return user;
	}

	@Override
	public boolean validateUser(String userName, String password) throws UserNotFoundException {
		logger.debug("validateUser ENTRY");
		
		logger.debug("Username = " + userName);
		logger.debug("Password = " + password);
		User user = null;
		Session session = this.sessionFactory.getCurrentSession();

		@SuppressWarnings("unchecked")
		TypedQuery<User> query = session.getNamedQuery("findUserByUsername");
		query.setParameter("username", userName);
		List<User> users = query.getResultList();

		if (users == null || users.size() != 1) {
			throw new UserNotFoundException("User "+ userName + " was not found");
		}
		user = users.get(0);	
		
		logger.info("Fetched user with username: " + user.getUsername());
		logger.info("Fetched user with password: " + user.getPassword());
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		logger.debug("encodedPassword = " + encodedPassword);
		encodedPassword = password;

		if(!encodedPassword.equals(user.getPassword())) {
			throw new BadPasswordException("Invalid Password");
		}
		logger.debug("EXIT");
		
		return true;
	}

}
