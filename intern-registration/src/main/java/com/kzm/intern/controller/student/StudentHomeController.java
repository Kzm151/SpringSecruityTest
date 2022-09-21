package com.kzm.intern.controller.student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student/home")
public class StudentHomeController {

	@GetMapping
	public String index() {
		return "/student/home";
	}

}
