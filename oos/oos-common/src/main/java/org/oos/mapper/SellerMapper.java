package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.SellerVO;
import org.oos.domain.StoreVO;

public interface SellerMapper {

	public List<SellerVO> getSellerList(Map<String, Object> map);
	
	public int getSellerCount(Criteria cri);
	
	public SellerVO get(String sid);
	
	public int insert(SellerVO vo);
	
	public int modify(SellerVO vo);
	public int addSno(StoreVO vo);
	public int delete(String sid);
	
	public int authorize(String sid);
	
	public int changeAutority(Map<String, Object> map);
	
	public int unapprovedCount(Map<String, Object> map);
	
	public int banCount(Map<String, Object> map);
	
	public int currentSeller(Map<String, Object> map);
	
	public SellerVO getBySid(String sid);
}
