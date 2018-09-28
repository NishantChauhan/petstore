package com.petstore.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petstore.user.exceptions.UserNotFoundException;
import com.petstore.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	    @Autowired
	    private UserRepository userRepository;
	 
	    @Override
	    public UserDetails loadUserByUsername(String username) {
	    	com.petstore.user.entities.User dbUser = null;
	    	
	    	try {
	    		dbUser 	= userRepository.findUserByUsername(username);
	    	}
	    	catch(UserNotFoundException unfe) {
	    		throw new UsernameNotFoundException(unfe.getMessage());
	    	}
	    	
//	        return new UserPrincipal(dbUser);
	     return toUserDetails(dbUser);
	    }
	    
	    private UserDetails toUserDetails(com.petstore.user.entities.User user) {
	        return User.withUsername(user.getUsername())
	                   .password(user.getPassword())
	                   .roles(user.getRole()).build();
	    }
	

}


