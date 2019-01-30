package org.oos.controller;
import java.security.Principal;

import org.oos.domain.StoreVO;
import org.oos.service.HashTagService;
import org.oos.service.SellerService;
import org.oos.service.StoreService;
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
@RequestMapping("/store/*")
public class StoreController {
	
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;

	@Setter(onMethod_=@Autowired)
	private SellerService sellerService;
	
	@Setter(onMethod_=@Autowired)
	private HashTagService hashTagService;
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String storeRegister(Principal principal, Model model) {
		
		String sid=principal.getName();
		if (sellerService.get(sid).getSno() != null) {
			return "redirect:/store/read";
		}
		model.addAttribute("owner",sid );
		model.addAttribute("hashTagList",hashTagService.getList());
		return "/store/register";
	}
	
	@PostMapping("/register")
	@PreAuthorize("principal.username==#vo.owner")
	public String storeRegisterPost(StoreVO vo) {
		
		storeService.register(vo);
		return "redirect:/main";
	}
	
	@GetMapping("/read")
	@PreAuthorize("isAuthenticated()")
	public void storeRead(Principal principal, Model model) {
		
		StoreVO vo=storeService.getBySid(principal.getName());
		model.addAttribute("store",vo);
	}
	
	@GetMapping("/modify")
	@PreAuthorize("isAuthenticated()")
	public void storeModify(Principal principal, Model model) {

		StoreVO vo=storeService.getBySid(principal.getName());
		model.addAttribute("store",vo);
		model.addAttribute("hashTagList",hashTagService.getList());
	}
	
	@PostMapping("/modify")
	@PreAuthorize("principal.username==#vo.owner")
	public String storeModifyPost(StoreVO vo) {
		storeService.modify(vo);
		return "redirect:/store/read";
	}
	
	

}