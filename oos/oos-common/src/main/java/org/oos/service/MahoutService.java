package org.oos.service;

import java.util.List;

import org.oos.domain.Mahout_MemberVO;

public interface MahoutService {
	
	public void setTable();
	
	public List<Mahout_MemberVO> getRecList(String mid);
}
