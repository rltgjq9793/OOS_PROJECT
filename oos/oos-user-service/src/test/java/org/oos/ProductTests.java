package org.oos;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.oos.domain.CategoryVO;
import org.oos.domain.Criteria;
import org.oos.domain.ProductVO;
import org.oos.mapper.CategoryMapper;
import org.oos.mapper.HashTagMapper;
import org.oos.mapper.MemberMapper;
import org.oos.mapper.ProductImgMapper;
import org.oos.mapper.ProductMapper;
import org.oos.mapper.ProductOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@MapperScan("org.oos.mapper")
public class ProductTests {

	@Setter(onMethod_=@Autowired)
	private ProductMapper mapper;
	@Setter(onMethod_=@Autowired)
	private ProductImgMapper imgMapper;
	@Setter(onMethod_=@Autowired)
	private ProductOptionMapper optMapper;
	
	@Setter(onMethod_=@Autowired)
	private MemberMapper memberMapper;
	
	@Setter(onMethod_=@Autowired)
	private HashTagMapper hashTagMapper;
	
	@Setter(onMethod_=@Autowired)
	private CategoryMapper cateMapper;
	
	@Test
	public void testGet() {
		
		CategoryVO vo =new CategoryVO();
		vo.setCatename("hi");
		vo.setPno(100L);
		log.info(""+ cateMapper.insert(vo));
	}
	
	
	@Test
	public void testMemberMapper() {
		
		log.info("============================");
		log.info("" + memberMapper);
		log.info("============================");
		
	}
	
	@Test
	public void test() {
		ProductVO vo=new ProductVO();
	
		//log.info(""+mapper.get(1L));
		Criteria cri=new Criteria();
		Map<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("criteria", cri);
	    parameters.put("sno",1L);

		log.info(""+mapper.getListBySno(parameters));
	}
	@Test
	public void testP() {
		ProductVO vo=new ProductVO();
		vo.setPname("가방");
		vo.setPrice(10000L);
		vo.setSno(1L);
		mapper.insert(vo);
	}
	@Test
	public void imgTest() {
		log.info(""+imgMapper.getList(1L));
	}
	@Test
	public void modiTest() {
		log.info(""+mapper.get(1L));
	log.info(""+mapper.modify(mapper.get(1L)));
	}
	
	@Test
	public void OptionTest() {
		log.info(""+optMapper.get(1L));
	}
	
	@Test
	public void pserviceTest() {
		
		Criteria cri=new Criteria();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("criteria", cri);
		map.put("sno",1L);

		log.info(""+mapper.count(map));
	}
}
