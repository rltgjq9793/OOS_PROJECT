package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;
import org.oos.domain.OrderDetailVO;
import org.oos.domain.OrderVO;
import org.oos.domain.PageDTO;
import org.oos.domain.StoreVO;
import org.oos.persistence.MemberRepository;
import org.oos.service.CartService;
import org.oos.service.MemberService;
import org.oos.service.OrderDetailService;
import org.oos.service.OrderService;
import org.oos.service.ProductService;
import org.oos.service.ReplyService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping(value= {"/user/*","/m/user/*"})
public class UserController {
    @Setter(onMethod_=@Autowired)
    private CartService cartService;
    
    @Setter(onMethod_= @Autowired)
    private MemberService memberService;
    
    @Setter(onMethod_= @Autowired)
    private OrderDetailService orderDetailService;
    
    @Setter(onMethod_= @Autowired)
    private OrderService orderService;
    
    @Setter(onMethod_= @Autowired)
    private StoreService storeService;
    
    @Setter(onMethod_= @Autowired)
    private ProductService productService;
    
	@Setter(onMethod_= @Autowired)
	private ReplyService replyService;

	@Autowired
	PasswordEncoder pwEncoder;
	
	@Autowired
	MemberRepository repo;

    @GetMapping("/mypage/reviewDetail")
	public void reviewDetail(long pno, String parent, Model model) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	map.put("parent", parent);
		map.put("pno", pno);
		map.put("kind", "r");
		model.addAttribute("reviewDetail", replyService.sellerReply(map));
	}
	
	@GetMapping("/mypage/review")
	public void reviewList(Criteria cri, Model model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("mid", name);
		map.put("kind", "r");
		
		PageDTO pageDTO = new PageDTO(cri,replyService.myOrderCount(map)); 
		map.put("dto", pageDTO);
		model.addAttribute("reply", replyService.getStoreReply(map));
		
		List<Integer> pageList = new ArrayList<>();
	    
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }

	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
	}

	@GetMapping("/mypage/qna")
	public void qnaList(Criteria cri, Model model) {
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("cri", cri);
		map.put("mid", name);
		map.put("kind", "q");
		
		PageDTO pageDTO = new PageDTO(cri,replyService.myOrderCount(map)); 
		map.put("dto", pageDTO);

		model.addAttribute("reply", replyService.getStoreReply(map));
		List<Integer> pageList = new ArrayList<>();
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }

	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
	}
	
	@GetMapping("/mypage/qnaDetail")
	public void qnaDetail(long pno, String parent, Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pno", pno);
		map.put("kind", "q");
		map.put("parent", parent);
		model.addAttribute("qnaDetail", replyService.sellerReply(map));
	}
	
	@GetMapping("/myStoreList")
	public void myStore(Criteria cri, Model model) {
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
	    
		Map<String, Object> map = new HashMap<String, Object>();
		List<StoreVO> storeList = new ArrayList<>();
		 
		map.put("cri", cri);
		map.put("mid", name);
		PageDTO pageDTO = new PageDTO(cri, memberService.getMyStoreCount(map));		
		
		List<Integer> pageList = new ArrayList<>();
	    
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("storeList", memberService.getMyStoreList(map));
	}
	
	 @PostMapping("/myStoreList")
    public String myStoreRemove(Long sno) {
		 
		 	String name = SecurityContextHolder.getContext().getAuthentication().getName();
		    
		 	StoreVO vo = new StoreVO();
		 	vo.setMid(name);
		 	vo.setSno(sno);
	    	storeService.delStoreLike(vo);
	    	
	    	return "redirect:/user/myStoreList";
	 }
	 
	@PostMapping("/zzim/{sno}")
    public ResponseEntity<String> insertZzim(@PathVariable("sno") Long sno) {	 
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
	    
	 	StoreVO vo = new StoreVO();
	 	vo.setMid(name);
	 	vo.setSno(sno);
	    
	 	if(storeService.getStoreLike(vo).size() == 0) {
	 		storeService.inStoreLike(vo);
	 		return new ResponseEntity<String>("insert",HttpStatus.OK);
	 	}else {
	 		storeService.delStoreLike(vo);
	 		return new ResponseEntity<String>("delete",HttpStatus.OK);
	 	}
	 	
	 }

    
    @GetMapping("/mypage/orderDetail")
    public void orderDetail(long ono, Model model) {
    	
    	List<OrderDetailVO> list = orderDetailService.getListByOno(ono);
    	if(list.size() > 0) {
            model.addAttribute("detail", list);
    	}else {
    		model.addAttribute("detail", "null");
    	}
    }
    
    @PostMapping("/mypage/orderDetail")
    public String orderDetailRemove(long odno, long ono) {
    	orderDetailService.delete(odno, ono);
    	
    	return "redirect:/user/mypage/orderDetail?ono="+ono;
    }
    
    @GetMapping("/list")
    public void List(Criteria cri, Model model) {
    	if(cri.getCategory() == "") {
    		cri.setCategory(null);
    	};
    	Map<String, Object> map = new HashMap<>();
    	map.put("cri", cri);
    	PageDTO pageDTO;
    	
    	if(cri.getFilter() != null && cri.getFilter().equals("select2")) {
    		pageDTO = new PageDTO(cri, storeService.count(cri));
    	}else {
    		pageDTO = new PageDTO(cri, productService.getTotal(map));
    	}
    	
    	map.put("dto", pageDTO);
		
		if(cri.getFilter() != null && cri.getFilter().equals("select2")) {	
        	model.addAttribute("store", storeService.getStoreList(pageDTO));
        }else{
        	model.addAttribute("product", productService.getList(map));
        }
		
		List<Integer> pageList = new ArrayList<>();
	    
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        model.addAttribute("cri", cri);   
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
        
    }
    
    @GetMapping("/mypage/orderList")
    public void orderDetailList(Criteria cri, Model model) {
    	
    	String name = SecurityContextHolder.getContext().getAuthentication().getName();
	    
    	
        Map<String, Object> map = new HashMap<String, Object>();
       
        map.put("mid",name);
        map.put("cri",cri);
        PageDTO pageDTO = new PageDTO(cri,orderService.count(map)); 
        map.put("dto", pageDTO);
   
        List<OrderVO> order = orderService.getList(map);
      
       model.addAttribute("orderList", order);
        
        List<Integer> pageList = new ArrayList<>();
        
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
        
    }
    
    @PostMapping("/mypage/orderList")
    public String remove(Long ono, RedirectAttributes rttr) {

    	String name = SecurityContextHolder.getContext().getAuthentication().getName();
	    
        if(orderService.delete(ono) == 1) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/user/mypage/orderList";
    }
    
    @GetMapping("/mypage/modify")
    public void get(Model model) {
    	String name = SecurityContextHolder.getContext().getAuthentication().getName();
    	MemberVO member = memberService.get(name);
        model.addAttribute("member",member);
    }
    
    @PostMapping("/modPw/{pw}")
    public ResponseEntity<String> modifyPw(@PathVariable("pw") String pw) {
    	String name = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	MemberVO vo = new MemberVO();
	 	vo.setMid(name);
	 	String password = pwEncoder.encode(pw);
	 	vo.setMpw(password);
	 	
    	if(memberService.modifyPw(vo) == 1) {
    		return new ResponseEntity<String>("success",HttpStatus.OK);
        }
    	
	 	return new ResponseEntity<String>(HttpStatus.OK);
	 }
    
    
    @PostMapping("/mypage/modify")
    public String modify(MemberVO vo, RedirectAttributes rttr) {

        if(memberService.modify(vo) == 1){
            rttr.addFlashAttribute("modify", "success");
        }
        
        return "redirect:/user/mypage/modify";
    }
    
    @GetMapping("/cart")
    public void list(Criteria cri, Model model) {
    	
    	String name = SecurityContextHolder.getContext().getAuthentication().getName();
	    
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("mid",name);
        
    	PageDTO pageDTO = new PageDTO(cri, cartService.count(map));

        map.put("dto",pageDTO);
        List<Integer> pageList = new ArrayList<>();
        
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
        model.addAttribute("cartList",cartService.getList(map));
    }
    
}