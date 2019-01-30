package org.oos.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.oos.domain.CategoryVO;
import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ProductVO;
import org.oos.service.AutoMlService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	@Setter(onMethod_ = @Autowired)
	private StoreService storeService;
	@Setter(onMethod_ = @Autowired)
	private AutoMlService autoMIService;
	
	
	@PostMapping("/remove")
	public String remove(ProductVO vo, RedirectAttributes rttr, Criteria cri) {
		
		int result = productService.remove(vo);
		
		rttr.addFlashAttribute("result", result ==1? "SUCCESS":"FAIL");
		
		return "redirect:/product/list";
	}
	
	@PostMapping("/modify")
	public String modifyPost(Criteria cri,ProductVO vo, RedirectAttributes rttr) {
		
		int result = productService.update(vo);
		
		rttr.addFlashAttribute("result", result ==1? "SUCCESS":"FAIL");
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/product/list";
	}
	
	@GetMapping({"/read","/modify"})
	public void productGet(Criteria cri,Long pno, Principal principal, Long opno, Model model) {
		
		
		Map<String, Object> map = new HashMap<>();
		PageDTO dto = new PageDTO(cri,productService.getTotal(map));
		
		map.put("dto", dto);
		map.put("pno", pno);
		map.put("sno", storeService.getBySid(principal.getName()).getSno());
		map.put("opno", opno);
		
		PageDTO pageDTO = new PageDTO(cri,productService.getTotal(map));
		
				
		ProductVO vo = productService.read(pno);
		model.addAttribute("store", storeService.getBySid(principal.getName()));
		log.info(""+vo);
		model.addAttribute("product", vo);
		model.addAttribute("pageMaker", pageDTO);

	}
	
	@GetMapping("/list")
	public void productList(Criteria cri,Model model, Principal principal) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("sno", storeService.getBySid(principal.getName()).getSno());
		PageDTO pageDTO = new PageDTO(cri, productService.totalProduct(map));
		map.put("dto", pageDTO);
		map.put("seller", "seller");
        log.info(storeService.getBySid(principal.getName())+"");
        List<Integer> pageList = new ArrayList<>();
        
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
           
        }
        
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("store", storeService.getBySid(principal.getName()));
		model.addAttribute("product", productService.getListBySno(map));
	}

	@GetMapping("/register")
	public void productRegister(Principal principal, Model model) {
		model.addAttribute("store", storeService.getBySid(principal.getName()));
	}

	@PostMapping("/register")
	@Transactional
	public String productRegisterPost(ProductVO vo) {
		List<CategoryVO> cateList = new ArrayList<>();
		String firstPath = "\\\\HB03-26\\upload\\" + vo.getImgList().get(0).getIpath() 
					+ "\\" + vo.getImgList().get(0).getUuid() + "_" 
					+ vo.getImgList().get(0).getIname();
		try {
			List<String> list = autoMIService.predict("oos-atree-224402", "us-central1", "ICN3296882034050407743",
					firstPath, "0.6");

			list.forEach(name -> {
				CategoryVO catevo = new CategoryVO();
				catevo.setCatename(name);
				cateList.add(catevo);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		vo.setCateList(cateList);
		log.info(""+vo);

		productService.register(vo);

		return "redirect:/product/list";
	}
	
	@PostMapping("/autocomplete")
	@ResponseBody
	public List<String> autoComplete() {

		List<String> list = productService.getName();
		log.info("" + list);
		return list;
	}

}