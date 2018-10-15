package com.petstore.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.security.entities.ExecutionStatus;
import com.petstore.security.entities.ExecutionStatus.StatusEnum;
import com.petstore.security.entities.User;
import com.petstore.security.entities.UserDetails;
import com.petstore.security.repository.UserRepository;

@RestController
public class AuthenticationController {

	@Autowired
	UserRepository userRepository;

	final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@RequestMapping(path = { "/user/{username}" }, method = { RequestMethod.GET }, produces = { "application/json" })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	User user(@PathVariable String username) throws NumberFormatException, Exception {
		logger.debug("AuthenticationController for "+username);
		UserDetails user =  new UserDetails(userRepository.findUserByUsername(username));
		return user;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ExecutionStatus logout(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		logger.debug("AuthenticationController LOGOUT ");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && (logout != null && !"".equals(logout))) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return new ExecutionStatus(StatusEnum.LOGOUT);
	}
}
