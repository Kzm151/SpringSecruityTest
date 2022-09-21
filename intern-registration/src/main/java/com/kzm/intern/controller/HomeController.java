package com.kzm.intern.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kzm.intern.entity.Account;
import com.kzm.intern.entity.Account.Role;
import com.kzm.intern.repo.AccountRepo;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private AccountRepo repo;
	
	@GetMapping
	public String index(HttpServletRequest req) {
		String loginId= req.getRemoteUser();
	return repo.findOneByEmail(loginId).map(a->{
		req.getSession(true).setAttribute("LoginUser", a);
		return a.getRole() == Role.Admin? "redirect:/admin/home" : "redirect:/student/home";	
	}).orElseThrow(()-> new  UsernameNotFoundException("Not Sing up"));
		
	}

}
