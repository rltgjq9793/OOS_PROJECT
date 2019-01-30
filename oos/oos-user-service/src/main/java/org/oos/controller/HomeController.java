package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.oos.domain.Criteria;
import org.oos.domain.Mahout_MemberVO;
import org.oos.domain.MemberVO;
import org.oos.domain.PageDTO;
import org.oos.service.HashTagService;
import org.oos.service.MahoutService;
import org.oos.service.MemberService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {  
	
	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@Setter(onMethod_=@Autowired)
	private HashTagService hashTagService;
	
	@Setter(onMethod_=@Autowired)
	private MemberService memberService;
	
	@Setter(onMethod_=@Autowired)
	private MahoutService mahoutService;

	@PostMapping("/hashTag")
	@ResponseBody
	public List<String> autoComplete() {
		List<String> getName=hashTagService.getName();
		return getName;
	}
	
	@GetMapping("/test")
	public void test() {
	}
	
	@GetMapping("/")
	public String home(){
		return "redirect:/aboutus";
	}

	
	@GetMapping("/aboutus")
	public void aboutus(Model model) {
		
	}
	
	
	@GetMapping("/pay")
	public void pay(Model model){
		
	}
	
	@GetMapping(value= {"/main","/m/main","/m/shopLayout"})
	public void submain(Model model) {
		Map<String, Object> map = new HashMap<>();
		
		String name = SecurityContextHolder.getContext()
							.getAuthentication().getName();
		
		Criteria cri = new Criteria();
		cri.setAmount(24);
		PageDTO pageDTO = new PageDTO(cri, productService.getTotal(map));
		map.put("dto", pageDTO);
		
		model.addAttribute("bestS", storeService.getBestStore());
		model.addAttribute("bestP", productService.bestProductList());
		model.addAttribute("product", productService.getList(map));
		
		List<Mahout_MemberVO> list = mahoutService.getRecList(name);
		
		if(list.size() > 0) {
			model.addAttribute("recommend", list);
		}
		
		List<Integer> pageList = new ArrayList<>();
	    
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        model.addAttribute("cri", cri);   
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
        
	}
	
	@GetMapping("/oos")
	public void main() {
		
	}

}