package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.oos.domain.ProductVO;
 
public interface ProductMapper {
	
	public List<ProductVO> getList2(Map<String, Object> map);
	
	public List<ProductVO> bestProductList();
	
	public int upVisitCnt(Long pno);
	public int resetVisitCnt();
	public int permit(Map<String, Object> map);
	
	public ProductVO get(Long pno);
	
	public ProductVO getByPno(Long pno);
	
	public List<ProductVO> getListBySno(Map<String, Object> map);
	
	public int modify(ProductVO vo);
	
	public int delete(ProductVO vo);
	
	public int insert(ProductVO vo);
	
	public int count(Map<String, Object> map);
	
	public List<String> getName();
	
	public void updateQuestionReplyCnt(@Param("pno") Long pno,@Param("amount") int amount);
	
	public void updateReviewReplyCnt(@Param("pno") Long pno,@Param("amount") int amount);
	
	public int  totalProduct(Map<String, Object> map);
	
	public int minus(Map<String, Object> map);
	
}
