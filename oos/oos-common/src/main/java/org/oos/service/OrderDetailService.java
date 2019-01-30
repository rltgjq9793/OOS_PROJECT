package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.OrderDetailVO;

public interface OrderDetailService {
	
/*	public List<OrderDetailVO> getList(Long ono);*/
	
	/*public int count(Map<String, Object> map);*/
	
	public OrderDetailVO get(Long odno);
	
	public int modify(Map<String, Object> map);
	
	public int delete(Long odno, Long ono);
	
	public int snoCount(Long sno);
	
	public int getTotal(Map<String, Object> map);
	
	public int getStateCount(Map<String, Object> map);
	
	public int insert(OrderDetailVO vo);
	
/*	public List<OrderDetailVO> orderList(Map<String, Object> map);*/
	
	public List<OrderDetailVO> getListByOno(Long ono);
	
	public List<OrderDetailVO> getListBySno(Map<String, Object> map);
}
