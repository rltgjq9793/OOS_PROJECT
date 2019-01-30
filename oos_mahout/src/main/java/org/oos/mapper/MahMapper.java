package org.oos.mapper;

import java.util.List;

import org.oos.domain.MemberVO;
import org.oos.domain.UserVO;

public interface MahMapper {

	public void insert_user_order(UserVO vo);
	
	public List<MemberVO> getMemberList();
	
	public void delete();

}
