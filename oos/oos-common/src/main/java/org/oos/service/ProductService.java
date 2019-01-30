package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.ProductOptionVO;
import org.oos.domain.ProductVO;

public interface ProductService {
	
	public List<ProductVO> getList(Map<String, Object> map);
	
	public List<ProductVO> getListBySno(Map<String, Object> map);
	
	public List<ProductVO> bestProductList();

	public ProductVO read(Long pno);
	
	public int update(ProductVO vo);
	
	public int upVisitCnt(Long pno);
	
	public int remove(ProductVO vo);
	
	public int register(ProductVO vo);
	
	public int getTotal(Map<String, Object> map);
	
	public int permit(Map<String, Object> map);
	
	public List<String> getName();
	
	public int  totalProduct(Map<String, Object> map);
	
	public int resetVisitCnt();
	
	public int minus(Map<String, Object> map);
	
	public ProductOptionVO getOption(Long opno);
}
