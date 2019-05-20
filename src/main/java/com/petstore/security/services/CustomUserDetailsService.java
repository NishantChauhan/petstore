package com.petstore.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petstore.security.exceptions.UserNotFoundException;
import com.petstore.security.repository.UserRepository;
import com.petstore.security.repository.impl.UserRepositoryImpl;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	    @Autowired
	    private UserRepository userRepository;

		final static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	    @Override
	    public UserDetails loadUserByUsername(String username) {
	    	com.petstore.security.entities.User dbUser = null;
	    	logger.debug("loadUserByUsername ENTRY");
	    	
	    	try {
	    		dbUser 	= userRepository.findUserByUsername(username);
	    	}
	    	catch(UserNotFoundException unfe) {
	    		throw new UsernameNotFoundException(unfe.getMessage());
	    	}
	    	
	     return toUserDetails(dbUser);
	    }
	    
	    private UserDetails toUserDetails(com.petstore.security.entities.User user) {
	        return User.withUsername(user.getUsername())
	                   .password(user.getPassword())
	                   .roles(user.getRole()).build();
	    }
	

}


