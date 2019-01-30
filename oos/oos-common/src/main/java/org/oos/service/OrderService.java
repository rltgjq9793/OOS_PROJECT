package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.OrderDetailVO;
import org.oos.domain.OrderVO;

public interface OrderService {
	
	public List<OrderVO> getList(Map<String, Object> map);
	
	public int count(Map<String, Object> map);
	
	public OrderVO get(Long ono);
	
	public int insert(OrderVO order, List<OrderDetailVO> detail);
	
	public int modify(OrderVO vo);
	
	public int delete(Long ono);
	
	public List<OrderVO> orderList(Map<String, Object> map);
	
	public int orderCount(Map<String, Object> map);
	
	public int todayRevenue(Map<String, Object> map);
	
	public int monthlyRevenue(Map<String,Object> map);
}
