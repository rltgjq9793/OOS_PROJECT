package org.oos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/m/*")
public class MobileController {

	@GetMapping("/aboutus")
	public void getMain() {
		
	}
	
	@GetMapping("/test")
	public void test() {
		
	}
	
}
