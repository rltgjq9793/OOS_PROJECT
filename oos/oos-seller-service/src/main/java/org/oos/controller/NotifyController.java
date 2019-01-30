package org.oos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.NotifyVO;
import org.oos.domain.PageDTO;
import org.oos.service.NotifyService;
import org.oos.service.SellerService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/notify/*")
public class NotifyController {

	@Setter(onMethod_= @Autowired)
	private NotifyService service;
	
	@Setter(onMethod_=@Autowired)
	private SellerService sellerService;
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@PostMapping("/popup")
    public String popupState(Long[] sbno,String swi,Principal principal) {
        log.info(sbno+"");
        String sid = principal.getName();
        log.info("sid:"+sid);
        Map<String, Object> map =new HashMap<>();
        map.put("switch", swi);
        for(Long num : sbno) {
        	
        	map.put("sbno", num);
    		service.popupSwitch(map);
        }
        return "redirect:/notify/sellerNotify";
        
    }
	
	@PostMapping("/sellerModify")
	@PreAuthorize("principal.username == #vo.sid")
	public String sellerModifyPost(NotifyVO vo,Criteria cri, RedirectAttributes rttr){
		
		int result = service.modify(vo);
		
		rttr.addFlashAttribute("result", result ==1? "SUCCESS":"FAIL");
		
		return "redirect:/notify/sellerGet?&sbno="+vo.getSbno()+"&amount="+cri.getAmount()+"&pageNum="+cri.getPageNum();
	}
	
	@GetMapping({"/sellerGet","/sellerModify"})
	@PreAuthorize("isAuthenticated()")
	public void sellerGet(Long sbno,Principal principal,Criteria cri,Model model) {
		
		String sid = principal.getName();
		Map<String, Object> map = new HashMap<>();
		PageDTO pageDTO = new PageDTO(cri, service.sidCount(map));
		map.put("sbno", sbno);
		map.put("sid", sid);
		map.put("dto", pageDTO);
		
		model.addAttribute("seller", sellerService.get(sid));
		model.addAttribute("notify", service.get(sbno));
		model.addAttribute("pageMaker", pageDTO);
	}
	
	@GetMapping("/sellerNotify")
	@PreAuthorize("isAuthenticated()")
	public void getList(Model model, Principal principal, Criteria cri) {
						
		Map<String, Object> map = new HashMap<String, Object>();

		String sid = principal.getName();
		
		map.put("cri", cri);
		PageDTO pageDTO = new PageDTO(cri, service.sidCount(map));
		map.put("dto", pageDTO);
		map.put("sid", sid);
		List<NotifyVO> notify = service.getList(map);
				
		model.addAttribute("notify", notify);
		
		List<Integer> pageList = new ArrayList<>();

		for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
			pageList.add(i);
		}
		
		model.addAttribute("pageList", pageList);
		model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("seller", storeService.getBySid(sid));
		model.addAttribute("popupList",service.popupList(map));

	}
	
    @PostMapping("/remove")
    @PreAuthorize("isAuthenticated()")
    public String remove(Long[] sbno, Principal principal, RedirectAttributes rttr) {
        log.info(sbno+"");
        
        String sid = principal.getName();
        log.info("sid:"+sid);
        for(Long num : sbno) {
    		if(service.delete(num) == 1) {
                rttr.addFlashAttribute("result", "success");
            }
    	}
        
        return "redirect:/notify/sellerNotify?";
        
    }

    @GetMapping("/sellerRegister")
	@PreAuthorize("isAuthenticated()")
	public String insert(Principal principal,Model model) {
		
    	String sid=principal.getName();
    	
		model.addAttribute("seller", sellerService.get(sid));
		
		return "/notify/sellerRegister";
    
	}
	
	@PostMapping("/sellerRegister")
	@PreAuthorize("principal.username == #vo.sid")
	public String register(NotifyVO vo, RedirectAttributes rttr) {
		
		int result = service.insert(vo);
		
		rttr.addFlashAttribute("result", result == 1? "SUCCESS":"FAIL");
		
		return "redirect:/notify/sellerNotify";
		
	} 
	
}
