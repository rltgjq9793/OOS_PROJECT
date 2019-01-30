package org.oos.service;

import java.util.List;

import org.oos.domain.HashTagVO;
import org.oos.domain.StoreHashTagVO;

public interface HashTagService {
	
	public List<String> getName();

	public List<HashTagVO> getList();
	
	public int insert(String tag);
	
	public int delete(int hno);
	
	public List<StoreHashTagVO> getStoreTagList(Long sno);
	
	public int insertStore(StoreHashTagVO vo);
	
	public int deleteAll(Long sno);
}
