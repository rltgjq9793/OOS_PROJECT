package org.oos;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.oos.domain.CartVO;
import org.oos.mapper.CartMapper;
import org.oos.mapper.OrderDetailMapper;
import org.oos.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@MapperScan("org.oos.mapper")
public class CartTests {

	@Setter(onMethod_=@Autowired)
	private CartService service;
	@Setter(onMethod_=@Autowired)
	private CartMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private OrderDetailMapper omapper;
	

	@Test
	public void total() {
		Map<String, Object> map=new HashMap<>();
		map.put("sno", 2L);
		//map.put("date", 3);
		map.put("range", "week");
		log.info(""+omapper.total(map));
	}
	
	
	@Test
	public void insert() {
		for(int i=0;i<10;i++) {
		CartVO vo=new CartVO();
		vo.setMid("test");
		vo.setOpno(1L);
		vo.setQty(3L);
		vo.setPno(1L);
		//log.info(""+mapper.insert(vo));
	
		}
	}
	
	@Test
	public void get() {
		log.info(""+service.get(1L));
	}
}
