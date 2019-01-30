package org.oos.controller;

import java.security.Principal;

import org.oos.domain.SellerVO;
import org.oos.domain.StoreVO;
import org.oos.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/sellerInfo/*")
public class SellerInfoController {
	
	@Setter(onMethod_=@Autowired)
	private SellerService service;
	
	@GetMapping("/read")
	public void sellerInfo(Principal pal, Model model) {
		model.addAttribute("sellerInfo", service.getBySid(pal.getName()));
	} 
	
	@GetMapping("/modify")
	@PreAuthorize("isAuthenticated()")
	public void sellerModify(Principal principal, Model model) {

		SellerVO vo=service.getBySid(principal.getName());
		model.addAttribute("sellerInfo",vo);
	}
	
	@PostMapping("/modify")
	@PreAuthorize("principal.username==#vo.sid")
	public String storeModifyPost(SellerVO vo) {
		service.modify(vo);
		return "redirect:/read";
	}
}