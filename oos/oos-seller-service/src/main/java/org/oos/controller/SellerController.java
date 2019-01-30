package org.oos.controller;

import javax.transaction.Transactional;

import org.oos.domain.AuthDTO;
import org.oos.domain.MemberVO;
import org.oos.domain.SellerVO;
import org.oos.persistence.SellerRepository;
import org.oos.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/seller/*")
public class SellerController {

	@Autowired
	PasswordEncoder pwEncoder;
	@Autowired
	SellerRepository repo;
	@Setter(onMethod_=@Autowired)
	private SellerService sellerService;
	
	@GetMapping("/login")
	public void login() {
		
	};
	
	@GetMapping("/signUp")
	public void getRegister(Model model) {
		
		
		
	}
	
	@Transactional
	@PostMapping("/signUp")
	public String postRegister(@ModelAttribute("seller") SellerVO vo) {
		
		
		String pw = pwEncoder.encode(vo.getSpw());
		vo.setSpw(pw);
		
		repo.save(vo);
		return "redirect:/seller/login";
	}
	
}
