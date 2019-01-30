package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.AuthDTO;
import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;
import org.oos.domain.StoreVO;

public interface MemberMapper {
	public List<MemberVO> getUserList(Map<String, Object> map);
	
	public int getUserCount(Criteria cri);
	
	public MemberVO get(String mid);
	
	public int insert(MemberVO vo);
	
	public int modify(MemberVO vo);
	
	public int modifyPw(MemberVO vo);
	
	public int delete(String mid);
	
/*	public int changePermit(MemberVO vo);*/
		
	public int changeAutority(Map<String, Object> map);
	
	public int count(Criteria cri);

	public List<StoreVO> getMyStoreList(Map<String, Object> map);

	public int getMyStoreCount(Map<String, Object> map);
	
	public int insertSnsAuth(AuthDTO dto);
	
	public AuthDTO getSnsAuth(String user_id);
	
	public int deleteSns(String mid);
	
	public List<MemberVO> manageMember(Map<String, Object> map);
	
	public int newCustomer(Map<String, Object> map);
		
	public int banCustomer(Map<String, Object> map);
	
}