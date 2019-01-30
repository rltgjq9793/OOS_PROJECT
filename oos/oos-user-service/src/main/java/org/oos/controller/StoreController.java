package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ProductVO;
import org.oos.domain.StoreVO;
import org.oos.service.HashTagService;
import org.oos.service.NotifyService;
import org.oos.service.ProductService;
import org.oos.service.SellerService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping(value= {"/store/*","/m/store/*"})
public class StoreController {

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	@Setter(onMethod_ = @Autowired)
	private StoreService storeService;
	
	@Setter(onMethod_ = @Autowired)
	private NotifyService notifyService;

	@Setter(onMethod_ = @Autowired)
	private SellerService sellerService;
	
	@Setter(onMethod_ = @Autowired)
	private HashTagService hashService;

	@GetMapping("/popupList")
	public void popupList(Criteria cri, String sid, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("cri", cri);
		map.put("sid", sid);

		PageDTO pageDTO = new PageDTO(cri,notifyService.popupCount(map)); 
		
		List<Integer> pageList = new ArrayList<>();
	    
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
        model.addAttribute("popupList", notifyService.popupList(map));
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
        model.addAttribute("seller", sellerService.get(sid));

	}

	@GetMapping("/popup")
	public void popupGet(Criteria cri, Long sbno, String sid, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pageDTO = new PageDTO(cri, notifyService.popupCount(map));
		map.put("sid", sid);
		map.put("sbno", sbno);
		map.put("dto", pageDTO);

		model.addAttribute("popup", notifyService.getPopup(sbno));
		model.addAttribute("seller", sellerService.get(sid));
		model.addAttribute("pageMaker", pageDTO);
	}
	private void checkStoreVisit(String sViewCookie,Long sno) {
		boolean check = false;
		if (sViewCookie != null) {
			String[] numbers = sViewCookie.split("_");

			String bno = "" + sno;
			for (String number : numbers) {

				if (number.equals(bno)) {
					check = true;
					break;
				}
			}

		}
		if (!check) {
			storeService.upVisitCnt(sno);
		}
	}
	
	private void checkProductVisit(String pViewCookie,Long pno) {
		boolean check = false;
		if (pViewCookie != null) {
			String[] numbers = pViewCookie.split("_");

			String bno = "" + pno;
			for (String number : numbers) {
				if (number.equals(bno)) {
					check = true;
					break;
				}
			}

		}
		if (!check) {
			productService.upVisitCnt(pno);
		}
	}
	
	@GetMapping("/list")
	public void storeList(@CookieValue(value = "sViewCookie", required = false) String sViewCookie, Criteria cri,
			Long sno, Model model) {

		String sid = storeService.get(sno).getOwner();
		Map<String, Object> map = new HashMap<>();
		PageDTO dto = new PageDTO(cri, productService.getTotal(map));
		map.put("sid", sid);
		map.put("dto", dto);
		map.put("sno", sno);

		checkStoreVisit(sViewCookie, sno);
		PageDTO pageDTO = new PageDTO(cri, productService.totalProduct(map));


		List<Integer> pageList = new ArrayList<>();

		for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
			pageList.add(i);
		}
		
		model.addAttribute("popupList", notifyService.popupList(map));
		model.addAttribute("pageList", pageList);
		model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("store", storeService.get(sno));
		model.addAttribute("storeTag", hashService.getStoreTagList(sno));
		model.addAttribute("product", productService.getListBySno(map));

	}

	@GetMapping("/detail")
	public void productRead(@CookieValue(value = "sViewCookie", required = false) String sViewCookie,
			@CookieValue(value = "pViewCookie", required = false) String pViewCookie,Long pno, Long sno, Model model) {

		ProductVO vo = productService.read(pno);
		checkStoreVisit(sViewCookie, sno);
		checkProductVisit(pViewCookie, pno);
		
		
		model.addAttribute("store", storeService.get(sno));
		model.addAttribute("product", vo);
	}

	@PostMapping("/checkzzim/{sno}")
	public ResponseEntity<String> checkzzim(@PathVariable("sno") Long sno) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		if (!name.equals("anonymousUser")) {
			StoreVO store = new StoreVO();
			store.setMid(name);
			store.setSno(sno);

			if (storeService.getStoreLike(store).size() == 0) {
				return new ResponseEntity<String>("no", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("yes", HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<String>(HttpStatus.OK);
		}

	}

	@PostMapping("/autocomplete")
	@ResponseBody
	public List<String> autoComplete() {

		List<String> list = storeService.getName();
		return list;
	}
}