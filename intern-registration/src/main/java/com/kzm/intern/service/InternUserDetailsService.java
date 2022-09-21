package com.kzm.intern.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.kzm.intern.repo.AccountRepo;

@Service
public class InternUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountRepo accRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return accRepo.findOneByEmail(email).map(a-> new User(a.getEmail(), a.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_".concat(a.getRole().toString()))))).orElseThrow(()-> new UsernameNotFoundException("error"));
	}

}
