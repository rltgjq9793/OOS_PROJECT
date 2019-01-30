package org.oos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ReplyVO;
import org.oos.service.ReplyService;
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

@Log
@Controller
@RequestMapping("/rev/*")
public class ReviewController { 
	
	@Setter(onMethod_=@Autowired)
	private ReplyService replyService;
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
		
	//화면리스트
	@GetMapping("/list")
	public void getList(Model model,Criteria cri, Principal principal) {

		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("kind", "r");
		map.put("sno", storeService.getBySid(principal.getName()).getSno());
		PageDTO pageDTO = new PageDTO(cri, replyService.count(map));
		map.put("dto", pageDTO);
		
		List<ReplyVO> reply = replyService.getStoreReply(map);
		
		model.addAttribute("replyList", reply);
		
		List<Integer> pageList = new ArrayList<>();

		for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
			pageList.add(i);
		}

		model.addAttribute("pageList", pageList);
		model.addAttribute("pageMaker", pageDTO);
		
	}
	
	//리스트 1단 삭제
	@PostMapping("/list")
	public String remove(Long rno, RedirectAttributes rttr) {
		
		
    		if(replyService.getRemove(rno) == 1) {
                rttr.addFlashAttribute("result", "success");
            }
    	
        
        return "redirect:/rev/list?kind=r&sno=1";
		
	}
	
	//팝업창화면
	@GetMapping("/reviewDetail")
	public void qnaDetail(long pno, int parent, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
  
		
		map.put("pno", pno);
		map.put("kind", "r");
		map.put("parent", parent);
		model.addAttribute("replyList", replyService.sellerReply(map));
}
	//팝업창화면 댓글등록하기
	@PostMapping("/reviewDetail")
	public String popInsert(ReplyVO vo) {
		int count = replyService.sellerInsert(vo);
		
    	return "redirect:/rev/reviewDetail?pno="+vo.getPno()+"&parent="+vo.getParent();
	}
}

/*Map<String, Object> map = new HashMap<String, Object>();

map.put("sid", sid);
map.put("cri", cri);
PageDTO pageDTO = new PageDTO(cri, service.sidCount(map));
map.put("dto", pageDTO);

List<NotifyVO> notify = service.getList(map);
		
model.addAttribute("notify", notify);

List<Integer> pageList = new ArrayList<>();

for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
	pageList.add(i);
}

model.addAttribute("pageList", pageList);
model.addAttribute("pageMaker", pageDTO);
*/
