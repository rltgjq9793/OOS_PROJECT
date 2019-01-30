package org.oos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.OrderDetailVO;
import org.oos.domain.OrderVO;
import org.oos.domain.PageDTO;
import org.oos.service.OrderDetailService;
import org.oos.service.OrderService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
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
@RequestMapping("/exam/*")
public class AdminController {

	@Setter(onMethod_ = @Autowired)
	private OrderService orderService;

	@Setter(onMethod_ = @Autowired)
	private OrderDetailService orderDetailService;

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@GetMapping({ "comment", "edit", "home", "paysettle", "products", "seller" })
	public void admin() {

	}

	@GetMapping("/manage")
	public void orderList(Model model, Principal principal, Criteria cri) {
		Long sno = storeService.getBySid(principal.getName()).getSno();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sno", sno);
		map.put("cri", cri);
		PageDTO pageDTO = new PageDTO(cri, orderDetailService.snoCount(sno));
		map.put("dto", pageDTO);
		
		List<OrderDetailVO> order = orderDetailService.getListBySno(map);
		model.addAttribute("orderList", order);
		
		List<Integer> pageList = new ArrayList<>();

		for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
			pageList.add(i);
		}
		
		model.addAttribute("pageList", pageList);
		model.addAttribute("pageMaker", pageDTO);

	}
	
    @PostMapping("/manage")
    public String remove(Long[] odno, RedirectAttributes rttr) {
    	for(Long num : odno) {
    		if(orderDetailService.delete(num,1L) == 1) {
                rttr.addFlashAttribute("result", "success");
            }
    	}
        
        return "redirect:/exam/manage?sno=1";
    }
    
    @PostMapping("/modify")
    public String modify(String[] option, RedirectAttributes rttr) {
        
    	for(String num : option) {    	
    		log.info(num);
    		Map<String, Object> map = new HashMap<String, Object>();
    		
    		String[] list= num.split("_");
    		Long odno = Long.parseLong(list[0]);
    		String dno = list[1];
    		String state = list[2];
    		log.info(dno+"");
    		if(state.equals("ready")) {
    			state = "준비중";
    		}else if(state.equals("shipping")){
    			state = "배송중";
    		}else if(state.equals("complete")) {
    			state = "배송완료";
    		}
    		
    		map.put("odno", odno);
    		if(dno != "-1") {
        		map.put("dno", dno);
    		}
    		map.put("detail_state", state);
    		
    		int count = orderDetailService.modify(map);
    		
    	}
    	
       return "redirect:/exam/manage?sno=1";
    }
}
/*    @GetMapping("/manage")
    public String modify(OrderVO vo, RedirectAttributes rttr) {
    	
    	if(orderService.modify(vo) == 1) {
    		rttr.addFlashAttribute("result", "success");
    	}
    	return  "redirect:/exam/manage?sno=1";
    }
}*/
