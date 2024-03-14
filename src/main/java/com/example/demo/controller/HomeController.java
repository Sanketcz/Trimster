package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.User;
import com.example.demo.helper.Message;
import com.example.demo.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@RequestMapping("/home")
	public String home() {
		
		return "home";
	}
	//Custom Login
	@GetMapping("/signin")
	public String customLogin(Model model) {
		
		return "signin";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		System.out.println("aaaa");
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	@RequestMapping(value="/process_signup", method = RequestMethod.POST)
	public String processSignUp(@Valid @ModelAttribute("user") User user,BindingResult result,Model model,HttpSession session) {
		
		if(result.hasErrors()){
			model.addAttribute("user", user);
			return "signup";
		}
		
		try {
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userService.saveUser(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully Registered...!", "alert-success"));
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Somthing went Wromg...!", "alert-danger"));
			return "signup";
		}
	}
	
	
	
	
	
}
