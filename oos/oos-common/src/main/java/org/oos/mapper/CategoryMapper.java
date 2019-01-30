package org.oos.mapper;

import org.oos.domain.CategoryVO;

public interface CategoryMapper {

	public int delete(Long cateno);
	
	public int insert(CategoryVO vo);
	
}
