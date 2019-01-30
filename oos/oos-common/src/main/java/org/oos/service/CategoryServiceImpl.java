package org.oos.service;

import java.util.List;

import org.oos.domain.CategoryVO;
import org.oos.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Setter
	private CategoryMapper mapper;
	
	@Override
	public int remove(Long cateno) {
		// TODO Auto-generated method stub
		return mapper.delete(cateno);
	}

	@Override
	public void register(List<CategoryVO> list) {
		// TODO Auto-generated method stub
		
		 list.forEach(vo->{
			 mapper.insert(vo);
		 });
	}

}
