package com.kzm.intern.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kzm.intern.entity.Account;
import com.kzm.intern.entity.Account.Role;
import com.kzm.intern.repo.AccountRepo;

@Controller
@RequestMapping("/sign-up")
public class SignUpController {
	
	@Autowired
	private AccountRepo repo;
	
	@GetMapping
	public String index() {
		return "/sign-up";
	}
	@PostMapping
	public String singUp(@RequestParam String name,@RequestParam String email,@RequestParam String pwd,HttpServletRequest req) {
		Account ac = new Account();
		ac.setName(name);
		ac.setEmail(email);
		ac.setPassword(new BCryptPasswordEncoder().encode(pwd));
		ac.setRole(Role.Student);
		repo.save(ac);
		try {
			req.login(email, pwd);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/home";
	}
}
