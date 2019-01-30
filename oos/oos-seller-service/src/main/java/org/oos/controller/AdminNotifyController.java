package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.AdminNotifyVO;
import org.oos.domain.PageDTO;
import org.oos.service.AdminNotifyService;
import org.oos.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/adminNotify/*")
public class AdminNotifyController {

	@Setter(onMethod_= @Autowired)
	private AdminNotifyService service;

	
	@PostMapping("/modify")
	public String sellerModifyPost(AdminNotifyVO vo,Criteria cri, RedirectAttributes rttr){
		
		int result = service.modify(vo);
		
		rttr.addFlashAttribute("result", result ==1? "SUCCESS":"FAIL");
		
		return "redirect:/adminNotify/get?bno="+vo.getBno();
	}
	
	@GetMapping({"/get","/modify"})
	public void get(Long bno, Criteria cri, Model model) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		
		model.addAttribute("notify", service.get(bno));
		model.addAttribute("page", cri);
	}
	
	@GetMapping("/notify")
	public void getList(Model model, Criteria cri) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(cri.getCategory() != null) {
			if(cri.getCategory().equals("t")) {cri.setCategory("title"); }
			else if(cri.getCategory().equals("c")) { cri.setCategory("content"); }
			else if(cri.getCategory().equals("tc")) { cri.setCategory("tc");}
		}
		
		map.put("cri", cri);
		PageDTO pageDTO = new PageDTO(cri, service.count(cri));
		map.put("dto", pageDTO);
				
		model.addAttribute("notify", service.getList(map));
		
		List<Integer> pageList = new ArrayList<>();

		for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
			pageList.add(i);
		}
		
		model.addAttribute("pageList", pageList);
		model.addAttribute("pageMaker", pageDTO);

	}
	
    @PostMapping("/remove")
    public String remove(Long[] bno, RedirectAttributes rttr) {
    	int result = -1;
        for(Long num : bno) {
    		result = service.delete(num);
    	}
        
		rttr.addFlashAttribute("result", result ==1? "SUCCESS":"FAIL");
        return "redirect:/adminNotify/notify";
    }
    
    
	@GetMapping("/register")
	public void insert(Model model) {
		
	}
	
	@PostMapping("/register")
	public String register(AdminNotifyVO vo, RedirectAttributes rttr) {
		
		int result = service.insert(vo);
		
		rttr.addFlashAttribute("result", result == 1? "SUCCESS":"FAIL");
		
		return "redirect:/adminNotify/notify";
		
	} 
	
}
