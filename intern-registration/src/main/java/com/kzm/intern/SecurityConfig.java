package com.kzm.intern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kzm.intern.service.InternUserDetailsService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private InternUserDetailsService userdetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/login","/sign-in","/sign-up","resources/**").permitAll()
		.antMatchers("/admin/**").hasRole("Admin")
		.antMatchers("/student/**").hasAnyRole("Admin","Student")
		.anyRequest().authenticated()
		
	.and()
		.formLogin()
		.loginPage("/sign-in")
		.loginProcessingUrl("/login")
		.usernameParameter("email")
		.passwordParameter("pwd")
		.defaultSuccessUrl("/home",true)
	.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true);
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
