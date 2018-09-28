package com.petstore.user.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.petstore.user.services.CustomUserDetailsService;

/**
 * @author Nishant
 * 
 * @see {@link Configuration}
 * 
 */
@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	final static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Autowired
	CustomUserDetailsService userDetailsService;

	private AuthenticationProvider authenticationProvider;
	
	
	/**
	 * 
	 * @return
	 */
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);	    
	    authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
	    return authProvider;
	}

	/**
	 * 
	 * @param authenticationProvider
	 */
	@Autowired
	@Qualifier("daoAuthenticationProvider")
	public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
		this.authenticationProvider=authenticationProvider;
	}
	
	/**
	 * 
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(authenticationProvider);
	}


	/**
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("configure HttpSecurity ENTRY");
		
		// Cross-Site Request Forgery - Disabled
		http.csrf().disable();
		
		// authorizeRequests with AntMatchers specifies the access to the URLs matches by pattern
		// loginPage --> redirects to the page if login is required
		// formlogin --> specify that form based login will be used
		// loginProcessingUrl --> URL to validate credentials
		http.authorizeRequests()
		.and().formLogin().loginPage("/logon").loginProcessingUrl("/login")
		// successForwardUrl --> URL to forward once authentication is successful
		// failureForwardUrl --> URL to forward if authentication fails
		.successForwardUrl("/logon/success").failureForwardUrl("/logon/failure")
		// logout --> provide logout support
		// logoutURL --> URL that will trigger logout
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccess");		
		//hasRole --> specifying ROLE required for accessing the matched URL
		http.authorizeRequests().antMatchers("/assets/**","/pet/**","/search*/**","/fetch*/**","/find*/**","/photoURL/**").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/add/**","/edit/**").hasRole("ADMIN");
		
		
		http.headers().frameOptions().disable();
	}

	/**
	 * 
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		logger.debug("configure WebSecurity ENTRY");
//		web.ignoring().antMatchers("");
	}

}
