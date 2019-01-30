package org.oos.mapper;

import java.util.List;

import org.oos.domain.MahoutVO;
import org.oos.domain.Mahout_MemberVO;
import org.oos.domain.ReplyVO;

public interface MahoutMapper {

	public List<Mahout_MemberVO> getOrderList();
	
	public List<Mahout_MemberVO> getRecList(String mid);
	
	public List<ReplyVO> getScoreList(String mid);
	
	public int insert(MahoutVO vo);

	public int delete();
	
}
