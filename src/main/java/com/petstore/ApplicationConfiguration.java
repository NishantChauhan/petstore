package com.petstore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nishant
 * 
 * @see {@link Configuration} 
 * 
 * */

@Configuration
@ComponentScan(basePackages= {"com.petstore.pet"})
public class ApplicationConfiguration {
	

}
