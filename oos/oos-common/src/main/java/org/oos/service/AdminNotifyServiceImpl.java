package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.AdminNotifyVO;
import org.oos.domain.Criteria;
import org.oos.mapper.AdminNotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class AdminNotifyServiceImpl implements AdminNotifyService {

	@Setter(onMethod_=@Autowired)
	private AdminNotifyMapper mapper;
	
	@Override
	public List<AdminNotifyVO> getList(Map<String, Object> map) {
		return mapper.getList(map);
	}

	@Override
	public AdminNotifyVO get(Long bno) {
		return mapper.get(bno);
	}

	@Override
	public int delete(Long bno) {
		return mapper.delete(bno);
	}

	@Override
	public int modify(AdminNotifyVO vo) {
		return mapper.modify(vo);
	}

	@Override
	public int insert(AdminNotifyVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int count(Criteria cri) {
		return mapper.count(cri);
	}

}
