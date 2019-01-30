package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.CartVO;

public interface CartMapper {

	public int insert(CartVO vo);
	public int delete(Long cno);
	public int update(CartVO vo);
/*	public List<CartVO> getList(Map<String, Object> map);*/
	public List<CartVO> getListByMid(Map<String, Object> map);
	public CartVO getByOpno (Long opno);
	public CartVO getByCno(Long cno);
	public int modify(CartVO vo);
	public int count(Map<String, Object> map);
	
}
