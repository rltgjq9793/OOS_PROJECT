package org.oos.service;

import java.util.List;

import org.oos.domain.HashTagVO;
import org.oos.domain.StoreHashTagVO;
import org.oos.mapper.HashTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
public class HashTagServiceImpl implements HashTagService {

	@Setter(onMethod_=@Autowired)
	private HashTagMapper mapper;
	
	@Override
	public List<String> getName() {
		return mapper.getName();
	}

	@Override
	public List<HashTagVO> getList() {
		return mapper.getList();
	}

	@Override
	public int insert(String tag) {
		return mapper.insert(tag);
	}

	@Override
	public int delete(int hno) {
		return mapper.delete(hno);
	}

	@Override
	public List<StoreHashTagVO> getStoreTagList(Long sno) {
		return mapper.getStoreTagList(sno);
	}

	@Override
	public int insertStore(StoreHashTagVO vo) {
		return mapper.insertStore(vo);
	}

	@Override
	public int deleteAll(Long sno) {
		return mapper.deleteAll(sno);
	}

}
