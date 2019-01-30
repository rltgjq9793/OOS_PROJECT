package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.OrderDetailVO;

public interface OrderDetailMapper {

/*	public List<OrderDetailVO> getList(Long ono);*/
	
	public OrderDetailVO getByOdno(Long odno);
	
	public List<OrderDetailVO> getListByOno(Long ono);
	
	public OrderDetailVO get(Long odno);
	
	public int modify(Map<String, Object> map);
	
	public int delete(Long odno);
	
	public int snoCount(Long sno);
	
	public int total(Map<String, Object> map);

	public int stateCount(Map<String, Object> map);
	
	public int insert(OrderDetailVO vo);

	/*public List<OrderDetailVO> orderList(Map<String, Object> map);
*/
	public int deleteAll(Long ono);
	
	public List<OrderDetailVO> getListBySno(Map<String, Object> map);
	
	
	
}
