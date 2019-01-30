package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.SellerVO;

public interface SellerService {
	
	public List<SellerVO> getSellerList(Map<String, Object> map);
	
	public int getSellerCount(Criteria cri);
	
	public SellerVO get(String sid);
	
	public int register(SellerVO vo);
	
	public int modify(SellerVO vo);
	
	public int remove(String sid);
	
	public int changeAutority(Map<String, Object> map);
	
	public int unapprovedCount(Map<String, Object> map);
	
	public int banCount(Map<String, Object> map);
	
	public int currentSeller(Map<String, Object> map);
	
	public SellerVO getBySid(String sid);

}
