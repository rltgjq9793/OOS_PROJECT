package org.oos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.SellerVO;
import org.oos.domain.StoreVO;
import org.oos.service.MemberService;
import org.oos.service.OrderDetailService;
import org.oos.service.OrderService;
import org.oos.service.ProductService;
import org.oos.service.ReplyService;
import org.oos.service.SellerService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {

	@Setter(onMethod_ = @Autowired)
	private StoreService storeService;
	@Setter(onMethod_ = @Autowired)
	private ReplyService replyService;

	@Setter(onMethod_ = @Autowired)
	private SellerService sellerService;

	@Setter(onMethod_ = @Autowired)
	private OrderDetailService detailService;
	
	@Setter(onMethod_=@Autowired)
	private MemberService memberService;
	
	@Setter(onMethod_=@Autowired)
	private OrderService orderService;
	
	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@GetMapping("/")
	public String main() {
		return "redirect:/main";
	}


	//최고관리자 메인
	@GetMapping("/adminMain")
	public void adminMain(Principal principal, Model model) {
			
		SellerVO vo = sellerService.get(principal.getName());
			
		Map<String,Object> map = new HashMap<>();
		Criteria cri = new Criteria();
		map.put("sid", vo.getSid());
		map.put("cri",cri);
					
		model.addAttribute("totalVisit", storeService.totalVisit(map));
		model.addAttribute("newCustomer", memberService.newCustomer(map));
		model.addAttribute("unapprovedSeller", sellerService.unapprovedCount(map));
		model.addAttribute("todayRevenue", orderService.todayRevenue(map));
		model.addAttribute("monthlyRevenue", orderService.monthlyRevenue(map));
		model.addAttribute("banCount", sellerService.banCount(map));
		model.addAttribute("currentSeller", sellerService.currentSeller(map));
		model.addAttribute("totalCustomer", memberService.getUserCount(cri));
		model.addAttribute("banCustomer", memberService.banCustomer(map));
		model.addAttribute("totalProduct", productService.totalProduct(map));
	}
	

	@GetMapping("/mypage")
	public void mypage() {

	}

	@GetMapping("/main")
	@PreAuthorize("isAuthenticated()")
	public String storeMain(Authentication authentication,Principal principal, Model model) {
		log.info("register get~");
		

		Collection<? extends Object> collection=authentication.getAuthorities();
		List list=new ArrayList(collection);
		
		String auth=""+list.get(0);
		log.info(auth);
		if(auth.equals("ROLE_NONE")) {
			return "redirect:/store/register";
			
		}
		else if(auth.equals("ROLE_ADMIN")) {
			return "redirect:/adminMain";
		}
	
		String[] state = { "ready", "shipping", "complete" };
		String[] kind = { "q", "r" };
		String[] range = { "day", "week", "month" };
		String sid=principal.getName();
	
		if (sellerService.get(sid).getSno() == null) {
			return "redirect:/store/register";
		}
		StoreVO vo = storeService.getBySid(sid);
		
		log.info(""+vo);
		
		
		Long sno=vo.getSno();
		int visitcnt = vo.getVisitcnt();
		log.info("visit"+visitcnt);

		Map<String, Object> map = new HashMap<>();

		map.put("sno", sno);

		for (String str : kind) {
			map.put("kind", str);
			model.addAttribute(str + "ReplyCnt", replyService.getNewReplyCnt(map));

		}
		for (String str : state) {
			map.put("state", str);
			log.info("state:" + str);

			model.addAttribute(str, detailService.getStateCount(map));
		}

		for (String str : range) {

			map.put("range", str);
			model.addAttribute(str, detailService.getTotal(map));
		}

		model.addAttribute("visitcnt", visitcnt);
		return "/main";

	}

}