package org.oos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.HashTagVO;
import org.oos.domain.PageDTO;
import org.oos.service.HashTagService;
import org.oos.service.MemberService;
import org.oos.service.ProductService;
import org.oos.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/admin/*")
public class SuperAdminController {

	@Setter(onMethod_=@Autowired)
	private MemberService memberService;
	
	@Setter(onMethod_=@Autowired)
	private SellerService sellerService;	
	
	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@Setter(onMethod_=@Autowired)
	private HashTagService tagService;
	
	@GetMapping("/manageUser")
	public void manageUser(Model model, Criteria cri) {
		Map<String, Object> map = new HashMap<>();
	
		PageDTO pageDTO = new PageDTO(cri,memberService.getUserCount(cri)); 
	
		map.put("dto", pageDTO);
		
		List<Integer> pageList = new ArrayList<>();
		
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("member",memberService.getUserList(map));
	}
	
	@PostMapping("/manageUser")
	public String manageUserPost(String[] infos, RedirectAttributes rttr) {
		int result = -1;
		
		for(String info : infos) { 
    		Map<String, Object> map = new HashMap<String, Object>();
    		
    		String[] list= info.split("_");
    		String mid = list[0];
    		String state = list[1];
    		
    		map.put("mid", mid);
    		map.put("auth", state);
    		result = memberService.changeAutority(map);
    	}
		
		rttr.addFlashAttribute("result", result ==1? "SUCCESS":"FAIL");
		return "redirect:/admin/manageUser";
	}
	
	
	@GetMapping("/manageSeller")
	public void manageSeller(Model model, Criteria cri) {
		Map<String, Object> map = new HashMap<>();
		
		PageDTO pageDTO = new PageDTO(cri,sellerService.getSellerCount(cri)); 

		map.put("dto", pageDTO);
		
		List<Integer> pageList = new ArrayList<>();
		
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("seller",sellerService.getSellerList(map));	
	}
	
	
	@PostMapping("/manageSeller")
	public String sellerDelete(RedirectAttributes rttr, Principal principal) {
		
			if(sellerService.remove(principal.getName()) == 1) {
				rttr.addAttribute("result", "success");
				
			}
		return "redirect:/admin/manageSeller";
	}
	
	@PostMapping("/sellerModfiy")
	public String sellerModify(String[] options) {
		
		for(String option : options) { 
    		Map<String, Object> map = new HashMap<String, Object>();
    		
    		String[] list= option.split("_");
    		String sid = list[0];
    		String sellerAuth = list[1];
    		
    		map.put("sid", sid);
    		map.put("auth", sellerAuth);
    		log.info(map+"");
    		sellerService.changeAutority(map);
    	}
	
		return "redirect:/admin/manageSeller";
	}
	
	
	
	@GetMapping("/manageProduct")
	public void manageProduct(Model model, Criteria cri) {
		Map<String, Object> map = new HashMap<>();
		map.put("cri", cri);
		PageDTO pageDTO = new PageDTO(cri,productService.getTotal(map)); 
	
		map.put("dto", pageDTO);
		map.put("seller", "seller");
		
		List<Integer> pageList = new ArrayList<>();
		
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("product",productService.getList(map));
	}
	
	@RequestMapping("/permitP/{state}/{pno}")
	public String productBan(@PathVariable("pno") Long pno,
					@PathVariable("state") String state, RedirectAttributes rttr) {
		Map<String, Object> map = new HashMap<>();
		map.put("pno", pno);
		if(state.equals("ye")){
			map.put("permit", "O");
		}else if(state.equals("no")) {
			map.put("permit", "X");
		}
		
		int result = productService.permit(map);
		rttr.addFlashAttribute("result", result ==1? "SUCCESS":"FAIL");
		
		return "redirect:/admin/manageProduct";
	}
	
	@GetMapping("/manageTag")
	public void manageTag(Model model) {
		
		model.addAttribute("tagList",tagService.getList());
	}
	
	@PostMapping("/addTag/{tags}")
	public ResponseEntity<String> addTag(@PathVariable("tags") String tags) {
		int result = -1;
		
		String tagList[] = tags.split(",");
		for(String tag: tagList) {
			result = tagService.insert(tag);
		}
		
		return result == 1? new ResponseEntity<>("success", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/deleteTag")
	public ResponseEntity<String> deleteTag(Model model, int[] tagArray) {
		int result = -1;
		
		for(int tagNum : tagArray) {
			result = tagService.delete(tagNum);
		}
		
		return result == 1? new ResponseEntity<>("success", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
