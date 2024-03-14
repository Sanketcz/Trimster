package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/barber")
public class BarberController {
	
	@RequestMapping("/addBarber")
	public String getAddBarber() {
		
		return "/barbers/addBarber";
	}
}
