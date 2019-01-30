package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.AuthDTO;
import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;
import org.oos.domain.StoreVO;
import org.oos.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;



@Service
public class MemberServiceImpl implements MemberService {

	@Setter(onMethod_= @Autowired)
	private MemberMapper mapper;
	
	@Override
	public List<MemberVO> getUserList(Map<String, Object> map) {
		return mapper.getUserList(map);
	}

	@Override
	public MemberVO get(String mid) {
		return mapper.get(mid);
	}

	@Override
	public int register(MemberVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int modify(MemberVO vo) {
		return mapper.modify(vo);
	}

	@Override
	public int remove(String mid) {
		return mapper.delete(mid);
	}

	@Override
	public int count(Criteria cri) {
		return mapper.count(cri);
	}

	@Override
	public int insertSnsAuth(AuthDTO dto) {
		return mapper.insertSnsAuth(dto);
	}

	@Override
	public AuthDTO getSnsAuth(String user_id) {
		return mapper.getSnsAuth(user_id);
	}

	@Override
	public List<StoreVO> getMyStoreList(Map<String, Object> map) {
		return mapper.getMyStoreList(map);
	}
	
	@Override
	public int removeSns(String mid) {
		return mapper.deleteSns(mid);
	}

	@Override
	public int modifyPw(MemberVO vo) {
		return mapper.modifyPw(vo);
	}

	@Override
	public int getMyStoreCount(Map<String, Object> map) {
		return mapper.getMyStoreCount(map);
	}

	@Override
	public int getUserCount(Criteria cri) {
		return mapper.getUserCount(cri);
	}

	@Override
	public List<MemberVO> manageMember(Map<String, Object> map) {
		return mapper.manageMember(map);
	}

	@Override
	public int changeAutority(Map<String, Object> map) {
		return mapper.changeAutority(map);
	}

	@Override
	public int newCustomer(Map<String, Object> map) {
		return mapper.newCustomer(map);
	}
	
	@Override
	public int banCustomer(Map<String, Object> map) {
		return mapper.banCustomer(map);
	}
	

}

