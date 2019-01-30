package org.oos.service;

import java.util.List;

import org.oos.domain.CategoryVO;

public interface CategoryService {

	
	public int remove(Long cateno);
	public void register(List<CategoryVO> list);
	
}
