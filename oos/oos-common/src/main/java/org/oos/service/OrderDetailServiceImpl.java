package org.oos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.OrderDetailVO;
import org.oos.domain.OrderVO;
import org.oos.domain.ProductOptionVO;
import org.oos.domain.ProductVO;
import org.oos.mapper.OrderDetailMapper;
import org.oos.mapper.OrderMapper;
import org.oos.mapper.ProductOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Setter(onMethod_=@Autowired)
	private OrderDetailMapper orderDetailMapper;
	
	@Setter(onMethod_=@Autowired)
	private OrderMapper orderMapper;
	
	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@Setter(onMethod_=@Autowired)
	private ProductOptionMapper productOptionMapper;
	
/*	@Override
	public List<OrderDetailVO> getList(Long ono) {
		
		List<OrderDetailVO> list = orderDetailMapper.getList(ono);
		list.forEach(vo -> {
			ProductVO product = productService.read(vo.getPno());
			ProductOptionVO option = productOptionMapper.get(vo.getOpno());
			vo.setProduct(product);
			vo.setOption(option);
		});
		
		return list;
	}*/
	
	@Override
	public List<OrderDetailVO> getListByOno(Long ono) {
		
		return orderDetailMapper.getListByOno(ono);
	}

	@Override
	public OrderDetailVO get(Long odno) {
//		OrderDetailVO vo = orderDetailMapper.get(odno);
//		ProductVO product = productService.read(vo.getPno());
//		ProductOptionVO option = productOptionMapper.get(vo.getOpno());
//		vo.setProduct(product);
//		vo.setOption(option);
		
		return orderDetailMapper.getByOdno(odno);
	}

	@Override
	public int modify(Map<String, Object> map) {
		return orderDetailMapper.modify(map);
	}

	@Override
	public int delete(Long odno, Long ono) {
		log.info(odno+"");
		int result  = orderDetailMapper.delete(odno);
		
		if(orderDetailMapper.getListByOno(ono).size() == 0) {
			orderMapper.delete(ono);
		};
		return result;
	}

	@Override
	public int insert(OrderDetailVO vo) {

		return orderDetailMapper.insert(vo);
	}


/*	@Override
	public List<OrderDetailVO> orderList(Map<String, Object> map) {

		List<OrderDetailVO> list = orderDetailMapper.orderList(map);
		list.forEach(vo -> {
			ProductVO product = productService.read(vo.getPno());
			ProductOptionVO option = productOptionMapper.get(vo.getOpno());
			OrderVO order = orderMapper.get(vo.getOno());
			vo.setProduct(product);
			vo.setOption(option);
			vo.setOrder(order);
		});
		
		return list;
	}*/

	@Override
	public int snoCount(Long sno) {
		return orderDetailMapper.snoCount(sno);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		
		
		return orderDetailMapper.total(map);
	}

	@Override
	public int getStateCount(Map<String, Object> map) {
		
		
		return orderDetailMapper.stateCount(map);
	}

	@Override
	public List<OrderDetailVO> getListBySno(Map<String, Object> map) {
		return orderDetailMapper.getListBySno(map);
	}
	
	
}
