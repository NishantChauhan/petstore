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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(1)
@Order(SecurityProperties.BASIC_AUTH_ORDER - 2)
  //. Required to make this priority over the Spring Security Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder encoder;
	
	@Bean
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
//	@Autowired
//    private CustomUserDetailsService customUserDetailsService;
   
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("inside OAuth2SecurityConfiguration : globalUserDetails()");
        auth.inMemoryAuthentication()
        .withUser("a").password(encoder.encode("p")).authorities(new SimpleGrantedAuthority("ADMIN")).roles("ADMIN").and()
        .withUser("u").password(encoder.encode("p")).authorities(new SimpleGrantedAuthority("USER")).roles("USER");
//    	auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
    }
    
    @Override
    public void configure( WebSecurity web ) throws Exception {
        web.ignoring().antMatchers( "/resources/**","/","/api/**/{[path:[^\\\\.]*}","/main*","/runtime*","/polyfills*","/styles*","/photoURL/**/","/assets/**/*");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
        .authorizeRequests()
        .antMatchers("/api/**/{[path:[^\\\\.]*}","/","/main*","/runtime*","/polyfills*","/styles*","/photoURL/**/*","/assets/**/*").permitAll()//"/**/{[path:[^\\\\.]*}",
        .and().formLogin()
        .loginPage("/api/logon")
//        .and()
//        .userDetailsService(customUserDetailsService)
        ;        
    }
}