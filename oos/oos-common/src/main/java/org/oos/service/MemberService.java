package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.AuthDTO;
import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;
import org.oos.domain.StoreVO;

public interface MemberService {

	public List<MemberVO> getUserList(Map<String, Object> map);
	
	public int getUserCount(Criteria cri);
	
	public MemberVO get(String mid);
	
	public int register(MemberVO vo);
	
	public int modify(MemberVO vo);
	
	public int modifyPw(MemberVO vo);
	
	public int remove(String mid);
		
	public int count(Criteria cri);
	
	public int changeAutority(Map<String, Object> map);

	public List<StoreVO> getMyStoreList(Map<String, Object> map);
	
	public AuthDTO getSnsAuth(String user_id);
	
	public int insertSnsAuth(AuthDTO dto);
	
	public int removeSns(String mid);
	
	public int getMyStoreCount(Map<String, Object> map);
	
	public List<MemberVO> manageMember(Map<String, Object> map);
	
	public int newCustomer(Map<String, Object> map);
		
	public int banCustomer(Map<String, Object> map);
}
