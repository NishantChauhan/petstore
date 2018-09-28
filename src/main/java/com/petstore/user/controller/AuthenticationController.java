package com.petstore.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.user.entities.ExecutionStatus;
import com.petstore.user.entities.ExecutionStatus.StatusEnum;
import com.petstore.user.repository.UserRepository;

@RestController
public class AuthenticationController {

	@Autowired
	UserRepository userRepository;
	
	final static  Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@RequestMapping(path = "/logon/success", method = { RequestMethod.POST }, produces = { "application/json" })
	ExecutionStatus success(@RequestParam String username,@RequestParam String password) throws NumberFormatException, Exception {
		return new ExecutionStatus(StatusEnum.OK);
	}
	@RequestMapping(path = "/logon/failure", method = { RequestMethod.POST }, produces = { "application/json" })
	ExecutionStatus failure(@RequestParam String username,@RequestParam String password) throws NumberFormatException, Exception {
		return new ExecutionStatus(StatusEnum.BAD_LOGIN);
	}
	
}
