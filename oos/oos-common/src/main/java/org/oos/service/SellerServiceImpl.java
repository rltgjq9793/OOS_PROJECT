package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.SellerVO;
import org.oos.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class SellerServiceImpl implements SellerService {
	
	@Setter(onMethod_=@Autowired)
	private SellerMapper mapper;


	@Override
	public SellerVO get(String sid) {
		return mapper.get(sid);
	}


	@Override
	public int modify(SellerVO vo) {
		return mapper.modify(vo);
	}

	@Override
	public int remove(String sid) {
		return mapper.delete(sid);
	}


	@Override
	public int register(SellerVO vo) {
		return mapper.insert(vo);
	}


	@Override
	public List<SellerVO> getSellerList(Map<String, Object> map) {
		return mapper.getSellerList(map);
	}


	@Override
	public int getSellerCount(Criteria cri) {
		return mapper.getSellerCount(cri);
	}


	@Override
	public int changeAutority(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.changeAutority(map);
	}


	@Override
	public int unapprovedCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.unapprovedCount(map);
	}


	@Override
	public int banCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.banCount(map);
	}


	@Override
	public int currentSeller(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.currentSeller(map);
	}


	@Override
	public SellerVO getBySid(String sid) {
		return mapper.getBySid(sid);
	}

}
