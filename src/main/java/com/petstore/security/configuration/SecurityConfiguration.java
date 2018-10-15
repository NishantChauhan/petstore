package com.petstore.security.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.petstore.security.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(1)
@Order(SecurityProperties.BASIC_AUTH_ORDER - 2)
  //. Required to make this priority over the Spring Security Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
   
    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder);
    }
    
    @Override
    public void configure( WebSecurity web ) throws Exception {
        web.ignoring().antMatchers( "/favicon.ico","/error","/resources/**","/","/api/**/{[path:[^\\\\.]*}","/main*","/runtime*","/polyfills*","/styles*","/photoURL/**/","/assets/**/*");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
        .authorizeRequests()
        .antMatchers("/error","/favicon.ico","/api/**/{[path:[^\\\\.]*}","/","/main*","/runtime*","/polyfills*","/styles*","/photoURL/**/*","/assets/**/*").permitAll()//"/**/{[path:[^\\\\.]*}",
//        .and().formLogin()
//        .loginPage("/api/logon")
//        .and()
//        .userDetailsService(customUserDetailsService)
        ;        
    }
}